/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.modelos.reyes.DatosVentanasInternas;
import Ve.com.biller.vistas.reyes.LoginPanel;
import Ve.com.biller.vistas.reyes.MenuInternalWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;




/**
 *
 * @author Luis_Reyes
 */
public class EventoBotonVerMenu implements ActionListener {
    JDesktopPane desktopPane;
    
    String[] menu;

    public EventoBotonVerMenu(JDesktopPane desktopPane){
        this.desktopPane=desktopPane;
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(LoginPanel.accessControl()){//chequeo de que se incio sesion
            execute();
        }              
    }
    private void execute(){
        MenuInternalWindow mnw= new MenuInternalWindow(DatosVentanasInternas.ATRIBUTOS_MENU,true, true, true, true,true);
        mnw.setSize(mnw.getWidth(),desktopPane.getHeight());                
        desktopPane.add(mnw);      
        
        try {
            mnw.setSelected(true);
        }catch(PropertyVetoException ex){}
            
    
    }
}
