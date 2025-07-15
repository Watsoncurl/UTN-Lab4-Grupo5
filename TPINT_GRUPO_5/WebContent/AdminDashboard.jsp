<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Dashboard Administrativo</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>

  <%-- Verificar el rol del usuario --%>
  <c:choose>
    <c:when test="${sessionScope.idTipoUsuario == 1}">
      <%-- Si el rol es administrador, mostrar el contenido del dashboard --%>
      <my:navbar activeTab="dashboard" userRole="admin" />
      <div class="container mt-5">
        <div class="row mb-4">
          <div class="col-12 text-center">
            <h2 class="fw-bold"><i class="bi bi-speedometer2 me-2"></i>Panel de Administración</h2>
            <p class="lead text-muted">Gestión integral del sistema bancario</p>
          </div>
        </div>
        <div class="row g-4 justify-content-center">
          <!-- Tarjeta Clientes -->
          <div class="col-md-3 col-sm-6">
            <div class="card h-100 border-primary shadow-sm text-center">
              <div class="card-body">
                <i class="bi bi-people-fill text-primary fs-1 mb-3"></i>
                <h5 class="card-title">Clientes</h5>
                <p class="card-text text-muted">Gestión de clientes del banco</p>
                <a href="${pageContext.request.contextPath}/ListarClientesServlet" class="btn btn-outline-primary stretched-link">
  <i class="bi bi-box-arrow-in-right me-1"></i> Acceder
</a>

              </div>
            </div>
          </div>
         <!-- Tarjeta Cuentas -->
<div class="col-md-3 col-sm-6">
  <div class="card h-100 border-success shadow-sm text-center">
    <div class="card-body">
      <i class="bi bi-wallet2 text-success fs-1 mb-3"></i>
      <h5 class="card-title">Cuentas</h5>
      <p class="card-text text-muted">Administración de cuentas</p>
      <a href="${pageContext.request.contextPath}/ListarCuentasServlet" class="btn btn-outline-success stretched-link">
        <i class="bi bi-box-arrow-in-right me-1"></i> Acceder
      </a>
    </div>
  </div>
</div>
          <!-- Tarjeta Préstamos -->
          <div class="col-md-3 col-sm-6">
            <div class="card h-100 border-warning shadow-sm text-center">
              <div class="card-body">
                <i class="bi bi-cash-stack text-warning fs-1 mb-3"></i>
                <h5 class="card-title">Préstamos</h5>
                <p class="card-text text-muted">Gestión de préstamos</p>
                <a href="${pageContext.request.contextPath}/AdminPrestamos.jsp" class="btn btn-outline-warning stretched-link">
                  <i class="bi bi-box-arrow-in-right me-1"></i> Acceder
                </a>
              </div>
            </div>
          </div>
          <!-- Tarjeta Movimientos -->
          <div class="col-md-3 col-sm-6">
            <div class="card h-100 border-info shadow-sm text-center">
              <div class="card-body">
                <i class="bi bi-arrow-left-right text-info fs-1 mb-3"></i>
                <h5 class="card-title">Movimientos</h5>
                <p class="card-text text-muted">Registro de transacciones</p>
                <a href="${pageContext.request.contextPath}/ServletAdminMovimientos" class="btn btn-outline-info stretched-link">
                  <i class="bi bi-box-arrow-in-right me-1"></i> Acceder
                </a>
              </div>
            </div>
          </div>
          <!-- Tarjeta Informes -->
          <div class="col-md-3 col-sm-6">
            <div class="card h-100 border-danger shadow-sm text-center">
              <div class="card-body">
                <i class="bi bi-graph-up text-danger fs-1 mb-3"></i>
                <h5 class="card-title">Informes</h5>
                <p class="card-text text-muted">Reportes y estadísticas</p>
                <a href="${pageContext.request.contextPath}/AdminInformes" class="btn btn-outline-danger stretched-link">
                  <i class="bi bi-box-arrow-in-right me-1"></i> Acceder
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </c:when>
    <c:otherwise>
      <%-- Si no es administrador, redirigir a otra página --%>
      <c:redirect url="index.jsp"/>
    </c:otherwise>
  </c:choose>

  <my:footer />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>