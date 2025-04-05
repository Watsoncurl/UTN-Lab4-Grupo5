package ejercicio2;

public class ProductoRefrigerado extends Producto {
    private String codigoOrganismo;

    public ProductoRefrigerado(String fechaCaducidad, int numeroLote, String codigoOrganismo) {
        super(fechaCaducidad, numeroLote);
        this.codigoOrganismo = codigoOrganismo;
    }

    public String getCodigoOrganismo() {
        return codigoOrganismo;
    }

    
    public void setCodigoOrganismo(String codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}

	@Override
    public String toString() {
        return super.toString() +
               ", Código organismo de supervisión: " + codigoOrganismo;
    }
}

