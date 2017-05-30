<%@page import="courseFeedback.bean.TermDetailsBean"%>
<%@page import="java.util.List"%>
<%@page import="courseFeedback.dao.TermDetailsDAO"%>
<%@page import="courseFeedback.bean.CourseDetailsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Course Update</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<script type="text/javascript" src="javaScript/mainscript.js"></script>
<link rel="icon" href="photos/daiict.png" />
</head>
<body>
	<%
		CourseDetailsBean courseDetailsBean = (CourseDetailsBean) request.getAttribute("courseDetailsBean");

		if (courseDetailsBean != null) {
	%>
	<%@ include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			Course <small>Update</small>
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
				<form action="CourseDetailsUpdateServlet" method="post"
					name="Registration">
					<br /> <input type="hidden" name="termCourseId"
						value=<%=courseDetailsBean.getTermCourseId()%>>
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Year-Term
								:</font>
						</label>
						<div class="col-lg-6">
							<select name="cmbYearTerm" class="form-control">
								<option value="0 0">Select Year and Term</option>
								<%
									List<TermDetailsBean> listOfTermDetails = new TermDetailsDAO().list();

										if (listOfTermDetails != null) {
											for (TermDetailsBean t : listOfTermDetails) {
												String s = t.getYearName() + " " + t.getTermName();
												String temp = t.getYearId() + " " + t.getTermId();
												if (temp.equals(courseDetailsBean.getYearId() + " " + courseDetailsBean.getTermId())) {
								%>
								<option value="<%=t.getYearId() + " " + t.getTermId()%>"
									selected="selected"><%=s%></option>
								<%
									} else {
								%>
								<option value="<%=t.getYearId() + " " + t.getTermId()%>"><%=s%></option>
								<%
									}
											}
										}
								%>
							</select> <font color="red">${cmbYearTerm}</font>
						</div>
					</div>
					<br />

					<div class="row">
						<label class="col-sm-2"> <font size="+1">Course
								Name :</font>
						</label>

						<%
							String courseName;
								if (courseDetailsBean.getCourseName() == null) {
									courseName = "";
								} else {
									courseName = courseDetailsBean.getCourseName();
								}
						%>

						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtCourseName"
								placeholder="course name" maxlength="30" value="<%=courseName%>" />
							<font color="red">${courseName} </font>
						</div>
					</div>
					<br />

					<div class="row">
						<label class="col-sm-2"> <font size="+1">Course
								Code :</font>
						</label>
						<%
							String courceCode;
								if (courseDetailsBean.getCourseCode() == null) {
									courceCode = "";
								} else {
									courceCode = courseDetailsBean.getCourseCode();
								}
						%>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtCourseCode"
								placeholder="IT619" maxlength="30" value="<%=courceCode%>" /> <font
								color="red">${courseCode} </font>
						</div>
					</div>
					<br />

					<div class="row">
						<label class="col-sm-2"> <font size="+1">Course
								Credit</font>
						</label>
						<div class="col-lg-6">
							<select name="cmbCredit" class="form-control">
								<%
									if (courseDetailsBean.getCredit() == 3.0) {
								%>
								<option value="3.0" selected="selected">3.0</option>
								<%
									} else {
								%>
								<option value="3.0">3.0</option>
								<%
									}
										if (courseDetailsBean.getCredit() == 4.0) {
								%>
								<option value="4.0" selected="selected">4.0</option>
								<%
									} else {
								%>
								<option value="4.0">4.0</option>
								<%
									}
										if (courseDetailsBean.getCredit() == 5.0) {
								%>
								<option value="5.0" selected="selected">5.0</option>
								<%
									} else {
								%>
								<option value="5.0">5.0</option>
								<%
									}
								%>
							</select>
						</div>
					</div>
					<br />

					<div class="row">

						<%
							String t = courseDetailsBean.getT() + "", l = courseDetailsBean.getL() + "",
										p = courseDetailsBean.getP() + "";
						%>

						<label class="col-sm-3"><font size="+1">Question
								Type : </font> </label>
						<div class="col-md-7">
							<div class="col-sm-3">

								<%
									if (l != null && l.equals("1.0")) {
								%>
								<input type="checkbox" name="rdoType" value="L" checked="<%=l%>" />Lecture
								<%
									} else {
								%>
								<input type="checkbox" name="rdoType" value="L" />Lecture
								<%
									}
								%>
							</div>
							<div class="col-sm-3">
								<%
									if (t != null && t.equals("1.0")) {
								%>
								<input type="checkbox" name="rdoType" value="T" checked="<%=t%>" />Tutorial
								<%
									} else {
								%>
								<input type="checkbox" name="rdoType" value="T" />Tutorial
								<%
									}
								%>
							</div>
							<div class="cal-sm-3">
								<%
									if (p != null && p.equals("1.0")) {
								%>
								<input type="checkbox" name="rdoType" value="P" checked="<%=p%>" />Prectical
								<%
									} else {
								%>
								<input type="checkbox" name="rdoType" value="P" />Prectical
								<%
									}
								%>
							</div>
						</div>
					</div>
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<font color="red">${questionType} </font> <br />

					<div class="row">

						<%
							String isAvailable = (String) (courseDetailsBean.getIsAvailable() + "");
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
								<input type="radio" class="radio" name="isAvailable" value="1" <%=yes%> />Yes
							</div>
							<div class="col-sm-6">
								<input type="radio"  class="radio" name="isAvailable" value="0" <%=no%> />No
							</div>
						</div>
						
					</div>
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<font color="red">${questionType} </font> <br /> <br /> <br /> <br />
					<label class="col-sm-2 control-label"></label> <input type="reset"
						value="Reset" name="reset" class="btn  btn-danger">
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Update" name="submit"
						class="btn btn-success">

				</form>
			</div>
		</div>
	</div>
	<%
		} else {

			request.setAttribute("msgLogin", "Please Login To Continue");
			response.sendRedirect("login.jsp");

		}
	%>
</body>
</html>

















