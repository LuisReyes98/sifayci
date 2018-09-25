/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.eventos.reyes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Ve.com.biller.helpers.reyes.DialogoCerrar;
/**
 *
 * @author S2M8
 */
public class EventoBotonCerrar implements ActionListener {
    DialogoCerrar dialogo;
    
    public EventoBotonCerrar() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dialogo= new DialogoCerrar();
    }
    
}
