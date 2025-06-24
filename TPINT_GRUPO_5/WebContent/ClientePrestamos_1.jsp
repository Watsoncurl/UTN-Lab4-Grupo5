<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>Solicitud de préstamos</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
  <my:navbar activeTab="prestamos1" userRole="cliente" />

  <div class="container mt-4 mb-5">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <div class="card shadow-sm">
          <div class="card-header bg-primary text-white">
            <h2 class="h4 mb-0"><i class="bi bi-cash-coin me-2"></i>Solicitud de préstamo</h2>
          </div>
          
          <div class="card-body">
            <form class="needs-validation" novalidate>
              <div class="mb-3">
                <label for="cuenta" class="form-label">Cuenta a depositar</label>
                <select class="form-select" id="cuenta" required>
                  <option value="" selected disabled>-- Seleccione una cuenta --</option>
                  <option value="1">Cuenta Corriente (****1234)</option>
                  <option value="2">Cuenta Ahorros (****5678)</option>
                </select>
                <div class="invalid-feedback">Por favor seleccione una cuenta</div>
              </div>
              
              <div class="mb-3">
                <label for="importe" class="form-label">Importe</label>
                <div class="input-group">
                  <span class="input-group-text">$</span>
                  <input type="number" class="form-control" id="importe" placeholder="0.00" required>
                  <div class="invalid-feedback">Ingrese un importe válido</div>
                </div>
              </div>
              
              <div class="mb-4">
                <label for="cuotas" class="form-label">Cantidad de cuotas</label>
                <select class="form-select" id="cuotas" required>
                  <option value="" selected disabled>-- Seleccione una opción --</option>
                  <option value="1">1 cuota</option>
                  <option value="3">3 cuotas</option>
                  <option value="6">6 cuotas</option>
                  <option value="12">12 cuotas</option>
                  <option value="15">15 cuotas</option>
                  <option value="18">18 cuotas</option>
                  <option value="21">21 cuotas</option>
                  <option value="24">24 cuotas</option>
                </select>
                <div class="invalid-feedback">Seleccione la cantidad de cuotas</div>
              </div>
              
              <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary px-4" name="accion" value="Siguiente">
                  <i class="bi bi-arrow-right-circle me-2"></i>Siguiente
                </button>
                
                <button type="submit" class="btn btn-danger px-4" name="accion" value="Salir">
                  <i class="bi bi-x-circle me-2"></i>Salir
                </button>
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
    // Ejemplo de validación de formulario
    (function() {
      'use strict';
      const forms = document.querySelectorAll('.needs-validation');
      
      Array.from(forms).forEach(function(form) {
        form.addEventListener('submit', function(event) {
          if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
          }
          form.classList.add('was-validated');
        }, false);
      });
    })();
  </script>
</body>
</html>