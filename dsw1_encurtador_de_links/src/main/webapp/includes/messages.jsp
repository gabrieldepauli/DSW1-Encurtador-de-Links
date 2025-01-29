<%@page import="org.apache.tomcat.jakartaee.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
        String errorMessage = (String) request.getAttribute("errorMessage");
		String successMessage = (String) request.getAttribute("successMessage");
		
        if (StringUtils.isNotBlank(errorMessage)) {
    %>
			<div class="d-flex justify-content-center mt-3">
				<div class="alert alert-danger alert-dismissible fade show text-center" role="alert">
					<%= errorMessage %>
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
			</div>
	<%
        }
		
        if (StringUtils.isNotBlank(successMessage)) {
	%>
			<div class="d-flex justify-content-center mt-3">
				<div class="alert alert-success alert-dismissible fade show text-center" role="alert">
					<%= successMessage %>
					<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
				</div>
			</div>
	<%
        }
    %>
</body>
</html>