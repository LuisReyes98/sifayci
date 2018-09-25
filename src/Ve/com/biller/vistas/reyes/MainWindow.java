
package Ve.com.biller.vistas.reyes;

/**
 *
 * @author Luis_Reyes
 */


import Ve.com.biller.conexion.reyes.sql.ConexionPlatillosBD;
import Ve.com.biller.eventos.reyes.EventoBotonCerrar;
import Ve.com.biller.eventos.reyes.EventoBotonEditarMenu;
import Ve.com.biller.eventos.reyes.EventoBotonNuevaOrden;
import Ve.com.biller.helpers.reyes.Ventana;
import Ve.com.biller.helpers.reyes.Botonera;
import Ve.com.biller.helpers.reyes.Menues;
import Ve.com.biller.modelos.reyes.DatosVentana;
import Ve.com.biller.eventos.reyes.EventoBotonVerMenu;
import Ve.com.biller.helpers.reyes.DesktopBorderBackground;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.DatosMenues;
import Ve.com.biller.modelos.reyes.ControladorMenuIW;
import Ve.com.biller.conexion.reyes.sql.CRUD_Atributos;
import Ve.com.biller.conexion.reyes.sql.CRUD_Menu;
import Ve.com.biller.eventos.reyes.EventoBotonRegistroDeVentas;
import Ve.com.biller.eventos.reyes.EventoBotonStock;
import Ve.com.biller.helpers.reyes.DialogoCerrar;

import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;




public class MainWindow extends Ventana {   
    private static LoginPanel login;
    private static JLabel labelVersion= new JLabel(DatosVentana.NOMBRE+" "+DatosVentana.VERSION);
    
    JDesktopPane desktopPane; // Escritoria para tener subventanas
    BufferedImage bufferdImagenFondo; //Creando la variable para el fondo 
    
    Color colorB;
    
    Botonera botonesMain;    //JPanel    
    Menues menues;   //JMenuBar
    JPanel panelBotones;
    Botonera botonSalir;
    ImageIcon[] imgIconos;
    ImageIcon[] imgIconoSalir;
    JLabel version;
    DialogoCerrar dialogoCerrar;
    
    public MainWindow(String[] atributosVentana,Boolean cerrable) throws IOException { //Constructor 
        super(atributosVentana,cerrable);
        
        crearGUI();
    }

    
    private void crearGUI() throws IOException { //Metodo Crear GUI      
        colorB=new Color(129,29,31);
        

        //llamada a los modelos
        ConexionPlatillosBD.abrirConexion();
      
        //Metodos de la Ventana
        this.setLayout(new BorderLayout()); //nuevo layout 'ordenador'
                
        desktopPane= new JDesktopPane(); // Ventana Principal 
        
        menuBar();   //cargar la JMenuBar                                       
       //se le asigna un color de fondo que cuadra con la imagen                   
        iconosFondo();     
        botones();          
        this.addWindowListener(new WindowAdapter() {//evento que evita que la ventana se cierre de golpe
                @Override
                public void windowClosing(WindowEvent e) {
                   dialogoCerrar = new DialogoCerrar();
                }
                
        });

        this.add(panelBotones,BorderLayout.SOUTH);
        this.add(desktopPane); 
        
        labelVersion();//llamado al metodo del label la version del programa
        createLogin();

        
        this.setMinimumSize(new Dimension(Integer.parseInt(DatosVentana.ATRIBUTOS_VENTANA[1]),Integer.parseInt(DatosVentana.ATRIBUTOS_VENTANA[2])));
        this.setVisible(true);
        
//Datos a cargar
        CRUD_Menu.selectMenu();// se pide el menu 
        ControladorMenuIW.CrearMenu();//se crear el objeto menu
        CRUD_Atributos.selectTipos();//se piden los atributos extras de las hamburguesas
        
    }//fin de Crear GUI
    
    private void createLogin(){
        login= new LoginPanel();
        login.setBounds(new Rectangle(new Point(0,0),login.getPreferredSize()));
        desktopPane.add(login);
        
    }
    
    private void labelVersion(){        
        
        labelVersion.setForeground(Color.WHITE);
        labelVersion.setOpaque(false);
        
        labelVersion.setBounds(new Rectangle(new Point(0,0 ),labelVersion.getPreferredSize()));//se crean los limites del label
        
        
        desktopPane.addComponentListener(new OyenteComponente());
        desktopPane.add(labelVersion);//se añde la label de la version 
        
        
    }
    
    
    
