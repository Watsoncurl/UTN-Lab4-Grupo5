package datos;

import entidades.Cuentas;

public interface CuentasDao {
	boolean crearCuenta(Cuentas cuenta);
	boolean existeNroCuenta(String nroCuenta);
	boolean existeCbu(String cbu);
	int contarCuentasActivasPorCliente(int idCliente);
	Cuentas obtenerPorNroCuenta(String nroCuenta);
    boolean actualizarPorNroCuenta(Cuentas cuenta);

}
