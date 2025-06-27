package negocioImpl;

import datos.UsuarioDao;
import datosImpl.UsuarioDaoImpl;
import entidades.Usuario;
import negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {

	private UsuarioDao usuarioDao;

	public UsuarioNegocioImpl() {
		this.usuarioDao = new UsuarioDaoImpl();
	}

	public UsuarioNegocioImpl(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	@Override
	public boolean agregarUsuario(Usuario usuario) {
		if (usuario.getUsuario() == null || usuario.getUsuario().isEmpty()) {
			System.out.println("Error de negocio: El nombre de usuario no puede estar vacío.");
			return false;
		}
		if (usuarioDao.existeNombreUsuario(usuario.getUsuario())) {
			System.out.println("Error de negocio: El nombre de usuario ya existe.");
			return false;
		}
		return usuarioDao.agregarUsuario(usuario);
	}

	@Override
	public Usuario obtenerUsuarioPorId(int idUsuario) {
		return usuarioDao.obtenerUsuarioPorId(idUsuario);
	}

	@Override
	public boolean actualizarUsuario(Usuario usuario) {

		return usuarioDao.actualizarUsuario(usuario);
	}

	@Override
	public boolean eliminarUsuario(int idUsuario) {
		return usuarioDao.eliminarUsuario(idUsuario);
	}

	@Override
	public boolean existeNombreUsuario(String nombreUsuario) {
		return usuarioDao.existeNombreUsuario(nombreUsuario);
	}
}
