<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="fechaInicio" required="true" type="java.lang.String" %>
<%@ attribute name="fechaFin" required="true" type="java.lang.String" %>
<%@ attribute name="nuevosClientesPorFecha" required="true" type="java.util.Map" %>
<%@ attribute name="nuevasCuentasPorFecha" required="true" type="java.util.Map" %>
<%@ attribute name="cuentasPorTipo" required="true" type="java.util.Map" %>
<%@ attribute name="tasaCrecimiento" required="true" type="java.lang.Double" %>
<%@ attribute name="titulo" required="true" type="java.lang.String" %>
<%@ attribute name="icono" required="true" type="java.lang.String" %>
<%@ attribute name="colorHeader" required="true" type="java.lang.String" %>
<%@ attribute name="actualizacion" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="card shadow-sm h-100 mb-4">
  <!-- Header -->
  <div class="card-header ${colorHeader} text-white">
    <h5 class="card-title mb-0">
      <i class="${icono} me-2"></i>${titulo}
    </h5>
  </div>

  <!-- Body -->
  <div class="card-body">
    <div class="row text-center">
      <!-- Gráfico de líneas -->
      <div class="col-md-8 mb-3">
        <h6 class="text-muted mb-2">Tendencia de Nuevos Clientes y Cuentas</h6>
        <canvas id="lineChart" width="600" height="250"></canvas>
      </div>

      <!-- Barras por tipo de cuenta -->
      <div class="col-md-4 mb-3">
        <h6 class="text-muted mb-2">Cuentas Nuevas por Tipo</h6>
        <canvas id="barTiposChart" width="300" height="250"></canvas>
      </div>
    </div>

    <div class="row mt-3">
      <div class="col text-end">
        <small class="text-muted">
          Período: ${fechaInicio} al ${fechaFin} |
          Tasa de crecimiento: <strong>${tasaCrecimiento}%</strong>
        </small>
      </div>
    </div>
    
  </div>
  <!-- Footer -->
	<div class="card-footer bg-light text-end">
	  <small class="text-muted">
	    Actualizado: ${empty actualizacion ? (empty fechaFin ? 'hoy' : fechaFin) : actualizacion}
	  </small>
	</div>
</div>

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
  const fechas = [
    <c:forEach var="entry" items="${nuevosClientesPorFecha}" varStatus="s">
      "${entry.key}"<c:if test="${!s.last}">,</c:if>
    </c:forEach>
  ];
  const clientes = [
    <c:forEach var="entry" items="${nuevosClientesPorFecha}" varStatus="s">
      ${entry.value}<c:if test="${!s.last}">,</c:if>
    </c:forEach>
  ];
  const cuentas = [
    <c:forEach var="entry" items="${nuevasCuentasPorFecha}" varStatus="s">
      ${entry.value}<c:if test="${!s.last}">,</c:if>
    </c:forEach>
  ];
  const tiposCuenta = [
    <c:forEach var="entry" items="${cuentasPorTipo}" varStatus="s">
      "${entry.key}"<c:if test="${!s.last}">,</c:if>
    </c:forEach>
  ];
  const cuentasPorTipo = [
    <c:forEach var="entry" items="${cuentasPorTipo}" varStatus="s">
      ${entry.value}<c:if test="${!s.last}">,</c:if>
    </c:forEach>
  ];

  // Línea de tendencia de clientes y cuentas
  new Chart(document.getElementById('lineChart'), {
    type: 'line',
    data: {
      labels: fechas,
      datasets: [
        {
          label: 'Clientes Nuevos',
          data: clientes,
          borderColor: '#198754',
          backgroundColor: 'rgba(25, 135, 84, 0.2)',
          tension: 0.4
        },
        {
          label: 'Cuentas Nuevas',
          data: cuentas,
          borderColor: '#0d6efd',
          backgroundColor: 'rgba(13, 110, 253, 0.2)',
          tension: 0.4
        }
      ]
    },
    options: {
      responsive: true,
      plugins: {
        legend: { position: 'top' },
        tooltip: { mode: 'index', intersect: false }
      },
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });

  // Barras por tipo de cuenta
  new Chart(document.getElementById('barTiposChart'), {
    type: 'bar',
    data: {
      labels: tiposCuenta,
      datasets: [{
        label: 'Cuentas Nuevas',
        data: cuentasPorTipo,
        backgroundColor: '#ffc107'
      }]
    },
    options: {
      responsive: true,
      plugins: { legend: { display: false } },
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
</script>
