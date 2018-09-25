/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.eventos.reyes;

import Ve.com.biller.modelos.reyes.DatosOrdenesIW;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis_Reyes
 */
public class EventoNODialogoAyudaRemoverPlatillo implements ActionListener{
    
    public EventoNODialogoAyudaRemoverPlatillo() {
        
    }
    
  
    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, 
              DatosOrdenesIW.DIALOGO_AYUDA_REMOVER_PLATILLO[0],DatosOrdenesIW.DIALOGO_AYUDA_REMOVER_PLATILLO[1] , JOptionPane.INFORMATION_MESSAGE);
        
    }
    
}
