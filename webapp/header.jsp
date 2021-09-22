<%@page import="restaurant.model.AccountType"%>
<%@page import="restaurant.model.Account"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
	<html>
	<head>
		<meta charset="utf-8">
		<title>header</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
		 <link href="<c:url value="/css/headers.css" />" rel="stylesheet">
		 <link href="<c:url value="/css/style.css" />" rel="stylesheet">
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
		    </style>
	</head>
	<body>
		 <div class="container">
		    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
		      <a href="index.jsp" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
		        <span class="logo">~Appetito~</span>
		      </a>
		
		      <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
		        <li><a href="#" class="nav-link px-2 link-secondary">Home</a></li>
		        <li><a href="#" class="nav-link px-2 link-dark">Encomenda</a></li>
		        <li><a href="#" class="nav-link px-2 link-dark">Reserva</a></li>
		        <li><a href="#" class="nav-link px-2 link-dark">Pratos</a></li>
		        <li><a href="#" class="nav-link px-2 link-dark">Sobre</a></li>
		      </ul>
			<%
				Account account = (Account) request.getSession().getAttribute("account");
				
				if (account == null) {
			%>
				<div class="col-md-3 text-end">
			        <a href="login.jsp" class="btn btn-outline-danger me-2">Entrar</a>
			        <a href="signin.jsp" class="btn btn-danger">Cadastrar</a>
			    </div>
			<%
				}
				else {
			%>
				<div class="col-md-3 text-end">
			        <a href="#" class="btn btn-outline-danger me-2"><%= account.getUsername() %></a>
			        <a href="mvc?logic=LogoutLogic" class="btn btn-danger">Sair</a>
			    </div>
			<%
				}
			%>		
		      
		    </header>
  		</div>
  		<script src="<c:url value="/js/bootstrap.bundle.min.js"/>"></script>
	</body>
</html>