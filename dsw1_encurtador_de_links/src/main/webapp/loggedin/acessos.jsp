<%@page import="org.apache.commons.collections4.CollectionUtils"%>
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
        <%
            List<Acesso> accesses = (List<Acesso>) request.getAttribute("accesses");
        %>
    
    	<%@ include file="/includes/messages.jsp" %>
    
        <h1 class="text-center">Acessos (<%= accesses != null ? accesses.size() : '0' %>) </h1>
        <hr>

        <table class="table table-bordered mt-4">
            <thead>
            	<tr>
                    <th class="text-center">ID acesso</th>
                    <th class="text-center">IP</th>
                    <th class="text-center">Data do acesso</th>
                </tr>
            </thead>
            <tbody>
                <%
                	if (CollectionUtils.isNotEmpty(accesses)) {          		
	                    for (Acesso acesso : accesses) {
                %>
                
                			<tr>
		                        <td class="text-center"><%= acesso.getId() %></td>
		                        <td class="text-center"><%= acesso.getIpCliente() %></td>
		                        <td class="text-center"><%= acesso.getDataHoraAcesso() %></td>
		                    </tr>
                <% 
	                    }
                	} 
                %>
            </tbody>
        </table>

        <div class="text-center mt-4">
            <a href="/encurtado.com/front.do?command=GetLinksCommand" class="btn btn-danger">Voltar</a>
        </div>
    </div>
</body>
</html>