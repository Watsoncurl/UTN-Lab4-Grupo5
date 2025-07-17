package negocio;

import java.util.List;

import entidades.Prestamos;

public interface PrestamosNegocio {
    boolean solicitarPrestamo(Prestamos prestamo);
	List<Prestamos> obtenerPrestamosPaginados(String busqueda, String estado, int pagina, int cantidadPorPagina);
	int contarPrestamos(String busqueda, String estado);
	Prestamos obtenerPrestamoPorId(int id);
	boolean cambiarEstadoPrestamo(int idPrestamo, String nuevoEstado);
    List<Prestamos> obtenerPrestamosPaginadosPorCliente(int idCliente, String busqueda, String estado, int pagina, int cantidadPorPagina);
	int contarPrestamosPorCliente(int idCliente, String busqueda, String estado);
	boolean pagarCuota(int nroCuenta, int idPrestamo);
}