package datosImpl;

import datos.ClienteDao;
import entidades.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDaoImpl implements ClienteDao {

	private Connection conexion;

	public ClientesDaoImpl() {
		conexion = Conexion.getConexion().getSQLConexion();
	}

	@Override
	public List<Cliente> obtenerActivos() {
		List<Cliente> listaClientes = new ArrayList<>();
		String sql = "SELECT id_cliente, dni, cuil, nombre, apellido, sexo, nacionalidad, "
				+ "fecha_nacimiento, direccion, localidad, provincia, email, telefono, estado "
				+ "FROM Clientes WHERE estado = 1";

		try (Statement consulta = conexion.createStatement(); ResultSet resultados = consulta.executeQuery(sql)) {

			while (resultados.next()) {
				listaClientes.add(mapearCliente(resultados));
			}
		} catch (SQLException e) {
			System.err.println("Error al listar clientes activos: " + e.getMessage());
		}
		return listaClientes;
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
		cliente.setLocalidad(rs.getString("localidad"));
		cliente.setProvincia(rs.getString("provincia"));
		cliente.setEmail(rs.getString("email"));
		cliente.setTelefono(rs.getString("telefono"));
		cliente.setEstado(rs.getBoolean("estado"));
		return cliente;
	}

	@Override
	public List<Cliente> obtenerTodos() {
		conexion = Conexion.getConexion().getSQLConexion();
		List<Cliente> listaClientes = new ArrayList<>();
		String sql = "SELECT id_cliente, dni, cuil, nombre, apellido, sexo, nacionalidad, "
				+ "fecha_nacimiento, direccion, localidad, provincia, email, telefono, estado " + "FROM Clientes";

		try (Statement consulta = conexion.createStatement(); ResultSet resultados = consulta.executeQuery(sql)) {

			while (resultados.next()) {
				listaClientes.add(mapearCliente(resultados));
			}
		} catch (SQLException e) {
			System.err.println("Error al listar todos los clientes: " + e.getMessage());
		}

		return listaClientes;
	}

	@Override
	public Cliente obtenerPorId(int id) {
		Cliente clienteSeleccionado = null;
		String sql = "SELECT id_cliente, dni, cuil, nombre, apellido, sexo, nacionalidad, "
				+ "fecha_nacimiento, direccion, localidad, provincia, email, telefono, estado "
				+ "FROM Clientes WHERE id_cliente = ?";

		try (PreparedStatement ps = conexion.prepareStatement(sql)) {
			ps.setInt(1, id);
			System.out.println("DEBUG DAO: Ejecutando consulta para id_cliente = " + id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				clienteSeleccionado = mapearCliente(rs);
				System.out.println("DEBUG DAO: Cliente encontrado: " + clienteSeleccionado.toString());
			}

		} catch (SQLException e) {
			System.err.println("Error en obtenerPorId: " + e.getMessage());
		}

		return clienteSeleccionado;
	}

	@Override
	public boolean insertar(Cliente cliente) {
		
		String sql = "INSERT INTO Clientes (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento, direccion, localidad, provincia, email, telefono, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    

    try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, cliente.getDni());
        ps.setString(2, cliente.getCuil());
        ps.setString(3, cliente.getNombre());
        ps.setString(4, cliente.getApellido());
        ps.setString(5, cliente.getSexo());
        ps.setString(6, cliente.getNacionalidad());
        ps.setString(7, cliente.getFechaNac());
        ps.setString(8, cliente.getDireccion());
        ps.setString(9, cliente.getLocalidad());
        ps.setString(10, cliente.getProvincia());
        ps.setString(11, cliente.getEmail());
        ps.setString(12, cliente.getTelefono());
        ps.setBoolean(13, cliente.isEstado());

        int filasAfectadas = ps.executeUpdate();

        if (filasAfectadas > 0) {
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setIdCliente(rs.getInt(1));
                    System.out.println("Cliente insertado con ID: " + cliente.getIdCliente());
                    conexion.commit();
                    return true;
                }
            }
        }
        return false;
    } catch (SQLException e) {
        System.err.println("Error al insertar cliente: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
	}

	@Override
	public boolean actualizar(Cliente cliente) {

		String sql = "UPDATE Clientes SET dni=?, cuil=?, nombre=?, apellido=?, sexo=?, nacionalidad=?, "
				+ "fecha_nacimiento=?, direccion=?, localidad=?, provincia=?, email=?, telefono=?"
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
			ps.setString(9, cliente.getLocalidad());
			ps.setString(10, cliente.getProvincia());
			ps.setString(11, cliente.getEmail());
			ps.setString(12, cliente.getTelefono());
			ps.setInt(13, cliente.getIdCliente());

			int filasAfectadas = ps.executeUpdate();
			return filasAfectadas > 0;

		} catch (SQLException e) {
			System.err.println("Error al actualizar cliente en DAO: " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean eliminar(int id) {
		String sql = "UPDATE clientes SET estado = 0 WHERE id_cliente = ?";

		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setInt(1, id);
			int filasAfectadas = stmt.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			System.err.println("Error al eliminar cliente: " + e.getMessage());
			return false;
		}
	}

	@Override
	public List<Cliente> listarPaginados(int inicio, int cantidad) {
		List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT id_cliente, dni, cuil, nombre, apellido, sexo, nacionalidad, "
                   + "fecha_nacimiento, direccion, localidad, provincia, email, telefono, estado "
                   + "FROM Clientes ORDER BY apellido, nombre LIMIT ?, ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, inicio);
            ps.setInt(2, cantidad);
            
            try (ResultSet resultados = ps.executeQuery()) {
                while (resultados.next()) {
                    listaClientes.add(mapearCliente(resultados));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al listar clientes paginados: " + e.getMessage());
        }
        return listaClientes;
	}

	@Override
	public int contar() {
		String sql = "SELECT COUNT(*) AS total FROM Clientes";
        return ejecutarConsultaContador(sql);
	}
	
	// Método auxiliar para ejecutar consultas de conteo
    private int ejecutarConsultaContador(String sql) {
        int total = 0;
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error al contar registros: " + e.getMessage());
        }
        return total;
    }
}
