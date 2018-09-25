package Ve.com.biller.vistas.reyes;
import Ve.com.biller.conexion.reyes.sql.CRUD_Menu;
import Ve.com.biller.eventos.reyes.EventoEMCerrarDialogoCrearPlatillos;
import Ve.com.biller.eventos.reyes.EventoEMCrearNuevoPlatilloCategoria;
import Ve.com.biller.helpers.reyes.Botonera;
import Ve.com.biller.helpers.reyes.RealOnlyDocFilter;
import Ve.com.biller.helpers.reyes.WordsOnlyDocFilter;
import Ve.com.biller.modelos.reyes.ControladorMenuIW;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.DatosEditarMenu;
import Ve.com.biller.modelos.reyes.Tipografia;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;
import net.miginfocom.swing.MigLayout;

/**
 *Clase para añadir Categorias y platillos 
 * @author Luis Reyes
 */
public class DialogoCrearNuevoPlatilloEM {
    

//PanelEditMenuIW usar el panel padre para actualizar una vez se halla creado el nuevo platillo
    
    
    
    protected PanelEditMenuIW panelPadre;
    private int index;//index del platillo
    Component parentComponent; 
    protected String categoria;//categoria a la que pertenecera el platillo a crear        
    String nombre;
    protected boolean esHamburguesa;
    protected boolean esHamburguesa_100gr;              
    protected double[] precios= new double[4];    
    JOptionPane jOption;
    JDialog jDialog;    
    protected JPanel panelContenedor;    
    JLabel labelAnadirCategoria;
    JLabel labelNombre;
    JTextField textFieldNombre;
    
    JLabel labelPreciosTamanos;

    
    protected JPanel panelFormulario;
    
    JLabel[] labelPrecios= new JLabel[4];
    
    protected JTextField[] textFieldPrecios= new JTextField[4];
    protected JCheckBox checkBox;
    
    Botonera botonera;
    public DialogoCrearNuevoPlatilloEM(Component parentComponent,PanelEditMenuIW panelPadre){
        this.parentComponent=parentComponent;
        this.panelPadre=panelPadre;
        iniciarPanelContenedor();

    }
    
    public DialogoCrearNuevoPlatilloEM(int index,Component parentComponent,PanelEditMenuIW panelPadre){        
        this.index=index;        
        this.parentComponent= parentComponent;
        this.panelPadre=panelPadre;
        
        //insets arriba izquierda abajo derecha
        iniciarPanelContenedor();
        crearGUI();//llamada al metodo principal
    }
    private void iniciarPanelContenedor(){
        panelContenedor= new JPanel(new MigLayout("insets 10 10 0 10"));//panel Contenedor
        panelContenedor.setBackground(Color.WHITE);
        panelContenedor.setOpaque(true);
    }
    private void crearGUI(){                        
        
       
                  //condicional para usar la misma clase para añadir categoria y platillos 
        esHamburguesa= CRUD_Menu.menuCategoria[index].isHamburguesa();
        categoria=CRUD_Menu.menuCategoria[index].getCategoria();        
                  
        crearLabelAnadirCategoria();
        crearPanelFormulario();
        
        panelContenedor.add(labelAnadirCategoria,"cell 0 0 2 1");
        panelContenedor.add(panelFormulario,"dock south");
        
        crearDialogo();                        
    }
    protected void crearLabelAnadirCategoria(){
        labelAnadirCategoria= new JLabel(DatosEditarMenu.LABELS_FORMULARIO[4]+categoria,JLabel.CENTER);                
        labelAnadirCategoria.setFont(Tipografia.ARIAL_25);
        labelAnadirCategoria.setForeground(Color.BLACK);
    }
    
