<%@ tag description="Pie de página con logo y créditos" pageEncoding="UTF-8" %>

<footer class="footer-custom py-3 mt-auto" style="background-color: var(--color-primary); color: var(--color-text-light);">
  <div class="container-fluid">
    <div class="row align-items-center">
      <!-- Logo + Derechos (izquierda) -->
      <div class="col-md-6">
        <div class="d-flex align-items-center">
          <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo Banco" width="40" height="40" class="me-3">
          <span>© 2025. Todos los derechos reservados.</span>
        </div>
      </div>
      
      <!-- Créditos del equipo (derecha) -->
      <div class="col-md-6 text-md-end mt-2 mt-md-0">
        <p class="mb-0 small">
          <h5>💻 Proyecto de Laboratorio 4 "Grupo 5"</h5>
          <span class="d-block d-md-inline">
            Nadia Hospitaleche, Iván Ignacio Lecumberry, Hernán Darío Molina, 
            Lucía Griselda Monges, Facundo Ezequiel Romano, Nicolás Marcelo Romero
          </span>
      </div>
    </div>
  </div>
</footer>