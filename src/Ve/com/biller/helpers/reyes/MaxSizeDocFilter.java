package Ve.com.biller.helpers.reyes;

import javax.swing.JOptionPane;

/**
 *
 * @author Luis Reyes
 */
public class MaxSizeDocFilter extends CustomDocFilter {

    long maxSize;
    /**
     * doc fillter que limita el numero de caracteres permitidos
     * @param maxSize 
     */
    public MaxSizeDocFilter(long maxSize) {
        this.maxSize = maxSize;
    }
    
    @Override
    public boolean test(String word) {
        
        if (word.toCharArray().length>maxSize) {
            JOptionPane.showMessageDialog(null, "No pude superar los "+maxSize+"caracteres","Límite Excedido", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
}
