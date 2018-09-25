package Ve.com.biller.helpers.reyes;

import java.awt.Component;
import java.awt.Dimension;

/**
 *
 * @author Luis Reyes
 */
public class RepositionHelper {
    
    public static final void positionMiddle(Component son,Component parent){        
        Dimension parentSize= parent.getSize();        
        Dimension sonSize= son.getSize();        
        int x = (parentSize.width/2 - sonSize.width/2);
        int y = 0;
        son.setLocation(x, y);                     
    }
}
