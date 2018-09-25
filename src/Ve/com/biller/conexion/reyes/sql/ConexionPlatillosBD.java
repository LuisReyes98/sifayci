package Ve.com.biller.conexion.reyes.sql;
/**
 *
 * @author Luis_Reyes
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionPlatillosBD implements InterfacePlatillosBD {
    
    public static Connection connection= null;

    public static void abrirConexion(){
        try {
            
            connection= DriverManager.getConnection( URL_CONEXION_EMBED , USUARIO, CLAVE);
            
            if (connection==null) {
               JOptionPane.showMessageDialog(null,"NO se logro conexion con la base de Datos "+ BASE_DATOS ,
                "Atención",JOptionPane.INFORMATION_MESSAGE);
            }
       
        } catch ( SQLException ex) {
            Logger.getLogger(ConexionPlatillosBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    public static void cerrarConexion(){
        if (connection!=null) {
            try { 
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConexionPlatillosBD.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    
}
