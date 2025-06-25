package servlets;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import datosImpl.Conexion;

@WebServlet("/LoginServlet")
public class servletLogin extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        System.out.println("Usuario: " + usuario); // ***
        System.out.println("Contraseña: " + contrasena); // ***

        Connection conexion = null;
        PreparedStatement consulta = null;
        ResultSet resultados = null;

        try {
            // 1. Obtener la conexión a la base de datos
            Conexion connect = Conexion.getConexion();
            conexion = connect.getSQLConexion();

            // 2. Preparar la consulta SQL para obtener el rol del usuario
            String sql = "SELECT id_tipo_usuario FROM Usuarios WHERE usuario = ? AND contrasena = SHA2(?, 256) AND estado = TRUE";
            consulta = conexion.prepareStatement(sql);
            consulta.setString(1, usuario);
            consulta.setString(2, contrasena);

            // 3. Ejecutar la consulta
            resultados = consulta.executeQuery();

            boolean usuarioEncontrado = resultados.next(); // ***
            System.out.println("Usuario encontrado: " + usuarioEncontrado); // ***

            // 4. Verificar si se encontró un usuario y obtener su rol
            if (usuarioEncontrado) {
                int idTipoUsuario = resultados.getInt("id_tipo_usuario");
                System.out.println("idTipoUsuario: " + idTipoUsuario); // ***

                // 5. Crear una sesión HTTP y guardar información del usuario
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario); // Guarda el nombre de usuario
                session.setAttribute("idTipoUsuario", idTipoUsuario); // Guarda el rol (id_tipo_usuario)

                // 6. Redirigir según el rol
                if (idTipoUsuario == 1) { // 1 = Admin
                    response.sendRedirect("AdminDashboard.jsp"); // Redirige al AdminDashboard
                } else {
                    response.sendRedirect("cliente/Dashboard.jsp"); // Redirige al Dashboard del Cliente (o a donde corresponda)
                }
            } else {
                // Usuario o contraseña incorrectos
                request.setAttribute("error", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

            conexion.commit(); // Confirmar la transacción

        } catch (SQLException e) {
            // Manejo de la excepción
            System.err.println("Error al iniciar sesión: " + e.getMessage());
            request.setAttribute("error", "Error al iniciar sesión: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);

            if (conexion != null) {
                try {
                    Conexion.getConexion().rollbackConexion(); // Rollback
                } catch (Exception ex) {
                    System.err.println("Error al hacer rollback: " + ex.getMessage());
                }
            }

        } finally {
            // 7. Cerrar los recursos
            try {
                if (resultados != null) resultados.close();
                if (consulta != null) consulta.close();
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
            if (conexion != null) {
                Conexion.getConexion().cerrarConexion();
            }
        }
    }
}