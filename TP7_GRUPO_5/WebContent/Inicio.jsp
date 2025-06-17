<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página de Inicio</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">SegurosApp</a>
    <div class="navbar-nav">
      <a class="nav-link active" href="Inicio.jsp">Inicio</a>
      <a class="nav-link" href="AgregarSeguro.jsp">Agregar Seguro</a>
      <a class="nav-link" href="ListarSeguros">Listar Seguros</a>
    </div>
  </div>
</nav>

<!-- CONTENIDO PRINCIPAL -->
<div class="container mt-5 text-center">
    <h1 class="display-4">Bienvenido a SegurosApp</h1>
    <p class="lead">Administra tus seguros de forma sencilla y rápida.</p>

    <div class="mt-4">
        <a href="AgregarSeguro.jsp" class="btn btn-success me-2">Agregar Seguro</a>
        <a href="ListarSeguros" class="btn btn-outline-primary">Listar Seguros</a>
    </div>
</div>

</body>
</html>
