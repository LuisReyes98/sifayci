package Ve.com.biller.estructuras.reyes;

/**
 *
 * @author Luis Reyes
 * resive dos string diferentes
 * que en la base de datos
 * habra que cambiar el viejo 
 * por el nuevo
 * Esta clase es utilizada por el controlador de cambios del editar menu
 */
public class Cambios {
    String oldName;
    String newName;

    public Cambios(String nombreViejo, String nombreNuevo) {
        this.oldName = nombreViejo;
        this.newName = nombreNuevo;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
    
    
    
}
