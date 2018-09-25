package Ve.com.biller.vistas.reyes;

import Ve.com.biller.helpers.reyes.Botonera;
import Ve.com.biller.helpers.reyes.ImageManager;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.ModeloIconosPlatillo;
import Ve.com.biller.conexion.reyes.sql.CRUD_Menu;
import Ve.com.biller.eventos.reyes.EventoEMBotonComponente;
import Ve.com.biller.eventos.reyes.EventoEMCerrarSinGuardarMenu;
import Ve.com.biller.eventos.reyes.EventoEMGuardarMenuEditado;
import Ve.com.biller.helpers.reyes.LabelDeTextoCambiante;
import Ve.com.biller.modelos.reyes.Tipografia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Luis Reyes
 */
public class PanelEditMenuIW extends JPanel{
  
    private EditMenuIW contenedor;
    private JPanel panelBorderContainer;
    private JScrollPane scrollGeneral;
    private Botonera botoneraCategoria;
    private ImageIcon iconoCategoria;
    private Botonera botoneraDeLasCategorias;
    private ImageIcon[] iconosDeLasCategorias;
    private JScrollPane scrollBotonesPanelCategorias;
    private ImageIcon[] iconosGuardarSalir;    
    private Botonera botoneraGuardarSalir;
    private PanelEditarCategoriasEM panelEditarCategorias;
    private PanelEditarPlatillosEM[] panelesEditarPlatillo;
    private JPanel panelTituloScroll;
    private JLabel labelTitulo;
    private Color colorBarraIzquierda;
  
