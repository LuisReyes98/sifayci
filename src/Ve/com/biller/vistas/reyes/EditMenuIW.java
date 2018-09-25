package Ve.com.biller.vistas.reyes;

import Ve.com.biller.helpers.reyes.DialogoCerrarSinGuardar;
import Ve.com.biller.helpers.reyes.ImageManager;
import Ve.com.biller.helpers.reyes.VentanaInterna;
import Ve.com.biller.modelos.reyes.DatosBotones;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author Luis Reyes
 */
public class EditMenuIW extends VentanaInterna {
    DialogoCerrarSinGuardar dia;
    JInternalFrame auxiliar;
    PanelEditMenuIW panelEditMenu;
    
    public EditMenuIW(String[] atributos, boolean resizable, boolean closable, boolean maximizable, boolean iconifiable, boolean cerrable) {
        super(atributos, resizable, closable, maximizable, iconifiable, cerrable);
        crearGUI();
        
        
    }
    private void crearGUI(){
        
        panelEditMenu= new PanelEditMenuIW(this);//se crea el panel contenedor de edtiar los menues
        this.add(panelEditMenu);
        
        try {//icono de la ventana interna
           
            this.setFrameIcon(ImageManager.iconResize(new ImageIcon(ImageIO.read(EditMenuIW.class.getResource(DatosBotones.ICONOS_BOTONES_MAIN[1]))),16,16));            
        } catch (IOException ex) {
        this.setVisible(true);
        }
        auxiliar=this;
        this.addInternalFrameListener(new InternalFrameAdapter() 
        { 
                //clase anonima que impide que se cierre todo sin confirmar           
                @Override
                public void internalFrameClosing(InternalFrameEvent e) {
                    if (panelEditMenu.hasChangesBeenMade()) {
                        dia=new DialogoCerrarSinGuardar(auxiliar);
                    }else{
                        auxiliar.dispose();
                    }
                                       
                }
        }); 
        
    }
    public void recargar(){
        this.remove(panelEditMenu);
        panelEditMenu= new PanelEditMenuIW(this);//se crea el panel contenedor de edtiar los menues
        
        this.add(panelEditMenu);
        this.revalidate();
        this.repaint();
    }
    
}
