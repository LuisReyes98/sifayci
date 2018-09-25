/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.vistas.reyes;


import Ve.com.biller.helpers.reyes.ImageManager;
import Ve.com.biller.helpers.reyes.TabCerrable;
import Ve.com.biller.helpers.reyes.TabAnadir;
import Ve.com.biller.helpers.reyes.VentanaInterna;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.DatosVentanasInternas;
import Ve.com.biller.modelos.reyes.DatosOrdenesIW;
import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;


/**
 *
 * @author Luis_Reyes
 */
public class OrdenInternalWindow extends VentanaInterna{    
    JTabbedPane tabbedPane;    
    
    
   
    
    public OrdenInternalWindow(String[] atributos, boolean resizable
            , boolean closable, boolean maximizable, boolean iconifiable,boolean cerrable) 
    {
        super(atributos, resizable, closable, maximizable, iconifiable,cerrable);
        
        crearGUI();      
    }
   
    private void crearGUI() {
        OrdenPanelNO ordenPanel;
        TabCerrable tabCer;
        TabAnadir tabAdd;
        
        tabbedPane=new JTabbedPane();  
        //se crea e instancia el panel de que ira a contener una orden
        tabbedPane.setOpaque(true);//para que se vea el color 
        tabbedPane.setBackground(new Color(202, 204, 211));//cambiar color tabPnae  
        tabbedPane.addTab(DatosOrdenesIW.TAB_ORDEN[0]+DatosOrdenesIW.i +DatosOrdenesIW.TAB_ORDEN[1],null); // se le asigna un nombre a la tab     
        tabCer= new TabCerrable(tabbedPane);// se crea el componente de la tab original
        tabbedPane.setTabComponentAt(0,tabCer);//se añade el componente de tab con boton de cerrar           
        ordenPanel= new OrdenPanelNO(tabbedPane);
        tabbedPane.setComponentAt(0, ordenPanel);
        //y se le añade el componente                                          
        tabbedPane.addTab(null,null); // se crea la tab que contendra al boton
        tabbedPane.setEnabledAt(1,false);//volvemos inaccesible la tab que contiene el boton añadir        
        tabAdd= new TabAnadir(tabbedPane);
        tabbedPane.setTabComponentAt(1, tabAdd);// se añade el boton de añadir tabs
        DatosOrdenesIW.i++;        
        add(tabbedPane); // se añade a la ventana interna
        
        
        try {//icono de la ventana interna
           
            this.setFrameIcon(ImageManager.iconResize(new ImageIcon(ImageIO.read(OrdenInternalWindow.class.getResource
        (DatosBotones.ICONOS_BOTONES_MAIN[3]))),16,16));
            
        } catch (IOException ex) {
            
        }
        this.setVisible(true);
        this.addInternalFrameListener(new InternalFrameAdapter() 
        { 
                //clase anonima que impide que se cierre todo sin confirmar           
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {                    
                    int respuesta; 
                    respuesta = JOptionPane.showConfirmDialog(null, 
                            DatosVentanasInternas.MENSAJES_DE_SALIDA_ORDENES[0],
                            DatosVentanasInternas.MENSAJES_DE_SALIDA_ORDENES[1],
                            JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (respuesta==JOptionPane.YES_OPTION) {
                        dispose();
                    }
                }
        });
        
    }                 
}
