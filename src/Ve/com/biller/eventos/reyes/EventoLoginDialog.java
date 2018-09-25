package Ve.com.biller.eventos.reyes;

/**
 *
 * @author Luis Reyes
 */
import Ve.com.biller.vistas.reyes.DialogLogin;
import Ve.com.biller.vistas.reyes.LoginPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventoLoginDialog implements ActionListener{
    
    LoginPanel loginPanel;
    public EventoLoginDialog(LoginPanel loginPanel) {
        this.loginPanel=loginPanel;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        DialogLogin dl= new DialogLogin(loginPanel);
    }
    
}
