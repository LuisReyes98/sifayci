package Ve.com.biller.eventos.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Ventas;
import Ve.com.biller.helpers.reyes.DateManager;
import Ve.com.biller.vistas.reyes.PanelRV;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
/**
 *
 * @author Luis Reyes
 */
public class EventoRVBotonHoy implements ActionListener {
    PanelRV panelRV;
    
    public EventoRVBotonHoy(PanelRV panelRV){
        this.panelRV= panelRV;
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Date[] todayDates= DateManager.getBeginEndToday();//fechas de inicio y fin del dia   

        if (panelRV.getCheckBoxSummary().isSelected()) {
            panelRV.updateBottomPanelSummary(CRUD_Ventas.selectDishesDateRange(todayDates[0],todayDates[1]));

        }else{
            panelRV.updateBottomPanel(CRUD_Ventas.selectSalesRedordDateRange(todayDates[0],todayDates[1]));

        }
    }
    
}
