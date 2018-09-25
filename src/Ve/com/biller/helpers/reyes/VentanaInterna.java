/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.helpers.reyes;

/**
 *
 * @author Luis Reyes
 */


import javax.swing.JInternalFrame;

public class VentanaInterna extends JInternalFrame {

    public VentanaInterna(String[] atributos, boolean resizable, boolean closable, 
            boolean maximizable, boolean iconifiable,boolean cerrable) {
        
        //cloasble permite que aparesca el boton de cerrar , cerrable hara que el boton haga o no nada 
        super(atributos[0], resizable, closable, maximizable, iconifiable);
        crearGui(atributos,cerrable);
        
    }

    private void crearGui(String[] atributos,boolean cerrable) {
        this.setSize(Integer.parseInt(atributos[1]),Integer.parseInt(atributos[2]));     
        
        if (cerrable) {
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Para cerrar solo la sub ventana
        }else{
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // Para cerrar solo la sub ventana
        }
            
        
        this.setVisible(true);        
    }
    
    
    
}
