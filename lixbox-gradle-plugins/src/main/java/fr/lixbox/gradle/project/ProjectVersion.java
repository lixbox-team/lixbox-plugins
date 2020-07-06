/*******************************************************************************
 *    
 *                           FRAMEWORK Lixbox
 *                          ==================
 *      
 * This file is part of lixbox-plugins.
 *
 *    lixbox-supervision is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    lixbox-supervision is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *    along with lixbox-plugins.  If not, see <https://www.gnu.org/licenses/>
 *   
 *   @AUTHOR Lixbox-team
 *
 ******************************************************************************/
package fr.lixbox.gradle.project;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Cette classe assure l'affichage de la version dans gradle
 * 
 * @author ludovic.terral
 */
public class ProjectVersion implements Serializable
{
    // ----------- Attributs -----------
    private static final long serialVersionUID = -201809191910L;
    private Integer major;
    private Integer medium;
    private Integer minor;
    private String build;
       
    
    
    // ----------- Methodes -----------
    public ProjectVersion(Integer major, Integer medium, Integer minor, String build)
    {
        this.major = major;
        this.medium = medium;
        this.minor = minor;
        this.build = build;
    }
        
    
    
    @Override
    public String toString()
    {
        String fullVersion = major+"."+medium+"."+minor;
        if (build!=null)
        {
            fullVersion += "."+build;
        }
        else
        {
            fullVersion += "."+Calendar.getInstance().getTimeInMillis();
        }
        return fullVersion;
    }   
}