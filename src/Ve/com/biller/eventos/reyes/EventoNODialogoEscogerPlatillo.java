/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.DialogoEscogerCategoriaNO;
import Ve.com.biller.vistas.reyes.DialogoEscogerPlatilloNO;
import Ve.com.biller.vistas.reyes.OrdenPanelNO;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 *
 * @author Luis_Reyes
 */
public class EventoNODialogoEscogerPlatillo implements ActionListener {
    String titulo;
    String[] nombresPlatillos;
    Component parentComponent;
    int indexCatg;
    DialogoEscogerCategoriaNO dialogPrevio;
    OrdenPanelNO ordenPanel;
    public EventoNODialogoEscogerPlatillo(String titulo, String[] nombresPlatillos, Component parentComponent,int indexCatg,DialogoEscogerCategoriaNO dialogPrevio,OrdenPanelNO ordenPanel) {
        this.titulo = titulo;
        this.nombresPlatillos = nombresPlatillos;
        this.parentComponent = parentComponent;
        this.indexCatg=indexCatg;
        this.dialogPrevio=dialogPrevio;
        this.ordenPanel= ordenPanel;
    }
   
    
   
    @Override
    public void actionPerformed(ActionEvent e) {
        dialogPrevio.cerrarDialog();
       
            DialogoEscogerPlatilloNO dep= new DialogoEscogerPlatilloNO(titulo, parentComponent, nombresPlatillos,indexCatg,ordenPanel);
     
        
    }
    
}
