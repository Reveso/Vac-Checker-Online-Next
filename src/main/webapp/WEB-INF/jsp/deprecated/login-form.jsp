<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<title>Login Page</title>

<style>
.failed {
	color: red;
}
</style>
</head>

<body>

	<h2>Login to your account</h2>

	<form:form action="${pageContext.request.contextPath}/authenticateUser"
		method="POST">

		<!-- Check for login error -->
		<c:if test="${param.error != null}">

			<i class="failed">You entered invalid username/password.</i>
		</c:if>

		<p>
			User Name: <input type="text" name="username" />
		</p>

		<p>
			Password: <input type="password" name="password" />
		</p>
		
		<p>
			Remember me: <input type="checkbox" name="remember-me" />
		</p>

		<input type="submit" value="Login" />

	</form:form>
	
	<a href="${pageContext.request.contextPath}/suspects/showList">Back to list</a>


</body>

</html>