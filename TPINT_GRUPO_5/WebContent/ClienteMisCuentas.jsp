<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <div class="row mb-4">
      <div class="col-12">
        <h1 class="display-5 fw-bold">Mis Cuentas</h1>
        <p class="lead text-muted">Administra tus cuentas bancarias</p>
      </div>
    </div>

    <div class="container mt-4">
  <h2 class="mb-4">Mis Cuentas</h2>

  <c:if test="${empty cuentas}">
    <div class="alert alert-info">No se encontraron cuentas activas.</div>
  </c:if>

  <c:if test="${not empty cuentas}">
    <ul class="list-group shadow-sm">
      <c:forEach var="cuenta" items="${cuentas}">
        <li class="list-group-item d-flex justify-content-between align-items-start">
          <div class="ms-2 me-auto">
            <div class="fw-bold">${cuenta.tipo_cuenta}</div>
            Nro: ${cuenta.nro_cuenta}<br />
            Saldo: $${cuenta.saldo}<br />
          </div>
          <div class="btn-group btn-group-sm">
            <a href="ServletIngresarCuenta?nroCuenta=${cuenta.nro_cuenta}" class="btn btn-outline-primary">Ingresar</a>
            <a href="#" class="btn btn-outline-secondary">Transferir</a>
          </div>
        </li>
      </c:forEach>
    </ul>
  </c:if>
</div>

  </div>

  <my:footer />
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>