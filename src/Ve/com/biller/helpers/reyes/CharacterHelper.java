/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.helpers.reyes;

/**
 *
 * @author Luis Reyes
 */
public class CharacterHelper {
    
    
    public static String lineMaker(String caracter,int repetir){
        String espacio="";
    
        for (int i = 0; i < repetir; i++) {
            espacio+=caracter;
        }
        
        return espacio;
    
    }
        
}

