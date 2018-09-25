/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.vistas.reyes;

import Ve.com.biller.estructuras.reyes.Venta;
import Ve.com.biller.estructuras.reyes.VentaDeleted;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author luisr
 */
public class HideShowRVDeleted extends HideShowPanelRV{
    VentaDeleted ventaDel;
    JLabel labelUser;
    JTextArea textArea;
    JPanel panelDeletedReason;
    
    public HideShowRVDeleted(VentaDeleted venta, PanelRV panelRV) {
        super(venta, panelRV);
        this.ventaDel=venta;
        buildGUI();
    }

    private void buildGUI() {
        panelDeletedReason= new JPanel(new BorderLayout());
                
        labelUser= new JLabel("Eliminado por: "+ventaDel.getUsuario());
        labelUser.setOpaque(true);
        labelUser.setBackground(Color.WHITE);
        labelUser.setForeground(Color.BLACK);        
        labelUser.setFont(Tipografia.ARIAL_BOLD_12);  
        
        textArea= new JTextArea("Razón de la Eliminación: "+ventaDel.getReason_delete());
        textArea.setForeground(Color.BLACK);        
        textArea.setFont(Tipografia.ARIAL_BOLD_12);  
        textArea.setEditable(false);        
        textArea.setOpaque(true);
        textArea.setBackground(Color.WHITE);
        textArea.setLineWrap(true);                  
        panelDeletedReason.add(labelUser,BorderLayout.NORTH);
        panelDeletedReason.add(textArea,BorderLayout.CENTER);
        
        //"cell 0 6 2 1" esta es la ultima linea que toca el metodo del cual hereda
        panelPrintable.add(panelDeletedReason,"cell 0 7 2 1,growx");
        
        botonera.removeButtonAt(2);
    }
    
}
