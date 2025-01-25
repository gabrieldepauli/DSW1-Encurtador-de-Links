<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<br><br><br>
	<h1 style="text-align: center;">Cadastro</h1>
    
     <%
   	 	String message = (String) request.getAttribute("message");
    	if (message != null) {
    %>
    <div class="d-flex justify-content-center mt-3">
        <div class="alert alert-secondary alert-dismissible fade show text-center" role="alert">
            <%= message %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    </div>
    <%
    }
    %>
        
    <div class="d-flex justify-content-center mt-4">
        <div class="border p-4" style="width: 300px; border-radius: 8px;">
            <form method="post" action="front.do?action=cadastro" style="text-align: center;">
            	<div style="margin-bottom: 15px;">
                    <label for="nome" style="display: block; font-size: 16px; font-weight: bold;">Nome:</label>
                    <input type="text" id="nome" name="nome" placeholder="Digite o nome." style="padding: 10px; font-size: 14px; width: 100%; text-align: center;" required="required">
                </div>
            
                <div style="margin-bottom: 15px;">
                    <label for="email" style="display: block; font-size: 16px; font-weight: bold;">Email:</label>
                    <input type="email" id="email" name="email" placeholder="Digite o email." style="padding: 10px; font-size: 14px; width: 100%; text-align: center;" required="required">
                </div>

                <div style="margin-bottom: 15px;">
                    <label for="senha" style="display: block; font-size: 16px; font-weight: bold;">Senha:</label>
                    <input type="password" id="senha" name="senha" placeholder="Digite a senha." style="padding: 10px; font-size: 14px; width: 100%; text-align: center;" required="required">
                </div>

                <div>
                    <button type="submit" style="padding: 10px 20px; font-size: 15px; font-weight: bold; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer; display: block; margin: 20px auto;">Logar</button>
                </div>
            </form>
        </div>
    </div>
    
	<div class="text-center mt-4">
        <a href="index.jsp" style="text-decoration: none;">
            <button class="btn btn-danger" style="font-weight: bold;">Voltar</button>
        </a>
    </div>
	
	<div class="text-center mt-4">
    	<p>Já possui cadastro? <a href="front.do?action=loginForm">Login</a></p>
    </div>
	
</body>
</html>