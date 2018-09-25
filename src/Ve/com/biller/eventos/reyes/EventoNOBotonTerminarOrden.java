package Ve.com.biller.eventos.reyes;

import Ve.com.biller.modelos.reyes.DatosOrdenesIW;
import Ve.com.biller.vistas.reyes.OrdenPanelNO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 *
 * @author Luis Reyes 
 */
public class EventoNOBotonTerminarOrden implements ActionListener{
    OrdenPanelNO ordenPanel;

    public EventoNOBotonTerminarOrden(OrdenPanelNO ordenPanel) {
        this.ordenPanel = ordenPanel;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int respuesta;
        if (ordenPanel.getCantidadPlatillos()>0) {//hay al menos un platillo en la orden
            if (ordenPanel.isClientFilled()) {//si el platillo tiene el campo cliente relleno
                if ((ordenPanel.getCliente().length()>5)&&(ordenPanel.getCedula()>10000)) {
                    //si el nombre tiene mas de 5 caracteres y la cedula es mayor de 10000 diez mil 
                    respuesta=JOptionPane.showConfirmDialog(null,DatosOrdenesIW.DIALOGO_TERMINAR_ORDEN[0],
                    DatosOrdenesIW.DIALOGO_TERMINAR_ORDEN[1],JOptionPane.WARNING_MESSAGE);
                    //dialogo de comprobacion de estar seguro de terminar la orden
                    if (respuesta==JOptionPane.YES_OPTION) {
                        System.gc();//llamado al garbage collector
                        ordenPanel.endOrder();//la orden se termina
                        ordenPanel.registrarVenta();//la orden se registra en la base de datos
                    }                
                }else{
                    JOptionPane.showMessageDialog(null, new JLabel(DatosOrdenesIW.DIALOGO_CLIENTE_NO_VALIDO,JLabel.CENTER),
                    DatosOrdenesIW.DIALOGO_TERMINAR_ORDEN[1], JOptionPane.WARNING_MESSAGE);
                    //dialogo de advertencia si el campo cliente o el campo cedula se le ingresan valores  es muy pequeños    
                }                      
            }else{//en caso de que el campo cliente o el campo cedula esten vacios
                JOptionPane.showMessageDialog(null, DatosOrdenesIW.DIALOGO_NO_HAY_CLIENTE,
                DatosOrdenesIW.DIALOGO_TERMINAR_ORDEN[1], JOptionPane.WARNING_MESSAGE);
                //dialogo de advertencia si el campo cliente se encuentra vacio         
            }
        }else{//en el caso de que no halla platillos en la orden 
            JOptionPane.showMessageDialog(null, DatosOrdenesIW.DIALOGO_NO_HAY_PLATILLOS,
            DatosOrdenesIW.DIALOGO_TERMINAR_ORDEN[1], JOptionPane.WARNING_MESSAGE);
            //dialogo que se muestra si no hay platillos en la orden aún
        }
       
        
        
        
        
    }
    
}
