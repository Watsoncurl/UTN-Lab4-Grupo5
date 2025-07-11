package entidades;

public class ResumenTransaccional {
    private String tipoMovimiento;
    private int volumen;
    private double montoTotal;
    private double importePromedio;

    public ResumenTransaccional(String tipoMovimiento, int volumen, double montoTotal, double importePromedio) {
        this.tipoMovimiento = tipoMovimiento;
        this.volumen = volumen;
        this.montoTotal = montoTotal;
        this.importePromedio = importePromedio;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public int getVolumen() {
        return volumen;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public double getImportePromedio() {
        return importePromedio;
    }
}
