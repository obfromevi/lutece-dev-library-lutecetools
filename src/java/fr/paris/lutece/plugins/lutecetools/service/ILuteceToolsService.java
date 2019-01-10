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

import fr.paris.lutece.plugins.lutecetools.business.Component;
import fr.paris.lutece.plugins.lutecetools.business.dto.SiteBuilderConfDto;
import java.util.Collection;
import java.util.List;

/**
 * This interface provides all the methods for interact with LuteceTools services, (components, site builder, jenkins, stats ...)
 */
public interface ILuteceToolsService
{
    /**
     * Get a pom site based on a site builder conf DTO
     * 
     * @param siteBuilderConf
     *            the SiteBuilderConfDto obj which contains infos about the site to build
     * @return a XML string representing the content of the pom.xml file of the site
     */
    String getSitePom( SiteBuilderConfDto siteBuilderConf );

    /**
     * Get the Lutece components lists. Use cache = true for better perf; and cache = false for be sure to get the last informations about the components. If
     * loadFull is set to true, all the components are loaded from all the infos fillers so it can be slow
     * 
     * @param bLoadFull
     *            the boolean for fetching all the components infos from all the Infos Fillers
     * @param bCache
     *            the boolean for using cache system of LuteceTools
     * @return The collection of the components, filled with only the artifactId if loadFull is false, all the availables informations otherwise
     */
    Collection<Component> getComponentList( boolean bLoadFull, Boolean bCache );

    /**
     * Get a fully loaded component based on its artifact id. Use cache = true for better perf; and cache = false for be sure to get the last informations about
     * the component
     * 
     * @param strArtifactId
     *            The artifact id
     * @param bCache
     *            The cache boolean
     * @return the component with all the informations available
     */
    Component getFullComponent( String strArtifactId, Boolean bCache );

    /**
     * Get the full components list corresponding to an array of given components artifact id. Use cache = true for better perf; and cache = false for be sure
     * to get the last informations about the components
     * 
     * @param listArtifactId
     *            The artifact id list
     * @param bCache
     *            The cache boolean
     * @return The collection of the components, filled with all the availables informations
     */
    List<Component> getFullComponentList( String [ ] listArtifactId, Boolean bCache );

    /**
     * Serealize given component to JSON string
     * 
     * @param component
     *            The component
     * @return the string representing the component in JSON format
     */
    String getComponentAsJsonString( Component component );

}
