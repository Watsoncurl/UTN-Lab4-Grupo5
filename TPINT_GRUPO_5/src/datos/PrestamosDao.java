package datos;

import java.util.List;

import entidades.Prestamos;

public interface PrestamosDao {
    boolean solicitarPrestamo(Prestamos prestamos);
    List<Prestamos> obtenerPrestamosPaginados(String busqueda, String estado, int pagina, int cantidadPorPagina);
    int contarPrestamos(String busqueda, String estado);
}