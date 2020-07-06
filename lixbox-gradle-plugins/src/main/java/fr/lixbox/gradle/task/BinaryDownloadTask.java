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