<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Link Personalizado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/images/logoEncurtador.png">
</head>
<body class="bg-light">

    <div class="container mt-5">
        <h1 class="text-center mb-4">Personalizar Link</h1>

        <div class="d-flex justify-content-center">
            <div class="card shadow-sm" style="width: 100%; max-width: 400px; border-radius: 12px;">
                <div class="card-body">
                    <form method="post" action="logged.do?action=personalizarLink">
                        <div class="mb-3">
                            <label for="link" class="form-label">Link original</label>
                            <input type="url" id="link" name="link" class="form-control" placeholder="Informe o link" required>
                        </div>

                        <div class="mb-3">
                            <label for="personalizacao" class="form-label">Identificador</label>
                            <input type="text" id="personalizacao" name="personalizacao" class="form-control" placeholder="Informe a chave de personalização" required>
                        </div>

                        <button type="submit" class="btn btn-success w-100">Encurtar</button>
                    </form>
                </div>
            </div>
        </div>

        <%
            String message = (String) request.getAttribute("message");
            Boolean success = (Boolean) request.getAttribute("sucess");

            if (message != null) {
        %>
            <div class="d-flex justify-content-center mt-3">
                <div class="alert <%= success ? "alert-success" : "alert-danger" %> alert-dismissible fade show w-75 text-center" role="alert">
                    <%= success ? "Link encurtado com sucesso: <a href='front.do?action=redirecionarLink&url_encurtada=" + message + "'>" + message + "</a>" : message %>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </div>
        <% } %>

        <div class="text-center mt-4">
            <a href="logged.do?action=userPage" class="btn btn-danger">Voltar</a>
        </div>
    </div>

</body>
</html>
