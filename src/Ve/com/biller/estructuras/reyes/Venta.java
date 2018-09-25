package Ve.com.biller.estructuras.reyes;

/**
 *
 * @author Luis Reyes
 */
public class Venta {
    protected long fecha;
    protected String descripcionVenta;
    protected String platillosContenidos;
    protected String metodoPago;
    protected String descripcionMetodoPago;
    protected double ivaDesc;
    protected double costo;//costo total de la venta
    protected int idVenta;//numero de factura
    protected long horaVenta;
    protected String nombreCliente;
    protected int rifCliente;    
    protected String tipoDeRif;
    protected String domicilio;
    protected String telefono;        

   

    public Venta(long fecha, String descripcion, String platillosContenidos, String metodoPago, String descripcionMetodoPago, double ivaDesc, double totalPrecio, int idVenta, long horaVenta, String nombreCliente, int cedulaCliente,String tipoDeRif,String domicilio,String telefono) {
        this.fecha = fecha;
        this.descripcionVenta = descripcion;
        this.platillosContenidos = platillosContenidos;
        this.metodoPago = metodoPago;
        this.descripcionMetodoPago = descripcionMetodoPago;
        this.ivaDesc = ivaDesc;
        this.costo = totalPrecio;
        this.idVenta = idVenta;
        this.horaVenta = horaVenta;
        this.nombreCliente = nombreCliente;
        this.rifCliente = cedulaCliente;
        
        this.tipoDeRif=tipoDeRif;
        this.domicilio=domicilio;
        this.telefono=telefono;        
        
    }

    public long getFecha() {
        return fecha;
    }

    public String getDescripcionVenta() {
        return descripcionVenta;
    }

    public String getPlatillosContenidos() {
        return platillosContenidos;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public String getDescripcionMetodoPago() {
        return descripcionMetodoPago;
    }

    public double getIvaDesc() {
        return ivaDesc;
    }
    /**
     * Costo total de la venta
     * @return 
     */
    public double getCosto() {
        return costo;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public long getHoraVenta() {
        return horaVenta;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public int getRifCliente() {
        return rifCliente;
    }
     public String getTipoDeRif() {
        return tipoDeRif;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public String getTelefono() {
        return telefono;
    }
    
    
}
