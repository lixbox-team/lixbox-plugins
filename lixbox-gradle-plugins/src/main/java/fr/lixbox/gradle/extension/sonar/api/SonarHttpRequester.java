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
package fr.lixbox.gradle.extension.sonar.api;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import fr.lixbox.gradle.extension.SonarQualityTaskExtension;

public class SonarHttpRequester
{
    private static final String SONAR_API_GATE = "/api/qualitygates/project_status?projectKey=%s";



    public SonarHttpRequester()
    {
    }



    public String getAPIInfo(SonarQualityTaskExtension config) throws ClientProtocolException, IOException
    {
        String sonarApiGate = config.getSonarUrl() + String.format(SONAR_API_GATE, config.getProjectKey());
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(sonarApiGate);        
        byte[] credentials = org.apache.commons.codec.binary.Base64.encodeBase64((config.getUtilisateur() + ":" + config.getPassword()).getBytes(StandardCharsets.UTF_8));
        request.setHeader("Authorization", "Basic " + new String(credentials, StandardCharsets.UTF_8));
        return executeGetRequest(client.execute(request));
    }



    private String executeGetRequest(CloseableHttpResponse response) throws IOException
    {
        try
        {
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            String returnResponse = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
            if (statusCode != 200)
            {
                throw new IOException("Expected status 200, got: " + statusCode + ". Response: " + returnResponse);
            }
            return returnResponse;
        }
        finally
        {
            try
            {
                if (response != null) response.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}