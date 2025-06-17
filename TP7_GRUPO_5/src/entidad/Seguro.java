package entidad;

public class Seguro {
    private int idSeguro;
    private String descripcion;
    private int idTipoSeguro;
    private double costoContratacion;
    private double costoAsegurado;
    
    public Seguro() {}

    public Seguro(int idSeguro, String descripcion, int idTipoSeguro, double costoContratacion, double costoAsegurado) {
        this.idSeguro = idSeguro;
        this.descripcion = descripcion;
        this.idTipoSeguro = idTipoSeguro;
        this.costoContratacion = costoContratacion;
        this.costoAsegurado = costoAsegurado;
    }

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTipoSeguro() {
        return idTipoSeguro;
    }

    public void setIdTipoSeguro(int idTipoSeguro) {
        this.idTipoSeguro = idTipoSeguro;
    }

	public double getCostoContratacion() {
		return costoContratacion;
	}

	public void setCostoContratacion(double costoContratacion) {
		this.costoContratacion = costoContratacion;
	}

	public double getCostoAsegurado() {
		return costoAsegurado;
	}

	public void setCostoAsegurado(double costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}

	@Override
	public String toString() {
		return "Seguro [idSeguro=" + idSeguro + ", descripcion=" + descripcion + ", idTipoSeguro=" + idTipoSeguro
				+ ", costoContratacion=" + costoContratacion + ", costoAsegurado=" + costoAsegurado + "]";
	}
	
	
}

