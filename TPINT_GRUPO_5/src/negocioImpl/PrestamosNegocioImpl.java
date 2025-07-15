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
    	// Si no usás CuentasNegocio acá, lo podés dejar como null o agregar una implementación real si la tenés
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
    	double tasaInteresMensual = 0.1; 
        //double cuotaMensual = prestamo.getImporte() * (tasaInteresMensual / (1 - Math.pow(1 + tasaInteresMensual, -prestamo.getPlazoMeses())));
    	double cuotaMensual = (importe + (importe * tasaInteresMensual * (plazoMeses / 12))) / plazoMeses;
    	prestamo.setCuotaMensual(cuotaMensual);

        // Aqu� podr�as agregar validaciones adicionales, como verificar si el cliente cumple con los requisitos para solicitar el pr�stamo.

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

}