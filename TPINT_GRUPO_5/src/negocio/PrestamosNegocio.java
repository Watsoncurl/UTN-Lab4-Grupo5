package negocio;

import java.util.List;

import entidades.Prestamos;

public interface PrestamosNegocio {
    boolean solicitarPrestamo(Prestamos prestamo);

	List<Prestamos> obtenerPrestamosPaginados(String busqueda, String estado, int pagina, int cantidadPorPagina);

	int contarPrestamos(String busqueda, String estado);
}