package Ve.com.biller.estructuras.reyes;

/**
 *Clase auxiliar utilizada 
 * para saber si una categoria es 
 * de hamburguesa a la hora de 
 * leerla
 * @author Luis Reyes
 */
public class NombreBooleano {
    String name;
    boolean verdadero;

    public NombreBooleano(String name, boolean valor) {
        this.name = name;
        this.verdadero = valor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVerdadero() {
        return verdadero;
    }

    public void setVerdadero(boolean verdadero) {
        this.verdadero = verdadero;
    }
    
}
