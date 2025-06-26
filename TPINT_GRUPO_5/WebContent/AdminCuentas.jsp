<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
  <title>Cuentas - Admin</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
  <my:navbar activeTab="cuentas" userRole="admin" />
  <div class="container mt-4">
    <div class="row mb-3 g-2">
      <div class="col-md-6">
        <input type="search" class="form-control" placeholder="Buscar (Nro. Cuenta, CBU, Cliente)">
      </div>
      <div class="col-md-2">
        <select class="form-select">
          <option selected>Todos los tipos</option>
          <option>Caja de Ahorro</option>
          <option>Cuenta Corriente</option>
          <option>Cuenta Sueldo</option>
        </select>
      </div>
      <div class="col-md-2">
        <select class="form-select">
          <option selected>Todos los estados</option>
          <option>Activas</option>
          <option>Inactivas</option>
          <option>Bloqueadas</option>
        </select>
      </div>
      <div class="col-md-2">
          <a href="AdminAgregarCuenta.jsp" class="btn btn-success w-100">
              <i class="bi bi-plus-circle"></i> Nueva
          </a>
      </div>
    </div>
    <div class="table-responsive">
      <table class="table table-hover border shadow-sm">
        <thead class="table-light">
          <tr>
            <th>Nro. Cuenta</th>
            <th>Cliente</th>
            <th>Tipo</th>
            <th class="text-end">Saldo</th>
            <th class="text-center">Estado</th>
            <th class="text-end">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="cuenta" items="${listaCuentas}">
            <tr>
              <td>${cuenta.nro_cuenta}</td>
              <td>${cuenta.cliente}</td>  <%--  Muestra el nombre del cliente  --%>
              <td>${cuenta.tipo_cuenta}</td>  <%--  Muestra el tipo de cuenta --%>
              <td class="text-end">$${cuenta.saldo}</td>
              <td class="text-center align-middle">
                <c:choose>
                  <c:when test="${cuenta.estado}">
                    <span class="badge bg-success">Activa</span>
                  </c:when>
                  <c:otherwise>
                    <span class="badge bg-danger">Inactiva</span>
                  </c:otherwise>
                </c:choose>
              </td>
              <td class="text-end">
                <div class="btn-group" role="group">
                  <button class="btn btn-sm btn-outline-primary">
                    <i class="bi bi-eye"></i>
                  </button>
                  <button class="btn btn-sm btn-outline-secondary">
                    <i class="bi bi-pencil"></i>
                  </button>
                  <button class="btn btn-sm btn-outline-danger">
                    <i class="bi bi-trash"></i>
                  </button>
                </div>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <nav class="mt-3">
      <ul class="pagination justify-content-center">
        <li class="page-item disabled">
          <span class="page-link">Anterior</span>
        </li>
        <li class="page-item active"><span class="page-link">1</span></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item"><a class="page-link" href="#">10</a></li>
        <li class="page-item">
          <a class="page-link" href="#">Siguiente</a>
        </li>
      </ul>
    </nav>
  </div>
  <my:footer />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>