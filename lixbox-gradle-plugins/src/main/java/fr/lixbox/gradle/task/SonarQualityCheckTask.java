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

        var requesterResult = "";
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