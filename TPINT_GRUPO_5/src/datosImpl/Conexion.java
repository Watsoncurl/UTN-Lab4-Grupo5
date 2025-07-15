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
                LOGGER.info("Conexión a la base de datos establecida correctamente. Pool size: " + connectionPool.size());
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
                LOGGER.warning("No hay conexiones disponibles en el pool. Creando una nueva conexión.");
                connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                connection.setAutoCommit(false);
            } else {
                connection = connectionPool.remove(0);
            }

             if (connection == null || connection.isClosed()) {
                LOGGER.warning("La conexión estaba cerrada. Reestableciendo la conexión...");
                connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
                connection.setAutoCommit(false);
                LOGGER.info("Conexión a la base de datos reestablecida correctamente.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al obtener o reestablecer la conexión a la base de datos", e);
            throw new RuntimeException("Error al obtener o reestablecer la conexión a la base de datos", e);
        }
        return connection;
    }

    public void cerrarConexion(Connection connection) { //Accept Connection object as parameter
       if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    if(connectionPool.size() < MAX_POOL_SIZE){
                        connectionPool.add(connection);
                        LOGGER.info("Conexión devuelta al pool. Pool size: " + connectionPool.size());
                    }else{
                         connection.close();
                        LOGGER.info("Pool is full. Conexión cerrada.");
                    }
                }else{
                   LOGGER.warning("La conexión ya estaba cerrada.");
                }
            } catch (SQLException e) {
                LOGGER.log(Level.SEVERE, "Error al cerrar la conexión a la base de datos", e);
            }
        }

    }

    public void rollbackConexion(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.rollback();
                LOGGER.warning("Rollback de la transacción realizado.");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error al hacer rollback de la transacción", e);
        }
    }

    public static void main(String[] args) {
        // Código de prueba para verificar la conexión
        Conexion conexion = Conexion.getConexion();
        Connection con = conexion.getSQLConexion();
        if (con != null) {
            System.out.println("Conexión exitosa!");
            conexion.cerrarConexion(con);
        } else {
            System.err.println("Error al conectar.");
        }
    }
}