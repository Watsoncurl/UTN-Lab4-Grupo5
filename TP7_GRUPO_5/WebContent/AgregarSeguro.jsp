<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Seguro" %>
<%@ page import="entidad.TipoSeguro" %>
<%@ page import="NegocioImpl.SeguroNegocioImpl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar Seguros</title>

<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body class="bg-light">

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">SegurosApp</a>
    <div class="navbar-nav">
      <a class="nav-link" href="Inicio.jsp">Inicio</a>
      <a class="nav-link active" href="AgregarSeguro.jsp">Agregar Seguro</a>
      <a class="nav-link" href="ListarSeguros.jsp">Listar Seguros</a>
    </div>
  </div>
</nav>

<!-- CONTENIDO -->
<div class="container mt-5">
  <h2 class="mb-4">Agregar Seguro</h2>
  
  <%
 	ArrayList<TipoSeguro> listaTipos = null;
 	if(request.getAttribute("listaTipos")!=null)
 	{
 		listaTipos = (ArrayList<TipoSeguro>)request.getAttribute("listaTipos");
 		
 	}
   %>
  
  <form action="AgregarSeguro" method="post" class="row g-3">
    
    <div class="col-md-6">
      <label for="idSeguro" class="form-label">Id Seguro</label>
      <input type="text" class="form-control" id="idSeguro" name="IdSeguro">
    </div>
    
    <div class="col-md-6">
      <label for="descripcion" class="form-label">Descripción</label>
      <input type="text" class="form-control" id="descripcion" name="Descripcion">
    </div>

    <div class="col-md-6">
      <label for="tipo" class="form-label">Tipo de seguro</label>
      <select class="form-select" id="tipo" name="Tipo">
      	<%  if(listaTipos!=null)
                for(TipoSeguro tipo : listaTipos) {
        %>
        <option value="<% tipo.getIdTipoSeguro(); %>"><% tipo.getNombre(); %></option>
        <% } %>
      </select>
    </div>

    <div class="col-md-6">
      <label for="costoCont" class="form-label">Costo de contratación</label>
      <input type="text" class="form-control" id="costoCont" name="CostoCont">
    </div>

    <div class="col-md-6">
      <label for="costoMax" class="form-label">Costo máximo asegurado</label>
      <input type="text" class="form-control" id="costoMax" name="CostoMax">
    </div>

    <div class="col-12">
      <button type="submit" class="btn btn-success" name="btnAceptar">Aceptar</button>
    </div>
  </form>
</div>

<!-- MODAL DE ÉXITO -->
<div class="modal fade" id="exitoModal" tabindex="-1" aria-labelledby="exitoLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-success text-white">
        <h5 class="modal-title" id="exitoLabel">Éxito</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        <%= request.getAttribute("mensaje") != null ? request.getAttribute("mensaje") : "" %>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-outline-success" data-bs-dismiss="modal">Aceptar</button>
      </div>
    </div>
  </div>
</div>

<!-- JS para mostrar modal si hay mensaje -->
<script>
window.onload = function() {
  var mensaje = "<%= request.getAttribute("mensaje") != null ? "true" : "false" %>";
  if (mensaje === "true") {
    var modal = new bootstrap.Modal(document.getElementById('exitoModal'));
    modal.show();
  }
}
</script>

</body>
</html>