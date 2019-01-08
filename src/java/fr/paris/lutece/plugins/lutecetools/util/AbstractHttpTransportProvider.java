/*
 * Copyright (c) 2002-2018, Mairie de Paris
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

import java.io.IOException;
import java.util.Map;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public abstract class AbstractHttpTransportProvider implements IHttpTransportProvider
{
    private static Logger _logger = Logger.getLogger( AbstractHttpTransportProvider.class );
    private static ObjectMapper mapper = new ObjectMapper( );

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T doPost( String strUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest, Class<T> jsonClass )
    {
        mapHeadersRequest.put( HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON );
        String strOutput = doPost( strUrl, mapParams, mapHeadersRequest );
        return deserealize( strOutput, jsonClass );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T doPostJSON( String strUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest, String strJson, Class<T> jsonClass )
    {
        mapHeadersRequest.put( HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON );
        String strOutput = doPostJSON( strUrl, mapParams, mapHeadersRequest, strJson );
        return deserealize( strOutput, jsonClass );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T doGet( String strUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest, Class<T> jsonClass )
    {
        mapHeadersRequest.put( HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON );
        String strOutput = doGet( strUrl, mapParams, mapHeadersRequest );
        return deserealize( strOutput, jsonClass );
    }

    /**
     * Deserealize a string a to given java class instance
     * 
     * @param <T>
     * @param strOutput
     * @param jsonClass
     * @return a java object representing the strOutput
     */
    <T> T deserealize( String strOutput, Class<T> jsonClass )
    {
        try
        {
            T obj = mapper.readValue( strOutput, jsonClass );
            return obj;
        }
        catch( IOException e )
        {
            _logger.error( "Unable to deserealize given json output to java class " + jsonClass, e );
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract String doPost( String strUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest );

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract String doPostJSON( String strUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest, String strJson );

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract String doGet( String strEndPointUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest );
}
