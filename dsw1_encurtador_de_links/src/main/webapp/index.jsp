<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página inicial</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<h1 style="text-align: center;">Página inicial</h1>
	
	<div class="d-flex justify-content-center mt-4">
        <div class="border p-4" style="width: 300px; border-radius: 8px;">
        	<h3 style="text-align: center;">Deseja logar no sistema?</h3>
        
            <a href="front.do?action=loginForm" style="text-decoration: none;">
                <button style="padding: 10px 20px; font-size: 15px; font-weight: bold; background-color: #4CAF50; color: white; border: none; border-radius: 5px; cursor: pointer; display: block; margin: 20px auto;">Log In</button>
            </a>
        </div>
    </div>
</body>
</html>