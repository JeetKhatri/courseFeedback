<%@page import="courseFeedback.dao.ProgramDetailsDAO"%>
<%@page import="courseFeedback.bean.ProgramDetailsBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Course Selection</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<script type="text/javascript">
	function chngOne() {
		var val = document.getElementById('selProgramName').value;
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState === 4 && xhttp.status === 200) {
				document.getElementById('selSemName').innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("POST", "AjaxServlet2?valAjax1=" + val, true);
		xhttp.send();
	}
	function chngTwo() {
		var val = document.getElementById('selSemName').value;
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState === 4 && xhttp.status === 200) {
				document.getElementById('selCourseName').innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("POST", "AjaxServlet2?valAjax=" + val, true);
		xhttp.send();
	}
</script>
<script type="text/javascript" src="javaScript/mainscript.js"></script>
<link rel="icon" href="photos/daiict.png" />
</head>
<body>
	<%@ include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			Course <small>Selection</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Course</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
				<form action="GetCourseFeedbackServlet" method="post"
					name="Registration">
					<br />

					<div class="row">
						<label class="col-sm-2"> <font size="+1"> Select
								Program :</font>
						</label>
						<div class="col-lg-6">
							<select class="browser-default form-control" onChange="chngOne()"
								style="width: 100%;" name="selProgramName" id="selProgramName">
								<option value="0">--Select Program--</option>
								<%
									ArrayList<ProgramDetailsBean> list = (ArrayList<ProgramDetailsBean>) new ProgramDetailsDAO().list();
									for (ProgramDetailsBean bean : list) {
								%>
								<option value="<%=bean.getProgramDetailsId()%>"><%=bean.getProgramName()%>
								</option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<br />

					<div class="row">
						<label class="col-sm-2"> <font size="+1"> Select
								Year :</font>
						</label>
						<div class="col-lg-6">
							<select data-placeholder="Select a Batch" onChange="chngTwo()"
								style="width: 100%;" name="selSemName" id="selSemName"
								class="form-control">
								<option value="0">---select Batch Name---</option>
							</select>
						</div>
					</div>
					<br />

					<div class="row">
						<label class="col-sm-2"> <font size="+1"> Select
								Course :</font>
						</label>
						<div class="col-lg-6">
							<select data-placeholder="Select a Batch" style="width: 100%;"
								name="selCourseName" id="selCourseName" class="form-control">
								<option value="0">---select Course Name---</option>
							</select>
						</div>
					</div>
					<br /> <br />
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Get List" name="submit"
						class="btn btn-success">

				</form>
			</div>
		</div>
	</div>
</body>
</html>


<%-- 




<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function chngOne() {
		var val = document.getElementById('selProgramName').value;
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState === 4 && xhttp.status === 200) {
				document.getElementById('selSemName').innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("POST", "AjaxServlet2?valAjax1=" + val, true);
		xhttp.send();
	}
	function chngTwo() {
		var val = document.getElementById('selSemName').value;
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState === 4 && xhttp.status === 200) {
				document.getElementById('selCourseName').innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("POST", "AjaxServlet2?valAjax=" + val, true);
		xhttp.send();
	}
</script>

</head>
<body>
	<form action="GetCourseFeedbackServlet" method="post">
		<table>

			<tr>
				<td>Select Program</td>
				<td><select class="browser-default" onChange="chngOne()"
					style="width: 100%;" name="selProgramName" id="selProgramName">
						<option value="0">--Select Program--</option>
						<%
							ArrayList<ProgramDetailsBean> list = (ArrayList<ProgramDetailsBean>) new ProgramDetailsDAO().list();
							for (ProgramDetailsBean bean : list) {
						%>
						<option value="<%=bean.getProgramDetailsId()%>"><%=bean.getProgramName()%>
						</option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Select Year</td>
				<td><select data-placeholder="Select a Batch"
					onChange="chngTwo()" style="width: 100%;" name="selSemName"
					id="selSemName">
						<option value="0">---select Batch Name---</option>
				</select></td>
			</tr>
			<tr>
				<td>Select Course</td>
				<td><select data-placeholder="Select a Batch"
					style="width: 100%;" name="selCourseName" id="selCourseName">
						<option value="0">---select Course Name---</option>
				</select></td>
			</tr>
			<tr>
				<td>Click</td>
				<td><input type="submit" name="submit" value="Submit">
				</td>
			</tr>
		</table>
	</form>
</body>
</html> --%>