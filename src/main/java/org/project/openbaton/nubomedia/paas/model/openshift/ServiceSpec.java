/*
 * Copyright (c) 2015-2016 Fraunhofer FOKUS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.project.openbaton.nubomedia.paas.model.openshift;

/**
 * Created by maa on 25/09/2015.
 */
public class ServiceSpec {

    private Selector selector;

    private ServicePort[] ports;

    public static class ServicePort{
        String protocol;
        int port;
        int targetPort;
        String name;

        public ServicePort() {
        }

        public ServicePort(String protocol, int port, int targetPort, String name) {
            this.protocol = protocol;
            this.port = port;
            this.targetPort = targetPort;
            this.name = name;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public int getTargetPort() {
            return targetPort;
        }

        public void setTargetPort(int targetPort) {
            this.targetPort = targetPort;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public ServiceSpec() {
    }

    public ServiceSpec(Selector selector, ServicePort[] ports) {
        this.selector = selector;
        this.ports = ports;
    }

    public Selector getSelector() {
        return selector;
    }

    public void setSelector(Selector selector) {
        this.selector = selector;
    }

    public ServicePort[] getPorts() {
        return ports;
    }

    public void setPorts(ServicePort[] ports) {
        this.ports = ports;
    }
}
