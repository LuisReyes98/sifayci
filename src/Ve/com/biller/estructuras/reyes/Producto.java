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
public class Producto {
    protected String name; 
    protected  double[] precios= new double[4];       
    protected int id;        
    private boolean is100grHamburguer;
    
    private boolean hasInventory;
    private int ammountInStock;
    private int ammountToTakeFromStock;
    
    private int hasAttributes;
    private int attributesId;
    private AttributesProducto[] attributes;
    
    public Producto(){
        
    }
    
    public Producto(String nombre,double precio0,double precio1,double precio2,double precio3,int id,boolean hamburguesa100gramos,boolean hasInventory,int ammountInStock,int ammountToTakeFromStock)
    {        
        this.name=nombre;
        this.precios[0]=precio0;
        this.precios[1]=precio1;
        this.precios[2]=precio2;
        this.precios[3]=precio3;
        this.id=id;
        this.is100grHamburguer=hamburguesa100gramos;
        
        this.hasInventory=hasInventory;
        this.ammountInStock=ammountInStock;
        this.ammountToTakeFromStock= ammountToTakeFromStock;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrecio(int index) {
         if (index>=0&&index<=precios.length) {
            return precios[index];
        }else{
            return -1;
        } 
        
    }

    public void setPrecio(double precio,int index) {
        if (index>=0&&index<=precios.length) {
            this.precios[index] = precio;
        }        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIs100grHamburguer() {
        return is100grHamburguer;
    }

    public void setIs100grHamburguer(boolean is100grHamburguer) {
        this.is100grHamburguer = is100grHamburguer;
    }
   
        
}
 
