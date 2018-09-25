/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.DialogoDescripcionPlatilloNO;
import Ve.com.biller.vistas.reyes.DialogoEscogerPlatilloNO;
import Ve.com.biller.vistas.reyes.OrdenPanelNO;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 *
 * @author Luis_Reyes
 */
public class EventoNODialogoDescripcionPlatillo implements ActionListener{
       
    Component parentComponent;    
    int indexCategoria;
    int indexPlatillo;
    DialogoEscogerPlatilloNO dialogoPrevio;
    ImageIcon iconoPlatillo;
    OrdenPanelNO ordenPanel;
    boolean hamburguesa;
    
    public EventoNODialogoDescripcionPlatillo( Component parentComponent, int indexCategoria, int indexPlatillo,boolean hamburguesa, DialogoEscogerPlatilloNO dialogoPrevio,ImageIcon iconoPlatillo,OrdenPanelNO ordenPanel) {
        
        this.parentComponent = parentComponent;
        this.indexCategoria = indexCategoria;
        this.indexPlatillo = indexPlatillo;
        this.dialogoPrevio = dialogoPrevio;
        this.iconoPlatillo= iconoPlatillo;
        this.hamburguesa=hamburguesa;
        this.ordenPanel=ordenPanel;
        
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        dialogoPrevio.cerrarDialog();
        DialogoDescripcionPlatilloNO ddp= new DialogoDescripcionPlatilloNO(iconoPlatillo,parentComponent,indexCategoria ,indexPlatillo,hamburguesa,ordenPanel);
        
    }
    
}
