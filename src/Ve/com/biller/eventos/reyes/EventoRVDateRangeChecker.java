package Ve.com.biller.eventos.reyes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Luis Reyes
 */
public class EventoRVDateRangeChecker implements ActionListener {
    JXDatePicker datePickerFrom;
    JXDatePicker datePickerTo;
    Date dateToOld;
    Date dateFromOld;
    
    public EventoRVDateRangeChecker(JXDatePicker datePickerFrom,JXDatePicker datePickerTo) {
        this.datePickerFrom= datePickerFrom;
        this.datePickerTo= datePickerTo;
        dateFromOld= datePickerFrom.getDate();
        dateToOld=datePickerTo.getDate();
        
    }

        
    
    @Override
    public void actionPerformed(ActionEvent e) {         
        Date dateTo;    
        Date dateFrom;   
        
        dateFrom=datePickerFrom.getDate();
        dateTo=datePickerTo.getDate();
                
        if (dateTo.after(dateFrom)) {
            
            dateFromOld=datePickerFrom.getDate();
            dateToOld=datePickerTo.getDate();            
        }else{
            JOptionPane.showMessageDialog(null, "Rango de fecha erróneo", "Atención", JOptionPane.WARNING_MESSAGE);
            datePickerFrom.setDate(dateFromOld);
            datePickerTo.setDate(dateToOld);                        
        }
    }
    
}
