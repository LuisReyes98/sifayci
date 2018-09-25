/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Users;
import Ve.com.biller.estructuras.reyes.User;
import Ve.com.biller.vistas.reyes.DialogLoginChangePassword;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/**
 *
 * @author luisr
 */
public class EventoLoginChangePassword implements ActionListener{
    DialogLoginChangePassword dialog;
    User user;
    JPasswordField passwordVector1;
    JPasswordField passwordVector2;

    
    public EventoLoginChangePassword(DialogLoginChangePassword dialog,User user,JPasswordField passwordVector1,JPasswordField passwordVector2) {
        this.dialog=dialog;
        this.user=user;
        this.passwordVector1=passwordVector1;
        this.passwordVector2=passwordVector2;

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean same=true;        
        
        char[] vector1= passwordVector1.getPassword();
        char[] vector2=passwordVector2.getPassword();        
        if (vector1.length<=5||vector2.length<=5) {
            JOptionPane.showMessageDialog(null, "La contraseña es demasiado corta", "Advertencia", JOptionPane.WARNING_MESSAGE);           
        }else{
            try{
                for (int i = 0; i < vector1.length ; i++) {
                    if (vector1[i]!=vector2[i]) {
                        same=false;
                    }
                }            
            }catch(IndexOutOfBoundsException|NullPointerException ex){
                same=false;
            }                 
            if (same) {
                
                CRUD_Users.changeUserPassword(dialog,user, vector2);
            }else{
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden ", "Error", 
                            JOptionPane.ERROR_MESSAGE); 
            }
        }
        
    }
    
}
