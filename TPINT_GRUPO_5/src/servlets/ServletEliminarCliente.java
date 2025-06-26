package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.Conexion;

@WebServlet("/EliminarClienteServlet")
public class ServletEliminarCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        System.out.println("ID recibido para eliminar: " + idParam);

        if (idParam != null && !idParam.isEmpty()) {
            int idCliente = Integer.parseInt(idParam);
            Connection conexion = null;
            PreparedStatement stmt = null;

            try {
                conexion = Conexion.getConexion().getSQLConexion();
                conexion.setAutoCommit(true);  
                String sql = "UPDATE clientes SET estado = 0 WHERE id_cliente = ?";
                stmt = conexion.prepareStatement(sql);
                stmt.setInt(1, idCliente);

                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Cliente con ID " + idCliente + " desactivado correctamente.");
                } else {
                    System.out.println("No se encontró el cliente con ID: " + idCliente);
                }

            } catch (Exception e) {
                System.err.println("Error al desactivar cliente: " + e.getMessage());
            } finally {
                try {
                    if (stmt != null) stmt.close();
                } catch (Exception e) {
                    System.err.println("Error al cerrar statement: " + e.getMessage());
                }
                if (conexion != null) {
                    Conexion.getConexion().cerrarConexion();
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/ListarClientesServlet");
    }
}
