/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ve.com.biller.modelos.reyes;

/**
 *
 * @author Luis_Reyes
 */
public interface InterfaceBotones {
    
    public final String[] NOMBRES_BOTONES_MAIN={
        "Ver Menú",       
        "Editar Menú",
        "Registro De Ventas",
        "Nueva Orden",   
       // "Inventario",  
        
        
};   
    public final char[] LETRAS_BOTONES_MAIN={
        'v',
        'e',
        'r',
        'n',
        'i'
        
    };
    public final String[] BOTON_SALIR={
        "Salir"
    };
    public final char[] LETRA_SALIR={
        's'  
    };
    
    public final String[] ICONOS_BOTONES_MAIN={
        "/Ve/com/biller/imagenes/054-restaurant.png",//icono ver menu
        "/Ve/com/biller/imagenes/010-interface-2.png",//icono Editar menu
        "/Ve/com/biller/imagenes/024-coins-2.png",//icono Registro de Ventas
        "/Ve/com/biller/imagenes/075-clipboard.png",//Icono nueva Orden
        "/Ve/com/biller/imagenes/094-transport.png",//Icono Inventario
        "/Ve/com/biller/imagenes/003-close.png",//Icono Salir
    };
     
    
    public final String[] NOMBRES_BOTONES_ORDENES={
        "Añadir Platillo",
        "Remover Platillo",
        "Terminar Orden",        
        "Cancelar Orden",
        
    };
    
    public final char[] LETRAS_BOTONES_ORDENES={
        'A',
        'R',
        'T',
        'C'
    };
            
    public final String[] ICONOS_BOTONES_ORDENES={
        "/Ve/com/biller/imagenes/021-plus.png",//Icono de añadir
        "/Ve/com/biller/imagenes/022-draw.png",//icono de remover platillo
        "/Ve/com/biller/imagenes/005-interface-3_1.png",//Icono terminar orden        
        "/Ve/com/biller/imagenes/009-delete.png",//Icono de Cancelar Orden
    };
    
    
    public final String[] ICONOS_BOTONES_DESCRIPCION_PLATILLO={
        "/Ve/com/biller/imagenes/037-circular.png",//icono de visto bueno
    };
    
    public final String[] ICONOS_BOTONES_CATEGORIAS={
        "/Ve/com/biller/imagenes/na-corregidax128.png",//Icono por defecto
        "/Ve/com/biller/imagenes/020-food-20.png",//Icono de Hamburguesa
        "/Ve/com/biller/imagenes/094-food-8.png",//Icono jarra de Café
        "/Ve/com/biller/imagenes/068-milkshake-2.png",//Icono de la Malteada
        "/Ve/com/biller/imagenes/022-circle.png",//Icono de  Platos (Plato con cuchillo y tenedor)
        "/Ve/com/biller/imagenes/097-potatoes.png",//Icono de las entradas
        "/Ve/com/biller/imagenes/022-cheese-2.png",//Icono del queso de los extras
        "/Ve/com/biller/imagenes/015-food-7.png",//Icono de los postres 
        "/Ve/com/biller/imagenes/082-drink-1.png",//Icono de las Bebidas 
    };
    public final String[] ICONOS_PLATILLOS_CAFES={//muchos iconos de café
        "/Ve/com/biller/imagenes/009-cup.png",//icono cafe 0  
        "/Ve/com/biller/imagenes/008-food-11.png",//1
        "/Ve/com/biller/imagenes/003-coffee-3.png",
        "/Ve/com/biller/imagenes/090-food-9.png",
        "/Ve/com/biller/imagenes/008-coffee.png",
        "/Ve/com/biller/imagenes/009-coffee-cup-1.png",
        "/Ve/com/biller/imagenes/014-coffee.png",
        "/Ve/com/biller/imagenes/016-food-22.png",       
        "/Ve/com/biller/imagenes/091-drink.png",
        "/Ve/com/biller/imagenes/011-coffee-cup.png",                
        "/Ve/com/biller/imagenes/087-food-11.png",
        "/Ve/com/biller/imagenes/013-coffee-1.png",
    };
    
