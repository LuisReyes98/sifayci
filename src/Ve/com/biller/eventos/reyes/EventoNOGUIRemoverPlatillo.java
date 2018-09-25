/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.eventos.reyes;


import Ve.com.biller.vistas.reyes.OrdenPanelNO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Luis_Reyes
 */
public class EventoNOGUIRemoverPlatillo implements ActionListener{
    OrdenPanelNO ordenPanel;

    public EventoNOGUIRemoverPlatillo(OrdenPanelNO ordenPanel) {
        this.ordenPanel=ordenPanel;                 
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {            
        ordenPanel.guiRemoverPlatillo();
    }
    
}
