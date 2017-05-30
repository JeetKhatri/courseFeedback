<%-- <%@page import="courseFeedback.bean.ProgramDetailsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Program Edit</title>
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
			Program <small>Edit</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Program</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
				<%
					ProgramDetailsBean programDetailsBean = (ProgramDetailsBean) request.getAttribute("programDetailsBean");
					if (programDetailsBean != null) {
						String programName = programDetailsBean.getProgramName();
						if (programName == null)
							programName = "";
				%>

				<form action="ProgramDetailsUpdateServlet" method="post"
					name="Registration">
					<br /> <input type="hidden" name="programId"
						value="<%=request.getParameter("programDetailsId")%>" />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Program
								Name :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtProgramName"
								placeholder="Program Name" maxlength="30"
								value="<%=programName%>" /> <font color="red">${programName}
							</font>
						</div>
					</div>
					<br />
					<div class="row">

						<%
							String isAvailable = (String) (programDetailsBean.getIsAvailable() + "");
								String yes = "", no = "";
								if (isAvailable.equals("1"))
									yes = "checked";
								else
									no = "checked";
						%>

						<label class="col-sm-3"><font size="+1">Is
								Available : </font> </label>
						<div class="col-md-7">
							<div class="col-sm-6">
								<input type="radio" class="radio" name="isAvailable" value="1"
									<%=yes%> />Yes
							</div>
							<div class="col-sm-6">
								<input type="radio" class="radio" name="isAvailable" value="0"
									<%=no%> />No
							</div>
						</div>

					</div>
					<br /> <br /> <label class="col-sm-2 control-label"></label> <input
						type="reset" value="Reset" name="reset" class="btn  btn-danger">
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Insert" name="submit"
						class="btn btn-success">

				</form>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>

 --%>
<%@page import="courseFeedback.bean.ProgramDetailsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Program Edit</title>
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
			Program <small>Edit</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Program</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
				<%
					ProgramDetailsBean programDetailsBean = (ProgramDetailsBean) request.getAttribute("programDetailsBean");
					if (programDetailsBean != null) {
						String programName = programDetailsBean.getProgramName();
						if (programName == null)
							programName = "";
				%>

				<form action="ProgramDetailsUpdateServlet" method="post"
					name="Registration">
					<br /> <input type="hidden" name="programId"
						value="<%=request.getParameter("programDetailsId")%>" />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Program
								Name :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtProgramName"
								placeholder="Program Name" maxlength="30"
								value="<%=programName%>" /> <font color="red">${programName}
							</font>
						</div>
					</div>
					<br />
					<div class="row">

						<%
							String isAvailable = (String) (programDetailsBean.getIsAvailable() + "");
								String yes = "", no = "";
								if (isAvailable.equals("1"))
									yes = "checked";
								else
									no = "checked";
						%>

						<label class="col-sm-3"><font size="+1">Is
								Available : </font> </label>
						<div class="col-md-7">
							<div class="col-sm-6">
								<input type="radio" class="radio" name="isAvailable" value="1"
									<%=yes%> />Yes
							</div>
							<div class="col-sm-6">
								<input type="radio" class="radio" name="isAvailable" value="0"
									<%=no%> />No
							</div>
						</div>

					</div>
					<br /> <br /> <label class="col-sm-2 control-label"></label> <input
						type="reset" value="Reset" name="reset" class="btn  btn-danger">
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Update" name="submit"
						class="btn btn-success">

				</form>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>