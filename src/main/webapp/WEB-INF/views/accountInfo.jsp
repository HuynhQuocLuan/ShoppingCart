<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="name" value = "${pageContext.request.userPrincipal.name }"/>

<style type="text/css">
  <%@include file="css/styles.css" %> 
</style>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Account Info</title>
<link rel="stylesheet" href="${contextPath }/css/styles.css" type="text/css" >
</head>
<body>
	<jsp:include page = "_header.jsp" />
	<jsp:include page = "_menu.jsp"/>
	
	<div class = "page-title">Account Info</div>
	
	<div class = "account-container">
		<ul>
			<li>User Name: ${name }</li>
			<li>Role:
				<ul>
					<c:forEach items = "${userDetails.authorities }" var="auth">
						<li>${auth.authority }</li>
					</c:forEach>
				</ul>
			</li>
		</ul>
	</div>
	
	<jsp:include page = "_footer.jsp"/>

</body>
</html>