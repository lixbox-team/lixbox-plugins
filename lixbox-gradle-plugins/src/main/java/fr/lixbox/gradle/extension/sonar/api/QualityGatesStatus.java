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
