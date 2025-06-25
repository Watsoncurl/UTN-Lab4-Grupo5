<%@tag description="Formulario de login limpio y reutilizable" pageEncoding="UTF-8"%>
<form action="LoginServlet" method="post">
    <div class="auth-card" style="width: 100%; max-width: 400px;">
      <h2 class="auth-title text-center mb-4">Ingreso al Banco</h2>
  
        <div class="auth-form-group mb-3">
          <label for="usuario" class="auth-label">Usuario:</label>
          <input
            type="text"
            class="auth-input form-control"
            id="usuario"
            name="usuario"
            required />
        </div>
    
        <div class="auth-form-group mb-3 password-group">
          <label for="contrasena" class="auth-label">Contraseña:</label>
          <div class="input-group">
            <input
              type="password"
              class="auth-input form-control"
              id="contrasena"
              name="contrasena"
              required />
          </div>
        </div>

        <div class="d-grid">
          <button type="submit" class="auth-button btn btn-primary">
            Iniciar sesión
          </button>
        </div>
    </div>
</form>