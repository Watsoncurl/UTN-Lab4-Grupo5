<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>Informes del Banco</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
  <my:navbar activeTab="informes" userRole="admin" />

  <div class="container mt-4">
    <div class="row mb-4">
      <div class="col-12">
        <h1 class="display-5 fw-bold"><i class="bi bi-graph-up me-2"></i>Informes del Banco</h1>
      </div>
    </div>

    <!-- Botones toggle centrados -->
    <div class="d-flex justify-content-center mb-4" role="group" aria-label="Toggle Informes">
      <div class="btn-group" role="group">
        <button class="btn btn-warning" onclick="mostrarInforme('transactionality', window['renderTransaccionalidadCharts_trans'])">Transaccionalidad</button>
        <button class="btn btn-success" onclick="mostrarInforme('growth', window['renderGrowthCharts_grw'])">Crecimiento de clientes</button>
        <button class="btn btn-primary" onclick="mostrarInforme('segmentation', window['renderSegmentationCharts_seg'])">Segmentación de clientes</button>
        <button class="btn btn-danger" onclick="mostrarInforme('loans', window['renderLoansRiskCharts_loans'])">Rendimientos de préstamos</button>
      </div>
    </div>

    <!-- INFORMES TOGGLEADOS -->

    <div id="transactionality" class="report-section">
      <my:transactionalityReport
        chartIdPrefix="trans"
        titulo="Análisis de Transaccionalidad"
        icono="bi bi-arrow-left-right"
        colorHeader="bg-warning text-dark"
        fechaInicio="${fechaInicio}"
        fechaFin="${fechaFin}"
        tipoMovimiento="${tipoMovimiento}"
        volumenPorTipo="${volumenPorTipo}"
        montoPorTipo="${montoPorTipo}"
        importePromedioPorTipo="${importePromedioPorTipo}"
        actualizacion="${fechaReporte}" />
    </div>

    <div id="growth" class="report-section d-none">
      <my:customerGrowthReport
        chartIdPrefix="grw"
        fechaInicio="${fechaInicio}"
        fechaFin="${fechaFin}"
        nuevosClientesPorFecha="${nuevosClientesPorFecha}"
        nuevasCuentasPorFecha="${nuevasCuentasPorFecha}"
        cuentasPorTipo="${cuentasPorTipo}"
        tasaCrecimiento="${tasaCrecimiento}"
        titulo="Crecimiento y Adquisición de Clientes"
        icono="bi bi-person-plus-fill"
        colorHeader="bg-success"
        actualizacion="${fechaReporte}" />
    </div>

    <div id="segmentation" class="report-section d-none">
      <my:segmentationReport
        chartIdPrefix="seg"
        titulo="Segmentación de Clientes por Saldo"
        icono="bi bi-pie-chart-fill"
        colorHeader="bg-primary"
        actualizacion="${fechaReporte}"
        fechaReporte="${fechaReporte}"
        cantidadClientesPorSegmento="${cantidadClientesPorSegmento}"
        montoTotalPorSegmento="${montoTotalPorSegmento}"
        porcentajeSaldoPorSegmento="${porcentajeSaldoPorSegmento}" />
    </div>

    <div id="loans" class="report-section d-none">
      <my:loansYieldRisk
        chartIdPrefix="loans"
        titulo="Rendimiento y Riesgo de Préstamos"
        icono="bi bi-bar-chart-fill"
        colorHeader="bg-danger"
        actualizacion="${fechaReporte}"
        fechaInicio="${fechaInicio}"
        fechaFin="${fechaFin}"
        capitalPrestado="${capitalPrestado}"
        cantidadPrestamos="${cantidadPrestamos}"
        tasaAprobacion="${tasaAprobacion}"
        tasaMorosidad="${tasaMorosidad}"
        prestamosPorEstado="${prestamosPorEstado}"
        prestamosPorMesEstado="${prestamosPorMesEstado}" />
    </div>

    <!-- INFORMES SIMPLES -->
    <div class="row mt-5">
      <div class="col-md-6 mb-4">
        <my:report
          titulo="Clientes"
          icono="bi bi-people-fill"
          colorHeader="bg-primary"
          columnas="Clientes Activos, Clientes Inactivos, Total de Clientes"
          valores="50, 10, 60" />
      </div>

      <div class="col-md-6 mb-4">
        <my:report
          titulo="Préstamos"
          icono="bi bi-cash-coin"
          colorHeader="bg-success"
          columnas="Préstamos Activos, Préstamos Pagados, Total de Préstamos"
          valores="50, 10, 60" />
      </div>

      <div class="col-md-6 mb-4">
        <my:report
          titulo="Cuentas"
          icono="bi bi-bank"
          colorHeader="bg-info"
          columnas="Cuentas Corrientes, Cuentas de Ahorro, Total de Cuentas"
          valores="120, 85, 205" />
      </div>

      <div class="col-md-6 mb-4">
        <my:report
          titulo="Transacciones"
          icono="bi bi-arrow-left-right"
          colorHeader="bg-warning text-dark"
          columnas="Transferencias, Depósitos, Total Movimientos"
          valores="350, 210, 560" />
      </div>
    </div>

  </div>

  <my:footer />

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
  <script>
    function mostrarInforme(id, callback) {
      document.querySelectorAll('.report-section').forEach(el => el.classList.add('d-none'));
      const selected = document.getElementById(id);
      selected.classList.remove('d-none');

      const event = new Event("onInformeMostrado");
      selected.dispatchEvent(event);

      if (typeof callback === 'function') callback();
    }

    window.addEventListener('DOMContentLoaded', function () {
      const transaccionalidadSection = document.getElementById('transactionality');
      if (transaccionalidadSection && !transaccionalidadSection.classList.contains('d-none')) {
        const event = new Event("onInformeMostrado");
        transaccionalidadSection.dispatchEvent(event);

        if (typeof window['renderTransaccionalidadCharts_trans'] === 'function') {
          window['renderTransaccionalidadCharts_trans']();
        }
      }
    });
  </script>
</body>
</html>
