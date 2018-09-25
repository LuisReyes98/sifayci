package Ve.com.biller.helpers.reyes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;

/**
 *
 * @author Luis Reyes
 */
public class PrintableJPanel extends JPanel implements Printable {
    
    
    public PrintableJPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public PrintableJPanel(LayoutManager layout) {
        super(layout);
    }

    public PrintableJPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
    }

    public PrintableJPanel() {
    }
        
       

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        
        if (page > 0) { /* We have only one page, and 'page' is zero-based */
            return NO_SUCH_PAGE;
        }
        //methods to scale it to the page size
        // get the bounds of the component
        Dimension dim = this.getSize();
        double cmpHeight = dim.getHeight();
        double cmpWidth = dim.getWidth();
        // get the bounds of the printable area
        double pHeight = pf.getImageableHeight();
        double pWidth = pf.getImageableWidth();
        double pXStart = pf.getImageableX();
        double pYStart = pf.getImageableY();
        /*
        //esta escala permite que se imprima una factura en una hoja completa

            double xRatio = pWidth / cWidth;          
            double yRatio = pHeight / cHeight;
            g2d.scale(xRatio, yRatio);//escalando la imagen para compensar la diferencia de resoulucion con impresora 
        */
        
        /*con esta escala sale mas similar al tamaño que muestra en pantalla                        
            double scaleFactor= Math.min(xRatio, yRatio);
            g2d.scale(scaleFactor, scaleFactor);

        */
        
        double xRatio = pWidth / cmpWidth/2;//escala del panel respecto a la pagina
        double yRatio = pHeight / cmpHeight/2;
                
        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         */
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.translate(pXStart, pYStart);
        
        //escalando la imagen para compensar la diferencia de resoulucion con impresora 
        g2d.scale(xRatio, yRatio);
        
        /* Now print the window and its visible contents */
        this.printAll(g2d);

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    
    public void printComponent() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable( this);
        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                
                /* The job did not successfully complete */
            }
         }
    }
}
