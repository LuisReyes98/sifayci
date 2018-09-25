package Ve.com.biller.eventos.reyes;

import Ve.com.biller.helpers.reyes.RepositionHelper;
import Ve.com.biller.modelos.reyes.DatosVentanasInternas;
import Ve.com.biller.vistas.reyes.LoginPanel;
import Ve.com.biller.vistas.reyes.StockIW;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;

/**
 *
 * @author Luis Reyes
 */
public class EventoBotonStock implements ActionListener{
    JDesktopPane desktopPane; 

    public EventoBotonStock(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(LoginPanel.accessControl()){//chequeo de que se incio sesion
            execute();
        }
    }
    private void execute(){
        StockIW stiw= new StockIW(DatosVentanasInternas.ATRIBUTOS_STOCK, true, true, true, true, true);
        RepositionHelper.positionMiddle(stiw, desktopPane);
        desktopPane.add(stiw);
        try{//pone en foco a la ventas
            stiw.setSelected(true);
        }catch(PropertyVetoException ex){}
    }
 
}
