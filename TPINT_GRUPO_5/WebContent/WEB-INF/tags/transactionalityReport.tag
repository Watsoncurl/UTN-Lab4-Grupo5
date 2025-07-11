<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="chartIdPrefix" required="true" type="java.lang.String" %>
<%@ attribute name="fechaInicio" required="true" type="java.lang.String" %>
<%@ attribute name="fechaFin" required="true" type="java.lang.String" %>
<%@ attribute name="tipoMovimiento" required="false" type="java.lang.String" %>
<%@ attribute name="volumenPorTipo" required="true" type="java.util.Map" %>
<%@ attribute name="montoPorTipo" required="true" type="java.util.Map" %>
<%@ attribute name="importePromedioPorTipo" required="true" type="java.util.Map" %>
<%@ attribute name="titulo" required="true" type="java.lang.String" %>
<%@ attribute name="icono" required="true" type="java.lang.String" %>
<%@ attribute name="colorHeader" required="true" type="java.lang.String" %>
<%@ attribute name="actualizacion" required="false" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
  String pieId = chartIdPrefix + "_pieTransaccionesChart";
  String montoId = chartIdPrefix + "_montoTransaccionesChart";
  String containerId = chartIdPrefix + "_container";
%>

<div class="card shadow-sm h-100 mb-4" id="<%= containerId %>">
  <!-- Encabezado -->
  <div class="card-header ${colorHeader} text-white">
    <h5 class="card-title mb-0">
      <i class="${icono} me-2"></i>${titulo}
    </h5>
  </div>

  <!-- Cuerpo -->
  <div class="card-body">
    <div class="row text-center">
      <div class="col-md-6 mb-4">
        <h6 class="text-muted mb-2">Volumen de Transacciones</h6>
        <canvas id="<%= pieId %>" width="50" height="50"></canvas>
      </div>

      <div class="col-md-6 mb-4">
        <h6 class="text-muted mb-2">Monto Total por Tipo</h6>
        <canvas id="<%= montoId %>" width="50" height="50"></canvas>
      </div>
    </div>

    <div class="row">
      <div class="col text-end">
        <small class="text-muted">
          Período: ${fechaInicio} al ${fechaFin}
          <c:if test="${not empty tipoMovimiento}"> | Filtro: <strong>${tipoMovimiento}</strong></c:if>
        </small>
      </div>
    </div>
  </div>

  <!-- Footer -->
  <div class="card-footer bg-light text-end">
    <small class="text-muted">
      Actualizado: ${empty actualizacion ? fechaFin : actualizacion}
    </small>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
  (function() {
    const tiposMovimiento = [
      <c:forEach var="entry" items="${volumenPorTipo}" varStatus="s">
        "${entry.key}"<c:if test="${!s.last}">,</c:if>
      </c:forEach>
    ];
    const volumenes = [
      <c:forEach var="entry" items="${volumenPorTipo}" varStatus="s">
        ${entry.value}<c:if test="${!s.last}">,</c:if>
      </c:forEach>
    ];
    const montos = [
      <c:forEach var="entry" items="${montoPorTipo}" varStatus="s">
        ${entry.value}<c:if test="${!s.last}">,</c:if>
      </c:forEach>
    ];

    let chartsRenderizados = false;

    window['renderTransaccionalidadCharts_' + '<%= chartIdPrefix %>'] = function() {
      if (chartsRenderizados) return;

      const pieCtx = document.getElementById('<%= pieId %>').getContext('2d');
      const barCtx = document.getElementById('<%= montoId %>').getContext('2d');

      new Chart(pieCtx, {
        type: 'pie',
        data: {
          labels: tiposMovimiento,
          datasets: [{
            data: volumenes,
            backgroundColor: ['#0d6efd', '#198754', '#ffc107', '#dc3545', '#6f42c1'],
            borderWidth: 1
          }]
        },
        options: {
          responsive: true,
          plugins: { legend: { position: 'bottom' } }
        }
      });

      new Chart(barCtx, {
        type: 'bar',
        data: {
          labels: tiposMovimiento,
          datasets: [{
            label: 'Monto Total',
            data: montos,
            backgroundColor: '#0dcaf0'
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

      chartsRenderizados = true;
    };

    // Evento para renderizar cuando el contenedor es visible
    const contenedor = document.getElementById('<%= containerId %>');
    if (contenedor) {
      contenedor.addEventListener('onInformeMostrado', function() {
        window['renderTransaccionalidadCharts_' + '<%= chartIdPrefix %>']();
      });
    }
  })();
</script>