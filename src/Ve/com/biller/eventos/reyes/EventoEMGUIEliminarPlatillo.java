package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.PanelEditarPlatillosEM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Luis Reyes
 */
public class EventoEMGUIEliminarPlatillo implements ActionListener{
    PanelEditarPlatillosEM panelEditar;

    public EventoEMGUIEliminarPlatillo(PanelEditarPlatillosEM panelEditar) {
        this.panelEditar = panelEditar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panelEditar.guiEliminarPlatillos();
    }
    
}
