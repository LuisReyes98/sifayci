package Ve.com.biller.estructuras.reyes;

/**
 *
 * @author Luis Reyes
 */
public class User {
    
    private final String nombreUsuario;
    private final String claveUsuario;
    private final int id;
    private final boolean admin;
    

    public User(String nombreUsuario, String claveUsuario, int id, boolean admin) {
        this.nombreUsuario = nombreUsuario;
        this.claveUsuario = claveUsuario;
        this.admin = admin;
        this.id = id;
    }        

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public int getId() {
        return id;
    }

    public boolean isAdmin() {
        return admin;
    }

    
    
    
}
