package entidades;

public class Cliente {

    private int idCliente;
    private String dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private String sexo;
    private String nacionalidad;
    private String fechaNac;
    private String direccion;
    private int idLocalidad;
    private String localidadNombre;
    private int idProvincia;           // <-- Nuevo campo para el ID
    private String provincia;
    private String email;
    private String telefono;
    private boolean estado;

    public Cliente() {}

    public Cliente(int idCliente, String dni, String cuil, String nombre, String apellido, String sexo,
                   String nacionalidad, String fechaNac, String direccion, int idLocalidad, String localidadNombre,
                   int idProvincia, String provincia, String email, String telefono, boolean estado) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.cuil = cuil;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.nacionalidad = nacionalidad;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.idLocalidad = idLocalidad;
        this.localidadNombre = localidadNombre;
        this.idProvincia = idProvincia;       // Seteado acá
        this.provincia = provincia;
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;
    }

    // Getters y setters
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getCuil() { return cuil; }
    public void setCuil(String cuil) { this.cuil = cuil; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }

    public String getFechaNac() { return fechaNac; }
    public void setFechaNac(String fechaNac) { this.fechaNac = fechaNac; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public int getIdLocalidad() { return idLocalidad; }
    public void setIdLocalidad(int idLocalidad) { this.idLocalidad = idLocalidad; }

    public String getLocalidadNombre() { return localidadNombre; }
    public void setLocalidadNombre(String localidadNombre) { this.localidadNombre = localidadNombre; }

    public int getIdProvincia() { return idProvincia; }
    public void setIdProvincia(int idProvincia) { this.idProvincia = idProvincia; }

    public String getProvincia() { return provincia; }
    public void setProvincia(String provincia) { this.provincia = provincia; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public boolean isEstado() { return estado; }
    public void setEstado(boolean estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Cliente [idCliente=" + idCliente + ", dni=" + dni + ", cuil=" + cuil + ", nombre=" + nombre
                + ", apellido=" + apellido + ", sexo=" + sexo + ", nacionalidad=" + nacionalidad
                + ", fechaNac=" + fechaNac + ", direccion=" + direccion + ", idLocalidad=" + idLocalidad
                + ", localidadNombre=" + localidadNombre + ", idProvincia=" + idProvincia + ", provincia=" + provincia
                + ", email=" + email + ", telefono=" + telefono + ", estado=" + estado + "]";
    }
}
