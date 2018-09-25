package Ve.com.biller.modelos.reyes;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Luis Reyes
 */
public class ModeloIconosPlatillo {
    
    
    /**
     * Metodo que Coloca los iconos
     * a las categorias segun su nombre     
     * @param nombre
     * @param imagenIcon
     * @param i 
     */
    public static void setIconosCategorias(String nombre,ImageIcon[] imagenIcon,int i){
        try{                
                switch (nombre) {
                    case "HAMBURGUESAS":
                        imagenIcon[i]=new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[1])));//icono de la hamburguesa                                        
                        break;
                    case "CAFÉS":
                        imagenIcon[i]=new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[2])));//icono de la Jarra de Cafe  
                        break;
                    case "MALTEADAS":
                        imagenIcon[i]=new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[3])));//icono de la Malteada 
                        break;
                    case "PLATOS":
                        imagenIcon[i]=new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[4])));//icono de la los Platos  
                        break;   
                    case "ENTRADAS":
                         imagenIcon[i]=new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[5])));//icono de las entradas 
                        break;
                        case "EXTRAS":
                         imagenIcon[i]=new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[6])));//icono de los extras queso amarillo
                        break;
                        case "POSTRES":
                         imagenIcon[i]=new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[7])));//icono de los postres 
                        break;
                        case "BEBIDAS":
                         imagenIcon[i]=new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[8])));//icono de las Bebidas
                        break;
                        
                    default:
                        imagenIcon[i]=new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[0])));//icono por defecto                  
                        break;
                }
            }catch(IOException e){ } 
    }        
    public static void setIconosCafes(String nombre,ImageIcon[] imagenIcon ,int index){
         try{
            switch(nombre){
                case "Espresso":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[0])));
                    break;
                case "Espresso Doble":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[1])));
                    break;
                case "Capuccino":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[2])));
                    break;
                case "Capuccino Doble":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[3])));
                    break;
                case "Latte":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[4])));
                    break;                
                case "Latte Doble":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[5])));
                    break;

                case "Toddy Latte":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[6])));
                    break;

                case "MocaBoom":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[7])));
                    break;

                case "Espresso Limón":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_MALTEADAS[4])));  //limon 
                    break;

                case "Sarrapiando":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[8])));
                    break;

                case "Affogato":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[9])));
                    break;

                case "Americano":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[10])));
                    break;

                case "Cold Brew Frutal":
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[11])));
                    break;                                
                default:
                    imagenIcon[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[0])));//icono por defecto
                    break;
            }
        }catch(IOException ex){}

        
    }    
    public static void setIconosBebidas(String nombre,ImageIcon[] iconos, int index){
        try{      
           switch(nombre){
                    
                case "Agua":    
                      iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_BEBIDAS[0])));//icono por defecto 
                    break;    
                case "Lipton":    
                      iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_BEBIDAS[1])));//icono por defecto 
                    break; 
                case "Refresco":    
                      iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_BEBIDAS[2])));//icono por defecto 
                    break;     
                default:
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[0])));//icono por defecto                
                    break;
                }
                
        }catch(IOException ex){}
                
    }
    
    public static void setIconosPostres(String nombre, ImageIcon[] iconos,int index){
          try{      
            switch(nombre){
                    
                case "Bronwnie":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_POSTRES[0])));//icono brownie   
                    break;    
                case "Torta":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_POSTRES[1])));  //ICONO torta de fresa       
                    break;                                                             
                default:
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[0])));//icono por defecto                
                    break;
                }                
        }catch(IOException ex){}
    }
    
    public static void setIconosHamburguesas(String nombre,ImageIcon[] iconos, int index){
        try{      
           switch(nombre){
                    
                case "Onion":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[0])));//icono de la hamburguesa onion   
                    break;    
                case "Peperonata":    
                iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[5])));       
                    break;  
                case "Manchego Cheese":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[8])));//icono de un queso... ¿manchego?        
                    break;  
                case "Addictions":    
                     iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[9])));            
                    break;  
                case "Sabana":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[4])));          
                    break;  
                case "Bacon":    
                   iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[1])));  //tocineta 
                    break;  
                case "Delicious":    
                   iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[3]))); //icono fresa    
                    break;  
                case "Clásica":    
                 iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[7])));     
                    break;  
                case "Vegetariana Sabana":    
                  iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[6])));         
                    break;  
                case "Vegetariana Onion":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[2])));        
                    break;      
                    
                default:
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[0])));//icono por defecto                
                    break;
                }
                
        }catch(IOException ex){}
    }
    public static void setIconosMalteadas(String nombre,ImageIcon[] iconos, int index){
          try{      
           switch(nombre){
                    
                case "Oreo":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_MALTEADAS[0])));//galleta oreo    
                    break;    
                case "Brownie":    
                iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_MALTEADAS[1])));       
                    break;  
                case "Galletas":    
                     iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_MALTEADAS[2])));         
                    break;  
                case "Mantecado":    
                     iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_MALTEADAS[3])));            
                    break;  
                case "Café":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_CAFES[8])));//mismo icono que sarrapiando         
                    break;  
                case "Fresa":    
                   iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[3]))); //icono fresa igual que delicious  
                    break;  
                case "Pie De Limón":    
                   iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_MALTEADAS[4])));  //limon   
                    break;  
                    
                default:
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[0])));//icono por defecto                
                    break;
                }
                
        }catch(IOException ex){}
    }
    
    public static void setIconosEntradas(String nombre,ImageIcon[] iconos, int index){
          try{      
           switch(nombre){
                    
                case "Alitas de Pollo (6 Uni.)":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_ENTRADAS[0])));//pechuga de pollo    
                    break;    
                case "Dedos de Mozzarella":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[8])));//icono de un queso       
                    break;  
                case "Rustic Sampler":    
                    iconos[index]=new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[5])));//icono de las entradas          
                    break;  
                case "Aros de Cebolla":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_ENTRADAS[1])));//aros de cebolla              
                    break;  
                case "Ración Papas Fritas":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_ENTRADAS[2])));        
                    break;  
                case "Ración Papas Rusticas":    
                  iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_ENTRADAS[3])));  
                    break;
                 case "Ración Yuca":    
                  iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_ENTRADAS[4])));  
                    break;                                        
                default:
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[0])));//icono por defecto                
                    break;
                }
                
        }catch(IOException ex){}
    }
    
    public static void setIconosPlatos(String nombre,ImageIcon[] iconos, int index){
         try{      
            switch(nombre){
                    
                case "Alitas de Pollo (12 Uni.)":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_ENTRADAS[0])));//pechuga de pollo    
                    break;    
                case "Alitas de Pollo (16 Uni.)":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_PLATOS[0])));  //pechuga de pollo 2       
                    break;  
                case "Ensalada Cesar":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_PLATOS[1]))); //ensalada         
                    break;                                                         
                default:
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[0])));//icono por defecto                
                    break;
                }                
        }catch(IOException ex){}
    }
    
    public static void setIconosExtras(String nombre,ImageIcon[] iconos, int index){
        try{      
           switch(nombre){
                    
                case "Champiñon":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_EXTRAS[0])));    
                    break;    
                case "Tocineta":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[1])));  //tocineta       
                    break;  
                case "Jamón Serrano":    
                     iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_EXTRAS[1])));          
                    break;  
                case "Queso Amarillo":    
                    iconos[index]=new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[6])));//icono de los extras queso amarillo              
                    break;  
                case "Queso Manchego":    
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_HAMBURGUESA[8])));//icono de un queso manchego        
                    break;  
                case "Queso Arepero":    
                  iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_EXTRAS[2])));  
                    break;
                 case "Queso Azul":    
                  iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_PLATILLOS_EXTRAS[3])));  
                    break;                                        
                default:
                    iconos[index] = new ImageIcon(ImageIO.read(ModeloIconosPlatillo.class.getResource(DatosBotones.ICONOS_BOTONES_CATEGORIAS[0])));//icono por defecto                
                    break;
                }
                
        }catch(IOException ex){}
    }
}