    private void iconosFondo(){
        desktopPane.setBackground(colorB); //se carga un color de fondo para combinar con la imagen
        //colores: 129,29,31 vinotinto claro     104, 29, 36 vinotinto marron
//se inicializan los vectores de iconos
        imgIconos= new ImageIcon[5];
        imgIconoSalir= new ImageIcon[1];
        try {                       //en caso de errores         
            //se carga la imagen de fondo
            //De esta forma el programa siempre podra acceder a la foto 
            //se llama la imagen desde el paquete                                          
            bufferdImagenFondo= ImageIO.read(MainWindow.class.getResource(DatosVentana.IMAGENES_VENTANA[0]));
            desktopPane.setBorder(new DesktopBorderBackground(bufferdImagenFondo,512,512));//se carga la imagen desde el paquete imagenes
            //Se envia la imagen como un borde para ser dibujado siempre centrada        
            //Iconos de los botones
            imgIconos[0]= new ImageIcon(ImageIO.read(OrdenPanelNO.class.getResource(DatosBotones.ICONOS_BOTONES_MAIN[0])));//
            imgIconos[1]= new ImageIcon(ImageIO.read(OrdenPanelNO.class.getResource(DatosBotones.ICONOS_BOTONES_MAIN[1])));
            imgIconos[2]= new ImageIcon(ImageIO.read(OrdenPanelNO.class.getResource(DatosBotones.ICONOS_BOTONES_MAIN[2])));
            imgIconos[3]= new ImageIcon(ImageIO.read(OrdenPanelNO.class.getResource(DatosBotones.ICONOS_BOTONES_MAIN[3])));
        //    imgIconos[4]= new ImageIcon(ImageIO.read(OrdenPanelNO.class.getResource(DatosBotones.ICONOS_BOTONES_MAIN[4])));   
            
            imgIconoSalir[0]= new ImageIcon(ImageIO.read(OrdenPanelNO.class.getResource(DatosBotones.ICONOS_BOTONES_MAIN[5])));        
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,e.getMessage()+"\nError en la Imagen de Fondo","Atención",JOptionPane.WARNING_MESSAGE);
        }// Ya se le puso fondo de imagen al escritorio 
    }

    private void botones(){
        //Botones Todo debajo:                
         //enviando los parametros para crear los botones
        panelBotones= new JPanel(new BorderLayout());        
        botonesMain = new Botonera(DatosBotones.NOMBRES_BOTONES_MAIN,DatosBotones.LETRAS_BOTONES_MAIN,imgIconos,new FlowLayout());                   
        botonesMain.setButtonsForeground(Color.BLACK);
        botonSalir= new Botonera(DatosBotones.BOTON_SALIR, DatosBotones.LETRA_SALIR,imgIconoSalir, new FlowLayout());
        botonSalir.setButtonsForeground(Color.BLACK);
        
        //se asignan los oyentes a los botones 
        botonesMain.setButtonEvent(new EventoBotonVerMenu(desktopPane), 0); //Evento del Boton Ver Menú
        botonesMain.setButtonEvent(new EventoBotonEditarMenu(desktopPane),1);//evento editar menu
        botonesMain.setButtonEvent(new EventoBotonRegistroDeVentas(desktopPane),2);//evento boton registro de ventas

        botonesMain.setButtonEvent(new EventoBotonNuevaOrden(desktopPane),3);   //evento boton nueva orden
      //  botonesMain.setButtonEvent(new EventoBotonStock(desktopPane),4);   //evento boton Inventario

        
        botonSalir.setButtonEvent(new EventoBotonCerrar(), 0);//Boton Salir 
        //se añade todo a la ventana principal         
        
        panelBotones.add(botonesMain,BorderLayout.CENTER);        
        panelBotones.add(botonSalir,BorderLayout.EAST);
    }
    
    private void menuBar(){
        menues = new Menues(DatosMenues.NOMBRES_MENUES_MAIN,DatosMenues.LETRAS_MENUES_MAIN);
        this.setJMenuBar(menues);
    }
    
    private class OyenteComponente extends ComponentAdapter{//clase privada para hacer dinamica el label
        private OyenteComponente(){
        
        }
        
        @Override
        public void componentResized(ComponentEvent e) {
            labelVersion.setLocation(desktopPane.getWidth()-labelVersion.getWidth(), desktopPane.getHeight()-labelVersion.getHeight());//localizando el label de la version en la ezquina inferiror derecha
            
            login.setLocation(desktopPane.getWidth()-login.getWidth(),0);//posicion del login en el desktopPane, esquina superior derecha
        }
        
    }
}


