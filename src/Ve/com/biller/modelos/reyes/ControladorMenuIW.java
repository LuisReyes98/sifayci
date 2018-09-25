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

import Ve.com.biller.conexion.reyes.sql.CRUD_Menu;
import Ve.com.biller.estructuras.reyes.Producto;
import Ve.com.biller.helpers.reyes.CharacterHelper;

public class ControladorMenuIW {
    public static String[] menu;
    
    public static void CrearMenu(){
        menu=null;
        
        Producto[] productos;
        int lineas=0;
        for (int i = 0; i < CRUD_Menu.menuCategoria.length; i++) {
            lineas+=3;
            productos=CRUD_Menu.menuCategoria[i].getProductos();
            lineas+=productos.length;
        }
        menu = new String[lineas];
        lineas=0;
        for (int i = 0; i < CRUD_Menu.menuCategoria.length; i++) {
            menu[lineas]=CharacterHelper.lineMaker("¯",100);
            menu[++lineas]=String.format("  %-5s",CRUD_Menu.menuCategoria[i].getCategoria());//Categoria               
            menu[++lineas]=CharacterHelper.lineMaker("¯",100);//se pasa inmediatamente a la siguiente posicion
            productos=CRUD_Menu.menuCategoria[i].getProductos();
            for (int j = 0; j < productos.length; j++) {     
                if (productos[j].getPrecio(3)==0) {
                    if (productos[j].getPrecio(2)==0) {
                        if (productos[j].getPrecio(1)==0) {
                            menu[++lineas]=String.format(" %-30s %15.2f ",//caso no hay precio 2,3 y 4
productos[j].getName(),
                            productos[j].getPrecio(0));
                        }else{
                            menu[++lineas]=String.format(" %-30s %15.2f %15.2f",//caso no hay precio 3 y 4
productos[j].getName(),
                            productos[j].getPrecio(0),
                            productos[j].getPrecio(1));
                        }                        
                    }else{
                        menu[++lineas]=String.format(" %-30s %15.2f %15.2f %15.2f", //caso no hay precio 4 
productos[j].getName(),
                            productos[j].getPrecio(0),
                            productos[j].getPrecio(1),
                            productos[j].getPrecio(2));
                    }
                }else{
                    menu[++lineas]=String.format(" %-30s %15.2f %15.2f %15.2f %15.2f", //caso estan los 4 precios
productos[j].getName(),
                            productos[j].getPrecio(0),
                            productos[j].getPrecio(1),
                            productos[j].getPrecio(2),
                            productos[j].getPrecio(3));//se le da formato a la linea
                }
                
                
            }
            lineas++;
        }                
        
    }

    
    
}
