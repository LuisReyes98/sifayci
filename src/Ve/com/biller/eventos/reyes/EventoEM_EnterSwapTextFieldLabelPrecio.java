package Ve.com.biller.eventos.reyes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *Como es para un textField 
 * @author Luis Reyes
 */
public class EventoEM_EnterSwapTextFieldLabelPrecio implements ActionListener{
    JTextField textField;
    JLabel label;
    JPanel panel;
    String posicion;

    public EventoEM_EnterSwapTextFieldLabelPrecio(JTextField textField, JLabel label, JPanel panel, String posicion) {
        this.textField = textField;
        this.label = label;
        this.panel = panel;
        this.posicion = posicion;
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //deberia ejecutarse cuando se presiona enter
        String texto=textField.getText();
        Double num=Double.parseDouble(texto.replace(',', '.'));
        label.setText(String.format("%1.2f", num));
        textField.setText(label.getText());
        panel.remove(textField);  
        
        panel.add(label,posicion);        
        panel.revalidate();
        panel.repaint();   
    }
    
}
