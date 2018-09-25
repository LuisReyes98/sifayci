package Ve.com.biller.eventos.reyes;

import Ve.com.biller.vistas.reyes.OrdenPanelNO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Luis_Reyes
 */
public class EventoNOBotonRemoverPlatillo implements ActionListener{
    DefaultTableModel tablaModelo;
    JTable tabla;
    OrdenPanelNO ordenPanel;
    
    
    public EventoNOBotonRemoverPlatillo(DefaultTableModel tablaModelo,JTable tabla,OrdenPanelNO ordenPanel){
        this.tablaModelo=tablaModelo;
        this.tabla=tabla;
        this.ordenPanel=ordenPanel;
    } 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String aux;  
        int[] seleccionados;   
        double descontar = 0;
        
        try{
            
            seleccionados=tabla.getSelectedRows();
            for (int i = 0; i < seleccionados.length; i++) {
                aux=(String)tablaModelo.getValueAt(seleccionados[i]-i, 1);
                descontar += Double.parseDouble(aux.replace(',','.'));
                tablaModelo.removeRow(seleccionados[i]-i); 
            }          
            ordenPanel.restarCosto(descontar);
        }catch(ArrayIndexOutOfBoundsException a  ){ }
        
    }
    
}
