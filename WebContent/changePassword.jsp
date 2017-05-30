<%@page import="courseFeedback.bean.AdminBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Change Password</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<script type="text/javascript" src="javaScript/mainscript.js"></script>
<link rel="icon" href="photos/daiict.png" />
</head>
<body>
<%
	AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
	if(adminBean!=null){
%>
<%@ include file="adminHeader.jsp"%>
<div style="margin-top: -850px; margin-left: 250px;">
	<section class="content-header">
	<h1>
		Password <small>Update</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
				Home</a></li>
		<li class="active">Password</li>
	</ol>
	</section>
	<br> <br>
	<div class="col-lg-6">
		<div class="container">
			<form action="ChangePasswordServlet" method="post" name="Registration">
			<input type="hidden" name="adminId" value="<%=adminBean.getAdminId()%>">
				<br />
				<div class="row">
					<label class="col-sm-2"> <font size="+1">Old PassWord :</font>
					</label>

					<div class="col-lg-6">
						<input type="password" class="form-control" name="oldPassword"/> <font color="red">${oldPassword}
						</font>
					</div>
				</div>
				<br />
				
				<div class="row">
					<label class="col-sm-2"> <font size="+1">New PassWord :</font>
					</label>

					<div class="col-lg-6">
						<input type="password" class="form-control" name="newPassword"/> <font color="red">${newPassword}
						</font>
					</div>
				</div>
				<br />
				
				<div class="row">
					<label class="col-sm-2"> <font size="+1">confirm PassWord :</font>
					</label>

					<div class="col-lg-6">
						<input type="password" class="form-control" name="confirmPassword"/> <font color="red">${confirmPassword}
						</font>
					</div>
				</div>
				<br />

				
				<br /> 
					<label class="col-sm-2 control-label"></label>
					<input type="reset" value="Reset" name="reset" class="btn  btn-danger">
						&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Ragister" name="submit" class="btn btn-success">
			</form>
		</div>
	</div>
</div>
<%}else{
	request.setAttribute("msgLogin", "Please Login To Continue");
	response.sendRedirect("login.jsp");
}%>
</body>
</html>







