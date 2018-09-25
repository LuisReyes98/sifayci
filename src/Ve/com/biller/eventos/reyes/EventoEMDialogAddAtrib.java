/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.DialogCreateNewAttribs;
import Ve.com.biller.vistas.reyes.DialogoEditAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author luisr
 */
public class EventoEMDialogAddAtrib implements ActionListener{
    DialogoEditAttributes dialogEdit;
    public EventoEMDialogAddAtrib(DialogoEditAttributes dialogEdit) {
        this.dialogEdit=dialogEdit;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DialogCreateNewAttribs dia= new DialogCreateNewAttribs(dialogEdit);
    }
    
}
