package Ve.com.biller.vistas.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Menu;
import Ve.com.biller.estructuras.reyes.EventoEMShowAttributes;
import Ve.com.biller.eventos.reyes.EventoEMBotonDialogoCrearPlatillo;
import Ve.com.biller.eventos.reyes.EventoEM_EnterSwapTextFieldLabelNombre;

import Ve.com.biller.eventos.reyes.OyenteDobleClickSwapComponents;
import Ve.com.biller.eventos.reyes.EventoEM_EnterSwapTextFieldLabelPrecio;
import Ve.com.biller.eventos.reyes.OyenteEMClickEscapeCambiarTextFieldLabel;
import Ve.com.biller.eventos.reyes.OyenteEMFocoCambiarTextFieldLabel;
import Ve.com.biller.helpers.reyes.Botonera;
import Ve.com.biller.helpers.reyes.ImageManager;
import Ve.com.biller.helpers.reyes.LabelDeTextoCambiante;
import Ve.com.biller.helpers.reyes.RealOnlyDocFilter;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.ModeloIconosPlatillo;
import Ve.com.biller.modelos.reyes.Tipografia;
import Ve.com.biller.estructuras.reyes.Producto;
import Ve.com.biller.eventos.reyes.EventoEMCancelarEliminarPlatillo;
import Ve.com.biller.eventos.reyes.EventoEM_EliminarPlatillo;
import Ve.com.biller.eventos.reyes.EventoEMGUIEliminarPlatillo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;
import net.miginfocom.swing.MigLayout;
/**
 *
 * @author Luis Reyes
 */
public class PanelEditarPlatillosEM extends JPanel{
    PanelEditMenuIW panelPadre;
    int index;
    int size;
    
    Botonera[] botoneraEliminar;
    
    Producto[] productos;
    JPanel[] panelPreciosPlatillos;//panel contendor de exclusivamente los precios
    JPanel[] panelNombresIconos;//panel contenedor de los iconos y nombres del platillo
    JPanel[] panelFila;
    
    ImageIcon[] iconosPlatillos;
    LabelDeTextoCambiante[] labelNombresPlatillos;
    JLabel[] labelIconosPlatillos;
    JTextField[] textFieldNombres;
    
    LabelDeTextoCambiante[][] labelPreciosPlatillo;
    JTextField[][] textFieldPrecios; 
    Botonera botoneraAnadirPlatillo;
    ImageIcon iconoAnadir;
    ImageIcon iconoEliminar;

    
    public PanelEditarPlatillosEM(int index,PanelEditMenuIW panelPadre) {
        super(new MigLayout("insets 0 0 0 0"));
        this.index=index;
        this.panelPadre=panelPadre;
        
        crearGUI();
    }

