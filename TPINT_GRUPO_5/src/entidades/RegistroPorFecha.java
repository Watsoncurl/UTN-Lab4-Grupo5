package entidades;

import java.time.LocalDate;

public class RegistroPorFecha {
    private LocalDate fecha;
    private int cantidad;

    public RegistroPorFecha() {}

    public RegistroPorFecha(LocalDate fecha, int cantidad) {
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
