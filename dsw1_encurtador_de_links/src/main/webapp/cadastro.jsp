<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<link rel="icon" type="image/png" href="<%= request.getContextPath() %>/images/logoEncurtador.png">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center text-dark">Cadastro</h1>

        <%
            String message = (String) request.getAttribute("message");
            String messageType = (String) request.getAttribute("messageType");
            if (message != null) {
                String alertClass = "success".equals(messageType) ? "alert-success" : "alert-danger";
        %>
        <div class="d-flex justify-content-center mt-3">
            <div class="alert <%= alertClass %> alert-dismissible fade show text-center" role="alert">
                <%= message %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </div>
        <%
            }
        %>

        <div class="d-flex justify-content-center mt-4">
            <div class="border p-4 shadow rounded" style="width: 350px; background-color: #f8f9fa;">
                <form method="post" action="front.do?action=cadastro">
                    <div class="mb-3">
                        <label for="nome" class="form-label fw-bold">Nome:</label>
                        <input type="text" id="nome" name="nome" class="form-control text-center" placeholder="Digite o nome." required>
                    </div>

                    <div class="mb-3">
                        <label for="email" class="form-label fw-bold">Email:</label>
                        <input type="email" id="email" name="email" class="form-control text-center" placeholder="Digite o email." required>
                    </div>

                    <div class="mb-3">
                        <label for="senha" class="form-label fw-bold">Senha:</label>
                        <input type="password" id="senha" name="senha" class="form-control text-center" placeholder="Digite a senha." required>
                    </div>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-success btn-lg fw-bold">Cadastrar</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="text-center mt-4">
            <a href="index.jsp" class="btn btn-danger fw-bold">Voltar</a>
        </div>

        <div class="text-center mt-4">
            <p>Já possui cadastro? <a href="front.do?action=loginForm" class="text-primary fw-bold">Login</a></p>
        </div>
    </div>
</body>
</html>
