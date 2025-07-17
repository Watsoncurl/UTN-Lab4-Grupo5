<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <title>Pagar Cuota - Préstamo</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>
<body>
  <my:navbar activeTab="prestamos" userRole="cliente" />
  <div class="container mt-4">
    <h2>Pagar Cuota del Préstamo</h2>
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Detalles del Préstamo</h5>
        <p><strong>ID Préstamo:</strong> <c:out value="${prestamo.idPrestamo}" /></p>
        <p><strong>Cliente:</strong> <c:out value="${prestamo.cliente.apellido}" />, <c:out value="${prestamo.cliente.nombre}" /> (<c:out value="${prestamo.cliente.dni}" />)</p>
        <p><strong>Fecha Alta:</strong> <c:out value="${prestamo.fechaAlta}" /></p>
        <p><strong>Importe:</strong> $<fmt:formatNumber value="${prestamo.importe}" type="number" minFractionDigits="2" /></p>
        <p><strong>Plazo:</strong> <c:out value="${prestamo.plazoMeses}" /> meses</p>
        <p><strong>Cuotas Pendientes:</strong> <c:out value="${prestamo.cuotasPendientes}" /></p>
        <p><strong>Cuota Mensual:</strong> $<fmt:formatNumber value="${prestamo.cuotaMensual}" type="number" minFractionDigits="2" /></p>
        
        <h5 class="mt-4">Realizar Pago</h5>
        <form action="${pageContext.request.contextPath}/PagarCuotaServlet" method="post">
          <input type="hidden" name="idCuenta" value="${prestamo.idCuenta}" /> <!-- Cambié a usar el idCuenta -->
          <input type="hidden" name="idPrestamo" value="${prestamo.idPrestamo}" />
          <div class="mb-3">
            <label class="form-label">Cantidad a Pagar:</label>
            <input type="text" class="form-control" value="${prestamo.cuotaMensual}" readonly />
          </div>
          <button type="submit" class="btn btn-primary">Confirmar Pago</button>
          <a href="${pageContext.request.contextPath}/ListarCuentasServlet" class="btn btn-secondary">Cancelar</a>
        </form>
      </div>
    </div>
  </div>
  <my:footer />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>