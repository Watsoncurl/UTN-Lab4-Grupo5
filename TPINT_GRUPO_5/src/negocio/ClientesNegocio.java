package negocio;

import java.util.List;
import entidades.Cliente;
import filtros.ClientesFiltros;

public interface ClientesNegocio {
    List<Cliente> listarTodos();
    List<Cliente> listarActivos();
    List<Cliente> filtrar(ClientesFiltros filtro) throws Exception;
    Cliente obtenerPorId(int id);
    boolean crear(Cliente cliente);
    boolean actualizar(Cliente cliente);
    List<Cliente> listarPaginados(int inicio, int cantidad);
    int contarTotalClientes();
    boolean activarCliente(int id);
    boolean eliminarCliente(int id);
}