package entidades;

public class Usuario {
	
	private int IdUsuario;
	private int IdCliente;
	private int IdTipoUsuario;
	private String Usuario;
	private String Password;
	private int Estado;
	
	
	public Usuario() {};
	
	
	
	public Usuario(int idUsuario, int idCliente, int idTipoUsuario, String usuario, String password, int estado) {
		super();
		IdUsuario = idUsuario;
		IdCliente = idCliente;
		IdTipoUsuario = idTipoUsuario;
		Usuario = usuario;
		Password = password;
		Estado = estado;
	}



	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	public int getIdCliente() {
		return IdCliente;
	}
	public void setIdCliente(int idCliente) {
		IdCliente = idCliente;
	}
	public int getIdTipoUsuario() {
		return IdTipoUsuario;
	}
	public void setIdTipoUsuario(int idTipoUsuario) {
		IdTipoUsuario = idTipoUsuario;
	}
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getEstado() {
		return Estado;
	}
	public void setEstado(int estado) {
		Estado = estado;
	}	

}
