package Ve.com.biller.modelos.reyes;

/**
 *
 * @author Luis Reyes
 */
public interface InterfaceRegistroDeVentas {
    public final String[] COLUMNAS_REGISTRO_VENTA={"Fecha","Hora","Platillos","Descripción","Metodo Pago","Descripcion Pago","IVA o Descuento","Precio Total","Nombre Cliente","Cedula"};
    
    
    public final String[] LABEL_VISUAL={"Desde:","Hasta:","Resumen"};
    
    public final String TITULO_ADVERTENCIA="Atención";
    
    public final String MENSAJE_ADVERTENCIA="¿ Esta seguro que desea mostrar \n todo el registro de ventas ?";
    
    public final String[] LABELS_REGISTRO_VENTA={   
        "Factura N°: ",//0
        "Cliente: ",//1        
        "Concepto",//2
        "Costo",//3
        "IVA (",//4
        "%): ",//5
        "Descuento (",//6
        "\nPlatillos: ",//7
        "\nTotal: ",//8
        "Metodo de Pago: ",//9
        "Descripción Pago:",//10
        "Descripción Pedido:",//11
               
                                                };
    public final String[] LABELS_CLIENTE={
            "Nombre: ",
            "N° RIF: ",
            "Domicilio Fiscal: ",
            "Telefono: "};
    
    public final String TOOL_TIP_BUTTON_SHOW[] ={"Mostrar Más","Mostrar Menos"};
    
    public final String SUMMARY_END="...";
    
    public final String[] MEMBRETE_FACTURA={
        "Colors Burger, C.A.",
        "RIF.: J 407804529",        
        "Av. Don Julio Centeno, C.C. El Portal de San Diego,Local 16",
        "Nivel 1, Sector el Remanso - Municipio San Diego Carabobo",
            "Telf.: 0241-891.19.77 - Correo: colorsdirecto@gmail.com"
    };
    
    public final String LOGO="/Ve/com/biller/imagenes/logo_colorsx554.png";
    
}
