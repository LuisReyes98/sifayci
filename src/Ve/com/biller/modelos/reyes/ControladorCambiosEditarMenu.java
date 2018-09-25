package Ve.com.biller.modelos.reyes;

import Ve.com.biller.estructuras.reyes.Cambios;
import Ve.com.biller.estructuras.reyes.Categoria;
import Ve.com.biller.vistas.reyes.PanelEditMenuIW;
import Ve.com.biller.vistas.reyes.PanelEditarCategoriasEM;
import Ve.com.biller.vistas.reyes.PanelEditarPlatillosEM;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Luis Reyes
 */
public class ControladorCambiosEditarMenu {
    public static  Categoria[] cambiosMenu;//datos tipo menu de todos los datos que hay que actualizar en la base de datos
    
   
    public static int sizeCategorias;
    public static boolean cambiarCategoriasNombres;
    
    public static void cambiosAEjecutar(PanelEditMenuIW panelEditarMenu){        
        //Producto[] cambiosProductos;
        
        PanelEditarCategoriasEM panelEditarCategorias=panelEditarMenu.getPanelEditarCategorias();
        PanelEditarPlatillosEM[] panelesEditarPlatillo;
        
        panelesEditarPlatillo = panelEditarMenu.getPanelesEditarPlatillo();
        
        
        
    }
    
    
    /**
     * Metodo para obtener una mtriz
     * de los cambios a realizar en los
     * nombres de las categorias
     * y en caso de usarse para los nombres de los
     * platillos debe saberse a que categoria pertenece
     * @param oldString
     * @param newString
     * @return 
     */
    public static String[][] cambiosNombres(String[] oldString,String[] newString){
        String[][] matrizComparacion;
        matrizComparacion=null;
        ArrayList<Cambios> cambiosFuturos= new ArrayList<>();
        Iterator<Cambios> iterarCambios;
        Cambios cambiosAuxiliar;
        int j=0;
        sizeCategorias=0;
        if (oldString.length==newString.length) {            
            for (int i = 0; i < oldString.length; i++) {                
                if (oldString[i].equals(newString[i])) {
                    cambiosAuxiliar=new Cambios(oldString[i],newString[i]);//saving the categories that have changed 
                    cambiosFuturos.add(cambiosAuxiliar);
                    sizeCategorias++;
                }                
            }
        }
        if (sizeCategorias==0) {
            cambiarCategoriasNombres=false;
        } else {
            matrizComparacion= new String[sizeCategorias][2];
            iterarCambios= cambiosFuturos.iterator();
            
            while(iterarCambios.hasNext()){
                cambiosAuxiliar= iterarCambios.next();
                matrizComparacion[j][0]=cambiosAuxiliar.getOldName();
                matrizComparacion[j][1]=cambiosAuxiliar.getNewName();
                j++;
            }            
            cambiarCategoriasNombres=true;
        }
        //columna 1 nombre viejo , columna 2 nombre nuevo
        return matrizComparacion;//if there are no changes to be made the matrix will be null
    }
    
    
    
}