    public final String[] ICONOS_PLATILLOS_HAMBURGUESA={
        "/Ve/com/biller/imagenes/035-onion.png",//Icono de cebolla (Hamburguesa ONION) 
        "/Ve/com/biller/imagenes/007-food-12.png",//bacon
        "/Ve/com/biller/imagenes/034-onion-1.png",//vegetariana onion morada
        "/Ve/com/biller/imagenes/030-strawberry-1.png",//fresa delicious
        "/Ve/com/biller/imagenes/033-avocado.png",//aguacate sabana
        "/Ve/com/biller/imagenes/021-pepper.png",//Pimenton peperonata
        "/Ve/com/biller/imagenes/027-lettuce.png",//Vegetariana Sabana lechuga
        "/Ve/com/biller/imagenes/010-food-1.png",//clasica hamburguesa normal
        "/Ve/com/biller/imagenes/073-cheese.png",//queso manchego
        "/Ve/com/biller/imagenes/023-pineapple-1.png",//piña addictions

    };
    public final String[] ICONOS_PLATILLOS_ENTRADAS={
        "/Ve/com/biller/imagenes/008-chicken-leg-1.png",//pechuga de pollo
        "/Ve/com/biller/imagenes/046-onion-rings-1.png",//aros de cebolla
        "/Ve/com/biller/imagenes/036-french-fries.png",//papas fritas
        "/Ve/com/biller/imagenes/076-potatoes-1.png",//PapasRusticas
        "/Ve/com/biller/imagenes/058-food-18.png",//yuca frita
    };
    public final String[] ICONOS_PLATILLOS_EXTRAS={
        "/Ve/com/biller/imagenes/029-mushroom.png",//champiñon hongo
        "/Ve/com/biller/imagenes/006-food.png",//jamon cocido ( pierna ) 
        "/Ve/com/biller/imagenes/003-cheese-2.png",//queso en rebanadas        
        "/Ve/com/biller/imagenes/002-cheese-4.png",//queso azul 
    };
    public final String[] ICONOS_PLATILLOS_PLATOS={
        "/Ve/com/biller/imagenes/006-food-24.png",//icono de pechuga de pollo 2
        "/Ve/com/biller/imagenes/085-salad.png",//ensalada con trozos en el aire
    };
    public final String[] ICONOS_PLATILLOS_POSTRES={
        "/Ve/com/biller/imagenes/064-brownie.png",//icono de brownie
        "/Ve/com/biller/imagenes/086-food-12.png",//icono de torta de fresa
    };
    public final String[] ICONOS_PLATILLOS_MALTEADAS={
        "/Ve/com/biller/imagenes/045-cookie.png",//oreo
        "/Ve/com/biller/imagenes/070-milkshake-1.png",//malteda marron
        "/Ve/com/biller/imagenes/120-cookies.png",//galleta mordida
        "/Ve/com/biller/imagenes/002-ice-cream.png",//helado mantecado
        "/Ve/com/biller/imagenes/005-lemon-2.png",//icono de limon 
        
    };
    public final String[] ICONOS_PLATILLOS_BEBIDAS={
        "/Ve/com/biller/imagenes/074-water.png",//agua
        "/Ve/com/biller/imagenes/006-drink.png",//lipton
        "/Ve/com/biller/imagenes/077-can.png",//refresco
    };
    
    public final String[] NOMBRES_REMOVER_PLATILLO={
        "Remover Platillo",
        "Volver",
        "",        
    };
    
    public final String[] ICONOS_BOTONES_REMOVER_PLATILLO={
        "/Ve/com/biller/imagenes/050-pen.png",//icono del lapiz para la botonera de remover platillos
        "/Ve/com/biller/imagenes/119-arrows-2.png",//Icono de volver a la botonera anterior
        "/Ve/com/biller/imagenes/001-question-sign-1.png"//icono signo de interrogacion
    };
    public final String PISTA_REMOVER_PLATILLO="Ayuda";
    
    public final String[] NOMBRES_BOTONES_ORDEN_TERMINADA={
            "Cerrar Pedido",  
    };
    public final String ICONOS_BOTON_ORDEN_TERMINADA="/Ve/com/biller/imagenes/118-arrows-1.png";//cuadro de salida de cerrar el pedido
        
    //Informacion correspondiente al editar menu
    
    public final String[] ICONOS_BOTONES_EDITAR_MENU={
        "/Ve/com/biller/imagenes/003-restaurant.png",//icono de cuchillo y tenedor para representar las categorias
        "/Ve/com/biller/imagenes/003-save.png",//icono del disquete de guardar
        
    };
               
    public final String[] NOMBRES_BOTONES_EDITAR_MENU_CATEGORIA={
        "CATEGORIAS",
    };
    
    public final String[] NOMBRES_BOTONES_EDITAR_MENU={
        "Guardar",
        "Salir",
    };
    
    public final String[] NOMBRES_BOTONES_ANADIR_CATEGORIA={
        "Añadir Categoria",
        
    };
    
    public final String ICONOS_BOTONES_ANADIR_CATEGORIA= "/Ve/com/biller/imagenes/005-plus.png";
    
    public final String NOMBRES_BOTONES_ANADIR_PLATILLO[]= {
        "Añadir Platillo",
        "Eliminar Platillo",
    };
    
    public final String[] NOMBRES_EDITAR_MENU_ELIMINAR_PLATILLO={"Eliminar","Cancelar"};
    
    public final String[] NOMBRES_BOTONES_FORMULARIO_ANADIR_PLATILLO={"Añadir","Cancelar"};
    
    //datos correspondientes al registro de ventas
    public final String[] ICONOS_BOTONES_REGISTRO_VENTA={
        "/Ve/com/biller/imagenes/002-search-1.png",//icono de lupa para el boton buscar
        "/Ve/com/biller/imagenes/115-time.png",//icono calendario boton hoy        
        
        "/Ve/com/biller/imagenes/038-squares.png",//icono de lista de cosas para el boton de mostrar todo el registro de venta 
        "/Ve/com/biller/imagenes/020-waste-bin.png"//icono de papelera de la basura         
    
    };
    
    public final String[] NOMBRES_BOTONES_REGISTRO_VENTA={
        "Buscar",
        "Hoy",                
        "Todo",
        "Eliminadas"
    };
    
    public final String[] NOMBRES_BOTONES_FACTURA={
        "Imprimir",
        "Guardar PNG",
        "Eliminar",
    };
    
    public final String[] ICONOS_BOTONES_FACTURA={
        "/Ve/com/biller/imagenes/009-printer-1.png",//icono de impresora
        "/Ve/com/biller/imagenes/002-png.png",//icono de png
        "/Ve/com/biller/imagenes/020-waste-bin.png"//icono de papelera de la basura         
    };
    
        
}
