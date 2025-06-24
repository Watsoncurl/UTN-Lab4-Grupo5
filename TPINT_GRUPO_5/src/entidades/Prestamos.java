package entidades;

import java.util.Date;

public class Prestamos {
    private int idPrestamo;
    private int idCliente;
    private Date fecha;
    private double importe;
    private int plazoMeses;
    private double cuotaMensual;
    private String estado;
    
    public Prestamos () {};

    public Prestamos(int idPrestamo, int idCliente, Date fecha, double importe, int plazoMeses, double cuotaMensual,
			String estado) {
		super();
		this.idPrestamo = idPrestamo;
		this.idCliente = idCliente;
		this.fecha = fecha;
		this.importe = importe;
		this.plazoMeses = plazoMeses;
		this.cuotaMensual = cuotaMensual;
		this.estado = estado;
	}

	public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(int plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public double getCuotaMensual() {
        return cuotaMensual;
    }

    public void setCuotaMensual(double cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