    private void crearGUI() {
        JButton buttonHamAtribs;
        ImageIcon iconButtonHam=null;
        int orden;
        PlainDocument doc; //filter for the textField
        String[] posicions={
            "cell 0 0,width 90px",
            "cell 1 0,width 200px",            
            "cell 0 0,width 95px",//posiciones del segundo panel 
            "cell 1 0,width 95px",
            "cell 2 0,width 95px",                
            "cell 3 0,width 95px",
        };//coodenadas de la poscion de los componentes
        
        orden = 0;
        productos= CRUD_Menu.menuCategoria[index].getProductos();
        size= productos.length;
        
        
        panelNombresIconos= new JPanel[size];
        panelPreciosPlatillos= new JPanel[size];    
        panelFila = new JPanel[size];
        
        labelNombresPlatillos= new LabelDeTextoCambiante[size];
        labelIconosPlatillos= new JLabel[size];
        textFieldNombres= new JTextField[size];
        iconosPlatillos= new ImageIcon[size];        
        labelPreciosPlatillo= new LabelDeTextoCambiante[size][4];
        textFieldPrecios= new JTextField[size][4];        
        
        botoneraEliminar= new Botonera[size];//botones que se muestran cuando se desea eliminar un platillo
        
        for (int i = 0; i < size; i++) {
            panelNombresIconos[i]= new JPanel(new MigLayout("insets 2 2 2 2"));//panel que contendra los iconos y nombres
            panelPreciosPlatillos[i]= new JPanel(new MigLayout("insets 2 2 2 2"));   //panel que contendra los precios
            panelFila[i]=new JPanel(new MigLayout("insets 0 0 0 0"));
            //Iconos de los platillos 
            try{      
                switch(CRUD_Menu.menuCategoria[index].getCategoria()){                    
                    case "CAFÉS":    
                       ModeloIconosPlatillo.setIconosCafes(productos[i].getName(),iconosPlatillos, i);
                        //los iconos individuales de cada cafe 
                        break;    
                    case "HAMBURGUESAS":
                        ModeloIconosPlatillo.setIconosHamburguesas(productos[i].getName(),iconosPlatillos, i);
                        break;                   
                    case "MALTEADAS":
                        ModeloIconosPlatillo.setIconosMalteadas(productos[i].getName(),iconosPlatillos, i);
                        break;
                    case "PLATOS":
                        ModeloIconosPlatillo.setIconosPlatos(productos[i].getName(),iconosPlatillos, i);
                        break;   
                    case "ENTRADAS":
                        ModeloIconosPlatillo.setIconosEntradas(productos[i].getName(),iconosPlatillos, i);
                        break;
                    case "EXTRAS":
                        ModeloIconosPlatillo.setIconosExtras(productos[i].getName(),iconosPlatillos, i);
                        break;
                    case "POSTRES":
                        ModeloIconosPlatillo.setIconosPostres(productos[i].getName(),iconosPlatillos, i);
                        break;
                    case "BEBIDAS":
                        ModeloIconosPlatillo.setIconosBebidas(productos[i].getName(),iconosPlatillos, i);
                        break;
                        
                    default:
                          iconosPlatillos[i] = new ImageIcon(ImageIO.read(PanelEditarPlatillosEM.class.
                          getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[0])));//icono por defecto      
                        break;
                }    
                
                iconButtonHam= new ImageIcon(ImageIO.read(PanelEditarPlatillosEM.class.
                          getResource(DatosBotones.ICONOS_BOTONES_MAIN[0])));//icono atributos hamburguesa
                
            }catch(IOException e){ }//fin de los iconos
            iconosPlatillos[i]=ImageManager.iconResize(iconosPlatillos[i], 64, 64);
            labelIconosPlatillos[i]= new JLabel(iconosPlatillos[i]);
            
            textFieldNombres[i]= new JTextField(productos[i].getName());//label del nombre
           
            textFieldNombres[i].setForeground(Color.BLACK);//color de fuente del nombre de los platillos            
            textFieldNombres[i].setFont(Tipografia.ARIAL_16);//fuente de los nombres de los platillo
            
            labelNombresPlatillos[i]= new LabelDeTextoCambiante(productos[i].getName(),JLabel.LEFT);
            labelNombresPlatillos[i].setForeground(Color.BLACK);//color de fuente del nombre de los platillos 
            labelNombresPlatillos[i].setFont(Tipografia.ARIAL_16);//fuente de los nombres de los platillo
                       
            
          
            for (int j = 0; j < 4; j++) {
                
                textFieldPrecios[i][j]=new JTextField(String.format("%1.2f",productos[i].getPrecio(j)));//textField de editar precios                            
               
                labelPreciosPlatillo[i][j]=new LabelDeTextoCambiante(String.format("%1.2f",productos[i].getPrecio(j)),JLabel.RIGHT); //label visual del precio
                labelPreciosPlatillo[i][j].setVerticalAlignment(JLabel.BOTTOM);
                //precios originales para verificar si ha habido algun cambio                                
                
                labelPreciosPlatillo[i][j].setFont(Tipografia.ARIAL_12);
                labelPreciosPlatillo[i][j].setForeground(Color.BLACK);
                textFieldPrecios[i][j].setFont(Tipografia.ARIAL_12);
                textFieldPrecios[i][j].setForeground(Color.BLACK);
                //filtrado de ducumento para solo perimitir numeros reales
                doc = (PlainDocument)textFieldPrecios[i][j].getDocument();
                doc.setDocumentFilter(new RealOnlyDocFilter(0, true));
            }//fin de j                               
            
            if (orden%2==0) {
                panelFila[i].setBackground(Color.WHITE);
                
            }
            orden++;
            panelFila[i].setOpaque(true);
            panelNombresIconos[i].setOpaque(false);
            panelPreciosPlatillos[i].setOpaque(false);
            
            panelNombresIconos[i].add(labelIconosPlatillos[i],posicions[0]);//icono del platillo
            panelNombresIconos[i].add(labelNombresPlatillos[i],posicions[1]);//nombre del platillo            
            
            panelPreciosPlatillos[i].add(labelPreciosPlatillo[i][0],posicions[2]);
            panelPreciosPlatillos[i].add(labelPreciosPlatillo[i][1],posicions[3]);
            panelPreciosPlatillos[i].add(labelPreciosPlatillo[i][2],posicions[4]);
            panelPreciosPlatillos[i].add(labelPreciosPlatillo[i][3],posicions[5]);            
            
            //se añade todo a su contenedor correspondiente
            panelFila[i].add(panelNombresIconos[i]);//se añade el panel de nombre e icono a la fila 
            
            panelFila[i].add(panelPreciosPlatillos[i]);//se añade el panel de precio al panel fila
            
            
            this.add(panelFila[i],"span 2,wrap,growx");//se añade el panel de los precios
            panelFila[i].setPreferredSize(new Dimension(720,panelFila[i].getHeight()));
            
            
            //Asignacion de Eventos
            //evento para editar los nombres
            labelNombresPlatillos[i].addMouseListener(new OyenteDobleClickSwapComponents(posicions[1]
                    , textFieldNombres[i], labelNombresPlatillos[i], panelNombresIconos[i]));

            //evento para editar con doble click
            labelPreciosPlatillo[i][0].addMouseListener(new OyenteDobleClickSwapComponents(posicions[2]
                    , textFieldPrecios[i][0], labelPreciosPlatillo[i][0], panelPreciosPlatillos[i]));
            labelPreciosPlatillo[i][1].addMouseListener(new OyenteDobleClickSwapComponents(posicions[3]
                    , textFieldPrecios[i][1], labelPreciosPlatillo[i][1], panelPreciosPlatillos[i]));
            labelPreciosPlatillo[i][2].addMouseListener(new OyenteDobleClickSwapComponents(posicions[4]
                    , textFieldPrecios[i][2], labelPreciosPlatillo[i][2], panelPreciosPlatillos[i]));
            labelPreciosPlatillo[i][3].addMouseListener(new OyenteDobleClickSwapComponents(posicions[5]
                    , textFieldPrecios[i][3], labelPreciosPlatillo[i][3], panelPreciosPlatillos[i]));
            //evento para volver con la tecla enter
            textFieldNombres[i].addActionListener(new EventoEM_EnterSwapTextFieldLabelNombre(textFieldNombres[i],
                    labelNombresPlatillos[i], panelNombresIconos[i],posicions[1]));
                                              
            textFieldPrecios[i][0].addActionListener(new EventoEM_EnterSwapTextFieldLabelPrecio(textFieldPrecios[i][0],
                    labelPreciosPlatillo[i][0], panelPreciosPlatillos[i], posicions[2]));
            textFieldPrecios[i][1].addActionListener(new EventoEM_EnterSwapTextFieldLabelPrecio(textFieldPrecios[i][1],
                    labelPreciosPlatillo[i][1], panelPreciosPlatillos[i], posicions[3]));
            textFieldPrecios[i][2].addActionListener(new EventoEM_EnterSwapTextFieldLabelPrecio(textFieldPrecios[i][2],
                    labelPreciosPlatillo[i][2], panelPreciosPlatillos[i], posicions[4]));
            textFieldPrecios[i][3].addActionListener(new EventoEM_EnterSwapTextFieldLabelPrecio(textFieldPrecios[i][3],
                    labelPreciosPlatillo[i][3], panelPreciosPlatillos[i], posicions[5]));
  
            //evento se da click a la tecla escape
            
            textFieldNombres[i].addKeyListener(new OyenteEMClickEscapeCambiarTextFieldLabel(panelNombresIconos[i],
                    textFieldNombres[i], labelNombresPlatillos[i], posicions[1]));//text field del nombre 
            
            textFieldPrecios[i][0].addKeyListener(new OyenteEMClickEscapeCambiarTextFieldLabel(panelPreciosPlatillos[i],textFieldPrecios[i][0], labelPreciosPlatillo[i][0], posicions[2]));//text field del precio 1
            textFieldPrecios[i][1].addKeyListener(new OyenteEMClickEscapeCambiarTextFieldLabel(panelPreciosPlatillos[i],                    textFieldPrecios[i][1], labelPreciosPlatillo[i][1], posicions[3]));//text field del precio 2
            textFieldPrecios[i][2].addKeyListener(new OyenteEMClickEscapeCambiarTextFieldLabel(panelPreciosPlatillos[i],                    textFieldPrecios[i][2], labelPreciosPlatillo[i][2], posicions[4]));//text field del precio 3
            textFieldPrecios[i][3].addKeyListener(new OyenteEMClickEscapeCambiarTextFieldLabel(panelPreciosPlatillos[i],                    textFieldPrecios[i][3], labelPreciosPlatillo[i][3], posicions[5]));//text field del precio 4
            
//evento se vuelve al Label cuando se pierde el Foco
            textFieldNombres[i].addFocusListener(new OyenteEMFocoCambiarTextFieldLabel(panelNombresIconos[i],
                    textFieldNombres[i], labelNombresPlatillos[i], posicions[1]));
            
            textFieldPrecios[i][0].addFocusListener(new OyenteEMFocoCambiarTextFieldLabel(panelPreciosPlatillos[i],
                    textFieldPrecios[i][0], labelPreciosPlatillo[i][0], posicions[2]));
            textFieldPrecios[i][1].addFocusListener(new OyenteEMFocoCambiarTextFieldLabel(panelPreciosPlatillos[i],
                    textFieldPrecios[i][1], labelPreciosPlatillo[i][1], posicions[3]));
            textFieldPrecios[i][2].addFocusListener(new OyenteEMFocoCambiarTextFieldLabel(panelPreciosPlatillos[i],
                    textFieldPrecios[i][2], labelPreciosPlatillo[i][2], posicions[4]));
            textFieldPrecios[i][3].addFocusListener(new OyenteEMFocoCambiarTextFieldLabel(panelPreciosPlatillos[i],
                    textFieldPrecios[i][3], labelPreciosPlatillo[i][3], posicions[5]));
            
//botonera de los botones eliminar 
            botoneraEliminar[i]= new Botonera(DatosBotones.NOMBRES_EDITAR_MENU_ELIMINAR_PLATILLO,new MigLayout());//se inicializan los botones de eliminar
            
            botoneraEliminar[i].setButtonsForeground(Color.BLACK);
            botoneraEliminar[i].setButtonsFont(Tipografia.ARIAL_BOLD_15);
            botoneraEliminar[i].setOpaque(false);
            //eventos de elminar platillo
            botoneraEliminar[i].setButtonEvent(new EventoEMCancelarEliminarPlatillo(this), 1);//evento cancelar eliminacion de platillo
            botoneraEliminar[i].setButtonEvent(new EventoEM_EliminarPlatillo(productos[i],this), 0);
            
        }//fin de i 
        botoneraAnadirPlatillo= new Botonera(DatosBotones.NOMBRES_BOTONES_ANADIR_PLATILLO,new FlowLayout(FlowLayout.LEFT));
        try {
            iconoAnadir= new ImageIcon(ImageIO.read(PanelEditarPlatillosEM.class.getResource(
                    DatosBotones.ICONOS_BOTONES_ANADIR_CATEGORIA)));   //icono añadir
            iconoEliminar=new ImageIcon(ImageIO.read(PanelEditarPlatillosEM.class.getResource(
                    DatosBotones.ICONOS_BOTONES_ORDENES[3]))); //icono borrar
        } catch (IOException ex) { }
        botoneraAnadirPlatillo.setOpaque(true);
        botoneraAnadirPlatillo.setButtonIcon(iconoAnadir, 0);//se adigna el icono añadir         
        botoneraAnadirPlatillo.setButtonIcon(iconoEliminar, 1);// se asigna icono borrar
        botoneraAnadirPlatillo.setButtonsForeground(Color.BLACK);
        botoneraAnadirPlatillo.setButtonsFont(Tipografia.ARIAL_BOLD_16);
        
