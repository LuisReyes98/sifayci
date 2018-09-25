package Ve.com.biller.vistas.reyes;

import Ve.com.biller.estructuras.reyes.Venta;
import Ve.com.biller.eventos.reyes.EventoRVDeleteBill;
import Ve.com.biller.eventos.reyes.EventoRVPrintBill;
import Ve.com.biller.eventos.reyes.EventoRVSavePng;
import Ve.com.biller.helpers.reyes.ButtonMoreLess;
import Ve.com.biller.helpers.reyes.Botonera;
import Ve.com.biller.helpers.reyes.DateManager;
import Ve.com.biller.helpers.reyes.JPanelCloneable;
import Ve.com.biller.helpers.reyes.PrintableJPanel;
import Ve.com.biller.modelos.reyes.DatosBotones;
import Ve.com.biller.modelos.reyes.DatosRegistroVentas;
import Ve.com.biller.modelos.reyes.Tipografia;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.AccessControlException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Luis Reyes
 * 
 */

public class HideShowPanelRV extends JPanel implements ActionListener{//nota no creo que valga la pena hacer una subclase que herede de esta clase      
    protected final int idVenta;
    protected final Venta venta;
    
    protected JPanel panelVisibleSmall;    
    protected JPanel panelHiddenBig;
    //componentes del panel visible que no esta oculto 
    protected JLabel labelFecha;
    protected JLabel labelSummary;
    protected JLabel labelCosto;
    protected ButtonMoreLess buttonVerOcultar;
    protected JPanel panelVisibleAux1;
    protected JPanel panelVisibleAux2;
    protected JPanel panelAuxPreciosFinales;
    //componentes del panel que esta oculto
    protected JPanel panelAuxConceptos;
    protected JPanel panelAuxCostos;        
    protected JPanelCloneable panelPrintable;//panel a imprimir    
    protected JPanel panelFechaNo;    
    protected JPanel panelCustomer;
    protected JPanel panelDescriptions;    
    protected JTextArea textAreaConceptos;      
    protected JTextPane textPaneCostos;//contiene todos los costos alineados a la derecha
    protected SimpleAttributeSet attributesRightNormal;            
    protected JLabel labelHora;        
    protected JLabel labelFraseConcepto;
    protected JLabel labelFraseCosto;    
    protected JLabel labelNumBill;         
    protected JLabel labelNombreCliente;   
    protected JLabel labelRif;            
    protected JLabel labelDomicilio;
    protected JLabel labelTelefono;  
    protected JLabel labelFechaHoraHidden;      
    protected JTextPane textPaneFrasesPreciosTotales;//contiene los labels de precio iva y precio total  
    protected JTextPane textPanePreciosTotales;    
    protected StyledDocument docTextPanePrecios;        
    protected StyledDocument docTextPaneFrasesPrecios;    
    protected SimpleAttributeSet attributesLeftNormal;
    protected SimpleAttributeSet attributesRightBold;
    protected SimpleAttributeSet attributesLeftTotalNormal;
    protected SimpleAttributeSet attributesRightTotalBold;    
    protected JTextPane textPaneMetodoPago;
    protected StyledDocument docTextMetodoPago;                  
    protected JTextArea textAreaDescripcionPago;
    protected JTextArea textAreaDescripcionVenta;    
    protected Botonera botonera;
    
    protected final Border borderNegro= BorderFactory.createLineBorder(Color.BLACK, 1);    
    protected final ImageIcon[] iconosBotonera= new ImageIcon[DatosBotones.ICONOS_BOTONES_FACTURA.length];        
    protected final Dimension dimensionDescriptions= new Dimension(300,20);
    protected final Color colorVisible=new Color(102, 5, 27);//color de la barra del panel visible
    protected String fecha;
    protected String hora;
    protected final PanelRV panelRV;
    
