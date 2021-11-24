<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style type="text/css">
  <%@include file="css/styles.css" %> 
</style>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
<link rel="stylesheet" href="${contextPath }/css/styles.css" type="text/css" >
</head>
<body>
	<jsp:include page = "_header.jsp" />
	<jsp:include page = "_menu.jsp"/>
	
	<div class  = "page-title">Shopping Cart</div>
	
	<div class = "demo-conainer">
		<h3>Content</h3>
		
		<ul>
			<li>Buy Online</li>
			<li>Admin pages</li>
			<li>Reports</li>
		</ul>
	</div>
	<jsp:include page="_footer.jsp"/>
</body>
</html>