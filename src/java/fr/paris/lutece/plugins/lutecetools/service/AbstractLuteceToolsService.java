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
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.paris.lutece.plugins.lutecetools.business.Component;
import fr.paris.lutece.portal.service.util.AppLogService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractLuteceToolsService implements ILuteceToolsService
{
    private static final ObjectMapper _mapper = new ObjectMapper( );

    /**
     * {@inheritDoc}
     */
    protected abstract String getJSONArtifactIdList( );

    /**
     * {@inheritDoc}
     */
    protected abstract String getJSONComponent( String strArtifactId, Boolean bCache );

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Component> getComponentList( boolean bLoadFull, Boolean bCache )
    {
        Collection<Component> colComponent = new ArrayList<Component>( );

        String strJSONArtifactIdList = getJSONArtifactIdList( );
        try
        {
            ComponentsHolder componentsHolder = _mapper.readValue( strJSONArtifactIdList, ComponentsHolder.class );

            if ( bLoadFull )
            {
                // All the comp are totally fetched. This process can take a long time when using remote Components WS
                for ( Component component : componentsHolder.getComponents( ) )
                {
                    String strJSONComponent = getJSONComponent( component.getArtifactId( ), bCache );
                    ComponentHolder componentHolder = _mapper.readValue( strJSONComponent, ComponentHolder.class );
                    colComponent.add( componentHolder.getComponent( ) );
                }
            }
            else
            {
                colComponent = componentsHolder.getComponents( );
            }

        }
        catch( IOException e )
        {
            AppLogService.error( " Unable to deserealize json ", e );
        }
        return colComponent;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component getFullComponent( String strArtifactId, Boolean bCache )
    {
        String strJSONComponent = getJSONComponent( strArtifactId, bCache );
        try
        {
            ComponentHolder componentHolder = _mapper.readValue( strJSONComponent, ComponentHolder.class );
            return componentHolder.getComponent( );
        }
        catch( IOException e )
        {
            AppLogService.error( " Unable to deserealize json ", e );
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public String getComponentAsJsonString( Component component )
    {
        try
        {
            return _mapper.writeValueAsString( component );
        }
        catch( IOException e )
        {
            AppLogService.error( "Unable to serealize component", e );
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Component> getFullComponentList( String [ ] listArtifactId, Boolean bCache )
    {
        List<Component> listComp = new ArrayList<>( );
        for ( String strArtifactId : listArtifactId )
        {
            Component comp = getFullComponent( strArtifactId, bCache );
            listComp.add( comp );
        }
        return listComp;
    }

    // Those two classes are used for JSON deserealisation to Component obj.
    @JsonIgnoreProperties( ignoreUnknown = true )
    public static class ComponentsHolder
    {

        @JsonProperty( "components" )
        private List<Component> _listComponents;

        public List<Component> getComponents( )
        {
            return _listComponents;
        }

        @JsonProperty( "components" )
        public void setComponents( List<Component> listComponents )
        {
            _listComponents = listComponents;
        }
    }

    @JsonIgnoreProperties( ignoreUnknown = true )
    public static class ComponentHolder
    {

        @JsonProperty( "component" )
        private Component _component;

        public Component getComponent( )
        {
            return _component;
        }

        @JsonProperty( "component" )
        public void setComponent( Component component )
        {
            _component = component;
        }
    }

}
