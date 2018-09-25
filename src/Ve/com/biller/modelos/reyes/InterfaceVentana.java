/*
 * 
 */
package Ve.com.biller.modelos.reyes;

/**
 *
 * @author Luis_Reyes
 */
public interface InterfaceVentana {
    public final String NOMBRE="SiFaYCI";
    public final String VERSION="";
    
    public final String[] ATRIBUTOS_VENTANA={
        NOMBRE+" [Colors Burgers Ver.] "+VERSION//nombre 0
                ,"850"// ancho 1
                ,"650"//altura 2
                ,"6"// Estado (maximizado minimizado) pos3
    };
    
    public final String[] IMAGENES_VENTANA={
        "/Ve/com/biller/imagenes/colores.png",//fondo de escritorio de la aplicacion                
    };
   
    public final String[] MENSAJES_DE_SALIDA={              
            " ¿ Desea SALIR del Sistema ? ",
            " Atención "
    };
    
    public final String[] IMAGENES_LOGIN={
        "/Ve/com/biller/imagenes/001-man-user.png",
        "/Ve/com/biller/imagenes/002-avatar.png",        
        "/Ve/com/biller/imagenes/001-gear.png"//imagen de tuerca
    };
    
    public final String[] LABELS_LOGIN={
        "No has iniciado sesión",
        "Iniciar sesión",
        "Cancelar",
        "Cerrar sesión"
            
    };
}
