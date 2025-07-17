package negocioImpl;

import datos.ClienteDao;
import datosImpl.ClientesDaoImpl;
import entidades.Cliente;
import filtros.ClientesFiltros;
import negocio.ClientesNegocio;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;  

public class ClientesNegocioImpl implements ClientesNegocio {
    private ClienteDao clienteDao;

    public ClientesNegocioImpl() {
        this.clienteDao = new ClientesDaoImpl();
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteDao.obtenerTodos();
    }

    @Override
    public List<Cliente> listarActivos() {
        return null;
    }

    @Override
    public List<Cliente> filtrar(ClientesFiltros filtro) throws Exception {
        return clienteDao.filtrar(filtro);
    }

    @Override
    public Cliente obtenerPorId(int id) {
        return clienteDao.obtenerPorId(id);
    }

    @Override
    public boolean crear(Cliente cliente) {
        return clienteDao.insertar(cliente);
    }

    @Override
    public boolean actualizar(Cliente cliente) {
        Connection conn = null;
        try {
            conn = ((ClientesDaoImpl)clienteDao).getConexion(); 
            java.sql.Connection sqlConn = (java.sql.Connection) conn;
            sqlConn.setAutoCommit(false); 
            boolean actualizado = clienteDao.actualizar(cliente);
            if (actualizado) {
                sqlConn.commit();
                return true;
            } else {
                sqlConn.rollback();
                return false;
            }
        } catch (Exception e) {
            try {
                java.sql.Connection sqlConn = (java.sql.Connection) conn;
                if (sqlConn != null) sqlConn.rollback(); 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                java.sql.Connection sqlConn = (java.sql.Connection) conn;
                if (sqlConn != null) sqlConn.setAutoCommit(true); 
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean eliminarCliente(int id) {
        Connection conn = null;
        try {
            conn = ((ClientesDaoImpl)clienteDao).getConexion(); 
            java.sql.Connection sqlConn = (java.sql.Connection) conn;
            sqlConn.setAutoCommit(false);
            clienteDao.eliminarCliente(id);
            sqlConn.commit();
            return true;
        } catch (Exception e) {
            try {
                java.sql.Connection sqlConn = (java.sql.Connection) conn;
                if (sqlConn != null) sqlConn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                java.sql.Connection sqlConn = (java.sql.Connection) conn;
                if (sqlConn != null) sqlConn.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean activarCliente(int id) {
         Connection conn = null;
        try {
            conn = ((ClientesDaoImpl)clienteDao).getConexion(); 
          java.sql.Connection sqlConn = (java.sql.Connection) conn;
            sqlConn.setAutoCommit(false);
            clienteDao.activarCliente(id);
            sqlConn.commit();
            return true;
        } catch (Exception e) {
            try {
              java.sql.Connection sqlConn = (java.sql.Connection) conn;
                if (sqlConn != null) sqlConn.rollback();
            } catch (SQLException ex) {
              ex.printStackTrace();
            }
            return false;
        } finally {
            try {
              java.sql.Connection sqlConn = (java.sql.Connection) conn;
                if (sqlConn != null) sqlConn.setAutoCommit(true);
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Cliente> listarPaginados(int inicio, int cantidad) {
        return clienteDao.listarPaginados(inicio, cantidad);
    }

    @Override
    public int contarTotalClientes() {
        return clienteDao.contar();
    }
}