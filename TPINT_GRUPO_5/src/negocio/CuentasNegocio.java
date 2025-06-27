package negocio;
import entidades.Cuentas;

public interface CuentasNegocio {
	
	boolean crearCuenta(Cuentas cuenta);
	Cuentas obtenerPorNroCuenta(String nroCuenta);
    boolean actualizar(Cuentas cuenta);

}
