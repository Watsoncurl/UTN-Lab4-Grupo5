<%@ tag description="Formulario de login limpio y reutilizable" pageEncoding="UTF-8" %>
<div class="d-flex justify-content-center">
  <div class="auth-card" style="width: 100%; max-width: 400px;">
    <h2 class="auth-title text-center mb-4">Ingreso al Banco</h2>
    
    <form class="auth-form">
      <!-- Campo Usuario -->
      <div class="auth-form-group mb-3">
        <label for="usuario" class="auth-label">Usuario:</label>
        <input 
          type="text" 
          class="auth-input form-control" 
          id="usuario" 
          name="usuario" 
          required
        >
      </div>
      
      <!-- Campo Contraseña -->
      <div class="auth-form-group mb-3 password-group">
        <label for="clave" class="auth-label">Contraseña:</label>
        <div class="input-group">
          <input 
            type="password" 
            class="auth-input form-control" 
            id="clave" 
            name="clave" 
            required
          >
        </div>
      </div>
      
      <!-- Botón de Submit -->
      <div class="d-grid">
        <button type="submit" class="auth-button btn btn-primary">
          Iniciar sesión
        </button>
      </div>
    </form>
  </div>
</div>

<script>
  document.querySelector('.password-toggle').addEventListener('click', function() {
    const passwordField = document.getElementById('clave');
    const icon = this.querySelector('i');
    if (passwordField.type === 'password') {
      passwordField.type = 'text';
      icon.classList.replace('fa-eye', 'fa-eye-slash');
    } else {
      passwordField.type = 'password';
      icon.classList.replace('fa-eye-slash', 'fa-eye');
    }
  });
</script>