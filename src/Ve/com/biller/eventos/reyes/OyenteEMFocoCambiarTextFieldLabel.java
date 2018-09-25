package Ve.com.biller.eventos.reyes;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Luis Reyes
 */
public class OyenteEMFocoCambiarTextFieldLabel implements FocusListener {

    JPanel panelContenedor;//se asume que el panel tiene migLayout
    JTextField textFieldEnFoco;
    JLabel labelFueraDeFoco;
    String posicion;

    public OyenteEMFocoCambiarTextFieldLabel(JPanel panelContenedor, JTextField textFieldEnFoco, JLabel labelFueraDeFoco, String posicion) {
        this.panelContenedor = panelContenedor;
        this.textFieldEnFoco = textFieldEnFoco;
        this.labelFueraDeFoco = labelFueraDeFoco;
        this.posicion = posicion;
    }
        
    @Override
    public void focusGained(FocusEvent e) {
        
    }

    @Override
    public void focusLost(FocusEvent e) {
        panelContenedor.remove(textFieldEnFoco);
        panelContenedor.add(labelFueraDeFoco,posicion);
        textFieldEnFoco.setText(labelFueraDeFoco.getText());
        panelContenedor.revalidate();
        panelContenedor.repaint();
    }
    
}
