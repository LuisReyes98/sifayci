package Ve.com.biller.eventos.reyes;

import Ve.com.biller.helpers.reyes.RepositionHelper;
import Ve.com.biller.modelos.reyes.DatosVentanasInternas;
import Ve.com.biller.vistas.reyes.LoginPanel;
import Ve.com.biller.vistas.reyes.RegistroDeVentaIW;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;

/**
 *
 * @author Luis Reyes
 */
public class EventoBotonRegistroDeVentas implements ActionListener{
    JDesktopPane desktopPane; 

    public EventoBotonRegistroDeVentas(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(LoginPanel.accessControl()){//chequeo de que se incio sesion
            execute();
        }
    }
    private void execute(){
        RegistroDeVentaIW rviw= new RegistroDeVentaIW(DatosVentanasInternas.ATRIBUTOS_REGISTRO_DE_VENTAS, true, true, true, true, true);
        desktopPane.add(rviw);
        RepositionHelper.positionMiddle(rviw,desktopPane);//pone la ventana en la mitad de la pantalla
        rviw.setSize(rviw.getSize().width,desktopPane.getSize().height);
        
        try{//pone en foco a la ventas
            rviw.setSelected(true);
        }catch(PropertyVetoException ex){}
        
    }
    
}
