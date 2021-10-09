<%@page import="restaurant.model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Detalhes do produto</title>
</head>
<body>
	<jsp:include page="admin-header.jsp"></jsp:include>
	<div class="container" style="margin-top: 3em">
      <div class="card">
      	<img class="bd-placeholder-img card-img-top" style="height: 350px; width: auto"
      	 alt="<%= request.getParameter("name") %>"
      	 src="GetImageServlet?id=<%= request.getParameter("id") %>">
		
        <div class="card-body">
          <h5 class="card-title"><%= request.getParameter("name") %></h5>
          <p class="card-text"><%= request.getParameter("description") %></p>
        </div>
        
        <ul class="list-group list-group-flush">
          <li class="list-group-item">Código: <%= request.getParameter("id") %></li>
          <li class="list-group-item">Quantidade: <%= request.getParameter("quantity") %></li>
          <li class="list-group-item">Preço: <%= request.getParameter("price") %></li>
        </ul>
      </div>
      <div style="margin-top: 1em">
        <a href="alter-product.jsp?id=<%= request.getParameter("id") %>&name=<%= request.getParameter("name") %>&description=<%= request.getParameter("description") %>&price=<%= request.getParameter("price") %>&quantity=<%= request.getParameter("quantity") %>"
         class="btn btn-primary">Editar</a>
        
        <a href="mvc?logic=RemoveProductLogic&id=<%= request.getParameter("id") %>"
         class="btn btn-danger">Remover</a>
      </div>
    </div>
</body>
</html>