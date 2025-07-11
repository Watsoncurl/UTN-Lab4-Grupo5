package negocioImpl;

import java.util.List;


import datos.MovimientoDao;
import datosImpl.MovimientoDaoImpl;
import entidades.Movimientos;
import filtros.MovimientosFiltros;
import negocio.MovimientosNegocio;


public class MovimientosNegocioImpl implements MovimientosNegocio{
	private MovimientoDao movimientoDao;
	public MovimientosNegocioImpl() {
        this.movimientoDao = new MovimientoDaoImpl();
    }
	@Override
	public List<Movimientos> obtenerPorCuenta(String nroCuenta) {
		return movimientoDao.obtenerPorCuenta(nroCuenta);
	}

	@Override
	public List<Movimientos> ListarTodosFiltrados(MovimientosFiltros filtro){
		return movimientoDao.ListarTodosFiltrados(filtro);
	}
	@Override
	public List<Movimientos> ListarTodos(){
		return movimientoDao.ListarTodos();
	}
	
	

}
