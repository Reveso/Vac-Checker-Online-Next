<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>

<head>
<title>Edit User</title>


</head>

<body>

	<h2>Edit User: ${user.username}</h2>

	<form:form modelAttribute="user" method="POST" action="saveUser" onsubmit="myButton.disabled = true; return true;">
		
		<form:hidden path="password"/>
		
		<table>
				<tbody>
					<tr>
						<td><label>Username:</label></td>		
						<form:hidden path="username"/>
						<form:errors path="username" cssClass="error" />			
						<td><input type="text" value="${user.username}" disabled/></td>
							
					</tr>
				
					<tr>
						<td><label>Authorities:</label></td>
						<!-- <td><form:checkboxes items="${authorityList}" path="authorities" /></td>
						 -->
						<td>
						<c:forEach var="tempRole" items="${authorityList}">
							<form:checkbox path="authorities" value="${fn:toUpperCase(tempRole)}"/>${fn:replace(tempRole, "ROLE_", "")}
						</c:forEach>
						</td>
					</tr>

					<tr>
						<td><label>Enabled:</label></td>
						<td><form:checkbox path="enabled" value="1"/></td>
					</tr>
					
					<tr>
						<td><input type="submit" value="Save" name="myButton" /></td>
					</tr>
				</tbody>
			</table>
	</form:form>
	
	<br><br>
	<form:form action="${pageContext.request.contextPath}/adminPanel/showPanel"
						method="GET">
		<input type="submit" value="Back to Admin Panel" class="adminPanelButton" />
	</form:form>
	

</body>

</html>