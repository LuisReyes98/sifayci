package Ve.com.biller.eventos.reyes;

import Ve.com.biller.helpers.reyes.PrintableJPanel;
import Ve.com.biller.vistas.reyes.HideShowPanelRV;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/**
 *
 * @author Luis Reyes
 */
public class EventoRVPrintBill implements ActionListener{
    private final HideShowPanelRV RVpanel;
   
    private PrintableJPanel panelToPrint;
    private JScrollPane scroll;

    public EventoRVPrintBill(HideShowPanelRV RVpanel) {
        this.RVpanel = RVpanel;
        createPrintablePanel();
        
    }
    
    private void createPrintablePanel(){
        panelToPrint= RVpanel.getClonedPrintableBillPanel();     
        
        scroll= new JScrollPane(panelToPrint);
        scroll.setPreferredSize(new Dimension(450,500));
        scroll.getVerticalScrollBar().setUnitIncrement(15);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        int l;
        l =JOptionPane.showOptionDialog(null, scroll, "Vista Previa", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (l==JOptionPane.YES_OPTION) {
            panelToPrint.printComponent();
        }
       
        
    }
    
}
