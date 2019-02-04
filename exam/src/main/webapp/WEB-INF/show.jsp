
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta charset="utf-8" />
	    <title>Show</title>
	    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
	    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
		<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
	</head>
	<body> 
	<a href="/main">Dashboard</a>   
		<h1><c:out value="${show.title}"/></h1>
		<h2><c:out value="${show.network}"/></h2>
		<h1>Users who rated this show</h1>
		<table class="table table-bordered">
				<thead>
					<tr>
						<th>Name</th>
						<th>Rating</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${r}" var="rating">
						<tr>
							<td><c:out value="${rating.user.username}"/></td>
							<td><c:out value="${rating.score}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		<button><a href="/shows/${show.id}/edit" style="color:black"> Edit</a></button>
		<h2>Leave a rating:</h2>
		<form:form action="/ratings" method="post" modelAttribute="rating">
		    <p>
		        <form:label path="score"></form:label>
		        <form:errors path="score" style="color: red;" />
		        <form:input path="score"/>
		    </p>
			<input type="hidden" id="show_id" name="show_id" value="${show.id}">
		    <input type="submit" value="Rate!"/>
		</form:form>    
	</body>
</html>    
