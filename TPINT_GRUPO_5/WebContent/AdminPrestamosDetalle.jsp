<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
                    <!-- Datos del préstamo -->
                    <div class="row g-3">
                        <!-- Primera columna -->
                        <div class="col-md-6">
                            <div class="form-floating">
                                <input type="text" class="form-control-plaintext" id="cuentaDeposito" 
                                       value="${prestamo.idCuenta}" readonly>
                                <label for="cuentaDeposito">Cuenta a depositar</label>
                            </div>

                            <div class="form-floating mt-3">
                                <input type="text" class="form-control-plaintext text-end" 
                                       value="$<fmt:formatNumber value='${prestamo.importe}' type='number' minFractionDigits='2'/>" readonly>
                                <label>Importe solicitado</label>
                            </div>
                            
                            <div class="form-floating mt-3">
                                <input type="text" class="form-control-plaintext" id="cuotas" 
                                       value="${prestamo.plazoMeses}" readonly>
                                <label for="cuotas">Cantidad de cuotas</label>
                            </div>
                        </div>
                        
                        <!-- Segunda columna -->
                        <div class="col-md-6">
                            <div class="form-floating">
                                <input type="text" class="form-control-plaintext" id="interes" 
                                       value="10%" readonly>
                                <label for="interes">Interés aplicado</label>
                            </div>
                            
                            <div class="form-floating mt-3">
                                <input type="text" class="form-control-plaintext text-end" 
                                       value="$<fmt:formatNumber value='${prestamo.importe + (prestamo.importe * 0.10 * (prestamo.plazoMeses / 12.0))}' type='number' minFractionDigits='2'/>" readonly>
                                <label>Monto final con intereses</label>
                            </div>
                            
                            <div class="form-floating mt-3">
                                <input type="text" class="form-control-plaintext text-end" 
                                       value="$<fmt:formatNumber value='${prestamo.cuotaMensual}' type='number' minFractionDigits='2'/>" readonly>
                                <label>Valor por cuota</label>
                            </div>
                        </div>
                    </div>
                    
                    <!-- Botones de acción -->
					<div class="d-flex justify-content-between mt-4 pt-3 border-top">
					    <!-- Botón para volver a la lista a la izquierda -->
					    <a href="ServletAdminPrestamos" class="btn btn-secondary px-4">
					        <i class="bi bi-arrow-left-circle me-2"></i> Volver a la lista
					    </a>
					
					    <div>
					        <c:if test="${prestamo.estado == 'pendiente'}">
					            <form action="ServletAdminPrestamosDetalle" method="post" class="d-inline me-2">
					                <input type="hidden" name="id" value="${prestamo.idPrestamo}" />
					                <input type="hidden" name="accion" value="aceptar" />
					                <button type="submit" class="btn btn-success px-4">
					                    <i class="bi bi-check-circle me-2"></i> Autorizar
					                </button>
					            </form>
					            <form action="ServletAdminPrestamosDetalle" method="post" class="d-inline">
					                <input type="hidden" name="id" value="${prestamo.idPrestamo}" />
					                <input type="hidden" name="accion" value="rechazar" />
					                <button type="submit" class="btn btn-danger px-4">
					                    <i class="bi bi-x-circle me-2"></i> Rechazar
					                </button>
					            </form>
					        </c:if>
					    </div>
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
