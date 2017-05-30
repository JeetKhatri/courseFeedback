<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot password</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<link rel="shortcut icon" href="photos/daiict.png" />
</head>
<body>
	<%@ include file="userHeader.jsp"%>
	<form action="ForgetPasswordServlet">
		<div class="login-content withbg">
			<div class="lc-block toggled " id="l-login">
				<div class="lcb-form hoverable ">
					<h5 class="text-centre strong">Enter Registered Email ID</h5>

					<div class="input-group m-b-20">
						<span class="input-group-addon"><i class="zmdi zmdi-email"></i></span>
						<div class="fg-line">
							<input type="text" class="form-control"
								placeholder="Email Address" name="emailId">${emailId}
						</div>
					</div>

					<input type="submit" 
						class="btn waves-effect waves-light blue accent-4 hoverable white-text">
				</div>

				<div class="lcb-navigation">
					<a href="login.jsp" data-ma-action="login-switch"
						data-ma-block="#l-login"
						class="hoverable blue accent-4 white-text"><i
						class="zmdi zmdi-long-arrow-right "></i> <span>Sign in</span></a>
					<!-- a href="#" data-ma-action="login-switch" data-ma-block="#l-register"><i class="zmdi zmdi-account-add"></i> <span>Register</span></a -->
				</div>
			</div>

		</div>
	</form>



	<%@ include file="userFooter.jsp"%>
</body>
</html>