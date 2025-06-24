<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>Mis Cuentas</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
  <my:navbar activeTab="misCuentas" userRole="cliente" />

  <div class="container mt-4">
    <div class="row mb-4">
      <div class="col-12">
        <h1 class="display-5 fw-bold">Mis Cuentas</h1>
        <p class="lead text-muted">Administra tus cuentas bancarias</p>
      </div>
    </div>

    <div class="row g-4">
      <div class="col-md-6">
        <div class="card h-100 border-0 shadow-sm">
          <div class="row g-0 h-100">
            <div class="col-md-4 d-flex align-items-center bg-light">
              <div class="p-3 w-100 text-center">
                <i class="bi bi-bank fs-1 text-primary"></i>
              </div>
            </div>
            <div class="col-md-8">
              <div class="card-body d-flex flex-column h-100">
                <h5 class="card-title fw-bold">Cuenta Corriente</h5>
                <p class="card-text text-muted flex-grow-1">Accede a tu cuenta corriente para realizar operaciones diarias.</p>
                <div class="d-grid gap-2 d-md-block">
                  <a href="#" class="btn btn-primary me-2">Ingresar</a>
                  <a href="#" class="btn btn-outline-secondary">Transferir</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-md-6">
        <div class="card h-100 border-0 shadow-sm">
          <div class="row g-0 h-100">
            <div class="col-md-4 d-flex align-items-center bg-light">
              <div class="p-3 w-100 text-center">
                <i class="bi bi-piggy-bank fs-1 text-success"></i>
              </div>
            </div>
            <div class="col-md-8">
              <div class="card-body d-flex flex-column h-100">
                <h5 class="card-title fw-bold">Cuenta de Ahorros</h5>
                <p class="card-text text-muted flex-grow-1">Gestiona tus ahorros y consulta tus beneficios.</p>
                <div class="d-grid gap-2 d-md-block">
                  <a href="#" class="btn btn-primary me-2">Ingresar</a>
                  <a href="#" class="btn btn-outline-secondary">Transferir</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="col-md-6">
        <div class="card h-100 border-0 shadow-sm">
          <div class="row g-0 h-100">
            <div class="col-md-4 d-flex align-items-center bg-light">
              <div class="p-3 w-100 text-center">
                <i class="bi bi-coin fs-1 text-warning"></i>
              </div>
            </div>
            <div class="col-md-8">
              <div class="card-body d-flex flex-column h-100">
                <h5 class="card-title fw-bold">Cuenta Inversión</h5>
                <p class="card-text text-muted flex-grow-1">Consulta y gestiona tus productos de inversión.</p>
                <div class="d-grid gap-2 d-md-block">
                  <a href="#" class="btn btn-primary me-2">Ingresar</a>
                  <a href="#" class="btn btn-outline-secondary">Transferir</a>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <my:footer />
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>