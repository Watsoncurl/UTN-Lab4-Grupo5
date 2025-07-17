<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:choose>
		<c:when test="${modo eq 'editar'}">Editar Cuenta</c:when>
		<c:otherwise>Detalle de la Cuenta</c:otherwise>
	</c:choose></title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<link href="${pageContext.request.contextPath}/css/styles.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<my:navbar activeTab="cuentas" userRole="admin" />
	<div class="container py-5">
		<div class="row justify-content-center">
			<div class="col-lg-10">
				<div class="card shadow">
					<div class="card-header bg-primary text-white">
						<h2 class="h4 mb-0">
							<i class="bi bi-bank me-2"></i>
							<c:choose>
								<c:when test="${modo eq 'editar'}">Editar Cuenta</c:when>
								<c:otherwise>Detalle de la Cuenta</c:otherwise>
							</c:choose>
						</h2>
					</div>
					<div class="card-body">
						<c:choose>
							<c:when test="${modo eq 'editar'}">
								<form action="ServletEditarCuenta" method="post"
									class="row g-3 needs-validation" novalidate>
							</c:when>
							<c:otherwise>
								<div class="row g-3">
							</c:otherwise>
						</c:choose>
						<input type="hidden" name="idCuenta" value="${cuenta.id_cuenta}" />
						<!-- Número de Cuenta -->
						<div class="col-md-6">
							<label class="form-label">Número de Cuenta</label>
							<div class="input-group">
								<span class="input-group-text"><i
									class="bi bi-credit-card-2-front"></i></span>
								<c:choose>
									<c:when test="${modo eq 'editar'}">
										<input type="text" class="form-control" name="nroCuenta"
											value="${cuenta.nro_cuenta}" required>
										<div class="invalid-feedback">Ingrese el número de
											cuenta</div>
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control bg-light"
											value="${cuenta.nro_cuenta}" readonly>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<!-- CBU -->
						<div class="col-md-6">
							<label class="form-label">CBU</label>
							<div class="input-group">
								<span class="input-group-text"><i class="bi bi-upc-scan"></i></span>
								<c:choose>
									<c:when test="${modo eq 'editar'}">
										<input type="text" class="form-control" name="cbu"
											value="${cuenta.cbu}" required>
										<div class="invalid-feedback">Ingrese el CBU</div>
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control bg-light"
											value="${cuenta.cbu}" readonly>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<!-- Cliente -->
						<div class="col-md-6">
							<label class="form-label">Cliente</label>
							<div class="input-group">
								<span class="input-group-text"><i class="bi bi-person"></i></span>
								<input type="text" class="form-control bg-light"
									value="${cuenta.cliente}" readonly> <input
									type="hidden" name="idCliente" value="${cuenta.id_cliente}" />
							</div>
						</div>
						<!-- Tipo de Cuenta -->
						<div class="col-md-6">
							<label class="form-label">Tipo de Cuenta</label>
							<div class="input-group">
								<span class="input-group-text"><i class="bi bi-card-list"></i></span>
								<c:choose>
									<c:when test="${modo eq 'editar'}">
										<select class="form-select" name="tipoCuenta" required>
											<option value="">Seleccione un tipo</option>
											<c:forEach var="tipoCuenta" items="${listaTiposCuenta}">
												<option value="${tipoCuenta.id_tipo_cuenta}"
													${cuenta.tipo_cuenta == tipoCuenta.descripcion ? 'selected' : ''}>
													${tipoCuenta.descripcion}</option>
											</c:forEach>
										</select>
										<div class="invalid-feedback">Seleccione el tipo de
											cuenta</div>
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control bg-light"
											value="${cuenta.tipo_cuenta}" readonly>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<!-- Saldo -->
						<div class="col-md-4">
							<label class="form-label">Saldo</label>
							<div class="input-group">
								<span class="input-group-text"><i
									class="bi bi-currency-dollar"></i></span>
								<c:choose>
									<c:when test="${modo eq 'editar'}">
										<input type="number" class="form-control" name="saldo"
											value="${cuenta.saldo}" step="0.01" required>
										<div class="invalid-feedback">Ingrese el saldo</div>
									</c:when>
									<c:otherwise>
										<input type="text" class="form-control bg-light"
											value="$${cuenta.saldo}" readonly>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<!-- Fecha de Creación -->
						<div class="col-md-4">
							<label class="form-label">Fecha de Creación</label>
							<div class="input-group">
								<span class="input-group-text"><i class="bi bi-calendar"></i></span>
								<input type="text" class="form-control bg-light"
									value="${cuenta.fecha_creacion}" readonly>
							</div>
						</div>
						<!-- Estado -->
						<div class="col-md-4">
							<label class="form-label">Estado</label> <input type="text"
								class="form-control bg-light"
								value="${cuenta.estado ? 'Activa' : 'Inactiva'}" readonly>
						</div>
						<!-- Botones -->
						<div class="col-12 text-end mt-4">
							<c:choose>
								<c:when test="${modo eq 'editar'}">
									<button class="btn btn-primary px-4 me-2" type="submit">
										<i class="bi bi-save me-2"></i>Guardar
									</button>
									<a href="ListarCuentasServlet" class="btn btn-secondary px-4">
										<i class="bi bi-x-circle me-2"></i>Cancelar
									</a>
								</c:when>
								<c:otherwise>
									<a href="ListarCuentasServlet"
										class="btn btn-primary px-4 me-2"> <i
										class="bi bi-arrow-left me-2"></i>Volver
									</a>
									<c:if test="${cuenta.estado}">
										<a
											href="ServletEditarCuenta?id=${cuenta.id_cuenta}&modo=editar"
											class="btn btn-warning px-4"> <i
											class="bi bi-pencil me-2"></i>Editar
										</a>
									</c:if>
								</c:otherwise>
							</c:choose>
						</div>
						<c:choose>
							<c:when test="${modo eq 'editar'}">
								</form>
							</c:when>
							<c:otherwise>
					</div>
					</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
	</div>
	<my:footer />
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<c:if test="${modo eq 'editar'}">
		<script>
      (function() {
          'use strict'
          const forms = document.querySelectorAll('.needs-validation')
          Array.from(forms).forEach(form => {
              form.addEventListener('submit', event => {
                  if (!form.checkValidity()) {
                      event.preventDefault()
                      event.stopPropagation()
                  }
                  form.classList.add('was-validated')
              }, false)
          })
      })()
    </script>
	</c:if>
</body>
</html>