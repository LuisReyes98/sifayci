/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.DialogLogin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Luis Reyes
 */
public class EventoLoginButton implements ActionListener{
    DialogLogin dialogLogin;
    /**
     * Evento publico de llamado al login 
     * @param dialogLogin 
     */
    public EventoLoginButton(DialogLogin dialogLogin) {
        this.dialogLogin = dialogLogin;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        dialogLogin.checkLogin();
        
    }
    
    
}
