package entidades;

public class tipo_cuenta {
    private int id_tipo_cuenta;
    private String descripcion;

    public tipo_cuenta() {}

    public tipo_cuenta(int id_tipo_cuenta, String descripcion) {
        this.id_tipo_cuenta = id_tipo_cuenta;
        this.descripcion = descripcion;
    }

    public int getId_tipo_cuenta() {
        return id_tipo_cuenta;
    }

    public void setId_tipo_cuenta(int id_tipo_cuenta) {
        this.id_tipo_cuenta = id_tipo_cuenta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Tipos_Cuenta{" +
                "id_tipo_cuenta=" + id_tipo_cuenta +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}