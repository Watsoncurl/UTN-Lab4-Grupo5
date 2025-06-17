package NegocioImpl;

import java.util.List;

import Negocio.ISeguroNegocio;
import entidad.Seguro;
import daoImpl.SeguroDaoImpl;

public class SeguroNegocioImpl implements ISeguroNegocio{

	SeguroDaoImpl seg = new SeguroDaoImpl();
	
	@Override
	public boolean agregar(Seguro seguro) {
		return seg.insertar(seguro);
	}
	
	@Override
	public List<Seguro> obtenerTodos(){
		return seg.obtenerTodos();
	}

}
