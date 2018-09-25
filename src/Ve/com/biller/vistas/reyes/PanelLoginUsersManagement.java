/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.vistas.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Users;
import Ve.com.biller.estructuras.reyes.User;
import Ve.com.biller.eventos.reyes.EventoLoginDeleteUser;
import Ve.com.biller.eventos.reyes.EventoLoginDialogChangePassword;
import Ve.com.biller.eventos.reyes.EventoLoginDialogCreateUser;
import Ve.com.biller.eventos.reyes.EventoLoginUserManagement;
import Ve.com.biller.helpers.reyes.ImageManager;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Compu-1
 */
public class PanelLoginUsersManagement extends JPanel{
    EventoLoginUserManagement userManagement;
    Iterator<User> iterarUsuario;
    private ImageIcon imgAdd;
    JLabel labelUsuarios;
    JButton buttonAddUser;
    int i=0;
    
    public PanelLoginUsersManagement(EventoLoginUserManagement userManagement) {
        super(new MigLayout());
        this.userManagement=userManagement;        
        create(CRUD_Users.selectAllUsers());
    }
    private void create(ArrayList<User> arrayUser){
        
        try {
            imgAdd= new ImageIcon(ImageIO.read(PanelLoginUsersManagement.class.getResource(DatosBotones.ICONOS_BOTONES_ORDENES[0])));//imagen de añadir
        } catch (IOException ex) {
            
        }
        
        User userAux;
        iterarUsuario=arrayUser.iterator();
        while(iterarUsuario.hasNext()){
            userAux=iterarUsuario.next();
            this.add(createUserPanel(userAux),"cell 0 "+i+" 1 1");
            i++;        
        }
        
        buttonAddUser= new JButton("Añadir Usuario",imgAdd);
        buttonAddUser.setForeground(Color.BLACK);
        buttonAddUser.setFont(Tipografia.ARIAL_BOLD_14);
        
        buttonAddUser.setToolTipText("Añadir Usuario");
        buttonAddUser.addActionListener(new EventoLoginDialogCreateUser(userManagement));
        
        this.add(buttonAddUser,"cell 0 "+i+" 1 1");
        
    }
    
    private JPanel createUserPanel(User usuario){
        JButton buttonDelete;
        JButton buttonEdit;
        ImageIcon imgDel=null;
        ImageIcon imgEdit=null;
        
        JPanel panel= new JPanel(new MigLayout());
        
        JLabel labelNombre=new JLabel(usuario.getNombreUsuario());
        JLabel labelPassword=new JLabel("********");
        JLabel labelAdmin;
        
        labelNombre.setFont(Tipografia.ARIAL_14);
        labelNombre.setForeground(Color.BLACK);
        
        labelPassword.setFont(Tipografia.ARIAL_14);
        labelPassword.setForeground(Color.BLACK);
        if (usuario.isAdmin()) {
            labelAdmin= new JLabel("admin");                            
        }else{
            labelAdmin= new JLabel("     ");  
        }
        labelAdmin.setFont(Tipografia.ARIAL_BOLD_14);
        labelAdmin.setForeground(Color.BLACK); 
        
        try {            
            //icono eliminar usuario
            imgDel=ImageManager.iconResize(new ImageIcon(ImageIO.read(PanelLoginUsersManagement.class.
                    getResource(DatosBotones.ICONOS_BOTONES_FACTURA[2]))),16,16);
            //icono editar usuario
            imgEdit=ImageManager.iconResize(new ImageIcon(ImageIO.read(PanelLoginUsersManagement.class.
                    getResource(DatosBotones.ICONOS_BOTONES_MAIN[1]))),16,16);                                  
        } catch (IOException ex) {}
        
        buttonDelete= new JButton(imgDel);
        buttonDelete.setBackground(Color.WHITE);
        buttonDelete.setToolTipText("Eliminar Usuario");

        buttonEdit= new JButton(imgEdit);                       
        buttonEdit.setToolTipText("Cambiar Contraseña");                
        buttonEdit.setBackground(Color.WHITE); 
                
        buttonEdit.addActionListener(new EventoLoginDialogChangePassword(usuario));
        buttonDelete.addActionListener(new EventoLoginDeleteUser(usuario,this,panel));
        
        if (i%2==0) {
            panel.setBackground(Color.WHITE);
        }
        
        panel.add(labelNombre,"width 100px");
        panel.add(labelPassword,"width 100px");
        panel.add(labelAdmin,"width 100px");
        panel.add(buttonEdit);
        panel.add(buttonDelete);
        
        
        return panel;
    }

    
}