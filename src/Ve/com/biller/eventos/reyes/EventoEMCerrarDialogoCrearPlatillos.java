package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.DialogoCrearNuevoPlatilloEM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Luis Reyes
 */
public class EventoEMCerrarDialogoCrearPlatillos implements ActionListener {
    DialogoCrearNuevoPlatilloEM dialogo;

    public EventoEMCerrarDialogoCrearPlatillos(DialogoCrearNuevoPlatilloEM dialogo) {
        this.dialogo = dialogo;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        dialogo.cerrarDialog();
    }
    
    
}
