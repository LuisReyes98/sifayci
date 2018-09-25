/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.helpers.reyes;

/**
 *
 * @author Luis_Reyes
 */

import Ve.com.biller.modelos.reyes.DatosOrdenesIW;
import Ve.com.biller.vistas.reyes.OrdenPanelNO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.plaf.basic.BasicButtonUI;

public class TabAnadir extends JPanel{
    private final JTabbedPane tabPane;
    
    public TabAnadir(JTabbedPane tabPane) {        
        super(new FlowLayout(FlowLayout.LEFT, 0 , 0));
        
        if (tabPane == null) {
            throw new NullPointerException("TabbedPane is null");
        }
        this.tabPane = tabPane;
        
        crearGUI();
    }
    
    private void crearGUI(){
        this.setOpaque(false);
        JButton button = new AddButton();
        
        this.add(button);
        
        this.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
    }
    
    private class AddButton extends JButton implements ActionListener{

        public AddButton() {
            super();
            construir();
        }
        
        private void construir(){
            final int size= 17;
            this.setPreferredSize(new Dimension(size,size));
            this.setToolTipText("Añadir Orden");
            //Make the button looks the same for all Laf's
            this.setUI(new BasicButtonUI());
           
            this.setContentAreaFilled(false);
            
            this.setFocusable(true);
            this.setBorder(BorderFactory.createEtchedBorder());
            this.setBorderPainted(false);
            //Making nice rollover effect
            //we use the same listener for all buttons
                                  
            this.setRolloverEnabled(true);            
            //Close the proper tab by clicking the button
            this.addActionListener(this);
            this.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            
            int j = tabPane.indexOfTabComponent(TabAnadir.this); //Lugar donde deberia estar el boton añadir
            OrdenPanelNO ordenPanel;
            TabCerrable tabCerrable;
            if (j != -1) {
                ordenPanel= new OrdenPanelNO(tabPane);
                tabCerrable= new TabCerrable(tabPane);                                
                
                tabPane.insertTab(DatosOrdenesIW.TAB_ORDEN[0]+DatosOrdenesIW.i +DatosOrdenesIW.TAB_ORDEN[1], null, ordenPanel,null,j); //3º es el componente interno
                tabPane.setTabComponentAt(j,tabCerrable);
                tabPane.setSelectedIndex(j);
                DatosOrdenesIW.i++;
            }
        }
        //paint the cross
        @Override
        protected void paintComponent(Graphics g) {
            final byte delta= 5;
            super.paintComponent(g);
            
            Graphics2D g2 = (Graphics2D) g.create();
            //shift the image for pressed buttons
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.lightGray);
            
            if (getModel().isRollover()) {               
                g2.setColor(Color.BLUE);
            }
            g2.drawLine((getWidth()/2), delta,(getWidth()/2), getHeight()-delta-1);//linea vertical 
            g2.drawLine(delta,(getHeight()/2),getWidth()-delta-1,(getHeight()/2));//linea horizontal
                       
            g2.dispose();
        }
    }
}
