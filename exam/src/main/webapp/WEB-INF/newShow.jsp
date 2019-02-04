<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta charset="utf-8" />
	    <title>Create a new show</title>
	    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
	</head>
	<body>
		<div class="container">   
			<h1>Create a new show</h1>
			<form:form action="/shows" method="post" modelAttribute="show">
			    <p>
			        <form:label path="title">Title: </form:label>
			        <form:errors path="title" style="color: red;" />
			        <form:input path="title"/>
			    </p>
			    <p>
			        <form:label path="network">Network: </form:label>
			        <form:errors path="network" style="color: red;" />
			        <form:input path="network"/>
			    </p>
			    <input type="submit" value="create"/>
			</form:form>
		</div>    
	</body>
</html>