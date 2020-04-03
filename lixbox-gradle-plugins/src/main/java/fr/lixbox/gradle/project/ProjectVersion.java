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