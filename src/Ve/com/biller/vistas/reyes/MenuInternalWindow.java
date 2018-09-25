/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.vistas.reyes;

/**
 *
 * @author Luis_Reyes
 */
import Ve.com.biller.helpers.reyes.ImageManager;
import Ve.com.biller.helpers.reyes.TextoVentana;
import Ve.com.biller.helpers.reyes.VentanaInterna;
import Ve.com.biller.modelos.reyes.ControladorMenuIW;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.BorderLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


import javax.swing.JPanel;


import javax.swing.JScrollPane;

 
public class MenuInternalWindow extends VentanaInterna{
    JScrollPane jScroll;    
    JPanel panelContedor;
    TextoVentana textoVentana;
    
    public MenuInternalWindow(String[] atributos, boolean resizable,boolean closable, boolean maximizable, boolean iconifiable,boolean cerrable) {
        super(atributos, resizable, closable, maximizable, iconifiable,cerrable);                
        crearGUI();
    }

    private void crearGUI() {              
        panelContedor= new JPanel(new BorderLayout());
         // esa fuente para mantener el formato del texto         
        textoVentana=new TextoVentana(ControladorMenuIW.menu,false,Tipografia.MONOSPACED_BOLD_14);//se envia a la clase textoventana 
        //con el objetivo de devolver el panel con el texto
        textoVentana.getTextArea().setCaretPosition(0);//coloca la posicion de la barra del scroll arriba        
        jScroll= new JScrollPane(textoVentana.getTextArea());  //añaniendo el texto ventana a una jscrollbar   
        jScroll.getVerticalScrollBar().setUnitIncrement(15);// aumentando la velocidad de movimiento de la barra    
        panelContedor.add(jScroll,BorderLayout.CENTER);   //se añade al panel 
        
        try {//icono de la ventana interna
           
            this.setFrameIcon(ImageManager.iconResize(new ImageIcon(ImageIO.read(
                    MenuInternalWindow.class.getResource(DatosBotones.ICONOS_BOTONES_MAIN[0]))),16,16));            
        } catch (IOException ex) {
            
        }
        
        this.add(panelContedor);
        this.setVisible(true);
    }
}
