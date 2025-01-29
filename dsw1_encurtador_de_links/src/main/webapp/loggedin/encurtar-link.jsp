<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Encurtar Link</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/images/logoEncurtador.png">
</head>
<body class="bg-light">

    <div class="container mt-5">
        <h1 class="text-center mb-4">Encurtar Link</h1>

        <div class="d-flex justify-content-center">
            <div class="card shadow-sm" style="width: 100%; max-width: 400px; border-radius: 12px;">
                <div class="card-body">
                    <form action="/encurtado.com/front.do" method="POST">
                    	<input type="hidden" name="command" value="LinkCommand">
                		<input type="hidden" name="action" value="create">
                    
                        <div class="mb-3">
                            <label for="link" class="form-label">Link original</label>
                            <input type="url" id="link" name="link" class="form-control" placeholder="Informe o link" required>
                        </div>

                        <button type="submit" class="btn btn-success w-100">Encurtar</button>
                    </form>
                </div>
            </div>
        </div>
        
        <%@ include file="/includes/messages.jsp" %>

        <div class="text-center mt-4">
            <a href="/encurtado.com/loggedin/logged.jsp" class="btn btn-danger">Voltar</a>
        </div>
    </div>

</body>
</html>
