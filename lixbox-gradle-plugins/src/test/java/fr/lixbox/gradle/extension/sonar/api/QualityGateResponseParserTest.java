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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QualityGateResponseParserTest {
    private QualityGateResponseParser qualityGateResponseParser;
    private String jsonArrayString;

    @BeforeEach
    public void init() {
        qualityGateResponseParser = new QualityGateResponseParser();
        jsonArrayString = "[\n{\nid: \"455\",\nrk: \"com.opensource:quality-gates\",\nn: \"Green (was Red)\",\nc: \"Alert\",\ndt: \"2016-03-25T12:01:31+0100\",\nds: \"\"\n},\n{\nid: \"430\",\nrk: \"com.opensource:quality-gates\",\nn: \"Red (was Green)\",\nc: \"Alert\",\ndt: \"2016-03-24T16:28:40+0100\",\nds: \"Major issues variation > 2 over 30 days (2016 Mar 15), Coverage variation < 60 since previous analysis (2016 Mar 24)\"\n}]";
    }


    @Test
    void testGetQualityGateResultFromJSONWithOneObjectShouldReturnStatusError() {
        String jsonArray = "[\n{\nid: \"430\",\nrk: \"com.opensource:quality-gates\",\nn: \"Red (was Green)\",\nc: \"Alert\",\ndt: \"2016-03-24T16:28:40+0100\",\nds: \"Major issues variation > 2 over 30 days (2016 Mar 15), Coverage variation < 60 since previous analysis (2016 Mar 24)\"\n}]";
        Assertions.assertEquals(new QualityGatesStatus("ERROR"), qualityGateResponseParser.getQualityGateResultFromJSON(jsonArray));
    }

    @Test
     void testGetQualityGateResultFromJSONWithMultipleObjectsShouldReturnStatusError() {
        jsonArrayString = "[\n{\nid: \"455\",\nrk: \"com.opensource:quality-gates\",\nn: \"Red (was Red)\",\nc: \"Alert\",\ndt: \"2016-03-26T12:01:31+0100\",\nds: \"\"\n},\n{\nid: \"455\",\nrk: \"com.opensource:quality-gates\",\nn: \"Green (was Red)\",\nc: \"Alert\",\ndt: \"2016-03-25T12:01:31+0100\",\nds: \"\"\n},\n{\nid: \"430\",\nrk: \"com.opensource:quality-gates\",\nn: \"Red (was Green)\",\nc: \"Alert\",\ndt: \"2016-03-24T16:28:40+0100\",\nds: \"Major issues variation > 2 over 30 days (2016 Mar 15), Coverage variation < 60 since previous analysis (2016 Mar 24)\"\n}]";
        Assertions.assertEquals(new QualityGatesStatus("ERROR"), qualityGateResponseParser.getQualityGateResultFromJSON(jsonArrayString));
    }
}