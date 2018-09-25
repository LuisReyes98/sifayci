package Ve.com.biller.vistas.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Ventas;
import Ve.com.biller.estructuras.reyes.Venta;
import Ve.com.biller.estructuras.reyes.VentaDeleted;
import Ve.com.biller.estructuras.reyes.VentaPlatillo;
import Ve.com.biller.eventos.reyes.EventoRVBotonBuscarVentasRangoFecha;
import Ve.com.biller.eventos.reyes.EventoRVBotonHoy;
import Ve.com.biller.eventos.reyes.EventoRVBotonMostrarTodo;
import Ve.com.biller.eventos.reyes.EventoRVDateRangeChecker;
import Ve.com.biller.eventos.reyes.EventoRVShowDeletedBills;
import Ve.com.biller.helpers.reyes.Botonera;
import Ve.com.biller.helpers.reyes.DateManager;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.DatosRegistroVentas;
import Ve.com.biller.modelos.reyes.Tipografia;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Luis Reyes
 */
public class PanelRV extends JPanel {
   
    
    private JXDatePicker datePickerFrom;   
    private JXDatePicker datePickerTo;           
    private JCheckBox checkBoxSummary;
    private JLabel labelFrom;
    private JLabel labelTo;
    private Botonera botonera;        
    private final ImageIcon[] iconsButton= new ImageIcon[DatosBotones.ICONOS_BOTONES_REGISTRO_VENTA.length];
    private JPanel topPanel;
    private JPanel centerPanel;
    private JScrollPane scrollCenter;
    private Date dateToday;
    private byte centinela=0;
    private double moneyShown=0;//cantidad de dinero mostrado en ese momento en la venta
    private JTextArea textAreaMoney;
    
    public PanelRV(){
        super(new BorderLayout());
        
        
        
        createGUI();
    }

    private void createGUI() {
        
        textAreaMoney= new JTextArea();
        textAreaMoney.setFont(Tipografia.ARIAL_BOLD_25);
        textAreaMoney.setForeground(Color.BLACK);
        textAreaMoney.setBackground(Color.WHITE);
        textAreaMoney.setEditable(false);
        textAreaMoney.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        this.createTopPanel();
        this.createBottomPanel();
        
       

        
        this.add(topPanel,BorderLayout.NORTH);
        this.add(scrollCenter,BorderLayout.CENTER);
        this.add(textAreaMoney,BorderLayout.SOUTH);
        
    }
    
