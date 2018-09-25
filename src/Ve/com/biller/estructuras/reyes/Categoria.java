package Ve.com.biller.estructuras.reyes;


/**
 *
 * @author Luis_Reyes
 */
public class Categoria{
    private String categoria;
    private Producto[] productos;    
    private boolean hamburguesa;
    
    
    public Categoria(String categoria, Producto[] productos,boolean hamburguesa) {
        this.categoria = categoria;
        this.productos = productos;
        this.hamburguesa=hamburguesa;
       
    }
              
    public String getCategoria() {
        return categoria;
    }

    public Producto[] getProductos() {
        return productos;
    }

    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }

    public boolean isHamburguesa() {
        return hamburguesa;
    }

    public void setHamburguesa(boolean hamburguesa) {
        this.hamburguesa = hamburguesa;
    }
    
   
    
    
    
}
