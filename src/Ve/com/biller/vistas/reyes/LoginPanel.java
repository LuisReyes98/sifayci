package Ve.com.biller.vistas.reyes;

import Ve.com.biller.estructuras.reyes.User;
import Ve.com.biller.eventos.reyes.EventoLoginDialog;
import Ve.com.biller.eventos.reyes.EventoLoginUserManagement;
import Ve.com.biller.eventos.reyes.EventoLogout;
import Ve.com.biller.helpers.reyes.ImageManager;
import Ve.com.biller.modelos.reyes.DatosVentana;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Luis Reyes
 */
public class LoginPanel extends JPanel {
    private static User loggedUser;
    private static boolean booleanLoggedUser;
    
    
    private static ImageIcon iconNotLogin;
    private static ImageIcon iconLogin;
    private static ImageIcon iconSettings; //el icono de las opciones 
    
    private static JLabel labelLogin;
    private static JLabel labelLoginIcon;    
    private static JButton botonIniciarSesion;
    
    private static JButton botonCloseSesion;
    
    private static JButton botonSettings;
    
    public LoginPanel(){
        super(new MigLayout("insets 2 2 2 2"));
        create();
    }
    
    private void create(){
        try {
            iconLogin= new ImageIcon(ImageIO.read(LoginPanel.class.getResource(DatosVentana.IMAGENES_LOGIN[0])));
            iconNotLogin= new ImageIcon(ImageIO.read(LoginPanel.class.getResource(DatosVentana.IMAGENES_LOGIN[1])));
            iconSettings= ImageManager.iconResize(new ImageIcon(ImageIO.read(LoginPanel.class.getResource(DatosVentana.IMAGENES_LOGIN[2]))),16,16);
        } catch (IOException ex) {
            Logger.getLogger(LoginPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        labelLoginIcon= new JLabel(iconNotLogin);
        //labelLoginIcon.setBackground(Color.WHITE);
        labelLoginIcon.setOpaque(false);
        
        labelLogin= new JLabel(DatosVentana.LABELS_LOGIN[0],JLabel.CENTER);
        labelLogin.setOpaque(false);
        labelLogin.setFont(Tipografia.ARIAL_BOLD_14);
        labelLogin.setForeground(Color.BLACK);
        
        
        botonIniciarSesion= new JButton(DatosVentana.LABELS_LOGIN[1]);
        botonIniciarSesion.setForeground(Color.BLACK);
        botonIniciarSesion.setBackground(Color.WHITE);
        botonIniciarSesion.setFont(Tipografia.ARIAL_BOLD_12);        
        botonIniciarSesion.addActionListener(new EventoLoginDialog(this));
        
        botonCloseSesion= new JButton(DatosVentana.LABELS_LOGIN[3]);
        botonCloseSesion.setForeground(Color.BLACK);
        botonCloseSesion.setBackground(Color.WHITE);
        botonCloseSesion.setFont(Tipografia.ARIAL_BOLD_12); 
        botonCloseSesion.addActionListener(new EventoLogout(this));
        
        botonSettings= new JButton();//boton de configuracion         
        botonSettings.setOpaque(false);
        botonSettings.setBorderPainted(false);
        botonSettings.setToolTipText("Configuración");        
        botonSettings.setIcon(iconSettings);
        botonSettings.setContentAreaFilled(false);
        botonSettings.addActionListener(new EventoLoginUserManagement());

        this.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        
                 // "cell column row width height"
        this.add(labelLoginIcon,    "cell 0 0 1 1");        
        this.add(labelLogin,        "cell 1 0 1 1");        
        this.add(botonIniciarSesion,"cell 0 1 2 1,grow");
        
        this.setPreferredSize(new Dimension(202,65));
        
        this.setBackground(new Color(24, 176, 146));
        this.setOpaque(true);
    }
    
    public void loginUser(User user){
        if(user!=null){            
            loggedUser= user;
            booleanLoggedUser=true;
            labelLogin.setText("Bienvenido, "+user.getNombreUsuario());
            labelLoginIcon.setIcon(iconLogin);
            
            this.remove(botonIniciarSesion);
            if (loggedUser.isAdmin()) {
              
                // "cell column row width height"
                this.remove(labelLogin);
                
                this.add(labelLogin,        "cell 1 0 2 1");
                
                this.add(botonCloseSesion,  "cell 0 1 2 1");
                this.add(botonSettings,     "cell 2 1 1 1");
                
            }else{
                this.add(botonCloseSesion,"cell 0 1 2 1");
            }
            this.repaint();
            this.revalidate();
        }
    }
    
    public void logout(){
        loggedUser= null;
        booleanLoggedUser=false;
        labelLoginIcon.setIcon(iconNotLogin);
        labelLogin.setText(DatosVentana.LABELS_LOGIN[0]);
        
        this.removeAll();//se remueven todos los componentes para poder añadirlos de nuevo
        this.add(labelLoginIcon,    "cell 0 0 1 1");        
        this.add(labelLogin,        "cell 1 0 1 1");        
        this.add(botonIniciarSesion,"cell 0 1 2 1,grow");
        this.repaint();
        this.revalidate();
        
    }

    public static User getLoggedUser() {
        return loggedUser;
    }
    
    

    public static boolean accessControl(){
        if (!booleanLoggedUser) {
            JOptionPane.showMessageDialog(null, "Debes iniciar sesion para tener acceso", "Acceso Negado", JOptionPane.WARNING_MESSAGE);
        }
        return booleanLoggedUser;
    }
    
}
