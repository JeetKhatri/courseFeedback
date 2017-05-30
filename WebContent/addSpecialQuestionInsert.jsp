<%@page import="courseFeedback.bean.CourseDetailsBean"%>
<%@page import="courseFeedback.dao.CourseDetailsDAO"%>
<%@page import="courseFeedback.dao.CourseProgramDetailsDAO"%>
<%@page import="courseFeedback.bean.CourseProgramDetailsBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Special Question Insert</title>
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
			Special Question <small>Insert</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li><a href="QuestionsListServlet"><i
					class="fa fa-question-circle"></i> Question</a></li>
			<li class="active">SpecialQuestion</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
				<form action="AddSpecialQuestionInsertServlet" method="post"
					name="Registration">
					<br />

					<div class="row">
						<label class="col-sm-2"> <font size="+1">Course :</font>
						</label>
						<div class="col-lg-6">
							<select name="cmbCourseProgram" class="form-control">
								<option value="0">Select Course</option>
								<%
									CourseDetailsDAO courseDetailsDAO = new CourseDetailsDAO();
									List<CourseDetailsBean> courseDetailsList = courseDetailsDAO.select();

									for (int i = 0; i < courseDetailsList.size(); i++) {

										String tmp = "unselected";
										String type = request.getParameter("cmbCourseProgram");
										if (type == null)
											tmp = "unselected";
										else if ((courseDetailsList.get(i).getTermCourseId() + "").equals(type))
											tmp = "selected";
								%>

								<option value=<%=courseDetailsList.get(i).getTermCourseId()%>
									<%=tmp%>>
									<%=courseDetailsList.get(i).getCourseCode() + " " + courseDetailsList.get(i).getCourseName()%></option>
								<%
									}
								%>

							</select> <font color="red">${courseProgramId}</font>
						</div>
					</div>
					<br />

					<div class="row">
						<label class="col-sm-2"> <font size="+1">Question
								Content :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtQuestionContent"
								placeholder="Question" maxlength="255"
								value="${txtQuestionContent}" /> <font color="red">${questionContent}
							</font>
						</div>
					</div>
					<br />

					<div class="row">

						<%
							String t = (String) (request.getAttribute("isT")), l = (String) (request.getAttribute("isL")),
									p = (String) (request.getAttribute("isP"));
						%>

						<label class="col-sm-3"><font size="+1">Question
								Type : </font> </label>
						<div class="col-md-7">
							<div class="col-sm-3">
								<%
									if (l != null && l.equals("1")) {
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
									if (t != null && t.equals("1")) {
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
									if (p != null && p.equals("1")) {
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
							String type = request.getParameter("ansType"), radio = "", textarea = "";
							if (type != null) {

								if (type.equals("radio"))
									radio = "checked";
								else
									textarea = "checked";
							} else {
								radio = "checked";
							}
						%>
						<label class="col-sm-3"><font size="+1">Answer Type
								: </font> </label>
						<div class="col-md-7">
							<div class="col-sm-6">
								<input type="radio" class="radio" name="ansType" value="radio"
									<%=radio%> />Radio button
							</div>
							<div class="col-sm-6">
								<input type="radio" class="radio" name="ansType" <%=textarea%>
									value="textarea" /> Textbox
							</div>
						</div>
					</div>


					<br /> <br /> <br /> <input type="reset" value="Reset"
						name="reset" class="btn  btn-danger">
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Insert" name="submit"
						class="btn btn-success">

				</form>
			</div>
		</div>
	</div>
</body>
</html>

