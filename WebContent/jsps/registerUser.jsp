<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/shop-project/resources/css/pages.css">
<title>registration</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/UserServlet"
		method="post">
		<a href="${pageContext.request.contextPath}/jsps/home.jsp">
			<button id="home-button">Home</button>
		</a>

		<div id="content">
			<table>
				<tr>
					<td>first name :</td>
					<td><input type="text" name="first-name" required="required"
						title="first name"></td>
				</tr>
				<tr>
					<td>last name :</td>
					<td><input type="text" name="last-name" required="required"
						title="last name"></td>
				</tr>
				<tr>
					<td>email address :</td>
					<td><input type="text" name="email-address"
						required="required" title="email"></td>
				</tr>
				<tr>
					<td>user name :</td>
					<td><input type="text" name="user-name" required="required"
						title="userName"></td>
				</tr>
				<tr>
					<td><input type="submit" value="save" name="action-mode">
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>