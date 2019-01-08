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
package fr.paris.lutece.plugins.lutecetools.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.paris.lutece.plugins.lutecetools.business.Component;
import fr.paris.lutece.plugins.lutecetools.business.dto.SiteBuilderConfDto;
import fr.paris.lutece.plugins.lutecetools.util.ILuteceToolsRestClient;
import fr.paris.lutece.portal.service.util.AppLogService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LuteceToolsService extends AbstractLuteceToolsService
{
    private ILuteceToolsRestClient _luteceToolsRestClient;

    /**
     * Constructor for Spring context file
     * 
     * @param luteceToolsRestClient
     *            The rest client for calling all the methods
     */
    LuteceToolsService( ILuteceToolsRestClient luteceToolsRestClient )
    {
        _luteceToolsRestClient = luteceToolsRestClient;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSitePom( SiteBuilderConfDto siteBuilderConf )
    {
        return _luteceToolsRestClient.getSitePom( siteBuilderConf );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getJSONArtifactIdList( )
    {
        return _luteceToolsRestClient.getJSONArtifactIdList( );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getJSONComponent( String strArtifactId, Boolean bCache )
    {
        return _luteceToolsRestClient.getJSONComponent( strArtifactId, bCache );
    }
}
