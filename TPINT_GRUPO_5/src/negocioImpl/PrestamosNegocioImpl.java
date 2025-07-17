package negocioImpl;

import java.util.List;

import datos.PrestamosDao;
import datosImpl.PrestamosDaoImpl;
import entidades.Prestamos;
import negocio.PrestamosNegocio;
import negocio.CuentasNegocio;

public class PrestamosNegocioImpl implements PrestamosNegocio {


    
	private PrestamosDao prestamoDao;
    private CuentasNegocio cuentasNegocio;

    public PrestamosNegocioImpl() {
    	this.prestamoDao = new PrestamosDaoImpl();
    	this.cuentasNegocio = null;
    }
    
    public PrestamosNegocioImpl(PrestamosDao prestamoDao, CuentasNegocio cuentasNegocio) {
        this.prestamoDao = prestamoDao;
        this.cuentasNegocio = cuentasNegocio;
    }

    @Override
    public boolean solicitarPrestamo(Prestamos prestamo) {
    	double importe = prestamo.getImporte();
    	int plazoMeses = prestamo.getPlazoMeses();
    	double tasaInteresMensual = 0.1/12; 
    	double cuotaMensual = (importe + (importe * tasaInteresMensual * plazoMeses)) / plazoMeses;
    	prestamo.setCuotaMensual(cuotaMensual);


        return prestamoDao.solicitarPrestamo(prestamo);
    }
    
    @Override
    public List<Prestamos> obtenerPrestamosPaginados(String busqueda, String estado, int pagina, int cantidadPorPagina) {
        return prestamoDao.obtenerPrestamosPaginados(busqueda, estado, pagina, cantidadPorPagina);
    }

    @Override
    public int contarPrestamos(String busqueda, String estado) {
        return prestamoDao.contarPrestamos(busqueda, estado);
    }

    @Override
    public Prestamos obtenerPrestamoPorId(int id) {
        return prestamoDao.obtenerPrestamoPorId(id);
    }
    
    @Override
    public boolean cambiarEstadoPrestamo(int idPrestamo, String nuevoEstado) {
        return prestamoDao.actualizarEstadoPrestamo(idPrestamo, nuevoEstado);
    }
    
    @Override
    public List<Prestamos> obtenerPrestamosPaginadosPorCliente(int idCliente, String busqueda, String estado, int pagina, int cantidadPorPagina) {
        return prestamoDao.obtenerPrestamosPaginadosPorCliente(idCliente, busqueda, estado, pagina, cantidadPorPagina);
    }

    @Override
    public int contarPrestamosPorCliente(int idCliente, String busqueda, String estado) {
        return prestamoDao.contarPrestamosPorCliente(idCliente, busqueda, estado);
    }
    @Override
    public boolean pagarCuota(int nroCuenta, int idPrestamo) {
        return prestamoDao.pagarCuota(nroCuenta, idPrestamo);
    }

}