/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.PanelLoginUsersManagement;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author luisr
 */
public class EventoLoginUserManagement implements ActionListener{
    PanelLoginUsersManagement panel;
    JScrollPane scrol;
    JOptionPane optionPane;
    JDialog dialog;
    public EventoLoginUserManagement() {
       
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        execute();       
    }
    public void execute(){
        panel=new PanelLoginUsersManagement(this);
        scrol= new JScrollPane(panel);
        scrol.getVerticalScrollBar().setUnitIncrement(10);
        scrol.setPreferredSize(new Dimension(480, 300));
        optionPane= new JOptionPane(scrol,JOptionPane.PLAIN_MESSAGE,
                JOptionPane.YES_OPTION,null,new String[]{"Cancelar"});
                
        dialog= optionPane.createDialog("Usuarios Registrados");
        dialog.setVisible(true);
    }

    public PanelLoginUsersManagement getPanel() {
        return panel;
    }

    public JScrollPane getScrol() {
        return scrol;
    }

    public JOptionPane getOptionPane() {
        return optionPane;
    }

    public JDialog getDialog() {
        return dialog;
    }
    
}
