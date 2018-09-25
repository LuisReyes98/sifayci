/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.eventos.reyes;


import Ve.com.biller.vistas.reyes.DialogoEscogerCategoriaNO;
import Ve.com.biller.vistas.reyes.OrdenPanelNO;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 *
 * @author Luis_Reyes
 */
public class EventoNODialogoEscogerCategoria implements ActionListener{
    String titulo;
    String[] nombresBotones;
    Component parentComponent;
    OrdenPanelNO ordenPanel;
    
    public EventoNODialogoEscogerCategoria(String titulo,String[] nombresBotones, Component parentComponent,OrdenPanelNO ordenPanel) {
        this.titulo=titulo;
        this.nombresBotones = nombresBotones;
        this.parentComponent = parentComponent;  
        this.ordenPanel= ordenPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {   
        
        DialogoEscogerCategoriaNO dec= new DialogoEscogerCategoriaNO(titulo, parentComponent, nombresBotones,ordenPanel);
    }
    
}
