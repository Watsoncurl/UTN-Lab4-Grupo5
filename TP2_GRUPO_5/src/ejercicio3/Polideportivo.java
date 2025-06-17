package ejercicio3;

public class Polideportivo extends Edificio implements InstalacionDeportiva {
    private String nombre;
    private double superficie;
    private int tipoDeInstalacion; // 1 = cerrado, 2 = abierto

    public Polideportivo(String nombre, double superficie, int tipoDeInstalacion) {
        this.nombre = nombre;
        this.superficie = superficie;
        this.tipoDeInstalacion = tipoDeInstalacion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public double getSuperficieEdificio() {
        return superficie;
    }

    @Override
    public int getTipoDeInstalacion() {
        return tipoDeInstalacion;
    }

    @Override
    public String toString() {
        return "Polideportivo Nombre= " + nombre +
               ", Superficie= " + superficie +
               ", Tipo de Instalación= " + 
               (tipoDeInstalacion == 1 ? "Cerrado" : "Abierto");
    }
}