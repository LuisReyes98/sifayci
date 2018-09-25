    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.conexion.reyes.sql;

/**
 *
 * @author Luis_Reyes
 */
public interface InterfaceSQL {
    public final String TABLA_ELIMINADOS_MENU="Platillos_Eliminados";
    /*
        Peticiones de la TABLA MENU
    */    
    public final String CAMPO_BASE_PRECIOI="precio";
    
    public final String
            TABLA_MENU                  = "Platillos",//nombre de la tabla
            CAMPO_CATEGORIAS            = "categoria",//campos de la tabla
            CAMPO_NOMBRE_MENU           = "nombre",
            CAMPO_PRECIO0               = "precio0",
            CAMPO_PRECIO1               = "precio1",
            CAMPO_PRECIO2               = "precio2",
            CAMPO_PRECIO3               = "precio3",            
            CAMPO_ID_MENU               = "id",
            CAMPO_HAMBURGUESA           = "has_attributes",
            CAMPO_INVENTARIADO          = "inventariado",
            CAMPO_CANTIDA_DE_INVENTARIO = "cantidad_en_inventario",
            CAMPO_HAMBURGUESA_100GR     = "es_hamburguesa_de_100gr",
            CAMPO_UNIDADES_DESCONTAR    ="unidades_descontar_inventario",
            CAMPO_ATTRIBUTES_ID         ="_attributes_id"
            ;
            
    
    
    public final String BUSCAR_CATEGORIAS= "select "+CAMPO_CATEGORIAS+","+CAMPO_HAMBURGUESA+" from "+ TABLA_MENU +" group by "+ CAMPO_CATEGORIAS+" ;";//peticion que busca todas las categorias que hay en el menu 
    
    public final String BUSCAR_PRODUCTO_CATEGORIA= "select "+CAMPO_NOMBRE_MENU+","+CAMPO_PRECIO0+","+CAMPO_PRECIO1+","+CAMPO_PRECIO2+","+CAMPO_PRECIO3+","+CAMPO_ID_MENU+","+CAMPO_HAMBURGUESA_100GR+","+CAMPO_INVENTARIADO+","+CAMPO_CANTIDA_DE_INVENTARIO+","+CAMPO_UNIDADES_DESCONTAR+" from "+ TABLA_MENU +" where "+CAMPO_CATEGORIAS+ " = ? ;" ;//peticion que pide todos los productos de una categoria el ? es la categoria a pedir
    
    //actualizacion del nombre de una categoria
    public final String ACTUALIZAR_NOMBRE_CATEGORIA= " update "+TABLA_MENU+" set "+CAMPO_CATEGORIAS+" = ? "
            + "where " +CAMPO_CATEGORIAS+" = ? ;";
    
    //actualizaciones de platillo plantilla de datos para construir la peticion de actualizar los platillos
    public final String ACTUALIZAR_PLATILLO_SAMPLE1= " update "+TABLA_MENU + " set ";
    public final String ACTUALIZAR_PLATILLO_SAMPLE2= " = ? ";
    public final String ACTUALIZAR_PLATILLO_SAMPLE_FINAL= " where id = ? ;";
    
//insercion de un nuevo platillo al menu   
    public final String ANADIR_NUEVO_PLATILLO_CATEGORIA=" insert into "+TABLA_MENU+"("+CAMPO_CATEGORIAS+","+CAMPO_NOMBRE_MENU+","+CAMPO_PRECIO0+","+CAMPO_PRECIO1+","+CAMPO_PRECIO2+","+CAMPO_PRECIO3+","+CAMPO_HAMBURGUESA+","+CAMPO_HAMBURGUESA_100GR+","+CAMPO_INVENTARIADO+") values (?,?,?,?,?,?,?,?,?) ;";//se insertan 9 atributos del nuevo platillo al menu 
    
    public final String ELIMINACION_LOGICA_PLATILLO= " insert into "+TABLA_ELIMINADOS_MENU +" select * from "+TABLA_MENU+" where id = ? ;";//se guarda un respaldo del platillo a borrar en la TABLA_ELIMINADOS_MENU para que la elminacion no sea permanente y a su vez se borra el platillo de la TABLA_MENU
    
