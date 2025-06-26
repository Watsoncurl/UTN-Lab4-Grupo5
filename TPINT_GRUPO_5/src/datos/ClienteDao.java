package datos;

import java.util.List;

import entidades.Cliente;

public interface ClienteDao {
	List<Cliente> obtenerTodos();
    List<Cliente> obtenerActivos();
    Cliente obtenerPorId(int id);
    boolean insertar(Cliente cliente);
    boolean actualizar(Cliente cliente);
    boolean eliminar(int id);
}
