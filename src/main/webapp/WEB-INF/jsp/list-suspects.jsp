<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>



<!DOCTYPE html>
<html>
<head>
	<title>Vac Checker Online</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1">	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/vac-styles.css">
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
		<div class="jumbotron jumbotron-dark">
			<h2>Check status of reported steam accounts</h2>
		</div>
		<table class="table table-striped table-dark">
			<thead>
				<tr>
					<th>Nickname</th>
					<th>Status</th>
					<th>Added</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="tempSuspect" items="${suspects}">
					<c:url var="updateLink" value="/suspects/showFormForUpdate">
						<c:param name="suspectId" value="${tempSuspect.id}" />
					</c:url>
					<c:url var="deleteLink" value="/suspects/delete">
						<c:param name="suspectId" value="${tempSuspect.id}" />
					</c:url>
					<tr>
						<td class="cell-nickname"><a href="http://steamcommunity.com/profiles/${tempSuspect.steamid}">
						${tempSuspect.username}</a>
							<span class="details details_${tempSuspect.id}">
								<hr>SteamID: <span class="steamid-details">${tempSuspect.steamid}</span>
								<br>Number of VAC Bans: <span class="numeric-ban-details">${tempSuspect.playerBans.numberOfVACBans}</span>
								<br>Days Since Last Ban: <span class="numeric-ban-details">${tempSuspect.playerBans.daysSinceLastBan}</span>
								<br>Community Ban: <span class="non-numeric-ban-details">${tempSuspect.playerBans.communityBanned}</span>
								<br>Number of Game Bans: <span class="numeric-ban-details">${tempSuspect.playerBans.numberOfGameBans}</span>
								<br>Economy Ban: <span class="non-numeric-ban-details">${tempSuspect.playerBans.economyBan}</span>
							</span>
						</td>
						<td class="cell-vacstatus">
							<span class="vacstatus">${tempSuspect.vacStatus}</span>
							<span class="details details_${tempSuspect.id}">
								<hr>
								<security:authorize url="/suspects/showFormForUpdate">
									<a href="${updateLink}" class="btn btn-info">Update</a>
								</security:authorize>
								<security:authorize url="/suspects/delete">
									<a href="${deleteLink}" class="btn btn-danger"
										onclick="if(!(confirm('Are you sure you want to delete this suspect?'))) return false">
										Delete
									</a>
								</security:authorize>
								<br><i>Description:</i> ${tempSuspect.description}
							</span>
						</td>
						<td>${fn:substring(tempSuspect.additionDate, 0, 16)}</td>
						<td>
							<button id="details_${tempSuspect.id}" type="button" class="btn btn-light details-button">Details</button>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
		</table>

	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/resources/js/list-suspects.js"></script>
</body>
</html>