<%@page import="courseFeedback.dao.LoginDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Feedback System</title>

</head>
<body>
	<%@ include file="header.jsp"%>

	<div class="section withbg" id="index-banner">
		<div class="container">

			<div class="row center" style="margin-top: 120px;">
				<h4 class="header col s12 light">Your Response is succesfully
					submitted</h4>
				<h5 class="header col s12 light" style="margin-top: 50px;">Thank
					You for your valuable feedback</h5>
			</div>
			<!-- <br /> <br /> <br /> -->
			<div class="row center" style="margin-top: 80px;">
				<form action="index.jsp" method="post">
					<input type="submit"
						class="btn waves-effect waves-light blue hoverable" value="Ok..!">
				</form>
			</div>
		</div>
	</div>
	<%@ include file="userFooter.jsp"%>

	<%
		
		HttpSession s1 = request.getSession();
		String str = (String) s1.getAttribute("userBean");
		s1.removeAttribute("timeStart");
		s1.removeAttribute("courseCode");
		s1.removeAttribute("feedbackedCourse");
		s1.removeAttribute("courseCounter");
		new LoginDAO().logout((String) s1.getAttribute("userPassword"));
		s1.removeAttribute("userPassword");
		s1.removeAttribute("programDetailsId");
		s1.invalidate();
	%>
</body>
</html>