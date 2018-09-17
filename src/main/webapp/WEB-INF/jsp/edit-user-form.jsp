<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<title>Edit User</title>

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
	<h2>Edit User: ${user.username}</h2>

	<form:form modelAttribute="user" method="POST" action="saveUser"
		onsubmit="myButton.disabled = true; return true;">

		<form:hidden path="password" />

		<table>
			<tbody>
				<tr>
					<td><label>Username:</label></td>
					<form:hidden path="username" />
					<form:errors path="username" cssClass="error" />
					<td><input type="text" value="${user.username}" disabled /></td>

				</tr>

				<tr>
					<td><label>Authorities:</label></td>
					<!-- <td><form:checkboxes items="${authorityList}" path="authorities" /></td>
						 -->
					<td><c:forEach var="tempRole" items="${authorityList}">
							<form:checkbox path="authorities"
								value="${fn:toUpperCase(tempRole)}" />${fn:replace(tempRole, "ROLE_", "")}
						</c:forEach></td>
				</tr>

				<tr>
					<td><label>Enabled:</label></td>
					<td><form:checkbox path="enabled" value="1" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Save" name="myButton" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>

	<br>
	<br>
	<form:form
		action="${pageContext.request.contextPath}/adminPanel/showPanel"
		method="GET">
		<input type="submit" value="Back to Admin Panel"
			class="adminPanelButton" />
	</form:form>


</body>

</html>