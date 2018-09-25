package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.DialogoCrearNuevaCategoriaEM;
import Ve.com.biller.vistas.reyes.PanelEditMenuIW;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Luis Reyes
 */
public class EventoEMBotonDialogoCrearCategoria implements ActionListener{
    Component parentComponent;
    PanelEditMenuIW panelPadre;
    public EventoEMBotonDialogoCrearCategoria(Component parentComponent,PanelEditMenuIW panelPadre) {
        this.parentComponent = parentComponent;
        this.panelPadre=panelPadre;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DialogoCrearNuevaCategoriaEM dcnc= new DialogoCrearNuevaCategoriaEM(parentComponent,panelPadre);
    }
    
}
