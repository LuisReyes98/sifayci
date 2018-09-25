
package Ve.com.biller.main.reyes;
/**
 * SiFaYCI [Colors Burgers Ver.] 
 * @author Luis_Reyes
 * C.I.: 27.097.304 
 * @version BETA 0.9
 */
import Ve.com.biller.vistas.reyes.MainWindow;
import Ve.com.biller.modelos.reyes.DatosVentana;
import java.io.IOException;


public class BillerMain {
    public static MainWindow ven1;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */     
    public static void main(String[] args) throws IOException {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // your logic here
                    ven1 =new MainWindow(DatosVentana.ATRIBUTOS_VENTANA,false);
                } catch (IOException ex) {
                    
                }
            }
        });
        
        //segundo atributo es para que la ventana sea cerrable          
        //si es verdadero la ventana se puede cerrar y si es falso no se puede cerrar  
        
    }    
}
