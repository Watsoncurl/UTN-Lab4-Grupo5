<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
  <title>Agregar Cuenta</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body class="bg-light">
  <my:navbar activeTab="cuentas" userRole="admin" />
  
  <div class="container py-5">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <div class="card shadow">
          <div class="card-header bg-primary text-white">
            <h2 class="h4 mb-0"><i class="bi bi-bank me-2"></i>Agregar Cuenta</h2>
          </div>
          
          <div class="card-body">
            <form id="formCuenta" class="row g-3 needs-validation" novalidate>
              <!-- Información del Cliente -->
              <div class="col-12">
                <h5 class="fw-bold text-primary mb-3"><i class="bi bi-person-lines-fill me-2"></i>Información del Cliente</h5>
              </div>
              
              <div class="col-md-12">
                <label for="cliente" class="form-label">Cliente*</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-person"></i></span>
                  <select class="form-select" id="cliente" name="id_cliente" required>
                    <option value="" selected disabled>Seleccionar cliente...</option>
                    <option value="1">Gómez, María (DNI: 30123456)</option>
                    <option value="2">Pérez, Juan (DNI: 28987654)</option>
                    <option value="3">Fernández, Lucía (DNI: 35678901)</option>
                  </select>
                  <div class="invalid-feedback">
                    Por favor seleccione un cliente
                  </div>
                </div>
              </div>
              
              <!-- Información de la Cuenta -->
              <div class="col-12 mt-4">
                <h5 class="fw-bold text-primary mb-3"><i class="bi bi-credit-card me-2"></i>Información de la Cuenta</h5>
              </div>
              
              <div class="col-md-6">
                <label for="tipoCuenta" class="form-label">Tipo de Cuenta*</label>
                <select class="form-select" id="tipoCuenta" name="id_tipo_cuenta" required>
                  <option value="" selected disabled>Seleccionar tipo...</option>
                  <option value="1">Caja de Ahorro</option>
                  <option value="2">Cuenta Corriente</option>
                  <option value="3">Cuenta Sueldo</option>
                  <option value="4">Cuenta Inversión</option>
                </select>
                <div class="invalid-feedback">
                  Por favor seleccione el tipo de cuenta
                </div>
              </div>
              
              <div class="col-md-6">
                <label for="nroCuenta" class="form-label">Número de Cuenta*</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-123"></i></span>
                  <input type="text" class="form-control" id="nroCuenta" name="nro_cuenta" required>
                  <div class="invalid-feedback">
                    Por favor ingrese el número de cuenta
                  </div>
                </div>
                <small class="text-muted">Número único de 10 dígitos</small>
              </div>
              
              <div class="col-md-12">
                <label for="cbu" class="form-label">CBU*</label>
                <div class="input-group">
                  <span class="input-group-text"><i class="bi bi-upc-scan"></i></span>
                  <input type="text" class="form-control" id="cbu" name="cbu" required>
                  <div class="invalid-feedback">
                    Por favor ingrese el CBU (22 dígitos)
                  </div>
                </div>
                <small class="text-muted">Código Bancario Uniforme de 22 dígitos</small>
              </div>
              
              <!-- Información de saldo automático -->
              <div class="col-12 mt-3">
                <div class="alert alert-info">
                  <i class="bi bi-info-circle me-2"></i>Al crear la cuenta se acreditarán automáticamente $10,000.00 como saldo inicial.
                </div>
              </div>
              
              <!-- Botones de acción -->
              <div class="col-12 mt-4 pt-3 border-top">
                <div class="d-flex justify-content-between">
                  <a href="AdminCuentas.jsp" class="btn btn-outline-danger px-4">
                    <i class="bi bi-arrow-left me-2"></i>Cancelar
                  </a>
                  <button type="button" class="btn btn-primary px-4" onclick="crearCuenta()">
                    <i class="bi bi-save me-2"></i>Crear Cuenta
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

  <my:footer />

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  <script>
    // Validación de formulario
    (function() {
      'use strict'
      const forms = document.querySelectorAll('.needs-validation')
      Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
          event.preventDefault()
          event.stopPropagation()
          form.classList.add('was-validated')
        }, false)
      })
    })()
    
    // Función para crear cuenta y redirigir
    function crearCuenta() {
      const form = document.getElementById('formCuenta');
      
      if (form.checkValidity()) {
        // Aquí iría la lógica para enviar los datos al servidor
        // Por ahora simulamos el envío exitoso
        alert('Cuenta creada exitosamente!');
        window.location.href = 'AdminCuentas.jsp';
      } else {
        form.classList.add('was-validated');
      }
    }
    
    // Generar número de cuenta aleatorio (opcional)
    document.getElementById('nroCuenta').addEventListener('focus', function() {
      if (!this.value) {
        this.value = Math.floor(1000000000 + Math.random() * 9000000000).toString();
      }
    });
    
    // Generar CBU aleatorio (opcional)
    document.getElementById('cbu').addEventListener('focus', function() {
      if (!this.value) {
        let cbu = '';
        for (let i = 0; i < 22; i++) {
          cbu += Math.floor(Math.random() * 10);
        }
        this.value = cbu;
      }
    });
  </script>
</body>
</html>