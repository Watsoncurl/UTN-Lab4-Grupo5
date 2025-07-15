package entidades;

import java.time.LocalDateTime;

public class Transferencia {
    private int id;
    private int idCuentaOrigen;
    private int idCuentaDestino;
    private LocalDateTime fecha;
    private double importe;
    private String concepto;
    private String cbuDestino; 

    public Transferencia() {}

    public Transferencia(int idCuentaOrigen, int idCuentaDestino, double importe, String concepto) {
        this.idCuentaOrigen = idCuentaOrigen;
        this.idCuentaDestino = idCuentaDestino;
        this.importe = importe;
        this.concepto = concepto;
        this.fecha = LocalDateTime.now();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdCuentaOrigen() { return idCuentaOrigen; }
    public void setIdCuentaOrigen(int idCuentaOrigen) { this.idCuentaOrigen = idCuentaOrigen; }

    public int getIdCuentaDestino() { return idCuentaDestino; }
    public void setIdCuentaDestino(int idCuentaDestino) { this.idCuentaDestino = idCuentaDestino; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public double getImporte() { return importe; }
    public void setImporte(double importe) { this.importe = importe; }

    public String getConcepto() { return concepto; }
    public void setConcepto(String concepto) { this.concepto = concepto; }
    
    public String getCbuDestino() {
        return cbuDestino;
    }

    public void setCbuDestino(String cbuDestino) {
        this.cbuDestino = cbuDestino;
    }
}
