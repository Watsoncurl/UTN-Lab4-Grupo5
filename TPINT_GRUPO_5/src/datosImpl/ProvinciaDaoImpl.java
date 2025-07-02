package datosImpl;

import datos.ProvinciaDao;
import entidades.Provincia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProvinciaDaoImpl implements ProvinciaDao {

    private Connection conexion;

    public ProvinciaDaoImpl() {
        conexion = Conexion.getConexion().getSQLConexion();
    }

    @Override
    public List<Provincia> obtenerTodas() {
        List<Provincia> provincias = new ArrayList<>();
        String sql = "SELECT id_provincia, nombre FROM Provincias ORDER BY nombre";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Provincia p = new Provincia();
                p.setIdProvincia(rs.getInt("id_provincia"));
                p.setNombre(rs.getString("nombre"));
                provincias.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener provincias: " + e.getMessage());
        }
        return provincias;
    }
    
    @Override
    public int obtenerIdPorNombre(String nombre) {
        int idProvincia = 0;
        String sql = "SELECT id_provincia FROM Provincias WHERE nombre = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombre);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idProvincia = rs.getInt("id_provincia");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener idProvincia por nombre: " + e.getMessage());
        }
        return idProvincia;
    }
    
    @Override
    public String obtenerNombrePorId(int idProvincia) {
        String nombre = null;
        String sql = "SELECT nombre FROM Provincias WHERE id_provincia = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProvincia);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("nombre");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener nombreProvincia por id: " + e.getMessage());
        }
        return nombre;
    }


}
