package Ve.com.biller.eventos.reyes;

import Ve.com.biller.conexion.reyes.sql.CRUD_Menu;
import Ve.com.biller.modelos.reyes.ControladorMenuIW;
import Ve.com.biller.modelos.reyes.DatosEditarMenu;
import Ve.com.biller.estructuras.reyes.Producto;
import Ve.com.biller.vistas.reyes.PanelEditarPlatillosEM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis Reyes
 */
public class EventoEM_EliminarPlatillo implements ActionListener{
    Producto producto;    
    PanelEditarPlatillosEM panelEditar;
   
    
    public EventoEM_EliminarPlatillo(Producto producto, PanelEditarPlatillosEM panelEditar) {
        this.producto = producto;
        this.panelEditar = panelEditar;
        
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        int respuesta;
        respuesta=JOptionPane.showConfirmDialog(null,DatosEditarMenu.ADVERTENCIA_ELIMINAR_PLATILLO,
                DatosEditarMenu.ADVERTENCIA_FORMULARIO_TITULO,JOptionPane.YES_NO_OPTION);
        if (respuesta==JOptionPane.YES_OPTION) {
            CRUD_Menu.eliminacionLogicaDePlatillo(producto.getId());           
            CRUD_Menu.selectMenu(); //se pide denuevo el menu
            ControladorMenuIW.CrearMenu();//se crea el texto del menu
            panelEditar.recargarTodo();
        }
        
        
    }
    
    
}
