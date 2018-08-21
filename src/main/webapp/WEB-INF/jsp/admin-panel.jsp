<%@page import="com.lukasrosz.vaccheckeronline.accounts.entity.User"%>
<%@page import="com.lukasrosz.vaccheckeronline.accounts.entity.Role"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>

<head>
<title>Vac Checker Admin Panel</title>
</head>

<body>
	<h2>Admin Panel</h2>

		<table>
			<tr>
				<th>User name</th>
				<th>Roles</th>
				<th>Action</th>
			</tr>
			<c:forEach var="tempUser" items="${userForm.users}" varStatus="status">
			
				<c:url var="updateLink" value="/adminPanel/editUser">
					<c:param name="username" value="${tempUser.username}" />
				</c:url>
					
				<tr>
					<td><p>${tempUser.username}</p></td>
					
					<td>
					<c:forEach var="tempRole" items="${tempUser.authorities}" varStatus="roleStatus">
						
						<input type="checkbox" checked disabled/> 
						${fn:toLowerCase(fn:replace(tempRole.authority, "ROLE_", ""))}
					
					</c:forEach>
					</td>
					
					<td><a href="${updateLink}">Edit</a></td>
					
				</tr>
			</c:forEach>
		</table>
		
		<br><br>
		<form:form action="${pageContext.request.contextPath}/suspects/showList"
						method="GET">
		<input type="submit" value="Back to list" class="adminPanelButton" />
		</form:form>
	
</body>
</html>
