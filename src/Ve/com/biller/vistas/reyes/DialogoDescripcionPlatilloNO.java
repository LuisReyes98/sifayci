package Ve.com.biller.vistas.reyes;

import Ve.com.biller.eventos.reyes.EventoNOBotonOrdenAnadirPlatillo;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.DatosOrdenesIW;
import Ve.com.biller.conexion.reyes.sql.CRUD_Atributos;
import Ve.com.biller.conexion.reyes.sql.CRUD_Menu;
import Ve.com.biller.modelos.reyes.Tipografia;
import Ve.com.biller.estructuras.reyes.Producto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import net.miginfocom.swing.MigLayout;


/**
 *
 * @author Luis_Reyes
 */
public class DialogoDescripcionPlatilloNO {
    JPanel panelContenedor;
    JPanel panelPlatillo;
    JPanel panelTamanos;
    JPanel panelCantidad;
    JPanel panelHamburguesa;
    JOptionPane jOption;
    JDialog jDialog;    
    JSpinner spinnerCantidad;
    SpinnerModel spinnerModel;
    DefaultEditor spinnerEditor;    
    int indexCategoria;
    int indexPlatillo;
    ImageIcon iconoPlatillo;
    JLabel labelPrecioUnico;
    JLabel labelPlatillo;
    JLabel labelCategoria;
    JLabel labelTamano;
    JLabel labelCantidad;    
    JComboBox[] combosHamburguesa;
    JLabel[] labelsHamburguesa;     
    JComboBox comboTamanos;        
    JButton botonAceptar; //boton aceptar 
    
    String[] vectorTamanos;
    String[] vectorDescTamanos;
    double[] vectorPrecios;
    String titulo;//titulo y nombre descriptivo del platillo    
       
    String platillo;
    boolean hamburguesa;
    int cantidadPrecios=4;//cantidad de precios que tiene el producto
    
    JCheckBox checkCortesia;//para saber si el platillo es cortesia o no 
    Producto producto;//producto que se lee   
    
