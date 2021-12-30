<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<style type="text/css">
  <%@include file="css/login.css" %> 
</style>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="${contextPath }/css/styles.css" type="text/css" >
</head>
<body>
	<jsp:include page = "_header.jsp" />
	<jsp:include page = "_menu.jsp"/>
	
	<%-- <div class="page-title">Login (for Employee, Manager)</div>
	
	<div class="login-container">
		<h3>Enter username and password</h3>
		<br>
		<!-- login?error=true -->
		<c:if test = "${param.error == 'true' }">
			<div style = "color: red; margin: 10px, 0px;">
				
				Login Failed!!!<br/> Reason :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }
				
			</div>
		</c:if>
		
		<form method = "POST" action = "${contextPath }/j_spring_security_check">
			<table>
				<tr>
					<td>User Name *</td>
					<td><input name = "userName" /></td>
				</tr>
				<tr>
					<td>Password *</td>
					<td><input type = "password" name ="password"/></td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
					<td>
						<input type="submit" value = "login">
						<input type="reset" value = "Reset"/>
					</td>
				</tr>
			</table>
		</form>
		<span class = "error-message">${error }</span>
	</div> --%>
	
	<div class="center">
<h1>
Sign In<br />
</h1>
<h6>For Manager & Employee only</h6>

<c:if test="${param.error == 'true'}">
<div style="color: red; margin: 5px 0px;">
Login Failed!!!<br /> Reason:
${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
</div>
</c:if>
<form method="POST" action="${contextPath}/j_spring_security_check">
<div class="txt_field">
<input type="text" id="text" name="userName" required>
<span></span>
<label>Username</label>
</div>

<div class="txt_field">
<input type="password" name="password" required>
<span></span>
<label>Passwords</label> 
</div>

<div class="pass"><a href="#">Forgot Password?</a></div>

<div>
<input class="button" type="submit" value="Sign In">
</div> 
</form>
<span class="error-message">${error}</span>
</div>
	<div style="margin-bottom: 550px;"></div>
	<jsp:include page="_footer.jsp"/>
	
</body>
</html>