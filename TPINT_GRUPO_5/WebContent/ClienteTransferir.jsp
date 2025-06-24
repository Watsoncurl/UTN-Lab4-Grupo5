<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>Transferencias Bancarias</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
  <my:navbar activeTab="transferir" userRole="cliente" />

  <div class="container mt-4 mb-5">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <div class="card shadow-sm">
          <div class="card-header bg-primary text-white">
            <h2 class="h4 mb-0"><i class="bi bi-arrow-left-right me-2"></i>Nueva Transferencia</h2>
          </div>
          
          <div class="card-body">
            <form class="needs-validation" novalidate>
              <!-- Sección Cuenta Origen -->
              <div class="mb-4">
                <h5 class="fw-bold text-primary mb-3"><i class="bi bi-wallet2 me-2"></i>Cuenta de origen</h5>
                <select class="form-select form-select-lg mb-3" id="cuentaOrigen" required>
                  <option value="" selected disabled>-- Seleccione su cuenta --</option>
                  <option value="1">Cuenta Corriente (****1234) - $25,000.00</option>
                  <option value="2">Cuenta Ahorros (****5678) - $42,300.50</option>
                </select>
              </div>
              
              <!-- Sección Datos Destinatario -->
              <div class="mb-4">
                <h5 class="fw-bold text-primary mb-3"><i class="bi bi-person-badge me-2"></i>Datos del destinatario</h5>
                
                <div class="mb-3">
                  <label for="cbuDestino" class="form-label fw-bold">CBU/CVU</label>
                  <div class="input-group">
                    <input type="text" class="form-control" id="cbuDestino" placeholder="Ingrese CBU (22 dígitos)" required pattern="[0-9]{22}">
                    <button class="btn btn-outline-secondary" type="button" id="btnBuscarCBU">
                      <i class="bi bi-search"></i> Buscar
                    </button>
                  </div>
                  <div class="invalid-feedback">Ingrese un CBU válido de 22 dígitos</div>
                </div>
                
                <div class="row g-3">
                  <div class="col-md-6">
                    <label for="nombreDestinatario" class="form-label fw-bold">Nombre</label>
                    <input type="text" class="form-control" id="nombreDestinatario" readonly>
                  </div>
                  <div class="col-md-6">
                    <label for="tipoCuenta" class="form-label fw-bold">Tipo de cuenta</label>
                    <input type="text" class="form-control" id="tipoCuenta" readonly>
                  </div>
                </div>
              </div>
              
              <!-- Sección Importe -->
              <div class="mb-4">
                <h5 class="fw-bold text-primary mb-3"><i class="bi bi-currency-dollar me-2"></i>Importe a transferir</h5>
                <div class="input-group input-group-lg">
                  <span class="input-group-text">$</span>
                  <input type="number" class="form-control" id="importe" placeholder="0.00" min="1" step="0.01" required>
                  <span class="input-group-text">,00</span>
                </div>
                <div class="form-text">Saldo disponible: $25,000.00</div>
                <div class="invalid-feedback">Ingrese un importe válido</div>
              </div>
              
              <!-- Botones de acción -->
              <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
                <button type="submit" class="btn btn-primary">
                  <i class="bi bi-send-check me-2"></i>Confirmar Transferencia
                </button>
                <button type="button" class="btn btn-danger me-md-2">
                  <i class="bi bi-x-circle me-2"></i>Cancelar
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
    
    // Simular búsqueda de CBU
    document.getElementById('btnBuscarCBU').addEventListener('click', function() {
      const cbuInput = document.getElementById('cbuDestino');
      if(cbuInput.value.length === 22) {
        document.getElementById('nombreDestinatario').value = "Juan Pérez";
        document.getElementById('tipoCuenta').value = "Cuenta Corriente";
      }
    });
  </script>
</body>
</html>