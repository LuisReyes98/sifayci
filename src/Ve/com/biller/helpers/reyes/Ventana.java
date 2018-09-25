
package Ve.com.biller.helpers.reyes;

/**
 *
 * @author Luis Reyes
 */





import javax.swing.JFrame;



public class Ventana extends JFrame {
    DialogoCerrar dialogoCerrar;
    
    public Ventana(String[] atributosVentana,Boolean cerrable) {
    super(atributosVentana[0]);
    crearGUI(atributosVentana,cerrable);
            
    }

    private void crearGUI(String[] atributosVentana,Boolean cerrable) {
                  
        this.setSize(Integer.parseInt(atributosVentana[1]),Integer.parseInt(atributosVentana[2]));
        //dandole tamaño a las ventanas
      
        this.setLocationRelativeTo(null);    //Poniendo la ventana en el medio de la pantalla
    
        this.setExtendedState(Integer.parseInt(atributosVentana[3]));//Estado con valores de 0 a 6
    
        // Operaciones necesarias para añadir un fondo a la Ventana
       
        if (cerrable) {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Para cerrar ventana
        
        //los atributos de clase siempre estan en mayuscula 
        }else {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); //Para que el usuario no pueda cerrar la ventana
        }
        
        
       
        this.setVisible(true);    
    }
    
 
    
}
