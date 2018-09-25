/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.modelos.reyes;


/**
 *
 * @author Luis_Reyes
 */
public interface InterfaceOrdenesIW {
    
    public final String[] TITULO_ORDEN={
        " Mesa N°",
     
    };
    public final String[] TAB_ORDEN={
        "          Orden ",
        "          "
    };
    public final String[] ENCABEZADO_ORDENES ={
        "Platillo",
        "Precio"    
    };
    public final String PISTA_SPINNER= " Valores ( + ) IVA / Valores ( - ) descuento";
    
    
    public final String[] CONTENIDO_ORDEN_PANEL={
        "Fecha: ",
        "Descripción Corta del Pedido",
        "0,00 Bs",
        "Total de la Orden: ",
        "Metodo de Pago: ",
        "I.V.A./Descuento en % ",
        "Descripción del Metodo de Pago (opcional) : ",
    };
    
    public final String[] METODOS_DE_PAGO={
        "Credito",
        "Debito",
        "Transferencia",
        "Efectivo",
        "Otros..",
    };
   
    public final String[] TAMANOS={
        "Pequeño: ",
        "Mediano: ",
        "Grande: ",
        "Extra Grande: ",
        "Único: "
    };
    public final String[] TAMANOS_TABLA={
        "Pequeño",
        "Mediano",
        "Grande",
        "Extra Grande",
       
    };
    public final String[] TAMANOS_HAMBURGUESA_TABLA={
        "100gr",
        "150gr",
        "200gr",
    };  
    public final String[] TAMANOS_HAMBURGUESA={
        "100gr,",
        "150gr,",
        "200gr,",
    };   
    
  
    public final String[] DIALOGO_DESCRIPCION={
        " Precio: ",
        " Cantidad ",
        " Precios: ",
    
    };
    public final String TITULO_DIALOGO_CATEGORIAS   =   " Categorias ";
    
    public final String LABEL_ORDEN_TERMINADA =" Pedido finalizado. ";
    
    public final String[] DIALOGO_AYUDA_REMOVER_PLATILLO={
          "Seleccione el platillo , luego haga click "
             +  "\n en el boton de \"Remover Platillo\" para Eliminarlo. "
             +  "\n Pista:"
             +  "\n Mantenga pulsada la tecla \"Ctrl\" "
             +  "\n para selecionar mas de un platillo a la vez",
        
        "Como Remover un platillo:",
    };
    
    public final String[] DIALOGO_TERMINAR_ORDEN={
        " Esta acción NO se puede DESHACER, "
               + "\n ¿ Está seguro que finalizó la orden ? " ,
        
        "Atención"//titulo pos 1
    };
    public final String DIALOGO_NO_HAY_CLIENTE=
            "La cedula y nombre del cliente "
            + "\n NO pueden ser nulos.";
    
    public final String DIALOGO_NO_HAY_PLATILLOS=
            "No hay platillos en el pedido";
    
    public final String DIALOGO_CLIENTE_NO_VALIDO=
            "Nombre o Cedula invalidos o muy cortos";
    
    public final String[] OPCION_CANCELAR={"Cancelar"};
    
    public final String DIALOGO_PLATILLO_CORTESIA="Cortesia";
    
    public final String[] ELEMENTOS_IVA_DESCUENTO={
        "    Sin Iva o Descuento",
        "    IVA (",
        "    Descuento (",
        "%) = ",
        
    };
    
    public final String[] ELEMENTOS_TOTAL_IVA_DESCUENTO={
        "\n(+IVA) ",
        "\n(-Desc.) ",    
    };
    
    public final String[] ELEMENTOS_CLIENTE_ORDEN={
        "Datos Cliente:",
        "*Nombre: ",
        "*Rif: ",
        "Domicilio Fiscal:",
        "Telefono:"
    };
    
    public final String[] CHARACTERS_RIF={
        "V",
        "E",
        "J",
        "D",        
    };
    public final String[] CHARACTERS_RIF_TOOL_TIP={
        "Venezolano",
        "Extranjero",
        "RIF",
        "Pasaporte"};   
    
    public final String[] PISTAS_DATOS_CLIENTES={
        "Obligatorio",
        "Opcional"};
}