        botoneraAnadirPlatillo.setButtonEvent(new EventoEMBotonDialogoCrearPlatillo(index,null,panelPadre), 0);//evento añadir platillo / crear nuevo platillo
        botoneraAnadirPlatillo.setButtonEvent(new EventoEMGUIEliminarPlatillo(this), 1);
        
        if (CRUD_Menu.menuCategoria[index].getCategoria().equals("HAMBURGUESAS")) {//boton para cambiar los extras de la hamburguesa
            buttonHamAtribs= new JButton("Acompañantes");
            buttonHamAtribs.setFont(Tipografia.ARIAL_BOLD_16);
            buttonHamAtribs.setForeground(Color.BLACK);
            buttonHamAtribs.setIcon(iconButtonHam);
            buttonHamAtribs.addActionListener(new EventoEMShowAttributes());
            
            botoneraAnadirPlatillo.add(buttonHamAtribs);
            
        }
        
        if (orden%2==0) {
            botoneraAnadirPlatillo.setBackground(Color.WHITE);
        }
        this.add(botoneraAnadirPlatillo,"span 10,wrap,growx");
    }
   
    
    public Producto[] getProductos(){return productos;}
    
    public LabelDeTextoCambiante[] getLabelPlatillosNombres(){
        return labelNombresPlatillos;
    }
    public LabelDeTextoCambiante[][] getLabelPlatillosPrecios(){
        return labelPreciosPlatillo;
    }
    public int getIndex(){return index;}      
    /**
     * metodo que cambia la GUI
     * a su modo de elminar platillos
     */
    public void guiEliminarPlatillos(){
        for (int i = 0; i < size; i++) {
            panelFila[i].remove(panelPreciosPlatillos[i]);//se remueven los precios
            panelFila[i].add(botoneraEliminar[i]);//se añaden los botones eliminar
            panelFila[i].repaint();
            panelFila[i].revalidate();
        }        
    }    
    public void guiCancelarEliminarPlatillos(){
        for (int i = 0; i < size; i++) {
            panelFila[i].remove(botoneraEliminar[i]);//se remueven lo botones elminar            
            panelFila[i].add(panelPreciosPlatillos[i]);//se añaden los precios
            panelFila[i].repaint();
            panelFila[i].revalidate();
        }      
    }
    public void recargarTodo(){
        panelPadre.recargarVentana();
    }
    
}
