<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Préstamos</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
    <style>
        /* Estilos para la ventana modal */
        /* Asegurarse de que el modal esté en la parte superior */
        .modal {
            z-index: 9999; /* Un valor alto para asegurar que esté en la parte superior */
        }
        /* Estilos básicos del modal */
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }
        /* Estilos para el botón de cierre */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<my:navbar activeTab="prestamos" userRole="cliente"/>
<div class="container mt-4">
      <c:if test="${not empty sessionScope.mensaje}">
                <div class="alert alert-success" role="alert">
                    <i class="bi bi-check-circle me-2"></i>${sessionScope.mensaje}
                    <c:remove var="mensaje" scope="session"/>
                </div>
            </c:if>
             <c:if test="${not empty sessionScope.error}">
                <div class="alert alert-danger" role="alert">
                    <i class="bi bi-exclamation-triangle me-2"></i>${sessionScope.error}
                    <c:remove var="error" scope="session"/>
                </div>
            </c:if>
    <!-- Tabla de resultados -->
    <div class="table-responsive">
        <table class="table table-hover border shadow-sm">
            <thead class="table-light">
            <tr>
                <th>ID</th>
                <th>Cliente</th>
                <th>Fecha Alta</th>
                <th class="text-end">Importe</th>
                <th class="text-end">Plazo</th>
                <th class="text-end">Cuotas Pendientes</th>
                <th class="text-end">Cuotas Pagas</th>
                <th class="text-end">Cuota Mensual</th>
                <th class="text-center">Estado</th>
                <th class="text-center">Acciones</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="p" items="${listaPrestamos}">
                <tr>
                    <td><c:out value="${p.idPrestamo}"/></td>
                    <td><c:out value="${p.cliente.apellido}"/>, <c:out value="${p.cliente.nombre}"/> (<c:out value="${p.cliente.dni}"/>)</td>
                    <td><c:out value="${p.fechaAlta}"/></td>
                    <td class="text-end">$<fmt:formatNumber value="${p.importe}" type="number" minFractionDigits="2"/></td>
                    <td class="text-end"><c:out value="${p.plazoMeses}"/></td>
                    <td class="text-end"><c:out value="${p.cuotasPendientes}"/></td>
                    <td class="text-end"><c:out value="${p.cuotasPagas}"/></td>
                    <td class="text-end">$<fmt:formatNumber value="${p.cuotaMensual}" type="number" minFractionDigits="2"/></td>
                    <td class="text-center align-middle">
                        <c:choose>
                            <c:when test="${p.estado == 'pendiente'}">
                                <span class="badge bg-warning text-dark">Pendiente</span>
                            </c:when>
                            <c:when test="${p.estado == 'aprobado'}">
                                <span class="badge bg-success">Aprobado</span>
                            </c:when>
                            <c:when test="${p.estado == 'pagado'}">
                                <span class="badge bg-secondary">Pagado</span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge bg-danger">Rechazado</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-center align-middle">
                    <c:choose>
                         <c:when test="${p.estado == 'aprobado' && p.cuotasPendientes > 0}">
                             <button type="button" class="btn btn-sm btn-outline-primary pagar-btn" data-bs-toggle="modal"
                                data-bs-target="#confirmarPagoModal" data-id-cuenta="${p.idCuenta}"
                                data-id-prestamo="${p.idPrestamo}" data-cuota-mensual="${p.cuotaMensual}">
                            <i class="bi bi-currency-dollar"></i> Pagar
                            </button>
                        </c:when>
                        <c:when test="${p.estado == 'pagado'}">
                         <span class="text-success"><i class="bi bi-check-circle-fill"></i> Prestamo Pagado</span>
                        </c:when>
                         <c:otherwise>
                              <span class="text-danger"><i class="bi bi-exclamation-triangle-fill"></i> Pendiente de Aprobación</span>
                        </c:otherwise>
                    </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <!-- Paginación -->
    <c:if test="${totalPaginas > 1}">
        <nav class="mt-3">
            <ul class="pagination justify-content-center">
                <li class="page-item ${paginaActual == 1 ? 'disabled' : ''}">
                    <a class="page-link" href="ServletClientePrestamo?pagina=${paginaActual - 1}">Anterior</a>
                </li>
                <c:forEach begin="1" end="${totalPaginas}" var="i">
                    <li class="page-item ${paginaActual == i ? 'active' : ''}">
                        <a class="page-link" href="ServletClientePrestamo?pagina=${i}">${i}</a>
                    </li>
                </c:forEach>
                <li class="page-item ${paginaActual == totalPaginas ? 'disabled' : ''}">
                    <a class="page-link" href="ServletClientePrestamo?pagina=${paginaActual + 1}">Siguiente</a>
                </li>
            </ul>
        </nav>
    </c:if>
</div>
<!-- Modal de confirmación de pago -->
<div class="modal fade" id="confirmarPagoModal" tabindex="-1" aria-labelledby="confirmarPagoModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmarPagoModalLabel">Confirmar Pago</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                ¿Desea pagar la cuota del préstamo por un importe de $<span id="montoCuota"></span>?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                <!-- Formulario que se envía al servlet -->
                <form id="formPagarCuota" action="${pageContext.request.contextPath}/PagarCuotaServlet" method="post">
                    <input type="hidden" name="idCuenta" id="idCuentaInput" value=""/>
                    <input type="hidden" name="idPrestamo" id="idPrestamoInput" value=""/>
                    <button type="submit" class="btn btn-primary">Confirmar Pago</button>
                </form>
            </div>
        </div>
    </div>
</div>
<my:footer/>
<!-- Bootstrap Bundle (Popper included) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        console.log("DOM cargado");
        const confirmarPagoModal = document.getElementById('confirmarPagoModal');
        console.log("Modal:", confirmarPagoModal);
        if (confirmarPagoModal) {
            confirmarPagoModal.addEventListener('show.bs.modal', function (event) {
                console.log("Evento show.bs.modal disparado");
                const button = event.relatedTarget;
                const idCuenta = button.dataset.idCuenta;
                const idPrestamo = button.dataset.idPrestamo;
                const cuotaMensual = button.dataset.cuotaMensual;
                console.log("idCuenta:", idCuenta);
                console.log("idPrestamo:", idPrestamo);
                console.log("cuotaMensual:", cuotaMensual);
                document.getElementById('idCuentaInput').value = idCuenta;
                document.getElementById('idPrestamoInput').value = idPrestamo;
                document.getElementById('montoCuota').textContent = cuotaMensual;
            });
        } else {
            console.error("Modal con ID 'confirmarPagoModal' no encontrado.");
        }
    });
</script>
</body>
</html>