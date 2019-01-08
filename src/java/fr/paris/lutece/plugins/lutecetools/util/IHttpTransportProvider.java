/*
 * Copyright (c) 2002-2016, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.lutecetools.util;

import java.util.Map;

/**
 * This interface provide all the used method for interacting with an HTTP Rest enpoint. This is used for providing differents HTTPClient, such as the client
 * defined in Lutece HttpAccess library
 */
public interface IHttpTransportProvider
{
    /**
     * make POST request on given url with params and headers
     * 
     * @param strUrl
     *            url to call
     * @param mapParams
     *            params to post
     * @param mapHeadersRequest
     *            headers of the request
     * @return response body as String
     */
    String doPost( String strUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest );

    /**
     * make POST request on given url with params and headers
     * 
     * @param <T>
     * @param strUrl
     *            url to call
     * @param mapParams
     *            params to post
     * @param mapHeadersRequest
     *            headers of the request
     * @param jsonClass
     * @return response body as String
     */
    <T> T doPost( String strUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest, Class<T> jsonClass );

    /**
     * make POST request on given url with params and headers of a JSON object to retrieve another JSON
     * 
     * @param strUrl
     *            url to call
     * @param mapParams
     *            params to post
     * @param mapHeadersRequest
     *            headers of the request
     * @param strJson
     * @return the return string of the endpoint
     */
    String doPostJSON( String strUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest, String strJson );

    /**
     * make POST request on given url with params and headers of a JSON object to retrieve another JSON
     * 
     * @param <T>
     * @param strUrl
     * @param mapParams
     * @param mapHeadersRequest
     * @param strJson
     * @param jsonClass
     * @return
     */
    <T> T doPostJSON( String strUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest, String strJson, Class<T> jsonClass );

    /**
     * Make a Get request on given url with parameters
     * 
     * @param strEndPointUrl
     *            url
     * @param mapParams
     *            param to add to url
     * @param mapHeadersRequest
     *            request header
     * @return the return string of the endpoint
     */
    String doGet( String strEndPointUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest );

    /**
     * Make a Get request on given url with parameters
     * 
     * @param <T>
     * @param strEndPointUrl
     *            url
     * @param mapParams
     *            param to add to url
     * @param mapHeadersRequest
     *            request header
     * @param jsonClass
     * @return the return string of the endpoint
     */
    <T> T doGet( String strEndPointUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest, Class<T> jsonClass );

}
