package negocio;

import java.util.List;

import entidades.Cliente;

public interface ClientesNegocio {
	
	public List<Cliente> listarTodos();
	public boolean agregarCliente();
	public boolean editarCliente();
	public boolean eliminarCliente();
	
}
