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

    @Override
    public String toString() {
        return super.toString() +
               ", Temperatura de congelación recomendada: " + temperaturaCongelacion + "°C";
    }
}

