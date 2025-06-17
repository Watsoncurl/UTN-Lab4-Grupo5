package daoImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dao.ISeguroDao;
import entidad.Seguro;


public class SeguroDaoImpl implements ISeguroDao {

    private static final String SQL_INSERTAR = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES (?, ?, ?, ?)";
    private static final String SQL_TRAER_TODOS = "SELECT idSeguro, descripcion, idTipo, costoContratacion, costoAsegurado FROM seguros";
    private static final String SP_TRAER_POR_TIPO = "{call traerSeguroPorIdTipo(?)}";

    @Override
    public boolean insertar(Seguro seguro) {
        try (Connection con = Conexion.getConexion().getSQLConexion();
             PreparedStatement ps = con.prepareStatement(SQL_INSERTAR)) {

            ps.setString(1, seguro.getDescripcion());
            ps.setInt(2, seguro.getIdTipoSeguro());
            ps.setDouble(3, seguro.getCostoContratacion());
            ps.setDouble(4, seguro.getCostoAsegurado());
            
            System.out.println("INSERTANDO:");
            System.out.println("Descripción: " + seguro.getDescripcion());
            System.out.println("Tipo ID: " + seguro.getIdTipoSeguro());
            System.out.println("Costo contratación: " + seguro.getCostoContratacion());
            System.out.println("Costo asegurado: " + seguro.getCostoAsegurado());
            
            
            int filas = ps.executeUpdate();
            if (filas > 0) {
                con.commit();
                return true;
            }

            System.out.println("Filas afectadas: " + filas);

            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public List<Seguro> obtenerTodos() {
        List<Seguro> lista = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = Conexion.getConexion().getSQLConexion();
            st = con.createStatement();
            rs = st.executeQuery(SQL_TRAER_TODOS);
            while (rs.next()) {
                Seguro s = new Seguro();
                s.setIdSeguro(rs.getInt("idSeguro"));
                s.setDescripcion(rs.getString("descripcion"));
                s.setIdTipoSeguro(rs.getInt("idTipo"));
                s.setCostoContratacion(rs.getDouble("costoContratacion"));
                s.setCostoAsegurado(rs.getDouble("costoAsegurado"));
                lista.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }

    @Override
    public List<Seguro> obtenerPorTipo(int idTipo) {
        List<Seguro> lista = new ArrayList<>();
        try (Connection con = Conexion.getConexion().getSQLConexion();
             CallableStatement cs = con.prepareCall(SP_TRAER_POR_TIPO)) {

            cs.setInt(1, idTipo);
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    Seguro s = new Seguro();
                    s.setIdSeguro(rs.getInt("idSeguro"));
                    s.setDescripcion(rs.getString("descripcion"));
                    s.setIdTipoSeguro(rs.getInt("idTipo"));
                    s.setCostoContratacion(rs.getInt("costoContratacion"));
                    s.setCostoAsegurado(rs.getInt("costoAsegurado"));
                    lista.add(s);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
