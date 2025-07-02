package datosImpl;

import datos.LocalidadDao;
import entidades.Localidad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalidadDaoImpl implements LocalidadDao {

    private Connection conexion;

    public LocalidadDaoImpl() {
        conexion = Conexion.getConexion().getSQLConexion();
    }

    @Override
    public List<Localidad> obtenerPorProvincia(int idProvincia) {
        List<Localidad> localidades = new ArrayList<>();
        String sql = "SELECT id_localidad, nombre FROM Localidades WHERE id_provincia = ? ORDER BY nombre";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProvincia);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Localidad l = new Localidad();
                    l.setIdLocalidad(rs.getInt("id_localidad"));
                    l.setNombre(rs.getString("nombre"));
                    l.setIdProvincia(idProvincia);
                    localidades.add(l);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener localidades: " + e.getMessage());
        }
        return localidades;
    }
    
    @Override
    public Localidad obtenerPorId(int idLocalidad) {
        Localidad localidad = null;
        String sql = "SELECT id_localidad, nombre, id_provincia FROM Localidades WHERE id_localidad = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idLocalidad);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    localidad = new Localidad();
                    localidad.setIdLocalidad(rs.getInt("id_localidad"));
                    localidad.setNombre(rs.getString("nombre"));
                    localidad.setIdProvincia(rs.getInt("id_provincia"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener localidad por id: " + e.getMessage());
        }
        return localidad;
    }

}
