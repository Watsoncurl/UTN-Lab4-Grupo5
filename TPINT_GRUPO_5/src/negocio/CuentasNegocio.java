package negocio;

import java.util.List;
import entidades.Cuentas;

public interface CuentasNegocio {

    boolean crearCuenta(Cuentas cuenta);
    Cuentas obtenerPorNroCuenta(String nroCuenta);
    boolean actualizar(Cuentas cuenta);
    List<Cuentas> listarPaginadas(int inicio, int cantidad);
    int contarTotalCuentas();
    List<Cuentas> listarCuentasPorCliente(int idCliente);
    public List<Cuentas> listarPaginadasFiltradas(int inicio, int cantidad, String busqueda, String tipoCuenta, Boolean estado);
    public int contarTotalCuentasFiltradas(String busqueda, String tipoCuenta, Boolean estado); 
    boolean activarCuentaPorNroCuenta(String nroCuenta);

}
