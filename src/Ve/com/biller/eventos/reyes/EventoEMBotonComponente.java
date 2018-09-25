package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.PanelEditMenuIW;
import Ve.com.biller.vistas.reyes.PanelEditarCategoriasEM;
import Ve.com.biller.vistas.reyes.PanelEditarPlatillosEM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Luis Reyes
 */
public class EventoEMBotonComponente implements ActionListener{
    
    PanelEditMenuIW panelEditarMenu;
    PanelEditarCategoriasEM panelCategorias;
    PanelEditarPlatillosEM[] panelPlatillos;
    String tituloNuevo;  
    boolean platillo;
    int indexPlatillo;
    
    public EventoEMBotonComponente(PanelEditMenuIW panelEditarMenu,String tituloNuevo,PanelEditarCategoriasEM panelCategorias,PanelEditarPlatillosEM[] panelPlatillos,boolean platillo,int indexPlatillo) {
        this.panelEditarMenu=panelEditarMenu;      
        this.tituloNuevo=tituloNuevo;
        this.panelCategorias= panelCategorias;
        this.panelPlatillos=panelPlatillos;
        this.platillo= platillo;
        this.indexPlatillo= indexPlatillo;        
    }       
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (platillo) {
            if(panelPlatillos[indexPlatillo]==null){
                panelPlatillos[indexPlatillo]= new PanelEditarPlatillosEM(indexPlatillo,panelEditarMenu);
            }
            panelEditarMenu.cambiarPanel(panelPlatillos[indexPlatillo]);            
        }else{
            panelEditarMenu.cambiarPanel(panelCategorias);            
        }                
        panelEditarMenu.cambiarTitulo(tituloNuevo);
    }
    
}
