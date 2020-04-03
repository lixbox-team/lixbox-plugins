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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.lixbox.gradle.extension.sonar.api.QualityGateResponseParser;
import fr.lixbox.gradle.extension.sonar.api.QualityGatesStatus;

public class QualityGateResponseParserTest {

    public static final String COM_OPENSOURCE_QUALITY_GATES = "com.opensource:quality-gates";
    public static final String GREEN_WAS_RED = "Green (was Red)";
    public static final String ALERT = "Alert";
    public static final String T12_01_31_0100 = "2016-03-25T12:01:31+0100";
    public static final String DT = "dt";
    private QualityGateResponseParser qualityGateResponseParser;

    private String jsonArrayString;

    @BeforeEach
    public void init() {
        qualityGateResponseParser = new QualityGateResponseParser();
        jsonArrayString = "[\n{\nid: \"455\",\nrk: \"com.opensource:quality-gates\",\nn: \"Green (was Red)\",\nc: \"Alert\",\ndt: \"2016-03-25T12:01:31+0100\",\nds: \"\"\n},\n{\nid: \"430\",\nrk: \"com.opensource:quality-gates\",\nn: \"Red (was Green)\",\nc: \"Alert\",\ndt: \"2016-03-24T16:28:40+0100\",\nds: \"Major issues variation > 2 over 30 days (2016 Mar 15), Coverage variation < 60 since previous analysis (2016 Mar 24)\"\n}]";
    }


    @Test
    public void testGetQualityGateResultFromJSONWithOneObjectShouldReturnStatusError() {
        String jsonArray = "[\n{\nid: \"430\",\nrk: \"com.opensource:quality-gates\",\nn: \"Red (was Green)\",\nc: \"Alert\",\ndt: \"2016-03-24T16:28:40+0100\",\nds: \"Major issues variation > 2 over 30 days (2016 Mar 15), Coverage variation < 60 since previous analysis (2016 Mar 24)\"\n}]";
        Assertions.assertEquals(new QualityGatesStatus("ERROR"), qualityGateResponseParser.getQualityGateResultFromJSON(jsonArray));
    }

    @Test
    public void testGetQualityGateResultFromJSONWithMultipleObjectsShouldReturnStatusError() {
        jsonArrayString = "[\n{\nid: \"455\",\nrk: \"com.opensource:quality-gates\",\nn: \"Red (was Red)\",\nc: \"Alert\",\ndt: \"2016-03-26T12:01:31+0100\",\nds: \"\"\n},\n{\nid: \"455\",\nrk: \"com.opensource:quality-gates\",\nn: \"Green (was Red)\",\nc: \"Alert\",\ndt: \"2016-03-25T12:01:31+0100\",\nds: \"\"\n},\n{\nid: \"430\",\nrk: \"com.opensource:quality-gates\",\nn: \"Red (was Green)\",\nc: \"Alert\",\ndt: \"2016-03-24T16:28:40+0100\",\nds: \"Major issues variation > 2 over 30 days (2016 Mar 15), Coverage variation < 60 since previous analysis (2016 Mar 24)\"\n}]";
        Assertions.assertEquals(new QualityGatesStatus("ERROR"), qualityGateResponseParser.getQualityGateResultFromJSON(jsonArrayString));
    }
}