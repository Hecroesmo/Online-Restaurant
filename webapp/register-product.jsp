<%@page import="restaurant.dao.CategoryDao"%>
<%@page import="restaurant.model.Category"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.List"%>

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
	      
	      .container {
	      	width: 80% !important;
	      }
	    </style>
	</head>
	<body>
	<%
			Connection connection = (Connection) request.getAttribute("connection");
			List<Category> categories = new CategoryDao(connection).getFirstLevelCategories();
	%>
		<jsp:include page="admin-header.jsp"></jsp:include>
		
		<div  class="container">
			<h2 style="margin: 1em 0;">Cadastrar Prato</h2>
			<form action="SaveProductServlet" method="post" enctype="multipart/form-data"
			 class="needs-validation" novalidate>
				
				<div style="padding-bottom: 1em;" class="row g-3">
					 <div class="col">
			              <label for="pName" class="form-label">Nome</label>
			              <input type="text" class="form-control" id="pName" name="name" required>
		             </div>
		             <div class="col">
		             	<label for="quantity" class="form-label">Quantidade</label>
			            <div class="input-group mb-3">
				        	<span class="input-group-text" id="quantity">Quantidade/Unidade</span>
				        	<input type="text" class="form-control" name="quantity">
				     	</div>
		             </div>
				</div>
				<div class="row g-3">
					<div class="col">
			              <div class="input-group">
					          <span class="input-group-text">Descri????o</span>
					          <textarea class="form-control" name="description" id="description"></textarea>
					      </div>
					</div>
		            <div class="col">
		              	<div class="input-group mb-3">
				        	<span class="input-group-text">kz</span>
				        	<input type="text" class="form-control" name="price" placeholder="Pre??o">
				          	<span class="input-group-text">.00</span>
				      	</div>
		            </div>
				</div>
				<div style="padding-bottom: 1em;" class="row g-3">
		             <div class="col">
			              <label for="fcategory" class="form-label">Primeira Categoria</label>
			              <select class="form-select" id="fcategory" name="fcategory" required>
			              		<option value="">Selecione...</option>
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
			              		<option value="">Selecione...</option>
			              </select>
		             </div>
		             <div class="col">
			              <label for="tcategory" class="form-label">Terceira Categoria</label>
			              <select class="form-select" id="tcategory" name="tcategory">
			              		<option value="">Selecione...</option>
			              </select>
		             </div>
				</div>
				
			   <div style="padding-bottom: 1em;" class="row g-3">
			   		<div class="col">
		              	<div class="mb-3">
				        	<input type="file" name="image" class="form-control form-control-lg">
				        </div>
          			</div>
			   </div>
			   <button type="submit" class="btn btn-primary text-center">Salvar</button>
			</form>
		</div>
		<script src="<c:url value="/js/bootstrap.bundle.min.js"/>"></script>
		<script src=" <c:url value="js/category-ajax.js"/> "></script>
		<script src="<c:url value="/js/form-validation.js"/>"></script>
	</body>
</html>