package Ve.com.biller.estructuras.reyes;

/**
 *
 * @author Luis Reyes
 */
public class AttributesProducto {
    private String name;
    private String description;
    private String ammountStock;
    private int idPlatillo;

    public AttributesProducto(String name, String description, String ammountStock, int idPlatillo) {
        this.name = name;
        this.description = description;
        this.ammountStock = ammountStock;
        this.idPlatillo = idPlatillo;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAmmountStock() {
        return ammountStock;
    }

    public int getIdPlatillo() {
        return idPlatillo;
    }
    
}
