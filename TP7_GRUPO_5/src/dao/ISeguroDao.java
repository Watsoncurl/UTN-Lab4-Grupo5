package dao;

import java.util.List;

import entidad.Seguro;

public interface ISeguroDao {
	
	boolean insertar(Seguro seguro);
	List<Seguro> obtenerTodos();
	List<Seguro> obtenerPorTipo(int idTipo);
	
	

}
