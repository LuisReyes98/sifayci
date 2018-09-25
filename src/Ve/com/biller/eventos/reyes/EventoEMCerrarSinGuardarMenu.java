package Ve.com.biller.eventos.reyes;

import Ve.com.biller.helpers.reyes.DialogoCerrarSinGuardar;
import Ve.com.biller.vistas.reyes.PanelEditMenuIW;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JInternalFrame;

/**
 *
 * @author Luis Reyes
 */
public class EventoEMCerrarSinGuardarMenu implements  ActionListener {
    JInternalFrame internalFrame;
    PanelEditMenuIW panelEditar;
    DialogoCerrarSinGuardar dialogo;
    
    public EventoEMCerrarSinGuardarMenu( JInternalFrame internalFrame,PanelEditMenuIW panelEditar){
        this.internalFrame=internalFrame;
        this.panelEditar=panelEditar;
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (panelEditar.hasChangesBeenMade()) {
            dialogo= new DialogoCerrarSinGuardar(internalFrame);
        }else{
            internalFrame.dispose();
        }
    }
    
}
