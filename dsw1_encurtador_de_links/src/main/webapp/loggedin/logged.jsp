<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Página do Usuário</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/images/logoEncurtador.png">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center text-dark">Bem-vindo(a)!</h1>

        <div class="border p-4 shadow-lg rounded mt-4" style="background-color: #f8f9fa; max-width: 500px; margin: 0 auto;">

            <a href="logged.do?action=pageEncurtador" class="btn btn-success btn-lg w-100 mb-4" style="font-weight: bold;">
                Encurtar Link
            </a><br>

            <a href="logged.do?action=pagePersonalizarLink" class="btn btn-primary btn-lg w-100 mb-4" style="font-weight: bold;">
                Personalizar Link
            </a><br>

            <a href="logged.do?action=listLinks" class="btn btn-info btn-lg w-100 mb-4 text-white" style="font-weight: bold;">
                Meus Links
            </a><br>
            
            <a href="logged.do?action=logout" class="btn btn-danger btn-lg w-100 mb-4" style="font-weight: bold;">
                Logout
            </a>
        </div>
    </div>
</body>
</html>
