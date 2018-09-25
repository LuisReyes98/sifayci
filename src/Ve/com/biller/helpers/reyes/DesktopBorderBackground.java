/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.helpers.reyes;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import javax.swing.border.Border;

/**
 *
 * @author Luis_Reyes
 */
public class DesktopBorderBackground implements Border {
// Esta clase prepara la imagen de la ventana para ser agregada a un desktoppane como un borde (fondo)
    //esta imagen siempre estara en el centro de la ventana 
    private BufferedImage img;
    private int ancho;
    private int alto;

    public DesktopBorderBackground(BufferedImage img,int ancho,int alto) {
        this.img=img;        
        this.ancho=ancho;
        this.alto=alto;
                
    }
      
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        int x0 = x+ (width-ancho)/2; //el punto de inicio del ancho
        int y0 = y+ (height-alto)/2; //el punto de inicio de lo alto        
        g.drawImage(img,x0,y0,ancho,alto,null); 
        
    }        

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);      
    }
    
    @Override
    public boolean isBorderOpaque() {
        return true;       
    }

    
    
    
}
