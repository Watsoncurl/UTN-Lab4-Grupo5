package entidades;

public class TiposUsuario {
    private int idTipoUsuario;
    private String descripcion;

    public TiposUsuario () {};
    
    public TiposUsuario(int idTipoUsuario, String descripcion) {
		super();
		this.idTipoUsuario = idTipoUsuario;
		this.descripcion = descripcion;
	}

	public int getIdTipoUsuario() {
        return idTipoUsuario;
    } 

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}