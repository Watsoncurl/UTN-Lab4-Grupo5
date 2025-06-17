package ejercicio3;

public class EdificioDeOficinas extends Edificio {
    private double superficie;
    private int numeroDeOficinas;

    public EdificioDeOficinas(double superficie, int numeroDeOficinas) {
        this.superficie = superficie;
        this.numeroDeOficinas = numeroDeOficinas;
    }

    public int getNumeroDeOficinas() {
        return numeroDeOficinas;
    }

    @Override
    public double getSuperficieEdificio() {
        return superficie;
    }

    @Override
    public String toString() {
        return "Edificio de Oficinas Superficie= " + superficie +
               ", Número de Oficinas= " + numeroDeOficinas;
    }
}