    public final String ELIMINACION_FISICA_PLATILLO= "delete from "+TABLA_MENU+" where "+CAMPO_ID_MENU+" = ? ;";//eliminacion física del menu
    
     /*
        Peticiones de la TABLA HAMBURGUESA 
    */     
    public final String
            TABLA_HAMBURGUESA       ="Atributos_Platillo",
            CAMPO_ATRIBS_NAME              ="name",
            CAMPO_DESCRIPCION       ="descripcion";
    
    public final String BUSCAR_TIPOS= " select "+ CAMPO_ATRIBS_NAME+ " from "+ TABLA_HAMBURGUESA+" group by "+CAMPO_ATRIBS_NAME+" ;";
    
    public final String BUSCAR_DESCRIPCION_TIPOS = " select "+ CAMPO_DESCRIPCION+ " from "+ TABLA_HAMBURGUESA+ " where "+ CAMPO_ATRIBS_NAME +" = ? ;"; //el ? es el grupo de componentes a pedir  
    
    public final String DELETE_ATRIBUTES=" delete from "+TABLA_HAMBURGUESA+" where "+CAMPO_ATRIBS_NAME+" = ? and "+CAMPO_DESCRIPCION+" = ? ;";
    
    public final String INSERT_ATRIBUTES=" insert into "+TABLA_HAMBURGUESA+" ("+CAMPO_ATRIBS_NAME+","
            +CAMPO_DESCRIPCION+") values (?,?) ;";
    
    /**
     *Peticiciones de la Tabla Ventas
    */    
    public final String 
            TABLA_VENTAS                    ="Ventas",
            CAMPO_FECHA_VENTA               ="fecha_venta",
            CAMPO_DESCRIPCION_VENTA        ="descripcion_venta",
            /**
             * Campo de todos los platillos de
             * una venta
             */
            CAMPO_PLATILLOS                 ="platillos_venta",
            CAMPO_METODO_PAGO               ="metodo_de_pago",
            CAMPO_DESCRIPCION_PAGO          ="descripcion_pago",
            CAMPO_IVA_DESCUENTO             ="iva_descuento",
            CAMPO_PRECIO_TOTAL              ="precio_total",
            CAMPO_ID_VENTA                  ="id_venta",
            CAMPO_HORA_VENTA                ="hora_venta",
            CAMPO_CLIENTE_NOMBRE            ="nombre_cliente_venta",
            CAMPO_CLIENTE_CEDULA            ="rif_cliente_venta",
            CAMPO_TIPO_DE_RIF               ="tipo_de_rif",
            CAMPO_DOMICILIO                 ="domicilio",
            CAMPO_TELEFONO                  ="telefono_cliente";                    
    
    public final String INSERTAR_VENTA = " insert into "+TABLA_VENTAS+" ("+CAMPO_FECHA_VENTA+","+CAMPO_HORA_VENTA+","+CAMPO_DESCRIPCION_VENTA+"," +CAMPO_PLATILLOS+","+CAMPO_METODO_PAGO+","+CAMPO_DESCRIPCION_PAGO+","+CAMPO_IVA_DESCUENTO+"," +CAMPO_PRECIO_TOTAL+","+CAMPO_CLIENTE_NOMBRE+","+CAMPO_CLIENTE_CEDULA+","+CAMPO_TIPO_DE_RIF+","+CAMPO_DOMICILIO+","+CAMPO_TELEFONO+") values (?,?,?,?,?,?,?,?,?,?,?,?,?) ;";//se insertan 8 atributos sin el id ya que este ultimo lo maneja la base de datos automaticamente 
    
