package Ve.com.biller.vistas.reyes;

import Ve.com.biller.helpers.reyes.ImageManager;
import Ve.com.biller.modelos.reyes.ModeloIconosPlatillo;
import Ve.com.biller.conexion.reyes.sql.CRUD_Menu;
import Ve.com.biller.eventos.reyes.EventoEMBotonDialogoCrearCategoria;
import Ve.com.biller.eventos.reyes.EventoEM_EnterSwapTextFieldLabelNombre;
import Ve.com.biller.eventos.reyes.OyenteEMClickEscapeCambiarTextFieldLabel;
import Ve.com.biller.eventos.reyes.OyenteDobleClickSwapComponents;
import Ve.com.biller.eventos.reyes.OyenteEMFocoCambiarTextFieldLabel;
import Ve.com.biller.helpers.reyes.Botonera;
import Ve.com.biller.helpers.reyes.LabelDeTextoCambiante;
import Ve.com.biller.modelos.reyes.Tipografia;
import Ve.com.biller.modelos.reyes.DatosBotones;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Luis Reyes
 */
public class PanelEditarCategoriasEM extends JPanel{
    PanelEditMenuIW panelPadre;
    Botonera botoneraAnadirCategoria;
    ImageIcon iconoAnadirCategoria;
    ImageIcon iconoEliminar;
    JLabel[] labelsIconos;
    LabelDeTextoCambiante[] labelsCategorias;
    ImageIcon[] iconosCategorias;    
    JTextField[] textFieldEditarCategorias;
    JPanel[] panelBarra;
    JPanel[] panelNombreIconos;
    int size;
    
    public PanelEditarCategoriasEM(PanelEditMenuIW panelPadre) {        
        super(new MigLayout("insets 0 0 0 0","","[] 0 []"));
        this.panelPadre=panelPadre;
        crearGUI();
    }

    private void crearGUI() {
        String[] posiciones={
            "cell 0 0,width 90px",
            "cell 1 0,width 340px"
        };
        int orden=0;
        size= CRUD_Menu.categorias.length;
        labelsCategorias= new LabelDeTextoCambiante[size];
        labelsIconos= new JLabel[size];
        iconosCategorias= new ImageIcon[size];
        panelBarra= new JPanel[size];
        panelNombreIconos= new JPanel[size];
        
        textFieldEditarCategorias= new JTextField[size];
        
        for (int i = 0; i < size; i++) { 
            
            panelBarra[i]= new JPanel(new MigLayout());
            
            panelNombreIconos[i]= new JPanel(new MigLayout("insets 2 2 2 2"));
                                                   //insets espacio_superior izquierda inferior derecha
            ModeloIconosPlatillo.setIconosCategorias(CRUD_Menu.categorias[i], iconosCategorias, i);//se pide el icono que pertenece al nombre de la categoria
            iconosCategorias[i]=ImageManager.iconResize(iconosCategorias[i], 64, 64);//Se cambia el tamaño del icono de 128x128 a 64x64
            labelsIconos[i]= new JLabel(iconosCategorias[i]);
            labelsCategorias[i]=new LabelDeTextoCambiante(CRUD_Menu.categorias[i],JLabel.LEFT);
            labelsCategorias[i].setFont(Tipografia.ARIAL_25);
            labelsCategorias[i].setOpaque(false);
            labelsCategorias[i].setForeground(Color.BLACK);
            labelsCategorias[i].setPreferredSize(new Dimension(900,90));
            labelsCategorias[i].setIconTextGap(20);
           
            textFieldEditarCategorias[i]=new JTextField(CRUD_Menu.categorias[i]);
            textFieldEditarCategorias[i].setColumns(15);
            textFieldEditarCategorias[i].setBackground(Color.WHITE);
            textFieldEditarCategorias[i].setForeground(Color.BLACK);
            textFieldEditarCategorias[i].setFont(Tipografia.ARIAL_25);
            
           
            panelBarra[i].setOpaque(true);
            if (orden%2==0) {
                panelBarra[i].setBackground(Color.WHITE);
            }                    
            orden++;
            
            //se añade todo a su contenedor
            panelNombreIconos[i].add(labelsIconos[i],posiciones[0]);//se añade el icono al panel individual de los nombres e iconos
            panelNombreIconos[i].add(labelsCategorias[i],posiciones[1]);//se el label del nombre el icono al panel individual de los nombres e iconos
            panelNombreIconos[i].setOpaque(false);
            
            panelBarra[i].add(panelNombreIconos[i]);
            panelBarra[i].setPreferredSize(new Dimension(700,panelBarra[i].getHeight()));
            
            this.add(panelBarra[i],"span 10,wrap,growx");     
            //asignacion de eventos 
            //evento doble click mostrar textfield
            labelsCategorias[i].addMouseListener(new OyenteDobleClickSwapComponents(posiciones[1]
                    ,textFieldEditarCategorias[i],labelsCategorias[i],panelNombreIconos[i]));
            //evento enter guardar cambio en el Label
            textFieldEditarCategorias[i].addActionListener(new EventoEM_EnterSwapTextFieldLabelNombre(
                    textFieldEditarCategorias[i], labelsCategorias[i], panelNombreIconos[i], posiciones[1]));            
            //evento para cuando se da click en escape            
            textFieldEditarCategorias[i].addKeyListener(new OyenteEMClickEscapeCambiarTextFieldLabel(panelNombreIconos[i]
                    , textFieldEditarCategorias[i], labelsCategorias[i], posiciones[1]));
            //evento volver al label si se sale de la escritura
             textFieldEditarCategorias[i].addFocusListener(new OyenteEMFocoCambiarTextFieldLabel(panelNombreIconos[i]
                    , textFieldEditarCategorias[i], labelsCategorias[i], posiciones[1]));
             
        }//fin del for
        
        
        
        try {
            iconoAnadirCategoria= new ImageIcon(ImageIO.read(PanelEditarCategoriasEM.class.getResource(
                    DatosBotones.ICONOS_BOTONES_ANADIR_CATEGORIA)));
            //iconoEliminar=new ImageIcon(ImageIO.read(PanelEditarPlatillos.class.getResource(
              //      DatosBotones.ICONOS_BOTONES_ORDENES[3]))); 
            
        } catch (IOException ex) { }
        botoneraAnadirCategoria= new Botonera(DatosBotones.NOMBRES_BOTONES_ANADIR_CATEGORIA,new FlowLayout(FlowLayout.LEFT));    
        botoneraAnadirCategoria.setButtonIcon(iconoAnadirCategoria,0);
        //botoneraAnadirCategoria.setButtonIcon(iconoEliminar,1);
        botoneraAnadirCategoria.setOpaque(true);
        botoneraAnadirCategoria.setButtonsFont(Tipografia.ARIAL_BOLD_16);
        botoneraAnadirCategoria.setButtonsForeground(Color.BLACK);
      
        //asignacion de eventos 
        
        botoneraAnadirCategoria.setButtonEvent(new EventoEMBotonDialogoCrearCategoria(null,panelPadre), 0);//formulario para añadir una nueva categoria
        if (orden%2==0) {
            botoneraAnadirCategoria.setBackground(Color.WHITE);
        } 
        
        this.add(botoneraAnadirCategoria,"span 10,wrap,growx");
        this.setVisible(true);
        
    }
    public void cambiarATextField(int index){
        panelBarra[index].remove(labelsCategorias[index]);
        panelBarra[index].add(textFieldEditarCategorias[index]);
        panelBarra[index].repaint();
        
    }
   
    
    public LabelDeTextoCambiante[] getLabelsCategorias(){
        return labelsCategorias;
    } 
    
    
    
}
