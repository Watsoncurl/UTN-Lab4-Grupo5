package entidades;

public class InformeSimple {
    private String columnas;
    private String valores;

    public InformeSimple() {}

    public InformeSimple(String columnas, String valores) {
        this.columnas = columnas;
        this.valores = valores;
    }

    public String getColumnas() {
        return columnas;
    }

    public void setColumnas(String columnas) {
        this.columnas = columnas;
    }

    public String getValores() {
        return valores;
    }

    public void setValores(String valores) {
        this.valores = valores;
    }
}
