/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.vistas.reyes;

import Ve.com.biller.estructuras.reyes.User;
import Ve.com.biller.eventos.reyes.EventoLoginChangePassword;
import Ve.com.biller.helpers.reyes.CustomDialog;
import Ve.com.biller.helpers.reyes.PasswordDocFilter;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.text.PlainDocument;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author luisr
 */
public class DialogLoginChangePassword extends CustomDialog{
    
    JPanel panelContainer;
    User user;
    JLabel labelPasswordNew;
    JLabel labelPasswordAgain;
    JButton buttonChangePassword;   
    JPasswordField[] passWord= new JPasswordField[2]; 
    
    public DialogLoginChangePassword(User user) {
        panelContainer= new JPanel(new MigLayout());
        this.user=user;
        
        create();
    }
    private void create(){
        PlainDocument doc;

        for (int i = 0; i < 2; i++) {
            passWord[i]= new JPasswordField();
            passWord[i].setFont(Tipografia.ARIAL_14);
            passWord[i].setForeground(Color.BLACK);
            
            doc=(PlainDocument)passWord[i].getDocument();
            doc.setDocumentFilter(new PasswordDocFilter());//filtro para permitir solo palabras            
        
        }
        labelPasswordNew=new JLabel("Inserte Nueva Contraseña");
        labelPasswordNew.setForeground(Color.BLACK);
        labelPasswordNew.setFont(Tipografia.ARIAL_14);
        
        labelPasswordAgain= new JLabel("Repita la contraseña");
        labelPasswordAgain.setForeground(Color.BLACK);
        labelPasswordAgain.setFont(Tipografia.ARIAL_14);

        buttonChangePassword= new JButton("Cambiar Contraseña");//evento de cambio de contraseña
        buttonChangePassword.setForeground(Color.BLACK);
        buttonChangePassword.setFont(Tipografia.ARIAL_BOLD_12);
        buttonChangePassword.addActionListener(new EventoLoginChangePassword(this,user, passWord[0], passWord[1]));


        buttonClose.setText("Cancelar");//evento de cambio de contraseña
        buttonClose.setForeground(Color.BLACK);
        buttonClose.setFont(Tipografia.ARIAL_BOLD_12);

        
        panelContainer.add(labelPasswordNew,     "cell 0 0 2 1");
        panelContainer.add(passWord[0],          "cell 0 1 2 1,width 200px");
        panelContainer.add(labelPasswordAgain,   "cell 0 2 2 1");
        panelContainer.add(passWord[1],          "cell 0 3 2 1,width 200px");
        panelContainer.add(buttonChangePassword, "cell 0 4 1 1");
        panelContainer.add(buttonClose,       "cell 0 4 1 1");
    
        this.createDialog(panelContainer, "Cambiar contraseña");
    }

    

  
}
