package datosImpl;


import java.util.*;

public class MockDataProvider {

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
}