<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Admin Insert</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<script type="text/javascript" src="javaScript/mainscript.js"></script>
<link rel="icon" href="photos/daiict.png" />
</head>
<body>
<%@ include file="adminHeader.jsp"%>
<div style="margin-top: -850px; margin-left: 250px;">
	<section class="content-header">
	<h1>
		Admin <small>Insert</small>
	</h1>
	<ol class="breadcrumb">
		<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
				Home</a></li>
		<li class="active">Admin</li>
	</ol>
	</section>
	<br> <br>
	<div class="col-lg-6">
		<div class="container">
			<form action="AdminInsertServlet" method="get" name="Registration">
				<br />
				<div class="row">
					<label class="col-sm-2"> <font size="+1">Email :</font>
					</label>

					<div class="col-lg-6">
						<input type="text" class="form-control" name="txtAdminEmail"
							placeholder="example@daiict.ac.in" maxlength="30"
							value="${txtAdminEmail}" /> <font color="red">${adminEmail}
						</font>
					</div>
				</div>
				<br />

				<div class="row">
					<label class="col-sm-2"> <font size="+1">Password :</font>
					</label>
					<div class="col-lg-6">
						<input type="password" class="form-control"
							name="txtAdminPassword" value="" placeholder="Create Password"
							maxlength="30" /> <font color="red">${adminPassword} </font>
					</div>
				</div>
				<br /> 
				<br /> 
					<label class="col-sm-2 control-label"></label>
					<input type="reset" value="Reset" name="reset" class="btn  btn-danger">
						&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Add" name="submit" class="btn btn-success">
			</form>
		</div>
	</div>
</div>
</body>
</html>


