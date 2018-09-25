/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.DialogLoginCreateUser;
import Ve.com.biller.vistas.reyes.PanelLoginUsersManagement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author luisr
 */
public class EventoLoginDialogCreateUser implements ActionListener{
    EventoLoginUserManagement userManagement;
    public EventoLoginDialogCreateUser(EventoLoginUserManagement panelLog) {
        this.userManagement=panelLog;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DialogLoginCreateUser dl= new DialogLoginCreateUser(userManagement);
    }
    
}