    public DialogoDescripcionPlatilloNO(ImageIcon iconoPlatillo,Component parentComponent,int indexCategoria,int indexPlatillo,boolean hamburguesa,OrdenPanelNO ordenPanel){
        this.iconoPlatillo=iconoPlatillo;
        this.indexCategoria = indexCategoria;
        this.indexPlatillo = indexPlatillo;
        this.hamburguesa= hamburguesa;
        
        crearGUI(parentComponent,ordenPanel);
    }
    private void crearGUI(Component parentComponent,OrdenPanelNO ordenPanel) {  
        String categoria;
        int num;
    //se añade el nombre y el icono del platillo 
        panelPlatillo= new JPanel(new BorderLayout());
        
        categoria= CRUD_Menu.categorias[indexCategoria];//la categoria del platillo
        
        if (categoria.charAt(categoria.length()-1)=='s'||categoria.charAt(categoria.length()-1)=='S') {
            categoria= categoria.substring(0, categoria.length()-1);//se le corta la ultima letra "S" a la categoria
        }

        labelCategoria= new JLabel(categoria,JLabel.CENTER);//nombre de la categoria en singular
        labelCategoria.setFont(Tipografia.ARIAL_BOLD_24);
        labelCategoria.setIcon(iconoPlatillo);//el icono del platillo
        labelCategoria.setVerticalTextPosition(JLabel.BOTTOM);
        labelCategoria.setHorizontalTextPosition(JLabel.CENTER);
        platillo= CRUD_Menu.getNombresPlatillos(indexCategoria)[indexPlatillo];//el nombre del platillo
        labelCategoria.setForeground(Color.BLACK);//color negro de las letras
        
        labelPlatillo= new JLabel(platillo,JLabel.CENTER);
        labelPlatillo.setFont(Tipografia.ARIAL_BOLD_24);
        labelPlatillo.setForeground(Color.BLACK);//color negro de las letras
        titulo= categoria+","+ platillo;//titulo y nombre descriptivo del platillo
        //Se crea un panel que contiene la pabra platillo su nombre y categoria
        
        panelPlatillo.add(labelCategoria,BorderLayout.NORTH);
        panelPlatillo.add(labelPlatillo,BorderLayout.SOUTH);           
        
        //Combo box con los precios del platillo
        producto= CRUD_Menu.menuCategoria[indexCategoria].getProductos()[indexPlatillo];
        if (producto.getPrecio(3)==0) {
            if (producto.getPrecio(2)==0) {
                if (producto.getPrecio(1)==0) {
                    cantidadPrecios = 1;
                }else{
                    cantidadPrecios = 2;
                }
            }else{
                    cantidadPrecios = 3;
            }
        }
        
        vectorTamanos= new String[cantidadPrecios];
        vectorPrecios= new double[cantidadPrecios];
        vectorDescTamanos= new String[cantidadPrecios];
        
        //Validacion de cuantos precios tiene el producto y como debera ser mostrado en la interfaz grafica 
        switch (cantidadPrecios) {
            case 4:
                //precios
                vectorPrecios[0] =  producto.getPrecio(0);
                vectorPrecios[1] =  producto.getPrecio(1);
                vectorPrecios[2] =  producto.getPrecio(2);
                vectorPrecios[3] =  producto.getPrecio(3);
                
                if (hamburguesa) {
                    vectorDescTamanos[0]= DatosOrdenesIW.TAMANOS_HAMBURGUESA_TABLA[0]; //"100gr",
                    vectorDescTamanos[1]= DatosOrdenesIW.TAMANOS_HAMBURGUESA_TABLA[1]; //"150gr",
                    vectorDescTamanos[2]= DatosOrdenesIW.TAMANOS_HAMBURGUESA_TABLA[2]; //"200gr",
                    
                    vectorTamanos[0]= DatosOrdenesIW.TAMANOS_HAMBURGUESA[0]+vectorPrecios[0];//"100gr",
                    vectorTamanos[1]= DatosOrdenesIW.TAMANOS_HAMBURGUESA[1]+vectorPrecios[1];//"150gr",
                    vectorTamanos[2]= DatosOrdenesIW.TAMANOS_HAMBURGUESA[2]+vectorPrecios[2];//"200gr",    
                }else{
                    //nombres del tamaño 
                    vectorDescTamanos[0]= DatosOrdenesIW.TAMANOS_TABLA[0];//pequeño
                    vectorDescTamanos[1]= DatosOrdenesIW.TAMANOS_TABLA[1];//mediano
                    vectorDescTamanos[2]= DatosOrdenesIW.TAMANOS_TABLA[2];//grande                     
                    
                    vectorTamanos[0]= DatosOrdenesIW.TAMANOS[0]+vectorPrecios[0];//pequeño
                    vectorTamanos[1]= DatosOrdenesIW.TAMANOS[1]+vectorPrecios[1];//mediano
                    vectorTamanos[2]= DatosOrdenesIW.TAMANOS[2]+vectorPrecios[2];//grande                     
                }
                    vectorDescTamanos[3]= DatosOrdenesIW.TAMANOS_TABLA[3];
                    vectorTamanos[3]= DatosOrdenesIW.TAMANOS[3]+vectorPrecios[3];//extra grande
                
                break;
            case 3:                
                vectorPrecios[0] =  producto.getPrecio(0);
                vectorPrecios[1] =  producto.getPrecio(1);
                vectorPrecios[2] =  producto.getPrecio(2);
                
                if (hamburguesa) {
                    vectorDescTamanos[0]= DatosOrdenesIW.TAMANOS_HAMBURGUESA_TABLA[0];//100 gr
                    vectorDescTamanos[1]= DatosOrdenesIW.TAMANOS_HAMBURGUESA_TABLA[1]; //150 gr
                    vectorDescTamanos[2]= DatosOrdenesIW.TAMANOS_HAMBURGUESA_TABLA[2]; //200 gr 
                    
                    vectorTamanos[0]= DatosOrdenesIW.TAMANOS_HAMBURGUESA[0]+vectorPrecios[0];//pequeño
                    vectorTamanos[1]= DatosOrdenesIW.TAMANOS_HAMBURGUESA[1]+vectorPrecios[1];//mediano
                    vectorTamanos[2]= DatosOrdenesIW.TAMANOS_HAMBURGUESA[2]+vectorPrecios[2];//grande   
                }else{
                    //nombres del tamaño 
                    vectorDescTamanos[0]= DatosOrdenesIW.TAMANOS_TABLA[0];
                    vectorDescTamanos[1]= DatosOrdenesIW.TAMANOS_TABLA[1];
                    vectorDescTamanos[2]= DatosOrdenesIW.TAMANOS_TABLA[2];

                    vectorTamanos[0]= DatosOrdenesIW.TAMANOS[0]+vectorPrecios[0];//pequeño
                    vectorTamanos[1]= DatosOrdenesIW.TAMANOS[1]+vectorPrecios[1];//mediano
                    vectorTamanos[2]= DatosOrdenesIW.TAMANOS[2]+vectorPrecios[2];//grande 
                }
                 
                
                
                break;
            case 2:
                vectorPrecios[0] =  producto.getPrecio(0);
                vectorPrecios[1] =  producto.getPrecio(1);
                if (hamburguesa) {
                    vectorDescTamanos[0]= DatosOrdenesIW.TAMANOS_HAMBURGUESA_TABLA[1];//150 gr
                    vectorDescTamanos[1]= DatosOrdenesIW.TAMANOS_HAMBURGUESA_TABLA[2]; //200 gr
                    
                    vectorTamanos[0]= DatosOrdenesIW.TAMANOS_HAMBURGUESA[1]+vectorPrecios[0];//mediano
                    vectorTamanos[1]= DatosOrdenesIW.TAMANOS_HAMBURGUESA[2]+vectorPrecios[1];//grande
                }else{
                   //nombres del tamaño 
                    vectorDescTamanos[0]= DatosOrdenesIW.TAMANOS_TABLA[1];//mediano
                    vectorDescTamanos[1]= DatosOrdenesIW.TAMANOS_TABLA[2];//grande

                    vectorTamanos[0]= DatosOrdenesIW.TAMANOS[1]+vectorPrecios[0];//mediano
                    vectorTamanos[1]= DatosOrdenesIW.TAMANOS[2]+vectorPrecios[1];//grande
                }
                
                
                break;           
            default:                
                vectorPrecios[0] = producto.getPrecio(0);               
                if (hamburguesa) {
                    if(producto.isIs100grHamburguer()){//si la hamburguesa tiene el atributo que su precio mas pequeño es de 100gr
                        vectorDescTamanos[0]= DatosOrdenesIW.TAMANOS_HAMBURGUESA_TABLA[0];//100 gr
                        vectorTamanos[0]= DatosOrdenesIW.TAMANOS_HAMBURGUESA[0]+vectorPrecios[0];//100gr 
                    }else{
                        vectorDescTamanos[0]= DatosOrdenesIW.TAMANOS_HAMBURGUESA_TABLA[1];//150 gr
                        vectorTamanos[0]= DatosOrdenesIW.TAMANOS_HAMBURGUESA[1]+vectorPrecios[0];//mediano150gr
                    }
                    
                }else{
                    vectorTamanos[0] = Double.toString(vectorPrecios[0]);//Precio Unico
                }

                break;
        }
        //segunda seccion tamaños        
        labelPrecioUnico= new JLabel(DatosOrdenesIW.TAMANOS[4]+vectorTamanos[0]);//nombre del precio        
        labelPrecioUnico.setFont(Tipografia.ARIAL_BOLD_24);        
        labelPrecioUnico.setForeground(Color.BLACK);//color negro de las letras
        comboTamanos= new JComboBox(vectorTamanos); //combo con los tamaños del platillo
        comboTamanos.setFont(Tipografia.ARIAL_BOLD_24);
        comboTamanos.setForeground(Color.BLACK);//color negro de las letras
        panelTamanos= new JPanel(new BorderLayout(0,2));
    
    
        if (cantidadPrecios==1) {
            
            labelTamano= new JLabel(DatosOrdenesIW.DIALOGO_DESCRIPCION[0],JLabel.CENTER);//palabra "precio"
            panelTamanos.add(labelPrecioUnico,BorderLayout.CENTER);
            
        }else{
            labelTamano= new JLabel(DatosOrdenesIW.DIALOGO_DESCRIPCION[2],JLabel.CENTER);//palabra "precios"
            panelTamanos.add(comboTamanos,BorderLayout.CENTER);
            
        }
        labelTamano.setFont(Tipografia.ARIAL_BOLD_24);
        labelTamano.setForeground(Color.BLACK);//color negro de las letras
        panelTamanos.add(labelTamano,BorderLayout.NORTH);
        //panel opcional en el caso de que se hamburguesa 
        if(hamburguesa){
            panelHamburguesa= new JPanel(new GridLayout(0,1));
            num=CRUD_Atributos.componentesHamburguesa.length;
            labelsHamburguesa= new JLabel[num];
            combosHamburguesa= new JComboBox[num];
            for (int i = 0; i < num; i++) {
                labelsHamburguesa[i]= new JLabel(CRUD_Atributos.componentesHamburguesa[i].getLabel());
                labelsHamburguesa[i].setForeground(Color.BLACK);
                labelsHamburguesa[i].setFont(Tipografia.ARIAL_BOLD_16);
                
                combosHamburguesa[i]= new JComboBox(CRUD_Atributos.componentesHamburguesa[i].getDescripciones());
                combosHamburguesa[i].setForeground(Color.BLACK);
                combosHamburguesa[i].setFont(Tipografia.ARIAL_BOLD_16); 
                
                panelHamburguesa.add(labelsHamburguesa[i]);
                panelHamburguesa.add(combosHamburguesa[i]);
            }                           
            panelHamburguesa.setOpaque(false);                        
            panelTamanos.add(panelHamburguesa,BorderLayout.SOUTH);
        }
        
        //fin del panel tamanos 
        
        //panel de cantidad de platillos
        panelCantidad= new JPanel(new MigLayout());
        
        labelCantidad= new JLabel (DatosOrdenesIW.DIALOGO_DESCRIPCION[1],JLabel.CENTER);//cantidad
        labelCantidad.setFont(Tipografia.ARIAL_BOLD_24);
        labelCantidad.setForeground(Color.BLACK);//color negro de las letras
        
        spinnerModel= new SpinnerNumberModel(1,//initial value
                                            1,//minimal value
                                            15,//maximum value
                                            1); //steps 
        
        spinnerCantidad= new JSpinner(spinnerModel);
        spinnerEditor= new DefaultEditor(spinnerCantidad);
        spinnerEditor.getTextField().setEditable(false);
        spinnerEditor.getTextField().setHorizontalAlignment(JLabel.RIGHT);
        spinnerEditor.getTextField().setBackground(Color.WHITE);
        spinnerEditor.getTextField().setForeground(Color.BLACK);
        spinnerEditor.getTextField().setOpaque(true);
        spinnerCantidad.setEditor(spinnerEditor);
        spinnerCantidad.setFont(Tipografia.ARIAL_BOLD_24);
        
      
        
        //check si el platillo a añadir es una cortesia
     
        checkCortesia= new JCheckBox(DatosOrdenesIW.DIALOGO_PLATILLO_CORTESIA);
        checkCortesia.setFont(Tipografia.ARIAL_BOLD_16);
        checkCortesia.setForeground(Color.WHITE);
        checkCortesia.setOpaque(true);
        checkCortesia.setBackground(new Color(185, 23, 23));

//checkCortesia.isSelected()
        
        
           //El boton se añadira en el mismo panel que contiene las cantidades 
        try{
            botonAceptar= new JButton(DatosBotones.NOMBRES_BOTONES_ORDENES[0], new ImageIcon(ImageIO.read(DialogoDescripcionPlatilloNO.class.getResource(
                            DatosBotones.ICONOS_BOTONES_DESCRIPCION_PLATILLO[0]))));
        }catch (IOException ex){}
        botonAceptar.setForeground(Color.BLACK);
        botonAceptar.setBackground(new Color(171, 235, 198));
        
        botonAceptar.addActionListener(new EventoNOBotonOrdenAnadirPlatillo(this, ordenPanel, spinnerModel, comboTamanos
               , vectorPrecios, vectorDescTamanos, titulo,hamburguesa,combosHamburguesa,checkCortesia));
        
        
        panelCantidad.add(labelCantidad,"wrap,span,width 150px");
        panelCantidad.add(spinnerCantidad,"wrap,span,width 150px");
        panelCantidad.add(checkCortesia,"wrap,span,width 150px");
        panelCantidad.add(botonAceptar,"wrap,span");
        
       
        
       
        //se añade todo a su contedor
        panelContenedor= new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
                  
        panelCantidad.setOpaque(false);
        panelPlatillo.setOpaque(false);
        panelTamanos.setOpaque(false);
        
        panelContenedor.add(panelPlatillo);
        panelContenedor.add(panelTamanos);
        panelContenedor.add(panelCantidad);
        
        panelContenedor.setBackground(Color.WHITE);//color de fondo de la mayoria del panel
        
        //se crea el dialogo a mostrar
        jOption= new JOptionPane(panelContenedor,JOptionPane.PLAIN_MESSAGE,JOptionPane.YES_NO_OPTION,  null, DatosOrdenesIW.OPCION_CANCELAR);
        jOption.setBackground(Color.WHITE);
        
        jDialog = jOption.createDialog(parentComponent,titulo);    
    
        jDialog.setVisible(true); 
   }
    
   
    public void cerrarDialog(){                
        jDialog.dispose();
        jDialog=null;
        jOption=null;             
   }   
}
