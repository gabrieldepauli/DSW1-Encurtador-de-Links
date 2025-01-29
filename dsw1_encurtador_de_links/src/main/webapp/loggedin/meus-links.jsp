<%@ page import="java.util.List" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.Link" %>
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
            List<Link> links = (List<Link>) request.getAttribute("links");
            String message = (String) request.getAttribute("message");
            String messageType = (String) request.getAttribute("messageType");
        %>

        <% if (message != null) { 
        		String alertClass = "success".equals(messageType) ? "alert-success" : "alert-danger";
        %>
            <div class="d-flex justify-content-center mt-3">
            <div class="alert <%= alertClass %> alert-dismissible fade show text-center" role="alert">
                <%= message %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </div>
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
                        <td class="text-center"><%= link.getUrlOriginal() %></td>
                        <td class="text-center"><%= link.getUrlEncurtada() %></td>
                        <td class="text-center"><a href="logged.do?action=verDados&id=<%= link.getId() %>" class="btn btn-info">Ver Acessos</a></td>
                        <td class="text-center"><a href="logged.do?action=pageUpdate&id=<%= link.getId() %>" class="btn btn-warning">Modificar</a></td>
                        <td class="text-center"><a href="logged.do?action=delete&id=<%= link.getId() %>" class="btn btn-danger">Deletar</a></td>
                    </tr>
                <% } %>
            </tbody>
        </table>

        <div class="text-center mt-4">
            <a href="/encurtado.com/loggedin/logged.jsp" class="btn btn-danger">Voltar</a>
        </div>
    </div>
</body>
</html>
