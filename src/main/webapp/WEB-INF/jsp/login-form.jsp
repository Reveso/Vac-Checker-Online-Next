<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>
	<title>Login Page</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/user-styles.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.2.0/css/all.css" 
	integrity="sha384-hWVjflwFxL6sNzntih27bfxkr27PmbbK/iSvJ+a4+0owXq79v+lsFkW54bOGbiDQ" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/nav-style.css">
	<link rel="icon"
	href="${pageContext.request.contextPath}/resources/VAC.jpg">
	
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="${pageContext.request.contextPath}/">Vac Checker Online</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active">
					<a class="nav-link" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
				</li>
				<security:authorize url="/suspects/showFormForAdd">
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.request.contextPath}/suspects/showFormForAdd">Add Suspect</a>
					</li>
				</security:authorize>
			</ul>
			<ul class="navbar-nav mr-right">
				<security:authorize url="/adminPanel/showPanel">
					<li class="navbar-item"><a class="nav-link" href="${pageContext.request.contextPath}/adminPanel/showPanel"><i class="fas fa-toolbox"></i> Admin Panel</a></li>
				</security:authorize>	
				<security:authorize access="isAnonymous()">
					<li class="navbar-item"><a class="nav-link" href="${pageContext.request.contextPath}/users/showRegistrationFrom"><i class="fas fa-user-plus"></i> Sign Up</a></li>
					<li class="navbar-item"><a class="nav-link" href="${pageContext.request.contextPath}/users/login"><i class="fas fa-user"></i> Log in</a></li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="navbar-item">
						<form:form action="${pageContext.request.contextPath}/logout"
							method="POST">
						<button type="submit" class="btn btn-link"><i class="fas fa-sign-out-alt"></i> Sign Out</button>
						</form:form>
					</li>
				</security:authorize>	
			</ul>
		</div>
	</nav>
	<div class="container">
		<form:form id="content" action="${pageContext.request.contextPath}/authenticateUser" 
			method="POST">
			<h2>Login to your account</h2>
			<c:if test="${param.error != null}">
				<i class="failed">You entered invalid username/password.</i>
			</c:if>
			<div class="form-group">
				<label for="usernameInput">Username</label>
				<input type="text" class="form-control" id="usernameInput" placeholder="Username" name="username">
			</div>
			<div class="form-group">
				<label for="passwordInput">Password</label>
				<input type="password" class="form-control" id="passwordInput" placeholder="Password" name="password">
			</div>
			<div class="form-group form-check">
				<input type="checkbox" class="form-check-input" id="rememberCheck" name="remember-me">
				<label class="form-check-label" for="rememberCheck">Stay logged in</label>
			</div>
			<input type="submit" value="Log in" class="btn btn-primary"></input>
			<a class="btn btn-secondary" href="${pageContext.request.contextPath}/">Go back</a>
		</form:form>
	</div>
</body>
</html>