    public HideShowPanelRV(Venta venta,PanelRV panelRV) {
        super(new BorderLayout());        
        this.venta=venta;
        idVenta=venta.getIdVenta();
        this.panelRV=panelRV;
        createGUI();
    }
    protected void createGUI(){                
        this.createSmallVisiblePanel();
        this.createBigHiddenPanel();
                        
        this.add(panelVisibleSmall,BorderLayout.NORTH);//panel barra pequeño visible
        this.add(panelHiddenBig,BorderLayout.CENTER);//panel grande que contiene los datos de la factura y aún más
    }
    protected void createSmallVisiblePanel(){   
        fecha=DateManager.fromLongDateToString(venta.getFecha());
        hora=DateManager.fromLongHourToString(venta.getHoraVenta());
        String summaryPlatillos="";
        char[] charVectorAux;                
        
        // "cell column row width height"   
        panelVisibleSmall= new JPanel(new BorderLayout());        
        panelVisibleSmall.setOpaque(true);
        panelVisibleSmall.setBackground(colorVisible);
        panelVisibleSmall.setPreferredSize(new Dimension(550,30));//tamaño del panel pequeño 
        
        panelVisibleAux1= new JPanel(new FlowLayout(FlowLayout.LEFT,10,1));
        panelVisibleAux1.setOpaque(false);
        panelVisibleAux1.setBorder(BorderFactory.createEmptyBorder(6,0,6,0));//n w s e borde para que no este pegado de los limites del panel
        
        panelVisibleAux2= new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelVisibleAux2.setOpaque(false);
        
        panelFechaNo= new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFechaNo.setOpaque(false);
                
        labelFecha= new JLabel(fecha);//label de la fecha
        labelFecha.setForeground(Color.WHITE);
        labelFecha.setFont(Tipografia.ARIAL_14);
        
        labelHora= new JLabel(hora);//label del la hora
        labelHora.setFont(Tipografia.ARIAL_14);
        labelHora.setForeground(Color.WHITE);
        
       
        
        
        charVectorAux=venta.getPlatillosContenidos().toCharArray();
        for (int i = 0; i < 13; i++) {//el resumen del pedido son los primeros 20 caracteres/letras de la orden 
            summaryPlatillos+=charVectorAux[i];
        }
        summaryPlatillos+=DatosRegistroVentas.SUMMARY_END;
        
        labelSummary=new JLabel(summaryPlatillos);//resumen de lo que es el pedido
        labelSummary.setForeground(Color.WHITE);
        labelSummary.setFont(Tipografia.ARIAL_14);
        
        labelCosto= new JLabel(String.format("%2.2f",venta.getCosto()));//costo del pedido
        labelCosto.setForeground(Color.WHITE);
        labelCosto.setFont(Tipografia.ARIAL_BOLD_14);
        
        
        buttonVerOcultar= new ButtonMoreLess(Color.WHITE, Color.GRAY, DatosRegistroVentas.TOOL_TIP_BUTTON_SHOW[0],DatosRegistroVentas.TOOL_TIP_BUTTON_SHOW[1]);
        buttonVerOcultar.setOpaque(false);//boton para ver mas
        buttonVerOcultar.addActionListener(this);
        
        panelVisibleAux1.add(labelFecha);
        panelVisibleAux1.add(labelHora);
        panelVisibleAux1.add(labelSummary);
        
        panelVisibleAux2.add(labelCosto);
        panelVisibleAux2.add(buttonVerOcultar);   
        
        
        panelVisibleSmall.add(panelVisibleAux1,BorderLayout.WEST);
        panelVisibleSmall.add(panelVisibleAux2,BorderLayout.EAST);
        
        panelVisibleSmall.setVisible(true);
    }
    
