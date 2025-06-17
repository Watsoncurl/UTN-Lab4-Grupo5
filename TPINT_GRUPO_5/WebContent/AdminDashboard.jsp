<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dashboard</title> 
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
<!--  <link rel="stylesheet" href="Estilo.css"> -->
</head>
<body>
<!--  
	<jsp:include page="AdminMenu.html" />
  -->
	
	<my:navbar 
	    brandName="Banco" 
	    logoPath="bank.png" 
	  />
	
	<div class="container mt-4">
    	<div class="row g-3">
     		<div class="col-md-2">
      	 		 <a href="#" class="btn btn-outline-primary w-100">
      	 		 <i class="bi bi-person-fill me-1"></i>
      	 		 Clientes</a>
     	 	</div>
     	 	<div class="col-md-2">
     	   		<a href="#" class="btn btn-outline-success w-100">
     	   		<i class="bi bi-wallet-fill me-1"></i>
     	   		Cuentas</a>
     	 	</div>
    	  	<div class="col-md-2">
      	  		<a href="#" class="btn btn-outline-warning w-100">
      	  		<i class="bi bi-cash-coin me-1"></i>
      	  		Préstamos</a>
      		</div>
     	 	<div class="col-md-2">
      	  		<a href="#" class="btn btn-outline-info w-100">
      	  		<i class="bi bi-arrow-left-right me-1"></i>
      	  		Movimientos</a>
      		</div>
      		<div class="col-md-2">
       	 		<a href="#" class="btn btn-outline-danger w-100">
       	 		<i class="bi bi-bar-chart-fill me-1"></i>
       	 		Informes</a>
    	  	</div>
   		 </div>
  	</div>
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	
	

</body>
</html>