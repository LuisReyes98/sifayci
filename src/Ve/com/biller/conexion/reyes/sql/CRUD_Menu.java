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

import Ve.com.biller.estructuras.reyes.Categoria;
import Ve.com.biller.estructuras.reyes.NombreBooleano;
import Ve.com.biller.estructuras.reyes.Producto;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;


public class CRUD_Menu{
    public static Categoria[] menuCategoria; //el dato menu es tan grande como la cantidad de categorias
    public static String[] categorias;  
    
    public static void selectMenu(){       
        
        ArrayList<NombreBooleano> arrayListNombresBool = new ArrayList<>(10);  //se inicializa con un tamaño esperado de 10 categorias a leer        
        Iterator<NombreBooleano> iterarCategorias;
        PreparedStatement preparedStatement;    
        ResultSet resultSet;
        menuCategoria=null;
        categorias=null;
        NombreBooleano item;
        String auxiliar;
        
        int aux=0;
      
        try{
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.BUSCAR_CATEGORIAS);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){ //mientras alla contenido en la peticion sql  
                item=new NombreBooleano(resultSet.getString(DatosSQL.CAMPO_CATEGORIAS),
                                        resultSet.getInt(DatosSQL.CAMPO_HAMBURGUESA)==1);
                arrayListNombresBool.add(item);//se almacenan las categorias                                                                    
                aux++;//contador de categorias
            }      
                                                                        
        }catch(SQLException e){
             Logger.getLogger(CRUD_Menu.class.getName()).log(Level.SEVERE, null, e);
            
        }        
        menuCategoria= new Categoria[aux];//se crea el tipo de dato menu  con la cantidad de categorias correcta
        aux=0;//resetea el valor para reciclarla        
        iterarCategorias = arrayListNombresBool.iterator(); //se declara e instancia el iterador 
       //variable auxiliar para poder lograr la lectura de datos         
        while(iterarCategorias.hasNext()){//guaradando los datos en un formato estatico
            item = iterarCategorias.next();
            auxiliar=item.getName();
            menuCategoria[aux++]= new Categoria(auxiliar,selectProductos(auxiliar),item.isVerdadero());//se llama a la base de datos pidiendo los productos de esa categoria ,para almacenar en el vector             
        }//fin de la iteracion de datos    
        
        //crear el vector de nombres de las categorias 
        categorias= new String[menuCategoria.length]; //se crea un vector de categorias para trabajarlo mejor con lo botones 
        for (int i = 0; i < categorias.length; i++) {
            categorias[i]=menuCategoria[i].getCategoria();
        }
        
    }
    
    
    private static Producto[] selectProductos(String categoria){ //peticion de una serie de productos que pertencen a una misma categoria
        PreparedStatement preparedStatement;    
        ResultSet resultSet;
        Producto[] prod;        
        ArrayList<Producto> productosAL= new ArrayList<>(15); //se inicializa a 15 platillos por categoria    
        Iterator<Producto> iterarProdAL;
        Producto producto;      
        int aux=0;        
        try{
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.BUSCAR_PRODUCTO_CATEGORIA);//peticion de todos los platillos de cierta categoria el ? es la categoria a pedir 
            
            preparedStatement.setString(1,categoria);//se asigna la categoria a pedir en el ?      
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){                                                
                producto = new Producto(resultSet.getString(DatosSQL.CAMPO_NOMBRE_MENU),
                                        resultSet.getDouble(DatosSQL.CAMPO_PRECIO0), 
                                        resultSet.getDouble(DatosSQL.CAMPO_PRECIO1), 
                                        resultSet.getDouble(DatosSQL.CAMPO_PRECIO2),
                                        resultSet.getDouble(DatosSQL.CAMPO_PRECIO3),
                                        resultSet.getInt(DatosSQL.CAMPO_ID_MENU),
                                        resultSet.getInt(DatosSQL.CAMPO_HAMBURGUESA_100GR)==1,
                                        resultSet.getInt(DatosSQL.CAMPO_INVENTARIADO)==1,
                                        resultSet.getInt(DatosSQL.CAMPO_CANTIDA_DE_INVENTARIO),
                                        resultSet.getInt(DatosSQL.CAMPO_UNIDADES_DESCONTAR));  
                productosAL.add(producto);
               
                aux++;                                
            }                    
        }catch(SQLException e){
            Logger.getLogger(CRUD_Menu.class.getName()).log(Level.SEVERE, null, e);
        }
        prod= new Producto[aux];
        aux=0;//se resetea el valor para reciclarla
        iterarProdAL= productosAL.iterator();//iterador de los productos
        while(iterarProdAL.hasNext()){
            producto= iterarProdAL.next();
            prod[aux++]= producto; //los productos se guardan en un entorno estatico
           
        }//fin de la iteracion de datos
                
        return prod;//se retorna un vector de produtos
    }
   
    public static String[] getNombresPlatillos(int index){ //para pedir los nombres de los platillos de una categoria en un vector trabajable
        String[] platillos;
        Producto[] producto;
        producto = menuCategoria[index].getProductos();
        platillos= new String[producto.length];
        for (int i = 0; i < producto.length; i++) {
            platillos[i]= producto[i].getName();
        }        
        return platillos; 
    }
   
    public static void eliminacionLogicaDePlatillo(int id){
      /*
          
    public final String ELIMINACION_LOGICA_PLATILLO= " insert into "+TABLA_ELIMINADOS_MENU +" select * from "+TABLA_MENU+" where id = ? ;";
    //se guarda un respaldo del platillo a borrar en la TABLA_ELIMINADOS_MENU para que la elminacion no sea permanente y a su vez se borra el platillo de la TABLA_MENU
    
    public final String ELIMINACION_FISICA_PLATILLO= "delete from "+TABLA_MENU+" where id = ?;";
    
        */
    
        PreparedStatement preparedStatement;
        
        try {
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.ELIMINACION_LOGICA_PLATILLO);
            
            preparedStatement.setInt(1,id);//ambos valores de la eliminacion se manejan mediante el id
            //preparedStatement.setInt(2,id);
            
            preparedStatement.executeUpdate();
            
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.ELIMINACION_FISICA_PLATILLO);
            preparedStatement.setInt(1,id);//ambos valores de la eliminacion se manejan mediante el id
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }
    
    public static void actualizarPlatillo(String instruccionSQL,double[] preciosNuevos,String nuevoNombre,int idPlatillo,boolean cambioPrecios){                                       
        PreparedStatement preparedStatement;                      
        try{
            int i=1;//se inicializa en 1 por que asi trabaja prepared statement
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(instruccionSQL);              
            //preparedStatement.setString(1,categoria);             
            if (nuevoNombre!=null) {
                preparedStatement.setString(i++, nuevoNombre);//se asigna nombre en 1             
            }
            if (cambioPrecios) {                
                for (int j = 0; j < preciosNuevos.length; j++) {
                    preparedStatement.setDouble(i++, preciosNuevos[j]);
                }
            }
            preparedStatement.setInt(i, idPlatillo);                                
            preparedStatement.executeUpdate();//se ejecuta el update en la base de datos
        }catch(SQLException e){
            Logger.getLogger(CRUD_Menu.class.getName()).log(Level.SEVERE, null, e);
     
        }                      
    }
    
    public static void actualizarCategoria(String nuevoNombreCategoria,String viejoNombreCategoria){
        PreparedStatement preparedStatement;        
        try {
            preparedStatement= ConexionPlatillosBD.connection.prepareStatement(DatosSQL.ACTUALIZAR_NOMBRE_CATEGORIA);
            //" update "+TABLA_MENU+" set "+CAMPO_CATEGORIAS+" = ? where +"CAMPO_CATEGORIAS+" = ? ;"            
            preparedStatement.setString(1, nuevoNombreCategoria.toUpperCase());
            preparedStatement.setString(2, viejoNombreCategoria);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            
        }                
    }
    
    public static void crearNuevoPlatilloCategoria(String categoria,String platillo, double[] precios,boolean esHamburguesa,boolean esHamburguesa_100gr){
        PreparedStatement preparedStatement;
        int aux1=0;
        int aux2=0;
        try {
            preparedStatement= ConexionPlatillosBD.connection.prepareStatement(DatosSQL.ANADIR_NUEVO_PLATILLO_CATEGORIA);//son 8 ?
        //ANADIR_NUEVO_PLATILLO_CATEGORIA=" insert into "+TABLA_MENU+"("+CAMPO_CATEGORIAS+","+CAMPO_NOMBRE_MENU+","+CAMPO_PRECIO0+","+CAMPO_PRECIO1+","+CAMPO_PRECIO2+","+CAMPO_PRECIO3+","+CAMPO_HAMBURGUESA+","+CAMPO_HAMBURGUESA_100GR+","+CAMPO_INVENTARIADO+") values (?,?,?,?,?,?,?,?,?) ;";//se insertan 9 atributos del nuevo platillo al menu 
            preparedStatement.setString(1, categoria);
            preparedStatement.setString(2, platillo);
            preparedStatement.setDouble(3, precios[0]);
            preparedStatement.setDouble(4, precios[1]);
            preparedStatement.setDouble(5, precios[2]);
            preparedStatement.setDouble(6, precios[3]);            
            if (esHamburguesa) {
                aux1=1;
                if (esHamburguesa_100gr) {
                    aux2=1;
                }
            }
            preparedStatement.setInt(7, aux1);//campo clave para saber si es hamburguesa
            preparedStatement.setInt(8, aux2);//campo clave para saber si se comporta como la hamburguesa clasica
            preparedStatement.setInt(9,0);//campo clave para saber si el producto lleva inventario o no
            
            preparedStatement.executeUpdate();//se ejecuta la insercion de datos en la base de datos
            
        } catch (SQLException ex) {
            
        }  
        
    }
}

