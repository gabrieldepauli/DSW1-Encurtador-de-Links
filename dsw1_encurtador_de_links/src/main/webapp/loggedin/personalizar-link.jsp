<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="br.edu.ifsp.encurtador.model.entity.Link"%>

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

	<%
		var link = (Link) request.getAttribute("link");
	%>

    <div class="container mt-5">
        <h1 class="text-center mb-4">Personalizar Link</h1>

        <div class="align-items-center">
            <div class="card mx-auto" style="width:600px">
                <div class="card-body">
                    
                    <form method="post" action="/encurtado.com/front.do">
                    	<input type="hidden" name="command" value="SaveLinkCommand">
                    	<input type="hidden" name="origin" value="/loggedin/personalizar-link.jsp">
                    	
                    	<input type="hidden" name="id"  value="<%= link != null ? link.getId() : "" %>">
                    
                        <label for="link" class="form-label">Link original</label>

                        <div class="input-group mb-3">
                            <input type="url" id="link" name="link" class="form-control" placeholder="Informe o link" 
                            	   value="<%= link != null ? link.getUrlOriginal() : "" %>" required>
                        </div>

                        <label for="identifier">Identificador</label>
                        
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text opacity-75" id="basic-addon3">localhost:8080/encurtado.com/</span>
							</div>
							<input type="text" class="form-control" id="identifier" name="identifier" 
								   value="<%= link != null ? link.getUrlEncurtada() : "" %>" aria-describedby="basic-addon3">
						</div>
						
						<div class="form-check form-switch mb-3">
						  	<input class="form-check-input" type="checkbox" role="switch" id="switchPrivateLink" name="privateLink" <%= link != null && link.isPrivateLink() ? "checked" : "" %>>
						  	<label class="form-check-label" for="switchPrivateLink">Link privado</label>
						</div>

						<button type="submit" class="btn btn-success w-100">Salvar</button>
                    </form>
                </div>
            </div>
        </div>

        <div class="text-center mt-4">
            <a href="/encurtado.com/loggedin/logged.jsp" class="btn btn-danger">Voltar</a>
        </div>

        <%@ include file="/includes/messages.jsp" %>
    </div>

</body>
</html>
