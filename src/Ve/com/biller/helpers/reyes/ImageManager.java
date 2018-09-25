package Ve.com.biller.helpers.reyes;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Luis Reyes
 */
public class ImageManager {
    
    
    public static ImageIcon iconResize(ImageIcon imgIcon, int x, int y){
        ImageIcon imgIconResize = null;
        try{
            Image img = imgIcon.getImage();
            Image newImg= img.getScaledInstance(x, y, Image.SCALE_SMOOTH);        
            imgIconResize=new ImageIcon(newImg);        
        }catch(NullPointerException ex){}
        
        return imgIconResize;
    }
    /**
     * the panel must have a setSize before being send to this method
     * @param panel
     * @return 
     */
    public static BufferedImage fromJPanelToImage(JPanel panel) {
        
        int w = panel.getWidth();
        int h = panel.getHeight();                
        
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2D = bi.createGraphics();
        panel.paint(g2D);
        panel.paintAll(g2D);
        

        g2D.dispose();
        return bi;
    }
    
    
}
