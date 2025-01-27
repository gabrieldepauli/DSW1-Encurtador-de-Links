<%@ page import="java.util.List" %>
<%@ page import="model.entity.Link" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Meus Links</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/images/logoEncurtador.png">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Meus Links</h1>
        <hr>

        <%
            List<Link> links = (List<Link>) request.getAttribute("lista_links");
            String message = (String) request.getAttribute("message");
        %>

        <% if (message != null) { %>
            <div class="alert alert-danger text-center" role="alert"><%= message %></div>
        <% } %>

        <table class="table table-bordered mt-4">
            <thead>
                <tr>
                    <th class="text-center">ID</th>
                    <th class="text-center">URL Original</th>
                    <th class="text-center">URL Encurtada</th>
                    <th class="text-center">Ver Dados</th>
                    <th class="text-center">Modificar</th>
                    <th class="text-center">Deletar</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Link link : links) {
                %>
                    <tr>
                        <td class="text-center"><%= link.getId() %></td>
                        <td class="text-center"><%= link.getUrl_original() %></td>
                        <td class="text-center"><%= link.getUrl_encurtada() %></td>
                        <td class="text-center"><a href="logged.do?action=verDados&id=<%= link.getId() %>" class="btn btn-info">Ver Dados</a></td>
                        <td class="text-center"><a href="logged.do?action=pageUpdate&id=<%= link.getId() %>" class="btn btn-warning">Modificar</a></td>
                        <td class="text-center"><a href="logged.do?action=delete&id=<%= link.getId() %>" class="btn btn-danger">Deletar</a></td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <div class="text-center mt-4">
            <a href="logged.do?action=userPage" class="btn btn-danger">Voltar</a>
        </div>
    </div>
</body>
</html>
