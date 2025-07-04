<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Clientes - Admin</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<link href="${pageContext.request.contextPath}/css/styles.css"
	rel="stylesheet">
</head>
<body>
	<my:navbar activeTab="clientes" userRole="admin" />
	<div class="container mt-4">
		<%-- Barra de busqueda --%>
		<div class="row mb-3 g-2">
			<div class="col-md-6">
				<input type="search" class="form-control"
					placeholder="Buscar (DNI, Nombre, Apellido, Email)">
			</div>
			<div class="col-md-2">
				<select class="form-select">
					<option selected>Todos los estados</option>
					<option>Activos</option>
					<option>Inactivos</option>
				</select>
			</div>
			<div class="col-md-2">
				<select class="form-select">
					<option selected>Todos los sexos</option>
					<option>Masculino</option>
					<option>Femenino</option>
					<option>Otro</option>
				</select>
			</div>
			<div class="col-md-2">
				<a href="AgregarCliente" class="btn btn-success w-100"><i
					class="bi bi-plus-circle"></i> Nuevo
				</a>
			</div>
		</div>
		
		<%-- Mostrar mensajes de operación --%>
		<c:if test="${not empty sessionScope.mensaje}">
			<div class="alert alert-success alert-dismissible fade show">
				${sessionScope.mensaje}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			<c:remove var="mensaje" scope="session" />
		</c:if>

		<c:if test="${not empty sessionScope.error}">
			<div class="alert alert-danger alert-dismissible fade show">
				${sessionScope.error}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			<c:remove var="error" scope="session" />
		</c:if>
		
		<%-- Tabla de clientes --%>
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
						<th>Acciones</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cliente" items="${listaClientes}">
						<tr>
							<td><c:out value="${cliente.dni}" /></td>
							<td><c:out value="${cliente.apellido}, ${cliente.nombre}" /></td>
							<td><c:out value="${cliente.email}" /></td>
							<td><c:out value="${cliente.telefono}" /></td>
							<td><c:out value="${cliente.localidadNombre}" /></td> <!-- Corregido -->
							<td class="text-center align-middle">
								<c:choose>
									<c:when test="${cliente.estado}">
										<span class="badge bg-success">Activo</span>
									</c:when>
									<c:otherwise>
										<span class="badge bg-danger">Inactivo</span>
									</c:otherwise>
								</c:choose>
							</td>
							<td class="text-end">
								<div class="btn-group" role="group">
									<a href="${pageContext.request.contextPath}/ServletEditarCliente?id=${cliente.idCliente}&modo=ver"
									   class="btn btn-sm btn-outline-primary">
									  <i class="bi bi-eye"></i>
									</a>
									<a href="${pageContext.request.contextPath}/ServletEditarCliente?id=${cliente.idCliente}&modo=editar"
									   class="btn btn-sm btn-outline-secondary">
									  <i class="bi bi-pencil"></i>
									</a>
									<form action="${pageContext.request.contextPath}/ListarClientesServlet"
										  method="post" style="display: inline;">
									  <input type="hidden" name="id" value="${cliente.idCliente}">
									  <input type="hidden" name="accion" value="eliminar">
									  <button type="submit" class="btn btn-sm btn-outline-danger"
											  onclick="return confirm('¿Está seguro que desea eliminar permanentemente este cliente?');">
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
		
		<%-- Paginación --%>
		<c:if test="${totalPaginas > 1}">
		  <nav class="mt-3">
		    <ul class="pagination justify-content-center">
		      <%-- Botón Anterior --%>
		      <li class="page-item ${paginaActual == 1 ? 'disabled' : ''}">
		        <a class="page-link" 
		           href="ListarClientesServlet?pagina=${paginaActual - 1}">Anterior</a>
		      </li>
		      
		      <%-- Páginas --%>
		      <c:forEach begin="1" end="${totalPaginas}" var="i">
		        <li class="page-item ${paginaActual == i ? 'active' : ''}">
		          <a class="page-link" href="ListarClientesServlet?pagina=${i}">${i}</a>
		        </li>
		      </c:forEach>
		      
		      <%-- Botón Siguiente --%>
		      <li class="page-item ${paginaActual == totalPaginas ? 'disabled' : ''}">
		        <a class="page-link" 
		           href="ListarClientesServlet?pagina=${paginaActual + 1}">Siguiente</a>
		      </li>
		    </ul>
		  </nav>
		</c:if>
		
	</div>
	<my:footer />
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