    //select fecha_venta,hora_venta,platillos_venta,descripcion_venta,metodo_de_pago,descripcion_pago,iva_descuento,precio_total,nombre_cliente_venta,cedula_cliente_venta from Ventas order by fecha_venta desc; 
    /**
     * Pide todas las ventas a la base de datos
     */ 
    public final String SELECT_ALL_SALES=" select "+CAMPO_FECHA_VENTA+","+CAMPO_HORA_VENTA+","+CAMPO_PLATILLOS+","+CAMPO_DESCRIPCION_VENTA+","+CAMPO_METODO_PAGO+","+CAMPO_DESCRIPCION_PAGO+","+CAMPO_IVA_DESCUENTO+","+CAMPO_PRECIO_TOTAL+","+CAMPO_CLIENTE_NOMBRE+","+CAMPO_CLIENTE_CEDULA+","+CAMPO_ID_VENTA+","+CAMPO_TIPO_DE_RIF+","+CAMPO_DOMICILIO+","+CAMPO_TELEFONO+" from "+TABLA_VENTAS+" order by "+CAMPO_FECHA_VENTA+" desc limit 300;";//peticion que pide todo el registro de ventas ordenadas con las mas recientes arriba y las mas viejas al final   
    /**
     * Pide todas las ventas comprendidas entre una fecha
     */
    public final String SELECT_SALES_DATE_RANGE=" select "+CAMPO_FECHA_VENTA+","+CAMPO_HORA_VENTA+","
            +CAMPO_PLATILLOS+","+CAMPO_DESCRIPCION_VENTA+","+CAMPO_METODO_PAGO+","
            +CAMPO_DESCRIPCION_PAGO+","+CAMPO_IVA_DESCUENTO+","+CAMPO_PRECIO_TOTAL+","+CAMPO_CLIENTE_NOMBRE
            +","+CAMPO_CLIENTE_CEDULA+","+CAMPO_ID_VENTA+","
            +CAMPO_TIPO_DE_RIF+","+CAMPO_DOMICILIO+","+CAMPO_TELEFONO+" from "+TABLA_VENTAS
            +" where "+CAMPO_FECHA_VENTA+" >= ? and "+CAMPO_FECHA_VENTA+" <= ? order by "
            +CAMPO_FECHA_VENTA+" desc ;";
    //peticion que pide todo el registro de ventas comprendido entre dos fechas ordenadas con las mas recientes arriba y las mas viejas al final 
    
    
    public final String BUSCAR_ID_VENTA_CON_FECHA_HORA= " select "+CAMPO_ID_VENTA+" from "+TABLA_VENTAS
            + " where "+CAMPO_HORA_VENTA+"= ? and "+CAMPO_FECHA_VENTA
            +"= ? limit 1;" ;//busqueda del id de una venta en base a su hora y fecha justo despues de ser ingresada
    
    public final String DELETE_SALE= "delete from "+TABLA_VENTAS+" where "+CAMPO_ID_VENTA+" = ? ;";//eliminacion fisica 
    /**
     * los atributos de la tabla ventas eliminas es igual al de la tabla ventas exceptuando
     * 3 tres campos extras
     */
    public final String
            TABLA_VENTAS_ELIMINADAS =   "Ventas_Eliminadas",
            CAMPO_USUARIO_DELETER=      "usuario",
            CAMPO_USUARIO_DELETER_ID=   "usuario_id",
            CAMPO_DELETED_REASON=       "reason_delete";
    
    public final String INSERT_DELETED_SALE=" insert into "+TABLA_VENTAS_ELIMINADAS+" select *,?,?,? from "
            + TABLA_VENTAS+" where "+CAMPO_ID_VENTA+" = ? ;";
    //los valores son en este orden usuario,usuario_id,reason_delete, id_venta (venta a eliminar)
    
     /**
     * Pide todas las ventas a la base de datos
     */ 
    public final String SELECT_ALL_DELETED_SALES=" select "+CAMPO_FECHA_VENTA+","+CAMPO_HORA_VENTA+","
            +CAMPO_PLATILLOS+","+CAMPO_DESCRIPCION_VENTA+","+CAMPO_METODO_PAGO+","+CAMPO_DESCRIPCION_PAGO
            +","+CAMPO_IVA_DESCUENTO+","+CAMPO_PRECIO_TOTAL+","+CAMPO_CLIENTE_NOMBRE+","
            +CAMPO_CLIENTE_CEDULA+","+CAMPO_ID_VENTA+","+CAMPO_TIPO_DE_RIF+","
            +CAMPO_DOMICILIO+","+CAMPO_TELEFONO+","+CAMPO_USUARIO_DELETER+","
            +CAMPO_USUARIO_DELETER_ID+","+CAMPO_DELETED_REASON+" from "
            +TABLA_VENTAS_ELIMINADAS+" order by "+CAMPO_FECHA_VENTA+" desc limit 300;";//peticion que pide todo el registro de ventas ordenadas con las mas recientes arriba y las mas viejas al final  
    
    
    /**
     * Peticiones de la tabla Ventas_Por_Platillo
     */
    public final String 
        TABLA_VENTAS_PLATILLO           ="Ventas_Por_Platillo",
        CAMPO_CATEGORIA_PLATILLO        ="categoria_platillo",
        CAMPO_NOMBRE_PLATILLO           ="nombre_platillo",
        CAMPO_CARACTERISTICAS_PLATILLO  ="caracteristicas_platillo",
        CAMPO_PRECIO_PLATILLO           ="precio_platillo",
        CAMPO_FECHA_PLATILLO            ="fecha_platillo",
        CAMPO_HORA_PLATILLO             ="hora_platillo",
        CAMPO_ID_VENTA_PLATILLO         ="_id_venta_platillo";

    
    
