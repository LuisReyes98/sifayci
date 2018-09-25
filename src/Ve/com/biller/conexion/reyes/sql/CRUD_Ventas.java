package Ve.com.biller.conexion.reyes.sql;

import Ve.com.biller.estructuras.reyes.Venta;
import Ve.com.biller.estructuras.reyes.VentaDeleted;
import Ve.com.biller.estructuras.reyes.VentaPlatillo;
import Ve.com.biller.vistas.reyes.LoginPanel;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 *
 * @author Luis Reyes
 */
public class CRUD_Ventas {
    //public static Venta[] salesRecord;//vale la pena mantenerlo estatico????
    
    
    
    public static void insertVenta(String fechaHora,String hora,String descripcion,DefaultTableModel modeloTablaPlatillos,String metodoPago,String descripcionMetodoPago,double ivaDescuento,double precioTotal,String nombreCliente,int cedulaCliente,String tipoDeRif,String domicilio,String telefono){  
        // public final String INSERTAR_VENTA = " insert into "+TABLA_VENTAS+" ("+CAMPO_FECHA_VENTA+","+CAMPO_HORA_VENTA+","+CAMPO_DESCRIPCION_VENTA+"," +CAMPO_PLATILLOS+","+CAMPO_METODO_PAGO+","+CAMPO_DESCRIPCION_PAGO+","+CAMPO_IVA_DESCUENTO+"," +CAMPO_PRECIO_TOTAL+","+CAMPO_CLIENTE_NOMBRE+","+CAMPO_CLIENTE_CEDULA+") values (?,?,?,?,?,?,?,?,?,?) ;";//se insertan 10 atributos sin el id ya que este ultimo lo maneja la base de datos automaticamente
        
        PreparedStatement pstatementPlatillo;
        ResultSet resultSetIdVenta;//resultset utilizado para obtener el id de la venta 
        int idVentaRegistrada; //el id de la venta con todos los platillos una vez ingresada
        int cantidadPlatillos=modeloTablaPlatillos.getRowCount();
        String descripcionPlatillo;//variable auxiliar de lectura de datos
        PreparedStatement preparedStatement;//statement que ejecuta la insercion de datos de una venta completa
        String listadoDePlatillos="";
        String auxPrecio;
        LocalTime auxTime;
        Date date;//sql date
        Time time;
            
        date =new Date(Date.parse(fechaHora));//fecha y hora de finiquitacion de la orden
        auxTime= LocalTime.parse(hora);//tiempo auxiliar que se utiliza para convertirlo en un tiempo legible por la base de datos
        time= Time.valueOf(auxTime);//hora de la orden en formato legible por la base de datos
                    
        for (int i = 0; i < cantidadPlatillos; i++) {//prepacion de la informacion que se encuentra en la tabla            
            descripcionPlatillo=(String) modeloTablaPlatillos.getValueAt(i, 0);//categoria nombre y caracteristicas del platillo
            listadoDePlatillos+=descripcionPlatillo+";";//todos los datos del platillo                       
            auxPrecio=(String) modeloTablaPlatillos.getValueAt(i,1);//precio del platillo
            auxPrecio=auxPrecio.replace(',','.');//precio con punto '.' en vez de de coma ','                                    
            listadoDePlatillos+=auxPrecio+";\n";//se añade a la lista de platillos    
            
        }
                
        try {      
            
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.INSERTAR_VENTA);           
            preparedStatement.setDate(1, date);//fecha de la orden
            preparedStatement.setTime(2, time);//hora de la orden
            preparedStatement.setString(3, descripcion);//descripcion de la venta 
            preparedStatement.setString(4, listadoDePlatillos);//lista de todos los platillos de la venta con su precio
            preparedStatement.setString(5, metodoPago);//metodo de pago de la orden
            preparedStatement.setString(6, descripcionMetodoPago);//descripcion del metodo de pago
            preparedStatement.setDouble(7, ivaDescuento);//valor del iva o descuento en esa venta            
            preparedStatement.setDouble(8, precioTotal);//precio total de la venta (precio + iva ó precio - descuento           
            preparedStatement.setString(9,nombreCliente);//nombre del cliente de la orden puede ser nulo
            preparedStatement.setInt(10,cedulaCliente);//cedula del cliente de la orden puede ser nulo  
            preparedStatement.setString(11,tipoDeRif);//caracter que identifica que tipo de rif es 
            preparedStatement.setString(12,domicilio);//domicilio de la persona 
            preparedStatement.setString(13,telefono);//telefono del cliente
            
            preparedStatement.executeUpdate();//se ejecuta la inserción de datos
            
            //se pide el id de la venta una vez regitrada en la base de datos
            pstatementPlatillo=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.BUSCAR_ID_VENTA_CON_FECHA_HORA);
            pstatementPlatillo.setTime(1, time);
            pstatementPlatillo.setDate(2, date);
            
