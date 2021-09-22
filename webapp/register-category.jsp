<%@page import="restaurant.model.Category"%>
<%@page import="restaurant.dao.CategoryDao"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt">
	<head>
		<meta charset="utf-8">
		<title>Inserir nova categoria</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
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
			.category__title {
				margin: 1rem 0;
			}
			
			.container {
				width: 80% !important;
			}
		</style>
		<script src="<c:url value="/js/jquery.js"/>"></script>
	</head>
	<body>
		<%
			Connection connection = (Connection) request.getAttribute("connection");
			List<Category> categories = new CategoryDao(connection).getFirstLevelCategories();
		%>
		
		<jsp:include page="admin-header.jsp"></jsp:include>
		<div class="container">
			<h2 class="category__title">Cadastrar Categoria</h2>
	        <form action="mvc?logic=SaveCategoryLogic" method="post">
	        	<div class="row">
	        		<div class="col">
	        			<label for="fcategory" class="form-label">Primeira Categoria</label>
	        			<select class="form-select" id="fcategory" name="fcategory">
			        		<option>Selecione...</option>
		<%
			        	for (Category category : categories) {
		%>
			        		<option value="<%= category.getPkCategory() %>"><%= category.getName() %></option>
		<%
			        	}
		%>
			        	</select>
	        		</div>
	        		<div class="col">
	        			<label for="scategory" class="form-label">Segunda Categoria</label>
	        			<select class="form-select" id="scategory" name="scategory">
			        		<option>Selecione...</option>
			        	</select>
	        		</div>
	        		<div class="col">
	        			<label for="tcategory" class="form-label">Terceira Categoria</label>
	        			<select class="form-select" id="tcategory" name="tcategory">
			        		<option>Selecione...</option>
			        	</select>
	        		</div>
	        	</div>
	        	<div class="row">
	        		<div class="mb-3">
		            	<label for="name" class="form-label">Nome</label>
		            	<input type="text" class="form-control" id="name" name="name">
		        	</div>
	        	</div>
	        	<input type="submit" class="btn btn-primary" value="Salvar">
	        </form>
		</div>
		<script src=" <c:url value="js/category-ajax.js"/> "></script>
	</body>
</html>