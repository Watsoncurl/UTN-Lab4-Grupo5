<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
  <title>Clientes - Admin</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
  <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet">
</head>
<body>
  <my:navbar activeTab="clientes" userRole="admin" />

  <div class="container mt-4">
    <div class="row mb-3 g-2">
      <div class="col-md-6">
        <input type="search" class="form-control" placeholder="Buscar (DNI, Nombre, Apellido, Email)">
      </div>
      <div class="col-md-2">
        <select class="form-select">
          <option selected>Todos los estados</option>
          <option>Activos</option>
          <option>Inactivos</option>
        </select>
      </div>
      <div class="col-md-2">
        <select class="form-select">
          <option selected>Todos los sexos</option>
          <option>Masculino</option>
          <option>Femenino</option>
          <option>Otro</option>
        </select>
      </div>
      <div class="col-md-2">
        <a href="AgregarCliente.jsp" class="btn btn-success w-100">
          <i class="bi bi-plus-circle"></i> Nuevo
        </a>
      </div>
    </div>

    <div class="table-responsive">
      <table class="table table-hover border shadow-sm">
        <thead class="table-light">
          <tr>
            <th>DNI</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Teléfono</th>
            <th>Localidad</th>
            <th class="text-center">Estado</th>
            <th class="text-end">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>30123456</td>
            <td>Gómez, María</td>
            <td>maria.gomez@email.com</td>
            <td>3511234567</td>
            <td>Córdoba</td>
            <td class="text-center align-middle">
              <span class="badge bg-success">Activo</span>
            </td>
            <td class="text-end">
              <div class="btn-group" role="group">
                <button class="btn btn-sm btn-outline-primary">
                  <i class="bi bi-eye"></i>
                </button>
                <button class="btn btn-sm btn-outline-secondary">
                  <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-sm btn-outline-danger">
                  <i class="bi bi-trash"></i>
                </button>
              </div>
            </td>
          </tr>
          <tr>
            <td>28987654</td>
            <td>Pérez, Juan</td>
            <td>juan.perez@email.com</td>
            <td>3517654321</td>
            <td>Villa María</td>
            <td class="text-center align-middle">
              <span class="badge bg-success">Activo</span>
            </td>
            <td class="text-end">
              <div class="btn-group" role="group">
                <button class="btn btn-sm btn-outline-primary">
                  <i class="bi bi-eye"></i>
                </button>
                <button class="btn btn-sm btn-outline-secondary">
                  <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-sm btn-outline-danger">
                  <i class="bi bi-trash"></i>
                </button>
              </div>
            </td>
          </tr>
          <tr>
            <td>35678901</td>
            <td>Fernández, Lucía</td>
            <td>lucia.fernandez@email.com</td>
            <td>3519876543</td>
            <td>Río Cuarto</td>
            <td class="text-center align-middle">
              <span class="badge bg-warning text-dark">Pendiente</span>
            </td>
            <td class="text-end">
              <div class="btn-group" role="group">
                <button class="btn btn-sm btn-outline-primary">
                  <i class="bi bi-eye"></i>
                </button>
                <button class="btn btn-sm btn-outline-secondary">
                  <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-sm btn-outline-danger">
                  <i class="bi bi-trash"></i>
                </button>
              </div>
            </td>
          </tr>
          <tr>
            <td>33456789</td>
            <td>López, Carlos</td>
            <td>carlos.lopez@email.com</td>
            <td>3514567890</td>
            <td>Alta Gracia</td>
            <td class="text-center align-middle">
              <span class="badge bg-danger">Inactivo</span>
            </td>
            <td class="text-end">
              <div class="btn-group" role="group">
                <button class="btn btn-sm btn-outline-primary">
                  <i class="bi bi-eye"></i>
                </button>
                <button class="btn btn-sm btn-outline-secondary">
                  <i class="bi bi-pencil"></i>
                </button>
                <button class="btn btn-sm btn-outline-danger">
                  <i class="bi bi-trash"></i>
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <nav class="mt-3">
      <ul class="pagination justify-content-center">
        <li class="page-item disabled">
          <span class="page-link">Anterior</span>
        </li>
        <li class="page-item active"><span class="page-link">1</span></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item"><a class="page-link" href="#">10</a></li>
        <li class="page-item">
          <a class="page-link" href="#">Siguiente</a>
        </li>
      </ul>
    </nav>
  </div>

  <my:footer />
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>