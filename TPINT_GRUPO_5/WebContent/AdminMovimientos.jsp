<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
  <title>Movimientos - Admin</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
  <my:navbar activeTab="movimientos" userRole="admin" />

  <div class="container mt-4">

    <!-- Formulario de filtros -->
    <form method="get" action="ServletAdminMovimientos" class="row mb-3 g-2 align-items-end">
      <input type="hidden" name="accion" value="buscar">

      <div class="col-md-6">
        <input type="search" name="busqueda" id="barraBusqueda" value="${busqueda}" class="form-control"
          placeholder="Buscar (Nro. Cuenta, Tipo de Cuenta)">
      </div>

      <div class="col-md-2">
        <label for="desdeDate" class="form-label fw-bold">Desde</label>
        <input type="date" id="desdeDate" name="desdeDate" class="form-control" value="${param.desdeDate}">
      </div>

      <div class="col-md-2">
        <label for="hastaDate" class="form-label fw-bold">Hasta</label>
        <input type="date" id="hastaDate" name="hastaDate" class="form-control" value="${param.hastaDate}">
      </div>

     

      <div class="col-md-3 d-flex gap-2">
        <button type="submit" class="btn btn-primary w-100">
          <i class="bi bi-search"></i>
        </button>
        <a href="ServletAdminMovimientos" class="btn btn-outline-secondary w-100">
          <i class="bi bi-arrow-counterclockwise"></i>
        </a>
      </div>
    </form>

    <!-- Mensajes -->
    <c:if test="${not empty sessionScope.mensaje}">
      <div class="alert alert-success alert-dismissible fade show">
        ${sessionScope.mensaje}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
      <c:remove var="mensaje" scope="session" />
    </c:if>

    <c:if test="${not empty sessionScope.error}">
      <div class="alert alert-danger alert-dismissible fade show">
        ${sessionScope.error}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      </div>
      <c:remove var="error" scope="session" />
    </c:if>

    <!-- Tabla de resultados -->
    <div class="table-responsive">
      <table class="table table-hover table-striped border shadow-sm">
        <thead class="table-light">
          <tr>
            <th>Fecha y Hora</th>
            <th>Cuenta</th>
            <th>Concepto</th>
            <th class="text-end">Importe</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="mov" items="${listaMovimientos}">
            <tr>
              <!-- Fecha y hora -->
              <td><fmt:formatDate value="${mov.fecha}" pattern="dd/MM/yyyy HH:mm" /></td>

              <!-- Cuenta -->
              <td>
                <c:forEach var="c" items="${cuentas}">
                  <c:if test="${c.id_cuenta == mov.idCuenta}">
                    ${c.tipo_cuenta} #${c.nro_cuenta}
                  </c:if>
                </c:forEach>
              </td>

              <!-- Concepto -->
              <td>${mov.concepto}</td>

              

              <!-- Importe -->
              <td class="text-end 
                <c:choose>
                  <c:when test="${mov.importe >= 0}">text-success</c:when>
                  <c:otherwise>text-danger</c:otherwise>
                </c:choose>
              ">
                 <c:if test="${mov.importe >= 0}">+</c:if>
  				<fmt:formatNumber value="${mov.importe}" type="number" minFractionDigits="2" maxFractionDigits="2" />
			</td>
            </tr>
          </c:forEach>

          <!-- Sin resultados -->
          <c:if test="${empty listaMovimientos}">
            <tr>
              <td colspan="5" class="text-center">No se encontraron movimientos.</td>
            </tr>
          </c:if>

        </tbody>
      </table>
    </div>

  </div>

  <my:footer />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
