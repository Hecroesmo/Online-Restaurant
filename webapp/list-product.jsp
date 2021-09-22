<%@page import="restaurant.model.Product"%>
<%@page import="restaurant.dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Listagem de productos</title>
		<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
		<style>
		  .bd-placeholder-img {
	        font-size: 1.125rem;
	        text-anchor: middle;
	        -webkit-user-select: none;
	        -moz-user-select: none;
	        user-select: none;
	      }
	      
	      a {
	      	text-decoration: none!important;
	      	color: #6c757d!important;
	      }
	
	      @media (min-width: 768px) {
	        .bd-placeholder-img-lg {
	          font-size: 3.5rem;
	        }
	      }
		</style>
	</head>
	<body>
	<%
		Connection connection = ( Connection ) request.getAttribute("connection");
		List<Product> products = new ProductDao(connection).getProducts();		
	%>
		<jsp:include page="admin-header.jsp"></jsp:include>		
		
		<div class="container" style="margin-top: 2rem">
			<div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
	<%
			for (Product product : products) {
	%>
		        <div class="col">
		        <a href="">
		          <div class="card shadow-sm">
					<img alt="<%= product.getName() %>" src="GetImageServlet?id=<%= product.getPkProduct() %>"
					 class="bd-placeholder-img card-img-top" width="100%" height="225">
		            <div class="card-body">
		              <p class="card-text"><%= product.getDescription() %>.</p>
		              <div class="d-flex justify-content-between align-items-center">
		                <div>
		                  <p class="card-text"><%= product.getName() %></p>
		                </div>
		                <small class="text-muted"><%= product.getPrice() %></small>
		              </div>
		            </div>
		          </div>
		        </a>
		        </div>
	<%
			}
	%>
	        </div>
        </div>
	</body>
</html>