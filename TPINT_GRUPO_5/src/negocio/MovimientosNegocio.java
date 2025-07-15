package negocio;
import java.util.List;
import entidades.Movimientos;
import filtros.MovimientosFiltros;


public interface MovimientosNegocio {
	List<Movimientos> obtenerPorCuenta(String nroCuenta);
	List<Movimientos> ListarTodosFiltrados(MovimientosFiltros filtro);
	List<Movimientos> ListarTodos();
	List<Movimientos> ListarTodosConDescripcion();
}
