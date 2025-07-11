package entidades;

import java.time.LocalDate;

public class TasaCrecimiento {
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int clientesInicio;
    private int clientesFin;
    private double porcentaje;

    public TasaCrecimiento() {}

    public TasaCrecimiento(LocalDate fechaInicio, LocalDate fechaFin, int clientesInicio, int clientesFin, double porcentaje) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.clientesInicio = clientesInicio;
        this.clientesFin = clientesFin;
        this.porcentaje = porcentaje;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getClientesInicio() {
        return clientesInicio;
    }

    public void setClientesInicio(int clientesInicio) {
        this.clientesInicio = clientesInicio;
    }

    public int getClientesFin() {
        return clientesFin;
    }

    public void setClientesFin(int clientesFin) {
        this.clientesFin = clientesFin;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
