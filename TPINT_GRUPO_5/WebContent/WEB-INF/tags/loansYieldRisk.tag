<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="chartIdPrefix" required="true" type="java.lang.String" %>
<%@ attribute name="fechaInicio" required="true" type="java.lang.String" %>
<%@ attribute name="fechaFin" required="true" type="java.lang.String" %>
<%@ attribute name="capitalPrestado" required="true" type="java.lang.Double" %>
<%@ attribute name="cantidadPrestamos" required="true" type="java.lang.Integer" %>
<%@ attribute name="tasaAprobacion" required="true" type="java.lang.Double" %>
<%@ attribute name="prestamosPorEstado" required="true" type="java.util.Map" %>
<%@ attribute name="prestamosPorMesEstado" required="true" type="java.util.Map" %>
<%@ attribute name="titulo" required="true" type="java.lang.String" %>
<%@ attribute name="icono" required="true" type="java.lang.String" %>
<%@ attribute name="colorHeader" required="true" type="java.lang.String" %>
<%@ attribute name="actualizacion" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
  String stackedBarChartId = chartIdPrefix + "_stackedBarChart";
  String containerId = chartIdPrefix + "_container";
%>

<div class="card shadow-sm h-100 mb-4" id="<%= containerId %>">
  <div class="card-header ${colorHeader} text-white">
    <h5 class="card-title mb-0">
      <i class="${icono} me-2"></i>${titulo}
    </h5>
  </div>

  <div class="card-body">
    <div class="row text-center mb-4">
      <div class="col-md-4">
        <h6>Monto Total Prestado</h6>
        <h5 class="text-success">$ <%= String.format("%,.2f", capitalPrestado) %></h5>
      </div>
      <div class="col-md-4">
        <h6>Préstamos Nuevos</h6>
        <h5>${cantidadPrestamos}</h5>
      </div>
      <div class="col-md-4">
        <h6>Tasa de Aprobación</h6>
        <h5 class="text-primary">${tasaAprobacion}%</h5>
      </div>
    </div>

    <div class="row">
      <div class="col-12">
        <h6 class="text-muted mb-2">Distribución de Préstamos por Mes y Estado</h6>
        <canvas id="<%= stackedBarChartId %>" style="max-width: 100%; height: 200px;"></canvas>
      </div>
    </div>

    <div class="row mt-3">
      <div class="col text-end">
        <small class="text-muted">
          Período: ${fechaInicio} al ${fechaFin}
        </small>
      </div>
    </div>
  </div>

  <div class="card-footer bg-light text-end">
    <small class="text-muted">
      Actualizado: ${empty actualizacion ? (empty fechaFin ? 'hoy' : fechaFin) : actualizacion}
    </small>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
(function() {
  const chartId = '<%= stackedBarChartId %>';

  // Obtener labels (meses)
  const labels = [
    <c:forEach var="entry" items="${prestamosPorMesEstado}" varStatus="status">
      "${entry.key}"<c:if test="${!status.last}">,</c:if>
    </c:forEach>
  ];

  // Construir estructura de datos: prestamosPorMesEstado = { mes: { estado: cantidad, ... }, ... }
  const prestamosData = {
    <c:forEach var="mesEntry" items="${prestamosPorMesEstado}" varStatus="mesStatus">
      "${mesEntry.key}": {
        <c:forEach var="estadoEntry" items="${mesEntry.value}" varStatus="estadoStatus">
          "${estadoEntry.key}": ${estadoEntry.value}<c:if test="${!estadoStatus.last}">,</c:if>
        </c:forEach>
      }<c:if test="${!mesStatus.last}">,</c:if>
    </c:forEach>
  };

  // Solo estados válidos según la tabla Prestamos
  const estadosValidos = ['pendiente', 'aprobado', 'pagado'];

  const datasets = [];
  const colors = ['#ffc107', '#0d6efd', '#198754']; // amarillo, azul, verde (puedes ajustar)

  estadosValidos.forEach((estado, index) => {
    const data = labels.map(mes => prestamosData[mes]?.[estado] || 0);
    datasets.push({
      label: estado.charAt(0).toUpperCase() + estado.slice(1), // capitalizar estado
      data: data,
      backgroundColor: colors[index % colors.length]
    });
  });

  let chartsRenderizados = false;

  window['renderLoansRiskCharts_' + '<%= chartIdPrefix %>'] = function() {
    if (chartsRenderizados) return;
    const ctx = document.getElementById(chartId).getContext('2d');
    new Chart(ctx, {
      type: 'bar',
      data: {
        labels: labels,
        datasets: datasets
      },
      options: {
        responsive: true,
        plugins: {
          tooltip: { mode: 'index', intersect: false },
          legend: { position: 'top' }
        },
        scales: {
          x: { stacked: true },
          y: { stacked: true, beginAtZero: true }
        }
      }
    });
    chartsRenderizados = true;
  };

  const contenedor = document.getElementById('<%= containerId %>');
  if (contenedor) {
    contenedor.addEventListener('onInformeMostrado', function() {
      window['renderLoansRiskCharts_' + '<%= chartIdPrefix %>']();
    });
  }
})();
</script>