    protected void crearDialogo(){
        jOption= new JOptionPane(panelContenedor,JOptionPane.PLAIN_MESSAGE,JOptionPane.DEFAULT_OPTION,
                null,new Object[]{});//se pone "new Object[]{}" para que no salgan opciones                        
        jDialog = jOption.createDialog(parentComponent,DatosEditarMenu.TITULO_DIALOGO_EDITAR_MENU);                          
        //jOption.setBackground(new Color(102, 5, 27));
        jDialog.setBackground(new Color(102, 5, 27));
               
        jDialog.setVisible(true); 
        
    }
    
    
    protected void crearPanelFormulario(){//metodo que crea el panel a partir de la zona Nombre del platillo
        PlainDocument doc ; 
        int aux=2;
        panelFormulario= new JPanel(new MigLayout("insets 10 10 10 10"));
        
        panelFormulario.setBackground(Color.WHITE);
        
      
        labelNombre= new JLabel(DatosEditarMenu.LABELS_FORMULARIO[0]);//label para la palabra Nombre en el formulario 
        labelNombre.setForeground(Color.BLACK);
        labelNombre.setFont(Tipografia.ARIAL_16);
        
        textFieldNombre= new JTextField();
        textFieldNombre.setForeground(Color.BLACK);
        textFieldNombre.setFont(Tipografia.ARIAL_16);
        doc=(PlainDocument)textFieldNombre.getDocument();
        doc.setDocumentFilter(new WordsOnlyDocFilter());
        
        labelPreciosTamanos= new JLabel(DatosEditarMenu.LABELS_FORMULARIO[1]);
        labelPreciosTamanos.setForeground(Color.BLACK);
        labelPreciosTamanos.setFont(Tipografia.ARIAL_BOLD_16);
        
        
        //"cell column row width height" 
        panelFormulario.add(labelNombre,"cell 0 0 1 1,width 100px");//label de la palabra "Nombre: "
        panelFormulario.add(textFieldNombre,"cell 1 0 1 1,width 150px");//text field
        panelFormulario.add(labelPreciosTamanos,"cell 0 1 2 1");//"cell column row width height"//palabra "precios o tamaños"   
        checkBox= new JCheckBox(DatosEditarMenu.NOMBRE_CHECKBOX_100GR);//check box para saber si la hamburguesa tendra el mismo comportamiento que una hamburguesa clasica
        if (esHamburguesa) {
            aux=3;            
            checkBox.setToolTipText(DatosEditarMenu.PISTA_CHECKBOX_100GR);//pista de que representa el checkbox
            checkBox.setBackground(Color.WHITE);
            checkBox.setForeground(Color.BLACK);
            checkBox.setFont(Tipografia.ARIAL_14);
            panelFormulario.add(checkBox,"cell 0 2 2 1");
        }
        
        for (int i = 0; i < 4; i++) {
            if (i==0) {
                labelPrecios[i]= new JLabel(DatosEditarMenu.LABELS_FORMULARIO[2]+(i+1)+DatosEditarMenu.LABELS_FORMULARIO[3]);                                    
            }else{
                labelPrecios[i]= new JLabel(DatosEditarMenu.LABELS_FORMULARIO[2]+(i+1));                        
            }
            
            textFieldPrecios[i]= new JTextField("0,0");
            
            doc = (PlainDocument)textFieldPrecios[i].getDocument();
            doc.setDocumentFilter(new RealOnlyDocFilter(0, true));
            
            textFieldPrecios[i].setFont(Tipografia.ARIAL_16);
            textFieldPrecios[i].setForeground(Color.BLACK);
            textFieldPrecios[i].setAlignmentX(JTextField.RIGHT_ALIGNMENT);
            labelPrecios[i].setForeground(Color.BLACK);
            labelPrecios[i].setFont(Tipografia.ARIAL_16);
            
            panelFormulario.add(labelPrecios[i],"cell 0 "+(i+aux)+" 1 1,width 100px");//se añade el label "Precio i"
            panelFormulario.add(textFieldPrecios[i],"cell 1 "+(i+aux)+" 1 1,width 150px");//Se añade el TextField del Precio                        
        }
        botonera= new Botonera(DatosBotones.NOMBRES_BOTONES_FORMULARIO_ANADIR_PLATILLO,new FlowLayout(FlowLayout.CENTER));//DOS botones añadir y cancelar
        
        botonera.setButtonsFont(Tipografia.ARIAL_BOLD_15);
        botonera.setButtonsBackground(new Color(235, 237, 239));
        botonera.setButtonsForeground(Color.BLACK);
        
        botonera.setBackground(new Color(102, 5, 27));
        //eventos botonera
        botonera.setButtonEvent(new EventoEMCerrarDialogoCrearPlatillos(this), 1);//evento que cierra el dialogo
        botonera.setButtonEvent(new EventoEMCrearNuevoPlatilloCategoria(this), 0);//evento que crear el nuevo platillo en el menu

       
        
        panelFormulario.add(botonera,"dock south,grow");
        
    }
            
    public void cerrarDialog(){                
        jDialog.dispose();
        jDialog=null;
        jOption=null;             
    }    
    
    
    public void crearPlatilloNuevo(){     //metodo de creacion del platillo             
        String auxiliarNombre=textFieldNombre.getText();
        
        for (int i = 0; i < precios.length; i++) {//iteracion para guardar todos los precios del formulario en un vector
            String aux= textFieldPrecios[i].getText().replace(',', '.');
            precios[i]=Double.parseDouble(aux);
        }        
        if (precios[0]==0||auxiliarNombre.isEmpty()) {//comprobante de que los campos no estan vacios
            dialogoAdvertencia();
        }else{
            actualizarMenu(auxiliarNombre);
        }
                
    }
    protected void actualizarMenu(String auxiliarNombre){
        CRUD_Menu.crearNuevoPlatilloCategoria(categoria,auxiliarNombre, precios, esHamburguesa,checkBox.isSelected());//se inserta el nuevo platillo en la base de datos
        
        cerrarDialog();//se cierra el dialogo de creacion del platillo con el formulario
        CRUD_Menu.selectMenu(); //se pide denuevo el menu
        ControladorMenuIW.CrearMenu();//se crea el texto del menu
        panelPadre.recargarVentana();//se recarga la ventana para mostrar la nueva categoria agregada
                
    }
    protected void dialogoAdvertencia(){
        JOptionPane.showMessageDialog(parentComponent,DatosEditarMenu.ADVERTENCIA_FORMULARIO_EDITAR_MENU,DatosEditarMenu.ADVERTENCIA_FORMULARIO_TITULO, JOptionPane.WARNING_MESSAGE);
    }
}

