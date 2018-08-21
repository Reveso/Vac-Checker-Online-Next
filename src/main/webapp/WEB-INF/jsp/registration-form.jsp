<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>

<head>
<title>Vac Checker Registration Page</title>

<style>
.failed {
	color: red;
}
</style>
</head>

<body>

	<h2>Create new VAC Checker account</h2>

	<form:form
		action="${pageContext.request.contextPath}/users/processRegistrationForm"
		modelAttribute="newUser" method="POST">

		<i class="failed">${registrationError}</i>
		<p>
			Username:
			<form:input path="username" />
			<form:errors path="username" cssClass="failed" />
		</p>

		<p>
			E-mail:
			<form:input path="email"/>
			<form:errors path="email" cssClass="failed" />
		</p>
		
		<p>
			Password:
			<form:input path="password" type="password"/>
			<form:errors path="password" cssClass="failed" />
		</p>
		
		<p>
			Confirm:
			<form:input path="matchingPassword" type="password"/>
			<form:errors path="matchingPassword" cssClass="failed"/>
		</p>

		<input type="submit" value="Register" />
	</form:form>

	<a href="${pageContext.request.contextPath}/suspects/showList">Back
		to HomePage</a>

</body>

</html>