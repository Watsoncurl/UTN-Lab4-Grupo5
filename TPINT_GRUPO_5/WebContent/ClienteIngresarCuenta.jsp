<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

	<div class="card shadow-sm mb-4">
		<div class="row mb-4">
			<div class="card-header bg-primary text-white">
				<h5 class="mb-0">
					<i class="bi bi-list-check me-2"></i>Cuenta
				</h5>
			</div>
		</div>

		<div class="container-fluid mt-0">

			<c:if test="${not empty Cuenta}">
				<ul class="list-group shadow-sm">
					<li class="list-group-item d-flex justify-content-between align-items-start">
						<div class="ms-0 me-auto">
							<div class="fw-bold">${Cuenta.tipo_cuenta}</div>
							Nro: ${Cuenta.nro_cuenta}<br />
							Saldo: $
							<fmt:formatNumber value="${Cuenta.saldo}" type="currency" currencySymbol="" maxFractionDigits="2" minFractionDigits="2" />
							<br />
							CBU: ${Cuenta.cbu}<br />
						</div>
					</li>
				</ul>
			</c:if>
		</div>
	</div>

	<div class="card shadow-sm mb-4">
		<div class="card-header bg-primary text-white">
			<h5 class="mb-0">
				<i class="bi bi-list-check me-2"></i>Consulta de Movimientos
			</h5>
		</div>

		<div class="card-body">
			<form action="ServletIngresarCuenta" method="GET" class="row g-3 align-items-end">
	
				<input type="hidden" name="nroCuenta" value="${Cuenta.nro_cuenta}" />

				<div class="col-md-2">
					<label for="desdeDate" class="form-label fw-bold">Desde</label>
					<input type="date" name="desdeDate" id="desdeDate" value="${param.desdeDate}" />
				</div>

				<div class="col-md-2">
					<label for="hastaDate" class="form-label fw-bold">Hasta</label>
					<input type="date" name="hastaDate" id="hastaDate" value="${param.hastaDate}" />
				</div>

				<div class="col-md-2">
				  <label for="tipoMovimiento" class="form-label fw-bold">Tipo</label>
				  <select name="tipoMovimiento" class="form-select" id="tipoMovimiento">
				    <option value="" ${empty param.tipoMovimiento ? 'selected' : ''}>Todos</option>
				    <option value="credito" ${param.tipoMovimiento == 'credito' ? 'selected' : ''}>Crédito</option>
				    <option value="debito" ${param.tipoMovimiento == 'debito' ? 'selected' : ''}>Débito</option>
				  </select>
				</div>


				<div class="col-md-2 d-grid">
					<button type="submit" class="btn btn-primary">
						<i class="bi bi-search me-2"></i>Buscar
					</button>
				</div>

				<div class="col-md-2 d-grid">
					<button type="button" class="btn btn-outline-secondary" onclick="limpiarFiltros()">
						<i class="bi bi-arrow-counterclockwise me-2"></i>Limpiar
					</button>
				</div>
			</form>
		</div>

		<div class="card shadow-sm">
			<div class="card-header bg-light">
				<h5 class="mb-0">
					<i class="bi bi-table me-2"></i>Resultados
				</h5>
			</div>

			<div class="card-body">
				<div class="table-responsive">
					<table class="table table-hover table-striped">
						<thead class="table-light">
							<tr>
								<th>Fecha</th>
								<th>Concepto</th>
								<th>Tipo</th>
								<th class="text-end">Importe</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty listaMovimientos}">
									<c:forEach var="movimiento" items="${listaMovimientos}">
										<tr>
											<td><fmt:formatDate value="${movimiento.fecha}" pattern="dd/MM/yyyy HH:mm" /></td>
											<td>${movimiento.concepto}</td>
											<td>
												<c:choose>
													<c:when test="${movimiento.idTipoMovimiento == 5}">
														<span class="badge bg-success">Crédito</span>
													</c:when>
													<c:when test="${movimiento.idTipoMovimiento == 4}">
														<span class="badge bg-danger">Débito</span>
													</c:when>
													<c:otherwise>
														<span class="badge bg-secondary">Otro</span>
													</c:otherwise>
												</c:choose>
											</td>
											<td class="text-end">
												<c:choose>
													<c:when test="${movimiento.idTipoMovimiento == 5}">
														<span class="text-success">+</span>
													</c:when>
													<c:when test="${movimiento.idTipoMovimiento == 4}">
														<span class="text-danger">-</span>
													</c:when>
												</c:choose>
												$<fmt:formatNumber value="${movimiento.importe}" type="currency" currencySymbol="" maxFractionDigits="2" minFractionDigits="2" />
											</td>
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="4" class="text-center">No hay movimientos para esta cuenta.</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<my:footer />

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		function limpiarFiltros() {
			document.getElementById('desdeDate').value = '';
			document.getElementById('hastaDate').value = '';
			document.getElementById('tipoMovimiento').value = '';
		}
	</script>
</body>
</html>
