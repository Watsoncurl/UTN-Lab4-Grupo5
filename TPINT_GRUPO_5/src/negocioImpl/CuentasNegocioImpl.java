package negocioImpl;

import java.util.List;
import entidades.Cuentas;
import negocio.CuentasNegocio;
import datos.CuentasDao;
import datosImpl.CuentasDaoImpl;

public class CuentasNegocioImpl implements CuentasNegocio {

    private CuentasDao cuentasDao = new CuentasDaoImpl();

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
    	return cuentasDao.obtenerCuentasPorCliente(idCliente);
    }
    
    @Override
    public List<Cuentas> listarPaginadasFiltradas(int inicio, int cantidad, String busqueda, String tipoCuenta, Boolean estado) {
        return cuentasDao.listarPaginadasFiltradas(inicio, cantidad, busqueda, tipoCuenta, estado);
    }

    @Override
    public int contarTotalCuentasFiltradas(String busqueda, String tipoCuenta, Boolean estado) {
        return cuentasDao.contarTotalCuentasFiltradas(busqueda, tipoCuenta, estado);
    }
    
    @Override
    public boolean activarCuentaPorNroCuenta(String nroCuenta) {
        return cuentasDao.activarCuentaPorNroCuenta(nroCuenta);
    }
    


}
