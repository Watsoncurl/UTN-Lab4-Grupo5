<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>Solicitudes de Préstamos - Admin</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
  <my:navbar activeTab="prestamos" userRole="admin" />

  <div class="container mt-4">
    <div class="card shadow-sm mb-4">
      <div class="card-header bg-primary text-white">
        <h5 class="mb-0"><i class="bi bi-cash-coin me-2"></i>Solicitudes de Préstamos</h5>
      </div>
      
      <div class="card-body">
        <!-- Filtros de búsqueda en una sola fila -->
        <form class="row g-2 align-items-end">
          <div class="col-md">
            <label for="clienteSelect" class="form-label fw-bold">Cliente</label>
            <select class="form-select" id="clienteSelect">
              <option value="" selected>Todos</option>
              <option value="1">Gómez, María (30123456)</option>
              <option value="2">Pérez, Juan (28987654)</option>
              <option value="3">Fernández, Lucía (35678901)</option>
            </select>
          </div>
          
          <div class="col-md">
            <label for="estadoSelect" class="form-label fw-bold">Estado</label>
            <select class="form-select" id="estadoSelect">
              <option value="" selected>Todos</option>
              <option value="pendiente">Pendiente</option>
              <option value="aprobado">Aprobado</option>
              <option value="pagado">Pagado</option>
            </select>
          </div>
          
          <div class="col-md">
            <label for="desdeDate" class="form-label fw-bold">Desde</label>
            <input type="date" class="form-control" id="desdeDate">
          </div>
          
          <div class="col-md">
            <label for="hastaDate" class="form-label fw-bold">Hasta</label>
            <input type="date" class="form-control" id="hastaDate">
          </div>
          
          <div class="col-md">
            <label for="importeMin" class="form-label fw-bold">Importe desde</label>
            <input type="number" class="form-control" id="importeMin" placeholder="Mínimo">
          </div>
          
          <div class="col-md-auto d-grid">
            <button type="button" class="btn btn-primary" onclick="consultarPrestamos()">
              <i class="bi bi-search me-1"></i>Buscar
            </button>
          </div>
          
          <div class="col-md-auto d-grid">
            <button type="button" class="btn btn-outline-secondary" onclick="limpiarFiltros()">
              <i class="bi bi-arrow-counterclockwise me-1"></i>Limpiar
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
                <th>ID</th>
                <th>Cliente</th>
                <th>Fecha Alta</th>
                <th class="text-end">Importe</th>
                <th class="text-end">Plazo (meses)</th>
                <th class="text-end">Cuota Mensual</th>
                <th>Estado</th>
                <th class="text-end">Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>1001</td>
                <td>Gómez, María (30123456)</td>
                <td>15/05/2023</td>
                <td class="text-end">$50,000.00</td>
                <td class="text-end">24</td>
                <td class="text-end">$2,500.00</td>
                <td><span class="badge bg-warning text-dark">Pendiente</span></td>
                <td class="text-end">
                  <a href="AdminPrestamosDetalle.jsp?id=1001" class="btn btn-sm btn-outline-primary">
                    <i class="bi bi-eye"></i> Ver
                  </a>
                </td>
              </tr>
              <tr>
                <td>1002</td>
                <td>Pérez, Juan (28987654)</td>
                <td>10/05/2023</td>
                <td class="text-end">$30,000.00</td>
                <td class="text-end">12</td>
                <td class="text-end">$2,700.00</td>
                <td><span class="badge bg-success">Aprobado</span></td>
                <td class="text-end">
                  <a href="AdminPrestamosDetalle.jsp?id=1002" class="btn btn-sm btn-outline-primary">
                    <i class="bi bi-eye"></i> Ver
                  </a>
                </td>
              </tr>
              <tr>
                <td>1003</td>
                <td>Fernández, Lucía (35678901)</td>
                <td>05/05/2023</td>
                <td class="text-end">$20,000.00</td>
                <td class="text-end">6</td>
                <td class="text-end">$3,500.00</td>
                <td><span class="badge bg-secondary">Pagado</span></td>
                <td class="text-end">
                  <a href="AdminPrestamosDetalle.jsp?id=1003" class="btn btn-sm btn-outline-primary">
                    <i class="bi bi-eye"></i> Ver
                  </a>
                </td>
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
    function consultarPrestamos() {
      // Obtener valores de los filtros
      const cliente = document.getElementById('clienteSelect').value;
      const estado = document.getElementById('estadoSelect').value;
      const desde = document.getElementById('desdeDate').value;
      const hasta = document.getElementById('hastaDate').value;
      const importeMin = document.getElementById('importeMin').value;
      
      // Lógica para consultar préstamos con los filtros
      console.log("Consultando préstamos con filtros:", {
        cliente,
        estado,
        desde,
        hasta,
        importeMin
      });
      
      // Aquí iría la llamada AJAX para obtener los datos filtrados
      // fetch(`/api/prestamos?cliente=${cliente}&estado=${estado}&desde=${desde}&hasta=${hasta}&importeMin=${importeMin}`)
      //   .then(response => response.json())
      //   .then(data => actualizarTabla(data));
    }
    
    function limpiarFiltros() {
      document.getElementById('clienteSelect').value = '';
      document.getElementById('estadoSelect').value = '';
      document.getElementById('desdeDate').value = '';
      document.getElementById('hastaDate').value = '';
      document.getElementById('importeMin').value = '';
      consultarPrestamos();
    }
    
    function verDetalle(idPrestamo) {
      console.log("Ver detalle del préstamo:", idPrestamo);
      // window.location.href = `/admin/prestamos/detalle?id=${idPrestamo}`;
    }
  </script>
</body>
</html>