package datos;

import java.util.List;

import entidades.Cliente;

public interface ClienteDao {
	
	public List<Cliente> listarTodos();
	public boolean insertar();
	public boolean modificar();
	public boolean eliminar();

}
