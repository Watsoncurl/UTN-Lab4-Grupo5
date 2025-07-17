<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
  <title>Solicitud de préstamos</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
  <my:navbar activeTab="prestamos" userRole="cliente" />
  <div class="container mt-4 mb-5">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <div class="card shadow-sm">
          <div class="card-header bg-primary text-white">
            <h2 class="h4 mb-0"><i class="bi bi-cash-coin me-2"></i>Solicitud de préstamo</h2>
          </div>
          <div class="card-body">
            <c:if test="${not empty requestScope.error}">
              <div class="alert alert-danger" role="alert">
                <i class="bi bi-exclamation-triangle me-2"></i>${requestScope.error}
              </div>
            </c:if>
            <form class="needs-validation" novalidate action="PrestamoServlet" method="post" id="prestamoForm">
              <div class="mb-3">
                <label for="idCuenta" class="form-label">Cuenta a depositar</label>
                <select class="form-select" id="idCuenta" name="idCuenta" required>
                  <option value="" selected disabled>-- Seleccione una cuenta --</option>
                  <c:forEach var="cuenta" items="${requestScope.cuentas}">
                    <option value="${cuenta.id_cuenta}">${cuenta.nro_cuenta} - ${cuenta.tipo_cuenta} (Saldo: $${cuenta.saldo})</option>
                  </c:forEach>
                </select>
                <div class="invalid-feedback">Por favor seleccione una cuenta</div>
              </div>
              <div class="mb-3">
                <label for="importe" class="form-label">Importe</label>
                <div class="input-group">
                  <span class="input-group-text">$</span>
                  <input type="number" class="form-control" id="importe" name="importe" placeholder="0.00" required>
                  <div class="invalid-feedback">Ingrese un importe válido</div>
                </div>
              </div>
              <div class="mb-4">
                <label for="plazoMeses" class="form-label">Cantidad de cuotas</label>
                <select class="form-select" id="plazoMeses" name="plazoMeses" required onchange="calcularCuota()">
                  <option value="" selected disabled>-- Seleccione una opción --</option>
                  <option value="6">6 cuotas</option>
                  <option value="12">12 cuotas</option>
                  <option value="18">18 cuotas</option>
                  <option value="24">24 cuotas</option>
                  <option value="36">36 cuotas</option>
                </select>
                <div class="invalid-feedback">Seleccione la cantidad de cuotas</div>
              </div>

              <!-- Caja de texto para mostrar el valor de la cuota -->
              <div class="mb-3">
                <label for="valorCuota" class="form-label">Valor de la Cuota</label>
                <div class="input-group">
                  <span class="input-group-text">$</span>
                  <input type="text" class="form-control" id="valorCuota" name="valorCuota" value="0.00" readonly>
                </div>
              </div>

              <!-- Caja de texto para mostrar la tasa de interés anual -->
              <div class="mb-3">
                <label for="tasaInteresAnual" class="form-label">Tasa de Interés Anual</label>
                <div class="input-group">
                  <input type="text" class="form-control" id="tasaInteresAnual" name="tasaInteresAnual" value="10.00%" readonly>
                </div>
              </div>

              <div class="d-flex justify-content-between">
                <button type="submit" class="btn btn-primary px-4" name="accion" value="Siguiente">
                  <i class="bi bi-arrow-right-circle me-2"></i>Siguiente
                </button>
                <a href="ClienteDashboard.jsp" class="btn btn-danger px-4">
                  <i class="bi bi-x-circle me-2"></i>Salir
                </a>
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

    function calcularCuota() {
      const importe = parseFloat(document.getElementById('importe').value);
      const plazoMeses = parseInt(document.getElementById('plazoMeses').value);
      const valorCuotaInput = document.getElementById('valorCuota');

      // Tasa de interés anual (dividida entre 12 para obtener la tasa mensual)
      const tasaInteresAnual = 0.1; // 10% anual
      const tasaInteresMensual = tasaInteresAnual / 12;

      if (isNaN(importe) || isNaN(plazoMeses) || importe <= 0 || plazoMeses <= 0) {
        valorCuotaInput.value = "0.00";
        return;
      }

      // Calcular el valor de la cuota usando la fórmula proporcionada
      const cuotaMensual = (importe + (importe * tasaInteresAnual * plazoMeses / 12)) / plazoMeses; //Corregido y simplificado

      // Mostrar el valor de la cuota formateado
      valorCuotaInput.value = cuotaMensual.toFixed(2); // Formatea a 2 decimales
    }

    // Agregar eventos de escucha para el cambio de importe
    document.getElementById('importe').addEventListener('input', calcularCuota);

  </script>
</body>
</html>