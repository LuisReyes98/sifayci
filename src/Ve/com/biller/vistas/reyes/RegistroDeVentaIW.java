package Ve.com.biller.vistas.reyes;


import Ve.com.biller.helpers.reyes.VentanaInterna;





/**
 *
 * @author Luis Reyes
 */
public class RegistroDeVentaIW extends VentanaInterna{
    PanelRV panelContenedor;
    
    
    public RegistroDeVentaIW(String[] atributos, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable, boolean cerrable) {
        super(atributos, resizable, closable, maximizable, iconifiable, cerrable);
        crearGUI();
    }

    private void crearGUI() {
        
        panelContenedor=new PanelRV();//el panel del registro de ventas
        
        
        this.add(panelContenedor);
        
    }
    
}
