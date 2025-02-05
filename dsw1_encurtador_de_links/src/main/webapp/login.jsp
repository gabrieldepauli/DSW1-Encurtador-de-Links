<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/images/logoEncurtador.png">
</head>
<body>
    <div class="container mt-5">
    
    	<%@ include file="/includes/messages.jsp" %>
    
        <h1 class="text-center text-dark">Login</h1>
        
        <div class="d-flex justify-content-center mt-4">
            <div class="border p-4 shadow rounded" style="width: 350px; background-color: #f8f9fa;">
            
                <form method="post" action="/encurtado.com/front.do" method="POST">
                	<input type="hidden" name="command" value="LoginCommand">
                
                    <div class="mb-3">
                        <label for="email" class="form-label fw-bold">Email:</label>
                        <input type="email" id="email" name="email" class="form-control text-center" placeholder="Digite o email" required>
                    </div>
                    
                    <div class="mb-3">
                        <label for="senha" class="form-label fw-bold">Senha:</label>
                        <input type="password" id="senha" name="senha" class="form-control text-center" placeholder="Digite a senha" required>
                    </div>
                    
                    <div class="d-grid">
                        <button type="submit" class="btn btn-success btn-lg fw-bold">Logar</button>
                    </div>
                </form>
                
            </div>
        </div>
        
        <div class="text-center mt-4">
            <a href="/encurtado.com/front.do?command=PageIndexCommand" class="btn btn-danger fw-bold">Voltar</a>
        </div>
        
        <div class="text-center mt-4">
            <p class="text-dark fw-bold">Não possui cadastro? <a href="/encurtado.com/front.do?command=PageRegisterCommand" class="text-primary fw-bold">Cadastre-se</a></p>
        </div>
    </div>
</body>
</html>
