/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.estructuras.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Atributos;
import Ve.com.biller.vistas.reyes.DialogoEditAttributes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author luisr
 */
public class EventoEMShowAttributes implements ActionListener{

    public EventoEMShowAttributes() {
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        DialogoEditAttributes dia= new DialogoEditAttributes(CRUD_Atributos.componentesHamburguesa);
    }
    
}
