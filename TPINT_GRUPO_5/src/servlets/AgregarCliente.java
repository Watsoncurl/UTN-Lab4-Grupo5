package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import datosImpl.Conexion;
import entidades.Cliente;
import entidades.Provincia;
import entidades.TiposUsuario;
import entidades.Usuario;
import negocio.ClientesNegocio;
import negocio.ProvinciaNegocio;
import negocio.UsuarioNegocio;
import negocioImpl.ClientesNegocioImpl;
import negocioImpl.ProvinciaNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/AgregarCliente")
public class AgregarCliente extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ClientesNegocio clientesNegocio;
    private UsuarioNegocio usuarioNegocio;
    private ProvinciaNegocio provinciaNegocio;

    public AgregarCliente() {
        super();
        this.clientesNegocio = new ClientesNegocioImpl();
        this.usuarioNegocio = new UsuarioNegocioImpl();
        this.provinciaNegocio = new ProvinciaNegocioImpl();  // inicializar negocio provincias
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Traer lista de provincias para mostrar en el select del formulario
        List<Provincia> provincias = provinciaNegocio.obtenerTodas();
        request.setAttribute("provincias", provincias);

        // Redirigir al JSP de agregar cliente con la lista cargada
        request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("btnGuardar") != null) {

            String dni = request.getParameter("DNI").trim();
            String cuil = request.getParameter("CUIL").trim();
            String nombre = request.getParameter("Nombre").trim();
            String apellido = request.getParameter("Apellido").trim();
            String sexo = request.getParameter("Sexo").trim();
            String nacionalidad = request.getParameter("Nacionalidad").trim();
            String fechaNac = request.getParameter("FechaNac").trim();
            String direccion = request.getParameter("Direccion").trim();
            String localidadStr = request.getParameter("Localidad").trim();
            String provincia = request.getParameter("Provincia").trim();
            String email = request.getParameter("Email").trim();
            String telefono = request.getParameter("Telefono").trim();
            String usuario = request.getParameter("Usuario").trim();
            String contrasenia = request.getParameter("Contrasenia").trim();

            Connection conexion = null;
            boolean operacionExitosa = false;

            try {
                conexion = Conexion.getConexion().getSQLConexion();

                if (conexion == null) {
                    System.err.println("Error: No se pudo establecer conexión a la base de datos.");
                    request.setAttribute("mensaje", "Error interno del servidor: Fallo de conexión.");
                    doGet(request, response); // para recargar provincias y mostrar mensaje
                    return;
                }

                conexion.setAutoCommit(false);

                Cliente clienteNuevo = new Cliente();
                clienteNuevo.setDni(dni);
                clienteNuevo.setCuil(cuil);
                clienteNuevo.setNombre(nombre);
                clienteNuevo.setApellido(apellido);
                clienteNuevo.setSexo(sexo);
                clienteNuevo.setNacionalidad(nacionalidad);
                clienteNuevo.setFechaNac(fechaNac);
                clienteNuevo.setDireccion(direccion);

                // Parsear localidad a int
                int idLocalidad = 0;
                try {
                    idLocalidad = Integer.parseInt(localidadStr);
                } catch (NumberFormatException e) {
                    System.err.println("Localidad inválida: " + localidadStr + ". Se asigna 0.");
                }
                clienteNuevo.setIdLocalidad(idLocalidad);

                clienteNuevo.setProvincia(provincia);
                clienteNuevo.setEmail(email);
                clienteNuevo.setTelefono(telefono);
                clienteNuevo.setEstado(true);

                if (clientesNegocio.crear(clienteNuevo)) {
                    System.out.println("DEBUG: Cliente insertado en DB, IdCliente generado: " + clienteNuevo.getIdCliente());

                    Usuario nuevoUsuario = new Usuario();
                    nuevoUsuario.setUsuario(usuario);
                    nuevoUsuario.setPassword(contrasenia);
                    nuevoUsuario.setEstado(true);
                    nuevoUsuario.setCliente(clienteNuevo);

                    TiposUsuario tipoUsuarioCliente = new TiposUsuario();
                    tipoUsuarioCliente.setIdTipoUsuario(2);  // Supongo que 2 es cliente
                    nuevoUsuario.setTipoUsuario(tipoUsuarioCliente);

                    if (usuarioNegocio.agregarUsuario(nuevoUsuario)) {
                        conexion.commit();
                        operacionExitosa = true;
                        System.out.println("Cliente y Usuario agregados exitosamente. Transacción confirmada.");
                        request.getSession().setAttribute("mensaje", "Cliente y Usuario registrados con éxito!");
                        response.sendRedirect(request.getContextPath() + "/ListarClientesServlet");
                    } else {
                        conexion.rollback();
                        System.err.println("ERROR: No se pudo agregar el usuario. Realizando rollback.");
                        request.setAttribute("mensaje", "Error al registrar el usuario. El cliente no fue guardado.");
                        doGet(request, response); // para recargar provincias y mostrar mensaje
                    }
                } else {
                    conexion.rollback();
                    System.err.println("ERROR: No se pudo agregar el cliente. Realizando rollback.");
                    request.setAttribute("mensaje", "Error al registrar el cliente. No se guardó nada.");
                    doGet(request, response); // para recargar provincias y mostrar mensaje
                }

            } catch (SQLException e) {
                System.err.println("Error de SQL durante la transacción: " + e.getMessage());
                e.printStackTrace();
                try {
                    if (conexion != null && !conexion.getAutoCommit()) {
                        conexion.rollback();
                        System.out.println("DEBUG: Rollback realizado debido a SQLException.");
                    }
                } catch (SQLException rb_e) {
                    System.err.println("Error al realizar rollback después de SQLException: " + rb_e.getMessage());
                }
                request.setAttribute("mensaje", "Error de base de datos. Por favor, intente de nuevo más tarde.");
                doGet(request, response); // para recargar provincias y mostrar mensaje

            } catch (Exception e) {
                System.err.println("Error inesperado al procesar registro de cliente/usuario: " + e.getMessage());
                e.printStackTrace();
                try {
                    if (conexion != null && !conexion.getAutoCommit()) {
                        conexion.rollback();
                        System.out.println("DEBUG: Rollback realizado debido a Exception general.");
                    }
                } catch (SQLException rb_e) {
                    System.err.println("Error al realizar rollback después de Exception general: " + rb_e.getMessage());
                }
                request.setAttribute("mensaje", "Ha ocurrido un error inesperado. Por favor, intente de nuevo.");
                doGet(request, response); // para recargar provincias y mostrar mensaje
            } finally {
                try {
                    if (conexion != null) {
                        conexion.setAutoCommit(true);
                        System.out.println("DEBUG: Auto-commit restaurado.");
                    }
                } catch (SQLException e) {
                    System.err.println("Error al cerrar conexión o restaurar auto-commit en finally: " + e.getMessage());
                }
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/ListarClientesServlet");
        }
    }
}
