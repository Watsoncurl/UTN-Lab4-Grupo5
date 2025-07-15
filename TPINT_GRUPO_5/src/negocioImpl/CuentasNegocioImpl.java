package negocioImpl;

import java.util.List;
import entidades.Cuentas;
import filtros.CuentasFiltros;
import negocio.CuentasNegocio;
import datos.CuentasDao;
import datosImpl.CuentasDaoImpl;

public class CuentasNegocioImpl implements CuentasNegocio {

	private CuentasDao cuentasDao = new CuentasDaoImpl();
	
	 private CuentasDao cuentasDao1;

	    public CuentasNegocioImpl() {
	    }

	    public void setCuentasDao(CuentasDao cuentasDao) {
	        this.cuentasDao1 = cuentasDao;
	    }
	    public CuentasNegocioImpl(CuentasDao cuentasDao) {
	        this.cuentasDao = cuentasDao;
	    }
    
    @Override
    public boolean crearCuenta(Cuentas cuenta) {
        if (cuentasDao.existeNroCuenta(cuenta.getNro_cuenta())) {
            return false;
        }

        if (cuentasDao.existeCbu(cuenta.getCbu())) {
            return false;
        }

        if (cuentasDao.contarCuentasActivasPorCliente(cuenta.getId_cliente()) >= 3) {
            return false;
        }
        return cuentasDao.crearCuenta(cuenta);
    }
    
    @Override
    public Cuentas obtenerPorNroCuenta(String nroCuenta) {
        return cuentasDao.obtenerPorNroCuenta(nroCuenta);
    }

    @Override
    public boolean actualizar(Cuentas cuenta) {
        Cuentas cuentaExistente = cuentasDao.obtenerPorNroCuenta(cuenta.getNro_cuenta());
        
        if (cuentaExistente == null) {
            return false; 
        }
        
        if (!cuentaExistente.getCbu().equals(cuenta.getCbu()) && 
            cuentasDao.existeCbu(cuenta.getCbu())) {
            return false;
        }
        
        return cuentasDao.actualizarPorNroCuenta(cuenta);
    }

    @Override
    public List<Cuentas> listarPaginadas(int inicio, int cantidad) {
        return cuentasDao.listarPaginadas(inicio, cantidad);
    }

    @Override
    public int contarTotalCuentas() {
        return cuentasDao.contarTotalCuentas();
    }
    @Override
    public List<Cuentas> listarCuentasPorCliente(int idCliente){
        CuentasFiltros filtro = new CuentasFiltros();
        filtro.setIdCliente(idCliente);    
        int inicio = 0;       
        int cantidad = 100;   
        return cuentasDao.filtrar(filtro, inicio, cantidad);
    }
    
    @Override
    public List<Cuentas> filtrar(CuentasFiltros filtro, int inicio, int cantidad) {
        return cuentasDao.filtrar(filtro, inicio, cantidad);
    }

    @Override
    public int contarFiltradas(CuentasFiltros filtro) {
        return cuentasDao.contarFiltradas(filtro);
    }
    
    @Override
    public boolean activarCuentaPorNroCuenta(String nroCuenta) {
        return cuentasDao.activarCuentaPorNroCuenta(nroCuenta);
    }
    
    @Override
    public List<Cuentas> listarTodas(){
    	return cuentasDao.listarTodas();
    }

	@Override
	public List<Cuentas> listarPorTipo(int idTipoCuenta) {
		return cuentasDao.listarPorTipo(idTipoCuenta);
	}
	@Override
	public List<Cuentas> obtenerCuentasPorIdCliente(int idCliente) {
	    return cuentasDao.obtenerCuentasPorIdCliente(idCliente);
	}

	 @Override
	    public Cuentas obtenerCuentaPorCBU(String cbu) {
	        return cuentasDao.obtenerCuentaPorCBU(cbu);
	    }
	 

}