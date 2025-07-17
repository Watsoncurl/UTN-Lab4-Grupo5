<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transferir</title>
<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">
<link rel="stylesheet"
href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<link href="${pageContext.request.contextPath}/css/styles.css"
rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<my:navbar activeTab="transferencias" userRole="cliente" />
<div class="container mt-5">
<div class="row justify-content-center">
<div class="col-md-8">
<div class="card shadow-sm">
<div class="card-header bg-primary text-white">
<h4 class="mb-0">
<i class="bi bi-send me-2"></i>Realizar Transferencia
</h4>
</div>
<div class="card-body">
<!-- Mensaje de Error -->
<c:if test="${not empty requestScope.error}">
<div class="alert alert-danger" role="alert">
<i class="bi bi-exclamation-triangle me-2"></i>${requestScope.error}
</div>
</c:if>
<form action="TransferirServlet" method="post">
<!-- Cuenta de Origen -->
<div class="mb-3">
<label for="cuentaOrigen" class="form-label">Cuenta de
Origen:</label> <select class="form-select" id="cuentaOrigen"
name="cuentaOrigen" required onchange="actualizarSaldo()">
<option value="" disabled selected>Seleccione una
cuenta</option>
<c:forEach var="cuenta" items="${requestScope.cuentas}">
<option value="${cuenta.id_cuenta}">
${cuenta.nro_cuenta} - ${cuenta.tipo_cuenta}</option>
</c:forEach>
</select>
</div>
<!-- Saldo de la Cuenta de Origen (No Editable) -->
<div class="mb-3">
<label for="saldoOrigen" class="form-label">Saldo de la
Cuenta de Origen:</label>
<div class="input-group">
<span class="input-group-text">$</span> <input type="text"
class="form-control" id="saldoOrigen" name="saldoOrigen"
value="" readonly>
</div>
</div>
<!-- CBU Destino -->
<div class="mb-3">
<label for="cbuDestino" class="form-label">CBU Destino:</label>
<div class="input-group">
<input type="text" class="form-control" id="cbuDestino"
name="cbuDestino" required>
<button class="btn btn-outline-secondary" type="button"
id="button-addon2">
<i class="bi bi-search"></i>
</button>
</div>
<div id="cbuFeedback" class="form-text">
    <!--  El contenido del feedback (nombre, etc.) se insertará aquí -->
</div>
</div>
<!-- Importe -->
<div class="mb-3">
<label for="importe" class="form-label">Importe:</label>
<div class="input-group">
<span class="input-group-text">$</span> <input type="number"
step="0.01" class="form-control" id="importe" name="importe"
required>
</div>
</div>
<!-- Concepto -->
<div class="mb-3">
<label for="concepto" class="form-label">Concepto:</label> <input
type="text" class="form-control" id="concepto" name="concepto"
required>
</div>
<!-- Botón de Enviar -->
<div class="d-grid gap-2">
<button type="submit" class="btn btn-primary">
<i class="bi bi-send me-2"></i>Transferir
</button>
</div>
</form>
<div class="mt-3 text-center">
<a href="ClienteDashboard.jsp" class="btn btn-secondary"><i
class="bi bi-arrow-left me-2"></i>Volver al Dashboard</a>
</div>
</div>
</div>
</div>
</div>
</div>
<my:footer />
<script
src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
        $(document).ready(function() {
            $("#cbuDestino").on("input", function() {
                var cbu = $(this).val();
                var cbuFeedback = $("#cbuFeedback");

                if (cbu.length == 22) {
                    $.ajax({
                        url: "ServletValidarCBU",
                        type: "POST",
                        data: { cbu: cbu },
                        success: function(response) {
                            cbuFeedback.html(response);  // Insertamos el HTML directamente
                        }
                    });
                } else {
                    cbuFeedback.html(""); // Limpiar el feedback si no tiene la longitud correcta
                }
            });

            $("#cuentaOrigen").change(function() {
                actualizarSaldo();
            });
        });

        function actualizarSaldo() {
           var idCuenta = $("#cuentaOrigen").val();
           if (idCuenta) {
               $.ajax({
                   url: "ServletObtenerSaldo",
                   type: "GET",
                   data: { idCuenta: idCuenta },
                   success: function(response) {
                       $("#saldoOrigen").val(response);
                   }
               });
           } else {
               $("#saldoOrigen").val("");
           }
        }
    </script>
</body>
</html>