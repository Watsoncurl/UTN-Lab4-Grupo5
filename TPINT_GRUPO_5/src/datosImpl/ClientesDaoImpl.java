package datosImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import datos.ClienteDao;
import entidades.Cliente;

public class ClientesDaoImpl implements ClienteDao  {

	private static final String INSERTAR = "INSERT INTO clientes (id_cliente, dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento ,direccion, localidad, provincia, email, telefono, estado) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	@Override
	public List<Cliente> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	
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
               ps.setString(8, cliente.getFechaNac());
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

	@Override
	public boolean modificar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminar() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean insertar() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
