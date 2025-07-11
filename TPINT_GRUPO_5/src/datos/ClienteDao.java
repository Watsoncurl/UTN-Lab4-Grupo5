package datos;

import java.util.List;

import entidades.Cliente;
import filtros.ClientesFiltros;

public interface ClienteDao {
	List<Cliente> obtenerTodos();
    List<Cliente> filtrar(ClientesFiltros filtro);
    Cliente obtenerPorId(int id);
    boolean insertar(Cliente cliente);
    boolean actualizar(Cliente cliente);
    boolean eliminar(int id);
    List<Cliente> listarPaginados(int inicio, int cantidad);
    int contar();
}
