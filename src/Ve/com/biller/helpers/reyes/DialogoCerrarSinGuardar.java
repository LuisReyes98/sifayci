package Ve.com.biller.helpers.reyes;

import Ve.com.biller.modelos.reyes.DatosVentanasInternas;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Reyes
 */
public class DialogoCerrarSinGuardar extends JOptionPane{
    int respuesta;
   
    public DialogoCerrarSinGuardar(JInternalFrame frame) {
        dialogoCerrar(frame);
    }
    
    private void dialogoCerrar(JInternalFrame frame){        
        respuesta = JOptionPane.showConfirmDialog(null, 
                DatosVentanasInternas.MENSAJES_DE_SALIDA_EDITAR_MENU[0],
                DatosVentanasInternas.MENSAJES_DE_SALIDA_EDITAR_MENU[1],
                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
        if (respuesta==JOptionPane.YES_OPTION) {
            frame.dispose();
        }
    
    }
    
}
