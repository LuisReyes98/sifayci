package Ve.com.biller.vistas.reyes;


import Ve.com.biller.helpers.reyes.WordsOnlyDocFilter;
import Ve.com.biller.modelos.reyes.DatosEditarMenu;
import Ve.com.biller.modelos.reyes.Tipografia;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Luis Reyes
 */
public class DialogoCrearNuevaCategoriaEM extends DialogoCrearNuevoPlatilloEM{
    private JTextField textFieldCategoriaNueva;
    private JLabel labelCategoriaNueva;
     

    public DialogoCrearNuevaCategoriaEM(Component parentComponent,PanelEditMenuIW panelPadre) {
        super(parentComponent,panelPadre);
        crearGUI();
    }

    private void crearGUI() {
        PlainDocument doc;
        esHamburguesa=false;
        esHamburguesa_100gr= false;
        categoria=DatosEditarMenu.CATEGORIA_LABEL_FORMULARIO;//nombre 
        
        labelCategoriaNueva= new JLabel(categoria+DatosEditarMenu.LABELS_FORMULARIO[3]);
        labelCategoriaNueva.setForeground(Color.BLACK);
        labelCategoriaNueva.setFont(Tipografia.ARIAL_16);            

        textFieldCategoriaNueva= new JTextField();
        textFieldCategoriaNueva.setForeground(Color.BLACK);
        textFieldCategoriaNueva.setFont(Tipografia.ARIAL_16);
        doc=(PlainDocument) textFieldCategoriaNueva.getDocument();
        doc.setDocumentFilter(new WordsOnlyDocFilter());
        
        crearLabelAnadirCategoria();//metodo heredado crea el label de la palabra añadir categoria
        crearPanelFormulario();//metodo heredado que crea el panel que contiene el formulario de nombres y precios
        
        panelContenedor.add(labelAnadirCategoria,"cell 0 0 2 1");//"cell column row width height"s
        panelContenedor.add(labelCategoriaNueva,"cell 0 1 1 1,width 100px");//añaniendo el label de la palabra "Categoria"
       
        panelContenedor.add(textFieldCategoriaNueva,"cell 1 1 1 1,width 150px");
        
        panelContenedor.add(panelFormulario,"dock south");
       
        crearDialogo();   
    }
    
    @Override
    public void crearPlatilloNuevo(){ //metodo de creacion del platillo    
        
        categoria=textFieldCategoriaNueva.getText();//nombre de la nueva categoria a agregar
        String auxiliarNombre= textFieldNombre.getText();
          for (int i = 0; i < precios.length; i++) {//iteracion para guardar todos los precios del formulario en un vector
            String aux= textFieldPrecios[i].getText().replace(',', '.');
            precios[i]=Double.parseDouble(aux);
        }     
        if (precios[0]==0||auxiliarNombre.isEmpty()||categoria.isEmpty()) {//comprobante de que los campos no estan vacios
            dialogoAdvertencia();
        }else{
            actualizarMenu(auxiliarNombre);
          
        }
    }
}
