<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <my:navbar activeTab="clientes" userRole="admin" />
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
                <form action="ServletEditarCliente" method="post" class="row g-3 needs-validation" novalidate>
              </c:when>
              <c:otherwise>
                <div class="row g-3">
              </c:otherwise>
            </c:choose>
            
              <input type="hidden" name="idCliente" value="${cliente.idCliente}" />
              
              <!-- Nombre -->
              <div class="col-md-6">
                <label class="form-label">Nombre</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-person"></i></span>
                  <c:choose>
                    <c:when test="${modo eq 'editar'}">
                      <input type="text" class="form-control" name="nombre" 
                             value="${cliente.nombre}" required>
                      <div class="invalid-feedback">Ingrese el nombre</div>
                    </c:when>
                    <c:otherwise>
                      <input type="text" class="form-control bg-light" 
                             value="${cliente.nombre}" readonly>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
              
              <!-- Apellido -->
              <div class="col-md-6">
                <label class="form-label">Apellido</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-person"></i></span>
                  <c:choose>
                    <c:when test="${modo eq 'editar'}">
                      <input type="text" class="form-control" name="apellido" 
                             value="${cliente.apellido}" required>
                      <div class="invalid-feedback">Ingrese el apellido</div>
                    </c:when>
                    <c:otherwise>
                      <input type="text" class="form-control bg-light" 
                             value="${cliente.apellido}" readonly>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
              
              <!-- DNI -->
              <div class="col-md-4">
                <label class="form-label">DNI</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-credit-card"></i></span>
                  <c:choose>
                    <c:when test="${modo eq 'editar'}">
                      <input type="text" class="form-control" name="dni"
                             value="${cliente.dni}" required>
                      <div class="invalid-feedback">Ingrese el DNI</div>
                    </c:when>
                    <c:otherwise>
                      <input type="text" class="form-control bg-light" 
                             value="${cliente.dni}" readonly>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
              
              <!-- CUIL -->
              <div class="col-md-4">
                <label class="form-label">CUIL</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-credit-card"></i></span>
                  <c:choose>
                    <c:when test="${modo eq 'editar'}">
                      <input type="text" class="form-control" name="cuil"
                             value="${cliente.cuil}" required>
                      <div class="invalid-feedback">Ingrese el CUIL</div>
                    </c:when>
                    <c:otherwise>
                      <input type="text" class="form-control bg-light" 
                             value="${cliente.cuil}" readonly>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
              
              <!-- Teléfono -->
              <div class="col-md-4">
                <label class="form-label">Teléfono</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-telephone"></i></span>
                  <c:choose>
                    <c:when test="${modo eq 'editar'}">
                      <input type="text" class="form-control" name="telefono"
                             value="${cliente.telefono}" required>
                      <div class="invalid-feedback">Ingrese el teléfono</div>
                    </c:when>
                    <c:otherwise>
                      <input type="text" class="form-control bg-light" 
                             value="${cliente.telefono}" readonly>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
              
              <!-- Email -->
              <div class="col-md-6">
                <label class="form-label">Email</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                  <c:choose>
                    <c:when test="${modo eq 'editar'}">
                      <input type="email" class="form-control" name="email"
                             value="${cliente.email}" required>
                      <div class="invalid-feedback">Ingrese un email válido</div>
                    </c:when>
                    <c:otherwise>
                      <input type="text" class="form-control bg-light" 
                             value="${cliente.email}" readonly>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
              
              <!-- Fecha Nacimiento -->
              <div class="col-md-6">
                <label class="form-label">Fecha Nacimiento</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-calendar"></i></span>
                  <c:choose>
                    <c:when test="${modo eq 'editar'}">
                      <input type="date" class="form-control" name="fechaNac"
                             value="${cliente.fechaNac}" required>
                      <div class="invalid-feedback">Ingrese la fecha</div>
                    </c:when>
                    <c:otherwise>
                      <input type="text" class="form-control bg-light" 
                             value="${cliente.fechaNac}" readonly>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
              
              <!-- Nacionalidad -->
              <div class="col-md-6">
                <label class="form-label">Nacionalidad</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-globe"></i></span>
                  <c:choose>
                    <c:when test="${modo eq 'editar'}">
                      <input type="text" class="form-control" name="nacionalidad"
                             value="${cliente.nacionalidad}" required>
                      <div class="invalid-feedback">Ingrese la nacionalidad</div>
                    </c:when>
                    <c:otherwise>
                      <input type="text" class="form-control bg-light" 
                             value="${cliente.nacionalidad}" readonly>
                    </c:otherwise>
                  </c:choose>
                </div>
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
                    <div class="invalid-feedback">Seleccione el sexo</div>
                  </c:when>
                  <c:otherwise>
                    <input type="text" class="form-control bg-light" 
                           value="${cliente.sexo eq 'M' ? 'Masculino' : cliente.sexo eq 'F' ? 'Femenino' : 'Otro'}" readonly>
                  </c:otherwise>
                </c:choose>
              </div>
              
              <!-- Provincia -->
              <div class="col-md-3">
                <label class="form-label">Provincia</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-geo-alt"></i></span>
                  <c:choose>
                    <c:when test="${modo eq 'editar'}">
                      <input type="text" class="form-control" name="provincia"
                             value="${cliente.provincia}" required>
                      <div class="invalid-feedback">Ingrese la provincia</div>
                    </c:when>
                    <c:otherwise>
                      <input type="text" class="form-control bg-light" 
                             value="${cliente.provincia}" readonly>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
              
              <!-- Localidad -->
              <div class="col-md-6">
                <label class="form-label">Localidad</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-geo"></i></span>
                  <c:choose>
                    <c:when test="${modo eq 'editar'}">
                      <input type="text" class="form-control" name="localidad"
                             value="${cliente.localidad}" required>
                      <div class="invalid-feedback">Ingrese la localidad</div>
                    </c:when>
                    <c:otherwise>
                      <input type="text" class="form-control bg-light" 
                             value="${cliente.localidad}" readonly>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
              
              <!-- Dirección -->
              <div class="col-md-6">
                <label class="form-label">Dirección</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-house"></i></span>
                  <c:choose>
                    <c:when test="${modo eq 'editar'}">
                      <input type="text" class="form-control" name="direccion"
                             value="${cliente.direccion}" required>
                      <div class="invalid-feedback">Ingrese la dirección</div>
                    </c:when>
                    <c:otherwise>
                      <input type="text" class="form-control bg-light" 
                             value="${cliente.direccion}" readonly>
                    </c:otherwise>
                  </c:choose>
                </div>
              </div>
              
              <!-- Botones -->
              <div class="col-12 text-end mt-4">
                <c:choose>
                  <c:when test="${modo eq 'editar'}">
                    <button class="btn btn-primary px-4 me-2" type="submit">
                      <i class="bi bi-save me-2"></i>Guardar
                    </button>
                    <a href="ServletEditarCliente?id=${cliente.idCliente}&modo=ver" class="btn btn-secondary px-4">
                      <i class="bi bi-x-circle me-2"></i>Cancelar
                    </a>
                  </c:when>
                  <c:otherwise>
                    <a href="ListarClientesServlet" class="btn btn-primary px-4 me-2">
                      <i class="bi bi-arrow-left me-2"></i>Volver
                    </a>
                    <c:if test="${cliente.estado}">
                      <a href="ServletEditarCliente?id=${cliente.idCliente}&modo=editar" class="btn btn-warning px-4">
                        <i class="bi bi-pencil me-2"></i>Editar
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
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
  <c:if test="${modo eq 'editar'}">
    <script>
      // Validación de formulario solo en modo edición
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