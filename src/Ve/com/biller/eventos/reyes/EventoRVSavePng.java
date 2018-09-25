package Ve.com.biller.eventos.reyes;

import Ve.com.biller.helpers.reyes.FileTypeFilter;
import Ve.com.biller.helpers.reyes.ImageManager;
import Ve.com.biller.vistas.reyes.BillHead;
import Ve.com.biller.vistas.reyes.HideShowPanelRV;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Luis Reyes
 */
public class EventoRVSavePng implements ActionListener {
    String extension=".png";
    HideShowPanelRV hideShowPanelRV;
    FileTypeFilter jpgFilter= new FileTypeFilter(extension, "Imagenes");
    
    JOptionPane jOption;//deben ser atributos de clase para no haber error de seguridad
    JDialog jDialog;//deben ser atributos de clase para no haber error de seguridad
    
    public EventoRVSavePng(HideShowPanelRV panelRV) {
        this.hideShowPanelRV = panelRV;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
//        final int w=410;
        File file;
        JFileChooser fileChooser;       
        BufferedImage buffer;
        JPanel panelContainer;
        JPanel panelBill;
        JPanel panelHeader;
        
        
        panelHeader= new BillHead();
        panelHeader.setPreferredSize(panelHeader.getPreferredSize());
       
        panelBill=hideShowPanelRV.getCopyPrintablePanel();
        
        panelContainer= new JPanel(new BorderLayout());
        
        panelContainer.add(panelBill,BorderLayout.CENTER);//se añade la factura
        panelContainer.add(panelHeader,BorderLayout.NORTH);//se añade el membrete
        panelContainer.setVisible(true);
        
        jOption= new JOptionPane(panelContainer);//se crea un dialogo
        jDialog = jOption.createDialog("Lorem");//se carga el dialogo y no se hace visible
        //la carga del dialogo es para poder obtener los tamaños del panel a la hora de guardarlo como imagen
       
        buffer= ImageManager.fromJPanelToImage(panelContainer);//el panel se tranforma en imagen
                        
        fileChooser= new JFileChooser(){
            //Clase anonima para evitar sobreescribir archivos por el usuario
            @Override
            public void approveSelection(){
                File f = getSelectedFile();
                
                if(f.exists() && getDialogType() == SAVE_DIALOG){
                    int result = JOptionPane.showConfirmDialog(this,"El archivo existe, sobreescribirlo?","Archivo existente",JOptionPane.YES_NO_OPTION);
                    switch(result){
                        case JOptionPane.YES_OPTION:
                            super.approveSelection();
                            return;
                        case JOptionPane.NO_OPTION:
                            return;
                        case JOptionPane.CLOSED_OPTION:
                            return;
                        case JOptionPane.CANCEL_OPTION:
                            //cancelSelection();
                            return;
                        default:
                            return;
                    }
                }
                super.approveSelection();
            }        
        };//se crea el filechooser
        
        file= new File(fileChooser.getSelectedFile(),"factura_"+hideShowPanelRV.getIdventa());//se predefine el nombre del archivo 
        fileChooser.setSelectedFile(file);
        
        fileChooser.setFileFilter(jpgFilter);//se le añade el filtro de imagenes
        fileChooser.setDialogTitle("Guardar factura");//nombre del dialogo
        
        int retVal=fileChooser.showSaveDialog(null);//se muestra el dialogo de guardado
        
        if (retVal== JFileChooser.APPROVE_OPTION) {   //si el usuario desea guardar la imagen         
            try {
                String filePath=fileChooser.getSelectedFile().getAbsolutePath();
                if (filePath.endsWith(extension)) {
                    //guardado de archivo con extension
                    ImageIO.write(buffer, "png",fileChooser.getSelectedFile());//ya que el archivo ya tiene la extension este se guarda inmediatamente 
                }else{
                    //guardado de archivo sin extension
                    ImageIO.write(buffer, "png", new File(filePath+extension));//se le añade la extension del archivo antes de escribirlo
                }                
            } catch (IOException ex) {
              
            }
        }
        jDialog.dispose();
        jDialog=null;
        jOption=null;
    }
    
}
