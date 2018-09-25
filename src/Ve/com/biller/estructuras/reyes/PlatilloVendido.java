package Ve.com.biller.estructuras.reyes;

/**
 *
 * @author Luis Reyes
 */
public class PlatilloVendido {
    String categoriaPlatillo;
    String nombrePlatillo;
    String caracteristicasPlatillo;
    double precioPlatillo;
    long horaPlatillo;
    long fechaPlatillo;
    int _idVentaPlatillo;

    public PlatilloVendido(String categoriaPlatillo, String nombrePlatillo, String caracteristicasPlatillo, double precioPlatillo, long horaPlatillo, long fechaPlatillo, int _idVentaPlatillo) {
        this.categoriaPlatillo = categoriaPlatillo;
        this.nombrePlatillo = nombrePlatillo;
        this.caracteristicasPlatillo = caracteristicasPlatillo;
        this.precioPlatillo = precioPlatillo;
        this.horaPlatillo = horaPlatillo;
        this.fechaPlatillo = fechaPlatillo;
        this._idVentaPlatillo = _idVentaPlatillo;
    }

    public String getCategoriaPlatillo() {
        return categoriaPlatillo;
    }

    public String getNombrePlatillo() {
        return nombrePlatillo;
    }

    public String getCaracteristicasPlatillo() {
        return caracteristicasPlatillo;
    }

    public double getPrecioPlatillo() {
        return precioPlatillo;
    }

    public long getHoraPlatillo() {
        return horaPlatillo;
    }

    public long getFechaPlatillo() {
        return fechaPlatillo;
    }

    public int getIdVentaPlatillo() {
        return _idVentaPlatillo;
    }
    
}
