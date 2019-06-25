<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>home</title>
<link rel="stylesheet" href="/shop-project/resources/css/home.css" />
</head>
<link
	href="https://fonts.googleapis.com/css?family=Dancing+Script|Orbitron"
	rel="stylesheet">

<body>
	<center>
		<p id="title">Fari Shop</p>
		<jsp:useBean id="newPerson" class="com.farzaneh.model.Person"
			scope="request" />
		<c:if test="${newPerson ne null}">
			<h2>welcome dear ${newPerson.firstName} , you can login with
				your user name and email. have e good time :)</h2>
		</c:if>
		<br> <br>
		<div id="NavBar">

			<a href="${pageContext.request.contextPath}/jsps/home.jsp">
				<button id="button">Home</button>
			</a> <a href="${pageContext.request.contextPath}/jsps/registerUser.jsp">
				<button id="button">Registration</button>
			</a> <a href="${pageContext.request.contextPath}/jsps/login.jsp">
				<button id="button">Login</button>
			</a> <a
				href="${pageContext.request.contextPath}/productServlet?action=show">
				<button id="button">Products</button>
			</a>

		</div>
	</center>

	<br>
	<br>
	<br>
	<center>
		<img src="http://s4.picofile.com/file/8364080400/222.png" /> <img
			src="http://s4.picofile.com/file/8364080518/%E2%80%94Pngtree%E2%80%94old_tv_676035.png" />
	</center>


</body>
<br>
<br>
<div id="project">
	<center>
		<p>
			<em>Wellcome to our shop , please login or if you are not member
				, first register </em>
		</p>
	</center>
</div>
</html>