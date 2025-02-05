<%@page import="org.apache.commons.collections4.CollectionUtils"%>
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
    
    	<%@ include file="/includes/messages.jsp" %>
    
        <h1 class="text-center">Meus Links</h1>
        
        <hr>

        <%
            List<Link> links = (List<Link>) request.getAttribute("links");
        %>

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
                	if (CollectionUtils.isNotEmpty(links)) {
                    	for (Link link : links) {
                %>
		                    <tr>
		                        <td class="text-center"><%= link.getId() %></td>
		                        <td class="text-center"><%= link.getUrlOriginal() %></td>
		                        <td class="text-center">localhost:8080/encurtado.com/<%= link.getUrlEncurtada() %></td>
		                        <td class="text-center"><a href="#" class="btn btn-info">Ver Acessos</a></td>
		                        <td class="text-center"><a href="/encurtado.com/front.do?command=PageEditLinkCommand&id=<%= link.getId() %>"">Modificar</a></td>
		                        <td class="text-center"><a href="/encurtado.com/front.do?command=DeleteLinkCommand&id=<%= link.getId() %>" class="btn btn-danger">Deletar</a></td>
		                    </tr>
                <% 
                		} 
                	}
                	else {
                %>
                			<tr> 
                				<td colspan="6">
                					<h4 class="text-center opacity-25">Você ainda não tem links encurtados</h4>
                				</td>
                			</tr>
                <%	
                	}
                %>
            </tbody>
        </table>

        <div class="text-center mt-4">
            <a href="/encurtado.com/front.do?command=PageHomeCommand" class="btn btn-danger">Voltar</a>
        </div>
    </div>
</body>
</html>
