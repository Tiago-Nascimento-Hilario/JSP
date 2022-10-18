<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css" media="screen" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<title>Login</title>
</head>
<body>
	<div class="container">
		<form class="row g-3" name="Servlet" action="Servlet" method="POST">
			<h3>Login</h3>
			<div class="col-md-3">
				<label for="inputEmail4" class="form-label">Email</label> <input
					type="email" class="form-control" id="inputEmail4">
			</div>
			<div class="col-md-2">
				<label for="inputPassword4" class="form-label">Password</label> <input
					type="password" class="form-control" id="inputPassword4">
			</div>


			<div class="col-12">
				<button type="submit" class="btn btn-primary">Sign in</button>
			</div>
		</form>
	</div>
	<h4>${msg}</h4>
</body>
</html>