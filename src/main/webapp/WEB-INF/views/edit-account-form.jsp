<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form123"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Account Form</title>
</head>
<body>
	<div align = "center">
		<h1>Account Management Application</h1>
		<h2>
			<a href="${pageContext.request.contextPath }/new">Add new Account</a>
			<a href="${pageContext.request.contextPath }/list">List all Accounts</a>
		</h2>
	</div>
	
	<c:url var="updateAction" value="/update"></c:url>
	<div align="center">
		<form123:form action="${updateAction }" method="post" modelAttribute="account">
			<h2>Update Account</h2>
			<table>
			
				<tr>
					<th>Username: </th>
					<th><c:out value="${account.userName }"></c:out></th>	
					<th><form123:hidden  path="userName"
						value="${account.userName }"/></th>
					
				</tr>
				<tr>
					<th>Your name:</th>
					<th><form123:input path="name"
						value="${account.name }"/></th>
				</tr>

				<tr>
					<th>Password:</th>
					<th><form123:password path="password"
						value="${account.password }"/></th>
				</tr>
					<tr>
					<th align = "right">Role: </th>
					<c:forEach items = "${roles }" var = "role">
						<td><form123:radiobutton path ="userRole" value ="${role }" checked ="${role == account.userRole ? 'checked' : '' }"
									label = "${role }" /></td>
					</c:forEach>
				</tr>
				<tr>
					<td><input type="submit" name="save" value="save"></td>
				</tr>

			</table>
		</form123:form>
		</div>

</body>
</html>