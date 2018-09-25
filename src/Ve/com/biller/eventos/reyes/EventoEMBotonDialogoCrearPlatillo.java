package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.DialogoCrearNuevoPlatilloEM;
import Ve.com.biller.vistas.reyes.PanelEditMenuIW;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Luis Reyes
 */
public class EventoEMBotonDialogoCrearPlatillo implements ActionListener {
    int index;
    Component parentComponent;
    PanelEditMenuIW panelPadre;
    public EventoEMBotonDialogoCrearPlatillo(int index, Component parentComponent,PanelEditMenuIW panelPadre) {
        this.index = index;
        this.parentComponent = parentComponent;
        this.panelPadre=panelPadre;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DialogoCrearNuevoPlatilloEM dcp = new DialogoCrearNuevoPlatilloEM(index, parentComponent,panelPadre);
        
    }
    
}
