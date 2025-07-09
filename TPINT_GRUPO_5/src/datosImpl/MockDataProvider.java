package datosImpl;


import java.util.*;

public class MockDataProvider {
	//Datos del informe de segmentos
    public static Map<String, Integer> getClientesPorSegmento() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Básico", 120);
        map.put("Plus", 45);
        map.put("Premium", 15);
        return map;
    }

    public static Map<String, Double> getMontosPorSegmento() {
        Map<String, Double> map = new LinkedHashMap<>();
        map.put("Básico", 3_200_000.0);
        map.put("Plus", 18_500_000.0);
        map.put("Premium", 34_500_000.0);
        return map;
    }

    public static Map<String, Double> calcularPorcentajeSaldo(Map<String, Double> montos) {
        double total = montos.values().stream().mapToDouble(Double::doubleValue).sum();
        Map<String, Double> porcentajes = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : montos.entrySet()) {
            double porcentaje = (entry.getValue() / total) * 100;
            porcentajes.put(entry.getKey(), Math.round(porcentaje * 10.0) / 10.0); // 1 decimal
        }
        return porcentajes;
    }
    
    // Datos del informe de crecimiento
    public static Map<String, Integer> getNuevosClientesPorFecha() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("01/06", 5);
        map.put("05/06", 12);
        map.put("10/06", 8);
        map.put("15/06", 15);
        map.put("20/06", 10);
        map.put("25/06", 20);
        map.put("30/06", 25);
        return map;
    }

    public static Map<String, Integer> getNuevasCuentasPorFecha() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("01/06", 8);
        map.put("05/06", 14);
        map.put("10/06", 10);
        map.put("15/06", 18);
        map.put("20/06", 14);
        map.put("25/06", 25);
        map.put("30/06", 30);
        return map;
    }

    public static Map<String, Integer> getCuentasPorTipo() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Caja de Ahorro", 65);
        map.put("Cuenta Corriente", 40);
        map.put("Cuenta Sueldo", 25);
        return map;
    }

    public static double getTasaCrecimiento() {
        return 42.3;
    }
    
    // Datos del informe de transaccionalidad
    public static Map<String, Integer> getVolumenTransacciones() {
        Map<String, Integer> data = new LinkedHashMap<>();
        data.put("Transferencias", 320);
        data.put("Depósitos", 210);
        data.put("Pagos de Servicios", 130);
        data.put("Extracciones", 90);
        return data;
    }

    public static Map<String, Double> getMontoTransacciones() {
        Map<String, Double> data = new LinkedHashMap<>();
        data.put("Transferencias", 1250000.0);
        data.put("Depósitos", 820000.0);
        data.put("Pagos de Servicios", 330000.0);
        data.put("Extracciones", 450000.0);
        return data;
    }

    public static Map<String, Double> getPromedioTransacciones() {
        Map<String, Integer> volumen = getVolumenTransacciones();
        Map<String, Double> montos = getMontoTransacciones();
        Map<String, Double> promedios = new LinkedHashMap<>();

        for (String tipo : volumen.keySet()) {
            int cantidad = volumen.get(tipo);
            double total = montos.get(tipo);
            double promedio = cantidad == 0 ? 0 : total / cantidad;
            promedios.put(tipo, Math.round(promedio * 10.0) / 10.0);
        }

        return promedios;
    }
    
    // Datos del informe de desempeño de préstamos
    public static Map<String, Integer> getPrestamosActivosPorMes() {
        Map<String, Integer> data = new LinkedHashMap<>();
        data.put("01/03", 40);
        data.put("01/04", 52);
        data.put("01/05", 60);
        data.put("01/06", 75);
        data.put("01/07", 88);
        return data;
    }

    public static Map<String, Double> getMontoPrestamosPorMes() {
        Map<String, Double> data = new LinkedHashMap<>();
        data.put("01/03", 2500000.0);
        data.put("01/04", 3200000.0);
        data.put("01/05", 3900000.0);
        data.put("01/06", 4600000.0);
        data.put("01/07", 5300000.0);
        return data;
    }

    public static double getTasaMorosidad() {
        // Supongamos que es un promedio basado en análisis del último mes
        return 4.7; // 4.7% de morosidad
    }

}