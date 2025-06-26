package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datosImpl.ClientesDaoImpl;
import datosImpl.Conexion;
import entidades.Cliente;
import negocioImpl.ClientesNegocioImpl;

/**
 * Servlet implementation class AgregarCliente
 */
@WebServlet("/AgregarCliente")
public class AgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("btnGuardar") != null) {
			
			/// Parametros de la pagina web
			String dni = request.getParameter("DNI").trim();
			String cuil = request.getParameter("CUIL").trim();
			String nombre = request.getParameter("Nombre").trim();
			String apellido = request.getParameter("Apellido").trim();
			String sexo = request.getParameter("Sexo").trim();
			String nacionalidad = request.getParameter("Nacionalidad").trim();
			String fechaNac = request.getParameter("FechaNac").trim();
			String direccion = request.getParameter("Direccion").trim();
			String localidad = request.getParameter("Localidad").trim();
			String provincia = request.getParameter("Provincia").trim();
			String email = request.getParameter("Email").trim();
			String telefono = request.getParameter("Telefono").trim();
			
			System.out.println(dni);
			System.out.println(cuil);
			System.out.println(nombre);
			System.out.println(apellido);
			System.out.println(sexo);
			System.out.println(nacionalidad);
			System.out.println(telefono);
			System.out.println(email);
			System.out.println(fechaNac);
			System.out.println(direccion);
			System.out.println(localidad);
			System.out.println(provincia);
			
			Connection conexion = null;
            PreparedStatement stmt = null;
            
            try {
                conexion = Conexion.getConexion().getSQLConexion();
                conexion.setAutoCommit(true);  
                String sql = "INSERT INTO clientes (dni, cuil, nombre, apellido, sexo, nacionalidad, fecha_nacimiento ,direccion, localidad, provincia, email, telefono, estado) "
            			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stmt = conexion.prepareStatement(sql);
                
                stmt.setString(1, dni);
                stmt.setString(2, cuil);
                stmt.setString(3, nombre);
                stmt.setString(4, apellido);
                stmt.setString(5, sexo);
                stmt.setString(6, nacionalidad);
                stmt.setString(7, fechaNac);
                stmt.setString(8, direccion);
                stmt.setString(9, localidad);
                stmt.setString(10, provincia);
                stmt.setString(11, email);
                stmt.setString(12, telefono);
                stmt.setBoolean(13, true);

                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    System.out.println("Cliente agregado correctamente.");
                } else {
                    System.out.println("No se údo agregar al cliente");
                }

            } catch (Exception e) {
                System.err.println("Error al agregar cliente: " + e.getMessage());
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
		
		//request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
		response.sendRedirect(request.getContextPath() + "/AgregarCliente.jsp");
	}

}
