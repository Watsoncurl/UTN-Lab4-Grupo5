<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <title>Mis Cuentas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
    <my:navbar activeTab="misCuentas" userRole="cliente" />
    <div class="container mt-4">
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">${error}</div>
        </c:if>
        <c:if test="${not empty mensaje}">
            <div class="alert alert-info" role="alert">${mensaje}</div>
        </c:if>
        <div class="card shadow-sm mb-4">
            <div class="card-header bg-primary text-white">
                <h5 class="mb-0">
                    <i class="bi bi-list-check me-2"></i>Cuenta
                </h5>
            </div>
            <div class="card-body">
               <!-- Select de cuentas -->
            <c:choose>
                <c:when test="${not empty cuentas}">
                    <form action="ServletIngresarCuenta" method="GET" id="cuentaForm">
                        <div class="mb-3">
                            <label for="nroCuenta" class="form-label">Seleccione una cuenta:</label>
                            <select class="form-select" id="nroCuenta" name="nroCuenta" required onchange="document.getElementById('cuentaForm').submit();">
                                <option value="" disabled ${empty nroCuenta ? 'selected' : ''}>Seleccione una cuenta</option>
                                <c:forEach var="cuenta" items="${requestScope.cuentas}">
                                    <option value="${cuenta.nro_cuenta}" ${cuenta.nro_cuenta == nroCuenta ? 'selected' : ''}>${cuenta.nro_cuenta} - ${cuenta.tipo_cuenta}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <p class="text-muted">No hay cuentas disponibles.</p>
                </c:otherwise>
            </c:choose>
        <c:if test="${not empty nroCuenta}">
                <div class="card shadow-sm mb-4">
                    <div class="card-header bg-primary text-white">
                        <h5 class="mb-0">
                            <i class="bi bi-list-check me-2"></i>Detalles de la Cuenta
                        </h5>
                    </div>
                    <div class="card-body">
                        <c:if test="${not empty Cuenta}">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <div class="fw-bold">${Cuenta.tipo_cuenta}</div>
                                    Nro: ${Cuenta.nro_cuenta}<br />
                                    Saldo: $
                                    <fmt:formatNumber value="${Cuenta.saldo}" type="currency" currencySymbol="" maxFractionDigits="2" minFractionDigits="2" /><br />
                                    CBU: ${Cuenta.cbu}
                                </li>
                            </ul>
                        </c:if>
                        <c:if test="${empty Cuenta}">
                            <p class="text-muted">No se encontró la cuenta seleccionada.</p>
                        </c:if>
                    </div>
                </div>
            </c:if>
            </div>
        </div>
        <c:if test="${not empty nroCuenta}">
        <div class="card shadow-sm mb-4">
            <div class="card-header bg-primary text-white">
                <h5 class="mb-0">
                    <i class="bi bi-list-check me-2"></i>Consulta de Movimientos
                </h5>
            </div>
            <div class="card-body">
                <form action="ServletIngresarCuenta" method="GET" class="row g-3 align-items-end">
                     <input type="hidden" name="nroCuenta" value="${nroCuenta}">
                    <div class="col-md-2">
                        <label for="desdeDate" class="form-label fw-bold">Desde</label>
                        <input type="date" name="desdeDate" id="desdeDate" class="form-control" value="${param.desdeDate}" />
                    </div>
                    <div class="col-md-2">
                        <label for="hastaDate" class="form-label fw-bold">Hasta</label>
                        <input type="date" name="hastaDate" id="hastaDate" class="form-control" value="${param.hastaDate}" />
                    </div>
                    <div class="col-md-2 d-grid">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-search me-2"></i>Buscar
                        </button>
                    </div>
                    <div class="col-md-2 d-grid">
                        <button type="button" class="btn btn-outline-secondary" onclick="limpiarFiltros()">
                            <i class="bi bi-arrow-counterclockwise me-2"></i>Limpiar
                        </button>
                    </div>
                    <input type="hidden" name="pagina" value="1">  <!-- Para asegurar que al filtrar volvamos a la primera página -->
                </form>
            </div>
        </div>
        </c:if>
          <c:if test="${not empty nroCuenta}">
        <div class="card shadow-sm">
            <div class="card-header bg-light">
                <h5 class="mb-0">
                    <i class="bi bi-table me-2"></i>Resultados
                </h5>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover table-striped">
                        <thead class="table-light">
                            <tr>
                                <th>Fecha</th>
                                <th>Concepto</th>
                                <th >Importe</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:choose>
                                <c:when test="${not empty listaMovimientos}">
                                    <c:forEach var="movimiento" items="${listaMovimientos}">
                                        <tr>
                                            <td><fmt:formatDate value="${movimiento.fecha}" pattern="dd/MM/yyyy HH:mm" /></td>
                                            <td>${movimiento.concepto}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${movimiento.idTipoMovimiento == 5}">
                                                        <span class="text-success">+</span>
                                                    </c:when>
                                                    <c:when test="${movimiento.idTipoMovimiento == 4}">
                                                        <span class="text-danger">-</span>
                                                    </c:when>
                                                </c:choose>
                                                $<fmt:formatNumber value="${movimiento.importe}" type="currency" currencySymbol="" maxFractionDigits="2" minFractionDigits="2" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <tr>
                                        <td colspan="3" class="text-center">No hay movimientos para esta cuenta.</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
         <%-- Paginación --%>
        <c:if test="${totalPaginas > 1}">
            <nav class="mt-3">
                <ul class="pagination justify-content-center">
                    <%-- Botón Anterior --%>
                    <li class="page-item ${paginaActual == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="ServletIngresarCuenta?pagina=${paginaActual - 1}
                        &nroCuenta=${param.nroCuenta}&desdeDate=${param.desdeDate}&hastaDate=${param.hastaDate}&tipoMovimiento=${param.tipoMovimiento}">Anterior</a>
                    </li>
                    <%-- Páginas --%>
                    <c:forEach begin="1" end="${totalPaginas}" var="i">
                        <li class="page-item ${paginaActual == i ? 'active' : ''}">
                            <a class="page-link" href="ServletIngresarCuenta?pagina=${i}&nroCuenta=${param.nroCuenta}&desdeDate=${param.desdeDate}&hastaDate=${param.hastaDate}&tipoMovimiento=${param.tipoMovimiento}">${i}</a>
                        </li>
                    </c:forEach>
                    <%-- Botón Siguiente --%>
                    <li class="page-item ${paginaActual == totalPaginas ? 'disabled' : ''}">
                        <a class="page-link" href="ServletIngresarCuenta?pagina=${paginaActual + 1}&nroCuenta=${param.nroCuenta}&desdeDate=${param.desdeDate}&hastaDate=${param.hastaDate}&tipoMovimiento=${param.tipoMovimiento}">Siguiente</a>
                    </li>
                </ul>
            </nav>
        </c:if>
          </c:if>
    </div>
    <my:footer />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function limpiarFiltros() {
            document.getElementById('desdeDate').value = '';
            document.getElementById('hastaDate').value = '';
        }
    </script>
</body>
</html>