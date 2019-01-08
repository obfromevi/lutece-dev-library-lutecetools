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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.paris.lutece.plugins.lutecetools.business.dto.SiteBuilderConfDto;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 * IdentityRestClientService
 */
public final class LuteceToolsHttpRestClient implements ILuteceToolsRestClient
{
    private final IHttpTransportProvider _httpTransportProvider;
    private final String _strLuteceToolsEndPoint;
    private final ObjectMapper _mapper = new ObjectMapper( );
    private static Logger _logger = Logger.getLogger( LuteceToolsHttpRestClient.class );

    /**
     * Constructor for the LuteceTools Rest Client, with given httpTransportProvider and given base endpoint of the rest service.
     * 
     * @param transportProvider
     *            The transport provider
     * @param strLuteceToolsEndPoint
     *            The lutece tools endpoint
     */
    LuteceToolsHttpRestClient( IHttpTransportProvider transportProvider, String strLuteceToolsEndPoint )
    {
        _httpTransportProvider = transportProvider;
        _strLuteceToolsEndPoint = strLuteceToolsEndPoint;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSitePom( SiteBuilderConfDto siteBuilderConfDto )
    {
        String strSitePomEndPoint = _strLuteceToolsEndPoint + Constants.PATH_POM_BUILDER + Constants.PATH_SITE;
        String strReturn = null;
        try
        {
            String strJSON = _mapper.writeValueAsString( siteBuilderConfDto );
            strReturn = _httpTransportProvider.doPostJSON( strSitePomEndPoint, null, null, strJSON );
        }
        catch( JsonProcessingException e )
        {
            _logger.error( " Unable to convert siteBuilderConfDto to json string ", e );
        }

        return strReturn;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getJSONArtifactIdList( )
    {
        String strArtifactIdListEndPoint = _strLuteceToolsEndPoint + Constants.PATH_COMPONENT + Constants.CONSTANT_SLASH + Constants.PATH_ALL;
        Map<String, String> mapParamRequest = new HashMap<>( );
        mapParamRequest.put( Constants.PARAM_FORMAT, Constants.CONSTANT_JSON );
        return _httpTransportProvider.doGet( strArtifactIdListEndPoint, mapParamRequest, null );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getJSONComponent( String strArtifactId, Boolean bCache )
    {
        String strArtifactIdListEndPoint = _strLuteceToolsEndPoint + Constants.PATH_COMPONENT + Constants.CONSTANT_SLASH + strArtifactId;

        Map<String, String> mapParamRequest = new HashMap<>( );
        mapParamRequest.put( Constants.PARAM_FORMAT, Constants.CONSTANT_JSON );
        mapParamRequest.put( Constants.PARAM_CACHE, Boolean.toString( bCache ) );

        return _httpTransportProvider.doGet( strArtifactIdListEndPoint, mapParamRequest, null );
    }

}
