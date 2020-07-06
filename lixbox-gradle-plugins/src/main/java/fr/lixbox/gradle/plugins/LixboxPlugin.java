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