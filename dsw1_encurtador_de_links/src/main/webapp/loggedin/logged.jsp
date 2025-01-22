<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página do usuário</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<h1 class="text-center my-4">Você está logado!</h1>
	
	<div class="container border p-4 shadow-sm rounded" style="max-width: 600px; margin: 0 auto;">
		<a href="home.do?action=logout" style="text-decoration: none;">
	    	<button class="btn btn-danger btn-block w-100" style="font-size: 15px; font-weight: bold;">Logout</button>
		</a>
	</div>
		
</body>
</html>