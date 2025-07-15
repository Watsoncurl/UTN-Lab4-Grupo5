package entidades;

import java.util.Date;

public class Movimientos {
    private int idMovimiento;
    private int idCuenta;
    private int idTipoMovimiento;
    private Date fecha;
    private String concepto;
    private double importe;
    private String tipoMovimientoDescripcion;

    
    

    public Movimientos() {};

	public Movimientos(int idMovimiento, int idCuenta, int idTipoMovimiento, Date fecha, String concepto,
			double importe, String tipoMovimientoDescripcion) {
		super();
		this.idMovimiento = idMovimiento;
		this.idCuenta = idCuenta;
		this.idTipoMovimiento = idTipoMovimiento;
		this.fecha = fecha;
		this.concepto = concepto;
		this.importe = importe;
		this.tipoMovimientoDescripcion = tipoMovimientoDescripcion;
		
	}

	public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public int getIdTipoMovimiento() {
        return idTipoMovimiento;
    }
    

    public String getTipoMovimientoDescripcion() {
        return tipoMovimientoDescripcion;
    }

    public void setTipoMovimientoDescripcion(String tipoMovimientoDescripcion) {
        this.tipoMovimientoDescripcion = tipoMovimientoDescripcion;
    }


    public void setIdTipoMovimiento(int idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}