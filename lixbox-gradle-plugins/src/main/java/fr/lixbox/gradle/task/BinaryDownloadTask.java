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

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Get;
import org.apache.tools.ant.taskdefs.Get.DownloadProgress;
import org.apache.tools.ant.taskdefs.Get.VerboseProgress;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

/**
 * Cette classe télécharge un objet vers un fichier local.
 *
 * @author ludovic.terral
 */
public class BinaryDownloadTask extends DefaultTask 
{
    // ----------- Attributs -----------   
    static final long serialVersionUID = -201809231920L;
    
    @Input private String sourceUrl;
    @OutputFile private File targetBinary;
    private Get ant;
        
    
    // ----------- Methodes -----------    
    @TaskAction    
    void download() throws MalformedURLException, IOException 
    {
        ant.doGet(new URL(sourceUrl), targetBinary, Project.MSG_INFO, (DownloadProgress) new VerboseProgress(System.out));
    }

    

    public String getSourceUrl()
    {
        return sourceUrl;
    }
    public void setSourceUrl(String sourceUrl)
    {
        this.sourceUrl = sourceUrl;
    }
    


    public File getTargetBinary()
    {
        return targetBinary;
    }
    public void setTargetBinary(File targetBinary)
    {
        this.targetBinary = targetBinary;
    }
}