package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.IPersonaDao;
import entidad.Persona;

public class PersonaDaoImpl implements IPersonaDao {

    private static final String Insertar = "INSERT INTO Personas (Dni, Nombre, Apellido) VALUES (?, ?, ?)";
    private static final String ExisteDNI = "SELECT COUNT(*) FROM Personas WHERE Dni = ?";
    private static final String Modificar = "UPDATE personas SET Nombre = ?, Apellido = ? WHERE Dni = ?";
    private static final String Eliminar = "DELETE FROM Personas WHERE Dni = ?";
    private static final String Listar = "SELECT * FROM Personas";

    @Override
    public boolean agregar(Persona persona) {
        PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean insertExitoso = false;
        
         try {

            statement = conexion.prepareStatement(ExisteDNI);
            statement.setString(1, persona.getDNI());
            ResultSet rs = statement.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return false; 
            }
		
            statement = conexion.prepareStatement(Insertar);
            statement.setString(1, persona.getDNI());
            statement.setString(2, persona.getNombre());
            statement.setString(3, persona.getApellido());

            if (statement.executeUpdate() > 0) {
                conexion.commit();
                insertExitoso = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return insertExitoso;
    }

    @Override
    public boolean modificar(Persona persona) {
        PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean updateExitoso = false;

        try {
            statement = conexion.prepareStatement(Modificar);
            statement.setString(1, persona.getNombre());
            statement.setString(2, persona.getApellido());
            statement.setString(3, persona.getDNI());
            
            if (statement.executeUpdate() > 0) {
                conexion.commit();
                updateExitoso = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return updateExitoso;
    }

    @Override
    public boolean eliminar(Persona persona) {
        PreparedStatement statement;
        Connection conexion = Conexion.getConexion().getSQLConexion();
        boolean deleteExitoso = false;

        try {
            statement = conexion.prepareStatement(Eliminar);
            statement.setString(1, persona.getDNI());

            if (statement.executeUpdate() > 0) {
                conexion.commit();
                deleteExitoso = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexion.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return deleteExitoso;
    }

    @Override
    public List<Persona> listarPersonas() {
        PreparedStatement statement;
        ResultSet resultSet;
        List<Persona> lista = new ArrayList<>();
        Connection conexion = Conexion.getConexion().getSQLConexion();

        try {
            statement = conexion.prepareStatement(Listar);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String dni = resultSet.getString("Dni");
                String nombre = resultSet.getString("Nombre");
                String apellido = resultSet.getString("Apellido");
                lista.add(new Persona(dni, nombre, apellido));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
