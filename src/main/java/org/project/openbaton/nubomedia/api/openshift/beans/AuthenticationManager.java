package org.project.openbaton.nubomedia.api.openshift.beans;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.project.openbaton.nubomedia.api.openshift.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by maa on 20.10.15.
 */
@Service
public class AuthenticationManager {

    @Autowired RestTemplate template;
    private Logger logger;
    private String suffix;

    @PostConstruct
    private void init(){
        template.setRequestFactory(new AvoidRedirectRequestFactory());
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.suffix = "/oauth/authorize";
    }

    //TODO rewrite when openshift uses correctly /oauth/token
    public String authenticate(String baseURL, String username, String password) throws UnauthorizedException{

        String res = "";

        String authBase = username + ":" + password;
        String authHeader = "Basic " + Base64.encodeBase64String(authBase.getBytes());
        logger.debug("Auth header " + authHeader);

        String url = baseURL + suffix;

        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.add("Authorization", authHeader);
        authHeaders.add("X-CSRF-Token","1");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("client_id","openshift-challenging-client")
                .queryParam("response_type","token");

        HttpEntity<String> authEntity = new HttpEntity<>(authHeaders);
        ResponseEntity<String> response = null;
        try {
            response = template.exchange(builder.build().encode().toUriString(), HttpMethod.GET, authEntity, String.class);
        } catch (ResourceAccessException e){
            return "PaaS Missing";
        }
        logger.debug("Response " + response.toString());

        if(response.getStatusCode().equals(HttpStatus.FOUND)){

            URI location = response.getHeaders().getLocation();
            logger.debug("Location " + location);
            res = this.getToken(location.toString());

        }
        else if(response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {

            throw new UnauthorizedException("Username: " + username + " password: " + password + " are invalid");
        }

        return res;
    }


    private String getToken (String header){

        return header.substring(header.indexOf("=")+1,header.indexOf("&"));

    }

}

