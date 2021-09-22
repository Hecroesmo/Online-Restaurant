<%@page import="restaurant.dao.PersonDao"%>
<%@page import="restaurant.dao.ProvinceDao"%>
<%@page import="restaurant.model.Region"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>Cadastra-se</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<c:url value="/css/form-validation.css" />" rel="stylesheet">
		<link href="<c:url value="/css/style.css" />" rel="stylesheet">
		<script src="<c:url value="/js/jquery.js"/>"></script>
	</head>
	<body>
		<jsp:useBean id="customer" class="restaurant.model.Person" scope="page">
			<jsp:setProperty property="*" name="customer"/>
		</jsp:useBean>
	
		<div class="py-5 text-center">
		     <h1 style="font-size: 4em;" class="d-block mx-auto mb-4 logo">~Appetito~</h1>
		     <h2>Inscreve-se</h2>
		     <p class="lead">Inscreve-se para poder fazer encomendas, reservas e <strong>#BuonAppetito!!</strong></p>
	    </div>
		<div class="container">
		<%
			Connection connection = (Connection) request.getAttribute("connection");
			List<Region> provinces = new ProvinceDao(connection).getAllProvinces();
		
			if (customer.getPhoneNumber() == null)
			{
		%>
			<form action="signin.jsp" method="post" class="needs-validation" novalidate>
				<div style="padding-bottom: 1em;" class="row g-3">
					 <div class="col">
			              <label for="fName" class="form-label">Nome</label>
			              <input type="text" class="form-control" id="fName" name="firstName" required>
			              <div class="invalid-feedback">
			                Valid first name is required.
			              </div>
		             </div>
		             <div class="col">
			              <label for="lName" class="form-label">Sobrenome</label>
			              <input type="text" class="form-control" id="lName" name="lastName" required>
			              <div class="invalid-feedback">
			                Valid last name is required.
			              </div>
		             </div>
				</div>
				<div class="row g-3">
					<div class="col">
			              <label for="bDate" class="form-label">Data Nascimento</label>
			              <input type="date" class="form-control" id="bDate" name="birthDate" required>
			              <div class="invalid-feedback">
			                Birth date is required.
			              </div>
		            </div>
				</div>
				<div style="padding-bottom: 1em;" class="row g-3">
					 <div class="col">
			              <label for="tel" class="form-label">Tel</label>
			              <input type="tel" class="form-control" id="tel" name="phoneNumber" required>
			              <div class="invalid-feedback">
			                Valid phone number name is required.
			              </div>
		             </div>
		             <div class="col">
			              <label for="email" class="form-label">Email</label>
			              <input type="email" class="form-control" id="email" name="email" placeholder="voce@example.com">
			              <div class="invalid-feedback">
			                Please enter a valid email address for shipping updates.
			              </div>
		             </div>
				</div>
				<div style="padding-bottom: 1em;" class="row g-3">
					<div class="col">
		              	<label for="province" class="form-label">Province</label>
		              	<select class="form-select" id="province" name="province" required>
		              		<option value="">Selecione...</option>
		<%
		              	for (Region province : provinces) {
		%>
			                <option value="<%= province.getPkRegion() %>"><%= province.getName() %></option>
		<%
		              	}
		%>
		              	</select>
		              	<div class="invalid-feedback">
		                	Please select a valid province.
		            	</div>
          			</div>
          			<div class="col">
		              	<label for="municipality" class="form-label">Municipio</label>
		              	<select class="form-select" id="municipality" name="municipality" required>
			                <option value="">Selecione...</option>
		              	</select>
		              	<div class="invalid-feedback">
		                	Please select a valid municipality.
		            	</div>
          			</div>
          			<div class="col">
		              	<label for="commune" class="form-label">Comuna/Districto</label>
		              	<select class="form-select" id="commune" name="commune" required>
			                <option value="">Selecione...</option>
		              	</select>
		              	<div class="invalid-feedback">
		                	Please select a valid commune.
		            	</div>
          			</div>
			   </div>
			   <div style="padding-bottom: 1em;" class="row g-3">
			   		<div class="col">
		              	<label for="neighborhood" class="form-label">Bairro</label>
		              	<select class="form-select" id="neighborhood" name="neighborhood" required>
			                <option value="">Selecione...</option>
		              	</select>
		              	<div class="invalid-feedback">
		                	Please select a valid neighborhood.
		            	</div>
          			</div>
			   </div>
			   <div style="padding-bottom: 1em;" class="row g-3">
			   		<div class="col">
		              	<label for="road" class="form-label">Rua</label>
		              	<input type="text" class="form-control" id="road" name="road">
		              	<div class="invalid-feedback">
		                	Please select a valid road.
		            	</div>
          			</div>
			   </div>
			   <button class="w-100 btn btn-danger btn-lg" type="submit">Cadastrar-se</button>
			</form>
		<%
			}
			else
			{
				new PersonDao(connection).save(customer);
				request.setAttribute("phoneNumber", customer.getPhoneNumber());
		%>
				<jsp:forward page="register-account.jsp" />
		<%
			}
		%>
			  <footer class="my-5 pt-5 text-muted text-center text-small">
				    <p class="mb-1">&copy; 2017–2021 Appetito</p>
				    <ul class="list-inline">
					      <li class="list-inline-item"><a href="#">Privacidade</a></li>
					      <li class="list-inline-item"><a href="#">Termos</a></li>
					      <li class="list-inline-item"><a href="#">Suporte</a></li>
				    </ul>
			  </footer>
		</div>
		<script src="<c:url value="/js/bootstrap.bundle.min.js"/>"></script>
		<script src="<c:url value="/js/region-ajax.js"/>"></script>
		<script src="<c:url value="/js/form-validation.js"/>"></script>
	</body>
</html>