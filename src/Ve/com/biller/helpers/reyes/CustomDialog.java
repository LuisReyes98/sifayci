/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.helpers.reyes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author luisr
 */
public class CustomDialog implements ActionListener{
    JOptionPane optionPane;
    JDialog jDialog;
    protected JButton buttonClose;
    
    public CustomDialog() {
        createCloseButton();
    }
    private void createCloseButton(){
        buttonClose= new JButton();
        buttonClose.addActionListener(this);
    }
    
    
    protected void createDialog(Component comp,String title){
        
        optionPane= new JOptionPane(comp, JOptionPane.PLAIN_MESSAGE,JOptionPane.YES_OPTION, 
                null, new String[]{});
        jDialog= optionPane.createDialog(title);
        jDialog.setVisible(true);
    }
    
    public void close(){
        jDialog.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        close();
    }
}
