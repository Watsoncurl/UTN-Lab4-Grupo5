<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Revisión de préstamo</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body class="bg-light">

<my:navbar activeTab="prestamos" userRole="admin" />

<div class="container py-4">
    <div class="row justify-content-center">
        <div class="col-lg-8">
            <div class="card shadow-sm">
                <div class="card-header bg-white border-bottom-0 pt-4">
                    <h2 class="h4 text-center mb-0">Revisión de solicitud de préstamo</h2>
                </div>
                
                <div class="card-body">
                    <!-- Cambié el formulario por enlaces simples -->
                    <div class="row g-3">
                        <!-- Primera columna -->
                        <div class="col-md-6">
                            <div class="form-floating">
                                <input type="text" class="form-control-plaintext" id="cuentaDeposito" value="12345678" readonly>
                                <label for="cuentaDeposito">Cuenta a depositar</label>
                            </div>
                            
                            <div class="form-floating mt-3">
                                <input type="text" class="form-control-plaintext" id="importe" value="$50.000" readonly>
                                <label for="importe">Importe solicitado</label>
                            </div>
                            
                            <div class="form-floating mt-3">
                                <input type="text" class="form-control-plaintext" id="cuotas" value="12" readonly>
                                <label for="cuotas">Cantidad de cuotas</label>
                            </div>
                        </div>
                        
                        <!-- Segunda columna -->
                        <div class="col-md-6">
                            <div class="form-floating">
                                <input type="text" class="form-control-plaintext" id="interes" value="10%" readonly>
                                <label for="interes">Interés aplicado</label>
                            </div>
                            
                            <div class="form-floating mt-3">
                                <input type="text" class="form-control-plaintext" id="montoFinal" value="$55.000" readonly>
                                <label for="montoFinal">Monto final con intereses</label>
                            </div>
                            
                            <div class="form-floating mt-3">
                                <input type="text" class="form-control-plaintext" id="valorCuota" value="$4.583,33" readonly>
                                <label for="valorCuota">Valor por cuota</label>
                            </div>
                        </div>
                    </div>
                    
                    <div class="d-flex justify-content-between mt-4 pt-3 border-top">
                        <a href="AdminPrestamos.jsp" class="btn btn-success px-4">
                            <i class="bi bi-check-circle me-2"></i> Autorizar
                        </a>
                        <a href="AdminPrestamos.jsp" class="btn btn-danger px-4">
                            <i class="bi bi-x-circle me-2"></i> Rechazar
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<my:footer />

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>