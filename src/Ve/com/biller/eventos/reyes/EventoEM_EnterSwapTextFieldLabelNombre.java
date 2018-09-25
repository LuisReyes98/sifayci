package Ve.com.biller.eventos.reyes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Luis Reyes
 */
public class EventoEM_EnterSwapTextFieldLabelNombre implements ActionListener{
    JTextField textField;
    JLabel label;
    JPanel panel;
    String posicion;

    public EventoEM_EnterSwapTextFieldLabelNombre(JTextField textField, JLabel label, JPanel panel, String posicion) {
        this.textField = textField;
        this.label = label;
        this.panel = panel;
        this.posicion = posicion;
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //deberia ejecutarse cuando se presiona enter
        
        label.setText(textField.getText());       
        panel.remove(textField);  
        
        panel.add(label,posicion);        
        panel.revalidate();
        panel.repaint();   
    }
}
