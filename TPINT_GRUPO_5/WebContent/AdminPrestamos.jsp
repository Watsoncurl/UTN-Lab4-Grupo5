<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
  <title>Préstamos - Admin</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">

  <!-- Opcional: evitar cache para que siempre se refresque -->
  <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate"/>
  <meta http-equiv="Pragma" content="no-cache"/>
  <meta http-equiv="Expires" content="0"/>
</head>
<body>
  <my:navbar activeTab="prestamos" userRole="admin" />

  <div class="container mt-4">

    <!-- Mostrar mensaje de éxito si viene -->
    <c:if test="${not empty sessionScope.mensaje}">
      <div class="alert alert-success alert-dismissible fade show" role="alert">
        ${sessionScope.mensaje}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Cerrar"></button>
      </div>
      <c:remove var="mensaje" scope="session" />
    </c:if>

    <!-- Filtros -->
    <form method="get" action="ServletAdminPrestamos" class="row mb-3 g-2 align-items-center">
      <div class="col-md-4">
        <input type="search" name="busqueda" class="form-control"
               placeholder="Buscar (DNI, Nombre, Apellido)" value="${param.busqueda}" />
      </div>

      <div class="col-md-3">
        <select name="estado" class="form-select">
          <option value="" ${empty param.estado ? 'selected' : ''}>Todos</option>
          <option value="pendiente" ${param.estado == 'pendiente' ? 'selected' : ''}>Pendiente</option>
          <option value="aprobado" ${param.estado == 'aprobado' ? 'selected' : ''}>Aprobado</option>
          <option value="rechazado" ${param.estado == 'rechazado' ? 'selected' : ''}>Rechazado</option>
          <option value="pagado" ${param.estado == 'pagado' ? 'selected' : ''}>Pagado</option>
        </select>
      </div>

      <div class="col-md-2 d-grid">
        <button type="submit" class="btn btn-primary"><i class="bi bi-search"></i> Buscar</button>
      </div>
    </form>

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
            <th class="text-end">Cuota</th>
            <th class="text-center">Estado</th>
            <th class="text-center">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="p" items="${listaPrestamos}">
            <tr>
              <td><c:out value="${p.idPrestamo}" /></td>
              <td><c:out value="${p.cliente.apellido}" />, <c:out value="${p.cliente.nombre}" /> (<c:out value="${p.cliente.dni}" />)</td>
              <td><c:out value="${p.fechaAlta}" /></td>
              <td class="text-end">$<fmt:formatNumber value="${p.importe}" type="number" minFractionDigits="2" /></td>
              <td class="text-end"><c:out value="${p.plazoMeses}" /></td>
              <td class="text-end">$<fmt:formatNumber value="${p.cuotaMensual}" type="number" minFractionDigits="2" /></td>

              <td class="text-center align-middle">
                <c:choose>
                  <c:when test="${p.estado == 'pendiente'}">
                    <span class="badge bg-warning text-dark">Pendiente</span>
                  </c:when>
                  <c:when test="${p.estado == 'aprobado'}">
                    <span class="badge bg-success">Aprobado</span>
                  </c:when>
                  <c:when test="${p.estado == 'rechazado'}">
                    <span class="badge bg-danger">Rechazado</span>
                  </c:when>
                  <c:otherwise>
                    <span class="badge bg-secondary">Pagado</span>
                  </c:otherwise>
                </c:choose>
              </td>

              <td class="text-center align-middle">
                <form action="ServletAdminPrestamosDetalle" method="get" class="d-inline">
                  <input type="hidden" name="id" value="${p.idPrestamo}" />
                  <button type="submit" class="btn btn-sm btn-outline-primary">
                    <i class="bi bi-eye"></i> Ver
                  </button>
                </form>
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
            <a class="page-link" 
               href="ServletAdminPrestamos?pagina=${paginaActual - 1}&busqueda=${fn:escapeXml(param.busqueda)}&estado=${fn:escapeXml(param.estado)}">
               Anterior
            </a>
          </li>

          <c:forEach begin="1" end="${totalPaginas}" var="i">
            <li class="page-item ${paginaActual == i ? 'active' : ''}">
              <a class="page-link" 
                 href="ServletAdminPrestamos?pagina=${i}&busqueda=${fn:escapeXml(param.busqueda)}&estado=${fn:escapeXml(param.estado)}">
                 ${i}
              </a>
            </li>
          </c:forEach>

          <li class="page-item ${paginaActual == totalPaginas ? 'disabled' : ''}">
            <a class="page-link" 
               href="ServletAdminPrestamos?pagina=${paginaActual + 1}&busqueda=${fn:escapeXml(param.busqueda)}&estado=${fn:escapeXml(param.estado)}">
               Siguiente
            </a>
          </li>
        </ul>
      </nav>
    </c:if>
  </div>

  <my:footer />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
