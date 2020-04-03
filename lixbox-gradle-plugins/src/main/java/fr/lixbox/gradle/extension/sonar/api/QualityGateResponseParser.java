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

public class QualityGateResponseParser
{
    public QualityGatesStatus getQualityGateResultFromJSON(String jsonString)
    {
        if (jsonString.contains("\"projectStatus\":{\"status\":\"OK\""))
        {
            return new QualityGatesStatus("OK");
        }
        else
        {            
            return new QualityGatesStatus("ERROR");
        }
    }
}
