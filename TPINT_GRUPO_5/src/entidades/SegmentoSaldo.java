package entidades;

public class SegmentoSaldo {
    private String segmento;
    private int cantidadClientes;
    private double montoTotal;
    private double porcentaje;

    public SegmentoSaldo() {}

    public SegmentoSaldo(String segmento, int cantidadClientes, double montoTotal, double porcentaje) {
        this.segmento = segmento;
        this.cantidadClientes = cantidadClientes;
        this.montoTotal = montoTotal;
        this.porcentaje = porcentaje;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public int getCantidadClientes() {
        return cantidadClientes;
    }

    public void setCantidadClientes(int cantidadClientes) {
        this.cantidadClientes = cantidadClientes;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
