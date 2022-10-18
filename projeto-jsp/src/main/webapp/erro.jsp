<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Erro</title>
</head>
<body>
	<h1>Error</h1>
	<p>Entre em contato com o nosso suporte.</p>
	<%
		out.print(request.getAttribute("msg"));
	%>>
</body>
</html>