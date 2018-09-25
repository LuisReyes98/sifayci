/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Users;
import Ve.com.biller.vistas.reyes.DialogLoginCreateUser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


/**
 *
 * @author luisr
 */
public class EventoLoginCreateNewUser implements ActionListener{
    DialogLoginCreateUser dialog;
    EventoLoginUserManagement eventLog;
    public EventoLoginCreateNewUser(DialogLoginCreateUser dialog,EventoLoginUserManagement panel) {
        this.dialog = dialog;
        this.eventLog=panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean doIt=true;
        String passwordString="";
        char[] password1=dialog.getPasswordFie1d1().getPassword();
        char[] password2=dialog.getPasswordFie1d2().getPassword();
        
        if (password1.length<=5) {
            JOptionPane.showMessageDialog(null, "La contraseña es demasiado corta", "Advertencia", JOptionPane.WARNING_MESSAGE);    
        }else{
            try{
            //se comparan ambas contraseñas
                for (int i = 0; i < password1.length; i++) {
                    passwordString+=password1[i];                
                    if (password1[i]!=password2[i]) {                    
                        doIt=false;
                        break;
                    }
                }
            }catch (IndexOutOfBoundsException|NullPointerException ex){ doIt=false;}//en caso de excepcion
            if (doIt) {
                if ( CRUD_Users.insertNewUser(dialog.getTextFieldNombre().getText()
                        , passwordString, dialog.getCheckAdmin().isSelected(), dialog)) {
                    //se pudo añadir el usuario
                    JOptionPane.showMessageDialog(null, "Usuario añadido exitosamente", "Usuario "+dialog.getTextFieldNombre().getText()+" añadido ", JOptionPane.INFORMATION_MESSAGE);
                    eventLog.getDialog().dispose();
                    eventLog.execute();


                }                        
            }else{
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden ", "Error", 
                                JOptionPane.ERROR_MESSAGE); 
            }
        }
        
    }
    
}
