package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import datos.CuentasDao;
import datos.TransferenciaDao;
import datosImpl.CuentasDaoImpl;
import datosImpl.TransferenciaDaoImpl;
import entidades.Cuentas;
import entidades.Transferencia;
import negocio.TransferenciaNegocio;
import negocio.CuentasNegocio;
import negocioImpl.TransferenciaNegocioImpl;
import negocioImpl.CuentasNegocioImpl;

@WebServlet("/TransferirServlet")
public class TransferirServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentasNegocio cuentasNegocio;
    private TransferenciaNegocio transferenciaNegocio;

    @Override
    public void init() throws ServletException {
        CuentasDao cuentasDao = new CuentasDaoImpl();
        cuentasNegocio = new CuentasNegocioImpl(cuentasDao);
        TransferenciaDao transferenciaDao = new TransferenciaDaoImpl();
        transferenciaNegocio = new TransferenciaNegocioImpl(transferenciaDao, cuentasNegocio);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("idCliente") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        try {
            int idCliente = (int) session.getAttribute("idCliente");
            // Obtener las cuentas RECIENTES desde la base de datos
            List<Cuentas> cuentas = cuentasNegocio.obtenerCuentasPorIdCliente(idCliente);
            request.setAttribute("cuentas", cuentas);

            // Obtener el ID de la cuenta origen (si existe)
            String cuentaOrigenStr = request.getParameter("cuentaOrigen");
            if (cuentaOrigenStr != null && !cuentaOrigenStr.isEmpty()) {
                request.setAttribute("cuentaOrigen", cuentaOrigenStr);
            }

            request.getRequestDispatcher("ClienteTransferir.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al cargar el formulario: " + e.getMessage());
            request.getRequestDispatcher("ClienteDashboard.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cbuDestino = request.getParameter("cbuDestino");
        String idCuentaOrigenStr = request.getParameter("cuentaOrigen");
        String importeStr = request.getParameter("importe");
        String concepto = request.getParameter("concepto");

        cbuDestino = (cbuDestino != null) ? cbuDestino.trim() : "";
        idCuentaOrigenStr = (idCuentaOrigenStr != null) ? idCuentaOrigenStr.trim() : "";
        importeStr = (importeStr != null) ? importeStr.trim() : "";
        concepto = (concepto != null) ? concepto.trim() : "";

        if (cbuDestino.isEmpty() || idCuentaOrigenStr.isEmpty() || importeStr.isEmpty() || concepto.isEmpty()) {
            request.setAttribute("error", "Faltan datos obligatorios.");
            if (idCuentaOrigenStr != null) request.setAttribute("cuentaOrigen", idCuentaOrigenStr); // Mantener la cuenta seleccionada
            doGet(request, response); // Volver a cargar las cuentas
            return;
        }

        try {
            int idCuentaOrigen = Integer.parseInt(idCuentaOrigenStr);
            double importe = Double.parseDouble(importeStr);

            if (importe <= 0) {
                request.setAttribute("error", "El importe debe ser mayor a cero.");
                request.setAttribute("cuentaOrigen", idCuentaOrigen); // Mantener la cuenta seleccionada
                doGet(request, response); // Volver a cargar las cuentas
                return;
            }

            Cuentas cuentaDestino = this.cuentasNegocio.obtenerCuentaPorCBU(cbuDestino);
            if (cuentaDestino == null) {
                request.setAttribute("error", "CBU no encontrado.");
                request.setAttribute("cuentaOrigen", idCuentaOrigen); // Mantener la cuenta seleccionada
                doGet(request, response); // Volver a cargar las cuentas
                return;
            }

            if (idCuentaOrigen == cuentaDestino.getId_cuenta()) {
                request.setAttribute("error", "No puede transferirse a la misma cuenta.");
                request.setAttribute("cuentaOrigen", idCuentaOrigen); // Mantener la cuenta seleccionada
                doGet(request, response); // Volver a cargar las cuentas
                return;
            }

            Transferencia transferencia = new Transferencia();
            transferencia.setIdCuentaOrigen(idCuentaOrigen);
            transferencia.setIdCuentaDestino(cuentaDestino.getId_cuenta());
            transferencia.setImporte(importe);
            transferencia.setConcepto(concepto);
            transferencia.setCbuDestino(cbuDestino);

            boolean exito = transferenciaNegocio.realizarTransferencia(transferencia);

            if (exito) {
                request.setAttribute("mensaje", "Transferencia realizada con �xito.");
                 request.setAttribute("cuentaOrigen", idCuentaOrigen); // Mantener la cuenta seleccionada

                HttpSession session = request.getSession();
                int idCliente = (int) session.getAttribute("idCliente");
                List<Cuentas> cuentas = cuentasNegocio.obtenerCuentasPorIdCliente(idCliente);

                 
                for (Cuentas cuenta : cuentas) {
                    System.out.println("Cuenta " + cuenta.getId_cuenta() + ": Saldo = " + cuenta.getSaldo());
                }
                  request.setAttribute("cuentaOrigen", idCuentaOrigen);

                request.setAttribute("cuentas", cuentas); 
                request.getRequestDispatcher("TransferenciaExitosa.jsp").forward(request, response);
                //response.sendRedirect("TransferenciaExitosa.jsp?cuentaOrigen=" + idCuentaOrigen); //Redirecciono aca
            } else {
                request.setAttribute("error", "No se pudo completar la transferencia.");
                request.setAttribute("cuentaOrigen", idCuentaOrigen); 
                doGet(request, response); 
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error en el formato de los datos num�ricos.");
            if (idCuentaOrigenStr != null) request.setAttribute("cuentaOrigen", idCuentaOrigenStr); // Mantener la cuenta seleccionada
            doGet(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
              if (idCuentaOrigenStr != null) request.setAttribute("cuentaOrigen", idCuentaOrigenStr); // Mantener la cuenta seleccionada
            doGet(request, response); 
        }
    }
}