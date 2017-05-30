<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Home | Feedback System</title>
<noscript>
	<meta HTTP-EQUIV="Refresh" CONTENT="0;URL=JavaScriptErrorPage.jsp">
</noscript>
<!-- CSS  -->
<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/select2.min.css">
<link rel="stylesheet" href="css/AdminLTE.min.css">
<link href="css/icon.css" rel="stylesheet">
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/material-design-iconic-font.min.css" rel="stylesheet">
<link href="css/app_1.min.css" rel="stylesheet">
<link href="css/app_2.min.css" rel="stylesheet">
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link rel="shortcut icon" href="photos/daiict.png" />
</head>
<body>
	<%
		String userBean = (String) session.getAttribute("userBean");
		if (userBean != null) {
	%>
	
	<nav class="nav1">
		<div class="nav-wrapper blue lighten-1">
			<!-- <img class="circle responsive-img waves-circle center" src="photos/BigLogo.png" id="brand-logo"> -->
			<div class="nav-wrapper container">
				<a id="logo-container" href="#" class="brand-logo center"> <img
					class="circle responsive-img waves-circle center"
					src="photos/BigLogo.png" id="logo"> Course Feedback System
				</a>
			</div>
		</div>
	</nav>


	<%
		} else {
			response.sendRedirect("index.jsp");
		}
	%>

	<!--  Scripts-->
	<!-- script src="https://code.jquery.com/jquery-2.1.1.min.js"></script  -->
	<script src="javaScript/jquery-2.1.4.min.js"></script>
	<script src="javaScript/materialize.js"></script>
	<script src="javaScript/init.js"></script>
	<script src="javaScript/jquery.min.js"></script>
	<script src="javaScript/bootstrap.min.js"></script>
	<script src="javaScript/waves.min.js"></script>
	<!-- <script src="javaScript/app.min.js"></script> -->
	<script src="javascript/js.js"></script>
	<script src="javascript/app1.js"></script>

	<script src="javaScript/select2/select2.full.min.js"></script>
</body>
</html>
