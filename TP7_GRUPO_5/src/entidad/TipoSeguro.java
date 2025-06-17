package entidad;

public class TipoSeguro {
    private int idTipoSeguro;
    private String nombre;

    public TipoSeguro() {}

    public TipoSeguro(int idTipoSeguro, String nombre) {
        this.idTipoSeguro = idTipoSeguro;
        this.nombre = nombre;
    }

    public int getIdTipoSeguro() {
        return idTipoSeguro;
    }

    public void setIdTipoSeguro(int idTipoSeguro) {
        this.idTipoSeguro = idTipoSeguro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoSeguro [idTipoSeguro=" + idTipoSeguro + ", nombre=" + nombre + "]";
    }
}

