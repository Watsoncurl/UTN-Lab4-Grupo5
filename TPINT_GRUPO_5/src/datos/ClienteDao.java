package datos;

import java.util.List;

import entidades.Cliente;

public interface ClienteDao {
	List<Cliente> obtenerTodos();
    List<Cliente> obtenerActivos();
    List<Cliente> filtrarPorBusquedaEstadoYSexo(String busqueda, String estadoSeleccionado, String sexoSeleccionado);
    Cliente obtenerPorId(int id);
    boolean insertar(Cliente cliente);
    boolean actualizar(Cliente cliente);
    boolean eliminar(int id);
    List<Cliente> listarPaginados(int inicio, int cantidad);
    int contar();
}
