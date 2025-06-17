<%@ tag description="Navbar con logo y botones de login/registro" pageEncoding="UTF-8" %>
<%@ attribute name="brandName" required="false" type="java.lang.String" %>
<%@ attribute name="logoPath" required="false" type="java.lang.String" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <!-- Logo + Nombre de la marca -->
    <a class="navbar-brand" href="#">
      <img src="${logoPath}" width="40" height="40" class="d-inline-block align-top" alt="Logo">
      ${not empty brandName ? brandName : 'MiApp'}
    </a>

    <!-- Botones de Login y Registro (alineados a la derecha) -->
    <div class="ms-auto">
      <a href="#" class="btn btn-outline-primary me-2">Iniciar sesión</a>
      <a href="#" class="btn btn-primary">Registrarse</a>
    </div>
  </div>
</nav>