    protected void createBigHiddenPanel(){                  
        String auxConceptos="";
        String auxCostos="";        
        char[] auxCharacteresVenta= venta.getPlatillosContenidos().toCharArray();        
        double totalIvaDesc=venta.getIvaDesc();
        double totalPrecioMasIvaDesc=venta.getCosto();
        double totalPrecio= totalPrecioMasIvaDesc-totalIvaDesc;        
        int porcentajeIvaDesc= (int) (totalIvaDesc/totalPrecio*100);//el valor del ivaDesc en porcentaje
        int centinelaPrecio=0;
        int centinelaComas=0;
        
        panelHiddenBig= new JPanel(new BorderLayout()); 
        
        panelHiddenBig.setVisible(false);
        panelHiddenBig.setBackground(Color.WHITE);                
        
        panelPrintable= new JPanelCloneable(new MigLayout("insets 15 15 15 15"));//panel imprimible        
        panelPrintable.setBackground(Color.WHITE);
        panelDescriptions= new JPanel(new MigLayout("insets 15 15 15 15"));
        panelDescriptions.setOpaque(false);
        
        
        panelAuxConceptos= new JPanel(new BorderLayout());
        panelAuxConceptos.setOpaque(false);
        panelAuxConceptos.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
        
        panelAuxCostos= new JPanel(new BorderLayout());
        panelAuxCostos.setOpaque(false);       
        
        panelAuxPreciosFinales= new JPanel (new MigLayout());
        panelAuxPreciosFinales.setOpaque(false);
        panelAuxPreciosFinales.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, Color.BLACK));
        
        
        labelNumBill= new JLabel(DatosRegistroVentas.LABELS_REGISTRO_VENTA[0]+venta.getIdVenta());//label del numero de factura
        labelNumBill.setFont(Tipografia.ARIAL_12);
        labelNumBill.setForeground(Color.BLACK);
        labelNumBill.setBorder(BorderFactory.createEmptyBorder(0, 95, 0, 0));
        
        
        //se inicializan las label del otro panel aquí ya que de este modo se reutiliza la varible de la hora y fecha
        labelFechaHoraHidden=new JLabel(fecha+" "+hora);//label de fecha y hora de la factura               
        labelFechaHoraHidden.setForeground(Color.BLACK);        
        labelFechaHoraHidden.setFont(Tipografia.ARIAL_BOLD_12);        
        
        labelFechaHoraHidden.setBackground(Color.WHITE);
        labelFechaHoraHidden.setOpaque(true);
        
        panelCustomer= new JPanel(new MigLayout("insets 0 0 0 0"));
        panelCustomer.setOpaque(false);
        panelCustomer.setBorder(new TitledBorder(borderNegro,DatosRegistroVentas.LABELS_REGISTRO_VENTA[1],TitledBorder.DEFAULT_JUSTIFICATION , TitledBorder.DEFAULT_POSITION, Tipografia.ARIAL_BOLD_12, Color.BLACK));//borde con frase cliente que encierra los datos del cliente "Cliente: ",
                
        labelNombreCliente= new JLabel(DatosRegistroVentas.LABELS_CLIENTE[0]+venta.getNombreCliente());// label con los datos del cliente nombre y numero de cedula encerrado en un borde con la palabra cliente      
        labelNombreCliente.setFont(Tipografia.ARIAL_12);
        labelNombreCliente.setForeground(Color.BLACK);
        
        labelRif= new JLabel(DatosRegistroVentas.LABELS_CLIENTE[1]+venta.getTipoDeRif()+"-"+venta.getRifCliente());//label del rif del cliente
        labelRif.setFont(Tipografia.ARIAL_12);
        labelRif.setForeground(Color.BLACK);
        
        labelDomicilio=new JLabel(DatosRegistroVentas.LABELS_CLIENTE[2]+venta.getDomicilio());//domicilio del cliente
        labelDomicilio.setFont(Tipografia.ARIAL_12);
        labelDomicilio.setForeground(Color.BLACK);
        
        labelTelefono=new JLabel(DatosRegistroVentas.LABELS_CLIENTE[3]+venta.getTelefono());//numero de telefono del cliente
        labelTelefono.setFont(Tipografia.ARIAL_12);
        labelTelefono.setForeground(Color.BLACK);
        
        
        labelFraseConcepto=new JLabel(DatosRegistroVentas.LABELS_REGISTRO_VENTA[2],JLabel.CENTER);//label del concepto de la venta
        labelFraseConcepto.setFont(Tipografia.ARIAL_BOLD_12);
        labelFraseConcepto.setForeground(Color.BLACK);
        
        labelFraseCosto= new JLabel(DatosRegistroVentas.LABELS_REGISTRO_VENTA[3],JLabel.CENTER);//label con la frase costo
        labelFraseCosto.setFont(Tipografia.ARIAL_BOLD_12);
        labelFraseCosto.setForeground(Color.BLACK);
                                         
        for (int i = 0; i < auxCharacteresVenta.length; i++) {//para cambiar los ; por espacios
            
            if (auxCharacteresVenta[i]==',') {
                centinelaComas++;//centinela cada vez que encuentra una coma ,
            }
            if (auxCharacteresVenta[i]==';') {
                centinelaComas=0;
                if (!(centinelaPrecio==0||(centinelaPrecio%2==0))) {
                    auxCostos+="\n";  
                }                    
                centinelaPrecio++;//centinela para saber si el proximo valor a leer es el precio
            }else{                
                if (centinelaPrecio==0||(centinelaPrecio%2==0)) {//antes de encontrarse con el ; separa el nombre de su precio 
                    if (centinelaComas<3) {//para que en caso de ser hamburguesa no muestre los acompañantes de la orden 
                        auxConceptos+=auxCharacteresVenta[i];
                    }                    
                }else{                    
                    auxCostos+=auxCharacteresVenta[i];                
                }                                
            }                        
        }
        textAreaConceptos= new JTextArea(auxConceptos);          
        textAreaConceptos.setFont(Tipografia.ARIAL_12);
        textAreaConceptos.setForeground(Color.BLACK);
        textAreaConceptos.setOpaque(false);                     
        textAreaConceptos.setEditable(false);
        
        
        attributesLeftNormal= new SimpleAttributeSet();//atributos arial normal 14 black 
        StyleConstants.setFontFamily(attributesLeftNormal, "Arial");//familia de la letra 
        StyleConstants.setFontSize(attributesLeftNormal, 12);//tamaño de letra
        StyleConstants.setAlignment(attributesLeftNormal, StyleConstants.ALIGN_LEFT);//alineamiento a la derecha
        
        attributesRightBold=(SimpleAttributeSet)attributesLeftNormal.clone();
        StyleConstants.setAlignment(attributesRightBold, StyleConstants.ALIGN_RIGHT);//alineamiento a la derecha      
        StyleConstants.setBold(attributesRightBold, true);//bold
        
        textPaneCostos= new JTextPane();
        textPaneCostos.setForeground(Color.BLACK);
        
        attributesRightNormal= (SimpleAttributeSet)attributesLeftNormal.clone();
        StyleConstants.setAlignment(attributesRightNormal, StyleConstants.ALIGN_RIGHT);//alineamiento hacia la derecha
         
        
        attributesLeftTotalNormal=(SimpleAttributeSet)attributesLeftNormal.clone();
        StyleConstants.setFontSize(attributesLeftTotalNormal,14);//atributos del  label del precio final "total"
        
        attributesRightTotalBold=(SimpleAttributeSet)attributesRightBold.clone();
        StyleConstants.setFontSize(attributesRightTotalBold,14);//atributos del precio final
        
        
        textPaneCostos.setParagraphAttributes(attributesRightNormal, true);//se agregan los atributos al text pane
        textPaneCostos.setText(auxCostos);
        textPaneCostos.setEditable(false);
        
        
        textPaneFrasesPreciosTotales= new JTextPane();
        textPaneFrasesPreciosTotales.setEditable(false);
        textPaneFrasesPreciosTotales.setForeground(Color.BLACK);
        docTextPaneFrasesPrecios= (StyledDocument) textPaneFrasesPreciosTotales.getDocument();
        
        textPanePreciosTotales= new JTextPane();        
        textPanePreciosTotales.setEditable(false);
        textPanePreciosTotales.setForeground(Color.BLACK);
        textPanePreciosTotales.setParagraphAttributes(attributesRightNormal, true);
        
        docTextPanePrecios= (StyledDocument) textPanePreciosTotales.getDocument();                       
        //inicio de la seccion de los pagos totales
        
        StyleConstants.setFontSize(attributesRightBold,12);//estilo del tipo de metodo de pago
        StyleConstants.setFontSize(attributesLeftNormal,12);//estilo de los labels de los precios totales
        
        textPaneMetodoPago= new JTextPane();
        textPaneMetodoPago.setEditable(false);
        textPaneMetodoPago.setForeground(Color.BLACK);
        
        docTextMetodoPago= (StyledDocument) textPaneMetodoPago.getDocument();
        
        try {
            if (porcentajeIvaDesc>=0) {//si es iva
                docTextPaneFrasesPrecios.insertString(docTextPaneFrasesPrecios.getLength(), DatosRegistroVentas.LABELS_REGISTRO_VENTA[4]+porcentajeIvaDesc+DatosRegistroVentas.LABELS_REGISTRO_VENTA[5], attributesLeftNormal);//se añade la frase iva

                docTextPanePrecios.insertString(docTextPanePrecios.getLength(),String.format("%2.2f",totalIvaDesc) , attributesRightNormal);//valor del iva
                              
            }else if (porcentajeIvaDesc<0){// si es descuento
                porcentajeIvaDesc*=-1;
                docTextPaneFrasesPrecios.insertString(docTextPaneFrasesPrecios.getLength(),DatosRegistroVentas.LABELS_REGISTRO_VENTA[6]+porcentajeIvaDesc+DatosRegistroVentas.LABELS_REGISTRO_VENTA[5], attributesLeftNormal);//se añade la frase descuento

                docTextPanePrecios.insertString(docTextPanePrecios.getLength(),String.format("%2.2f",totalIvaDesc) , attributesRightNormal);//valor del descuento

            }
            
            docTextPaneFrasesPrecios.insertString(docTextPaneFrasesPrecios.getLength(),DatosRegistroVentas.LABELS_REGISTRO_VENTA[7], attributesLeftNormal);//frase platillos         
            docTextPanePrecios.insertString(docTextPanePrecios.getLength(),String.format("\n%2.2f",totalPrecio) , attributesRightNormal);//precio sin iva

            docTextPaneFrasesPrecios.insertString(docTextPaneFrasesPrecios.getLength(), DatosRegistroVentas.LABELS_REGISTRO_VENTA[8], attributesLeftTotalNormal);//frase total        
            docTextPanePrecios.insertString(docTextPanePrecios.getLength(),String.format("\n%2.2f",totalPrecioMasIvaDesc), attributesRightTotalBold);//costo total de la venta
            
            docTextMetodoPago.insertString(docTextMetodoPago.getLength(), DatosRegistroVentas.LABELS_REGISTRO_VENTA[9], attributesLeftNormal);//estilo de la frase metodo de pago           

            docTextMetodoPago.insertString(docTextMetodoPago.getLength(), venta.getMetodoPago(), attributesRightBold);//estilo del metodo de pago 
        }catch(BadLocationException ex){}
              
        
        textAreaDescripcionPago= new JTextArea(venta.getDescripcionMetodoPago());//descripcion del metodo de pago de la venta 
        textAreaDescripcionPago.setBorder(new TitledBorder(borderNegro,DatosRegistroVentas.LABELS_REGISTRO_VENTA[10],TitledBorder.DEFAULT_JUSTIFICATION , TitledBorder.DEFAULT_POSITION, Tipografia.ARIAL_BOLD_12, Color.BLACK));     
        textAreaDescripcionPago.setMinimumSize(dimensionDescriptions);//tamaño del panel para evitar que se demasiado pequeño
        textAreaDescripcionPago.setFont(Tipografia.ARIAL_12);
        textAreaDescripcionPago.setForeground(Color.BLACK);
        textAreaDescripcionPago.setOpaque(false);
        textAreaDescripcionPago.setLineWrap(true);
        
        textAreaDescripcionPago.setEditable(false);        
        
        textAreaDescripcionVenta= new JTextArea(venta.getDescripcionVenta());//descripcion de la venta 
        textAreaDescripcionVenta.setBorder(new TitledBorder(borderNegro,DatosRegistroVentas.LABELS_REGISTRO_VENTA[11],TitledBorder.DEFAULT_JUSTIFICATION , TitledBorder.DEFAULT_POSITION, Tipografia.ARIAL_BOLD_12, Color.BLACK));
        textAreaDescripcionVenta.setMinimumSize(dimensionDescriptions);//tamaño del panel para evitar que se demasiado pequeño
        textAreaDescripcionVenta.setFont(Tipografia.ARIAL_12);
        textAreaDescripcionVenta.setForeground(Color.BLACK);
        textAreaDescripcionVenta.setOpaque(false);
        textAreaDescripcionVenta.setLineWrap(true);                  
        textAreaDescripcionVenta.setEditable(false);
        
        try{
            for (int i = 0; i < iconosBotonera.length; i++) {
            
            iconosBotonera[i]= new ImageIcon(ImageIO.read(HideShowPanelRV.class.getResource(DatosBotones.ICONOS_BOTONES_FACTURA[i])));
            }        
        }catch(IOException ex){}
                
        botonera= new Botonera(DatosBotones.NOMBRES_BOTONES_FACTURA,iconosBotonera,new FlowLayout(FlowLayout.CENTER));//botonera de los botones imprmir , guardar imagen y eliminar
        
        botonera.setOpaque(false);
        botonera.setButtonsFont(Tipografia.ARIAL_12);
        botonera.setButtonsForeground(Color.BLACK);
        
        
        
        //paneles auxiliares
        panelAuxConceptos.add(labelFraseConcepto,BorderLayout.NORTH);
        panelAuxConceptos.add(textAreaConceptos,BorderLayout.CENTER);
        
        panelAuxCostos.add(labelFraseCosto,BorderLayout.NORTH);
        panelAuxCostos.add(textPaneCostos,BorderLayout.CENTER);
                
        panelAuxPreciosFinales.add(textPaneFrasesPreciosTotales,    "cell 0 1 1 2,width 75%");
        panelAuxPreciosFinales.add(textPanePreciosTotales,          "cell 1 1 1 2,width 25%");               
        
        panelCustomer.add(labelNombreCliente,   "cell 0 0 1 1");
        panelCustomer.add(labelRif,             "cell 0 1 1 1");
        panelCustomer.add(labelDomicilio,       "cell 0 2 1 1");
        panelCustomer.add(labelTelefono,        "cell 0 3 1 1");

        //labelDomicilio labelTelefono
        
        //todo debajo debe ser impreso
               
                                                 // "cell column row width height"
                                                 
        panelFechaNo.add(labelFechaHoraHidden);//fecha de la factura        
        panelFechaNo.add(labelNumBill);//numero de factura
        
        panelPrintable.add(panelFechaNo,                    "cell 0 0 2 1");//panel con la fecha y el numero de factura
        panelPrintable.add(panelCustomer,                   "cell 0 1 2 1,width 360px");                
        panelPrintable.add(panelAuxConceptos,               "cell 0 2 1 2,width 250px");
        panelPrintable.add(panelAuxCostos,                  "cell 1 2 1 2");                            
        panelPrintable.add(panelAuxPreciosFinales,          "cell 0 4 2 2,width 360px");
        panelPrintable.add(textPaneMetodoPago,              "cell 0 6 2 1"); 

        //todo debajo no requiere ser impreso con la factura 
        panelDescriptions.add(textAreaDescripcionPago,          "cell 0 0 2 2,growx");                
        panelDescriptions.add(textAreaDescripcionVenta,         "cell 0 2 2 2,growx");
        
        panelDescriptions.add(botonera,"dock south");
        
        panelHiddenBig.add(panelPrintable,BorderLayout.CENTER);
        
        panelHiddenBig.add(panelDescriptions,BorderLayout.SOUTH);
        
        
        //eventos botonera
        botonera.setButtonEvent(new EventoRVPrintBill(this), 0);
        botonera.setButtonEvent(new EventoRVSavePng(this),1);
        botonera.setButtonEvent(new EventoRVDeleteBill(this,panelRV), 2);
        
    }
    /**
     * returns a cloned copy 
     * of the panel with the 
     * bill information
     * @return 
     */
    public PrintableJPanel getClonedPrintableBillPanel(){
        PrintableJPanel panelToPrint;
        panelToPrint= new PrintableJPanel(new BorderLayout(0, 0));
        try {
            panelToPrint.add((Component)((Cloneable) panelPrintable.clone()),BorderLayout.CENTER);//se clona el panel de la factura y se añade en el medio al panel contenedor que puede ser impreso 
        } catch (CloneNotSupportedException| AccessControlException ex) {}
        panelToPrint.add( new BillHead(),BorderLayout.NORTH);//se le añade el membrete de la factura al panel que puede ser impreso
        
        return panelToPrint;
    }
    
    
    public void showHiddenPanel(){
        panelHiddenBig.setVisible(true);
        
        panelHiddenBig.revalidate();
        panelHiddenBig.repaint();        
                
        this.revalidate();
        this.repaint();
                    
    }
    
    public void hideHiddenPanel(){
        panelHiddenBig.setVisible(false);
                              
        this.revalidate();
        this.repaint();
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {//evento que muestra y oculta el panel
        
        if (panelHiddenBig.isVisible()) {
            this.hideHiddenPanel();
            buttonVerOcultar.centinela=1;
        }else{
            this.showHiddenPanel();
            buttonVerOcultar.centinela=0;
        }
    }
    /**
     * panel de factura sin membrete
     * 
     * @return 
     */
    public JPanel getCopyPrintablePanel(){        
        try {
            return (JPanel)((Component)((Cloneable) panelPrintable.clone()));
        } catch (CloneNotSupportedException | AccessControlException ex) {
            Logger.getLogger(HideShowPanelRV.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
            
    }
    
    public int getIdventa(){
        return idVenta;
    }
}
