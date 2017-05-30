<%@page import="courseFeedback.bean.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login | Feedback System</title>
<link rel="shortcut icon" href="photos/daiict.png" />
</head>
<body>
	<%@ include file="userHeader.jsp"%>
	<form action="ResetPasswordServlet" method="post">
	<input type="hidden" name="adminId" value="<%=request.getParameter("adminId")%>">
	
		<div class="login-content withbg">
			<!-- Login -->
			<div class="lc-block toggled " id="l-login">
				<div class="lcb-form hoverable ">
 					<h5 class="text-centre strong">Reset Password</h5><br>
					<div class="input-group m-b-20">
						<span class="input-group-addon"></span>
						<div class="fg-line">
							<input type="password" class="form-control" name="newPassword" value="" 
								placeholder="new Password"><label style="text-align: left;">${newPassword}</label>
						</div>
					</div>

					<div class="input-group m-b-20">
						<span class="input-group-addon"></span>
						<div class="fg-line">
							<input type="password" class="form-control" name="confirmPassword" value="" 
								placeholder="confirm Password"><label style="text-align: left;">${confirmPassword}</label>
						</div>
					</div>

					<input type="submit" value="Change"
						class="btn waves-effect waves-light blue accent-4 hoverable white-text">
				</div>

			</div>
		</div>
	</form>


	<%@ include file="userFooter.jsp"%>

</body>
</html>























<body>

	<form action="ResetPasswordServlet">
	<input type="hidden" name="adminId" value="<%=request.getParameter("adminId")%>">
		<table>

			<tr>
				<td>Enter New Password</td>
				<td><input type="password" name="newPassword">${newPassword}
			</tr>
			<tr>
				<td>Enter Confirm Password</td>
				<td><input type="password" name="confirmPassword">${confirmPassword}
			</tr>

			<tr>
				<td colspan="2"><center><input type="submit" value="Change"></center></td>
			</tr>

		</table>

	</form>

</body>
</html>