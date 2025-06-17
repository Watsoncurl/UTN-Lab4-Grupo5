<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Seguro" %>
<%@ page import="NegocioImpl.SeguroNegocioImpl" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listar Seguros</title>

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
      <a class="nav-link" href="Inicio.jsp">Inicio</a>
      <a class="nav-link" href="AgregarSeguro.jsp">Agregar Seguro</a>
      <a class="nav-link active" href="ListarSeguros">Listar Seguros</a>
    </div>
  </div>
</nav>

<!-- CONTENIDO -->
<div class="container mt-5">
    <h2 class="mb-4">Listado de Seguros</h2>    
 	<%
 	ArrayList<Seguro> listaSeguros = null;
 	if(request.getAttribute("seguros")!=null)
 	{
 		listaSeguros = (ArrayList<Seguro>)request.getAttribute("seguros");
 		
 	}
 	%>
 	<table style="width:100%; border-collapse: collapse;">
 	<thead>
            <tr>
                <th style="border: 1px solid black; padding: 8px; text-align: left;">ID</th>
                <th style="border: 1px solid black; padding: 8px; text-align: left;">DESCRIPCION</th>
                <th style="border: 1px solid black; padding: 8px; text-align: left;">TIPO SEGURO</th>
                <th style="border: 1px solid black; padding: 8px; text-align: left;">COSTO CONTRATACION</th>
                <th style="border: 1px solid black; padding: 8px; text-align: left;">COSTO ASEGURADO</th>
            </tr>
        </thead>
        <tbody>
            <%  if(listaSeguros!=null)
                for(Seguro user : listaSeguros) {
            %>
            <tr>  
                <td style="border: 1px solid black; padding: 8px;"><%=user.getIdSeguro()%> </td>   
                <td style="border: 1px solid black; padding: 8px;"><%=user.getDescripcion()%></td>   
                <td style="border: 1px solid black; padding: 8px;"><%=user.getIdTipoSeguro()%></td> 
                <td style="border: 1px solid black; padding: 8px;"><%=user.getCostoContratacion()%></td>
                <td style="border: 1px solid black; padding: 8px;"><%=user.getCostoAsegurado()%></td> 
            </tr>
            <%  } %>
        </tbody>
    </table>
</div>

</body>
</html>
