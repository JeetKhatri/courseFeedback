<%@page import="courseFeedback.bean.SemesterProgramDetailsBean"%>
<%@page import="courseFeedback.dao.SemesterProgramDetailsDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="courseFeedback.bean.CourseProgramDetailsBean"%>
<%@page import="java.util.List"%>
<%@page import="courseFeedback.dao.CourseProgramDetailsDAO"%>
<%@page import="courseFeedback.dao.CourseDetailsDAO"%>
<%@page import="courseFeedback.bean.DateProgramDetailsBean"%>
<%@page import="courseFeedback.dao.DateProgramDetailsDAO"%>
<%@page import="courseFeedback.dao.ProgramDetailsDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="courseFeedback.bean.ProgramDetailsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Course</title>
</head>
<script src="javaScript/jQuery-2.1.4.min.js"></script>
<script src="javaScript/bootstrap.min.js"></script>
<script src="javaScript/select2/select2.full.min.js"></script>
<link rel="stylesheet" href="css/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="css/select2.min.css">
<link rel="stylesheet" href="css/AdminLTE.min.css">

<script type="text/javascript">
	function chngOne() {
		var val = document.getElementById('selSemName').value;
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState === 4 && xhttp.status === 200) {
				document.getElementById('selCourseName').innerHTML = xhttp.responseText;
			}
		};
		xhttp.open("POST", "AjaxServlet?valAjax1=" + val, true);
		xhttp.send();
	}
</script>
<script>
	$(function() {

		//Initialize Select2 Elements
		$(".select2").select2();
	});
</script>
<script type="text/javascript">
	function alertBox1() {
		/* var k = $('selectCourseName').val; */
		/* alert(k); */

		var str = "", i;
		var cnt = 0;
		for (i = 0; i < myForm.selCourseName.options.length; i++) {
			if (myForm.selCourseName.options[i].selected) {

				++cnt;
			}
		}
		if (cnt == 9) {
			
			var r = confirm("You have selected " + cnt
					+ " Courses\n Do you want to continue??");
			if (r == false) {
				
			} else {
				document.getElementById("myForm").submit();
			}

		} else if (cnt >= 10) {
			alert("Sorry...!!\nYou cannot select more than 9 Courses...!");
		}

		else {
			var r = confirm("You have selected Only " + cnt
					+ " Courses \nDo You want to Continue...?");
			if (r == false) {
				
			} else {
				document.getElementById("myForm").submit();
			}

		}
	}

	/* var temp = document.getElementById("selCourseName").value;		
	alert(temp); */
</script>
<link rel="icon" href="photos/daiict.png" />
<body>
	<%@ include file="userHeader.jsp"%>
	<div class="section" id="index-banner">
		<div class="container">
			<div class="row">
				<form class="col s12" action="FeedbackCourseSelectedServlet"
					name="myForm" id="myForm" method="post">
					<br /> 

					<h4>
						<center><%=session.getAttribute("programDetailsName")%></center>
					</h4>
					<br />
					<h5 style="color: red">
						<center>Select all Courses that you have registered for
							in this semester</center>
					</h5>
					<br />
					<div class="input-field col s6">
						<label class="card-title black-text">Select Batch</label>
					</div>
					<div class="input-field col s6">

						<select class="browser-default" onChange="chngOne()"
							style="width: 100%;" name="selSemName" id="selSemName">
							<option value="0">--Select Batch--</option>
							<%
								String programDetailsId = (String) session.getAttribute("programDetailsId");
								ArrayList<SemesterProgramDetailsBean> list = (ArrayList<SemesterProgramDetailsBean>) new SemesterProgramDetailsDAO()
										.list(programDetailsId);
								for (SemesterProgramDetailsBean bean : list) {
							%>
							<option value="<%=bean.getSemId()%>"><%=bean.getBatchName()%>
							</option>
							<%
								}
							%>
						</select>
					</div>
					<div class="input-field col s6">
						<label class="card-title black-text">Select Courses</label>
					</div>
					<div class="input-field col s6">
						<label>Course Name</label> <select class="form-control select2"
							multiple="multiple" data-placeholder="Select a Course"
							style="width: 100%;" name="selCourseName" id="selCourseName">
							<option value="0">---select Course Name---</option>
						</select>
						<h3></h3>${selectMultiCourse}
					</div>
					<div class="input-field col s12">
							<center>
							<input type="button" onClick="alertBox1()"
								class="btn waves-effect waves-light blue hoverable"
								value="Start Feedback">
						</center>
					</div>
				</form>
			</div>
		</div>
	</div>
	<br />
	<br />
	<%@ include file="userFooter.jsp"%>
</body>
</html>