<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agregar Cliente</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body class="bg-light">
    <my:navbar activeTab="agregarCliente" userRole="admin" />
    
    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-lg-10">
                <div class="card shadow">
                    <div class="card-header bg-primary text-white">
                        <h2 class="h4 mb-0"><i class="bi bi-person-plus me-2"></i>Agregar Cliente</h2>
                    </div>
                    
                    <div class="card-body">
                        <form action="AgregarCliente" method="post" class="row g-3 needs-validation" novalidate>
                            <!-- Primera fila -->
                            <div class="col-md-6">
                                <label for="nombre" class="form-label">Nombre</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-person"></i></span>
                                    <input type="text" class="form-control" id="nombre" name="Nombre" required placeholder="Nombre...">
                                    <div class="invalid-feedback">
                                        Por favor ingrese el nombre
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-md-6">
                                <label for="apellido" class="form-label">Apellido</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-person"></i></span>
                                    <input type="text" class="form-control" id="apellido" name="Apellido" required placeholder="Apellido...">
                                    <div class="invalid-feedback">
                                        Por favor ingrese el apellido
                                    </div>
                                </div>
                            </div>
                            
                            <!-- Segunda fila -->
                            <div class="col-md-4">
                                <label for="dni" class="form-label">DNI</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-credit-card"></i></span>
                                    <input type="text" class="form-control" id="dni" name="DNI" required placeholder="DNI...">
                                    <div class="invalid-feedback">
                                        Por favor ingrese el DNI
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-md-4">
                                <label for="cuil" class="form-label">CUIL</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-credit-card"></i></span>
                                    <input type="text" class="form-control" id="cuil" name="CUIL" required placeholder="Cuil...">
                                    <div class="invalid-feedback">
                                        Por favor ingrese el CUIL
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-md-4">
                                <label for="telefono" class="form-label">Teléfono</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-telephone"></i></span>
                                    <input type="text" class="form-control" id="telefono" name="Telefono" required placeholder="Telefono...">
                                    <div class="invalid-feedback">
                                        Por favor ingrese el teléfono
                                    </div>
                                </div>
                            </div>
                            
                            <!-- Tercera fila -->
                            <div class="col-md-6">
                                <label for="email" class="form-label">Email</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-envelope"></i></span>
                                    <input type="email" class="form-control" id="email" name="Email" required placeholder="Email...">
                                    <div class="invalid-feedback">
                                        Por favor ingrese un email válido
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-md-6">
                                <label for="fechaNacimiento" class="form-label">Fecha de nacimiento</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-calendar"></i></span>
                                    <input type="date" class="form-control" id="fechaNacimiento" name="FechaNac" required>
                                    <div class="invalid-feedback">
                                        Por favor ingrese la fecha de nacimiento
                                    </div>
                                </div>
                            </div>
                            
                            <!-- Cuarta fila -->
                            <div class="col-md-6">
                                <label for="nacionalidad" class="form-label">Nacionalidad</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-globe"></i></span>
                                    <input type="text" class="form-control" id="nacionalidad" name="Nacionalidad" required placeholder="Nacionalidad...">
                                    <div class="invalid-feedback">
                                        Por favor ingrese la nacionalidad
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-md-3">
                                <label for="sexo" class="form-label">Sexo</label>
                                <select class="form-select" id="sexo" name="Sexo" required>
                                    <option value="">Seleccione...</option>
                                    <option value="M">Masculino</option>
                                    <option value="F">Femenino</option>
                                    <option value="O">Otro</option>
                                </select>
                                <div class="invalid-feedback">
                                    Por favor seleccione el sexo
                                </div>
                            </div>
                            
                            <div class="col-md-3">
                                <label for="provincia" class="form-label">Provincia</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-geo-alt"></i></span>
                                    <input type="text" class="form-control" id="provincia" name="Provincia" required placeholder="Provincia...">
                                    <div class="invalid-feedback">
                                        Por favor ingrese la provincia
                                    </div>
                                </div>
                            </div>
                            
                            <!-- Quinta fila -->
                            <div class="col-md-6">
                                <label for="localidad" class="form-label">Localidad</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-geo"></i></span>
                                    <input type="text" class="form-control" id="localidad" name="Localidad" required placeholder="Localidad...">
                                    <div class="invalid-feedback">
                                        Por favor ingrese la localidad
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-md-6">
                                <label for="direccion" class="form-label">Dirección</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-house"></i></span>
                                    <input type="text" class="form-control" id="direccion" name="Direccion" required placeholder="Direccion...">
                                    <div class="invalid-feedback">
                                        Por favor ingrese la dirección
                                    </div>
                                </div>
                            </div>
                            
                            <!-- Sexta fila -->
                            <div class="col-md-6">
                                <label for="usuario" class="form-label">Usuario</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-person-badge"></i></span>
                                    <input type="text" class="form-control" id="usuario" name="Usuario" required placeholder="Usuario...">
                                    <div class="invalid-feedback">
                                        Por favor ingrese el usuario
                                    </div>
                                </div>
                            </div>
                            
                            <div class="col-md-6">
                                <label for="contrasenia" class="form-label">Contraseña</label>
                                <div class="input-group">
                                    <span class="input-group-text"><i class="bi bi-lock"></i></span>
                                    <input type="password" class="form-control" id="contrasenia" name="Contrasenia" required placeholder="******">
                                    <button class="btn btn-outline-secondary toggle-password" type="button">
                                        <i class="bi bi-eye"></i>
                                    </button>
                                    <div class="invalid-feedback">
                                        Por favor ingrese la contraseña
                                    </div>
                                </div>
                            </div>
                            
                            <!-- Botón de submit -->
                            <div class="col-12 text-end mt-4">
                                <button class="btn btn-primary px-4" type="submit" name="btnGuardar">
                                    <i class="bi bi-save me-2"></i>Guardar Cliente
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <my:footer />

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        // Toggle para mostrar/ocultar contraseña
        document.querySelector('.toggle-password').addEventListener('click', function() {
            const passwordField = document.getElementById('contrasenia');
            const icon = this.querySelector('i');
            if (passwordField.type === 'password') {
                passwordField.type = 'text';
                icon.classList.replace('bi-eye', 'bi-eye-slash');
            } else {
                passwordField.type = 'password';
                icon.classList.replace('bi-eye-slash', 'bi-eye');
            }
        });
        
        // Validación de formulario
        (function() {
            'use strict'
            const forms = document.querySelectorAll('.needs-validation')
            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {
                    if (!form.checkValidity()) {
                        event.preventDefault()
                        event.stopPropagation()
                    }
                    form.classList.add('was-validated')
                }, false)
            })
        })()
	</script>
</body>
</html>