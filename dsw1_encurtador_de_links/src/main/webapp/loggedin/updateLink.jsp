<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar Link</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/images/logoEncurtador.png">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Modificar Link</h1>

        <%
            int idLink = (Integer) request.getAttribute("id");
        %>

        <div class="d-flex justify-content-center mt-4">
            <div class="card p-4" style="width: 100%; max-width: 400px; border-radius: 8px;">
                <form method="post" action="logged.do?action=updateLink&id=<%=idLink%>">
                    <div class="mb-3">
                        <label for="link" class="form-label">Link original:</label>
                        <input type="url" id="link" name="link" class="form-control" placeholder="Informe o novo link" required="required">
                    </div>

                    <div class="mb-3">
                        <label for="personalizacao" class="form-label">Identificador (opcional):</label>
                        <input type="text" id="personalizacao" name="personalizacao" class="form-control" placeholder="Informe a nova chave de personalização">
                    </div>

                    <div class="d-flex justify-content-center">
                        <button type="submit" class="btn btn-success w-100">Modificar</button>
                    </div>
                </form>
            </div>
        </div>

        <%
            String message = (String) request.getAttribute("message");
            Boolean sucess = (Boolean) request.getAttribute("sucess");

            if (message != null) {
        %>
        <div class="alert <%= sucess ? "alert-primary" : "alert-danger" %> alert-dismissible fade show mt-4 text-center" role="alert">
            <%= sucess ? "Link modificado com sucesso: " : message %>
            <% if (sucess) { %>
                <a href="front.do?action=redirecionarLink&url_encurtada=<%= message %>">
                    <%= message %>
                </a>
            <% } %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% } %>

        <div class="text-center mt-4">
            <a href="logged.do?action=listLinks" class="btn btn-danger">Voltar</a>
        </div>
    </div>
</body>
</html>