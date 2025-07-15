package entidades;

public class Prestamos {

    private int idPrestamo;
    private int idCliente;
    private int idCuenta;
    private String fechaAlta;
    private double importe;
    private int plazoMeses;
    private double cuotaMensual;
    private String estado;
    private Cliente cliente;

    public Prestamos() {}

    public Prestamos(int idPrestamo, int idCliente, int idCuenta, String fechaAlta, double importe, int plazoMeses, double cuotaMensual, String estado, Cliente cliente) {
        this.idPrestamo = idPrestamo;
        this.idCliente = idCliente;
        this.idCuenta = idCuenta;
        this.fechaAlta = fechaAlta;
        this.importe = importe;
        this.plazoMeses = plazoMeses;
        this.cuotaMensual = cuotaMensual;
        this.estado = estado;
        this.cliente = cliente;
    }

    // Getters y setters
    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public int getPlazoMeses() {
        return plazoMeses;
    }

    public void setPlazoMeses(int plazoMeses) {
        this.plazoMeses = plazoMeses;
    }

    public double getCuotaMensual() {
        return cuotaMensual;
    }

    public void setCuotaMensual(double cuotaMensual) {
        this.cuotaMensual = cuotaMensual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    // Métodos auxiliares para facilitar acceso desde JSP
    public String getNombreCliente() {
        return cliente != null ? cliente.getNombre() : "";
    }

    public String getApellidoCliente() {
        return cliente != null ? cliente.getApellido() : "";
    }

    public String getDniCliente() {
        return cliente != null ? cliente.getDni() : "";
    }
}
