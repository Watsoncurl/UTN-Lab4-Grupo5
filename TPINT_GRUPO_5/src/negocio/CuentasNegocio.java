package negocio;

import java.util.List;
import entidades.Cuentas;
import filtros.CuentasFiltros;

public interface CuentasNegocio {

    boolean eliminarCuenta(String nroCuenta);
    boolean crearCuenta(Cuentas cuenta);
    Cuentas obtenerPorNroCuenta(String nroCuenta);
    int contarTotalCuentasFiltradas(String busqueda, String tipoCuenta, Boolean estado);
    List<Cuentas> listarPaginadasFiltradas(int inicio, int cantidad, String busqueda, String tipoCuenta, Boolean estado);
    boolean actualizar(Cuentas cuenta);
    List<Cuentas> listarPaginadas(int inicio, int cantidad);
    int contarTotalCuentas();
    List<Cuentas> listarCuentasPorCliente(int idCliente);
    List<Cuentas> filtrar(CuentasFiltros filtro, int inicio, int cantidad);
    int contarFiltradas(CuentasFiltros filtro);
    boolean activarCuentaPorNroCuenta(String nroCuenta);
    List<Cuentas> listarTodas();
    List<Cuentas> listarPorTipo(int idTipoCuenta);
    List<Cuentas> obtenerCuentasPorIdCliente(int idCliente);
    Cuentas obtenerCuentaPorCBU(String cbu);
    int obtenerIdPorNroCuenta(String nroCuenta);
    int obtenerIdCuentaPorNroCuenta(String nroCuenta);
    Cuentas obtenerCuentaPorId(int idCuenta);
    Cuentas obtenerPorId(int idCuenta);
    boolean cambiaSaldo(Cuentas cuenta);
}