package negocio;

import java.util.List;
import entidades.Cuentas;
import filtros.CuentasFiltros;

public interface CuentasNegocio {

    boolean crearCuenta(Cuentas cuenta);
    Cuentas obtenerPorNroCuenta(String nroCuenta);
    boolean actualizar(Cuentas cuenta);
    List<Cuentas> listarPaginadas(int inicio, int cantidad);
    int contarTotalCuentas();
    List<Cuentas> listarCuentasPorCliente(int idCliente);
    List<Cuentas> filtrar(CuentasFiltros filtro, int inicio, int cantidad);
    int contarFiltradas(CuentasFiltros filtro); 
    boolean activarCuentaPorNroCuenta(String nroCuenta);
    List<Cuentas> listarTodas();
    List<Cuentas> listarPorTipo(int idTipoCuenta);

}
