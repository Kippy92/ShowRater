<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isErrorPage="true" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
	    <meta charset="utf-8" />
	    <title>Edit</title>
	    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css">
	</head>
	<body>
	<a href="/main">Dashboard</a>    
		<h1>Edit Show</h1>
		<form:form action="/shows/${show.id}" method="post" modelAttribute="show">
		    <input type="hidden" name="_method" value="put">
		    <p>
		        <form:label path="title">Network</form:label>
		        <form:errors path="title"/>
		        <form:input path="title"/>
		    </p>
		    <p>
		        <form:label path="network">Network</form:label>
		        <form:errors path="network"/>
		        <form:input path="network"/>
		    </p>
		    <input type="submit" value="Submit"/>
		</form:form>
		<form action="/shows/${show.id}" method="post">
		    <input type="hidden" name="_method" value="delete">
		    <input type="submit" value="Delete">
		</form>     
	</body>
</html>    