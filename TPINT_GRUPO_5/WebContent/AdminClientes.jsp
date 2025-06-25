<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ page import="java.util.List, entidades.Cliente" %>

<!DOCTYPE html>
<html>
<head>
  <title>Clientes - Admin</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="<%= request.getContextPath() %>/css/styles.css" rel="stylesheet">
</head>
<body>
<my:navbar activeTab="clientes" userRole="admin" />

<div class="container mt-4">
  <% String mensaje = (String) request.getAttribute("mensaje"); %>
  <% if (mensaje != null && !mensaje.isEmpty()) { %>
    <div class="alert alert-info"><%= mensaje %></div>
  <% } %>

  <!-- Filtros -->
  <form method="get" action="<%= request.getContextPath() %>/admin/clientes">
    <div class="row mb-3 g-2">
      <div class="col-md-5">
        <input type="search" name="filtro" class="form-control" placeholder="Buscar (DNI, Nombre, Apellido, Email)"
               value="<%= request.getParameter("filtro") != null ? request.getParameter("filtro") : "" %>">
      </div>
      <div class="col-md-2">
        <select name="estado" class="form-select">
          <option value="">Todos los estados</option>
          <option value="true" <%= "true".equals(request.getParameter("estado")) ? "selected" : "" %>>Activos</option>
          <option value="false" <%= "false".equals(request.getParameter("estado")) ? "selected" : "" %>>Inactivos</option>
        </select>
      </div>
      <div class="col-md-2">
        <select name="sexo" class="form-select">
          <option value="">Todos los sexos</option>
          <option value="Masculino" <%= "Masculino".equalsIgnoreCase(request.getParameter("sexo")) ? "selected" : "" %>>Masculino</option>
          <option value="Femenino" <%= "Femenino".equalsIgnoreCase(request.getParameter("sexo")) ? "selected" : "" %>>Femenino</option>
          <option value="Otro" <%= "Otro".equalsIgnoreCase(request.getParameter("sexo")) ? "selected" : "" %>>Otro</option>
        </select>
      </div>
      <div class="col-md-1 d-grid">
        <button type="submit" class="btn btn-primary w-100">
          <i class="bi bi-search"></i>
        </button>
      </div>
      <div class="col-md-2 d-grid">
	    <a href="<%= request.getContextPath() %>/AgregarCliente.jsp" class="btn btn-success">
	      <i class="bi bi-plus-circle"></i> Nuevo Cliente
	    </a>
	  </div>
    </div>
  </form>

  

  <!-- Tabla de clientes -->
  <div class="table-responsive">
    <table class="table table-hover border shadow-sm">
      <thead class="table-light">
        <tr>
          <th>DNI</th>
          <th>Nombre</th>
          <th>Email</th>
          <th>Teléfono</th>
          <th>Localidad</th>
          <th class="text-center">Estado</th>
          <th class="text-end">Acciones</th>
        </tr>
      </thead>
      <tbody>
        <% 
        List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
        if (clientes != null && !clientes.isEmpty()) {
          for (Cliente cliente : clientes) { 
        %>
          <tr>
            <td><%= cliente.getDni() %></td>
            <td><%= cliente.getApellido() %>, <%= cliente.getNombre() %></td>
            <td><%= cliente.getEmail() %></td>
            <td><%= cliente.getTelefono() %></td>
            <td><%= cliente.getLocalidad() %></td>
            <td class="text-center align-middle">
              <% if (cliente.isEstado()) { %>
                <span class="badge bg-success">Activo</span>
              <% } else { %>
                <span class="badge bg-danger">Inactivo</span>
              <% } %>
            </td>
            <td class="text-end">
              <div class="btn-group">
                <a href="<%= request.getContextPath() %>/admin/clientes/ver?id=<%= cliente.getIdCliente() %>" class="btn btn-sm btn-outline-primary">
                  <i class="bi bi-eye"></i>
                </a>
                <a href="<%= request.getContextPath() %>/admin/clientes/editar?id=<%= cliente.getIdCliente() %>" class="btn btn-sm btn-outline-secondary">
                  <i class="bi bi-pencil"></i>
                </a>
                <a href="<%= request.getContextPath() %>/admin/clientes?accion=eliminar&id=<%= cliente.getIdCliente() %>" 
                   class="btn btn-sm btn-outline-danger"
                   onclick="return confirm('¿Está seguro que desea eliminar este cliente?');">
                  <i class="bi bi-trash"></i>
                </a>
              </div>
            </td>
          </tr>
        <% 
          }
        } else {
        %>
          <tr>
            <td colspan="7" class="text-center text-muted">No se encontraron clientes.</td>
          </tr>
        <% } %>
      </tbody>
    </table>
  </div>

  <!-- Paginación dummy -->
  <nav class="mt-3">
    <ul class="pagination justify-content-center">
      <li class="page-item disabled"><span class="page-link">Anterior</span></li>
      <li class="page-item active"><span class="page-link">1</span></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item"><a class="page-link" href="#">Siguiente</a></li>
    </ul>
  </nav>
</div>

<my:footer />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>