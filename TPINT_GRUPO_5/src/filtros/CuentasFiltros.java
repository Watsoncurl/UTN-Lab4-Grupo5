package filtros;

public class CuentasFiltros {
    private String busqueda;       // número de cuenta, CBU, nombre o apellido del cliente
    private String tipoCuenta;     // caja_ahorro, cuenta_corriente, cuenta_sueldo
    private Boolean estado;         // activa, inactiva
    private Integer idCliente;		//idCliente

    public String getBusqueda() {
        return busqueda;
    }

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
    
    

}
