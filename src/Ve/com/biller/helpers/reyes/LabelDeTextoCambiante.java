package Ve.com.biller.helpers.reyes;

import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author Luis Reyes
 */
public class LabelDeTextoCambiante extends JLabel{
    private String originalText;
    public LabelDeTextoCambiante(String text, Icon icon, int horizontalAlignment) {       
        super(text, icon, horizontalAlignment);
        originalText=text;
    }

    public LabelDeTextoCambiante(String text, int horizontalAlignment) {        
        super(text, horizontalAlignment);
        originalText=text;
    }

    public LabelDeTextoCambiante(String text) {
        super(text);
        originalText=text;
    }
    
    
    
    public boolean hasChangedText(){
        //si son distintos el equals sera falso por la tanto habra que invertirlo para poder ver si ha cambiado
        return !(this.getText().equals(originalText));
    }

    public String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }
    
    
    

    
    
}
