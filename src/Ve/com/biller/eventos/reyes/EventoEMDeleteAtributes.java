/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Atributos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author luisr
 */
public class EventoEMDeleteAtributes implements ActionListener {
    String name;
    String description;
    JPanel panelParent;
    JPanel panelSon;
    
    public EventoEMDeleteAtributes(String name, String description,JPanel panelParent,JPanel panelSon) {
        this.name = name;
        this.description = description;
        this.panelParent=panelParent;
        this.panelSon=panelSon;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int r=JOptionPane.showConfirmDialog(null,"Estas seguro de eliminar el acompañante "+name+" "+description 
                                            +"\n esta acción NO SE PUEDE DESHACER", "Atención", 
                                            JOptionPane.YES_NO_CANCEL_OPTION,
                                            JOptionPane.WARNING_MESSAGE);
        if (r==JOptionPane.YES_OPTION) {
            if (CRUD_Atributos.deleteAtribute(name, description)) {
                CRUD_Atributos.selectTipos();
                panelParent.remove(panelSon);
                panelParent.repaint();
                panelParent.revalidate();
            }
        }
    }
    
}
