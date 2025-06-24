<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>Detalle de Cliente</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
  <my:navbar activeTab="verMasCliente" userRole="admin" />

  <div class="container my-5">
    <div class="card shadow">
      <div class="card-header bg-primary text-white">
        <div class="d-flex justify-content-between align-items-center">
          <h3 class="mb-0"><i class="bi bi-person-badge me-2"></i>Detalle del Cliente</h3>
          <span class="badge bg-light text-dark fs-6">ID: CL-44223355</span>
        </div>
      </div>
      
      <div class="card-body">
        <form class="row g-3 needs-validation" novalidate>
          <!-- Sección Información Personal -->
          <div class="col-12">
            <h5 class="fw-bold text-primary mb-3"><i class="bi bi-person-lines-fill me-2"></i>Información Personal</h5>
          </div>
          
          <div class="col-md-6">
            <label class="form-label fw-bold">Nombre</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-person"></i></span>
              <input type="text" class="form-control" value="Lucia" required>
            </div>
          </div>
          
          <div class="col-md-6">
            <label class="form-label fw-bold">Apellido</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-person"></i></span>
              <input type="text" class="form-control" value="Monges" required>
            </div>
          </div>
          
          <div class="col-md-4">
            <label class="form-label fw-bold">DNI</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-credit-card"></i></span>
              <input type="text" class="form-control" value="44223355" required>
            </div>
          </div>
          
          <div class="col-md-4">
            <label class="form-label fw-bold">CUIL</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-card-checklist"></i></span>
              <input type="text" class="form-control" value="27-44223355-3" required>
            </div>
          </div>
          
          <div class="col-md-4">
            <label class="form-label fw-bold">Teléfono</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-telephone"></i></span>
              <input type="text" class="form-control" value="1122334455" required>
            </div>
          </div>
          
          <!-- Sección Contacto -->
          <div class="col-12 mt-4">
            <h5 class="fw-bold text-primary mb-3"><i class="bi bi-envelope me-2"></i>Información de Contacto</h5>
          </div>
          
          <div class="col-md-6">
            <label class="form-label fw-bold">Email</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-envelope"></i></span>
              <input type="email" class="form-control" value="ejemplo@utn.com" required>
            </div>
          </div>
          
          <div class="col-md-6">
            <label class="form-label fw-bold">Fecha de nacimiento</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-calendar"></i></span>
              <input type="date" class="form-control" value="1998-06-21" required>
            </div>
          </div>
          
          <!-- Sección Ubicación -->
          <div class="col-12 mt-4">
            <h5 class="fw-bold text-primary mb-3"><i class="bi bi-geo-alt me-2"></i>Domicilio</h5>
          </div>
          
          <div class="col-md-6">
            <label class="form-label fw-bold">Nacionalidad</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-globe"></i></span>
              <input type="text" class="form-control" value="Argentina" required>
            </div>
          </div>
          
          <div class="col-md-3">
            <label class="form-label fw-bold">Sexo</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-gender-female"></i></span>
              <input class="form-control" type="text" value="Femenino" required>
            </div>
          </div>
          
          <div class="col-md-3">
            <label class="form-label fw-bold">Provincia</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-map"></i></span>
              <input type="text" class="form-control" value="Buenos Aires" required>
            </div>
          </div>
          
          <div class="col-md-6">
            <label class="form-label fw-bold">Localidad</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-geo"></i></span>
              <input type="text" class="form-control" value="Beccar" required>
            </div>
          </div>
          
          <div class="col-md-6">
            <label class="form-label fw-bold">Dirección</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-house-door"></i></span>
              <input type="text" class="form-control" value="Calle falsa 123" required>
            </div>
          </div>
          
          <!-- Sección Credenciales -->
          <div class="col-12 mt-4">
            <h5 class="fw-bold text-primary mb-3"><i class="bi bi-shield-lock me-2"></i>Credenciales de Acceso</h5>
          </div>
          
          <div class="col-md-6">
            <label class="form-label fw-bold">Usuario</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-person-circle"></i></span>
              <input type="text" class="form-control" value="User123" required>
            </div>
          </div>
          
          <div class="col-md-6">
            <label class="form-label fw-bold">Contraseña</label>
            <div class="input-group">
              <span class="input-group-text"><i class="bi bi-key"></i></span>
              <input type="password" class="form-control" value="1234" required>
              <button class="btn btn-outline-secondary" type="button" id="togglePassword">
                <i class="bi bi-eye"></i>
              </button>
            </div>
          </div>
          
          <!-- Botones de acción -->
          <div class="col-12 mt-4">
            <div class="d-flex justify-content-between">
              <button type="button" class="btn btn-outline-secondary">
                <i class="bi bi-arrow-left me-2"></i>Volver
              </button>
              <div>
                <button type="button" class="btn btn-danger me-2">
                  <i class="bi bi-trash me-2"></i>Eliminar
                </button>
                <button type="submit" class="btn btn-primary">
                  <i class="bi bi-pencil-square me-2"></i>Guardar Cambios
                </button>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  <script>
	  // Mostrar/ocultar contraseña - Versión corregida
	  document.getElementById('togglePassword').addEventListener('click', function() {
	    const passwordInput = this.closest('.input-group').querySelector('input');
	    const icon = this.querySelector('i');
	    
	    // Alternar entre tipo password y text
	    const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
	    passwordInput.setAttribute('type', type);
	    
	    // Cambiar el icono visualmente
	    icon.classList.toggle('bi-eye');
	    icon.classList.toggle('bi-eye-slash');
	  });
	</script>
</body>
</html>