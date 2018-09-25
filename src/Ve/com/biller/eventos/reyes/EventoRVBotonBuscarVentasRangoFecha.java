package Ve.com.biller.eventos.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Ventas;
import Ve.com.biller.helpers.reyes.DateManager;
import Ve.com.biller.vistas.reyes.PanelRV;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Luis Reyes
 */
public class EventoRVBotonBuscarVentasRangoFecha implements ActionListener{
    PanelRV panelRV;
    JXDatePicker datePickerFrom;
    JXDatePicker datePickerTo;

    public EventoRVBotonBuscarVentasRangoFecha(PanelRV panelRV, JXDatePicker datePickerFrom, JXDatePicker datePickerTo) {
        this.panelRV = panelRV;
        this.datePickerFrom = datePickerFrom;
        this.datePickerTo = datePickerTo;
    }       
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Date[] dateRange= DateManager.getBeginEndDateRange(datePickerFrom.getDate(),
                    datePickerTo.getDate());
        if (panelRV.getCheckBoxSummary().isSelected()) {
            
            panelRV.updateBottomPanelSummary(CRUD_Ventas.selectDishesDateRange(dateRange[0],dateRange[1]));
        
        }else{
            //arregla las fechas de inicio y fin para lograr obtener un rango realmente valido para las ventas
           
            panelRV.updateBottomPanel(CRUD_Ventas.selectSalesRedordDateRange(dateRange[0],dateRange[1]));
        
        }
        
    }
    
}
