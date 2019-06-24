<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
<link rel="stylesheet" href="/shop-project/resources/css/error.css">
<script src="/shop-project/resources/js/error.js"></script>

</head>
<body>
	<div class="background">

		<!-- ---------- the SVG clouds ------------ -->
		<svg class="clouds cloud1" xmlns="http://www.w3.org/2000/svg"
			xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" x="0" y="0"
			width="512" height="512" viewBox="0 0 512 512"
			enable-background="new 0 0 512 512" xml:space="preserve">
    <path id="cloud-icon"
				d="M406.1 227.63c-8.23-103.65-144.71-137.8-200.49-49.05 -36.18-20.46-82.33 3.61-85.22 45.9C80.73 229.34 50 263.12 50 304.1c0 44.32 35.93 80.25 80.25 80.25h251.51c44.32 0 80.25-35.93 80.25-80.25C462 268.28 438.52 237.94 406.1 227.63z" />
	</svg>
		<svg class="clouds cloud2" xmlns="http://www.w3.org/2000/svg"
			xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" x="0" y="0"
			width="512" height="512" viewBox="0 0 512 512"
			enable-background="new 0 0 512 512" xml:space="preserve"> 
    <path id="cloud-icon"
				d="M406.1 227.63c-8.23-103.65-144.71-137.8-200.49-49.05 -36.18-20.46-82.33 3.61-85.22 45.9C80.73 229.34 50 263.12 50 304.1c0 44.32 35.93 80.25 80.25 80.25h251.51c44.32 0 80.25-35.93 80.25-80.25C462 268.28 438.52 237.94 406.1 227.63z" />
	</svg>
		<svg class="clouds cloud3" xmlns="http://www.w3.org/2000/svg"
			xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" x="0" y="0"
			width="512" height="512" viewBox="0 0 512 512"
			enable-background="new 0 0 512 512" xml:space="preserve">
    <path id="cloud-icon"
				d="M406.1 227.63c-8.23-103.65-144.71-137.8-200.49-49.05 -36.18-20.46-82.33 3.61-85.22 45.9C80.73 229.34 50 263.12 50 304.1c0 44.32 35.93 80.25 80.25 80.25h251.51c44.32 0 80.25-35.93 80.25-80.25C462 268.28 438.52 237.94 406.1 227.63z" />
	</svg>
		<svg class="clouds cloud4" xmlns="http://www.w3.org/2000/svg"
			xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" x="0" y="0"
			width="512" height="512" viewBox="0 0 512 512"
			enable-background="new 0 0 512 512" xml:space="preserve">
    <path id="cloud-icon"
				d="M406.1 227.63c-8.23-103.65-144.71-137.8-200.49-49.05 -36.18-20.46-82.33 3.61-85.22 45.9C80.73 229.34 50 263.12 50 304.1c0 44.32 35.93 80.25 80.25 80.25h251.51c44.32 0 80.25-35.93 80.25-80.25C462 268.28 438.52 237.94 406.1 227.63z" />
	</svg>

		<div class="wrapper">
			<!-- ---------------  Text  --------------- -->
			<div class="errorPage">
				<h1 class="slide-in-top">Oops!</h1>
				<h3 class="slide-in-left">${requestScope.exception}</h3>
				<h4 class="slide-in-left2">${requestScope.statusCode}.<br>${requestScope.servletName}.</h4>
			</div>

			<!-- --------------   Penguin   ---------------- -->
			<a href="${pageContext.request.contextPath}/jsps/home.jsp">

				<div class="penguinContainer">

					<div class="penguinWrap">
						<div class="penguin">
							<div class="bodyForm1"></div>
							<div class="bodyForm2"></div>
							<div class="leftWing"></div>
							<div class="rightWing"></div>
							<div class="abdomen1"></div>
							<div class="abdomen2"></div>
							<div class="beak1"></div>
							<div class="beak2"></div>
							<div class="eyes">
								<div id="eye1_1"></div>
								<div id="eye2_1"></div>
								<div id="eye1_4"></div>
								<div id="eye2_4"></div>
							</div>
							<div class="leg1_1"></div>
							<div class="leg1_2"></div>
							<div class="leg2_1"></div>
							<div class="leg2_2"></div>
						</div>

						<span class="bubble">Home page maybe?</span>
					</div>
				</div>

			</a>
		</div>

	</div>


</body>
</html>