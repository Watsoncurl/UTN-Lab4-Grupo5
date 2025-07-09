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


	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        System.out.println("Usuario: " + usuario); // ***
        System.out.println("Contrase a: " + contrasena); // ***

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
                    response.sendRedirect("AdminDashboard.jsp"); 
                } else {
                    response.sendRedirect("ClienteDashboard.jsp"); 
                    int idCliente = resultados.getInt("id_cliente");
                    System.out.println("idCliente: " + idCliente);
                    session.setAttribute("idCliente", idCliente);
                    
                }
            } else {
                
                request.setAttribute("error", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }


        } catch (SQLException e) {
           
            System.err.println("Error al iniciar sesi n: " + e.getMessage());
            request.setAttribute("error", "Error al iniciar sesi n: " + e.getMessage());
            request.getRequestDispatcher("Login.jsp").forward(request, response);

            if (conexion != null) {
                try {
                    Conexion.getConexion().rollbackConexion();
                } catch (Exception ex) {
                    System.err.println("Error al hacer rollback: " + ex.getMessage());
                }
            }

        } 
    }
}