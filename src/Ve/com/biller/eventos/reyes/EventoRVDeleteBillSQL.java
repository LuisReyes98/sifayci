/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Ventas;
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
public class EventoRVDeleteBillSQL implements ActionListener{
    HideShowPanelRV hideShowPanelRV;
    DialogRVDeleteBill dialogRV;
    PanelRV panelParent;
    public EventoRVDeleteBillSQL(HideShowPanelRV hideShowPanelRV,DialogRVDeleteBill dialogRV,PanelRV panelParent) {
        this.hideShowPanelRV = hideShowPanelRV;
        this.dialogRV=dialogRV;
        this.panelParent=panelParent;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (dialogRV.getTextField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "El motivo de la eliminación \n no puede ser nulo.","Error", JOptionPane.ERROR_MESSAGE);
        }else{
            CRUD_Ventas.deleteBill(hideShowPanelRV.getIdventa(),dialogRV.getTextField().getText());
            dialogRV.close();
            panelParent.getCenterPanel().remove(hideShowPanelRV);
            panelParent.getCenterPanel().repaint();
            panelParent.getCenterPanel().revalidate();          
        }
                      
    }
    
}
