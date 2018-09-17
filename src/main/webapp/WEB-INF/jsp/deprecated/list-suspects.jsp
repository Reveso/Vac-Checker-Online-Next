<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>

<html>

<head>
<title>VAC Checker Online</title>

<!-- reference our style sheet -->

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />

<link rel="icon"
	href="${pageContext.request.contextPath}/resources/VAC.jpg">

<style>
.adminPanelButton {
	background-color: #4CAF50; /* Green */
	border: none;
	float: right;
	color: white;
	padding: 15px 32px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
}
</style>
</head>

<body>

	<security:authorize access="hasRole('ADMIN')">
		<form:form action="${pageContext.request.contextPath}/adminPanel/showPanel"
						method="GET">

			<input type="submit" value="Admin Panel" class="adminPanelButton" />
		</form:form>
	</security:authorize>

	<div id="wrapper">
		<div id="header">
			<h2>VAC Checker Online</h2>
		</div>


	</div>

	<div id="container">

		<div id="content">
			<div id="buttons">
				<security:authorize url="/suspects/showFormForAdd">
					<input type="button" value="Add Suspect"
						onclick="window.location.href='showFormForAdd'; return false;"
						class="add-button" />
				</security:authorize>

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
					<form:form
						action="${pageContext.request.contextPath}/users/showRegistrationFrom"
						method="GET">
						<input type="submit" value="Register" class="login-button" />
					</form:form>
				</security:authorize>
			</div>
			<!--  add our html table here -->
			<table>
				<tr>
					<th>Username</th>
					<th>VAC Status</th>
					<th>Added</th>
					<th>Description</th>

					<security:authorize access="hasAnyRole('MODERATOR', 'ADMIN')">
						<th>Action</th>
					</security:authorize>

				</tr>

				<!-- loop over and print our customers -->
				<c:forEach var="tempSuspect" items="${suspects}">

					<!--  construct an "update" link with customer id -->
					<c:url var="updateLink" value="/suspects/showFormForUpdate">
						<c:param name="suspectId" value="${tempSuspect.id}" />
					</c:url>


					<!--  construct an "delete" link with customer id -->
					<c:url var="deleteLink" value="/suspects/delete">
						<c:param name="suspectId" value="${tempSuspect.id}" />
					</c:url>
					

					<tr>
						<td><a href="http://steamcommunity.com/profile/${tempSuspect.steamid}">
						${tempSuspect.username}</a></td>
						<td>${tempSuspect.vacStatus}</td>
						<td>${tempSuspect.additionDate}</td>
						<td>${tempSuspect.description}</td>

						<security:authorize access="hasAnyRole('MODERATOR', 'ADMIN')">
							<td>
								<!--  display the update link --> <a href="${updateLink}">Update</a>
								<security:authorize access="hasRole('ADMIN')">
								| <a href="${deleteLink}"
										onclick="if(!(confirm('Are you sure you want to delete this suspect?'))) return false">Delete</a>
								</security:authorize>
							</td>
						</security:authorize>

					</tr>

				</c:forEach>
			</table>

		</div>

	</div>

</body>

</html>









