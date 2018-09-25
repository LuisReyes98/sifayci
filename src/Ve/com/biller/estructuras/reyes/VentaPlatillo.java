/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.estructuras.reyes;

/**
 *
 * @author luisr
 */
public class VentaPlatillo {
    public double precioAcumulado;
    public String nombre;
    public String categoria;
    public int cantidad;

    public VentaPlatillo(double precioAcumulado, String nombre, String categoria, int cantidad) {
        this.precioAcumulado = precioAcumulado;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }
    
    
    
}
