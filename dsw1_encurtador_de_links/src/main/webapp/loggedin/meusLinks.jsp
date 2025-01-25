<%@page import="java.util.List"%>
<%@page import="model.entity.Link"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Meus Links</title>
</head>
<body>
	<%
		var links = (List<Link>) request.getAttribute("lista_links");
	%>

	<br><br><br>
	<h1 style="text-align: center;">Meus Links</h1>
	<br><br>
	<hr>
	<br><br>
	
	<%
		String message = (String) request.getAttribute("message");
		
		if(message != null){
		%> 
		<h3 style="color: red"><%= message %></h3>
		<%}
	%>
	
	<br><br>
	
	<table border="1">
		<thead>
			<tr>
				<th scope="col">ID</th>
				<th scope="col">URL Original</th>
				<th scope="col">URL Encurtada</th>
				<th scope="col">Action 1</th>
				<th scope="col">Action 2</th>
				<th scope="col">Action 3</th>
			</tr>
		</thead>
		<tbody>
			<%
				int i = 1;
				for(Link link : links){
			%>
				<tr>
				<th scope="row"><%= link.getId() %></th>
				<td><%= link.getUrl_original() %></td>
				<td><%= link.getUrl_encurtada() %></td>
				<td><a href="logged.do?action=verDados&id=<%=link.getId()%>">Ver Dados</a></td>
				<td><a href="logged.do?action=update&id=<%=link.getId()%>">Modficar Link</a></td>
				<td><a href="logged.do?action=delete&id=<%=link.getId()%>">Deletar Link</a></td>
			</tr>
			<%i++;} %>
		</tbody>
	</table>
	
	<br><br>
	<hr>
	<br><br>
	
	<div class="text-center mt-4">
        <a href="logged.do?action=userPage" style="text-decoration: none;">
            <button class="btn btn-danger" style="font-weight: bold;">Voltar</button>
        </a>
    </div>
</body>
</html>