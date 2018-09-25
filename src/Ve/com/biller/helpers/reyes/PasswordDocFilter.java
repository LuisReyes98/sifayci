package Ve.com.biller.helpers.reyes;

import javax.swing.JOptionPane;


/**
 * La clase permite el uso 
 * de letras y numeros
 * no permite el uso de espacios
 * ni de ningun caracter especial
 * @author Luis Reyes
 */
public class PasswordDocFilter extends CustomDocFilter{
    /**
     * Clase filtro que solo permite letras minusculas mayusculas 
     */
    public PasswordDocFilter() {
    }
    
    @Override
    public boolean test(String word) {
        char[] charVector= word.toCharArray();       
        for (int i = 0; i < charVector.length ; i++) {
            if (!((charVector[i]>=97&&charVector[i]<=122)||(charVector[i]>=65&&charVector[i]<=90)
                    ||(charVector[i]>=48&&charVector[i]<=57))) {
                JOptionPane.showMessageDialog(null, "Solo se permite el uso de números (0-9),"
                                                    + "\n letras mayusculas (A) y minusculas (a)."
                                                    , "Advertencia", 
                                                    JOptionPane.WARNING_MESSAGE);
                return false;
                
            }
        }        
        return true;
    }
    
}
