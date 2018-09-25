package Ve.com.biller.eventos.reyes;

import Ve.com.biller.modelos.reyes.DatosVentanasInternas;
import Ve.com.biller.vistas.reyes.EditMenuIW;
import Ve.com.biller.vistas.reyes.LoginPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;

/**
 *
 * @author Luis Reyes
 */
public class EventoBotonEditarMenu implements ActionListener{
    JDesktopPane desktopPane;

    public EventoBotonEditarMenu(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }  
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(LoginPanel.accessControl()){//chequeo de que se incio sesion
            execute();
        }
    }
    
    private void execute(){
        EditMenuIW edm = new EditMenuIW(DatosVentanasInternas.ATRIBUTOS_EDITAR_MENU,true,true,true,true,false);//la ventana no se cerrara de golpe
        
        edm.setSize(new Dimension(desktopPane.getWidth(),desktopPane.getHeight()));
        positionMiddle(edm);//se pone el panel para que aparesca en el medio

        desktopPane.add(edm);
        try{
            edm.setSelected(true);
        }catch(PropertyVetoException ex){}
    }
    
    private void positionMiddle(EditMenuIW edm){        
        Dimension desktopSize= desktopPane.getSize();
        Dimension edmSize= edm.getSize();
        int x = (desktopSize.width/2 - edmSize.width/2);
        int y = 0;
        edm.setLocation(x, y);
                     
    }
    
    
}
