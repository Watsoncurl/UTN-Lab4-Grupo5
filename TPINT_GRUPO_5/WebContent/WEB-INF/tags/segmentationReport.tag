<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="fechaReporte" required="true" %>
<%@ attribute name="cantidadClientesPorSegmento" type="java.util.Map" required="true" %>
<%@ attribute name="montoTotalPorSegmento" type="java.util.Map" required="true" %>
<%@ attribute name="porcentajeSaldoPorSegmento" type="java.util.Map" required="true" %>
<%@ attribute name="titulo" required="true" type="java.lang.String" %>
<%@ attribute name="icono" required="true" type="java.lang.String" %>
<%@ attribute name="colorHeader" required="true" type="java.lang.String" %>
<%@ attribute name="actualizacion" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card shadow-sm h-100 mb-4">
  <!-- Encabezado -->
  <div class="card-header ${colorHeader} text-white">
    <h5 class="card-title mb-0">
      <i class="${icono} me-2"></i>${titulo}
    </h5>
  </div>

  <!-- Cuerpo -->
  <div class="card-body">
    <div class="row text-center">
	  <!-- Montos -->
	  <div class="col-md-4">
	    <h6 class="text-muted mb-2">Monto por Segmento</h6>
	    <canvas id="montosChart" width="250" height="180" style="display: block; margin: 0 auto;"></canvas>
	  </div>
		
	  <!-- Donut -->
	  <div class="col-md-4">
	    <h6 class="text-muted mb-2">Distribución del Saldo (%)</h6>
	    <canvas id="donutChart" width="200" height="180" style="display: block; margin: 0 auto;"></canvas>
	  </div>
	
	  <!-- Clientes -->
	  <div class="col-md-4">
	    <h6 class="text-muted mb-2">Clientes por Segmento</h6>
	    <canvas id="clientesChart" width="250" height="180" style="display: block; margin: 0 auto;"></canvas>
	  </div>
	</div>
  </div>

  <!-- Footer (ahora correctamente posicionado) -->
  <div class="card-footer bg-light text-end">
    <small class="text-muted">Actualizado: ${empty actualizacion ? 'hoy' : actualizacion}</small>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
  const labels = [
    <c:forEach var="entry" items="${cantidadClientesPorSegmento}" varStatus="s">
      "${entry.key}"<c:if test="${!s.last}">,</c:if>
    </c:forEach>
  ];
  const cantidades = [
    <c:forEach var="entry" items="${cantidadClientesPorSegmento}" varStatus="s">
      ${entry.value}<c:if test="${!s.last}">,</c:if>
    </c:forEach>
  ];
  const montos = [
    <c:forEach var="entry" items="${montoTotalPorSegmento}" varStatus="s">
      ${entry.value}<c:if test="${!s.last}">,</c:if>
    </c:forEach>
  ];
  const porcentajes = [
    <c:forEach var="entry" items="${porcentajeSaldoPorSegmento}" varStatus="s">
      ${entry.value}<c:if test="${!s.last}">,</c:if>
    </c:forEach>
  ];

  // Donut Chart
  new Chart(document.getElementById('donutChart'), {
    type: 'doughnut',
    data: {
      labels: labels,
      datasets: [{
        data: porcentajes,
        backgroundColor: ['#0d6efd', '#198754', '#ffc107'],
        borderWidth: 1
      }]
    },
    options: {
      plugins: { legend: { position: 'bottom' } }
    }
  });

  // Bar Chart - Cantidad de Clientes
  new Chart(document.getElementById('clientesChart'), {
    type: 'bar',
    data: {
      labels: labels,
      datasets: [{
        label: 'Cantidad de Clientes',
        data: cantidades,
        backgroundColor: '#0dcaf0'
      }]
    },
    options: {
      responsive: true,
      plugins: { legend: { display: false } },
      scales: { y: { beginAtZero: true } }
    }
  });

  // Bar Chart - Montos
  new Chart(document.getElementById('montosChart'), {
    type: 'bar',
    data: {
      labels: labels,
      datasets: [{
        label: 'Monto Total del Segmento',
        data: montos,
        backgroundColor: '#dc3545'
      }]
    },
    options: {
      responsive: true,
      plugins: { legend: { display: false } },
      scales: {
        y: {
          beginAtZero: true,
          ticks: {
            callback: function(value) {
              return '$' + value.toLocaleString();
            }
          }
        }
      }
    }
  });
</script>