    public final String INSERTAR_VENTA_POR_PLATILLO=" insert into "+TABLA_VENTAS_PLATILLO+" ("
            +CAMPO_CATEGORIA_PLATILLO+"," +CAMPO_NOMBRE_PLATILLO+","+CAMPO_CARACTERISTICAS_PLATILLO
            +","+CAMPO_PRECIO_PLATILLO+","+CAMPO_FECHA_PLATILLO+","+CAMPO_HORA_PLATILLO+","
            +CAMPO_ID_VENTA_PLATILLO+") values (?,?,?,?,?,?,?) ;";
//metodo que guarda que un platillo especifico se ha vendido permitiendo despues chequear cual ha sido el platillo mas vendido en un periodo de tiempo
    /**
     * peticion que pide categoria, nombre , precio de los platillos de la tabla por platillo para
     * saber cuantos se han vendido
     */ 
    public final String SELECT_DISHES_BY_DATE=" select "+CAMPO_CATEGORIA_PLATILLO+","+
            CAMPO_NOMBRE_PLATILLO+","+CAMPO_PRECIO_PLATILLO+" from "+TABLA_VENTAS_PLATILLO+
            " where "+ CAMPO_FECHA_PLATILLO+" >= ? and "+CAMPO_FECHA_PLATILLO+" <= ? order by "+
            CAMPO_CATEGORIA_PLATILLO+" asc,"+CAMPO_NOMBRE_PLATILLO+" desc;";
    
    
    
    /**
     * Peticiones de la tabla usuarios 
     */
    public final String 
            TABLA_USUARIOS          ="Users",
            CAMPO_NOMBRE_USUARIO    ="user_name",
            CAMPO_PASSWORD          ="user_password",
            CAMPO_USER_ID           ="user_id",
            CAMPO_USER_ADMIN        ="admin"; 
       
    public final String SELECT_LOGIN=" select "+CAMPO_USER_ID+","+CAMPO_USER_ADMIN+" from "
            +TABLA_USUARIOS+" where "+ CAMPO_NOMBRE_USUARIO+"=? and "+CAMPO_PASSWORD+"=? limit 1 ;";
    
    public final String SELECT_ALL_USERS=" select "+CAMPO_NOMBRE_USUARIO+","+CAMPO_PASSWORD+","+CAMPO_USER_ID+","
            +CAMPO_USER_ADMIN+" from "+TABLA_USUARIOS+" order by "+CAMPO_USER_ID;
    
    public final String UPDATE_USER_PASSWORD=" update "+TABLA_USUARIOS+" set "+CAMPO_PASSWORD+" = ? where "
            + CAMPO_USER_ID+"  = ? ;";
    
    public final String DELETE_USER = " delete from "+TABLA_USUARIOS+" where "+CAMPO_USER_ID+" = ? ;";
    /**
     * instruccion SQL para crear un nuevo usuario
     */
    public final String INSERT_NEW_USER=" insert into "+TABLA_USUARIOS+" ("+CAMPO_NOMBRE_USUARIO+","
            +CAMPO_PASSWORD+","+CAMPO_USER_ADMIN+") values (?,?,?) ;";
    
    public final String SELECT_USER_BY_NAME=" select "+CAMPO_NOMBRE_USUARIO+" from "+TABLA_USUARIOS
            +" where "+CAMPO_NOMBRE_USUARIO+"= ? limit 1 ;";
    
}
