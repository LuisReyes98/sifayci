/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.LoginPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *evento del boton
 * cerrar sesion
 */
public class EventoLogout implements ActionListener{
    LoginPanel loginPanel;

    public EventoLogout(LoginPanel loginPanel) {
        this.loginPanel = loginPanel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int k=JOptionPane.showConfirmDialog(null, "¿Desea cerrar la sesión actual?", "Advertencia", JOptionPane.YES_NO_OPTION);
        if (k==JOptionPane.YES_OPTION) {
            loginPanel.logout();
        }
        
    }
    
}
