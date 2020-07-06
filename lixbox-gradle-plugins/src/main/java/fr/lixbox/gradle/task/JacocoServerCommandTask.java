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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

/**
 * Cette classe récupère le status du quality gate d'un projet dans sonar.
 * 
 * @author ludovic.terral
 */
public class JacocoServerCommandTask extends DefaultTask implements Serializable
{
    // ----------- Attributs -----------
    private static final long serialVersionUID = -201602191755L;
    private static final Log LOG = LogFactory.getLog(JacocoServerCommandTask.class);
    
    @Input private String host;
    @Input private Integer port;
    @Input private String command;

    
    
    // ----------- Methodes -----------    
    @TaskAction
    public void sendCommandOnJacocoServer()
    {
        try(
            Socket socket = new Socket(host, port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(),Charset.forName("UTF-8")));
            )
        {
            String fromServer = "";
            do{
                Thread.sleep(100);
                fromServer = in.readLine();
            }
            while (!"Hi!".equals(fromServer));
            out.println(command);
        } 
        catch (UnknownHostException e) 
        {
            LOG.fatal("Don't know about host " + host);
        }
        catch (IOException e) 
        {
            LOG.fatal("Couldn't get I/O for the connection to " + host);
        }
        catch (InterruptedException e)
        {
            LOG.fatal("Couldn't get I/O for the connection to " + host);
        }
    }



    public String getHost()
    {
        return host;
    }
    public void setHost(String host)
    {
        this.host = host;
    }



    public Integer getPort()
    {
        return port;
    }
    public void setPort(Integer port)
    {
        this.port = port;
    }



    public String getCommand()
    {
        return command;
    }
    public void setCommand(String command)
    {
        this.command = command;
    }
}