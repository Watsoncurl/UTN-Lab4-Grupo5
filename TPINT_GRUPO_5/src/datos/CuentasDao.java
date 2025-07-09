package datos;

import java.util.List;
import entidades.Cuentas;

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
    List<Cuentas> obtenerCuentasPorCliente(int idCliente);
    
    List<Cuentas> listarPaginadasFiltradas(int inicio, int cantidad, String busqueda, String tipoCuenta, Boolean estado);
    int contarTotalCuentasFiltradas(String busqueda, String tipoCuenta, Boolean estado);
    public boolean activarCuentaPorNroCuenta(String nroCuenta);

}

