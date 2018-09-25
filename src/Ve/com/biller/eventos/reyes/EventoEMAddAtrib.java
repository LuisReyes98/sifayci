/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Atributos;
import Ve.com.biller.vistas.reyes.DialogCreateNewAttribs;
import Ve.com.biller.vistas.reyes.DialogoEditAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author luisr
 */
public class EventoEMAddAtrib implements ActionListener{
    DialogCreateNewAttribs dialogCreate;
    DialogoEditAttributes dialogEdit;

    public EventoEMAddAtrib(DialogCreateNewAttribs dialogCreate, DialogoEditAttributes dialogEdit) {
        this.dialogCreate = dialogCreate;
        this.dialogEdit = dialogEdit;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int indexCombo;
        String name;
        String type;
        name= dialogCreate.getTextFieldName().getText();
        type= dialogCreate.getTextFieldType().getText();
        if (dialogCreate.getRadioButtons()[0].isSelected()) {
            indexCombo= dialogCreate.getComboBox().getSelectedIndex();
            if (name.isEmpty()) {
                 JOptionPane.showMessageDialog(null, "El nombre no puede ser nulo", "Advertencia", JOptionPane.WARNING_MESSAGE);   
            }else{
                execute(dialogCreate.getComboOption()[indexCombo], name);                                
            }
        }else{
            if (name.isEmpty()&&type.isEmpty()) {
                 JOptionPane.showMessageDialog(null, "El campo no puede ser nulo", "Advertencia", JOptionPane.WARNING_MESSAGE);   
            }else{
                execute(type, name);                                
            }
        }
        
    }
    private void execute(String type,String name){
        CRUD_Atributos.createAtribute(type, name);
        dialogCreate.close();
        dialogEdit.close();
        CRUD_Atributos.selectTipos();
        dialogEdit= new DialogoEditAttributes(CRUD_Atributos.componentesHamburguesa);
    }
    
}
