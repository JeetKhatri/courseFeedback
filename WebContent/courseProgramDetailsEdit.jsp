<%@page import="courseFeedback.bean.CourseDetailsBean"%>
<%@page import="courseFeedback.dao.CourseDetailsDAO"%>
<%@page import="courseFeedback.bean.ProgramDetailsBean"%>
<%@page import="java.util.List"%>
<%@page import="courseFeedback.dao.ProgramDetailsDAO"%>
<%@page import="courseFeedback.bean.CourseProgramDetailsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Program's Course Update</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<script type="text/javascript" src="javaScript/mainscript.js"></script>
<link rel="icon" href="photos/daiict.png" />
</head>
<body>
	<%
		CourseProgramDetailsBean courseProgramDetailsBean = (CourseProgramDetailsBean) request
				.getAttribute("courseProgramDetailsBean");

		if (courseProgramDetailsBean != null) {
	%>
	<%@ include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			Program's Course <small>Update</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Program's Course</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
				<form action="CourseProgramDetailsUpdateServlet" method="post"
					name="Registration">
					<br /> <input type="hidden" name="courseProgramId"
						id="courseProgramId"
						value="<%=courseProgramDetailsBean.getCourseProgramId()%>">

					<div class="row">
						<label class="col-sm-2"> <font size="+1">Course :</font>
						</label>
						<div class="col-lg-6">
							<select name="seltermCourseId" class="form-control">
								<option value="0 0" selected="selected">Select Course
									Code - Name</option>
								<%
									CourseDetailsDAO courseDetailsDAO = new CourseDetailsDAO();

										List<CourseDetailsBean> detailsBeansList1 = courseDetailsDAO.select();

										for (int i = 0; i < detailsBeansList1.size(); i++) {
											CourseDetailsBean detailsBean = detailsBeansList1.get(i);
											if ((detailsBean.getTermCourseId() + "").equals(courseProgramDetailsBean.getTermCourseId())) {
								%>
								<option value="<%=detailsBean.getTermCourseId()%>"
									selected="selected"><%=detailsBean.getCourseCode() + "   " + detailsBean.getCourseName()%></option>
								<%
									} else {
								%>
								<option value="<%=detailsBean.getTermCourseId()%>"><%=detailsBean.getCourseCode() + " " + detailsBean.getCourseName()%></option>
								<%
									}
										}
								%>
							</select> <font color="red">${termCourseId}</font>
						</div>
					</div>
					<br />

					<div class="row">
						<label class="col-sm-2"> <font size="+1">Program :</font>
						</label>
						<div class="col-lg-6">
							<select name="selProgramDetailsId" class="form-control">
								<option value="0" selected="selected">Select Program</option>
								<%
									ProgramDetailsDAO programDetailsDAO = new ProgramDetailsDAO();

										List<ProgramDetailsBean> detailsBeansList = programDetailsDAO.list();

										for (int i = 0; i < detailsBeansList.size(); i++) {
											ProgramDetailsBean detailsBean = detailsBeansList.get(i);
											if (detailsBean.getProgramDetailsId().equals(courseProgramDetailsBean.getProgramDetailsId())) {
								%>
								<option value="<%=detailsBean.getProgramDetailsId()%>"
									selected="selected"><%=detailsBean.getProgramName()%></option>
								<%
									} else {
								%>
								<option value="<%=detailsBean.getProgramDetailsId()%>"><%=detailsBean.getProgramName()%></option>
								<%
									}
										}
								%>
							</select> <font color="red">${programDetailsId}</font>
						</div>
					</div>
					<br />

					<div class="row">

						<%
							String isAvailable = courseProgramDetailsBean.getIsAvailable() + "";
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








