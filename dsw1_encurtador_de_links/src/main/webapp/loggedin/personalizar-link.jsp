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

	<%
		var linkId = (Integer) request.getAttribute("linkId");
	%>

    <div class="container mt-5">
        <h1 class="text-center mb-4">Personalizar Link</h1>

        <div class="align-items-center">
            <div class="card mx-auto" style="width:600px">
                <div class="card-body">
                    
                    <form method="post" action="/encurtado.com/front.do">
                    	<input type="hidden" name="command" value="CreateLinkCommand">
                    	
                    	<input type="hidden" name="id"  value="<%= linkId != null ? linkId : "" %>">
                    
                        <label for="link" class="form-label">Link original</label>

                        <div class="input-group mb-3">
                            <input type="url" id="link" name="link" class="form-control" placeholder="Informe o link" required>
                        </div>

                        <label for="identifier">Identificador</label>
                        
						<div class="input-group mb-3">
							<div class="input-group-prepend">
								<span class="input-group-text opacity-75" id="basic-addon3">localhost:8080/encurtado.com/</span>
							</div>
							<input type="text" class="form-control" id="identifier" name="identifier" aria-describedby="basic-addon3">
						</div>

						<button type="submit" class="btn btn-success w-100">Encurtar</button>
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
