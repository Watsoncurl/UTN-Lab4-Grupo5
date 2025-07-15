package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import entidades.Cuentas;
import negocio.CuentasNegocio;
import negocioImpl.CuentasNegocioImpl;

@WebServlet("/ServletEditarCuenta")
public class ServletEditarCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentasNegocio cuentaNegocio;

    @Override
    public void init() throws ServletException {
        super.init();
        cuentaNegocio = new CuentasNegocioImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Obtener parámetros de la solicitud
        String nroCuenta = request.getParameter("nroCuenta");
        String modo = request.getParameter("modo");
        
        // 2. Validaciones iniciales
        if (nroCuenta == null || nroCuenta.trim().isEmpty()) {
            manejarError(request, response, "Número de cuenta no proporcionado");
            return;
        }
        
        // Limpiar y estandarizar el número de cuenta
        nroCuenta = nroCuenta.trim();
        
        try {
            // 3. Debug: Mostrar parámetros recibidos
            System.out.println("[DEBUG] Parámetros recibidos - NroCuenta: " + nroCuenta + ", Modo: " + modo);
            
            // 4. Obtener la cuenta desde la capa de negocio
            Cuentas cuenta = cuentaNegocio.obtenerPorNroCuenta(nroCuenta);
            
            // 5. Validar si la cuenta existe
            if (cuenta == null) {
                System.out.println("[WARNING] No se encontró cuenta con número: " + nroCuenta);
                manejarError(request, response, "Cuenta no encontrada");
                return;
            }
            
            // 6. Debug: Mostrar datos de la cuenta encontrada
            System.out.println("[DEBUG] Cuenta encontrada: " + cuenta.getNro_cuenta() + 
                             " - Tipo: " + cuenta.getTipo_cuenta() + 
                             " - Cliente: " + cuenta.getCliente());
            
            // 7. Determinar el modo de visualización (con valor por defecto 'ver')
            String modoVista = "ver"; // Valor por defecto
            if ("editar".equalsIgnoreCase(modo)) {
                modoVista = "editar";
            }
            
            // 8. Configurar atributos para la vista
            request.setAttribute("cuenta", cuenta);
            request.setAttribute("modo", modoVista);
            
            // 9. Debug: Mostrar modo de visualización
            System.out.println("[DEBUG] Modo de visualización: " + modoVista);
            
            // 10. Despachar a la vista JSP
            request.getRequestDispatcher("AdminEditarCuenta.jsp").forward(request, response);
            
        } catch (Exception e) {
            // 11. Manejo de excepciones mejorado
            System.err.println("[ERROR] Error al procesar solicitud GET para cuenta: " + nroCuenta);
            e.printStackTrace();
            
            // Mensaje de error más descriptivo
            String mensajeError = "Error al procesar la solicitud: " + 
                                (e.getMessage() != null ? e.getMessage() : "Consulte los logs para más detalles");
            
            manejarError(request, response, mensajeError);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	if ("true".equals(request.getParameter("cambiarModo"))) {
            String nroCuenta = request.getParameter("nroCuenta"); // Cambiamos id por nroCuenta
            String modo = request.getParameter("nuevoModo");
            response.sendRedirect("ServletEditarCuenta?nroCuenta=" + nroCuenta + "&modo=" + modo);
            return;
        }

        try {
            Cuentas cuentaActualizada = mapearCuentaDesdeRequest(request);
            
            boolean exito = cuentaNegocio.actualizar(cuentaActualizada);
            
            if (exito) {
                request.getSession().setAttribute("mensaje", "Cuenta actualizada exitosamente.");
                response.sendRedirect("ServletEditarCuenta?nroCuenta=" + cuentaActualizada.getNro_cuenta() + "&modo=ver");
            } else {
                request.getSession().setAttribute("error", "No se pudo actualizar la cuenta.");

                request.setAttribute("cuenta", cuentaActualizada);
                request.setAttribute("modo", "editar");
                request.getRequestDispatcher("AdminEditarCuenta.jsp").forward(request, response);
            }
            
        } catch (NumberFormatException e) {
            manejarError(request, response, "Error en el formato de los datos: " + e.getMessage());
        } catch (Exception e) {
            manejarError(request, response, "Error al actualizar cuenta: " + e.getMessage());
        }
    }

    private Cuentas mapearCuentaDesdeRequest(HttpServletRequest request) {
        Cuentas cuenta = new Cuentas();
        
        String nroCuentaOriginal = request.getParameter("nroCuentaOriginal");
        cuenta.setNro_cuenta(nroCuentaOriginal != null ? nroCuentaOriginal : request.getParameter("nroCuenta"));
        String saldoStr = request.getParameter("saldo");
        if ( saldoStr != null && !saldoStr.trim().isEmpty()) {
        	cuenta.setSaldo(Double.parseDouble(saldoStr));
        }
        cuenta.setId_cliente(Integer.parseInt(request.getParameter("idCliente")));
        cuenta.setCbu(request.getParameter("cbu"));
        cuenta.setEstado(Boolean.parseBoolean(request.getParameter("estado")));
        cuenta.setTipo_cuenta(request.getParameter("tipoCuenta"));
        
        return cuenta;
    }
    
    private void manejarError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws IOException {
        request.getSession().setAttribute("error", mensaje);
        response.sendRedirect("ListarCuentasServlet");
    }
}