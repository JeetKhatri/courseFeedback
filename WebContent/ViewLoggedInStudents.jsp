<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		response.setIntHeader("Refresh", 5);
		ServletContext context = getServletContext();
		int cnt = (Integer) context.getAttribute("cnt");
	%>
	<h3>
		Total Students Are Logged In
		<%=cnt%>
	</h3>
</body>
</html>