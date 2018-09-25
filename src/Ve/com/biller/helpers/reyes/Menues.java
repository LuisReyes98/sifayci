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
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;


public class Menues extends JMenuBar{
    JMenu[] jMenu;
    JMenuItem[] jMenuItem;
    int cantidad;
    
    public Menues(String[] nombresJMenu, char[] letras){
        crearGUI(nombresJMenu,letras);
        
    }

    private void crearGUI(String[] nombresJMenu, char[] letras) {
        cantidad = nombresJMenu.length; 
        jMenu = new JMenu[cantidad];
        
        for (int i = 0; i < cantidad; i++) {
            jMenu[i]= new JMenu(nombresJMenu[i]);
            jMenu[i].setMnemonic(letras[i]);
              
            add(jMenu[i]);
        }   
        


    }
    
    public void setRellenarJMenuItem(String[] nombresJMenuItem, int indiceJMenu){
        cantidad= nombresJMenuItem.length;
        jMenuItem= new JMenuItem[cantidad];
        for (int i = 0; i < cantidad; i++) {
            if(nombresJMenuItem[i].equals("-")){
                jMenu[indiceJMenu].add(new JSeparator());
                
            }else{
            jMenuItem[i]= new JMenuItem(nombresJMenuItem[i]);
            jMenu[indiceJMenu].add(jMenuItem[i]);
            }
        }       
    }
    public void asignarOyenteMenu (ActionListener evento,int indiceJMenuItem){
        if(indiceJMenuItem>=0 && indiceJMenuItem<=cantidad){
            jMenuItem[indiceJMenuItem].addActionListener(evento);
        }                
    }
    
    
    
}
