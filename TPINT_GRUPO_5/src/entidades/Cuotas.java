package entidades;

import java.util.Date;

public class Cuotas {
    private int idCuota;
    private int idPrestamo;
    private double montoCuota;
    private Date fechaPago;
    private boolean estado;

    public Cuotas() {};

	public Cuotas(int idCuota, int idPrestamo, double montoCuota, Date fechaPago, boolean estado) {
		super();
		this.idCuota = idCuota;
		this.idPrestamo = idPrestamo;
		this.montoCuota = montoCuota;
		this.fechaPago = fechaPago;
		this.estado = estado;
	}

	public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public double getMontoCuota() {
        return montoCuota;
    }

    public void setMontoCuota(double montoCuota) {
        this.montoCuota = montoCuota;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
