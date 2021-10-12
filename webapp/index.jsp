<%@page import="restaurant.dao.ProductDao"%>
<%@page import="java.sql.Connection"%>
<%@page import="restaurant.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="restaurant.model.AccountType"%>
<%@page import="restaurant.model.Account"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta charset="utf-8">
		<title>Appetito | Bem vindo</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="<c:url value="/css/reset.css" />" rel="stylesheet">
		<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<c:url value="/css/carousel.css" />" rel="stylesheet">
		<style>
	      .bd-placeholder-img {
	        font-size: 1.125rem;
	        text-anchor: middle;
	        -webkit-user-select: none;
	        -moz-user-select: none;
	        user-select: none;
	      }
	
	      @media (min-width: 768px) {
	        .bd-placeholder-img-lg {
	          font-size: 3.5rem;
	        }
	      }
	      
	      body {
	      	padding-top: 0;
	      }
	    </style>
	</head>
	<body>
	<%
		Account account = (Account) request.getSession().getAttribute("account");
		AccountType type = null;
		Connection connection = (Connection) request.getAttribute("connection");
		List<Product> fastFoodProducts = new ProductDao(connection).getProductByCategoryName("Fast Food"); 
		
		if (account == null) {
			type = new AccountType("annonymous");
		}
		else {
			type = account.getAccountType();
		}
		
		
		if (type.getDescription().equals("admin") || type.getDescription().equals("root"))
		{
	%>
			<jsp:include page="admin-header.jsp"></jsp:include>
			<div class="b-example-divider"></div>
	<% 
		}
	%>
		<jsp:include page="header.jsp"></jsp:include>
		<div id="myCarousel" class="container carousel slide" data-bs-ride="carousel">
		
	    <%-- <div class="carousel-indicators">
	      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
	      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
	      <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
	    </div>	--%>
	    
	    <div class="carousel-inner">
	<%
		for (Product fastFood : fastFoodProducts) {
	%>
			<div class="carousel-item">
	        <img alt="<%= fastFood.getName() %>" src="GetImageServlet?id=<%= fastFood.getPk_product() %>" />">
	
	        <div class="container">
	          <div class="carousel-caption text-start">
	            <h1><%= fastFood.getName() %>.</h1>
	            <p><%= fastFood.getDescription() %>.</p>
	            <p><a class="btn btn-lg btn-danger" href="#">Pedir Agora</a></p>
	          </div>
	        </div>
	      </div>
	<%
		}
	%>
	    </div>	
	    <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
	      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
	      <span class="visually-hidden">Anterior</span>
	    </button>
	    <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
	      <span class="carousel-control-next-icon" aria-hidden="true"></span>
	      <span class="visually-hidden">Próximo</span>
	    </button>
	  </div>
	  <script>
	  	var products = document.getElementsByClassName('carousel-item')
	  	products[0].classList.add('active')
	  </script>
	  <script src="<c:url value="/js/bootstrap.bundle.min.js"/>"></script>
	</body>
</html>