/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.conexion.reyes.sql;

import Ve.com.biller.estructuras.reyes.SidesHamburguesa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Luis_Reyes
 */
public class CRUD_Atributos {    
    public static SidesHamburguesa componentesHamburguesa[];//Columna 1 tipo , Columna 2 vector de nombres de los componentes de la hamburguesa en ese tipo 
    
    
       
    
    public static void selectTipos(){
        ArrayList<String> tiposH = new ArrayList<>(3);  //Array de lectura de datos
        Iterator<String> iterar;
        PreparedStatement preparedStatement;    
        ResultSet resultSet;
        String label; //variable auxiliar para poder lograr la lectura de datos
        
        componentesHamburguesa=null;
        int size=0;
        
          try{
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.BUSCAR_TIPOS);
            resultSet = preparedStatement.executeQuery();            
            while(resultSet.next()){ //mientras alla contenido en la peticion sql                               
               tiposH.add(resultSet.getString(DatosSQL.CAMPO_ATRIBS_NAME));//se almacenan las categorias    
               size++;//cantidad de tipos de elementos 
            }                                                                              
        }catch(SQLException e){
             Logger.getLogger(CRUD_Menu.class.getName()).log(Level.SEVERE, null, e);
            
        }        
        componentesHamburguesa= new SidesHamburguesa[size];
      
        
        size=0;//resetea el valor para reciclarla                
        iterar = tiposH.iterator(); //se declara e instancia el iterador                
        while(iterar.hasNext()){//guaradando los datos en un formato estatico
            label=iterar.next();
            componentesHamburguesa[size++]= new SidesHamburguesa(label, selectDescripcion(label));
            
        }//fin de la iteracion de datos
        
        
    }
    
    private static String[] selectDescripcion(String name){
        String[] descripcion;
        PreparedStatement preparedStatement;    
        ResultSet resultSet;              
        ArrayList<String> array= new ArrayList<>(5); //se inicializa    
        Iterator<String> iterar;
        String item;
        int size=0;   
        
        try{
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.BUSCAR_DESCRIPCION_TIPOS);//peticion de todas las descripciones de un tipo
            
            preparedStatement.setString(1,name);//se asigna la categoria a pedir en el ? 
            
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){                                                
                array.add(resultSet.getString(DatosSQL.CAMPO_DESCRIPCION));
                size++;                                
            }                    
        }catch(SQLException e){
            Logger.getLogger(CRUD_Menu.class.getName()).log(Level.SEVERE, null, e);
        }
        descripcion= new String[size];
        size=0;//se resetea el valor para reciclarla
        iterar = array.iterator();
        while(iterar.hasNext()){
            item= iterar.next();
            descripcion[size++]= item; //los productos se guardan en un entorno estatico
           
        }//fin de la iteracion de datos
                
        return descripcion;
    }
    public static boolean deleteAtribute(String name,String description){
        PreparedStatement preparedStatement;    

        try {
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.DELETE_ATRIBUTES);//borrar Atributos
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Atributos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public static void createAtribute(String type,String name){
        PreparedStatement preparedStatement;    

        try {
            preparedStatement=ConexionPlatillosBD.connection.prepareStatement(DatosSQL.INSERT_ATRIBUTES);//borrar Atributos
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, name);
            
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CRUD_Atributos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
