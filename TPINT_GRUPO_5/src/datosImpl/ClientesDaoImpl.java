package datosImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datos.ClienteDao;
import entidades.Cliente;

public class ClientesDaoImpl implements ClienteDao {

    private static final String INSERTAR = "INSERT INTO clientes (id_cliente, dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento ,direccion, localidad, provincia, email, telefono, estado) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<Cliente> listarTodos() {
        // Datos simulados para pruebas
        List<Cliente> lista = new ArrayList<>();
        lista.add(new Cliente(
                1, "12345678", "20-12345678-3", "Juan", "Pérez", "M", "Argentina",
                java.time.LocalDate.of(1990, 5, 15), "Calle Falsa 123", "Buenos Aires", "Buenos Aires",
                "juan@mail.com", "1134567890", true
        ));
        lista.add(new Cliente(
                2, "87654321", "27-87654321-7", "Ana", "González", "F", "Uruguaya",
                java.time.LocalDate.of(1985, 8, 22), "Av. Siempre Viva 742", "Rosario", "Santa Fe",
                "ana@mail.com", "1145678901", false
        ));
        return lista;
    }

    @Override
    public boolean insertar(Cliente cliente) {
        try (Connection con = Conexion.getConexion().getSQLConexion();
             PreparedStatement ps = con.prepareStatement(INSERTAR)) {

            ps.setInt(1, cliente.getIdCliente());
            ps.setString(2, cliente.getDni());
            ps.setString(3, cliente.getCuil());
            ps.setString(4, cliente.getNombre());
            ps.setString(5, cliente.getApellido());
            ps.setString(6, cliente.getSexo());
            ps.setString(7, cliente.getNacionalidad());
            ps.setDate(8, Date.valueOf(cliente.getFechaNac())); // Convertir LocalDate a java.sql.Date
            ps.setString(9, cliente.getDireccion());
            ps.setString(10, cliente.getLocalidad());
            ps.setString(11, cliente.getProvincia());
            ps.setString(12, cliente.getEmail());
            ps.setString(13, cliente.getTelefono());
            ps.setBoolean(14, cliente.isEstado());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                con.commit();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Métodos adicionales (a implementar en el futuro)
    @Override
    public boolean modificar(Cliente cliente) {
        // Lógica futura
        return false;
    }

    @Override
    public boolean eliminar(int idCliente) {
        // Lógica futura
        return false;
    }
}
