package filtros;

public class ClientesFiltros {
    private String busqueda;  // Para nombre, apellido, dni, email
    private String estado;    // "1" para activo, "0" para inactivo, o vacío para todos
    private String sexo;      // "M", "F", "O" o vacío para todos
    
    public ClientesFiltros() {}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}  
}
