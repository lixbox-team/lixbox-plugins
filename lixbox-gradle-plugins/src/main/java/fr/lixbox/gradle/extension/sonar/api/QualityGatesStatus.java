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

public class QualityGatesStatus
{
    public static final String BUILDS = "OK";
    private String status;



    public QualityGatesStatus(String status)
    {
        this.status = status;
    }



    public String getStatus()
    {
        return status;
    }



    public boolean hasStatusGreen()
    {
        return BUILDS.equals(status);
    }



    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        QualityGatesStatus that = (QualityGatesStatus) o;
        return status != null ? status.equals(that.status) : that.status == null;
    }



    @Override
    public int hashCode()
    {
        return status != null ? status.hashCode() : 0;
    }



    @Override
    public String toString()
    {
        return "QualityGatesStatus{" + "status='" + status + '\'' + '}';
    }
}
