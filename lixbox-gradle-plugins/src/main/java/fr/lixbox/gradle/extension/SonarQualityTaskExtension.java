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
package fr.lixbox.gradle.extension;

import java.io.Serializable;

/**
 * Cette classe est l'extension de la task sonar.
 * 
 * @author ludovic.terral
 */
public class SonarQualityTaskExtension implements Serializable
{
    // ----------- Attributs -----------
    private static final long serialVersionUID = -120160203113845L;
    
    private String projectKey, sonarUrl, utilisateur, password = "unknown";
    private long waitingTime=1000;



    // ----------- Methodes -----------
    public SonarQualityTaskExtension()
    {    
        this.projectKey = "";
        this.sonarUrl = "";
        this.utilisateur = "";
        this.password = "";    
    }
    
    public SonarQualityTaskExtension(String projectKey, String sonarUrl, String utilisateur, String password)
    {  
        this.projectKey = projectKey;
        this.sonarUrl = sonarUrl;
        this.utilisateur = utilisateur;
        this.password = password;
    }
    
    
    
    public String getProjectKey()
    {
        return projectKey;
    }
    public void setProjectKey(String application)
    {
        this.projectKey = application;
    }



    public String getSonarUrl()
    {
        return sonarUrl;
    }
    public void setSonarUrl(String sonarUrl)
    {
        this.sonarUrl = sonarUrl;
    }



    public String getUtilisateur()
    {
        return utilisateur;
    }
    public void setUtilisateur(String utilisateur)
    {
        this.utilisateur = utilisateur;
    }



    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    
    
    public long getWaitingTime()
    {
        return waitingTime;
    }
    public void setWaitingTime(long waitingTime)
    {
        this.waitingTime = waitingTime;
    }
}