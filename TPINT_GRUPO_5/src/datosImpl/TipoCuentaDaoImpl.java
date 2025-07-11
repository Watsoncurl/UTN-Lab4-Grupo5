package datosImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import datos.TipoCuentaDao;
import entidades.tipo_cuenta;

public class TipoCuentaDaoImpl implements TipoCuentaDao{

	private Connection conexion;

    public TipoCuentaDaoImpl() {
        conexion = Conexion.getConexion().getSQLConexion();
    }
	 @Override
	    public List<tipo_cuenta> obtenerTodos() {
	        List<tipo_cuenta> tipoCuentas = new ArrayList<>();
	        String sql = "SELECT id_tipo_cuenta, descripcion FROM tipos_cuenta ORDER BY descripcion";
	        try (Statement stmt = conexion.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                tipo_cuenta tc = new tipo_cuenta();
	                tc.setId_tipo_cuenta(rs.getInt("id_tipo_cuenta"));
	                tc.setDescripcion(rs.getString("descripcion"));
	                tipoCuentas.add(tc);
	            }
	        } catch (SQLException e) {
	            System.err.println("Error al obtener tipos de cuenta: " + e.getMessage());
	        }
	        return tipoCuentas;
	    }

}
