package ejercicio2;

public class Producto {
	private String fechaCaducidad;
	private int numeroLote;
	
	public Producto(String fechaCaducidad, int numeroLote) {
	    this.fechaCaducidad = fechaCaducidad;
	    this.numeroLote = numeroLote;
	}
	
	public String getFechaCaducidad() {
	    return fechaCaducidad;
	}
	
	public int getNumeroLote() {
	    return numeroLote;
	}
	
	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public void setNumeroLote(int numeroLote) {
		this.numeroLote = numeroLote;
	}

	@Override
	public String toString() {
		return "Producto Fecha Caducidad: " + fechaCaducidad + ", Número Lote: " + numeroLote;
	}


}
