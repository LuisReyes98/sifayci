/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.OrdenInternalWindow;
import Ve.com.biller.modelos.reyes.DatosVentanasInternas;
import Ve.com.biller.vistas.reyes.LoginPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;



/**
 *
 * @author Luis_Reyes
 */
public class EventoBotonNuevaOrden implements ActionListener {
    JDesktopPane desktopPane;
   

    
    public EventoBotonNuevaOrden(JDesktopPane desktopPane) {
        this.desktopPane=desktopPane;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {        
        if(LoginPanel.accessControl()){//chequeo de que se incio sesion
            execute();
        }
    }
    
    private void execute(){
        OrdenInternalWindow ordenInternal = new OrdenInternalWindow(DatosVentanasInternas.ATRIBUTOS_ORDENES,
                true, true, true, true,false);
        //ultimo atributo es para que al cerrar no haga nada        
        
       ordenInternal.setSize(desktopPane.getWidth(),desktopPane.getHeight());
       
       posTopRightCorner(ordenInternal);    
        
        desktopPane.add(ordenInternal);
        try {
            ordenInternal.setSelected(true);
        }catch(PropertyVetoException ex){}
    }

    private void posTopRightCorner(OrdenInternalWindow ordenInternal){ 
//posiciona la ventana interna en la esquina superior derecha
        Dimension desktopSize= desktopPane.getSize();
        Dimension fiwSize= ordenInternal.getSize();
        int x = (desktopSize.width - fiwSize.width);
        int y = 0;
        ordenInternal.setLocation(x, y);
        
    }
    
    

    
    
}
