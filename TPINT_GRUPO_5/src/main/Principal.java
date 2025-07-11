package main;

import datos.InformesDao;
import datosImpl.InformesDaoImpl;
import entidades.ResumenTransaccional;
import entidades.TasaCrecimiento;
import negocioImpl.InformesNegocioImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Principal {

    	public static void main(String[] args) {
    		 InformesNegocioImpl negocio = new InformesNegocioImpl();

    	        // Prueba 1: Cantidad de clientes por segmento
    	        Map<String, Integer> clientesPorSegmento = negocio.obtenerCantidadClientesPorSegmento();
    	        System.out.println("Clientes por segmento:");
    	        clientesPorSegmento.forEach((segmento, cantidad) -> System.out.println(" - " + segmento + ": " + cantidad));

    	        // Prueba 2: Montos por segmento
    	        Map<String, Double> montosPorSegmento = negocio.obtenerMontosPorSegmento();
    	        System.out.println("\nMontos por segmento:");
    	        montosPorSegmento.forEach((segmento, monto) -> System.out.println(" - " + segmento + ": $" + monto));

    	        // Prueba 3: Nuevos clientes por fecha
    	        Map<java.time.LocalDate, Integer> nuevosClientesPorFecha = negocio.obtenerNuevosClientesPorFecha();
    	        System.out.println("\nNuevos clientes por fecha:");
    	        nuevosClientesPorFecha.forEach((fecha, cantidad) -> System.out.println(" - " + fecha + ": " + cantidad));

    	        // Prueba 4: Nuevas cuentas por fecha
    	        Map<java.time.LocalDate, Integer> nuevasCuentasPorFecha = negocio.obtenerNuevasCuentasPorFecha();
    	        System.out.println("\nNuevas cuentas por fecha:");
    	        nuevasCuentasPorFecha.forEach((fecha, cantidad) -> System.out.println(" - " + fecha + ": " + cantidad));

    	        // Prueba 5: Cuentas por tipo
    	        Map<String, Integer> cuentasPorTipo = negocio.obtenerCuentasPorTipo();
    	        System.out.println("\nCuentas por tipo:");
    	        cuentasPorTipo.forEach((tipo, cantidad) -> System.out.println(" - " + tipo + ": " + cantidad));

    	        // Prueba 6: Tasa de crecimiento
    	        LocalDate inicio = LocalDate.of(2025, 6, 1);
    	        LocalDate fin = LocalDate.of(2025, 6, 30);
    	        TasaCrecimiento tasaCrecimiento = negocio.calcularTasaCrecimiento(inicio, fin);
    	        if (tasaCrecimiento != null) {
    	            System.out.println("\nTasa de crecimiento entre " + inicio + " y " + fin + ":");
    	            System.out.println(" - Clientes antes: " + tasaCrecimiento.getClientesInicio());
    	            System.out.println(" - Clientes después: " + tasaCrecimiento.getClientesFin());
    	            System.out.printf(" - Porcentaje crecimiento: %.2f%%\n", tasaCrecimiento.getPorcentaje());
    	        } else {
    	            System.out.println("No se pudo calcular la tasa de crecimiento.");
    	        }

    	        // Prueba 7: Resumen transaccional (sin filtro)
    	        Map<String, ResumenTransaccional> resumen = negocio.obtenerResumenTransaccional(null);
    	        System.out.println("\nResumen transaccional (todos tipos):");
    	        resumen.forEach((tipo, resumenTransaccional) -> {
    	            System.out.println(" - Tipo: " + tipo);
    	            System.out.println("   Volumen: " + resumenTransaccional.getVolumen());
    	            System.out.println("   Monto total: " + resumenTransaccional.getMontoTotal());
    	            System.out.println("   Importe promedio: " + resumenTransaccional.getImportePromedio());
    	        });

    	        // Prueba 8: Préstamos - capital prestado
    	        try {
    	            double capitalPrestado = negocio.obtenerCapitalPrestado();
    	            System.out.println("\nCapital prestado total: $" + capitalPrestado);

    	            int cantidadPrestamos = negocio.obtenerCantidadPrestamos();
    	            System.out.println("Cantidad de préstamos: " + cantidadPrestamos);

    	            double tasaAprobacion = negocio.obtenerTasaAprobacion();
    	            System.out.printf("Tasa de aprobación: %.2f%%\n", tasaAprobacion);

    	            double tasaMorosidad = negocio.obtenerTasaMorosidad();
    	            System.out.printf("Tasa de morosidad: %.2f%%\n", tasaMorosidad);

    	            Map<String, Integer> prestamosPorEstado = negocio.obtenerPrestamosPorEstado();
    	            System.out.println("Préstamos por estado:");
    	            prestamosPorEstado.forEach((estado, cantidad) -> System.out.println(" - " + estado + ": " + cantidad));

    	            Map<String, Map<String, Integer>> prestamosPorMesEstado = negocio.obtenerPrestamosPorMesEstado();
    	            System.out.println("Préstamos por mes y estado:");
    	            prestamosPorMesEstado.forEach((mes, estadoMap) -> {
    	                System.out.println("Mes: " + mes);
    	                estadoMap.forEach((estado, cantidad) -> System.out.println("   " + estado + ": " + cantidad));
    	            });

    	        } catch (Exception e) {
    	            System.err.println("Error al obtener datos de préstamos: " + e.getMessage());
    	            e.printStackTrace();
    	        }
    }
}
