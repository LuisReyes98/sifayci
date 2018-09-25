package Ve.com.biller.vistas.reyes;
/**
 *
 * @author Luis_Reyes
 */
import Ve.com.biller.eventos.reyes.EventoNODialogoAyudaRemoverPlatillo;
import Ve.com.biller.eventos.reyes.EventoNOBotonCerrarTab;
import Ve.com.biller.eventos.reyes.EventoNOGUIRemoverPlatillo;
import Ve.com.biller.eventos.reyes.EventoNODialogoEscogerCategoria;
import Ve.com.biller.eventos.reyes.EventoNOBotonRemoverPlatillo;
import Ve.com.biller.eventos.reyes.EventoNOGUIRetornarRemoverPlatillo;
import Ve.com.biller.eventos.reyes.EventoNOBotonTerminarOrden;
import Ve.com.biller.modelos.reyes.DatosOrdenesIW;
import Ve.com.biller.helpers.reyes.Botonera;
import Ve.com.biller.helpers.reyes.DateManager;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.conexion.reyes.sql.CRUD_Menu;
import Ve.com.biller.conexion.reyes.sql.CRUD_Ventas;
import Ve.com.biller.helpers.reyes.CellPhoneOnlyDocFilter;
import Ve.com.biller.helpers.reyes.ComboboxToolTipRenderer;
import Ve.com.biller.helpers.reyes.IntegerOnlyDocFilter;
import Ve.com.biller.helpers.reyes.MaxSizeDocFilter;
import Ve.com.biller.helpers.reyes.WordsOnlyDocFilter;
import Ve.com.biller.modelos.reyes.Tipografia;

