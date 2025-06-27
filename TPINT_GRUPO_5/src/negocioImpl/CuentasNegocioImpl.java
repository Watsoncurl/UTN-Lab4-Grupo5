package negocioImpl;

import entidades.Cuentas;
import negocio.CuentasNegocio;
import datos.CuentasDao;
import datosImpl.CuentasDaoImpl;


public class CuentasNegocioImpl implements CuentasNegocio{
	private CuentasDao cuentasDao;
    
    public CuentasNegocioImpl() {
        this.cuentasDao = new CuentasDaoImpl();
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
        // Validaciones adicionales antes de actualizar
        Cuentas cuentaExistente = cuentasDao.obtenerPorNroCuenta(cuenta.getNro_cuenta());
        
        if (cuentaExistente == null) {
            return false; // La cuenta no existe
        }
        
        // Validar que el nuevo CBU no esté en uso (si cambió)
        if (!cuentaExistente.getCbu().equals(cuenta.getCbu()) && 
            cuentasDao.existeCbu(cuenta.getCbu())) {
            return false;
        }
        
        return cuentasDao.actualizarPorNroCuenta(cuenta);
    }

}
