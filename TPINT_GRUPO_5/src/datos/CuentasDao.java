package datos;

import java.util.List;
import entidades.Cuentas;
import filtros.CuentasFiltros;

public interface CuentasDao {
    boolean crearCuenta(Cuentas cuenta);
    boolean existeNroCuenta(String nroCuenta);
    boolean existeCbu(String cbu);
    int contarCuentasActivasPorCliente(int idCliente);
    Cuentas obtenerPorNroCuenta(String nroCuenta);
    boolean actualizarPorNroCuenta(Cuentas cuenta);
    boolean eliminarCuenta(String nroCuenta);
    List<Cuentas> listarPaginadas(int inicio, int cantidad);
    int contarTotalCuentas();    
    List<Cuentas> filtrar(CuentasFiltros filtro, int inicio, int cantidad);
    int contarFiltradas(CuentasFiltros filtro);
    public boolean activarCuentaPorNroCuenta(String nroCuenta);
    List<Cuentas> listarTodas();
    List<Cuentas>listarPorTipo(int idTipoCuenta);
    List<Cuentas> obtenerCuentasPorIdCliente(int idCliente);
    Cuentas obtenerCuentaPorCBU(String cbu);
    int obtenerIdPorNroCuenta(String nroCuenta);
    int obtenerIdCuentaPorNroCuenta(String nroCuenta);
    Cuentas obtenerCuentaPorId(int idCuenta);
    Cuentas obtenerPorId(int idCuenta);
    boolean actualizar(Cuentas cuenta);
    boolean cambiaSaldo(Cuentas cuenta);
    
}