            resultSetIdVenta=pstatementPlatillo.executeQuery();
            idVentaRegistrada= resultSetIdVenta.getInt(DatosSQL.CAMPO_ID_VENTA);//id de la venta una vez registrada
            
                      
            
            for (int j = 0; j < cantidadPlatillos; j++) {//registro individual de cada platillo               
                descripcionPlatillo=(String) modeloTablaPlatillos.getValueAt(j, 0);//categoria nombre y caracteristicas del platillo
                auxPrecio=(String) modeloTablaPlatillos.getValueAt(j,1);//precio del platillo 
                auxPrecio=auxPrecio.replace(',','.');//precio con punto '.' en vez de de coma ','                           
                insertVentaPlatilloSingular(descripcionPlatillo, Double.parseDouble(auxPrecio), date, time,idVentaRegistrada);//registro de cada platillo especifico               
            }      
            
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    public static void insertVentaPlatilloSingular (String categNomCarac,double precioPlatillo,Date fecha,Time hora,int idVenta){        
        //public final String INSERTAR_VENTA_POR_PLATILLO=" insert into "+TABLA_VENTAS_PLATILLO+" ("+CAMPO_CATEGORIA_PLATILLO+","+CAMPO_NOMBRE_PLATILLO+","+CAMPO_CARACTERISTICAS_PLATILLO+","+CAMPO_PRECIO_PLATILLO+","+CAMPO_FECHA_PLATILLO+","+CAMPO_HORA_PLATILLO+") values (?,?,?,?,?,?) ;";//metodo que guarda que un platillo especifico se ha vendido permitiendo despues chequear cual ha sido el platillo mas vendido en un periodo de tiempo
        PreparedStatement preparedStatement;  
        byte centinela=0;
        String auxiliar="";
        char[] vectorCharAux;
        String categoria=null;
        String nombre=null;
        String caracteristicas=null;//este valor puede ser nulo en la base de datos
        
        vectorCharAux=categNomCarac.toCharArray();//vector de caracteres del string de la informacion del platillo
        
        for (int i = 0; i < vectorCharAux.length; i++) {            
            if (vectorCharAux[i]==',') {//se ha llegado a un campo distinto 
                switch (centinela){
                    case 0://se ejecutara al llegar a la primera coma ,
                        categoria=auxiliar;
                        auxiliar="";
                        centinela++;
                        break;
                    case 1://segunda coma se corta para asegurar la lectura del siguiente campo
                        nombre=auxiliar;
                        auxiliar="";
                        centinela++;
                        break;
                    default://no es ninguna como por lo cual se añade el texto a la variable  auxiliar 
                        auxiliar+=vectorCharAux[i];                         
                        break;
                }                                                   
            }else{
                auxiliar+=vectorCharAux[i];                
            }                        
        }
        if (centinela==1) {//si la lectura tuvo una unica coma ','
            nombre=auxiliar;//se asegura de leer el campo nombre cuando el dato provisto tuvo una unica coma ','
        }else{
            caracteristicas=auxiliar;
        }                
        
        try { 
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.INSERTAR_VENTA_POR_PLATILLO);
            preparedStatement.setString(1, categoria);
            preparedStatement.setString(2, nombre);
            preparedStatement.setString(3, caracteristicas);
            preparedStatement.setDouble(4, precioPlatillo);
            preparedStatement.setDate(5, fecha);
            preparedStatement.setTime(6, hora);
            preparedStatement.setInt(7, idVenta);//id de la venta a la cual el platillo pertenece
            
            preparedStatement.executeUpdate();//se ejecuta la inserción de datos
            
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    public static ArrayList<Venta> selectSalesRecordAll(){
        ArrayList<Venta> arrayListVenta= new ArrayList<>(50); //array list de todas la ventas
        PreparedStatement preparedStatement;  
        ResultSet resultSet;
        Venta ventaAux;
        
        try {
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.SELECT_ALL_SALES);
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()){
                ventaAux=new Venta( resultSet.getLong(DatosSQL.CAMPO_FECHA_VENTA),
                                    resultSet.getString(DatosSQL.CAMPO_DESCRIPCION_VENTA),
                                    resultSet.getString(DatosSQL.CAMPO_PLATILLOS),
                                    resultSet.getString(DatosSQL.CAMPO_METODO_PAGO),
                                    resultSet.getString(DatosSQL.CAMPO_DESCRIPCION_PAGO),
                                    resultSet.getDouble(DatosSQL.CAMPO_IVA_DESCUENTO),
                                    resultSet.getDouble(DatosSQL.CAMPO_PRECIO_TOTAL),
                                    resultSet.getInt(DatosSQL.CAMPO_ID_VENTA),
                                    resultSet.getLong(DatosSQL.CAMPO_HORA_VENTA),
                                    resultSet.getString(DatosSQL.CAMPO_CLIENTE_NOMBRE), 
                                    resultSet.getInt(DatosSQL.CAMPO_CLIENTE_CEDULA),
                                    resultSet.getString(DatosSQL.CAMPO_TIPO_DE_RIF),
                                    resultSet.getString(DatosSQL.CAMPO_DOMICILIO),
                                    resultSet.getString(DatosSQL.CAMPO_TELEFONO)
                                    );   
                
                arrayListVenta.add(ventaAux);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Ventas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }                
        return arrayListVenta;
    }
    
