package datos;

import java.util.List;

import entidades.Cliente;

public interface ClienteDao {
	
	List<Cliente> listarTodos();
	boolean insertar(Cliente cliente);
	boolean modificar(Cliente cliente);
	boolean eliminar(int idCliente);

}
