/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.vistas.reyes;

import Ve.com.biller.eventos.reyes.EventoRVDeleteBillSQL;
import Ve.com.biller.helpers.reyes.CustomDialog;
import Ve.com.biller.helpers.reyes.WordsOnlyDocFilter;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;
import net.miginfocom.swing.MigLayout;

public class DialogRVDeleteBill extends CustomDialog{
    JPanel panelContainer;
    JTextField textField;
    JButton buttonDelete;
    HideShowPanelRV hideShow;
    PanelRV panelParent;

    public DialogRVDeleteBill(HideShowPanelRV hideShow,PanelRV panelParent) {
        this.hideShow=hideShow;
        this.panelParent= panelParent;
        create();
    }
    private void create(){
        PlainDocument doc;
        JLabel labelTexto;
        panelContainer=new JPanel(new MigLayout());
        
        labelTexto= new JLabel("Describa la razón de la eliminación:");
        labelTexto.setFont(Tipografia.ARIAL_14);
        labelTexto.setForeground(Color.BLACK);
        
        buttonDelete= new JButton("Eliminar");
        buttonDelete.setForeground(Color.BLACK);
        buttonDelete.setFont(Tipografia.ARIAL_14);
        buttonDelete.addActionListener(new EventoRVDeleteBillSQL(hideShow, this,panelParent));
        
        buttonClose.setText("Cancelar");
        buttonClose.setForeground(Color.BLACK);
        buttonClose.setFont(Tipografia.ARIAL_14);
        
        textField= new JTextField();
        doc=(PlainDocument)textField.getDocument();
        doc.setDocumentFilter(new WordsOnlyDocFilter());//filtro para permitir solo palabras 
        textField.setForeground(Color.BLACK);
        textField.setFont(Tipografia.ARIAL_14);
        
        panelContainer.add(labelTexto,      "cell 0 0 2 1");
        panelContainer.add(textField,       "cell 0 1 2 1,growx");       
        panelContainer.add(buttonDelete,    "cell 0 2 1 1");
        panelContainer.add(buttonClose,     "cell 1 2 1 1");

        this.createDialog(panelContainer, "Elminar Factura");

    }

    public JTextField getTextField() {
        return textField;
    }
    
}
