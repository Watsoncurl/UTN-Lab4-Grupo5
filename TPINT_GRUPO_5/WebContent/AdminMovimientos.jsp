<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>Movimientos - Admin</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
  <my:navbar activeTab="movimientos" userRole="admin" />

  <div class="container mt-4">
    <div class="card shadow-sm mb-4">
      <div class="card-header bg-primary text-white">
        <h5 class="mb-0"><i class="bi bi-list-check me-2"></i>Consulta de Movimientos</h5>
      </div>
      
      <div class="card-body">
        <!-- Filtros de búsqueda -->
        <form class="row g-3 align-items-end">
          <div class="col-md-3">
            <label for="cuentaSelect" class="form-label fw-bold">Cuenta</label>
            <select class="form-select" id="cuentaSelect">
              <option selected disabled>Seleccionar cuenta</option>
              <option value="1">Cuenta Corriente #1234</option>
              <option value="2">Cuenta Ahorros #5678</option>
              <option value="3">Cuenta Inversión #9012</option>
            </select>
          </div>
          
          <div class="col-md-3">
            <label for="desdeDate" class="form-label fw-bold">Desde</label>
            <input type="date" class="form-control" id="desdeDate">
          </div>
          
          <div class="col-md-3">
            <label for="hastaDate" class="form-label fw-bold">Hasta</label>
            <input type="date" class="form-control" id="hastaDate">
          </div>
          
          <div class="col-md-3 d-grid">
            <button type="button" class="btn btn-primary" onclick="consultarMovimientos()">
              <i class="bi bi-search me-2"></i>Buscar
            </button>
          </div>
        </form>
      </div>
    </div>
    
    <!-- Tabla de resultados -->
    <div class="card shadow-sm">
      <div class="card-header bg-light">
        <h5 class="mb-0"><i class="bi bi-table me-2"></i>Resultados</h5>
      </div>
      
      <div class="card-body">
        <div class="table-responsive">
          <table class="table table-hover table-striped">
            <thead class="table-light">
              <tr>
                <th>Fecha</th>
                <th>Descripción</th>
                <th>Tipo</th>
                <th class="text-end">Importe</th>
                <th class="text-end">Saldo</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>15/05/2023</td>
                <td>Transferencia recibida</td>
                <td><span class="badge bg-success">Crédito</span></td>
                <td class="text-end text-success">+$5,000.00</td>
                <td class="text-end">$15,250.00</td>
              </tr>
              <tr>
                <td>10/05/2023</td>
                <td>Pago de servicios</td>
                <td><span class="badge bg-danger">Débito</span></td>
                <td class="text-end text-danger">-$1,200.00</td>
                <td class="text-end">$10,250.00</td>
              </tr>
              <tr>
                <td>05/05/2023</td>
                <td>Depósito en efectivo</td>
                <td><span class="badge bg-success">Crédito</span></td>
                <td class="text-end text-success">+$3,000.00</td>
                <td class="text-end">$11,450.00</td>
              </tr>
            </tbody>
          </table>
        </div>
        
        <!-- Paginación -->
        <nav aria-label="Page navigation" class="mt-3">
          <ul class="pagination justify-content-center">
            <li class="page-item disabled">
              <a class="page-link" href="#" tabindex="-1">Anterior</a>
            </li>
            <li class="page-item active"><a class="page-link" href="#">1</a></li>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
              <a class="page-link" href="#">Siguiente</a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  </div>

  <my:footer />
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  <script>
    function consultarMovimientos() {
      // Lógica para consultar movimientos
      console.log("Consultando movimientos...");
      // Aquí iría la llamada AJAX o similar para obtener los datos
    }
  </script>
</body>
</html>