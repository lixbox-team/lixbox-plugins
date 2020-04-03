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
package fr.lixbox.gradle.task;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.tasks.TaskAction;

import fr.lixbox.gradle.extension.SonarQualityTaskExtension;
import fr.lixbox.gradle.extension.sonar.api.QualityGateResponseParser;
import fr.lixbox.gradle.extension.sonar.api.SonarHttpRequester;

/**
 * Cette classe récupère le status du quality gate d'un projet dans sonar.
 * 
 * @author ludovic.terral
 */
public class SonarQualityCheckTask extends DefaultTask implements Serializable
{
    // ----------- Attributs -----------
    private static final long serialVersionUID = -120160203114345L;

    private transient QualityGateResponseParser qualityGateResponseParser = new QualityGateResponseParser();
    private transient SonarHttpRequester sonarHttpRequester = new SonarHttpRequester();

    

    // ----------- Methodes -----------
    @TaskAction
    public void checkSonarQualityGate() throws InterruptedException
    {
        SonarQualityTaskExtension extension = getProject().getExtensions().findByType(SonarQualityTaskExtension.class);
        if (extension == null)
        {
            extension = new SonarQualityTaskExtension();
        }
        TimeUnit.MILLISECONDS.sleep(extension.getWaitingTime());

        String requesterResult = "";
        try
        {
            requesterResult = sonarHttpRequester.getAPIInfo(extension);
        }
        catch(Exception e)
        {
            System.err.println(e);
        }
        
        if (!"OK".equals(qualityGateResponseParser.getQualityGateResultFromJSON(requesterResult).getStatus()))
        {
            throw new GradleException("the quality's gate is not reached!");
        }
    }
}