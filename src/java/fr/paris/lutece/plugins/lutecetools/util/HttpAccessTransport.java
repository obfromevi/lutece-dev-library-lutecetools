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

import fr.paris.lutece.util.httpaccess.HttpAccess;
import fr.paris.lutece.util.httpaccess.HttpAccessException;
import java.net.URISyntaxException;
import org.apache.commons.lang.StringUtils;

import org.apache.http.client.utils.URIBuilder;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * IHttpTransportProvider which use library-httpaccess
 */
public class HttpAccessTransport extends AbstractHttpTransportProvider
{
    private static Logger _logger = Logger.getLogger( HttpAccessTransport.class );

    /**
     * {@inheritDoc}
     */
    @Override
    public String doPost( String strUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest )
    {
        HttpAccess clientHttp = new HttpAccess( );
        Map<String, String> mapHeadersResponse = new HashMap<String, String>( );

        String strOutput = StringUtils.EMPTY;

        try
        {
            strOutput = clientHttp.doPost( strUrl, mapParams, null, null, mapHeadersRequest, mapHeadersResponse );
        }
        catch( HttpAccessException e )
        {
            handleException( e );
        }

        return strOutput;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String doPostJSON( String strUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest, String strJson )
    {
        HttpAccess clientHttp = new HttpAccess( );
        Map<String, String> mapHeadersResponse = new HashMap<String, String>( );

        if ( mapHeadersRequest == null )
            mapHeadersRequest = new HashMap<>( );
        mapHeadersRequest.put( HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON );

        String strResponse = null;

        try
        {
            strResponse = clientHttp.doPostJSON( strUrl, strJson, mapHeadersRequest, mapHeadersResponse );
        }
        catch( HttpAccessException e )
        {
            handleException( e );
        }

        return strResponse;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String doGet( String strEndPointUrl, Map<String, String> mapParams, Map<String, String> mapHeadersRequest )
    {
        HttpAccess clientHttp = new HttpAccess( );
        String strResponse = null;

        try
        {
            URIBuilder uriBuilder = new URIBuilder( strEndPointUrl );

            if ( ( mapParams != null ) && !mapParams.isEmpty( ) )
            {
                for ( String strParamKey : mapParams.keySet( ) )
                {
                    uriBuilder.addParameter( strParamKey, mapParams.get( strParamKey ) );
                }
            }

            strResponse = clientHttp.doGet( uriBuilder.toString( ), null, null, mapHeadersRequest );
        }
        catch( HttpAccessException | URISyntaxException e )
        {
            handleException( e );
        }

        return strResponse;
    }

    /**
     * add error log and throw correct Exception depending on the specified Exception
     * 
     * @param e
     *            root exception
     */
    private void handleException( Exception e )
    {
        String strError = "LibraryLuteceTools - Error HttpAccessTransport :";
        _logger.error( strError + e.getMessage( ), e );
    }

}
