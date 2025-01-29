<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página inicial</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/images/logoEncurtador.png">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center text-dark">Página Inicial</h1>
        <div class="d-flex justify-content-center mt-4">
            <div class="border p-4 shadow rounded" style="width: 350px; background-color: #f8f9fa;">
                <h3 class="text-center mb-4">Deseja logar no sistema?</h3>
                
                <a href="/encurtado.com/login.jsp" class="btn btn-success btn-lg d-block mb-4 text-center">Log In</a>
                
                <div class="text-center mb-2">
                    <span class="text-dark fw-bold">Ainda não tem uma conta?</span>
                </div>
                
                <a href="/cadastro.jsp" class="btn btn-primary btn-lg d-block text-center">Cadastrar</a>
            </div>
        </div>
    </div>
</body>
</html>
