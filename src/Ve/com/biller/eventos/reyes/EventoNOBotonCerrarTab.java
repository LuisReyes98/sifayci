/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.eventos.reyes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTabbedPane;

/**
 *
 * @author Luis_Reyes
 */
public class EventoNOBotonCerrarTab implements ActionListener{
    JTabbedPane tabPane;
    Component component;
    int indexOfTab;

    public EventoNOBotonCerrarTab(JTabbedPane tabPane,Component component) {
        this.tabPane = tabPane;
        this.component= component;
        
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {   
        //si lo que se pasa es el tab component (Tab Cerrable)
        indexOfTab =  tabPane.indexOfTabComponent(component);        
            if (indexOfTab != -1) {
                tabPane.remove(indexOfTab);
            }
        indexOfTab= tabPane.indexOfComponent(component); //si lo que se pasa es el componente (OrdenPanel)
            if (indexOfTab != -1) {
                tabPane.remove(indexOfTab);
            }        
    }
    
    
    
}
