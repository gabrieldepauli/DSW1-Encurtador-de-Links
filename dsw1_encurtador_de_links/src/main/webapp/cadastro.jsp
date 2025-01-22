<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro</title>
</head>
<body>
	<h1>Cadastro</h1>
	<br><br>
	<hr>
	<br><br>
	<%
	String message = (String) request.getAttribute("message");
	if(message != null){
	%>
	<h3 style="color: black"><%=message%></h3>
	<%} %>
	<br>
	
	<form action="front.do?action=cadastro" method="post">
		<label for="nome">Nome</label>
		<input type="text" name="nome" id="nome" required="required"><br><br>
	
		<label for="email">Email</label>
		<input type="email" name="email" id="email" required="required"><br><br>
		
		<label for="senha">Senha</label>
		<input type="password" name="senha" id="senha" required="required"><br><br>
		
		<button type="submit">Enviar</button>
	</form>
	
	<br><br>
	<hr>
	<br><br>
	
	<p>Já possui cadastro? <a href="front.do?action=loginForm">Login</a></p>
</body>
</html>