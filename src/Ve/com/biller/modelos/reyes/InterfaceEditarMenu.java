package Ve.com.biller.modelos.reyes;

/**
 *
 * @author Luis Reyes
 */
public interface InterfaceEditarMenu {
    
    public final String[] LABELS_FORMULARIO={
        " Nombre (*)",//label del formulario
        "Precios o Tamaños :",//subtitulo del formulario
        "Precio ",//precio del precio 1 2 3 4
        " (*)",//campo clave 
        "Añadir "//label TITULO del formulario  de añadir platillos
    };
    
    public final String ADVERTENCIA_FORMULARIO_EDITAR_MENU="Formulario mal Estructurado o "
                                                            + "\n Campos Vacios.";
    public final String ADVERTENCIA_FORMULARIO_TITULO= "Atención";
    public final String CATEGORIA_LABEL_FORMULARIO="Categoria";
    
    public final String TITULO_DIALOGO_EDITAR_MENU="Añadir Platillo";//titulo del dialog añadir platillo
    
    
    public final String NOMBRE_CHECKBOX_100GR="Precio 1 : Carne 100gr";
    public final String PISTA_CHECKBOX_100GR="Hamburguesa de un solo precio \n cuya carne es de 100gramos";
    
    public final String ADVERTENCIA_ELIMINAR_PLATILLO="¿Seguro que desea Eliminar \n "
                                                       + " el platillo seleccionado ?";
}
