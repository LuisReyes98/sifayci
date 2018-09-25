package Ve.com.biller.helpers.reyes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 *
 * @author Luis Reyes
 */
public class ButtonMoreLess extends JButton{

    Color hoverColor;
    Color defaultColor;
    String toolTip1;
    String toolTip2;
    public byte centinela=1;//solo puede tomar valores de 0 y 1 en 1 se muestra un simbolo de mas y en 0 se muestra un simbolo de menos
    Object horizontalLine;
    
    public ButtonMoreLess(Color hoverColor,Color defaultColor,String toolTip1,String toolTip2) {
        super();
        this.hoverColor=hoverColor;
        this.defaultColor=defaultColor;
        this.toolTip1=toolTip1;
        this.toolTip2=toolTip2;
        this.createGUI();
    }
    private void createGUI(){
        final int size=20;
        this.setPreferredSize(new Dimension(size,size));
        this.setToolTipText(toolTip1);
        
        this.setUI(new BasicButtonUI());           
        this.setContentAreaFilled(false);
        this.setFocusable(true);
        this.setBorder(BorderFactory.createEtchedBorder());
        this.setBorderPainted(false);
        //Making nice rollover effect
        
        //we use the same listener for all buttons
        this.setRolloverEnabled(true);   
        
        this.setVisible(true);

    }
    
    @Override
    protected void paintComponent(Graphics g) {
        final byte delta= 15; 
        super.paintComponent(g);
        
        Graphics2D graphics2D = (Graphics2D) g.create();       
        
        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.setColor(defaultColor);//se pinta del color por defecto

        if (this.getModel().isRollover()){               
            graphics2D.setColor(hoverColor);//se cambia a otro color cuando el ususario pone el mouse sobre el boton
            
        }                
        
        if (centinela==0) {//linea de menos mostrandose                        
            graphics2D.drawLine(delta,(this.getHeight()/2),this.getWidth()-delta,(this.getHeight()/2));//linea horizontal  
            this.setToolTipText(toolTip2);
        }else{                    
            //simbolo de mas mostrandose 
            
            graphics2D.drawLine(delta,(this.getHeight()/2),this.getWidth()-delta,(this.getHeight()/2));//linea horizontal
            graphics2D.drawLine((this.getWidth()/2), delta,(this.getWidth()/2), this.getHeight()-delta);//linea vertical  
            this.setToolTipText(toolTip1);

        }
       
        this.repaint();
        this.revalidate();
        graphics2D.dispose();
    }
    
    
}
