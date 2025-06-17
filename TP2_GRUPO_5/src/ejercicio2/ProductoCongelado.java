package ejercicio2;

public class ProductoCongelado extends Producto {
    private double temperaturaCongelacion;

    public ProductoCongelado(String fechaCaducidad, int numeroLote, double temperaturaCongelacion) {
        super(fechaCaducidad, numeroLote);
        this.temperaturaCongelacion = temperaturaCongelacion;
    }

    public double getTemperaturaCongelacion() {
        return temperaturaCongelacion;
    }

    
    public void setTemperaturaCongelacion(double temperaturaCongelacion) {
    	if (temperaturaCongelacion > 0) {
            System.out.println("Advertencia: La temperatura de congelación debería ser menor o igual a 0°C");
        }
        this.temperaturaCongelacion = temperaturaCongelacion;
	}

	@Override
	public String toString() {
	    return "Producto Congelado - " + super.toString() +
	           ", Temperatura de Congelación Recomendada: " + temperaturaCongelacion + "°C";
	}
	
	public void mostrar() {
	    System.out.println(this.toString());
	}
}

