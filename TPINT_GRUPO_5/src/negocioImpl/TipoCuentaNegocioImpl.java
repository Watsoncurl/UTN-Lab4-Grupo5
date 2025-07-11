package negocioImpl;

import java.util.List;

import datos.TipoCuentaDao;
import datosImpl.TipoCuentaDaoImpl;
import entidades.tipo_cuenta;
import negocio.TiposCuentaNegocio;

public class TipoCuentaNegocioImpl implements TiposCuentaNegocio{
	private TipoCuentaDao tipoCuentaDao;
	public TipoCuentaNegocioImpl() {
		this.tipoCuentaDao = new TipoCuentaDaoImpl();
		}
	



	@Override
	public List<tipo_cuenta> listarTodos() {
		return tipoCuentaDao.obtenerTodos();
	}}

