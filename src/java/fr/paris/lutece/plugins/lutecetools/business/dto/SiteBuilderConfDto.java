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
package fr.paris.lutece.plugins.lutecetools.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.paris.lutece.plugins.lutecetools.business.Component;
import java.util.List;

/**
 * This object is used for configure a site pom building task
 */
public class SiteBuilderConfDto
{
    private static final String DEFAULT_ARTIFACT_ID = "site-mysite";
    private static final String DEFAULT_GROUP_ID = "fr.paris.lutece";
    private static final String DEFAULT_NAME = "My site";
    private static final String DEFAULT_VERSION = "1.0.0-SNAPSHOT";
    private static final String DEFAULT_TYPE = "lutece-site";

    @JsonProperty( "site_name" )
    private String _strSiteName;
    @JsonProperty( "artifact_id" )
    private String _strArtifactId;
    @JsonProperty( "components" )
    private List<Component> _listComponents;
    @JsonProperty( "group_id" )
    private String _strGroupId;
    @JsonProperty( "version" )
    private String _strVersion;
    @JsonProperty( "component_type" )
    private String _strComponentType;

    /**
     * Default constructor
     */
    public SiteBuilderConfDto( )
    {
        _strSiteName = DEFAULT_NAME;
        _strArtifactId = DEFAULT_ARTIFACT_ID;
        _strGroupId = DEFAULT_GROUP_ID;
        _strVersion = DEFAULT_VERSION;
        _strComponentType = DEFAULT_TYPE;
    }

    /**
     * Get the site name
     * 
     * @return the site name
     */
    @JsonProperty( "site_name" )
    public String getSiteName( )
    {
        return _strSiteName;
    }

    /**
     * Set the site name of the site to build
     * 
     * @param strSiteName
     *            the site name
     */
    @JsonProperty( "site_name" )
    public void setSiteName( String strSiteName )
    {
        if ( strSiteName != null && !strSiteName.isEmpty( ) )
        {
            _strSiteName = strSiteName;
        }
    }

    /**
     * Set the artifact id of the site to build
     * 
     * @return the artifact id
     */
    @JsonProperty( "artifact_id" )
    public String getArtifactId( )
    {
        return _strArtifactId;
    }

    /**
     * Set the artifact id of the site to build
     * 
     * @param strArtifactId
     */
    @JsonProperty( "artifact_id" )
    public void setArtifactId( String strArtifactId )
    {
        if ( strArtifactId != null && !strArtifactId.isEmpty( ) )
        {
            _strArtifactId = strArtifactId;
        }
    }

    /**
     * Get the components list of the site to build
     * 
     * @return the components list
     */
    @JsonProperty( "components" )
    public List<Component> getListComponents( )
    {
        return _listComponents;
    }

    /**
     * Set the components list of the site to build
     * 
     * @param listComponents
     *            the components list
     */
    @JsonProperty( "components" )
    public void setListComponents( List<Component> listComponents )
    {
        _listComponents = listComponents;
    }

    /**
     * Get the group id of the site
     * 
     * @return the group id of the site
     */
    @JsonProperty( "group_id" )
    public String getGroupId( )
    {
        return _strGroupId;
    }

    /**
     * Set the group id of the site
     * 
     * @param strGroupId
     *            the group id of the site
     */
    @JsonProperty( "group_id" )
    public void setGroupId( String strGroupId )
    {
        if ( strGroupId != null && !strGroupId.isEmpty( ) )
        {
            _strGroupId = strGroupId;
        }
    }

    /**
     * Get the version of the site
     * 
     * @return the version of the site
     */
    @JsonProperty( "version" )
    public String getVersion( )
    {
        return _strVersion;
    }

    /**
     * Set the version of the site
     * 
     * @param strVersion
     *            the version of the site
     */
    @JsonProperty( "version" )
    public void setVersion( String strVersion )
    {
        if ( strVersion != null && !strVersion.isEmpty( ) )
        {
            _strVersion = strVersion;
        }
    }

    /**
     * Get the component type
     * 
     * @return the component type of the site
     */
    @JsonProperty( "component_type" )
    public String getComponentType( )
    {
        return _strComponentType;
    }

    /**
     * Set the component type of the site
     * 
     * @param strComponentType
     *            the component type of the site
     */
    @JsonProperty( "component_type" )
    public void setComponentType( String strComponentType )
    {
        if ( strComponentType != null && !strComponentType.isEmpty( ) )
        {
            _strComponentType = strComponentType;
        }
    }

}
