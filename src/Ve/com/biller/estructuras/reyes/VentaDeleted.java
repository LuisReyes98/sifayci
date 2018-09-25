/*
 * Copyright (C) Luis Rogelio Reyes Hernandez.
 * All rights reserved.
 */
package Ve.com.biller.estructuras.reyes;

/**
 *
 * @author luisr
 */
public class VentaDeleted extends Venta{
    String usuario;
    int usuario_id;
    String reason_delete;
    
    public VentaDeleted(long fecha, String descripcion, String platillosContenidos, String metodoPago, String descripcionMetodoPago, double ivaDesc, double totalPrecio, int idVenta, long horaVenta, String nombreCliente, int cedulaCliente, String tipoDeRif, String domicilio, String telefono,String usuario,int usuario_id,String reason_delete) {
        super(fecha, descripcion, platillosContenidos, metodoPago, descripcionMetodoPago, ivaDesc, totalPrecio, idVenta, horaVenta, nombreCliente, cedulaCliente, tipoDeRif, domicilio, telefono);
        this.usuario=usuario;
        this.usuario_id=usuario_id;
        this.reason_delete=reason_delete;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public String getReason_delete() {
        return reason_delete;
    }
    
    
    
}
