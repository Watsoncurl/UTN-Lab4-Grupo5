package datosImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

public class Conexion {

    private static Conexion instancia;
    private static final String URL = "jdbc:mysql://localhost:3306/banco_digital?useSSL=false";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "root";
    private static final Logger LOGGER = Logger.getLogger(Conexion.class.getName());

    private List<Connection> connectionPool = new ArrayList<>();
    private static final int MAX_POOL_SIZE = 10;

    private Conexion() {
        // Private constructor to enforce singleton pattern.
        initializeConnectionPool();
    }

    private void initializeConnectionPool() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < MAX_POOL_SIZE; i++) {
                Connection connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                connection.setAutoCommit(false); // Disable autocommit for transaction management
                connectionPool.add(connection);
                LOGGER.info("Conexi�n a la base de datos establecida correctamente. Pool size: " + connectionPool.size());
            }
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Error al cargar el driver JDBC", e);
            throw new RuntimeException("Error al cargar el driver JDBC", e);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al conectar a la base de datos", e);
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }

    public static synchronized Conexion getConexion() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public synchronized Connection getSQLConexion() {
        Connection connection = null;
        try {
             if (connectionPool.isEmpty()) {
                LOGGER.warning("No hay conexiones disponibles en el pool. Creando una nueva conexi�n.");
                connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                connection.setAutoCommit(false);
            } else {
                connection = connectionPool.remove(0);
            }

             if (connection == null || connection.isClosed()) {
                LOGGER.warning("La conexi�n estaba cerrada. Reestableciendo la conexi�n...");
                connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                connection.setAutoCommit(false);
                LOGGER.info("Conexi�n a la base de datos reestablecida correctamente.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener o reestablecer la conexi�n a la base de datos", e);
            throw new RuntimeException("Error al obtener o reestablecer la conexi�n a la base de datos", e);
        }
        return connection;
    }

    public void cerrarConexion(Connection connection) { //Accept Connection object as parameter
       if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    if(connectionPool.size() < MAX_POOL_SIZE){
                        connectionPool.add(connection);
                        LOGGER.info("Conexi�n devuelta al pool. Pool size: " + connectionPool.size());
                    }else{
                         connection.close();
                        LOGGER.info("Pool is full. Conexi�n cerrada.");
                    }
                }else{
                   LOGGER.warning("La conexi�n ya estaba cerrada.");
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al cerrar la conexi�n a la base de datos", e);
            }
        }

    }

    public void rollbackConexion(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.rollback();
                LOGGER.warning("Rollback de la transacci�n realizado.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al hacer rollback de la transacci�n", e);
        }
    }

    public static void main(String[] args) {
        // C�digo de prueba para verificar la conexi�n
        Conexion conexion = Conexion.getConexion();
        Connection con = conexion.getSQLConexion();
        if (con != null) {
            System.out.println("Conexi�n exitosa!");
            conexion.cerrarConexion(con);
        } else {
            System.err.println("Error al conectar.");
        }
    }
}