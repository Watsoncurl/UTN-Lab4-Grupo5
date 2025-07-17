package datos;

import java.util.List;

import entidades.Movimientos;
import filtros.MovimientosFiltros;

public interface MovimientoDao {
	List<Movimientos> obtenerPorCuenta(String nroCuenta);
	List<Movimientos> ListarTodosFiltrados(MovimientosFiltros filtro);
	List<Movimientos> ListarTodos();	
	List<Movimientos> ListarTodosConDescripcion();
	 List<Movimientos> ListarTodosFiltradosPaginado(MovimientosFiltros filtro, int offset, int registrosPorPagina);
	 int obtenerTotalMovimientosFiltrados(MovimientosFiltros filtro);
}
