<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form123"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/accountForm.css"/>
<head>
<meta charset="ISO-8859-1">
<title>User Form</title>
</head>
<body>
	<div class="create-account-form" align = "center">
		<h1>Account Management</h1>
		<h2>
			<a href="${contextPath}/new">Add New Account</a>
			<a href="${contextPath}/list">List All Accounts</a>
		</h2>
	</div>
	<div align="center">
	
	<!-- form123 is same to prefix="form123" -->
	<%-- <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form123"%> --%>
	
	<form123:form action="insert" method="post" modelAttribute="account">
			<h2>Add New Account</h2>
			<table>			
				<%-- <tr>
				<th>ID: </th>
				<th><form123:input path="id"/></th>
				
				
				</tr> --%>
				<tr>
					<th><p>Your Name:</p> </th>
					<th><form123:input path="name"/></th>
					<td><form123:errors path="name" cssClass="error"/></td>
					
					
				</tr>
				<tr>
					<th><p>Username:</p> </th>
					<th><form123:input path="userName"/></th>
					<td><form123:errors path="userName" cssClass="error"/></td>
					
					
				</tr>
				<tr>
					<th><p>Password:</p> </th>
					<th><form123:input path="password" type = "password"/></th>
					<th><form123:errors path="password" cssClass="error"/></th>	
				</tr>
				<tr>
					<th><p>Password Confirm:</p> </th>
					<th><form123:input path="passwordConfirm" type = "password"/></th>
					<th><form123:errors path="passwordConfirm" cssClass="error"/></th>	
				</tr>
				<tr>
					<th align = "right"><p>Role: </p></th>
					<c:forEach items = "${roles }" var = "role">
						<td><form123:radiobutton path ="userRole" value ="${role }"
									label = "${role }" checked="${role == 'EMPLOYEE'}"/></td>
					</c:forEach>
					<%-- <th><form123:input type="radio" path="userRole" value = "MANAGER"/>Manager</th>
					<th><form123:input type="radio" path="userRole" value = "EMPLOYEE"/>Employee</th> --%>
				</tr>
				<tr>
					<td><button type="submit" name="save" value="create">Create</button></td>
				</tr>
			</table>
		</form123:form>
	
<!-- 
		<form action="insert" method="post">
			<h2>Add New Customer</h2>
			<table>			
				<tr>
				<th>First Name: </th>
				<th><input type="text" name="firstname"></th>
				</tr>
				<tr>
					<th>Last Name: </th>
					<th><input type="text" name="lastname"></th>
				</tr>
				<tr>
					<th>Email: </th>
					<th><input type="text" name="email"></th>
				</tr>
				<tr>
					<td><input type="submit" name="save" value="create"></td>
				</tr>
			</table>
		</form>
		 -->
	</div>
</body>
</html>