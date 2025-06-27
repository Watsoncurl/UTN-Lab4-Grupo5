package negocio;

import java.util.List;
import entidades.Cliente;

public interface ClientesNegocio {
	List<Cliente> listarTodos();
    List<Cliente> listarActivos();
    Cliente obtenerPorId(int id);
    boolean crear(Cliente cliente);
    boolean actualizar(Cliente cliente);
    boolean eliminar(int id);
	List<Cliente> listarPaginados(int inicio, int cantidad);
	int contarTotalClientes(); 
}
