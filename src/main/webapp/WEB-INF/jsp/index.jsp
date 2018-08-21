<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html>

<body>

	<h2>Maybe some good looking redirection page in the future</h2>
	
			<div id="buttons" align="right">
				<security:authorize access="isAuthenticated()">
					<form:form action="${pageContext.request.contextPath}/logout"
						method="POST">

						<input type="submit" value="Logout" class="login-button" />
					</form:form>
				</security:authorize>

				<security:authorize access="isAnonymous()">
					<form:form
						action="${pageContext.request.contextPath}/users/login"
						method="GET">
						<input type="submit" value="Login" class="login-button" />
					</form:form>
				</security:authorize>
			</div>

	<hr>

	<a href="suspects/showList">Vac Checker Online</a>

	<br>
	<br>
	
	<a href="videos/list">Videos</a>
	
	<br>

</body>

</html>