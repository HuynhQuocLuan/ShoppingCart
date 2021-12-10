<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer List</title>
</head>
<body>
	<div align="center">
		<h1>User Management Application</h1>
		
		<h2>
			<a href="${contextPath}/new">Add New User</a>
			<a href="${contextPath }/list">List All Users</a>
		</h2>
		
	</div>
	<div align="center">
		<table border="1">
			<tr><h2>List of Users</h2></tr>
			<tr>
				<th>NAME</th>
				<th>Username</th>
				<th>encrypted password</th>
				<th>Roles</th>
				<th>ACTION</th>
			</tr>
			<c:forEach var="a" items="${accounts}">
				<tr>
					<td><c:out value="${a.name}"></c:out></td>
					<td><c:out value="${a.userName}"></c:out></td>
					<td><c:out value="${a.password}"></c:out></td>
					<%-- <td>
						<c:forEach items="${a.roles }" var ="role">
							<c:out value="${role.name }"></c:out>
						</c:forEach>
					</td> --%>
					<td><c:out value="${a.userRole}"></c:out></td>
					
					<td colspan="2">
						<%-- <a href="${pageContext.request.contextPath }/customer/edit?customerId=<c:out value='${c.id }'/>">Edit</a> --%>
						<a href="${pageContext.request.contextPath }/edit/<c:out value='${a.userName }'/>">Edit</a>
	 					<a href="${pageContext.request.contextPath }/delete?deleteUserName=<c:out value='${a.userName }'/>">Delete</a>
	 		</td> 
					
				</tr>
				
			</c:forEach>
		</table>
		<a href="${contextPath }/logout">logout</a>
		<%-- <c:url var="logoutAction" value ="/j_spring_security_logout"></c:url>
		<form action = "${logoutAction }" method = "post">
			<input type="submit" value = "Logout"/> --%>
	</div>
</body>
</html>