<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
	<title>Save Suspect</title>

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css">

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/add-customer-style.css">
		  
	<style>
		.error {color:red}
		span{color:red}
	</style>
	
	<link rel="icon" href="${pageContext.request.contextPath}/resources/VAC.jpg">
	
</head>

<body>
	
	<div id="wrapper">
		<div id="header">
			<h2>VAC Checker Online</h2>
		</div>
	</div>

	<div id="container">
		<h3>Save Suspect</h3>
	
		<form:form action="saveSuspect" modelAttribute="suspect" method="POST">
		
			<!--  need to associate this data with customer id -->
			<form:hidden path="id" />
			<table>
				<tbody>		
					<tr>
						<td><label>SteamID:</label></td>
						<td><form:input path="steamid" />
						<form:errors path="steamid" cssClass="error" /><span>${error}</span></td>
					</tr>

					<tr>
						<td><label>Description (optional):</label></td>
						<td><form:input path="description" /></td>
					</tr>

					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	
		<div style="clear; both;"></div>
		
		<p>
			<a href="${pageContext.request.contextPath}/suspects/showList">Back to List</a>
		</p>
	
	</div>

</body>

</html>