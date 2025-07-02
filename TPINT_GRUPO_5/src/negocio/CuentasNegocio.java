package negocio;

import java.util.List;
import entidades.Cuentas;

public interface CuentasNegocio {

    boolean crearCuenta(Cuentas cuenta);
    Cuentas obtenerPorNroCuenta(String nroCuenta);
    boolean actualizar(Cuentas cuenta);
    List<Cuentas> listarPaginadas(int inicio, int cantidad);
    int contarTotalCuentas();
}
