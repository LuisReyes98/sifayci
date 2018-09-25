/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.vistas.reyes;

import Ve.com.biller.eventos.reyes.EventoLoginCreateNewUser;
import Ve.com.biller.eventos.reyes.EventoLoginUserManagement;
import Ve.com.biller.helpers.reyes.CustomDialog;
import Ve.com.biller.helpers.reyes.PasswordDocFilter;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author luisr
 */
public class DialogLoginCreateUser extends CustomDialog{
    EventoLoginUserManagement eventLog;
    JPanel panel;
    JTextField textFieldNombre;
    JPasswordField  passwordFie1d1;
    JPasswordField  passwordFie1d2;
    JButton buttonCreate;
    JCheckBox checkAdmin;
    //button close 
    
   
    public DialogLoginCreateUser(EventoLoginUserManagement panelLog) {
        panel= new JPanel(new MigLayout());
        this.eventLog=panelLog;
        createGUI();
    }
    private void createGUI(){
        PlainDocument doc;
        
        JLabel labelUser;
        JLabel labelPassword1;
        JLabel labelPassword2;
        
        labelUser= new JLabel ("Nombre de Usuario:");
        labelUser.setFont(Tipografia.ARIAL_14);
        labelUser.setForeground(Color.BLACK);
        
        textFieldNombre= new JTextField();
        textFieldNombre.setFont(Tipografia.ARIAL_14);
        textFieldNombre.setForeground(Color.BLACK);
        doc=(PlainDocument)textFieldNombre.getDocument();
        doc.setDocumentFilter(new PasswordDocFilter());//filtro para permitir solo palabras 
        
        labelPassword1= new JLabel("Ingrese contraseña:" );
        labelPassword1.setFont(Tipografia.ARIAL_14);
        labelPassword1.setForeground(Color.BLACK);
        
        labelPassword2= new JLabel("Ingrese contraseña otra vez:");
        labelPassword2.setFont(Tipografia.ARIAL_14);
        labelPassword2.setForeground(Color.BLACK);
        
        passwordFie1d1= new JPasswordField();
        passwordFie1d1.setFont(Tipografia.ARIAL_14);
        passwordFie1d1.setForeground(Color.BLACK);
        doc=(PlainDocument)passwordFie1d1.getDocument();
        doc.setDocumentFilter(new PasswordDocFilter());//filtro para permitir solo palabras 
        
        passwordFie1d2= new JPasswordField();
        passwordFie1d2.setFont(Tipografia.ARIAL_14);
        passwordFie1d2.setForeground(Color.BLACK);
        doc=(PlainDocument)passwordFie1d2.getDocument();
        doc.setDocumentFilter(new PasswordDocFilter());//filtro para permitir solo palabras 
                
        buttonCreate= new JButton("Crear Usuario");        
        buttonCreate.setFont(Tipografia.ARIAL_14);
        buttonCreate.setForeground(Color.BLACK);
        buttonCreate.addActionListener(new EventoLoginCreateNewUser(this,eventLog));
        
        buttonClose.setText("Cancelar");                   
        buttonClose.setFont(Tipografia.ARIAL_14);
        buttonClose.setForeground(Color.BLACK);
        buttonClose.addActionListener(this);//evento cerrar el dialogo
        
        checkAdmin= new JCheckBox("Es admin.");
        
        checkAdmin.setToolTipText("Los admin pueden crear y eliminar usuarios.");
        checkAdmin.setFont(Tipografia.ARIAL_14);
        checkAdmin.setForeground(Color.BLACK);
        
        panel.setBackground(Color.WHITE);
        
        //cell c f w h        
        panel.add(labelUser,         "cell 0 0 2 1");
        panel.add(textFieldNombre,       "cell 0 1 2 1,width 150px");
        panel.add(labelPassword1,    "cell 0 2 2 1");
        panel.add(passwordFie1d1,    "cell 0 3 2 1,width 150px");
        panel.add(labelPassword2,    "cell 0 4 2 1");
        panel.add(passwordFie1d2,    "cell 0 5 2 1,width 150px");
        panel.add(checkAdmin,        "cell 0 6 2 1");    
        
        panel.add(buttonCreate,      "cell 0 7 1 1");    //boton crear       
        panel.add(buttonClose,      "cell 1 7 1 1");//boton cancelar

        createDialog(panel, "Crear Usuario");
    }

    
    public JTextField getTextFieldNombre() {
        return textFieldNombre;
    }

    public JPasswordField getPasswordFie1d1() {
        return passwordFie1d1;
    }

    public JPasswordField getPasswordFie1d2() {
        return passwordFie1d2;
    }
    public JCheckBox getCheckAdmin() {
        return checkAdmin;
    }
    
}
