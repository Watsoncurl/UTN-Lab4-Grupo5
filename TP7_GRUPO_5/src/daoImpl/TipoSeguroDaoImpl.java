package daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.ITipoSeguroDao;
import entidad.TipoSeguro;

public class TipoSeguroDaoImpl implements ITipoSeguroDao {

    private static final String listarTipos = "SELECT * FROM tiposeguros";

    @Override
    public List<TipoSeguro> obtenerTipos() {
        List<TipoSeguro> lista = new ArrayList<>();

        try (Connection con = Conexion.getConexion().getSQLConexion();
             PreparedStatement ps = con.prepareStatement(listarTipos);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                TipoSeguro tipo = new TipoSeguro();
                tipo.setIdTipoSeguro(rs.getInt("idTipo"));
                tipo.setNombre(rs.getString("descripcion"));
                lista.add(tipo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