    public static ArrayList<Venta> selectSalesRedordDateRange(Date dateFrom,Date dateTo){
        ArrayList<Venta> arrayListVenta= new ArrayList<>(30); //array list de todas la ventas
        PreparedStatement preparedStatement;  
        ResultSet resultSet;
        Venta ventaAux;
        try {
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.SELECT_SALES_DATE_RANGE);
            preparedStatement.setDate(1, dateFrom);
            preparedStatement.setDate(2,dateTo);            
            
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()){
                ventaAux=new Venta( resultSet.getLong(DatosSQL.CAMPO_FECHA_VENTA),
                                    resultSet.getString(DatosSQL.CAMPO_DESCRIPCION_VENTA),
                                    resultSet.getString(DatosSQL.CAMPO_PLATILLOS),
                                    resultSet.getString(DatosSQL.CAMPO_METODO_PAGO),
                                    resultSet.getString(DatosSQL.CAMPO_DESCRIPCION_PAGO),
                                    resultSet.getDouble(DatosSQL.CAMPO_IVA_DESCUENTO),
                                    resultSet.getDouble(DatosSQL.CAMPO_PRECIO_TOTAL),
                                    resultSet.getInt(DatosSQL.CAMPO_ID_VENTA),
                                    resultSet.getLong(DatosSQL.CAMPO_HORA_VENTA),
                                    resultSet.getString(DatosSQL.CAMPO_CLIENTE_NOMBRE), 
                                    resultSet.getInt(DatosSQL.CAMPO_CLIENTE_CEDULA),
                                    resultSet.getString(DatosSQL.CAMPO_TIPO_DE_RIF),
                                    resultSet.getString(DatosSQL.CAMPO_DOMICILIO),
                                    resultSet.getString(DatosSQL.CAMPO_TELEFONO)
                                    );                
                arrayListVenta.add(ventaAux);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Ventas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }                
        return arrayListVenta;
               
    }
    /**
     * Borrado logico y fisico de una factura
     * @param id_venta
     * @param reasonOfDelete 
     */ 
    public static void deleteBill(int id_venta,String reasonOfDelete){
        PreparedStatement preparedStatement;  
        
        try {
            preparedStatement= ConexionPlatillosBD.connection.prepareStatement(DatosSQL.INSERT_DELETED_SALE);
            preparedStatement.setString(1, LoginPanel.getLoggedUser().getNombreUsuario());
            preparedStatement.setInt(2, LoginPanel.getLoggedUser().getId());
            preparedStatement.setString(3, reasonOfDelete);
            preparedStatement.setInt(4, id_venta);            
            preparedStatement.executeUpdate();//elminacion logica
            
            preparedStatement= ConexionPlatillosBD.connection.prepareStatement(DatosSQL.DELETE_SALE);
            preparedStatement.setInt(1, id_venta);
            preparedStatement.executeUpdate();//se ejecuta la eliminacion física

        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Ventas.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    /**
     * peticion de los platillos individuales para saber cuales fueron los mas vendidos
     * @param dateFrom
     * @param dateTo
     * @return 
     */
    public static ArrayList<VentaPlatillo> selectDishesDateRange(Date dateFrom,Date dateTo){
        ArrayList<VentaPlatillo> arrayListVenta= new ArrayList<>(30); //array list de todas la ventas
        PreparedStatement preparedStatement;  
        ResultSet resultSet;
        String nombreAux;
        String catAux;
        VentaPlatillo ventaAux=null;
        byte aux=0;
        try {
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.SELECT_DISHES_BY_DATE);
            preparedStatement.setDate(1, dateFrom);
            preparedStatement.setDate(2,dateTo);    
            
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()){
                if (aux==0) {//Se ejecuta solo la primera vez                     
                    ventaAux= new VentaPlatillo(resultSet.getDouble(DatosSQL.CAMPO_PRECIO_PLATILLO),
                                            resultSet.getString(DatosSQL.CAMPO_NOMBRE_PLATILLO),
                                            resultSet.getString(DatosSQL.CAMPO_CATEGORIA_PLATILLO),
                                            1);
                }
                //nombre temporal del ultimo platillo                
                nombreAux=resultSet.getString(DatosSQL.CAMPO_NOMBRE_PLATILLO);
                
                //nombre temporal de la ultima categoria                
                catAux= resultSet.getString(DatosSQL.CAMPO_CATEGORIA_PLATILLO);
                
                //si se ha llegado a una nueva categoria o platillo
                if (!(nombreAux.equals(ventaAux.nombre)&&(catAux.equals(ventaAux.categoria)))) {
                    arrayListVenta.add(ventaAux);
                    ventaAux= new VentaPlatillo(resultSet.getDouble(DatosSQL.CAMPO_PRECIO_PLATILLO),
                                            resultSet.getString(DatosSQL.CAMPO_NOMBRE_PLATILLO),
                                            resultSet.getString(DatosSQL.CAMPO_CATEGORIA_PLATILLO),
                                            1);
                }else{
                    if (aux==0) {//Se ejecuta solo la primera vez
                        aux++;
                    }else{//para evitar que se repita el primero
                        ventaAux.cantidad++;//se aumenta la cantidad de platillos del mismo tipo
                        ventaAux.precioAcumulado+=resultSet.getDouble(DatosSQL.CAMPO_PRECIO_PLATILLO);
                        //precio acumulado de los platillos del mismo tipo
                    }
                    
                }                            
            }
            arrayListVenta.add(ventaAux);//Se añade la ultima venta

        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Ventas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
        return arrayListVenta;
    }
    
    public static ArrayList<VentaDeleted> selectDeletedBills(){
        ArrayList<VentaDeleted> array = new ArrayList<>(20);
        PreparedStatement preparedStatement;  
        ResultSet resultSet;    
        VentaDeleted ventaDelAux;
        try {
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.SELECT_ALL_DELETED_SALES);
            resultSet=preparedStatement.executeQuery();
            
            while(resultSet.next()){
                ventaDelAux=new VentaDeleted( resultSet.getLong(DatosSQL.CAMPO_FECHA_VENTA),
                                    resultSet.getString(DatosSQL.CAMPO_DESCRIPCION_VENTA),
                                    resultSet.getString(DatosSQL.CAMPO_PLATILLOS),
                                    resultSet.getString(DatosSQL.CAMPO_METODO_PAGO),
                                    resultSet.getString(DatosSQL.CAMPO_DESCRIPCION_PAGO),
                                    resultSet.getDouble(DatosSQL.CAMPO_IVA_DESCUENTO),
                                    resultSet.getDouble(DatosSQL.CAMPO_PRECIO_TOTAL),
                                    resultSet.getInt(DatosSQL.CAMPO_ID_VENTA),
                                    resultSet.getLong(DatosSQL.CAMPO_HORA_VENTA),
                                    resultSet.getString(DatosSQL.CAMPO_CLIENTE_NOMBRE), 
                                    resultSet.getInt(DatosSQL.CAMPO_CLIENTE_CEDULA),
                                    resultSet.getString(DatosSQL.CAMPO_TIPO_DE_RIF),
                                    resultSet.getString(DatosSQL.CAMPO_DOMICILIO),
                                    resultSet.getString(DatosSQL.CAMPO_TELEFONO),
                                    resultSet.getString(DatosSQL.CAMPO_USUARIO_DELETER),
                                    resultSet.getInt(DatosSQL.CAMPO_USUARIO_DELETER_ID),
                                    resultSet.getString(DatosSQL.CAMPO_DELETED_REASON)
                                    );                   
                array.add(ventaDelAux);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Ventas.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }                        
        return array;
    }
}
