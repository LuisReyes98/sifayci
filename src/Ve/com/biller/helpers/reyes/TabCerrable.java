

package Ve.com.biller.helpers.reyes;


import Ve.com.biller.eventos.reyes.EventoNOBotonCerrarTab;
import java.awt.BasicStroke;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicButtonUI;



/**
 * Component to be used as tabComponent;
 * Contains a JLabel to show the text and 
 * a JButton to close the tab it belongs to 
 */ 
public class TabCerrable extends JPanel {
    private final JTabbedPane tabPane;

    public TabCerrable(final JTabbedPane pane) {
        //unset default FlowLayout' gaps
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
                
        if (pane == null) {
            throw new NullPointerException("TabbedPane is null");
        }
        this.tabPane = pane;
        
        crearGUI();
    }
    
    private void crearGUI(){
        JButton button;
        JLabel label;
        setOpaque(false);
        
        //make JLabel read titles from JTabbedPane
        label = new JLabel() {
            @Override
            public String getText() {
                int i = tabPane.indexOfTabComponent(TabCerrable.this);
                if (i != -1) {
                    return tabPane.getTitleAt(i);
                }
                return null;
            }
        };
        
        this.add(label);
        //add more space between the label and the button
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        //tab button
        button = new TabButton();

        
        this.add(button);
        //add more space to the top of the component
        this.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        
    }

    private class TabButton extends JButton   {
        
        public TabButton() {
            super();            
            construir();
            
        }
        
        private void construir(){
            
            int size = 17;
            
            this.setPreferredSize(new Dimension(size, size));
            this.setToolTipText("Cancelar esta Orden");
            //Make the button looks the same for all Laf's
            this.setUI(new BasicButtonUI());
           
            
            this.setContentAreaFilled(true);
            //No need to be focusable
            this.setFocusable(false);
            this.setBorder(BorderFactory.createEtchedBorder());
            this.setBorderPainted(false);
            //Making nice rollover effect            
                                 
            this.setRolloverEnabled(true);
            
            //Close the proper tab by clicking the button
            
            
            this.addActionListener(new EventoNOBotonCerrarTab(tabPane, TabCerrable.this));
            this.setVisible(true);
            
        }
        //we don't want to update UI for this button
        @Override
        public void updateUI() {
        }

        //paint the cross
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D g2 = (Graphics2D) g.create();
            //shift the image for pressed buttons
            if (getModel().isPressed()) {
                g2.translate(1, 1);
            }
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.RED);
            setBackground(null);
            this.setOpaque(false);
            if (getModel().isRollover()) {
                 this.setOpaque(true);
                setBackground(Color.red);
                g2.setColor(Color.WHITE);
            }
            
            int delta= 5;
            g2.drawLine(delta, delta,getWidth()-delta, getHeight()-delta);
            g2.drawLine(getWidth()-delta,delta,delta,getHeight()-delta);
                       
            g2.dispose();
        }

        
    }
    
   
}