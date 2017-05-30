<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script>
	function goBack() {
		alert("ok");
		window.history.go(-2);
	}
</script>
</head>
<%
	String cc = session.getAttribute("courseCounter") + "";
	int courseCounter = Integer.parseInt(cc);
	if (courseCounter > 0)
		courseCounter--;
	session.setAttribute("courseCounter", courseCounter);
%>
<body onload="goBack()">

</body>
</html>






