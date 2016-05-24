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

/**
 * Read information from a column of input (type text) elements and return an
 * array to use as a basis for sorting.
 *
 *  @summary Sorting based on the values of `dt-tag input` elements in a column.
 *  @name Input element data source
 *  @requires DataTables 1.10+
 *  @author [Allan Jardine](http://sprymedia.co.uk)
 */

$.fn.dataTable.ext.order['dom-text'] = function  ( settings, col )
{
	return this.api().column( col, {order:'index'} ).nodes().map( function ( td, i ) {
		return $('input', td).val();
	} );
};
