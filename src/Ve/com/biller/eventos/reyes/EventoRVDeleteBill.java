/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.main.reyes.BillerMain;
import Ve.com.biller.vistas.reyes.DialogRVDeleteBill;
import Ve.com.biller.vistas.reyes.HideShowPanelRV;
import Ve.com.biller.vistas.reyes.PanelRV;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author luisr
 */
public class EventoRVDeleteBill implements ActionListener{
    HideShowPanelRV panelHideShow;
    PanelRV panelParent;
    public EventoRVDeleteBill(HideShowPanelRV panelHideShow,PanelRV panelParent) {
        this.panelHideShow = panelHideShow;
        this.panelParent= panelParent;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int j;
        j = JOptionPane.showConfirmDialog(null, "Esta acción no se puede deshacer \n¿Desea continuar con la eliminación?","Alerta de Elminación", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
        if (j==JOptionPane.YES_OPTION) {
            DialogRVDeleteBill dialog= new DialogRVDeleteBill(panelHideShow,panelParent);
            
        }
    }
    
}
