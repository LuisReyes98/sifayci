package Ve.com.biller.eventos.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Ventas;
import Ve.com.biller.modelos.reyes.DatosRegistroVentas;
import Ve.com.biller.vistas.reyes.PanelRV;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Reyes
 */
public class EventoRVBotonMostrarTodo implements ActionListener {
    PanelRV panelRV;

    public EventoRVBotonMostrarTodo(PanelRV panelRV) {
        this.panelRV = panelRV;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int aux=JOptionPane.showConfirmDialog(null, DatosRegistroVentas.MENSAJE_ADVERTENCIA, DatosRegistroVentas.TITULO_ADVERTENCIA, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (aux==JOptionPane.YES_OPTION) {
            panelRV.updateBottomPanel(CRUD_Ventas.selectSalesRecordAll());
        }
    }
    
}
