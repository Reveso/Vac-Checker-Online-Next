<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Access Denied</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.2.0/css/all.css"
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/nav-style.css">
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/VAC.jpg">
</head>

<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/">Vac
			Checker Online</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="${pageContext.request.contextPath}/">Home <span
						class="sr-only">(current)</span></a></li>
				<security:authorize url="/suspects/showFormForAdd">
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/suspects/showFormForAdd">Add
							Suspect</a></li>
				</security:authorize>
			</ul>
			<ul class="navbar-nav mr-right">
				<security:authorize url="/adminPanel/showPanel">
					<li class="navbar-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/adminPanel/showPanel"><i
							class="fas fa-toolbox"></i> Admin Panel</a></li>
				</security:authorize>
				<security:authorize access="isAnonymous()">
					<li class="navbar-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/users/showRegistrationFrom"><i
							class="fas fa-user-plus"></i> Sign Up</a></li>
					<li class="navbar-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/users/login"><i
							class="fas fa-user"></i> Log in</a></li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="navbar-item"><form:form
							action="${pageContext.request.contextPath}/logout" method="POST">
							<button type="submit" class="btn btn-link">
								<i class="fas fa-sign-out-alt"></i> Sign Out
							</button>
						</form:form></li>
				</security:authorize>
			</ul>
		</div>
	</nav>
	<h2>Insufficient Permissions</h2>
	<hr>
	<p>
		<a href="${pageContext.request.contextPath}/suspects/showList">Back
			to HomePage</a>
	</p>
	
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
</body>
</html>