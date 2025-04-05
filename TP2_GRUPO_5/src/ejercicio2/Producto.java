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
	
	@Override
	public String toString() {
	    return "Fecha de caducidad: " + fechaCaducidad + ", Número de lote: " + numeroLote;
	}
}
