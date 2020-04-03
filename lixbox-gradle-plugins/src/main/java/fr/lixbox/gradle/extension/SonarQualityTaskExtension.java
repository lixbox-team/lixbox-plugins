/*******************************************************************************
 *    
 *                           FRAMEWORK Lixbox
 *                          ==================
 *      
 *   Copyrigth - LIXTEC - Tous droits reserves.
 *   
 *   Le contenu de ce fichier est la propriete de la soci�t� Lixtec.
 *   
 *   Toute utilisation de ce fichier et des informations, sous n'importe quelle
 *   forme necessite un accord ecrit explicite des auteurs
 *   
 *   @AUTHOR Ludovic TERRAL
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