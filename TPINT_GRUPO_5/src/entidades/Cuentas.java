package entidades;

import java.sql.Timestamp;

public class Cuentas {
    private int id_cuenta;
    private int id_cliente; 
    private String nro_cuenta;
    private String cbu;
    private double saldo;
    private boolean estado;
    private String cliente; 
    private String tipo_cuenta; 
    private Timestamp fecha_creacion; 

    public Cuentas() {}

    public Cuentas(int id_cuenta, int id_cliente, String nro_cuenta, String cbu, double saldo, boolean estado, String cliente, String tipo_cuenta, Timestamp fecha_creacion) {
        this.id_cuenta = id_cuenta;
        this.id_cliente = id_cliente;
        this.nro_cuenta = nro_cuenta;
        this.cbu = cbu;
        this.saldo = saldo;
        this.estado = estado;
        this.cliente = cliente;
        this.tipo_cuenta = tipo_cuenta;
        this.fecha_creacion = fecha_creacion; 
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNro_cuenta() {
        return nro_cuenta;
    }

    public void setNro_cuenta(String nro_cuenta) {
        this.nro_cuenta = nro_cuenta;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    public Timestamp getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Timestamp fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    @Override
    public String toString() {
        return "Cuentas{" +
                "id_cuenta=" + id_cuenta +
                ", id_cliente=" + id_cliente +
                ", nro_cuenta='" + nro_cuenta + '\'' +
                ", cbu='" + cbu + '\'' +
                ", saldo=" + saldo +
                ", estado=" + estado +
                ", cliente='" + cliente + '\'' +
                ", tipo_cuenta='" + tipo_cuenta + '\'' +
                ", fecha_creacion=" + fecha_creacion +
                '}';
    }
}