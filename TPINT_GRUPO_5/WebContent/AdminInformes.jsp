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

    <div class="row g-4">
      <!-- Informe de Clientes -->
      <div class="col-md-6">
        <my:report
          titulo="Informe de Clientes"
          icono="bi bi-people-fill"
          colorHeader="bg-primary"
          columnas="Clientes Activos, Clientes Inactivos, Total de Clientes"
          valores="50, 10, 60"
        />
      </div>

      <!-- Informe de Préstamos -->
      <div class="col-md-6">
        <my:report
          titulo="Informe de Préstamos"
          icono="bi bi-cash-coin"
          colorHeader="bg-success"
          columnas="Préstamos Activos, Préstamos Pagados, Total de Préstamos"
          valores="50, 10, 60"
        />
      </div>

      <!-- Informe de Cuentas -->
      <div class="col-md-6">
        <my:report
          titulo="Informe de Cuentas"
          icono="bi bi-bank"
          colorHeader="bg-info"
          columnas="Cuentas Corrientes, Cuentas de Ahorro, Total de Cuentas"
          valores="120, 85, 205"
        />
      </div>

      <!-- Informe de Transacciones -->
      <div class="col-md-6">
        <my:report
          titulo="Informe de Transacciones"
          icono="bi bi-arrow-left-right"
          colorHeader="bg-warning text-dark"
          columnas="Transferencias, Depósitos, Total Movimientos"
          valores="350, 210, 560"
        />
      </div>
    </div>
  </div>

  <my:footer />
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>