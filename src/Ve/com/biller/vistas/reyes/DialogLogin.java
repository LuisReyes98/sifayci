package Ve.com.biller.vistas.reyes;


import Ve.com.biller.conexion.reyes.sql.CRUD_Users;
import Ve.com.biller.estructuras.reyes.User;
import Ve.com.biller.eventos.reyes.EventoLoginButton;
import Ve.com.biller.helpers.reyes.CustomDialog;
import Ve.com.biller.helpers.reyes.PasswordDocFilter;
import Ve.com.biller.modelos.reyes.DatosVentana;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Luis Reyes
 */
public class DialogLogin extends CustomDialog implements ActionListener {
    LoginPanel loginPanel;
    JPanel panelForm;
    
    JLabel labelNombre;
    JLabel labelPassword;
    
    JTextField textField;
    JPasswordField passwordField;
    
    JButton buttonLogin;
    
    JButton buttonCancel;
   
    
    public DialogLogin(LoginPanel loginPanel) {
        this.loginPanel=loginPanel;
        create();
    }

    private void create() {        
        PlainDocument doc;
         
        panelForm= new JPanel(new MigLayout("insets 0 0 0 0"));
        
        labelNombre= new JLabel("Nombre: ");
        labelNombre.setForeground(Color.BLACK);
        labelNombre.setFont(Tipografia.ARIAL_14);
        
        labelPassword= new JLabel("Contraseña: ");        
        labelPassword.setForeground(Color.BLACK);
        labelPassword.setFont(Tipografia.ARIAL_14);
        
        textField= new JTextField();
        textField.setForeground(Color.BLACK);
        textField.setFont(Tipografia.ARIAL_14);

        passwordField= new JPasswordField();
        passwordField.setForeground(Color.BLACK);
        passwordField.setFont(Tipografia.ARIAL_14);
        
        doc=(PlainDocument)passwordField.getDocument();
        doc.setDocumentFilter(new PasswordDocFilter());//filtro para permitir solo palabras            
        
        buttonLogin= new JButton("Iniciar sesión");
        buttonLogin.setForeground(Color.BLACK);
        buttonLogin.setFont(Tipografia.ARIAL_14);
        
        buttonCancel= new JButton("Cancelar");
        buttonCancel.setForeground(Color.BLACK);
        buttonCancel.setFont(Tipografia.ARIAL_14);
        
        
        
        buttonLogin.addActionListener(new EventoLoginButton(this));
        passwordField.addActionListener(new EventoLoginButton(this));
        buttonCancel.addActionListener(this);
        
        // "cell column row width height"
        panelForm.add(labelNombre,      "cell 0 0 1 1");
        panelForm.add(textField,        "cell 1 0 1 1,width 140px");
        panelForm.add(labelPassword,    "cell 0 1 1 1");
        panelForm.add(passwordField,    "cell 1 1 1 1,width 140px");        
        panelForm.add(buttonLogin,      "cell 0 2 1 1");
        panelForm.add(buttonCancel,     "cell 1 2 1 1");
       
        //DatosVentana.LABELS_LOGIN[1] titulo
       
        this.createDialog(panelForm, DatosVentana.LABELS_LOGIN[1]);
     
    }
       
    public void checkLogin(){
        User user= CRUD_Users.loginUser(textField.getText(), 
                passwordField.getPassword());
        if (user!=null) {
            loginPanel.loginUser(user);    
            close();
        }else{
            JOptionPane.showMessageDialog(null,"Nombre de ususario o contraseña incorrectos",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.close();
    }
    
    
}
