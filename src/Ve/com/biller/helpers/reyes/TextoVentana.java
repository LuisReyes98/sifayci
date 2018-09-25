/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ve.com.biller.helpers.reyes;

import java.awt.Font;
import javax.swing.JTextArea;

/**
 *
 * @author Luis_Reyes
 */
public class TextoVentana  {
    JTextArea textArea;
    Boolean editable;
    String[] texto;
    Font font;
    
    public TextoVentana(String[] texto, Boolean editable,Font font) {
        super();
        this.texto=texto;        
        this.editable=editable;
        this.font=font;               
        crearGUI();
    }

    private void crearGUI() {  
        
      
        textArea= new JTextArea();
        
        if(font!=null){
            textArea.setFont(font); //se coloca al Jtext la fuente previamente enviada
        }
        
       
        for (int i = 0; i < texto.length; i++) {
            textArea.append(texto[i]+" \n");            
        }                           
        textArea.setEditable(editable);
        
       
    }
    
    public void addLinea(String linea){
        textArea.append(linea+"\n");
    }
    
    public void addLineas(String[] lineas){
        for (int i = 0; i < lineas.length; i++) {
            textArea.append(lineas[i]+"\n");
        }
    }
    
    public JTextArea getTextArea(){
        return textArea;
    }
    
}
