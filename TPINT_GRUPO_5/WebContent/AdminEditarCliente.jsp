<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        <c:choose>
            <c:when test="${modo eq 'editar'}">Editar Cliente</c:when>
            <c:otherwise>Detalle del Cliente</c:otherwise>
        </c:choose>
    </title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body class="bg-light">
<my:navbar activeTab="clientes" userRole="admin"/>
<div class="container py-5">
    <div class="row justify-content-center">
        <div class="col-lg-10">
            <div class="card shadow">
                <div class="card-header bg-primary text-white">
                    <h2 class="h4 mb-0">
                        <i class="bi bi-person-gear me-2"></i>
                        <c:choose>
                            <c:when test="${modo eq 'editar'}">Editar Cliente</c:when>
                            <c:otherwise>Detalle del Cliente</c:otherwise>
                        </c:choose>
                    </h2>
                </div>
                <div class="card-body">
                    <c:choose>
                        <c:when test="${modo eq 'editar'}">
                            <form action="ServletEditarCliente" method="post" class="row g-3 needs-validation"
                                  novalidate>
                        </c:when>
                        <c:otherwise>
                            <div class="row g-3">
                        </c:otherwise>
                    </c:choose>
                         <!-- Campos ocultos para mantener la paginación y los filtros -->
                            <input type="hidden" name="busqueda" value="${param.busqueda}">
                            <input type="hidden" name="estado" value="${param.estado}">
                            <input type="hidden" name="sexo" value="${param.sexo}">
                            <input type="hidden" name="pagina" value="${param.pagina}">

                        <input type="hidden" name="idCliente" value="${cliente.idCliente}"/>
                        <!-- Nombre -->
                        <div class="col-md-6">
                            <label class="form-label">Nombre</label>
                            <input type="text" class="form-control ${modo eq 'editar' ? '' : 'bg-light'}"
                                   name="nombre" value="${cliente.nombre}"
                                    ${modo eq 'editar' ? 'required' : 'readonly'}>
                        </div>
                        <!-- Apellido -->
                        <div class="col-md-6">
                            <label class="form-label">Apellido</label>
                            <input type="text" class="form-control ${modo eq 'editar' ? '' : 'bg-light'}"
                                   name="apellido" value="${cliente.apellido}"
                                    ${modo eq 'editar' ? 'required' : 'readonly'}>
                        </div>
                        <!-- DNI -->
                        <div class="col-md-4">
                            <label class="form-label">DNI</label>
                            <input type="text" class="form-control ${modo eq 'editar' ? '' : 'bg-light'}" name="dni"
                                   value="${cliente.dni}" ${modo eq 'editar' ? 'required' : 'readonly'}>
                        </div>
                        <!-- CUIL -->
                        <div class="col-md-4">
                            <label class="form-label">CUIL</label>
                            <input type="text" class="form-control ${modo eq 'editar' ? '' : 'bg-light'}" name="cuil"
                                   value="${cliente.cuil}" ${modo eq 'editar' ? 'required' : 'readonly'}>
                        </div>
                        <!-- Teléfono -->
                        <div class="col-md-4">
                            <label class="form-label">Teléfono</label>
                            <input type="text" class="form-control ${modo eq 'editar' ? '' : 'bg-light'}"
                                   name="telefono" value="${cliente.telefono}"
                                    ${modo eq 'editar' ? 'required' : 'readonly'}>
                        </div>
                        <!-- Email -->
                        <div class="col-md-6">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control ${modo eq 'editar' ? '' : 'bg-light'}"
                                   name="email" value="${cliente.email}"
                                    ${modo eq 'editar' ? 'required' : 'readonly'}>
                        </div>
                        <!-- Fecha Nacimiento -->
                        <div class="col-md-6">
                            <label class="form-label">Fecha Nacimiento</label>
                            <input type="date" class="form-control ${modo eq 'editar' ? '' : 'bg-light'}"
                                   name="fechaNac" value="${cliente.fechaNac}"
                                    ${modo eq 'editar' ? 'required' : 'readonly'}>
                        </div>
                        <!-- Nacionalidad -->
                        <div class="col-md-6">
                            <label class="form-label">Nacionalidad</label>
                            <input type="text" class="form-control ${modo eq 'editar' ? '' : 'bg-light'}"
                                   name="nacionalidad" value="${cliente.nacionalidad}"
                                    ${modo eq 'editar' ? 'required' : 'readonly'}>
                        </div>
                        <!-- Sexo -->
                        <div class="col-md-3">
                            <label class="form-label">Sexo</label>
                            <c:choose>
                                <c:when test="${modo eq 'editar'}">
                                    <select class="form-select" name="sexo" required>
                                        <option value="">Seleccione...</option>
                                        <option value="M" ${cliente.sexo eq 'M' ? 'selected' : ''}>Masculino</option>
                                        <option value="F" ${cliente.sexo eq 'F' ? 'selected' : ''}>Femenino</option>
                                        <option value="O" ${cliente.sexo eq 'O' ? 'selected' : ''}>Otro</option>
                                    </select>
                                </c:when>
                                <c:otherwise>
                                    <input type="text" class="form-control bg-light"
                                           value="${cliente.sexo eq 'M' ? 'Masculino' : cliente.sexo eq 'F' ? 'Femenino' : 'Otro'}"
                                           readonly>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <!-- Provincia -->
                        <div class="col-md-3">
                            <label for="provincia" class="form-label">Provincia</label>
                            <select class="form-select" id="provincia" name="provincia" required>
                                <option value="">Seleccione una provincia...</option>
                                <c:forEach var="prov" items="${provincias}">
                                    <option value="${prov.idProvincia}"
                                             ${prov.idProvincia == cliente.idProvincia ? 'selected' : ''}>
                                            ${prov.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">Por favor seleccione la provincia</div>
                        </div>
                        <!-- Localidad -->
                        <div class="col-md-6">
                            <label for="localidad" class="form-label">Localidad</label>
                            <select class="form-select" id="localidad" name="localidad" required>
                                <option value="">Seleccione una localidad...</option>
                                <c:forEach var="loc" items="${localidades}">
                                    <option value="${loc.idLocalidad}"
                                             ${loc.idLocalidad == cliente.idLocalidad ? 'selected' : ''}>
                                            ${loc.nombre}
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">Por favor seleccione la localidad</div>
                        </div>
                        <!-- Dirección -->
                        <div class="col-md-6">
                            <label class="form-label">Dirección</label>
                            <input type="text" class="form-control ${modo eq 'editar' ? '' : 'bg-light'}"
                                   name="direccion" value="${cliente.direccion}"
                                    ${modo eq 'editar' ? 'required' : 'readonly'}>
                        </div>
                        <!-- Botones -->
                        <div class="col-12 text-end mt-4">
                            <c:choose>
                                <c:when test="${modo eq 'editar'}">
                                    <button class="btn btn-primary px-4 me-2" type="submit">
                                        <i class="bi bi-save me-2"></i>Guardar
                                    </button>
                                    <a href="ListarClientesServlet" class="btn btn-secondary px-4">
                                        <i class="bi bi-x-circle me-2"></i>Cancelar
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <a href="ListarClientesServlet" class="btn btn-primary px-4 me-2">
                                        <i class="bi bi-arrow-left me-2"></i>Volver
                                    </a>
                                    <c:if test="${cliente.estado}">
                                        <a href="ServletEditarCliente?id=${cliente.idCliente}&modo=editar"
                                           class="btn btn-warning px-4">
                                            <i class="bi bi-pencil me-2"></i>Editar
                                        </a>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>
<my:footer/>
<script>
document.getElementById('provincia').addEventListener('change', function () {
    const idProvincia = this.value;
    const localidadSelect = document.getElementById('localidad');
    localidadSelect.innerHTML = '<option value="">Seleccione una localidad...</option>';
    if (!idProvincia) return;
    fetch('${pageContext.request.contextPath}/LocalidadesPorProvincia?idProvincia=' + idProvincia)
        .then(response => response.json())
        .then(data => {
            data.forEach(localidad => {
                const option = document.createElement('option');
                option.value = localidad.idLocalidad;
                option.text = localidad.nombre;
                localidadSelect.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Error al cargar localidades:', error);
        });
});
</script>
</body>
</html>