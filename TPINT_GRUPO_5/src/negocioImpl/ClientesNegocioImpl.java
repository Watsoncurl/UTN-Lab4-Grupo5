package negocioImpl;

import java.util.List;

import datos.ClienteDao;
import datosImpl.ClientesDaoImpl;
import entidades.Cliente;
import filtros.ClientesFiltros;
import negocio.ClientesNegocio;

public class ClientesNegocioImpl implements ClientesNegocio {
	private ClienteDao clienteDao;
    
    public ClientesNegocioImpl() {
        this.clienteDao = new ClientesDaoImpl();
    }
    
    @Override
    public List<Cliente> listarActivos() {
        ClientesFiltros filtro = new ClientesFiltros();
        filtro.setEstado("1"); // Solo activos
        return clienteDao.filtrar(filtro);
    }
    
    @Override
    public List<Cliente> filtrar(ClientesFiltros filtro) throws Exception {
        return clienteDao.filtrar(filtro);
    }
    
    @Override
    public boolean eliminar(int id) {
       
        return clienteDao.eliminar(id);
    }
    

	@Override
	public List<Cliente> listarTodos() {
		return clienteDao.obtenerTodos();
	}
	
    @Override
    public List<Cliente> listarPaginados(int inicio, int cantidad) {
        return clienteDao.listarPaginados(inicio, cantidad);
    }
    
    @Override
    public int contarTotalClientes() {
        return clienteDao.contar();
    }

	@Override
	public Cliente obtenerPorId(int id) {

		return clienteDao.obtenerPorId(id);
	}

	@Override
	public boolean crear(Cliente cliente) {
		return clienteDao.insertar(cliente);
	}

	@Override
	public boolean actualizar(Cliente cliente) {
		return clienteDao.actualizar(cliente);
	}

}