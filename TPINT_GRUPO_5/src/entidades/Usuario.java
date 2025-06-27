package entidades;

public class Usuario {

	private int IdUsuario;
	private Cliente Cliente;
	private TiposUsuario TipoUsuario;
	private String Usuario;
	private String Password;
	private boolean Estado;

	public Usuario() {
	};

	public Usuario(int idUsuario, Cliente cliente, TiposUsuario tipoUsuario, String usuario, String password,
			boolean estado) {
		super();
		IdUsuario = idUsuario;
		Cliente = cliente;
		TipoUsuario = tipoUsuario;
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

	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}

	public TiposUsuario getTipoUsuario() {
		return TipoUsuario;
	}

	public void setTipoUsuario(TiposUsuario tipoUsuario) {
		TipoUsuario = tipoUsuario;
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

	public boolean getEstado() {
		return Estado;
	}

	public void setEstado(boolean estado) {
		Estado = estado;
	}
	 @Override
	    public String toString() {
	        return "Usuario [IdUsuario=" + IdUsuario +
	               ", Cliente=" + (Cliente != null ? Cliente.getIdCliente() : "NULL") + 
	               ", TipoUsuario=" + (TipoUsuario != null ? TipoUsuario.getIdTipoUsuario() : "NULL") +
	               ", Usuario=" + Usuario + ", Estado=" + Estado + "]";
	    }

}
