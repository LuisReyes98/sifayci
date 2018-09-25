package Ve.com.biller.eventos.reyes;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Luis Reyes
 */
public class OyenteEMClickEscapeCambiarTextFieldLabel extends KeyAdapter {
    JPanel panelContenedor;//se asume que el panel tiene migLayout
    JTextField textFieldEnFoco;
    JLabel labelFueraDeFoco;
    String posicion;

    public OyenteEMClickEscapeCambiarTextFieldLabel(JPanel panelContenedor, JTextField textFieldEnFoco, JLabel labelFueraDeFoco,String posicion) {
        this.panelContenedor = panelContenedor;
        this.textFieldEnFoco = textFieldEnFoco;
        this.labelFueraDeFoco = labelFueraDeFoco;
        this.posicion=posicion;
    }
           
  

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
            panelContenedor.remove(textFieldEnFoco);
            panelContenedor.add(labelFueraDeFoco,posicion);

            textFieldEnFoco.setText(labelFueraDeFoco.getText());
            panelContenedor.revalidate();
            panelContenedor.repaint();
        }     
    }

   
}