    private void createTopPanel(){               
        dateToday= DateManager.getTodayDate();
        
        topPanel=new JPanel(new FlowLayout(0));
        topPanel.setBackground(Color.WHITE);
        
        labelFrom= new JLabel(DatosRegistroVentas.LABEL_VISUAL[0]);//etiqueta "Desde" para el selector de fecha
        labelFrom.setForeground(Color.BLACK);
        labelFrom.setFont(Tipografia.ARIAL_14);
        
        labelTo=new JLabel(DatosRegistroVentas.LABEL_VISUAL[1]);//etiqueta "Hasta" para el selector de fecha
        labelTo.setForeground(Color.BLACK);
        labelTo.setFont(Tipografia.ARIAL_14);
        
        
        checkBoxSummary= new JCheckBox(DatosRegistroVentas.LABEL_VISUAL[2]);//Etiqueta Resumen del checkbox
        checkBoxSummary.setFont(Tipografia.ARIAL_14);
        checkBoxSummary.setOpaque(false);
        
        datePickerFrom= new JXDatePicker(dateToday);//date picker desde       
        datePickerFrom.setFont(Tipografia.ARIAL_14);
        datePickerFrom.setForeground(Color.BLACK);
        
        
        //datePickerFrom.addActionListener(this);cada vez que se selecciona una nueva venta este evento se ejecuta
        
        datePickerTo= new JXDatePicker(dateToday);//date picker hasta
        datePickerTo.setFont(Tipografia.ARIAL_14);
        datePickerTo.setForeground(Color.BLACK);
        
        //eventos de validación de los date pickers
        datePickerFrom.addActionListener(new EventoRVDateRangeChecker(datePickerFrom, datePickerTo));
        datePickerTo.addActionListener(new EventoRVDateRangeChecker(datePickerFrom, datePickerTo));
        
        botonera = new Botonera(DatosBotones.NOMBRES_BOTONES_REGISTRO_VENTA,new FlowLayout());
        botonera.setOpaque(false);
        botonera.setButtonsFont(Tipografia.ARIAL_14);
        botonera.setButtonsForeground(Color.BLACK);
        botonera.setButtonsBackground(new Color(244, 246, 247));                
        for (int i = 0; i < iconsButton.length; i++) {
            try {
                iconsButton[i]= new ImageIcon(ImageIO.read(PanelRV.class.getResource(DatosBotones.ICONOS_BOTONES_REGISTRO_VENTA[i])));
                botonera.setButtonIcon(iconsButton[i], i);//se le coloca el icono adecuado a cada boton
            } catch (IOException ex) {
                //Logger.getLogger(PanelRV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        botonera.setButtonsIconsEast();//los iconos se colocan a la derecha de la palabra
        
        //eventos de los botones
        botonera.setButtonEvent(new EventoRVBotonBuscarVentasRangoFecha(this, datePickerFrom, datePickerTo),0);
        botonera.setButtonEvent(new EventoRVBotonHoy(this), 1);//evento del boton hoy
        botonera.setButtonEvent(new EventoRVBotonMostrarTodo(this),2);//evento del boton mostrar todo
        botonera.setButtonEvent(new EventoRVShowDeletedBills(this),3);//evento para mostrar las facturas eliminadas

        
        //todo se añade al panel
        topPanel.add(checkBoxSummary);        
        topPanel.add(labelFrom);
        topPanel.add(datePickerFrom);        
        topPanel.add(labelTo);       
        topPanel.add(datePickerTo);
        topPanel.add(botonera);
        
        topPanel.setPreferredSize(new Dimension(topPanel.getPreferredSize().width,90));
     
    }

    
    private void createBottomPanel() {
        java.sql.Date[] todayDates= DateManager.getBeginEndToday();       
        
        this.updateBottomPanel(CRUD_Ventas.selectSalesRedordDateRange(todayDates[0],todayDates[1]));
        
        scrollCenter= new JScrollPane(centerPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollCenter.getVerticalScrollBar().setUnitIncrement(10);//aumenta la cantidad de unidades con las que avanza la barra   


    }
    
    /**
     * Metodo que recibe el array list de las ventas que se van a mostrar y 
     * las muestra 
     * @param arrayListVenta 
     */
    public void updateBottomPanel(ArrayList<Venta> arrayListVenta){                    
        Iterator<Venta> iterarVentas= arrayListVenta.iterator();
        Venta ventaAux;
        HideShowPanelRV panelesVentasAux;
        int i=0;                
                                        // "cell column row width height"                        
        centerPanel= new JPanel(new MigLayout("insets 0 0 0 0"));        
        moneyShown=0;
        while(iterarVentas.hasNext()){
            ventaAux= iterarVentas.next();  
            moneyShown+=ventaAux.getCosto();
            panelesVentasAux= new HideShowPanelRV(ventaAux,this);
            centerPanel.add(panelesVentasAux,"cell 0 "+i+" 1 1");//cell 0 i 1 1
            i++;
        }        
        textAreaMoney.setText(String.format(" Total Mostrado %1.2fBs",moneyShown));        
        revalidateCenterPanel();                     
    }
    
    public void updateBottomPanelDeletedBill(ArrayList<VentaDeleted> array){
        Iterator<VentaDeleted> iterarVentas= array.iterator();
        VentaDeleted ventaDelAux;
        HideShowRVDeleted panelesVentasAux;
        int i=0;                
                                        // "cell column row width height"                        
        centerPanel= new JPanel(new MigLayout("insets 0 0 0 0"));        
        moneyShown=0;
        while(iterarVentas.hasNext()){
            ventaDelAux= iterarVentas.next();  
            moneyShown+=ventaDelAux.getCosto();
            panelesVentasAux= new HideShowRVDeleted(ventaDelAux,this);
            centerPanel.add(panelesVentasAux,"cell 0 "+i+" 1 1");//cell 0 i 1 1
            i++;
        }        
        textAreaMoney.setText(String.format(" Total Mostrado %1.2fBs",moneyShown));        
        revalidateCenterPanel(); 
    }
    
    
    
    /**
     * actualizador del panel de registro de ventas cuando
     * es modo resumen
     * 
     * @param arrayList 
     */
    public void updateBottomPanelSummary(ArrayList<VentaPlatillo> arrayList){
        Iterator<VentaPlatillo> iterarVentas= arrayList.iterator();
        VentaPlatillo ventaAux;
        int i=0;
        moneyShown=0;
        centerPanel= new JPanel(new MigLayout("insets 0 0 0 0"));        

        while(iterarVentas.hasNext()){
            ventaAux= iterarVentas.next(); 
            moneyShown+=ventaAux.precioAcumulado;
            centerPanel.add(createSummaryPanel(ventaAux, i),"cell 0 "+i+" 1 1");//cell 0 i 1 1
            i++;
        }        
        textAreaMoney.setText(String.format(" Total Mostrado %1.2fBs (Sin IVA/Descuento)",moneyShown));        
        revalidateCenterPanel();       
    }
    private JPanel createSummaryPanel(VentaPlatillo venta,int i){
        JLabel labelCategoria;
        JLabel labelNombre;
        JLabel labelCantidad;
        JLabel labelPrecioAcu;
        
        JPanel panelSum= new JPanel(new MigLayout("insets 0 0 0 0"));
        if (i%2==0) {
            panelSum.setBackground(Color.WHITE);
        }
        labelCategoria= new JLabel(venta.categoria);
        labelCategoria.setFont(Tipografia.ARIAL_BOLD_16);
        labelCategoria.setForeground(Color.BLACK);

        
        labelNombre= new JLabel(venta.nombre);
        labelNombre.setFont(Tipografia.ARIAL_BOLD_16);
        labelNombre.setForeground(Color.BLACK);
        
        labelCantidad= new JLabel(Integer.toString(venta.cantidad));
        labelCantidad.setFont(Tipografia.ARIAL_BOLD_16);
        labelCantidad.setForeground(Color.BLACK);
        
        labelPrecioAcu= new JLabel(" Total: "+Double.toString(venta.precioAcumulado)+" Bs.");
        labelPrecioAcu.setFont(Tipografia.ARIAL_BOLD_16);
        labelPrecioAcu.setForeground(Color.BLACK);
        
        panelSum.add(labelCategoria,    "cell 0 0 1 1,width 200px");
        panelSum.add(labelNombre,       "cell 1 0 1 1,width 200px");
        panelSum.add(labelCantidad,     "cell 2 0 1 1,width 80px");
        panelSum.add(labelPrecioAcu,    "cell 3 0 1 1,width 200px");

        return panelSum;
    }
    
    private void revalidateCenterPanel(){
        this.revalidate();
        this.repaint();
        centerPanel.revalidate();
        centerPanel.repaint();
        
        if (centinela!=0) { //se ejecuta luego de que el panel ya se cargo por primera vez
            scrollCenter.getViewport().removeAll();             
            scrollCenter.setViewportView(centerPanel);
            
        }else{
            centinela=1;
        }   
    }
    
    public JCheckBox getCheckBoxSummary() {
        return checkBoxSummary;
    }

    public JPanel getCenterPanel() {
        return centerPanel;
    }
    
    
    
   
}
