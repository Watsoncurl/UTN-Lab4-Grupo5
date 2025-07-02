package datos;


import entidades.Usuario;
public interface UsuarioDao {
	 public boolean agregarUsuario(Usuario usuario);
	    public Usuario obtenerUsuarioPorId(int idUsuario);
	    public boolean actualizarUsuario(Usuario usuario);
	    public boolean eliminarUsuario(int idUsuario);
	    public boolean existeNombreUsuario(String nombreUsuario);
}
