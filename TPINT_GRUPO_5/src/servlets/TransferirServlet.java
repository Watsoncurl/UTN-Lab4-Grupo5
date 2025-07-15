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
            List<Cuentas> cuentas = cuentasNegocio.obtenerCuentasPorIdCliente(idCliente);
            request.setAttribute("cuentas", cuentas);
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
            request.getRequestDispatcher("ClienteTransferir.jsp").forward(request, response);
            return;
        }

        try {
            int idCuentaOrigen = Integer.parseInt(idCuentaOrigenStr);
            double importe = Double.parseDouble(importeStr);

            if (importe <= 0) {
                request.setAttribute("error", "El importe debe ser mayor a cero.");
                request.getRequestDispatcher("ClienteTransferir.jsp").forward(request, response);
                return;
            }

            System.out.println("CBU Destino: " + cbuDestino);
            System.out.println("ID Cuenta Origen: " + idCuentaOrigen);
            System.out.println("Importe: " + importe);
            System.out.println("Concepto: " + concepto);

            Cuentas cuentaDestino = this.cuentasNegocio.obtenerCuentaPorCBU(cbuDestino);
            if (cuentaDestino == null) {
                request.setAttribute("error", "CBU no encontrado.");
                request.getRequestDispatcher("ClienteTransferir.jsp").forward(request, response);
                return;
            }

            System.out.println("Cuenta Destino Encontrada: " + cuentaDestino.toString());

            if (idCuentaOrigen == cuentaDestino.getId_cuenta()) {
                request.setAttribute("error", "No puede transferirse a la misma cuenta.");
                request.getRequestDispatcher("ClienteTransferir.jsp").forward(request, response);
                return;
            }

            Transferencia transferencia = new Transferencia();
            transferencia.setIdCuentaOrigen(idCuentaOrigen);
            transferencia.setIdCuentaDestino(cuentaDestino.getId_cuenta());
            transferencia.setImporte(importe);
            transferencia.setConcepto(concepto);
            transferencia.setCbuDestino(cbuDestino);

          
             System.out.println("ID Cuenta Origen: " + transferencia.getIdCuentaOrigen());
            System.out.println("ID Cuenta Destino: " + transferencia.getIdCuentaDestino());
            System.out.println("Importe: " + transferencia.getImporte());
            System.out.println("Concepto: " + transferencia.getConcepto());
            System.out.println("CBU Destino: " + transferencia.getCbuDestino());

            boolean exito = transferenciaNegocio.realizarTransferencia(transferencia);

            if (exito) {
                request.setAttribute("mensaje", "Transferencia realizada con éxito.");
                request.getRequestDispatcher("TransferenciaExitosa.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "No se pudo completar la transferencia.");
                request.getRequestDispatcher("ClienteTransferir.jsp").forward(request, response);
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error en el formato de los datos numéricos.");
            request.getRequestDispatcher("ClienteTransferir.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error inesperado: " + e.getMessage());
            request.getRequestDispatcher("ClienteTransferir.jsp").forward(request, response);
        }
    }
}