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