<!doctype html>
<html lang="en">
<head>
	<title>Index</title>
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
</head>
<body>
	<h1>Welcome ${user.username}</h1>
	
	<a href="/logout">Logout</a>
	<div class="container">
			<h1>TV Shows</h1>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Show</th>
						<th>Network</th>
						<th>Average Rating</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${shows}" var="show">
						<tr>
							<td><a href="/shows/${show.id}"><c:out value="${show.title}"/></a></td>
							<td><c:out value="${show.network}"/></td>
							<td><c:out value="${show.avg}"/></td>	
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<a href="/new">Add a new show</a>  
</body>
</html>