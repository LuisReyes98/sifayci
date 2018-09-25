/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.vistas.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Menu;
import Ve.com.biller.eventos.reyes.EventoNODialogoDescripcionPlatillo;
import Ve.com.biller.helpers.reyes.Botonera;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.DatosOrdenesIW;
import Ve.com.biller.modelos.reyes.ModeloIconosPlatillo;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.Color;

import java.awt.Component;
import java.awt.GridLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


/**
 *
 * @author Luis_Reyes
 */
public class DialogoEscogerPlatilloNO {
    Botonera botones;
    JOptionPane jOption;
    JDialog jDialog;
    int indexCatg;
    ImageIcon[] iconos;
    String[] nombresPlatillos;
    public DialogoEscogerPlatilloNO(String categoria,Component parentComponent,String[] nombresPlatillos,int indexCatg,OrdenPanelNO ordenPanel){
        this.indexCatg=indexCatg;
        this.nombresPlatillos=nombresPlatillos;
        botones= new Botonera(nombresPlatillos,new GridLayout(0, 5, 5, 5));
        iconos= new ImageIcon[nombresPlatillos.length];
        
        crearGUI(categoria,parentComponent,ordenPanel);
    }


private void crearGUI(String categoria,Component parentComponent,OrdenPanelNO ordenPanel) {
       
        
        for (int i = 0; i < nombresPlatillos.length; i++) {
            //Zona para añadir los iconos a categorias especificas            
            try{      
                switch(categoria){                    
                    case "CAFÉS":    
                       ModeloIconosPlatillo.setIconosCafes(nombresPlatillos[i], iconos, i);//los iconos individuales de cada cafe 
                        break;    
                    case "HAMBURGUESAS":
                        ModeloIconosPlatillo.setIconosHamburguesas(nombresPlatillos[i], iconos, i);
                        break;                   
                    case "MALTEADAS":
                        ModeloIconosPlatillo.setIconosMalteadas(nombresPlatillos[i], iconos, i);
                        break;
                    case "PLATOS":
                        ModeloIconosPlatillo.setIconosPlatos(nombresPlatillos[i], iconos, i);
                        break;   
                    case "ENTRADAS":
                        ModeloIconosPlatillo.setIconosEntradas(nombresPlatillos[i], iconos, i);
                        break;
                    case "EXTRAS":
                        ModeloIconosPlatillo.setIconosExtras(nombresPlatillos[i], iconos, i);
                        break;
                    case "POSTRES":
                        ModeloIconosPlatillo.setIconosPostres(nombresPlatillos[i], iconos, i);
                        break;
                    case "BEBIDAS":
                        ModeloIconosPlatillo.setIconosBebidas(nombresPlatillos[i], iconos, i);
                        break;
                        
                    default:
                          iconos[i] = new ImageIcon(ImageIO.read(DialogoEscogerPlatilloNO.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[0])));//icono por defecto                
                        break;
                }
               
                    
                  
            }catch(IOException e){ }
              
            botones.setButtonIcon(iconos[i],i);            
            botones.setButtonEvent(new EventoNODialogoDescripcionPlatillo( parentComponent, indexCatg, i,CRUD_Menu.menuCategoria[indexCatg].isHamburguesa(), this,botones.getIcon(i),ordenPanel), i);
            
        }//fin del ciclo for
        
        botones.setButtonsIconsNorth();
        botones.setButtonsBackground(Color.WHITE);
        botones.setButtonsForeground(Color.BLACK);
        botones.setButtonsFont(Tipografia.ARIAL_BOLD_16);
        
        jOption= new JOptionPane(botones,JOptionPane.PLAIN_MESSAGE,JOptionPane.YES_OPTION,  null, DatosOrdenesIW.OPCION_CANCELAR);
      
        jDialog = jOption.createDialog(parentComponent, categoria);
  
        jDialog.setVisible(true);
        
    }
      
    
    public void cerrarDialog(){                
        jDialog.dispose();
        jDialog=null;
        jOption=null;
        botones=null;        
    }
}

