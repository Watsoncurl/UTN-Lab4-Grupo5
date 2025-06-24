<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>Confirmación de préstamo</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
  <my:navbar activeTab="prestamos2" userRole="cliente" />

  <div class="container mt-4 mb-5">
    <div class="row justify-content-center">
      <div class="col-lg-8">
        <div class="card shadow-sm">
          <div class="card-header bg-primary text-white">
            <h2 class="h4 mb-0"><i class="bi bi-file-text me-2"></i>Resumen de préstamo</h2>
          </div>
          
          <div class="card-body">
            <form>
              <div class="mb-3 row">
                <label for="cuentaDeposito" class="col-sm-4 col-form-label fw-bold">Cuenta destino:</label>
                <div class="col-sm-8">
                  <input type="text" class="form-control-plaintext" id="cuentaDeposito" value="Cuenta Ahorros ****4567" readonly>
                </div>
              </div>
              
              <div class="mb-3 row">
                <label for="importe" class="col-sm-4 col-form-label fw-bold">Importe solicitado:</label>
                <div class="col-sm-8">
                  <div class="input-group">
                    <span class="input-group-text">$</span>
                    <input type="text" class="input-group-text" id="importe" value="25,000.00" readonly>
                  </div>
                </div>
              </div>
              
              <div class="mb-3 row">
                <label for="cuotas" class="col-sm-4 col-form-label fw-bold">Plazo:</label>
                <div class="col-sm-8">
                  <input type="text" class="form-control-plaintext" id="cuotas" value="12 cuotas mensuales" readonly>
                </div>
              </div>
              
              <div class="mb-3 row">
                <label for="porcentajeinteres" class="col-sm-4 col-form-label fw-bold">Tasa de interés:</label>
                <div class="col-sm-8">
                  <div class="input-group">
                    <input type="text" class="input-group-text" id="porcentajeinteres" value="12.5" readonly>
                    <span class="input-group-text">% anual</span>
                  </div>
                </div>
              </div>
              
              <hr class="my-4">
              
              <div class="mb-3 row">
                <label for="interes" class="col-sm-4 col-form-label fw-bold text-danger">Total a pagar:</label>
                <div class="col-sm-8">
                  <div class="input-group">
                    <span class="input-group-text">$</span>
                    <input type="text" class="input-group-text fw-bold text-danger" id="interes" value="28,150.00" readonly>
                  </div>
                </div>
              </div>
              
              <div class="mb-4 row">
                <label for="interesporcuota" class="col-sm-4 col-form-label fw-bold">Cuota mensual:</label>
                <div class="col-sm-8">
                  <div class="input-group">
                    <span class="input-group-text">$</span>
                    <input type="text" class="input-group-text fw-bold" id="interesporcuota" value="2,345.83" readonly>
                  </div>
                </div>
              </div>
              
              <div class="alert alert-info">
                <i class="bi bi-info-circle-fill me-2"></i>Revise cuidadosamente los detalles antes de confirmar.
              </div>
              
              <div class="d-flex justify-content-between mt-4">
                <button type="submit" class="btn btn-success px-4" name="accion" value="Solicitar prestamo">
                  <i class="bi bi-check-circle-fill me-2"></i>Confirmar préstamo
                </button>
                
                <button type="submit" class="btn btn-danger px-4" name="accion" value="Salir">
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
</body>
</html>