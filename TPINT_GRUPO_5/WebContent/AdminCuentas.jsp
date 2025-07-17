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
    <c:set var="busqueda" value="${empty param.busqueda ? '' : param.busqueda}" />
    <c:set var="tipoCuenta" value="${empty param.tipoCuenta ? 'Todos los tipos' : param.tipoCuenta}" />
    <c:set var="estadoFiltro" value="${empty param.estado ? '' : param.estado}" />
    <form id="formularioFiltroCuentas" class="row mb-3 g-2" method="get" action="ListarCuentasServlet">
      <div class="col-md-6">
        <input type="search" name="busqueda" id="barraBusqueda" value="${busqueda}" class="form-control"
               placeholder="Buscar (Nro. Cuenta, CBU, Cliente)">
      </div>
      <div class="col-md-2">
        <select id="selectTipoCuenta" name="tipoCuenta" class="form-select">
          <option ${tipoCuenta == '' ? 'selected' : ''}value="">Todos los tipos</option>
          <option ${tipoCuenta == 'Caja de Ahorro' ? 'selected' : ''}>Caja de Ahorro</option>
          <option ${tipoCuenta == 'Cuenta Corriente' ? 'selected' : ''}>Cuenta Corriente</option>
          <option ${tipoCuenta == 'Cuenta Sueldo' ? 'selected' : ''}>Cuenta Sueldo</option>
        </select>
      </div>
      <div class="col-md-2">
        <select name="estado" id="estadoCuenta" class="form-select">
          <option ${estadoFiltro == '' ? 'selected' : ''} value="">Todos los estados</option>
          <option ${estadoFiltro == 'Activas' ? 'selected' : ''}>Activas</option>
          <option ${estadoFiltro == 'Inactivas' ? 'selected' : ''}>Inactivas</option>
        </select>
      </div>
      <div class="col-md-2 d-flex">
        <button type="submit" class="btn btn-primary w-100">
          <i class="bi bi-search"></i> Buscar
        </button>
      </div>
    </form>
    <div class="mb-3 text-end">
      <a href="AdminAgregarCuenta.jsp" class="btn btn-success">
        <i class="bi bi-plus-circle"></i> Nueva
      </a>
    </div>
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
                  <a href="${pageContext.request.contextPath}/ServletEditarCuenta?nroCuenta=${cuenta.nro_cuenta}&modo=ver"
                     class="btn btn-sm btn-outline-primary"><i class="bi bi-eye"></i></a>
                  <a href="${pageContext.request.contextPath}/ServletEditarCuenta?nroCuenta=${cuenta.nro_cuenta}&modo=editar"
                     class="btn btn-sm btn-outline-secondary"><i class="bi bi-pencil"></i></a>
                  <c:choose>
                    <c:when test="${cuenta.estado}">
                      <!-- Botón eliminar si está activa -->
                      <form action="${pageContext.request.contextPath}/ListarCuentasServlet" method="post" style="display:inline;">
                        <input type="hidden" name="nro_cuenta" value="${cuenta.nro_cuenta}" />
                        <input type="hidden" name="accion" value="eliminar" />
                        <input type="hidden" name="busqueda" value="${busqueda}" />
                        <input type="hidden" name="tipoCuenta" value="${tipoCuenta}" />
                        <input type="hidden" name="estado" value="${estadoFiltro}" />
                        <input type="hidden" name="pagina" value="${paginaActual}" />
                        <button type="submit" class="btn btn-sm btn-outline-danger"
                                onclick="return confirm('¿Está seguro que desea eliminar esta cuenta?');">
                          <i class="bi bi-trash"></i>
                        </button>
                      </form>
                    </c:when>
                    <c:otherwise>
                      <!-- Botón activar si está inactiva -->
                      <form action="${pageContext.request.contextPath}/ListarCuentasServlet" method="post" style="display:inline;">
                        <input type="hidden" name="nro_cuenta" value="${cuenta.nro_cuenta}" />
                        <input type="hidden" name="accion" value="activar" />
                         <input type="hidden" name="busqueda" value="${busqueda}" />
                        <input type="hidden" name="tipoCuenta" value="${tipoCuenta}" />
                        <input type="hidden" name="estado" value="${estadoFiltro}" />
                        <input type="hidden" name="pagina" value="${paginaActual}" />
                        <button type="submit" class="btn btn-sm btn-outline-success"
                                onclick="return confirm('¿Desea activar esta cuenta?');">
                          <i class="bi bi-check-circle"></i>
                        </button>
                      </form>
                    </c:otherwise>
                  </c:choose>
                </div>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
    <c:if test="${totalPaginas > 1}">
      <nav class="mt-3">
        <ul class="pagination justify-content-center">
          <li class="page-item ${paginaActual == 1 ? 'disabled' : ''}">
            <a class="page-link"
               href="ListarCuentasServlet?pagina=${paginaActual - 1}&busqueda=${busqueda}&tipoCuenta=${tipoCuenta}&estado=${estadoFiltro}">
              Anterior
            </a>
          </li>
          <c:forEach begin="1" end="${totalPaginas}" var="i">
            <li class="page-item ${paginaActual == i ? 'active' : ''}">
              <a class="page-link"
                 href="ListarCuentasServlet?pagina=${i}&busqueda=${busqueda}&tipoCuenta=${tipoCuenta}&estado=${estadoFiltro}">
                ${i}
              </a>
            </li>
          </c:forEach>
          <li class="page-item ${paginaActual == totalPaginas ? 'disabled' : ''}">
            <a class="page-link"
               href="ListarCuentasServlet?pagina=${paginaActual + 1}&busqueda=${busqueda}&tipoCuenta=${tipoCuenta}&estado=${estadoFiltro}">
              Siguiente
            </a>
          </li>
        </ul>
      </nav>
    </c:if>
  </div>
  <my:footer />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
   <script>
   const barraBusqueda = document.getElementById("barraBusqueda");
      const selectTipoCuenta = document.getElementById("selectTipoCuenta");
      const estadoCuenta = document.getElementById("estadoCuenta");
   const formulario = document.getElementById("formularioFiltroCuentas");
   barraBusqueda.addEventListener("input", function () {
     if (this.value.trim() === "") {
       formulario.submit();
     }
     });
   selectTipoCuenta.addEventListener("change", function () {
       if (this.value === "") {
         formulario.submit();
       }
   });
   estadoCuenta.addEventListener("change", function () {
       if (this.value === "") {
         formulario.submit();
       }
   });
</script>
</body>
</html>