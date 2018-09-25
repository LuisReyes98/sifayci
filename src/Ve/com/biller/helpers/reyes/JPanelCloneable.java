package Ve.com.biller.helpers.reyes;

import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 *
 * @author Luis Reyes
 */
public class JPanelCloneable extends JPanel implements Cloneable{

    public JPanelCloneable(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public JPanelCloneable(LayoutManager layout) {
        super(layout);
    }

    public JPanelCloneable(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public JPanelCloneable() {
    }
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        try {
            return (JPanelCloneable)super.clone();
        }catch (CloneNotSupportedException e){
            
            //e.printStackTrace();
            return null;
        }
        
    }
}   
