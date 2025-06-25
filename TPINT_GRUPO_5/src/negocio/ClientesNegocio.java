package negocio;

import java.util.List;
import entidades.Cliente;

public interface ClientesNegocio {
    List<Cliente> listarTodos();
    List<Cliente> buscarClientes(String filtro);
    List<Cliente> filtrarPorEstado(boolean estado);
    List<Cliente> filtrarPorSexo(String sexo);
    boolean agregarCliente(Cliente nuevoCliente);
    boolean editarCliente(Cliente clienteActualizado);
    boolean eliminarCliente(int idCliente);
}