package datos;

import java.util.List;

import entidades.Prestamos;

public interface PrestamosDao {
    boolean solicitarPrestamo(Prestamos prestamos);
    List<Prestamos> obtenerPrestamosPaginados(String busqueda, String estado, int pagina, int cantidadPorPagina);
    int contarPrestamos(String busqueda, String estado);
	Prestamos obtenerPrestamoPorId(int id);
	boolean actualizarEstadoPrestamo(int idPrestamo, String nuevoEstado);
	List<Prestamos> obtenerPrestamosPaginadosPorCliente(int idCliente, String busqueda, String estado, int pagina, int cantidadPorPagina);
	int contarPrestamosPorCliente(int idCliente, String busqueda, String estado);
	boolean pagarCuota(int nroCuenta, int idPrestamo);
}