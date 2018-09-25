
package Ve.com.biller.helpers.reyes;

/**
 * @author Luis Reyes
 * Component consisted of a gruop of buttons
 * Contained in a JPanel 
 * accepts only one constructor
 */


import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JPanel; //importando el panel que lo contiene
import javax.swing.JButton;//importando los botones
import java.awt.event.ActionListener; //importando el oyente de eventos que puede realizar el usuario
import javax.swing.ImageIcon;


public class Botonera extends JPanel {

    protected JButton[] botones;
    protected int cantidad;
    
    
    
    public Botonera(String[] nombreBotones,LayoutManager ly)
    {
        super(ly);
        crearGUI(nombreBotones,null,null);
    }    
   
    
    //Constructor con iconos
    public Botonera(String[] nombreBotones,char[] letras,ImageIcon[] iconos,LayoutManager ly) 
    {
        super(ly); //de esta forma se puede enviar el layout deseado
        crearGUI(nombreBotones,iconos,letras);            
    }
    
    public Botonera(String[] nombreBotones,ImageIcon[] iconos,LayoutManager ly){
        super(ly);
        crearGUI(nombreBotones,iconos,null);            

    }
    
    /**
     * icons,hints can be null
     * @param nombresBotones
     * @param icons
     * @param hints 
     */
    private void crearGUI(String[] nombresBotones,ImageIcon[] icons,char[] hints){
        cantidad=nombresBotones.length;
        botones= new JButton[cantidad];
        for (int i = 0; i < cantidad; i++) {
            botones[i]= new JButton(nombresBotones[i]);//se crea el objeto
            try{
                botones[i].setMnemonic(hints[i]);                
            }catch(NullPointerException a){}
            try{
                botones[i].setIcon(icons[i]);                
            }catch(NullPointerException a){}
            this.add(botones[i]);
            
        }
    
    }
   
    public void setButtonsForeground(Color color){
        for (int i = 0; i < cantidad; i++) {
            botones[i].setForeground(color);            
        }
    }
    public void setButtonsBackground(Color color){
        for (int i = 0; i < cantidad; i++) {
            botones[i].setBackground(color);            
        }
    }
    public void  setButtonEvent(ActionListener evento, int indiceBoton){
        if(indiceBoton>=0 && indiceBoton<cantidad){
            botones[indiceBoton].addActionListener(evento);
        }
    }
    
    public void setButtonsFont(Font fuente){
        for (int i = 0; i < botones.length; i++) {
            botones[i].setFont(fuente);                   
        }
    }
    public JButton getButton(int indexButton){
        if(indexButton>=0 && indexButton<cantidad){
            return botones[indexButton];
        }else{
            return null;
        }
        
    }
    
    public void setButtonIcon(ImageIcon imgIcon,int indiceBoton){
        if(indiceBoton>=0 && indiceBoton<cantidad){
            botones[indiceBoton].setIcon(imgIcon);
        }
    }
    
    
    public void  setToolTipButton(String pista,int indiceBoton){
        if(indiceBoton>=0 && indiceBoton<cantidad){
            botones[indiceBoton].setToolTipText(pista);
        }
    }
    
    public void setButtonsIconsNorth(){
        for (int i = 0; i < cantidad; i++) {
            botones[i].setVerticalTextPosition(JButton.BOTTOM);           
            botones[i].setHorizontalTextPosition(JButton.CENTER);           
        }
    }    
    public void setButtonsIconsEast(){
        for (int i = 0; i < cantidad; i++) {                      
            botones[i].setHorizontalTextPosition(JButton.LEFT);           
        }
    }
    public ImageIcon getIcon(int index){
        ImageIcon icon;
        if(index>=0 && index<cantidad){
            icon= (ImageIcon)botones[index].getIcon();
            return icon;
        }else{
            return null;
        }
            
    }
    public void removeButtonAt(int index){
        if(index>=0 && index<cantidad){
            this.remove(botones[index]);
        }
    }
    
}
