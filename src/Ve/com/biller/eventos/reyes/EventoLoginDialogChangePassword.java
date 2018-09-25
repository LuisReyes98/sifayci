/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.estructuras.reyes.User;
import Ve.com.biller.vistas.reyes.DialogLoginChangePassword;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;


/**
 *
 * @author luisr
 */
public class EventoLoginDialogChangePassword implements ActionListener{
    User user;
    JDialog dialog;
    JOptionPane optionPane;
    JButton buttonChangePassword;   
    JButton buttonCancelar;
    JPasswordField[] passWord= new JPasswordField[2];       
    DialogLoginChangePassword dialogLoginChange;

    public EventoLoginDialogChangePassword(User user) {
        this.user=user;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        dialogLoginChange = new DialogLoginChangePassword(user);
        
    }
    
    
}