import net.miginfocom.swing.MigLayout;//import the miglayout

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class OrdenPanelNO extends JPanel {  
    //valores clave que se tomaran del panel para registrarlos
    private JLabel labelFecha;
    private String fechaHora;
    private String hora;    
    private double totalOrden;
    private double totalIva;
    private double totalMasIva;    
    private  int ivaDesc;//valor porcentual del iva    
    private JLabel labelPagoOrdenTerminada;   
    private DefaultTableModel tablaModelo;
    private JTable tablaPedido;
    private JTextArea textAreaDescripcion;      
    private JTextArea textAreaMetodoPago;    
    private JLabel labelIvaDescuentoOrdenTerminada;//puede o no ser necesario para el registro de ventas                    
    private TableRowSorter ordenarTabla;    
    private JLabel labelTitulo; 
    private JPanel panelTitulo;
    private JScrollPane scrollTabla;
    private JScrollPane scrollDescripcion;    
    private ImageIcon[] imgIcon;
    private ImageIcon[] imgIconRemoverPlatillo;
    private ImageIcon   imgIconOrdenTerminada;    
    private Border borderNegro;   
    private Botonera botonesOrdenes;    //botones originales
    private Botonera botonesRemoverPlatillo; //botones Remover          
    private JLabel labelOrdenTerminada;
    private Botonera botonesTerminarOrden; //botones que aparecen al cerrar la orden y aun ver la ventana        
    private JScrollPane scrollMetodoPago;
    private JComboBox comboMetodoPago;   
    private JPanel panelMetodoPago;      
    private JSpinner spinnerIvaDescuento;
    private SpinnerModel spinnerModel;
    private JSpinner.DefaultEditor spinnerEditor;    
    private JSpinner spinnerNroMesa;
    private SpinnerModel spinnerModelNroMesa;
    private JSpinner.DefaultEditor spinnerEditorNroMesa;    
    private JPanel panelClientes;
    private JTextField textFieldNombreCliente;
    private JTextField textFieldCedulaCliente;       
    private JLabel labelNombre;
    private JLabel labelRifCedula;    
    private JLabel labelNombreOrdenTerminada;
    private JLabel labelCedulaOrdenTerminada;    
    private JTextPane textPaneTotal; 
    private SimpleAttributeSet attribs;//atributos sin iva del texto del total
    private SimpleAttributeSet atributes2;//estilo del precio nuevo calculado cuando hay iva o descuento
    private SimpleAttributeSet atributes1;//estilo del precio viejo mas pequeño cuando hay iva o descuento
    private StyledDocument docTextPaneTotal;
    private Color colorBackground;
    
    private JComboBox comboRifTypes;
    private List<String> toolTipComboRif;
    
    private JLabel labelDomicilio;
    private JLabel labelTelefono;
    private JTextField textFieldTelefono;
    private JTextField textFieldDomicilio;
    
    private JLabel labelTelefonoEnded;    
    private JLabel labelDomicilioEnded;
    private JLabel labelRifEnded;
    
    public OrdenPanelNO(JTabbedPane tabPane) {
        
        super(new MigLayout("insets 0 0 0 0")); //mig layout sin bordes
        
        crearGUI(tabPane);//Llamada al crear GUI
    }    
    private void crearGUI(JTabbedPane tabPane){  
        colorBackground=new Color(40, 55, 71);//color principal de la interfaz
        tabla();   //metodo que se encarga de crear la tabla             
        titulo();           //Titulo y lo referente al  panel superior norte       
        botones(tabPane);          //cargando el boton y su imagen     
        botonesRemover();
        botonesTerminar(tabPane);
        fecha();    
        descripcion();      
        textTotal();
        clientes();                       
        construirPanelPago();//se crea el panel     jpanel que contiene lo referente al metodo de pago       
        //"cell column row width height"        
        this.add(panelTitulo,"dock north,grow,span"); //titulo de la orden "Mesa N°#"        
        this.add(scrollTabla,"cell 1 0 2 5,grow,width 70%,height 90%");// tabla principal donde se ven los platillos de la orden 
        this.add(labelFecha,"cell 0 0 1 1,grow");//label de la fecha del pedido
        this.add(panelClientes,"cell 0 1 1 1,grow,height 18%");//panel de los datos del cliente
        this.add(scrollDescripcion,"cell 0 2 1 1,grow,shrink 10,height 15%");//scroll de la descripcion del pedido 
        this.add(textPaneTotal,"cell 0 3 1 1,grow,shrink 10,height 20%");//panel donde se muestra el precio total de la orden 
        this.add(panelMetodoPago,"cell 0 4 1 1,grow,shrink 10,height 18%");// panel del metodo de pago e iva 
        this.add(botonesOrdenes,"dock south,grow");//panel de los botones del sistema de ordenes 

        this.setVisible(true);
        
        // es necesario declarar una ventana que llame todas las formas de la ventana
    }   
    private void clientes(){//panel de los clientes cedula y nombre 
        PlainDocument doc;
        ComboboxToolTipRenderer renderer;
        panelClientes= new JPanel(new MigLayout());//se crea el panel que contiene los datos del cliente 
        
        panelClientes.setBorder(BorderFactory.createTitledBorder(borderNegro,DatosOrdenesIW.ELEMENTOS_CLIENTE_ORDEN[0]
                , TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Tipografia.ARIAL_14,Color.BLACK));
                //borde negro con la palabra "Datos Cliente"        
        
        labelNombre= new JLabel(DatosOrdenesIW.ELEMENTOS_CLIENTE_ORDEN[1],JLabel.CENTER);//frase Nombre
        labelNombre.setForeground(Color.BLACK);
        labelNombre.setFont(Tipografia.ARIAL_14);
        
        comboRifTypes= new JComboBox(DatosOrdenesIW.CHARACTERS_RIF);//combo de todas las letras que puede ser el rif V,E,J
        renderer = new ComboboxToolTipRenderer();
        comboRifTypes.setRenderer(renderer);
        comboRifTypes.setFont(Tipografia.ARIAL_14);
        toolTipComboRif= new ArrayList();        
        toolTipComboRif.addAll(Arrays.asList(DatosOrdenesIW.CHARACTERS_RIF_TOOL_TIP));//se añaden todos lo elementos del vector a la lista
        renderer.setTooltips(toolTipComboRif);
        
        
        labelRifCedula= new JLabel(DatosOrdenesIW.ELEMENTOS_CLIENTE_ORDEN[2],JLabel.CENTER);//frase Cedula
        labelRifCedula.setForeground(Color.BLACK);
        labelRifCedula.setFont(Tipografia.ARIAL_14);
                
        textFieldCedulaCliente= new JTextField();//text field de la cedula del cliente
        textFieldCedulaCliente.setForeground(Color.BLACK);
        textFieldCedulaCliente.setFont(Tipografia.ARIAL_14);
        doc=(PlainDocument)textFieldCedulaCliente.getDocument();
        doc.setDocumentFilter(new IntegerOnlyDocFilter(1, true));//filtro para que solo se acepten numeros enteros 
        
        textFieldNombreCliente= new JTextField();//text field del nombre del cliente
        doc=(PlainDocument)textFieldNombreCliente.getDocument();
        doc.setDocumentFilter(new WordsOnlyDocFilter());//filtro para permitir solo palabras        
        textFieldNombreCliente.setForeground(Color.BLACK);
        textFieldNombreCliente.setFont(Tipografia.ARIAL_14);
        
        labelDomicilio= new JLabel(DatosOrdenesIW.ELEMENTOS_CLIENTE_ORDEN[3],JLabel.CENTER);
        labelDomicilio.setForeground(Color.BLACK);
        labelDomicilio.setFont(Tipografia.ARIAL_14);
        
        
        textFieldDomicilio= new JTextField();
        textFieldDomicilio.setForeground(Color.BLACK);
        textFieldDomicilio.setFont(Tipografia.ARIAL_14);
        doc=(PlainDocument)textFieldDomicilio.getDocument();
        doc.setDocumentFilter(new MaxSizeDocFilter(30));//filtro para permitir solo palabras 
        
        labelTelefono= new JLabel(DatosOrdenesIW.ELEMENTOS_CLIENTE_ORDEN[4],JLabel.CENTER);
        labelTelefono.setForeground(Color.BLACK);
        labelTelefono.setFont(Tipografia.ARIAL_14);
        
        textFieldTelefono=new JTextField();
        doc=(PlainDocument)textFieldTelefono.getDocument();
        doc.setDocumentFilter(new CellPhoneOnlyDocFilter());
        textFieldTelefono.setForeground(Color.BLACK);
        textFieldTelefono.setFont(Tipografia.ARIAL_14);
        
                                                //"cell column row width height"
        panelClientes.add(labelNombre,              "cell 0 0 1 1,width 110px");//label "nombre 
        panelClientes.add(textFieldNombreCliente,   "cell 1 0 1 1,width 130px");        
        panelClientes.add(labelRifCedula,           "cell 2 0 1 1,width 60px");
        panelClientes.add(comboRifTypes,            "cell 3 0 1 1");
        panelClientes.add(textFieldCedulaCliente,   "cell 4 0 1 1,width 130px");
        
        panelClientes.add(labelTelefono,            "cell 0 1 1 1,width 110px");
        panelClientes.add(textFieldTelefono,        "cell 1 1 1 1,width 160px");
        panelClientes.add(labelDomicilio,           "cell 2 1 1 1,width 110px");
        panelClientes.add(textFieldDomicilio,       "cell 3 1 2 1,width 140px");
        
    }
    
    private void tabla(){
        //cargando la tabla y todos los metodos referentes a la misma               
        tablaModelo= new DefaultTableModel(DatosOrdenesIW.ENCABEZADO_ORDENES,0){ 
        //clase anonima para que no se pueda editar la tabla por teclado  
            @Override
            public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
            }
        };        
        tablaPedido=new JTable(tablaModelo){ //nueva tabla           
            @Override
            public int getRowHeight() {
                return 27;//altura de las celdas de la tabla
            }
        };
               
        tablaPedido.setFont(Tipografia.ARIAL_BOLD_15);//fuente arial negrita 15
        tablaPedido.setForeground(Color.BLACK);//color de letra negro
        
        //columna Nombre 
        tablaPedido.getColumnModel().getColumn(0).setPreferredWidth(250);        
        tablaPedido.getColumnModel().getColumn(0).setMinWidth(80);
        tablaPedido.getColumnModel().getColumn(0).setMaxWidth(900);
        //Columna Precio
        tablaPedido.getColumnModel().getColumn(1).setMinWidth(70);
        tablaPedido.getColumnModel().getColumn(1).setPreferredWidth(70);
        tablaPedido.getColumnModel().getColumn(1).setMaxWidth(200);
     
        
        ordenarTabla= new TableRowSorter(tablaModelo);    
        
        tablaPedido.setRowSorter(ordenarTabla);
        
        scrollTabla= new JScrollPane(tablaPedido);

        borderNegro= new LineBorder(Color.black,1);
        
        scrollTabla.setBorder(borderNegro);
    }
    private void titulo(){
        panelTitulo= new JPanel(new FlowLayout(FlowLayout.CENTER));  
        panelTitulo.setOpaque(true);
        panelTitulo.setBackground(Color.WHITE);
        
       
        
        labelTitulo= new JLabel(DatosOrdenesIW.TITULO_ORDEN[0],JLabel.RIGHT); //label con la palabra "Mesa N°" 
        labelTitulo.setFont(Tipografia.ARIAL_25);
        labelTitulo.setForeground(Color.BLACK);
        labelTitulo.setOpaque(true);
        labelTitulo.setBackground(Color.WHITE);
        
        spinnerModelNroMesa= new SpinnerNumberModel(1,//initial value
                                            1,//minimal value
                                            30,//maximum value
                                            1); //steps 
        spinnerNroMesa= new JSpinner(spinnerModelNroMesa);
        spinnerEditorNroMesa= new JSpinner.DefaultEditor(spinnerNroMesa);
        spinnerEditorNroMesa.getTextField().setEditable(false);
        spinnerEditorNroMesa.getTextField().setHorizontalAlignment(JLabel.RIGHT);
        spinnerEditorNroMesa.getTextField().setBackground(Color.WHITE);
        spinnerEditorNroMesa.getTextField().setForeground(Color.BLACK);
        spinnerEditorNroMesa.getTextField().setOpaque(true);
        spinnerEditorNroMesa.getTextField().setFont(Tipografia.ARIAL_25);
        
        spinnerNroMesa.setEditor(spinnerEditorNroMesa);
        
        panelTitulo.add(labelTitulo);
        panelTitulo.add(spinnerNroMesa);
        
    }          
    private void botones(JTabbedPane tabPane){
         imgIcon= new ImageIcon[DatosBotones.ICONOS_BOTONES_ORDENES.length];
        try{            
            for (int i = 0; i < imgIcon.length; i++) {
                imgIcon[i]= new ImageIcon(ImageIO.read(OrdenPanelNO.class.getResource(DatosBotones.ICONOS_BOTONES_ORDENES[i])));
            }
            //Icono añadir 0 Icono remover Platillo 1 Icono terminar 2 icono cancelar 3
            
        }catch(IOException ex){        }        
        
        botonesOrdenes= new Botonera(DatosBotones.NOMBRES_BOTONES_ORDENES, DatosBotones.LETRAS_BOTONES_ORDENES,imgIcon,
                new FlowLayout(FlowLayout.LEFT));        
        botonesOrdenes.setButtonsBackground(Color.WHITE);
        botonesOrdenes.setButtonsForeground(Color.BLACK);
        botonesOrdenes.setOpaque(true);
        botonesOrdenes.setBackground(colorBackground);//fondo de los colores de los botones         
        //Asignacion de oyentes a los botones
       
        botonesOrdenes.setButtonEvent(new EventoNODialogoEscogerCategoria(DatosOrdenesIW.TITULO_DIALOGO_CATEGORIAS,
                CRUD_Menu.categorias,null,this),0); //boton añadir Platillo
        
        botonesOrdenes.setButtonEvent(new EventoNOGUIRemoverPlatillo(this), 1);//boton remover platillo
       
        botonesOrdenes.setButtonEvent(new EventoNOBotonTerminarOrden(this), 2); //boton Terminar Orden       
       
        botonesOrdenes.setButtonEvent(new EventoNOBotonCerrarTab(tabPane, OrdenPanelNO.this), 3); //boton cancelar orden
    }    
    private void botonesRemover(){
        botonesRemoverPlatillo= new Botonera(DatosBotones.NOMBRES_REMOVER_PLATILLO, new FlowLayout(FlowLayout.LEFT));
        imgIconRemoverPlatillo = new ImageIcon[DatosBotones.ICONOS_BOTONES_REMOVER_PLATILLO.length];
        try{
            for (int i = 0; i < imgIconRemoverPlatillo.length; i++) {
                imgIconRemoverPlatillo[i]=new ImageIcon(ImageIO.read(OrdenPanelNO.class.getResource(DatosBotones.
                        ICONOS_BOTONES_REMOVER_PLATILLO[i])));
                botonesRemoverPlatillo.setButtonIcon(imgIconRemoverPlatillo[i], i);
            }        
        }catch(IOException ex){ }                       
        botonesRemoverPlatillo.getButton(2).setOpaque(false);//boton de interrogante que es solo el icono
        botonesRemoverPlatillo.getButton(2).setBorderPainted(false);
        botonesRemoverPlatillo.getButton(2).setToolTipText(DatosBotones.PISTA_REMOVER_PLATILLO);
        
        botonesRemoverPlatillo.setButtonEvent(new EventoNOBotonRemoverPlatillo(tablaModelo, tablaPedido, this), 0);
        botonesRemoverPlatillo.setButtonEvent(new EventoNOGUIRetornarRemoverPlatillo(this), 1);
        botonesRemoverPlatillo.setButtonEvent(new EventoNODialogoAyudaRemoverPlatillo(),2);
        
        botonesRemoverPlatillo.setButtonsBackground(Color.WHITE);
        botonesRemoverPlatillo.setButtonsForeground(Color.BLACK);
        botonesRemoverPlatillo.setOpaque(true);
        botonesRemoverPlatillo.setBackground(colorBackground);
    }    
    private void botonesTerminar(JTabbedPane tabPane) {
        
        botonesTerminarOrden= new Botonera(DatosBotones.NOMBRES_BOTONES_ORDEN_TERMINADA,new FlowLayout(FlowLayout.LEFT));
        try{
            imgIconOrdenTerminada= new ImageIcon(ImageIO.read(OrdenPanelNO.class.getResource(
            DatosBotones.ICONOS_BOTON_ORDEN_TERMINADA)));
        }catch(IOException ex){}
        botonesTerminarOrden.setButtonIcon(imgIconOrdenTerminada, 0);
        //label de que la orden se ha cerrado 
        labelOrdenTerminada= new JLabel(DatosOrdenesIW.LABEL_ORDEN_TERMINADA
                , JLabel.CENTER);
        labelOrdenTerminada.setOpaque(false);
        labelOrdenTerminada.setForeground(Color.WHITE);
        labelOrdenTerminada.setFont(Tipografia.ARIAL_BOLD_24);
        
        botonesTerminarOrden.add(labelOrdenTerminada);
        
        botonesTerminarOrden.setButtonEvent(new EventoNOBotonCerrarTab(tabPane, this), 0);
        botonesTerminarOrden.setButtonsBackground(Color.WHITE);
        botonesTerminarOrden.setButtonsForeground(Color.BLACK);
        botonesTerminarOrden.setOpaque(true);
        botonesTerminarOrden.setBackground(colorBackground);
        
        
    }    
    private void fecha(){
            //la labelFecha del panel 
        fechaHora=DateManager.getTodayDateString("dd/MM/yyyy");//fecha visual que ve el usuario
        labelFecha = new JLabel(DatosOrdenesIW.CONTENIDO_ORDEN_PANEL[0]+fechaHora);
        labelFecha.setFont(Tipografia.ARIAL_BOLD_15);
        labelFecha.setForeground(Color.BLACK);
        
    }//label fecha
    private void descripcion(){
          //el are de texto de la descripcion         
        textAreaDescripcion= new JTextArea(1,10);//filas columnas 
        textAreaDescripcion.setEditable(true);
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);
        textAreaDescripcion.setFont(Tipografia.ARIAL_14);
        textAreaDescripcion.setForeground(Color.BLACK);
         //scroll del panel de la descripcion
        scrollDescripcion= new JScrollPane(textAreaDescripcion);  

        
        scrollDescripcion.setBorder(BorderFactory.createTitledBorder(borderNegro,DatosOrdenesIW.CONTENIDO_ORDEN_PANEL[1], TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Tipografia.ARIAL_14,Color.BLACK));
    }
    private void textTotal(){
         //area de texto donde se muestra el total de la compra
        textPaneTotal= new JTextPane();
        attribs = new SimpleAttributeSet();//atributos sin iva del texto del total
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_RIGHT);//alineamiento hacia la derecha
        StyleConstants.setFontFamily(attribs, "Arial");//familia de la letra 
        StyleConstants.setFontSize(attribs, 30);//tamaño de letra
        StyleConstants.setBold(attribs, true);//negrita
        
        atributes2 = new SimpleAttributeSet();//estilo del precio nuevo calculado cuando hay iva o descuento
        StyleConstants.setAlignment(atributes2, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setFontFamily(atributes2, "Arial");//familia de la letra 
        StyleConstants.setFontSize(atributes2, 28);//tamaño de letra
        StyleConstants.setBold(atributes2, true);//negrita
        
        
        atributes1 = new SimpleAttributeSet();//estilo del precio viejo mas pequeño cuando hay iva o descuento
        StyleConstants.setAlignment(atributes1, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setFontFamily(atributes1, "Arial");//familia de la letra 
        StyleConstants.setFontSize(atributes1, 18);//tamaño de letra
        StyleConstants.setBold(atributes1, true);//negrita   
        
        textPaneTotal.setText(DatosOrdenesIW.CONTENIDO_ORDEN_PANEL[2]);        
        
        textPaneTotal.setParagraphAttributes(attribs, true);
        textPaneTotal.setEditable(false);     

        textPaneTotal.setBackground(Color.WHITE);
        textPaneTotal.setForeground(Color.BLACK);
        textPaneTotal.setOpaque(true);
        textPaneTotal.setPreferredSize(new Dimension(300,500));//ancho alto
        textPaneTotal.setBorder(BorderFactory.createTitledBorder(borderNegro, DatosOrdenesIW.CONTENIDO_ORDEN_PANEL[3],
        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Tipografia.ARIAL_14,Color.BLACK));
        
        docTextPaneTotal = (StyledDocument) textPaneTotal.getDocument();//los valores visuales se guardan por valor por lo cual no hace falta hacer *textPane.setText(doc);
    }
    private void construirPanelPago(){
        panelMetodoPago= new JPanel(new MigLayout("insets 0 0 0 0"));  //
        panelMetodoPago.setBorder(BorderFactory.createTitledBorder(borderNegro,DatosOrdenesIW.CONTENIDO_ORDEN_PANEL[4],
        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Tipografia.ARIAL_14,Color.BLACK));
        
        comboPago();//combo de los distintos metodos de pago  
        //Text Area para describir el metodo de pago         
        descripcionPago();        
        spinner();//spinner del IVA      
        //se añade todo al panel de los metodos depago 
        panelMetodoPago.add(comboMetodoPago,"cell 0 0 1 1,width 40%,grow");//"cell column row width height"    
        panelMetodoPago.add(spinnerIvaDescuento,"cell 1 0 1 1,width 60%");//"cell column row width height"
        panelMetodoPago.add(scrollMetodoPago,"cell 0 1 2 1,span,height 60%,grow");//"cell column row width height"

    }
    private void spinner(){

          //Spinner del IVA y descuento 
        spinnerModel= new SpinnerNumberModel(0,//initial value
                                            -100,//minimal value
                                            50,//maximum value
                                            1); //steps 
        spinnerIvaDescuento= new JSpinner(spinnerModel);
        
        spinnerEditor= new JSpinner.NumberEditor(spinnerIvaDescuento);
        spinnerEditor.getTextField().setEditable(false);
    
        
        spinnerEditor.getTextField().setHorizontalAlignment(JLabel.RIGHT);
        spinnerEditor.getTextField().setBackground(Color.WHITE);
        spinnerEditor.getTextField().setForeground(Color.BLACK);
        spinnerEditor.getTextField().setOpaque(true);
        
        spinnerIvaDescuento.setEditor(spinnerEditor);
        spinnerIvaDescuento.setFont(Tipografia.ARIAL_14);
        
  
        spinnerIvaDescuento.setToolTipText(DatosOrdenesIW.PISTA_SPINNER);//pista de lo que hace 
        spinnerIvaDescuento.setBorder(BorderFactory.createTitledBorder(borderNegro, DatosOrdenesIW.CONTENIDO_ORDEN_PANEL[5],
        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Tipografia.ARIAL_14,Color.BLACK));   
        
        spinnerIvaDescuento.addChangeListener((ChangeEvent e) -> {
            updatePrice();
            panelMetodoPago.repaint();
            panelMetodoPago.revalidate();
            textPaneTotal.repaint();
            textPaneTotal.revalidate();
        });//lambda expression
    }
    private void comboPago(){
         //dropdown combo box que contiene selecciones de los metodos de pago 
        comboMetodoPago= new JComboBox(DatosOrdenesIW.METODOS_DE_PAGO);
        comboMetodoPago.setFont(Tipografia.ARIAL_14);
     
    }    
    
    private void descripcionPago(){
        textAreaMetodoPago= new JTextArea(1,30);
        textAreaMetodoPago.setBackground(Color.WHITE);
        textAreaMetodoPago.setForeground(Color.BLACK);
        textAreaMetodoPago.setFont(Tipografia.ARIAL_14);               
        textAreaMetodoPago.setEditable(true);
        textAreaMetodoPago.setLineWrap(true);
        textAreaMetodoPago.setWrapStyleWord(true);
        textAreaMetodoPago.setBorder(BorderFactory.createTitledBorder(null, DatosOrdenesIW.CONTENIDO_ORDEN_PANEL[6], 
        TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.CENTER, Tipografia.ARIAL_14,Color.BLACK));        
        
        scrollMetodoPago= new JScrollPane(textAreaMetodoPago);
     
    }        
    /**
     * Metodo que acomoda la parte visual para marcar la orden como terminada
     * 
     */
    public void endOrder(){//metodo final que cambia la gui para que el ususario no pueda editar más
        ivaDesc= (int) spinnerModel.getValue();
      
                    
        labelPagoOrdenTerminada= new JLabel(DatosOrdenesIW.METODOS_DE_PAGO[comboMetodoPago.getSelectedIndex()]);
        labelPagoOrdenTerminada.setFont(Tipografia.ARIAL_14);
        labelPagoOrdenTerminada.setForeground(Color.BLACK);
        labelPagoOrdenTerminada.setBackground(Color.WHITE);
        labelPagoOrdenTerminada.setOpaque(true);
        labelPagoOrdenTerminada.setHorizontalAlignment(JLabel.LEFT);
        
        labelIvaDescuentoOrdenTerminada= new JLabel();
        labelIvaDescuentoOrdenTerminada.setFont(Tipografia.ARIAL_14);
        labelIvaDescuentoOrdenTerminada.setForeground(Color.BLACK);
        labelIvaDescuentoOrdenTerminada.setBackground(Color.WHITE);
        labelIvaDescuentoOrdenTerminada.setText(DatosOrdenesIW.ELEMENTOS_IVA_DESCUENTO[0]);
        //label que se pone si el iva/descuento es igual a cero ==0  
        
        labelIvaDescuentoOrdenTerminada.setOpaque(true);
        labelIvaDescuentoOrdenTerminada.setHorizontalAlignment(JLabel.RIGHT);
        
        this.updatePrice();
        if(ivaDesc!=0){                                     
            if(ivaDesc>0){
                labelIvaDescuentoOrdenTerminada.setText(DatosOrdenesIW.ELEMENTOS_IVA_DESCUENTO[1]+
                ivaDesc+DatosOrdenesIW.ELEMENTOS_IVA_DESCUENTO[3]+String.format("%1.2f",totalIva));      
            }else if(ivaDesc<0){                        
                labelIvaDescuentoOrdenTerminada.setText(DatosOrdenesIW.ELEMENTOS_IVA_DESCUENTO[2]+
                ivaDesc+DatosOrdenesIW.ELEMENTOS_IVA_DESCUENTO[3]+String.format("%1.2f",totalIva));                            
                    }                   
                }
       
        
        //cambios en el panel de clientes
        
        labelNombreOrdenTerminada= new JLabel(textFieldNombreCliente.getText());
        labelNombreOrdenTerminada.setForeground(Color.BLACK);
        labelNombreOrdenTerminada.setFont(Tipografia.ARIAL_14);
        
        panelClientes.remove(textFieldNombreCliente);//se remueve el text field del nombre del cliente
        panelClientes.add(labelNombreOrdenTerminada,"cell 1 0 1 1,width 25%");//se cambia por un label que no puede ser editado
        
        labelCedulaOrdenTerminada=new JLabel(textFieldCedulaCliente.getText());
        labelCedulaOrdenTerminada.setForeground(Color.BLACK);
        labelCedulaOrdenTerminada.setFont(Tipografia.ARIAL_14);
        
        panelClientes.remove(textFieldCedulaCliente);//se remueve el textfield         
        panelClientes.add(labelCedulaOrdenTerminada,"cell 4 0 1 1,width 25%");//se cambia por un label para no ser editado
        
        labelTelefonoEnded= new JLabel(textFieldTelefono.getText());
        labelTelefonoEnded.setForeground(Color.BLACK);
        labelTelefonoEnded.setFont(Tipografia.ARIAL_14);
        
        panelClientes.remove(textFieldTelefono);//se remueve el textfield         
        panelClientes.add(labelTelefonoEnded,"cell 1 1 1 1,width 25%");//se cambia por un label para no ser editado
        
        labelDomicilioEnded= new JLabel(textFieldDomicilio.getText());
        labelDomicilioEnded.setForeground(Color.BLACK);
        labelDomicilioEnded.setFont(Tipografia.ARIAL_14);
        
        panelClientes.remove(textFieldDomicilio);//se remueve el textfield         
        panelClientes.add(labelDomicilioEnded,"cell 3 1 2 1,width 25%");//se cambia por un label para no ser editado
        
        labelRifEnded= new JLabel(DatosOrdenesIW.CHARACTERS_RIF[comboRifTypes.getSelectedIndex()]);
        labelRifEnded.setForeground(Color.BLACK);
        labelRifEnded.setFont(Tipografia.ARIAL_14);
        panelClientes.remove(comboRifTypes);//se remueve el textfield         
        panelClientes.add(labelRifEnded,"cell 3 0 1 1");//se cambia por un label para no ser editado
        
        
        panelClientes.revalidate();
        panelClientes.repaint();
        //cambios del panel metodo de pago
        panelMetodoPago.remove(spinnerIvaDescuento);//se remueve el spinner
        panelMetodoPago.add(labelIvaDescuentoOrdenTerminada,"cell 1 0 1 1,width 60%");//reemplazando el spinner con un label
        
        panelMetodoPago.remove(comboMetodoPago);//se remueve el combo (dropdown) de los metodos de pago 
        panelMetodoPago.add(labelPagoOrdenTerminada,"cell 0 0 1 1,width 40%,grow");//se reemplaza con un label
        panelMetodoPago.revalidate();//revalidar el panel metodo pago
        panelMetodoPago.repaint();//Se repinta el metodo de pago
        //fin cambios metodo de pago
        
        //cambios en el titulo de la orden
        panelTitulo.remove(spinnerNroMesa);
        labelTitulo.setText(labelTitulo.getText()+(int)spinnerModelNroMesa.getValue());        
        this.remove(botonesOrdenes);        
        this.add(botonesTerminarOrden,"dock south,grow,shrink 0");        
        textAreaMetodoPago.setEditable(false);
        textAreaDescripcion.setEditable(false);        
        this.repaint();//se repinta el contenedor
        this.revalidate();
                        
    }
    
    /**
     * metodo final que se llama para almacenar la venta
     * 
     */
    public void registrarVenta(){
        String nombreCliente= textFieldNombreCliente.getText();
        int cedulaCliente=Integer.parseInt(textFieldCedulaCliente.getText());
        String descripcionVenta=textAreaDescripcion.getText();
        String descripcionPago=textAreaMetodoPago.getText();
        String metodoDePago=DatosOrdenesIW.METODOS_DE_PAGO[comboMetodoPago.getSelectedIndex()];
        
        String tipoDeRif=DatosOrdenesIW.CHARACTERS_RIF[comboRifTypes.getSelectedIndex()];//caracter del rif
        
        String domicilio= textFieldDomicilio.getText();
        String telefono= textFieldTelefono.getText();
        
        fechaHora=DateManager.getTodayDateString("HH:mm:ss MM/dd/yyyy");
        //Se cambia el formato de la fecha para luego guardarlo en la base de datos es primordial que sea "HH:mm:ss MM/dd/yyyy"
        
        hora= DateManager.getTodayDateString("HH:mm:ss");//formtato de la hora a la que se procesa el pedido
        
        //llamado al metodo encargado de guardar la venta
        CRUD_Ventas.insertVenta(fechaHora, hora, descripcionVenta, tablaModelo,metodoDePago,descripcionPago, totalIva, totalMasIva,nombreCliente,cedulaCliente,tipoDeRif,domicilio,telefono);//Metodo que almacena la venta
    
    }
    
    public void addPlatilloOrden(String nombre,double precio,int cantidad){
        Object[] fila= {nombre,String.format("%1.2f",precio)};        
        for (int i = 0; i < cantidad; i++) {
            totalOrden+=precio;
            tablaModelo.addRow(fila);
        }
        
        this.updatePrice();
    }    
    public void restarCosto(double precio){
        totalOrden-=precio;
        this.updatePrice();
        
    }    
    
    public void guiRemoverPlatillo(){
        this.remove(botonesOrdenes);
        this.add(botonesRemoverPlatillo,"dock south,grow,shrink 0");
        this.repaint();            
    }    
    public void volverPanelOrdenes(){
        this.remove(botonesRemoverPlatillo);
        this.add(botonesOrdenes,"dock south,grow,shrink 0");
        this.repaint();          
    }
    
    
    public void updatePrice(){
        ivaDesc= (int) spinnerModel.getValue();//iva que se muestra en el spinner
                                
        
        totalIva= totalOrden*ivaDesc/100;//se calcula cuanto es el valor del iva / descuento
        totalMasIva = totalOrden+totalIva; //se suma el total del iva/descuento mas el total de la orden
        try {
                if(ivaDesc!=0){
                    textPaneTotal.setText(null);
                                   
                    docTextPaneTotal.insertString(docTextPaneTotal.getLength(), String.format(" %1.2f Bs",totalOrden), atributes1);//linea del precio viejo con letra mas pequeña
                    if(ivaDesc>0){                                             
                        docTextPaneTotal.insertString(docTextPaneTotal.getLength(),
                        DatosOrdenesIW.ELEMENTOS_TOTAL_IVA_DESCUENTO[0]+
                        String.format("%1.2f Bs",totalMasIva), atributes2);//segunta linea si es iva
                    }else if (ivaDesc<0){                                                  
                        docTextPaneTotal.insertString(docTextPaneTotal.getLength(),DatosOrdenesIW.
                        ELEMENTOS_TOTAL_IVA_DESCUENTO[1]+String.format("%1.2f Bs",totalMasIva), atributes2);//segunda linea si es descuento
                    }                   
                }else{                                                               
                    textPaneTotal.setParagraphAttributes(attribs, true);                  
                    textPaneTotal.setText(String.format("%1.2f Bs",totalOrden));                    
                }
            }catch (BadLocationException ex){
               
            }
    
    }
   
    public String getCliente(){
        return textFieldNombreCliente.getText();
    }
    public int getCedula(){
        
        return Integer.parseInt(textFieldCedulaCliente.getText());
    }
    public int getCantidadPlatillos(){    
        return tablaModelo.getRowCount();
    }
    public double getTotalOrden(){
        return totalOrden;
    }
    /**
     * verdad si tanto el campo cliente como el campo 
     * cedula estan rellenos
     * @return boolean 
     */
    public boolean isClientFilled(){   
        return !(textFieldNombreCliente.getText().isEmpty()||textFieldCedulaCliente.getText().isEmpty());
    }
}
