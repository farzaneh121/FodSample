<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shopping Cart</title>
<link rel="stylesheet" href="/shop-project/resources/css/pages.css">
</head>
<body>
	<h3>Shopping cart</h3>
	<a href="${pageContext.request.contextPath}/jsps/home.jsp">
		<button id="home-button">Home</button>
	</a>
	<a
		href="${pageContext.request.contextPath}/shoppingCartServlet?action=logout">
		<button id="button">log out</button>
	</a>
	<br>
	<br>
	<form action="${pageContext.request.contextPath}shoppingCartServlet"
		method="get">
		<center>
			<table id="table">
				<c:forEach var="productItem" items="${requestScope.productListCart}">
					<c:set var="newRow" value="${newRow+1}" scope="request" />
					<c:if test="${newRow mod 5 eq 0 }">
						<tr>
					</c:if>
					<td>
						<table id="miniTable">
							<tr>
								<td><c:out value="${productItem.name}" /></td>
							</tr>
							<tr>
								<td><c:out value="${productItem.cost}" /></td>
							</tr>
							<tr>
								<td><c:out value="${productItem.category.name}" /></td>
							</tr>
							<tr>
								<td><c:out value="${productItem.productStatus}" /></td>
							</tr>
							<tr>
								<td><a
									href="${pageContext.request.contextPath}/shoppingCartServlet?action=delete&ProductId=${productItem.id}">delete</a>
								</td>
							</tr>
						</table>
				</c:forEach>

			</table>
			<br>
			<br> <a
				href="${pageContext.request.contextPath}/shoppingCartServlet?action=back">back
				to Shopping page</a>

		</center>
	</form>
	<table>
		<tr>
			<td>total amount:</td>
			<td>${requestScope.totalCost}</td>
		</tr>
	</table>

</body>
</html>