package Ve.com.biller.eventos.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Atributos;
import Ve.com.biller.vistas.reyes.DialogoDescripcionPlatilloNO;
import Ve.com.biller.vistas.reyes.OrdenPanelNO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.SpinnerModel;



/**
 *
 * @author Luis_Reyes
 */
public class EventoNOBotonOrdenAnadirPlatillo implements ActionListener{
    DialogoDescripcionPlatilloNO dialogoPrevio;
    OrdenPanelNO panelDeOrdenes;
    SpinnerModel spinnerModel;
    JComboBox comboSizes;   
    double[] precios;
    String[] descripcionTamanos;
    JComboBox[] combosHamburguesa;    
    String nombre;
    boolean hamburguesa;
    JCheckBox checkBox;
    
    public EventoNOBotonOrdenAnadirPlatillo(DialogoDescripcionPlatilloNO dialogoPrevio, OrdenPanelNO panelDeOrdenes, SpinnerModel spinnerModel,JComboBox comboSizes, double[] precios,String[] descripcionTamanos, String nombre,boolean hamburguesa,JComboBox[] combosHamburguesa,JCheckBox checkBox) {
        this.dialogoPrevio = dialogoPrevio;
        this.panelDeOrdenes = panelDeOrdenes;
        this.spinnerModel = spinnerModel;
        this.comboSizes= comboSizes;
        this.precios = precios;
        this.descripcionTamanos = descripcionTamanos;
        this.nombre = nombre;
        this.combosHamburguesa=combosHamburguesa;
        this.hamburguesa=hamburguesa;
        this.checkBox=checkBox;
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {  
        int cantidad;
        int tamanoIndex;
        int auxiliar;       
        cantidad= (int) spinnerModel.getValue();
        tamanoIndex= comboSizes.getSelectedIndex();
        dialogoPrevio.cerrarDialog(); //se cierra el dialogo anterior 
        
        if (descripcionTamanos[tamanoIndex]!=null) {
            nombre+=","+descripcionTamanos[tamanoIndex];
        }                        
        if (hamburguesa) {           
            for (int i = 0; i < combosHamburguesa.length; i++) {
                auxiliar=combosHamburguesa[i].getSelectedIndex();
                nombre+=","+CRUD_Atributos.componentesHamburguesa[i].getDescripciones()[auxiliar];
            }
        }
        if (checkBox.isSelected()) {
            panelDeOrdenes.addPlatilloOrden(nombre,0, cantidad);//introducir un producto gratis o de cortesia
        }else{
            panelDeOrdenes.addPlatilloOrden(nombre, precios[tamanoIndex], cantidad);//se envia el producto y cantidad deseada
        }
        
    }
    
}
