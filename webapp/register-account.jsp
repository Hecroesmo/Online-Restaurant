<%@page import="restaurant.model.Person"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta charset="utf-8">
		<title>Entrar</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
		 <link href="<c:url value="/css/signin.css" />" rel="stylesheet">
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
	<body  class="text-center">
		<% String phoneNumber = (String) request.getAttribute("phoneNumber"); %>
		
		<main class="form-signin">
			<form action="mvc?logic=ValidateAccountLogic&phoneNumber=${phoneNumber}" method="post">
	    		<h1 style="font-size: 3em;
	    		  font-weight: bold;
	    		  padding-bottom: 1em"
	    		 class="h3 mb-3 fw-normal">~Appetito~</h1>
	    		<div class="form-floating">
					<input type="text" name="username" id="user" class="form-control" required>
					<label for="user">Nome de usuario</label>
	    		</div>
	    		<div class="form-floating">
					<input type="password" name="password" id="passwd" class="form-control" required>
					<label for="passwd">Palavra passe</label>
	    		</div>
	    		<div class="form-floating">
					<input type="password" name="re-password" id="re-passwd" class="form-control" required>
					<label for="re-passwd">Reintroduz Palavra passe</label>
	    		</div>				
				<input class="w-100 btn btn-lg btn-danger" type="submit" value="Entrar">
				<span style="color: red;">${error}</span>
			</form>
		</main>
	</body>
</html>