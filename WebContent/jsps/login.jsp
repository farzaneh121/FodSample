<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pages.css">
<title>login</title>
</head>
<body>
	<h2>Login</h2>
	<a href="${pageContext.request.contextPath}/jsps/home.jsp">
		<button id="home-button">Home</button>
	</a>
	<form action="${pageContext.request.contextPath}/UserServlet"
		method="post">

		<div id="content">
			<table>
				<tr>
					<td>user name :</td>
					<td><input type="text" name="user-name" required="required"
						title="user name"></td>
				</tr>
				<tr>
					<td>email address :</td>
					<td><input type="text" name="email" required="required"
						title="email address"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Login" name="action-mode">
					</td>

				</tr>
			</table>

		</div>
	</form>
</body>
</html>