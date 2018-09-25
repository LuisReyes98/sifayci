/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.estructuras.reyes;

/**
 *
 * @author Luis_Reyes
 */
public class SidesHamburguesa {
    private String label;
    private String[] descripciones;

    public SidesHamburguesa() {
    }

    
    public SidesHamburguesa(String label, String[] descripciones) {
        this.label = label;
        this.descripciones = descripciones;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String[] getDescripciones() {
        return descripciones;
    }

    public void setDescripciones(String[] descripciones) {
        this.descripciones = descripciones;
    }
    
}
