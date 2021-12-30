<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<style type="text/css">
  <%@include file="css/styles.css" %> 
</style>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product List</title>

<link rel="stylesheet" type="text/css" href="${contextPath}/css/styles.css">

</head>
<body>
	<jsp:include page = "_header.jsp" />
	<jsp:include page = "_menu.jsp"/>
	
	<fmt:setLocale value="en_US" scope="session"/>
	
	<div class ="page-title">Product List</div>
	
	<form method="GET" action="${contextPath}/productList"> <!-- /insert?name=Tom&age=10    /productList?name=qwe -->
		<table>
			<tr>
				<td>SEARCH </td>
				</br>
				<td>by name or code: </td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="Search">
				</td>
			</tr>
		</table>
	</form>

	<c:forEach items="${paginationProductInfos.list }" var="productInfo">
		<div class = "product-preview-container">
			<ul>
				<li>
					<img class="product-image" src="${contextPath }/productImage?code=${productInfo.code}"/>
				</li>
				<li>Code: ${productInfo.code}</li>
				<li>Name: ${productInfo.name}</li>
				<li>Price:
					<fmt:formatNumber value ="${productInfo.price }" type="currency"/> 
				</li>
				<li>
					<a href="${contextPath }/buyProduct?code=${productInfo.code}">Buy Now</a>
				</li>
				<security:authorize access="hasRole('ROLE_MANAGER')">
					<li>
						<a style = "color: red;" href="${contextPath }/product?code=${productInfo.code}">Edit Product</a>
						<li>
							<a style = "color: red;" href="${contextPath }/removeProduct?code=${productInfo.code}">Delete Product</a>
						</li>
						 
					
					</li>
				</security:authorize>
			</ul>
		</div>
	</c:forEach>
	<br />
	
	<c:if test="${paginationProductInfos.totalPages >1 }">
		<div class ="page-navigator">
			<c:forEach items= "${paginationProductInfos.navigationPages }" var = "page">
				<c:if test="${page != -1 }">
					<a href ="productList?page=${page }" class = "nav-item">${page }</a>
				</c:if>
				<c:if test="${page ==-1 }">
					<span class="nav-item"> ... </span>
				</c:if>
			</c:forEach>
		</div>
	</c:if>
	
	<jsp:include page="_footer.jsp"/>

</body>
</html>