/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.conexion.reyes.sql;

/**
 *
 * @author Luis_Reyes
 */
public interface InterfacePlatillosBD {
    public final String          
            BASE_DATOS              = "datos.db",
            USUARIO                 = "",
            CLAVE                   = "",
            URL_CONEXION_EMBED      = "jdbc:sqlite:"+BASE_DATOS;
       
    
}
