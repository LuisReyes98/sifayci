package Ve.com.biller.eventos.reyes;

import Ve.com.biller.conexion.reyes.sql.DatosSQL;
import Ve.com.biller.conexion.reyes.sql.CRUD_Menu;
import Ve.com.biller.helpers.reyes.LabelDeTextoCambiante;
import Ve.com.biller.modelos.reyes.ControladorMenuIW;
import Ve.com.biller.estructuras.reyes.Producto;
import Ve.com.biller.vistas.reyes.PanelEditMenuIW;
import Ve.com.biller.vistas.reyes.PanelEditarCategoriasEM;
import Ve.com.biller.vistas.reyes.PanelEditarPlatillosEM;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Luis Reyes
 */
public class EventoEMGuardarMenuEditado implements ActionListener {
    PanelEditMenuIW panelEditarMenu;
    public EventoEMGuardarMenuEditado(PanelEditMenuIW panelEditarMenu) {
        this.panelEditarMenu=panelEditarMenu;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        PanelEditarCategoriasEM panelEditCategorias= panelEditarMenu.getPanelEditarCategorias();//panel de editar categorias
        PanelEditarPlatillosEM[] panelEditarPlatillos= panelEditarMenu.getPanelesEditarPlatillo();//paneles de editar platillos
        LabelDeTextoCambiante[] labelNombreCategoria= panelEditCategorias.getLabelsCategorias();//labels que permiten saber si ha habido un cambio para de esta forma efectuar el guardado de informacion 
        LabelDeTextoCambiante[] labelNombrePlatillo; //label para guardar nombres de platillos
        LabelDeTextoCambiante[][] labelPreciosPlatillo;//labels para guardar precios de platillos
        //actualizar platillo
        String instruccionActualizacionPlatilloUnico=DatosSQL.ACTUALIZAR_PLATILLO_SAMPLE1;
        //" update "+TABLA_MENU + " set "
        String nuevoNombre=null;
        ArrayList<Double> nuevosPrecios;
        Producto[] productos;
        int newPricesSize=0;
        
        //String posicionesCambio="";
        int cantidadCambios=0;//variable centinela para controlar el uso de comas ','
        
        boolean cambioPrecios= false;
        boolean cambioPlatillo=false;
        boolean algunCambio=false;
        
        for (int i = 0; i < panelEditarPlatillos.length; i++) {//for de la cantidad de categorias
            if (panelEditarPlatillos[i]!=null) {
                labelNombrePlatillo=panelEditarPlatillos[i].getLabelPlatillosNombres();
                labelPreciosPlatillo= panelEditarPlatillos[i].getLabelPlatillosPrecios();
                productos=panelEditarPlatillos[i].getProductos();
                                
                for (int j = 0; j < labelNombrePlatillo.length; j++) {//cantidad de platillos en la categoria
                    nuevosPrecios= new ArrayList<>();
                  
                    if (labelNombrePlatillo[j].hasChangedText()) {
                        cambioPlatillo=true;
                        algunCambio=true;
                        
                        nuevoNombre=labelNombrePlatillo[j].getText();
                        labelNombrePlatillo[j].setOriginalText(nuevoNombre); //el nombre original es ahora el nuevo nombre                     
                        cantidadCambios++;
                        instruccionActualizacionPlatilloUnico+=DatosSQL.CAMPO_NOMBRE_MENU+
                                DatosSQL.ACTUALIZAR_PLATILLO_SAMPLE2;// nombre+ " = ? "                        
                    }//fin text has changed
                    for (int k = 0; k < 4; k++) {                        
                        if (labelPreciosPlatillo[j][k].hasChangedText()) {//si algun precio es distinto
                            cambioPrecios=true;
                            cambioPlatillo=true;
                            algunCambio=true;
                            labelPreciosPlatillo[j][k].setOriginalText(labelPreciosPlatillo[j][k].getText());//el precio original es ahora el nuevo precio
                            nuevosPrecios.add(Double.parseDouble(labelPreciosPlatillo[j][k].getText().
                                    replace(',','.')));
                            newPricesSize++;
                            
                            if (cantidadCambios>0) {
                                instruccionActualizacionPlatilloUnico+=" , ";
                            }
                            instruccionActualizacionPlatilloUnico+=DatosSQL.CAMPO_BASE_PRECIOI+k+
                                    DatosSQL.ACTUALIZAR_PLATILLO_SAMPLE2;
                            // , precioK = ?   ,   precio3 = ?
                            cantidadCambios++;
                        }//fin price has changed                       
                    }
                    if (cambioPlatillo) {
                        instruccionActualizacionPlatilloUnico+= DatosSQL.ACTUALIZAR_PLATILLO_SAMPLE_FINAL;
                        //where id = ? ;                      
                        CRUD_Menu.actualizarPlatillo(instruccionActualizacionPlatilloUnico, transformarArrayList(nuevosPrecios,newPricesSize), nuevoNombre, productos[j].getId(),cambioPrecios);
                        
                    }
                    
                    cambioPrecios= false;
                    newPricesSize=0;
                    cantidadCambios=0;                                       
                    cambioPlatillo=false;
                    instruccionActualizacionPlatilloUnico=DatosSQL.ACTUALIZAR_PLATILLO_SAMPLE1;               
                    nuevoNombre=null;
                    
                }//fin j
               
            }//fin if not null
            
            if (labelNombreCategoria[i].hasChangedText()) {//se comprueban si los nombres de las categorias han cambiado
                algunCambio=true;
                CRUD_Menu.actualizarCategoria(labelNombreCategoria[i].getText(),labelNombreCategoria[i].getOriginalText());//se actualiza la base de datos
                labelNombreCategoria[i].setOriginalText(labelNombreCategoria[i].getText());//se actuliza la interfaze para cambios consecutivos
            }            
        }//fin i                   
        
        if (algunCambio) {//se chequea si ha habido cambios para no hacer actualizaciones innecesarias
            CRUD_Menu.selectMenu();
            ControladorMenuIW.CrearMenu();
            
            //panelEditarMenu.recargarVentana();//se recarga toda la ventana interna para así actualizar iconos de ser necesario "Metodo inneserario al guardar pero necesario al añadir un platillo nuevo"
            System.gc();//se llama al garbage colector para controlar la cantidad de memoria en la RAM
        }        
        
    }
    
    private double[] transformarArrayList(ArrayList<Double> arrayList,int size){
        Iterator<Double> iterator= arrayList.iterator();
        if (size>0) {
            double[] precios= new double[size];        
            int i=0;
            while(iterator.hasNext()){
                precios[i++]=iterator.next();                    
            }                
            return precios;
        }else{
            return null;
        }                                          
    }
                
}
