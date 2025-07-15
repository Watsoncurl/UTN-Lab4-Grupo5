<%@ tag description="Barra de navegación con menú centrado adaptado por rol de usuario" pageEncoding="UTF-8" %>
<%@ attribute name="activeTab" required="false" type="java.lang.String" %>
<%@ attribute name="userRole" required="false" type="java.lang.String" description="Rol del usuario (admin/cliente)" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-custom bg-light">
  <div class="container-fluid">
    <!-- Logo -->
    <%
      String logoHref;
      if ("admin".equals(userRole)) {
        logoHref = request.getContextPath() + "/AdminDashboard.jsp";
      } else if ("cliente".equals(userRole)) {
        logoHref = request.getContextPath() + "/ClienteDashboard.jsp";
      } else {
        logoHref = request.getContextPath() + "/Login.jsp";
      }
    %>
    <a class="navbar-brand" href="<%= logoHref %>">
      <img src="${pageContext.request.contextPath}/img/logo.png" width="30" height="30" class="d-inline-block align-top me-2" alt="Logo">
      <span class="d-none d-sm-inline">Banco</span>
    </a>

    <!-- Botón móvil -->
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
      <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Contenido navbar -->
    <div class="collapse navbar-collapse" id="navbarContent">
      <!-- Menú centrado -->
      <div class="position-absolute start-50 translate-middle-x">
        <ul class="navbar-nav">
          <% if ("admin".equals(userRole)) { %>
            <li class="nav-item px-2">
              <a class="nav-link <%= "clientes".equals(jspContext.getAttribute("activeTab")) ? "active" : "" %>" href="${pageContext.request.contextPath}/ListarClientesServlet">Clientes</a>
            </li>
            <li class="nav-item px-2">
              <a class="nav-link <%= "cuentas".equals(jspContext.getAttribute("activeTab")) ? "active" : "" %>" href="${pageContext.request.contextPath}/ListarCuentasServlet?pagina=1">Cuentas</a>
            </li>
            <li class="nav-item px-2">
              <a class="nav-link <%= "prestamos".equals(jspContext.getAttribute("activeTab")) ? "active" : "" %>" href="${pageContext.request.contextPath}/AdminPrestamos.jsp">Préstamos</a>
            </li>
            <li class="nav-item px-2">
              <a class="nav-link <%= "movimientos".equals(jspContext.getAttribute("activeTab")) ? "active" : "" %>" 
                 href="${pageContext.request.contextPath}/ServletAdminMovimientos">
                <i class="bi bi-arrow-left-right me-1"></i> Movimientos
              </a>
            </li>
            <li class="nav-item px-2">
              <a class="nav-link <%= "informes".equals(jspContext.getAttribute("activeTab")) ? "active" : "" %>" href="${pageContext.request.contextPath}/AdminInformes.jsp">Informes</a>
            </li>
          <% } else if ("cliente".equals(userRole)) { %>
            <li class="nav-item px-2">
              <a class="nav-link <%= "misCuentas".equals(jspContext.getAttribute("activeTab")) ? "active" : "" %>" href="${pageContext.request.contextPath}/ServletMisCuentas">Mis Cuentas</a>
            </li>
            <c:choose>
              <c:when test="${not empty sessionScope.nroCuentaSesion}">
                <li class="nav-item px-2">
                  <a class="nav-link <%= "movimientos".equals(jspContext.getAttribute("activeTab")) ? "active" : "" %>" 
                     href="${pageContext.request.contextPath}/ServletIngresarCuenta?nroCuenta=${sessionScope.nroCuentaSesion}">
                    <i class="bi bi-arrow-left-right me-1"></i> Movimientos
                  </a>
                </li>
              </c:when>
              <c:otherwise>
                <li class="nav-item px-2">
                  <a class="nav-link disabled" href="#">Movimientos</a>
                </li>
              </c:otherwise>
            </c:choose>
            <li class="nav-item px-2">
              <a class="nav-link <%= "prestamos".equals(jspContext.getAttribute("activeTab")) ? "active" : "" %>" href="${pageContext.request.contextPath}/ClientePrestamos_1.jsp">Préstamos</a>
            </li>
            <li class="nav-item px-2">
              <a class="nav-link <%= "transferencias".equals(jspContext.getAttribute("activeTab")) ? "active" : "" %>" href="${pageContext.request.contextPath}/ClienteTransferir.jsp">Transferencias</a>
            </li>
          <% } %>
        </ul>
      </div>

      <!-- Botón cerrar sesión -->
      <div class="d-flex ms-auto">
        <% if (userRole != null && !userRole.isEmpty()) { %>
          <a href="${pageContext.request.contextPath}/Login.jsp" class="btn btn-outline-danger me-2">Cerrar sesión</a>
        <% } else { %>
          <a href="${pageContext.request.contextPath}/Login.jsp" class="btn btn-outline-primary me-2">Iniciar sesión</a>
        <% } %>
      </div>
    </div>
  </div>
</nav>
