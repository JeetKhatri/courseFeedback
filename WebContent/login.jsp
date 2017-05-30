<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Login | Feedback System</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<link rel="shortcut icon" href="photos/daiict.png" />
<script type="text/javascript" src="javaScript/showhidepwd.js"></script>
	<script type="text/javascript">
		
		$(document).ready(function () {
		$("#showHide").click(function () {
			if ($("#pwd").attr("type")=="password") {
				$("#pwd").attr("type", "text");
			}
			else{
				$("#pwd").attr("type", "password");
			}
		
			});
		});
	</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<form action="LoginServlet" method="post">
		<div class="login-content withbg">

			<!-- Login -->
			<div class="lc-block toggled " id="l-login">
				<div class="lcb-form hoverable ">
					<h5 class="text-centre strong">Login</h5>
					<div class="input-group m-b-20">
						<span class="input-group-addon" style="border-color: #ffffff;"></span>
						<div class="fg-line">
							<input type="text" class="form-control bold"
								placeholder="Username" name="adminEmail"
								value="${txtAdminEmail}"><label>${adminEmail}</label>
						</div>
					</div>

					<div class="input-group m-b-20">
						<span class="input-group-addon" style="border-color: #ffffff;"></span>
						<div class="fg-line">
							<input type="password" class="form-control" id="pwd"
								placeholder="Password" name="adminPassword" value=""><label>${adminPassword}</label>
						</div>
					</div>
					<div class="input-group m-b-20 fg-line	">
						<input type="checkbox" id="showHide" />
						<label for="showHide" id="showHideLabel" class="header">Show Password</label>
                    
					</div>
					<input type="submit" value="Sign In"
						class="btn waves-effect waves-light blue accent-4 hoverable white-text">
				</div>
				<!-- 
				<div class="lcb-navigation">
					<a href="forgotPassword.jsp" data-ma-action="login-switch"
						data-ma-block="#l-forget-password"
						class="hoverable blue accent-4 white-text "><i>?</i> <span>Forgot
							Password</span></a>
				</div> -->
			</div>

		</div>
	</form>


	<%@ include file="userFooter.jsp"%>

</body>
</html>
