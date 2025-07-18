package datosImpl;

import datos.ClienteDao;
import entidades.Cliente;
import filtros.ClientesFiltros;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDaoImpl implements ClienteDao {
    private Connection conexion;
    private Conexion miConexion; 

    public ClientesDaoImpl() {
        miConexion = Conexion.getConexion(); 
        conexion = miConexion.getSQLConexion(); 
    }

    public Connection getConexion() {
        return conexion; 
    }

    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(rs.getInt("id_cliente"));
        cliente.setDni(rs.getString("dni"));
        cliente.setCuil(rs.getString("cuil"));
        cliente.setNombre(rs.getString("nombre"));
        cliente.setApellido(rs.getString("apellido"));
        cliente.setSexo(rs.getString("sexo"));
        cliente.setNacionalidad(rs.getString("nacionalidad"));
        cliente.setFechaNac(rs.getString("fecha_nacimiento"));
        cliente.setDireccion(rs.getString("direccion"));
        cliente.setIdLocalidad(rs.getInt("id_localidad"));
        cliente.setLocalidadNombre(rs.getString("localidad"));
        cliente.setProvincia(rs.getString("provincia"));
        cliente.setEmail(rs.getString("email"));
        cliente.setTelefono(rs.getString("telefono"));
        cliente.setEstado(rs.getBoolean("estado"));
        return cliente;
    }

    @Override
    public List<Cliente> obtenerTodos() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT "
                   + "c.id_cliente, c.dni, c.cuil, c.nombre, c.apellido, c.sexo, "
                   + "c.nacionalidad, c.fecha_nacimiento, c.direccion, c.id_localidad, "
                   + "c.email, c.telefono, c.estado, "
                   + "l.nombre AS localidad, p.nombre AS provincia "
                   + "FROM Clientes c "
                   + "JOIN Localidades l ON c.id_localidad = l.id_localidad "
                   + "JOIN Provincias p ON l.id_provincia = p.id_provincia";
        try (Statement consulta = conexion.createStatement();
             ResultSet resultados = consulta.executeQuery(sql)) {
            while (resultados.next()) {
                listaClientes.add(mapearCliente(resultados));
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return listaClientes;
    }

    @Override
    public void activarCliente(int idCliente) {
        String sql = "UPDATE clientes SET estado = 1 WHERE id_cliente = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, idCliente);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    @Override
    public Cliente obtenerPorId(int id) {
        Cliente clienteSeleccionado = null;
        String sql = "SELECT "
                   + "c.id_cliente, c.dni, c.cuil, c.nombre, c.apellido, c.sexo, "
                   + "c.nacionalidad, c.fecha_nacimiento, c.direccion, c.id_localidad, "
                   + "c.email, c.telefono, c.estado, "
                   + "l.nombre AS localidad, p.nombre AS provincia "
                   + "FROM Clientes c "
                   + "JOIN Localidades l ON c.id_localidad = l.id_localidad " +
                   "JOIN Provincias p ON l.id_provincia = p.id_provincia "
                   + "WHERE c.id_cliente = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                clienteSeleccionado = mapearCliente(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return clienteSeleccionado;
    }

    @Override
    public boolean insertar(Cliente cliente) {
        String sql = "INSERT INTO Clientes (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, id_localidad, email, telefono, estado) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getCuil());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getSexo());
            ps.setString(6, cliente.getNacionalidad());
            ps.setString(7, cliente.getFechaNac());
            ps.setString(8, cliente.getDireccion());
            ps.setInt(9, cliente.getIdLocalidad());
            ps.setString(10, cliente.getEmail());
            ps.setString(11, cliente.getTelefono());
            ps.setBoolean(12, cliente.isEstado());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        cliente.setIdCliente(rs.getInt(1));
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }

    @Override
    public boolean actualizar(Cliente cliente) {
        String sql = "UPDATE Clientes SET dni = ?, cuil = ?, nombre = ?, apellido = ?, sexo = ?, nacionalidad = ?, "
                   + "fecha_nacimiento = ?, direccion = ?, id_localidad = ?, email = ?, telefono = ?, estado = ? "
                   + "WHERE id_cliente = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getCuil());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getSexo());
            ps.setString(6, cliente.getNacionalidad());
            ps.setString(7, cliente.getFechaNac());
            ps.setString(8, cliente.getDireccion());
            ps.setInt(9, cliente.getIdLocalidad());
            ps.setString(10, cliente.getEmail());
            ps.setString(11, cliente.getTelefono());
            ps.setBoolean(12, cliente.isEstado());
            ps.setInt(13, cliente.getIdCliente());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }

    @Override
    public boolean eliminarCliente(int id) {
        String sql = "UPDATE clientes SET estado = 0 WHERE id_cliente = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }

    @Override
    public List<Cliente> listarPaginados(int inicio, int cantidad) {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT c.id_cliente, c.dni, c.cuil, c.nombre, c.apellido, c.sexo, c.nacionalidad, "
                   + "c.fecha_nacimiento, c.direccion, c.email, c.telefono, c.estado, c.id_localidad, "
                   + "l.nombre AS localidad, p.nombre AS provincia "
                   + "FROM Clientes c " +
                   "JOIN Localidades l ON c.id_localidad = l.id_localidad " +
                   "JOIN Provincias p ON l.id_provincia = p.id_provincia " +
                   "ORDER BY c.apellido, c.nombre "
                   + "LIMIT ?, ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, inicio);
            ps.setInt(2, cantidad);
            try (ResultSet resultados = ps.executeQuery()) {
                while (resultados.next()) {
                    listaClientes.add(mapearCliente(resultados));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return listaClientes;
    }

    @Override
    public int contar() {
        String sql = "SELECT COUNT(*) AS total FROM Clientes";
        return ejecutarConsultaContador(sql);
    }

    private int ejecutarConsultaContador(String sql) {
        int total = 0;
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return total;
    }

    @Override
    public List<Cliente> filtrar(ClientesFiltros filtro) {
        List<Cliente> listaClientes = new ArrayList<>();
        String busqueda = filtro.getBusqueda();
        String estado = filtro.getEstado();
        String sexo = filtro.getSexo();
        StringBuilder sql = new StringBuilder(
            "SELECT c.id_cliente, c.dni, c.cuil, c.nombre, c.apellido, c.sexo, c.nacionalidad, " +
            "c.fecha_nacimiento, c.direccion, c.email, c.telefono, c.estado, c.id_localidad, " +
            "l.nombre AS localidad, p.nombre AS provincia " +
            "FROM Clientes c " +
            "JOIN Localidades l ON c.id_localidad = l.id_localidad " +
            "JOIN Provincias p ON l.id_provincia = p.id_provincia " +
            "WHERE 1=1 "
        );

        if (busqueda != null && !busqueda.trim().isEmpty()) {
            sql.append("AND (c.dni LIKE ? OR c.email LIKE ? OR CONCAT(c.nombre, ' ', c.apellido) LIKE ?) ");
        }
        if (estado != null && !estado.isEmpty()) {
            sql.append("AND c.estado = ? ");
        }
        if (sexo != null && !sexo.isEmpty()) {
            sql.append("AND c.sexo = ? ");
        }
        sql.append("ORDER BY c.apellido, c.nombre");

        try (PreparedStatement ps = conexion.prepareStatement(sql.toString())) {
            int paramIndex = 1;

            if (busqueda != null && !busqueda.trim().isEmpty()) {
                String likeParam = "%" + busqueda + "%";
                ps.setString(paramIndex++, likeParam);
                ps.setString(paramIndex++, likeParam);
                ps.setString(paramIndex++, likeParam);
            }
            if (estado != null && !estado.isEmpty()) {
                ps.setBoolean(paramIndex++, estado.equals("1"));
            }
            if (sexo != null && !sexo.isEmpty()) {
                ps.setString(paramIndex++, sexo);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    listaClientes.add(mapearCliente(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return listaClientes;
    }
}