    public PanelEditMenuIW(EditMenuIW contenedor) {
        super(new BorderLayout());
        this.contenedor=contenedor;
        
        crearGUI();
    }
    private void crearGUI(){        
        int cantCateg;
        final int anchoBarraOeste=150;//ancho de la barra de navegacion        
        colorBarraIzquierda= new Color(102, 5, 27);//color general de la interfaz y barra de navegacion       
        cantCateg= CRUD_Menu.categorias.length;
        
        iconosDeLasCategorias= new ImageIcon[cantCateg];
        
        panelesEditarPlatillo= new PanelEditarPlatillosEM[cantCateg];        
        panelEditarCategorias= new PanelEditarCategoriasEM(this);//panel de editar categorias que aparece por defecto    
        
        //metodo de la barra de scroll
        scrollGeneral= new JScrollPane(panelEditarCategorias,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//los paneles se continen en un scroll para removerlos mejor               
        scrollGeneral.getVerticalScrollBar().setUnitIncrement(10);
        
        panelTituloScroll= new JPanel(new BorderLayout());//jPanel que tiene un titulo sobre el y un scroll con las cosas a editar

        labelTitulo= new JLabel(String.format("%40s",DatosBotones.NOMBRES_BOTONES_EDITAR_MENU_CATEGORIA[0]));
        labelTitulo.setFont(Tipografia.ARIAL_BOLD_24);
        labelTitulo.setForeground(Color.WHITE);
        labelTitulo.setBackground(colorBarraIzquierda);
        labelTitulo.setOpaque(true);
       
        panelTituloScroll.add(scrollGeneral,BorderLayout.CENTER);//scroll de los objetos a editar
        panelTituloScroll.add(labelTitulo,BorderLayout.NORTH);//titulo de la categoria
        
        panelBorderContainer= new JPanel(new MigLayout("insets 0 0 0 0","","[] 0 []"));//panel que contiene al boton "categoria" y a los botones de todas las categorias                                            
        //botonera del boton de Categorias
        try{
            iconoCategoria= new ImageIcon(ImageIO.read(PanelEditMenuIW.class.
            getResource(DatosBotones.ICONOS_BOTONES_EDITAR_MENU[0])));
            iconoCategoria= ImageManager.iconResize(iconoCategoria, 64, 64);
        }catch(IOException ex){}             
        botoneraCategoria= new Botonera(DatosBotones.NOMBRES_BOTONES_EDITAR_MENU_CATEGORIA, new GridLayout(1,1));
        botoneraCategoria.setButtonIcon(iconoCategoria, 0);
        botoneraCategoria.setButtonsIconsNorth();       
        botoneraCategoria.setOpaque(true);
        botoneraCategoria.getButton(0).setForeground(Color.WHITE);   
        botoneraCategoria.setButtonsBackground(colorBarraIzquierda);   
        //asignando evento a la botonera de "categoria"
        botoneraCategoria.setButtonEvent(new EventoEMBotonComponente(this, String.format("%40s",
        DatosBotones.NOMBRES_BOTONES_EDITAR_MENU_CATEGORIA[0]), panelEditarCategorias,null, false, 0), 0);        
        //botonera de todas las categorias que tiene el programa
        
        botoneraDeLasCategorias= new Botonera(CRUD_Menu.categorias,new GridLayout(0,1));
        
        for (int i = 0; i < iconosDeLasCategorias.length; i++) {
            //panelesEditarPlatillo[i]= new PanelEditarPlatillosEM(i);
            
            ModeloIconosPlatillo.setIconosCategorias(CRUD_Menu.categorias[i], iconosDeLasCategorias, i);
            iconosDeLasCategorias[i]= ImageManager.iconResize(iconosDeLasCategorias[i], 64, 64);
            botoneraDeLasCategorias.setButtonIcon(iconosDeLasCategorias[i],i);
            botoneraDeLasCategorias.setButtonEvent(new EventoEMBotonComponente(this,String.format("%40s",
            CRUD_Menu.categorias[i]),null,panelesEditarPlatillo,true,i), i);
            
            
        }
        
        botoneraDeLasCategorias.setButtonsIconsNorth();
        botoneraDeLasCategorias.setButtonsBackground(colorBarraIzquierda);
        botoneraDeLasCategorias.setButtonsForeground(Color.WHITE);
        scrollBotonesPanelCategorias= new JScrollPane(botoneraDeLasCategorias);
        scrollBotonesPanelCategorias.getVerticalScrollBar().setUnitIncrement(10);//sensibilidad de la barra scroll    
        botoneraCategoria.setPreferredSize(new Dimension(anchoBarraOeste,botoneraCategoria.getHeight()));          
        scrollBotonesPanelCategorias.setPreferredSize(new Dimension(anchoBarraOeste,900));
        
        //botonera guardar Salir
        
        iconosGuardarSalir= new ImageIcon[2];
        try{
        iconosGuardarSalir[0]= new ImageIcon(ImageIO.read(PanelEditMenuIW.class.getResource(
                DatosBotones.ICONOS_BOTONES_EDITAR_MENU[1])));
        iconosGuardarSalir[1]= new ImageIcon(ImageIO.read(PanelEditMenuIW.class.getResource(
                DatosBotones.ICONOS_BOTONES_MAIN[5])));
        
        }catch(IOException ex){}
        botoneraGuardarSalir= new Botonera(DatosBotones.NOMBRES_BOTONES_EDITAR_MENU,null,iconosGuardarSalir
                ,new FlowLayout(FlowLayout.CENTER));
        botoneraGuardarSalir.setOpaque(true);
        botoneraGuardarSalir.setBackground(colorBarraIzquierda);//color de la barra inferior
        //   102, 5, 27vinotinto   40, 55, 71 cyan oscuro
        botoneraGuardarSalir.setButtonsBackground(Color.WHITE);
        botoneraGuardarSalir.setButtonsForeground(Color.BLACK);
        
        //evento de guardar
        botoneraGuardarSalir.setButtonEvent(new EventoEMGuardarMenuEditado(this), 0);
        
        botoneraGuardarSalir.setButtonEvent(new EventoEMCerrarSinGuardarMenu(contenedor, this), 1);
        //adding everything to it's container
        panelBorderContainer.add(botoneraCategoria,"cell 0 0 1 1");//cell columna fila ancho alto
        panelBorderContainer.add(scrollBotonesPanelCategorias,"cell 0 1 1 1");           
    
        this.add(panelBorderContainer,BorderLayout.WEST);
        this.add(panelTituloScroll,BorderLayout.CENTER);
        this.add(botoneraGuardarSalir,BorderLayout.SOUTH);
    }    
   
    /**
     * Metodo para cambiar el contenido del scrollpane
     * @param panel 
     */
    public void cambiarPanel(JPanel panel){        
        scrollGeneral.getViewport().removeAll();        
        scrollGeneral.setViewportView(panel);              
    }
    
    public void cambiarTitulo(String texto){
        labelTitulo.setText(texto); 
        
    }
    
    public PanelEditarCategoriasEM getPanelEditarCategorias(){
        return panelEditarCategorias;
    }
    public PanelEditarPlatillosEM[] getPanelesEditarPlatillo(){
        return panelesEditarPlatillo;
    }
    /**
     * 
     * @return 
     * //metodo que se llama para saber si ha habido cambios 
     * antes de cerrar para que el dialogo de no ha guardado solo aparezca cuando el usuario no ha guardado
     */
    public boolean hasChangesBeenMade(){
        LabelDeTextoCambiante[] labelNombreCategoria= panelEditarCategorias.getLabelsCategorias();
        //labels que permiten el guardado de informacion 
        LabelDeTextoCambiante[] labelNombrePlatillo; //label para guardar nombres de platillos
        LabelDeTextoCambiante[][] labelPreciosPlatillo;
        for (int i = 0; i < labelNombreCategoria.length; i++) {
            if (panelesEditarPlatillo[i]!=null) {
                labelNombrePlatillo=panelesEditarPlatillo[i].getLabelPlatillosNombres();
                labelPreciosPlatillo= panelesEditarPlatillo[i].getLabelPlatillosPrecios();
                 for (int j = 0; j < labelNombrePlatillo.length; j++) {
                    if (labelNombrePlatillo[j].hasChangedText()) {
                        return true;
                    }
                    for (int k = 0; k < 4; k++) {                        
                        if (labelPreciosPlatillo[j][k].hasChangedText()) {
                            return true;
                        }
                    }
                 }
            }
            if (labelNombreCategoria[i].hasChangedText()) {
                return true;
            }
        }        
        return false;
    }
    public void recargarVentana(){
        contenedor.recargar();
    }
    
}
