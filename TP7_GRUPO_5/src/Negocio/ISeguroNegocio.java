package Negocio;

import java.util.List;

import entidad.Seguro;

public interface ISeguroNegocio {

	public boolean agregar(Seguro seguro);
	public List<Seguro> obtenerTodos();
	
}
