/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.helpers.reyes;

import Ve.com.biller.modelos.reyes.DatosVentana;
import Ve.com.biller.conexion.reyes.sql.ConexionPlatillosBD;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Reyes
 */
public class DialogoCerrar extends JOptionPane {
    int respuesta;
   
    
    public DialogoCerrar(){
        
        crearDialogo();
    }

    private void crearDialogo() {  
       
      
        respuesta=showConfirmDialog(null,DatosVentana.MENSAJES_DE_SALIDA[0], DatosVentana.MENSAJES_DE_SALIDA[1], YES_NO_OPTION,QUESTION_MESSAGE);
        if (respuesta==YES_OPTION) {
            ConexionPlatillosBD.cerrarConexion();//cerrar la conexion al base de datos
            
            System.exit(0);//cerrar el programa
            
        }
        
    }
}
