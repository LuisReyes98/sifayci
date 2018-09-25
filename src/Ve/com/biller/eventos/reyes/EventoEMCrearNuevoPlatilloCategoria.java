package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.DialogoCrearNuevoPlatilloEM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Luis Reyes
 */
public class EventoEMCrearNuevoPlatilloCategoria implements ActionListener {
    DialogoCrearNuevoPlatilloEM dialogo;

    public EventoEMCrearNuevoPlatilloCategoria(DialogoCrearNuevoPlatilloEM dialogo) {
        this.dialogo = dialogo;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        dialogo.crearPlatilloNuevo();
    }
    
}
