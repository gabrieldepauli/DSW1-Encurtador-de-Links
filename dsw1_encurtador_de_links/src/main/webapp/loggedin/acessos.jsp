<%@ page import="java.util.List" %>
<%@ page import="br.edu.ifsp.encurtador.model.entity.Acesso" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta charset="UTF-8">
    <title>Acessos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    
    <link rel="icon" type="image/png" href="<%= request.getContextPath() %>/images/logoEncurtador.png">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Acessos</h1>
        <hr>

        <%
            List<Acesso> acessos = (List<Acesso>) request.getAttribute("lista_acessos");
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
                    <th class="text-center">IP</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Acesso acesso : acessos) {
                %>
                    <tr>
                        <td class="text-center"><%= acesso.getIpCliente()%></td>
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