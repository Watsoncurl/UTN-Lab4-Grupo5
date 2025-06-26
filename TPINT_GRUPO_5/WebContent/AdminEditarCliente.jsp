<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Editar Cliente</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<link href="${pageContext.request.contextPath}/css/styles.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<my:navbar activeTab="clientes" userRole="admin" />
	<div class="container py-5">
		<div class="row justify-content-center">
			<div class="col-lg-10">
				<div class="card shadow">
					<div class="card-header bg-primary text-white">
						<h2 class="h4 mb-0">
							<i class="bi bi-person-gear me-2"></i>Editar Cliente
						</h2>
					</div>
					<div class="card-body">
						<form action="ServletEditarCliente" method="post"
							class="row g-3 needs-validation" novalidate>
							<%-- Campo oculto para el ID del cliente --%>
							<input type="hidden" name="idCliente"
								value="${cliente.idCliente}" />
							<div class="col-md-6">
								<label for="nombre" class="form-label">Nombre</label>
								<div class="input-group">
									<span class="input-group-text"><i class="bi bi-person"></i></span>
									<input type="text" class="form-control" id="nombre"
										name="nombre" value="${cliente.nombre}" required
										placeholder="Nombre...">
									<div class="invalid-feedback">Por favor ingrese el nombre
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<label for="apellido" class="form-label">Apellido</label>
								<div class="input-group">
									<span class="input-group-text"><i class="bi bi-person"></i></span>
									<input type="text" class="form-control" id="apellido"
										name="apellido" value="${cliente.apellido}" required
										placeholder="Apellido...">
									<div class="invalid-feedback">Por favor ingrese el
										apellido</div>
								</div>
							</div>
							<div class="col-md-4">
								<label for="dni" class="form-label">DNI</label>
								<div class="input-group">
									<span class="input-group-text"><i
										class="bi bi-credit-card"></i></span> <input type="text"
										class="form-control" id="dni" name="dni"
										value="${cliente.dni}" required placeholder="DNI...">
									<div class="invalid-feedback">Por favor ingrese el DNI</div>
								</div>
							</div>
							<div class="col-md-4">
								<label for="cuil" class="form-label">CUIL</label>
								<div class="input-group">
									<span class="input-group-text"><i
										class="bi bi-credit-card"></i></span> <input type="text"
										class="form-control" id="cuil" name="cuil"
										value="${cliente.cuil}" required placeholder="Cuil...">
									<div class="invalid-feedback">Por favor ingrese el CUIL</div>
								</div>
							</div>
							<div class="col-md-4">
								<label for="telefono" class="form-label">Teléfono</label>
								<div class="input-group">
									<span class="input-group-text"><i
										class="bi bi-telephone"></i></span> <input type="text"
										class="form-control" id="telefono" name="telefono"
										value="${cliente.telefono}" required placeholder="Telefono...">
									<div class="invalid-feedback">Por favor ingrese el
										teléfono</div>
								</div>
							</div>
							<div class="col-md-6">
								<label for="email" class="form-label">Email</label>
								<div class="input-group">
									<span class="input-group-text"><i class="bi bi-envelope"></i></span>
									<input type="email" class="form-control" id="email"
										name="email" value="${cliente.email}" required
										placeholder="Email...">
									<div class="invalid-feedback">Por favor ingrese un email
										válido</div>
								</div>
							</div>

							<div class="col-md-6">
								<label for="fechaNacimiento" class="form-label">Fecha de
									nacimiento</label>
								<div class="input-group">
									<span class="input-group-text"><i class="bi bi-calendar"></i></span>
									<%-- Importante: El formato de fecha para input type="date" debe ser YYYY-MM-DD --%>
									<input type="date" class="form-control" id="fechaNacimiento"
										name="fechaNac" value="${cliente.fechaNac}" required>
									<div class="invalid-feedback">Por favor ingrese la fecha
										de nacimiento</div>
								</div>
							</div>

							<div class="col-md-6">
								<label for="nacionalidad" class="form-label">Nacionalidad</label>
								<div class="input-group">
									<span class="input-group-text"><i class="bi bi-globe"></i></span>
									<input type="text" class="form-control" id="nacionalidad"
										name="nacionalidad" value="${cliente.nacionalidad}" required
										placeholder="Nacionalidad...">
									<div class="invalid-feedback">Por favor ingrese la
										nacionalidad</div>
								</div>
							</div>

							<div class="col-md-3">
								<label for="sexo" class="form-label">Sexo</label> <select
									class="form-select" id="sexo" name="sexo" required>
									<option value="">Seleccione...</option>
									<option value="M" ${cliente.sexo eq 'M' ? 'selected' : ''}>Masculino</option>
									<option value="F" ${cliente.sexo eq 'F' ? 'selected' : ''}>Femenino</option>
									<option value="O" ${cliente.sexo eq 'O' ? 'selected' : ''}>Otro</option>
								</select>
								<div class="invalid-feedback">Por favor seleccione el sexo
								</div>
							</div>

							<div class="col-md-3">
								<label for="provincia" class="form-label">Provincia</label>
								<div class="input-group">
									<span class="input-group-text"><i class="bi bi-geo-alt"></i></span>
									<input type="text" class="form-control" id="provincia"
										name="provincia" value="${cliente.provincia}" required
										placeholder="Provincia...">
									<div class="invalid-feedback">Por favor ingrese la
										provincia</div>
								</div>
							</div>

							<div class="col-md-6">
								<label for="localidad" class="form-label">Localidad</label>
								<div class="input-group">
									<span class="input-group-text"><i class="bi bi-geo"></i></span>
									<input type="text" class="form-control" id="localidad"
										name="localidad" value="${cliente.localidad}" required
										placeholder="Localidad...">
									<div class="invalid-feedback">Por favor ingrese la
										localidad</div>
								</div>
							</div>
							<div class="col-md-6">
								<label for="direccion" class="form-label">Dirección</label>
								<div class="input-group">
									<span class="input-group-text"><i class="bi bi-house"></i></span>
									<input type="text" class="form-control" id="direccion"
										name="direccion" value="${cliente.direccion}" required
										placeholder="Direccion...">
									<div class="invalid-feedback">Por favor ingrese la
										dirección</div>
								</div>
							</div>
							<div class="col-12 text-end mt-4">
								<button class="btn btn-primary px-4 me-2" type="submit">
									<i class="bi bi-save me-2"></i>Guardar Cambios
								</button>
								<a href="ListarClientesServlet" class="btn btn-secondary px-4">
									<i class="bi bi-x-circle me-2"></i>Cancelar
								</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<my:footer />
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	<script>
        // Validación de formulario
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
