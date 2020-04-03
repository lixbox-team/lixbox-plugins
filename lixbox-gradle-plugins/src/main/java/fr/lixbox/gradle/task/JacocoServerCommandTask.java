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