package ejercicio2;

public class ProductoFresco extends Producto {
    private String fechaEnvasado;
    private String paisOrigen;

    public ProductoFresco(String fechaCaducidad, int numeroLote, String fechaEnvasado, String paisOrigen) {
        super(fechaCaducidad, numeroLote);
        this.fechaEnvasado = fechaEnvasado;
        this.paisOrigen = paisOrigen;
    }

    public String getFechaEnvasado() {
        return fechaEnvasado;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    
    public void setFechaEnvasado(String fechaEnvasado) {
		this.fechaEnvasado = fechaEnvasado;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	@Override
	public String toString() {
	    return "Producto Fresco - " + super.toString() +
	           ", Fecha de Envasado: " + fechaEnvasado +
	           ", País de Origen: " + paisOrigen;
	}
	
	public void mostrar() {
	    System.out.println(this.toString());
	}
}
