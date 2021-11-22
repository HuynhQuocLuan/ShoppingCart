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
<title>Shopping Cart Finalize</title>
<link rel = "stylesheet" type = "text/css" href = "${contextPath }/css/styples.css">
</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />
	
	<div class="page-title">Finalize</div>
	
	<div class = "container">
		<h3>Thank you for Order</h3>
		Your order number is: ${lastOrderedCartInfo.orderNum }
	</div>
	
	<jsp:include page="_footer.jsp" />

</body>
</html>