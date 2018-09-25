package Ve.com.biller.vistas.reyes;

import Ve.com.biller.helpers.reyes.ImageManager;
import Ve.com.biller.modelos.reyes.DatosRegistroVentas;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.Color;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *Header de las facturas
 * @author Luis Reyes
 */
public final class BillHead extends JPanel{    
    
    private JLabel[] labelsMembrete= new JLabel[5];
    private JLabel labelIcono;
    public BillHead() {     // n w s e
        super(new MigLayout("insets 15 15 15 15"));
        create();
    }
    private void create(){
        
        this.setBackground(Color.WHITE);
        this.setOpaque(true);

        try{
            labelIcono= new JLabel(ImageManager.iconResize(new ImageIcon(ImageIO.read(BillHead.class.getResource(DatosRegistroVentas.LOGO))), 64, 64));
        }catch(IOException e){}
        for (int i = 0; i < 5; i++) {
            labelsMembrete[i]=new JLabel(DatosRegistroVentas.MEMBRETE_FACTURA[i],JLabel.CENTER);
            labelsMembrete[i].setForeground(Color.BLACK);

        }
        labelsMembrete[0].setFont(Tipografia.ARIAL_BOLD_25);
        
        labelsMembrete[1].setFont(Tipografia.ARIAL_10);
        labelsMembrete[2].setFont(Tipografia.ARIAL_10);
        labelsMembrete[3].setFont(Tipografia.ARIAL_10);
        labelsMembrete[4].setFont(Tipografia.ARIAL_BOLD_10);

                            //          "cell column row width height"
        this.add(labelIcono,            "cell 0 0 1 2");
        //primer membrete
        this.add(labelsMembrete[0],     "cell 1 0 2 1,growx");
        this.add(labelsMembrete[1],     "cell 1 1 2 1,growx");
        //segundo membrete y direcciones 
        this.add(labelsMembrete[2],     "cell 0 2 2 1,growx");
        this.add(labelsMembrete[3],     "cell 0 3 3 1,growx");
        this.add(labelsMembrete[4],     "cell 0 4 3 1,growx");
        
        
        this.setVisible(true);
    }
    
}
