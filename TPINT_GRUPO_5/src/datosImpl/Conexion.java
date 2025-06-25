package datosImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static Conexion instancia;
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/banco_digital?useSSL=false";  // Elimina serverTimezone
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root";
    private static final Logger LOGGER = Logger.getLogger(Conexion.class.getName()); // Logger

    private Conexion() {
        try {
            // 1. Cargar el driver (opcional desde JDBC 4.0, pero recomendado)
            Class.forName("com.mysql.jdbc.Driver"); // Usar el driver antiguo
            // 2. Establecer la conexión
            this.connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
            // 3. Deshabilitar el auto-commit (para el manejo de transacciones)
            this.connection.setAutoCommit(false);
            LOGGER.info("Conexión a la base de datos establecida correctamente."); // Log
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al cargar el driver JDBC", e); // Log
            // Considera lanzar una excepción personalizada aquí para indicar un error irrecuperable
            throw new RuntimeException("Error al cargar el driver JDBC", e); // Propagar la excepción
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al conectar a la base de datos", e); // Log
            // Considera lanzar una excepción personalizada aquí para indicar un error irrecuperable
            throw new RuntimeException("Error al conectar a la base de datos", e); // Propagar la excepción
        }
    }

    public static synchronized Conexion getConexion() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public Connection getSQLConexion() {
        return this.connection;
    }

    public void cerrarConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                LOGGER.info("Conexión a la base de datos cerrada correctamente."); // Log
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al cerrar la conexión a la base de datos", e); // Log
        } finally {
            instancia = null; // Resetea la instancia para la próxima vez
        }
    }

    // Método para rollback (importante para transacciones)
    public void rollbackConexion() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.rollback();
                LOGGER.warning("Rollback de la transacción realizado."); // Log
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al hacer rollback de la transacción", e); // Log
        }
    }

    public static void main(String[] args) {
        // Código de prueba para verificar la conexión
        Conexion conexion = Conexion.getConexion();
        Connection con = conexion.getSQLConexion();

        if (con != null) {
            System.out.println("Conexión exitosa!");
            conexion.cerrarConexion();
        } else {
            System.err.println("Error al conectar.");
        }
    }
}