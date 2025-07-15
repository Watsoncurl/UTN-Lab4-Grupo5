<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transferencia Exitosa</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
    <my:navbar activeTab="transferencias" userRole="cliente" />

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-header bg-success text-white">
                        <h4 class="mb-0"><i class="bi bi-check-circle me-2"></i>Transferencia Exitosa</h4>
                    </div>
                    <div class="card-body text-center">
                        <p class="lead">
                            <i class="bi bi-check-lg text-success me-2"></i>
                            La transferencia se ha completado correctamente.
                        </p>
                        <a href="ClienteDashboard.jsp" class="btn btn-primary"><i class="bi bi-arrow-left me-2"></i>Volver al Dashboard</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <my:footer />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>