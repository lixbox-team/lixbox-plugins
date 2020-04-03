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
package fr.lixbox.gradle.plugins;

import java.io.Serializable;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import fr.lixbox.gradle.extension.SonarQualityTaskExtension;
import fr.lixbox.gradle.task.BinaryDownloadTask;
import fr.lixbox.gradle.task.JacocoServerCommandTask;
import fr.lixbox.gradle.task.SonarQualityCheckTask;

/**
 * Cette classe est le plugin gerant les tâches spécifiques du Lixbox.
 * 
 * @author ludovic.terral
 */
public class LixboxPlugin implements Serializable, Plugin<Project>
{
    // ----------- Attributs -----------
    private static final long serialVersionUID = -120160203112300L;



    // ----------- Methodes -----------
    @Override
    public void apply(Project project)
    {
        project.getExtensions().create("sonarQuality", SonarQualityTaskExtension.class);
        project.getTasks().create("checkSonarQualityGate", SonarQualityCheckTask.class);
        project.getTasks().create("binaryDownload", BinaryDownloadTask.class);
        project.getTasks().create("jacocoSendServerCommand", JacocoServerCommandTask.class);
    }
}