/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Users;
import Ve.com.biller.estructuras.reyes.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author luisr
 */
public class EventoLoginDeleteUser implements ActionListener{
    User user;
    JPanel panelParent;
    JPanel panelChildren;
            
    public EventoLoginDeleteUser(User user,JPanel panelParent,JPanel panelChildren) {
        this.user=user;
        this.panelParent= panelParent;
        this.panelChildren= panelChildren;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int r=JOptionPane.showConfirmDialog(null,"Estas seguro de eliminar el usuario "+user.getNombreUsuario() 
                                            +"\n esta acción NO SE PUEDE DESHACER", "Atención", JOptionPane.YES_NO_CANCEL_OPTION,
                                            JOptionPane.WARNING_MESSAGE);
        if (r==JOptionPane.YES_OPTION) {            
            if (CRUD_Users.deleteUser(user)) {
                panelParent.remove(panelChildren);
                panelParent.repaint();
                panelParent.revalidate();
            }
            
        }
    }
    
}
