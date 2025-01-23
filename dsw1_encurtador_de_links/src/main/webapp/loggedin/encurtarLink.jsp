<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Encurtar link</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<div class="d-flex justify-content-center mt-4">
        <div class="border p-4" style="width: 300px; border-radius: 8px;">
            <form method="post" action="logged.do?action=encurtarLink" style="text-align: center;">
                <div style="margin-bottom: 15px;">
                    <label for="link" style="display: block; font-size: 16px; font-weight: bold;">Link original:</label>
                    <input type="url" id="link" name="link" placeholder="Informe o link." style="padding: 10px; font-size: 14px; width: 100%; text-align: center;" required="required">
                </div>

                <div>
                    <button type="submit" style="padding: 10px 20px; font-size: 15px; font-weight: bold; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer; display: block; margin: 20px auto;">Encurtar</button>
                </div>
            </form>
        </div>
    </div>
    
    <div class="text-center mt-4">
        <a href="index.jsp" style="text-decoration: none;">
            <button class="btn btn-danger" style="font-weight: bold;">Voltar</button>
        </a>
    </div>

 	<%
	    String message = (String) request.getAttribute("message");
 		Boolean sucess = (Boolean) request.getAttribute("sucess");
	
	    if (message != null) {
	    	if(sucess == true){
	%>
		<div class="d-flex justify-content-center mt-3">
	        <div class="alert alert-primary alert-dismissible fade show text-center" role="alert">
	            Link encurtado com sucesso: <a href="logged.do?action=redirecionarLink&url_encurtada=<%= message %>">
	            <%= message %></a>
	            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
	        </div>
    	</div>
	<%
	    }else{
	    	%><div class="d-flex justify-content-center mt-3">
		    <div class="alert alert-secundary alert-dismissible fade show text-center" role="alert">
		        <%= message %>
		        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		    </div>
		</div> <%
	    }
	    }
	%>


</body>
</html>