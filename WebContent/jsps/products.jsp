<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products list</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pages.css">
</head>
<body>

	welcome back ${requestScope.member.firstName}

	<a href="${pageContext.request.contextPath}/jsps/home.jsp">
		<button id="home-button">Home</button>
	</a>
	<a
		href="${pageContext.request.contextPath}/productServlet?action=logout">
		<button id="button">log out</button>
	</a>
	<br>
	<br>
	<div>
		search by name or category:
		<form action="${pageContext.request.contextPath}/productServlet"
			method="post">
			<table>
				<tr>
					<td><select name="product-category" title="category">
					<option value=""><c:out
										value="select one item" /></option>
							<c:forEach var="category" items="${requestScope.categoryList}">
								<option value="${category.id}"><c:out
										value="${category.name}" /></option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td><input type="text" name="product-name"
						title="product name"></td>
				</tr>
				<tr>
					<td><input type="submit" value="search"></td>
				</tr>
			</table>
		</form>
	</div>
	<br>
	<br>

	<div id="cart">
		<a
			href="${pageContext.request.contextPath}/shoppingCartServlet?action=cart">Shopping
			cart</a>
	</div>

	<br>
	<br>
	<form action="${pageContext.request.contextPath}productServlet"
		method="get">
		<center>
			<table id="table">
				<c:forEach var="productItem" items="${requestScope.productList}">
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
									href="${pageContext.request.contextPath}/shoppingCartServlet?action=add&ProductId=${productItem.id}">Add</a>
								</td>
							</tr>
						</table>
				</c:forEach>
				</center>
			</table>
	</form>

</body>
</html>