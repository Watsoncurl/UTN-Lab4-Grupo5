package negocioImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import datos.ClienteDao;
import datosImpl.ClientesDaoImpl;
import entidades.Cliente;
import negocio.ClientesNegocio;

public class ClientesNegocioImpl implements ClientesNegocio {

    private ClienteDao clienteDao;

    public ClientesNegocioImpl() {
        this.clienteDao = new ClientesDaoImpl();
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteDao.listarTodos();
    }

    @Override
    public List<Cliente> buscarClientes(String filtro) {
        String filtroLower = filtro.toLowerCase();

        return clienteDao.listarTodos().stream()
                .filter(cliente ->
                        cliente.getDni().contains(filtro) ||
                        cliente.getNombre().toLowerCase().contains(filtroLower) ||
                        cliente.getApellido().toLowerCase().contains(filtroLower) ||
                        cliente.getEmail().toLowerCase().contains(filtroLower)
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> filtrarPorEstado(boolean estado) {
        return clienteDao.listarTodos().stream()
                .filter(cliente -> cliente.isEstado() == estado)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cliente> filtrarPorSexo(String sexo) {
        return clienteDao.listarTodos().stream()
                .filter(cliente -> cliente.getSexo().equalsIgnoreCase(sexo))
                .collect(Collectors.toList());
    }

    @Override
    public boolean agregarCliente(Cliente nuevoCliente) {
        return clienteDao.insertar(nuevoCliente);
    }

    @Override
    public boolean editarCliente(Cliente clienteActualizado) {
        return clienteDao.modificar(clienteActualizado);
    }

    @Override
    public boolean eliminarCliente(int idCliente) {
        return clienteDao.eliminar(idCliente);
    }
}
