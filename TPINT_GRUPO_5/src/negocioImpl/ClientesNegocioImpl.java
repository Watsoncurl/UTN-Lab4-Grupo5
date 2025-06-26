package negocioImpl;

import java.util.List;

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
    public List<Cliente> listarActivos() {
        return clienteDao.obtenerActivos();
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
	public Cliente obtenerPorId(int id) {

		return clienteDao.obtenerPorId(id);
	}

	@Override
	public boolean crear(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean actualizar(Cliente cliente) {
		return clienteDao.actualizar(cliente);
	}

}
