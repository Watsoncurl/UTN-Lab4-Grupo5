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
    <%-- Barra de búsqueda y filtros --%>
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
        </select>
      </div>
      <div class="col-md-2">
        <a href="AdminAgregarCuenta.jsp" class="btn btn-success w-100">
          <i class="bi bi-plus-circle"></i> Nueva
        </a>
      </div>
    </div>
    
    <%-- Mostrar mensajes de operación --%>
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
    
    <%-- Tabla de cuentas --%>
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
              <td>${cuenta.cliente}</td>
              <td>${cuenta.tipo_cuenta}</td>
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
                  <%-- Botón Ver (modo visualización) --%>
                  <a href="${pageContext.request.contextPath}/ServletEditarCuenta?nroCuenta=${cuenta.nro_cuenta}&modo=ver"
                     class="btn btn-sm btn-outline-primary">
                    <i class="bi bi-eye"></i>
                  </a>
                  
                  <%-- Botón Editar (modo edición) --%>
                  <a href="${pageContext.request.contextPath}/ServletEditarCuenta?nroCuenta=${cuenta.nro_cuenta}&modo=editar"
                     class="btn btn-sm btn-outline-secondary">
                    <i class="bi bi-pencil"></i>
                  </a>
                  
                  <%-- Formulario para eliminar --%>
				  <form action="${pageContext.request.contextPath}/EliminarCuentaServlet" method="post" style="display:inline;">
				      <input type="hidden" name="nro_cuenta" value="${cuenta.nro_cuenta}" />
				      <button type="submit" class="btn btn-sm btn-outline-danger"
				              onclick="return confirm('¿Está seguro que desea eliminar esta cuenta?');">
				        <i class="bi bi-trash"></i>
				      </button>
                  </form>
                </div>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <!-- 
    <%-- Paginación --%>
    <c:if test="${totalPaginas > 1}">
      <nav class="mt-3">
        <ul class="pagination justify-content-center">
          <%-- Botón Anterior --%>
          <li class="page-item ${paginaActual == 1 ? 'disabled' : ''}">
            <a class="page-link" 
               href="ListarCuentasServlet?pagina=${paginaActual - 1}">Anterior</a>
          </li>
          
          <%-- Páginas --%>
          <c:forEach begin="1" end="${totalPaginas}" var="i">
            <li class="page-item ${paginaActual == i ? 'active' : ''}">
              <a class="page-link" href="ListarCuentasServlet?pagina=${i}">${i}</a>
            </li>
          </c:forEach>
          
          <%-- Botón Siguiente --%>
          <li class="page-item ${paginaActual == totalPaginas ? 'disabled' : ''}">
            <a class="page-link" 
               href="ListarCuentasServlet?pagina=${paginaActual + 1}">Siguiente</a>
          </li>
        </ul>
      </nav>
    </c:if>
     -->
  </div>
  <my:footer />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>