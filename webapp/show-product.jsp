<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="admin-header.jsp"></jsp:include>
	<div class="container" style="margin-top: 2em">
      <div class="card">
      	<img class="bd-placeholder-img card-img-top" width=100%; height=380"
      	 alt="<%= request.getParameter("name") %>" src="GetImageServlet?id=<%= request.getParameter("id") %>">
		
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
        <a href="#" class="btn btn-primary">Editar</a>
        <a href="#" class="btn btn-danger">Remover</a>
      </div>
    </div>
</body>
</html>