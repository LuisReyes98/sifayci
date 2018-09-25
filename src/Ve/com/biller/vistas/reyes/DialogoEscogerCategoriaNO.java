/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.vistas.reyes;


import Ve.com.biller.eventos.reyes.EventoNODialogoEscogerPlatillo;
import Ve.com.biller.helpers.reyes.Botonera;

import Ve.com.biller.modelos.reyes.ModeloIconosPlatillo;
import Ve.com.biller.conexion.reyes.sql.CRUD_Menu;
import Ve.com.biller.modelos.reyes.DatosOrdenesIW;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.Color;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis_Reyes
 */
public class DialogoEscogerCategoriaNO {
    Botonera botones;//JPanel
    JOptionPane jOption;
    JDialog jDialog;
    ImageIcon[] iconos;
  
    public DialogoEscogerCategoriaNO(String titulo,Component parentComponent,String[] nombresCategorias,OrdenPanelNO ordenPanel) {
        botones= new Botonera(nombresCategorias,new GridLayout(0, 4, 5, 5));
        iconos= new ImageIcon[nombresCategorias.length];
        crearGUI(titulo,parentComponent,nombresCategorias,ordenPanel);
    }
    
    private void crearGUI(String titulo,Component parentComponent,String[] nombresCategorias,OrdenPanelNO ordenPanel) {           
        for (int i = 0; i < nombresCategorias.length; i++) {
            //Zona para añadir los iconos a categorias especificas 
            ModeloIconosPlatillo.setIconosCategorias(nombresCategorias[i], iconos,i);//metodo que coloca los iconos correctos a las categorias             
            botones.setButtonIcon(iconos[i], i);
            botones.setButtonEvent(new EventoNODialogoEscogerPlatillo(nombresCategorias[i], 
                    CRUD_Menu.getNombresPlatillos(i), parentComponent,i,this,ordenPanel), i);
        }//fin del for
        botones.setButtonsIconsNorth();//cambiar la posion del texto para estar debajo de los iconos
        botones.setButtonsBackground(Color.WHITE);
        botones.setButtonsForeground(Color.BLACK);
        botones.setButtonsFont(Tipografia.ARIAL_BOLD_16);         
        jOption= new JOptionPane(botones,JOptionPane.PLAIN_MESSAGE,JOptionPane.YES_OPTION,  null, DatosOrdenesIW.OPCION_CANCELAR);
        jDialog = jOption.createDialog( parentComponent,titulo);      
                    
        jDialog.setVisible(true); 
                          
    }
    public void cerrarDialog(){                
        jDialog.dispose();
        jDialog=null;
        jOption=null;
        botones=null;        
    }
}
