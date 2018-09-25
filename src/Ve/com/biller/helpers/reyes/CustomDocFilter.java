package Ve.com.biller.helpers.reyes;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Luis Reyes
 */
public abstract class CustomDocFilter extends DocumentFilter {

    public CustomDocFilter() {
    }
    
    
    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.delete(offset, offset + length);       
        String aux=sb.toString();     
        if (test(aux)) {
            super.remove(fb, offset, length);
        } else {
         
         // warn the user and don't allow the insert
        }

    }
    
    @Override
    public void insertString(FilterBypass fb, int offset, String string,
            AttributeSet attr) throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.insert(offset, string);
        String aux=sb.toString();
     
        
        if (test(aux)) {
            super.insertString(fb, offset, string, attr);
        } else {
            
         // warn the user and don't allow the insert
        }
        
        
    }


    @Override
    public void replace(FilterBypass fb, int offset, int length, String text,
                        AttributeSet attrs) throws BadLocationException {
        Document doc = fb.getDocument();
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getText(0, doc.getLength()));
        sb.replace(offset, offset + length, text);
        String aux=sb.toString();        
    
        if (test(aux)) {
            super.replace(fb, offset, length, text, attrs);
        } else {
            
         // warn the user and don't allow the insert
        }
        
    }
    
    public abstract boolean test(String word);
}
