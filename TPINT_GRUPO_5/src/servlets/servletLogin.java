package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import datosImpl.Conexion;

@WebServlet("/LoginServlet")
public class servletLogin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");
        System.out.println("Usuario: " + usuario);
        System.out.println("Contraseña: " + contrasena);

        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultados = null;

        try {
            Conexion connect = Conexion.getConexion();
            conexion = connect.getSQLConexion();

            String sql = "SELECT id_tipo_usuario, id_cliente FROM Usuarios WHERE usuario = ? AND contrasena = SHA2(?, 256) AND estado = TRUE";
            consulta = conexion.prepareStatement(sql);
            consulta.setString(1, usuario);
            consulta.setString(2, contrasena);
            resultados = consulta.executeQuery();

            boolean usuarioEncontrado = resultados.next();
            System.out.println("Usuario encontrado: " + usuarioEncontrado);

            if (usuarioEncontrado) {
                int idTipoUsuario = resultados.getInt("id_tipo_usuario");
                System.out.println("idTipoUsuario: " + idTipoUsuario);

                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                session.setAttribute("idTipoUsuario", idTipoUsuario);

                if (idTipoUsuario == 1) {
                    // Si es administrador
                    response.sendRedirect("AdminDashboard.jsp");
                } else {
                    // Si es cliente
                    int idCliente = resultados.getInt("id_cliente");
                    System.out.println("idCliente: " + idCliente);
                    session.setAttribute("idCliente", idCliente);

                    // Consulta para obtener nroCuenta del cliente
                    String sqlCuenta = "SELECT nro_cuenta FROM Cuentas WHERE id_cliente = ? AND estado = 1 LIMIT 1";
                    try (PreparedStatement consultaCuenta = conexion.prepareStatement(sqlCuenta)) {
                        consultaCuenta.setInt(1, idCliente);
                        try (ResultSet resultadoCuenta = consultaCuenta.executeQuery()) {
                            if (resultadoCuenta.next()) {
                                String nroCuenta = resultadoCuenta.getString("nro_cuenta");
                                System.out.println("nroCuenta: " + nroCuenta);
                                session.setAttribute("nroCuentaSesion", nroCuenta);
                            }
                        }
                    }

                    response.sendRedirect("ClienteDashboard.jsp");
                }

            } else {
                // Si no se encontró usuario
                request.setAttribute("error", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }

            conexion.commit();

        } catch (SQLException e) {
            System.err.println("Error al iniciar sesión: " + e.getMessage());
            request.setAttribute("error", "Error al iniciar sesión: " + e.getMessage());
            request.getRequestDispatcher("Login.jsp").forward(request, response);

            if (conexion != null) {
                try {
                    Conexion.getConexion().rollbackConexion(conexion);
                } catch (Exception ex) {
                    System.err.println("Error al hacer rollback: " + ex.getMessage());
                }
            }
        } finally {
            try { if (resultados != null) resultados.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (consulta != null) consulta.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conexion != null) {
                Conexion.getConexion().cerrarConexion(conexion);
            }
        }
    }
}
