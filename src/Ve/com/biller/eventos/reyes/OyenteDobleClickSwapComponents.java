package Ve.com.biller.eventos.reyes;


import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;


/**
 *
 * @author Luis Reyes
 */
public class OyenteDobleClickSwapComponents extends MouseAdapter{       
    String posicion;
    Component newComponent;
    Component oldComponent;
    JPanel panel;//its assumed the panel is using mig layout
    
    public OyenteDobleClickSwapComponents(String posicion,  Component newComponent, Component oldComponent, JPanel panel) {
        this.posicion = posicion;
        this.newComponent = newComponent;
        this.oldComponent = oldComponent;
        this.panel = panel;       
    }    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //evento cuando se hace click en el componente
        if (e.getClickCount() == 2 && !e.isConsumed()) {
            e.consume();
            //handle double click event.
            panel.remove(oldComponent);          
            panel.add(newComponent,posicion);            
            newComponent.requestFocusInWindow();           
            panel.revalidate();
            panel.repaint();                    
        }
    }

    
}
