<%@page import="courseFeedback.bean.CourseDetailsBean"%>
<%@page import="courseFeedback.dao.CourseDetailsDAO"%>
<%@page import="courseFeedback.dao.QuestionsDAO"%>
<%@page import="courseFeedback.bean.QuestionsBean"%>
<%@page import="java.util.List"%>
<%@page import="courseFeedback.dao.CourseProgramDetailsDAO"%>
<%@page import="courseFeedback.bean.CourseProgramDetailsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Remove Question</title>
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
			Question <small>Remove</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Question</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
				<form action="RemoveSpecialQuestionInsertServlet" method="post"
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
						<label class="col-sm-2"> <font size="+1">Question :</font>
						</label>
						<div class="col-lg-6">
							<select name="cmbQuestion" class="form-control">
								<option value="0">Select Question</option>
								<%
									List<QuestionsBean> listOfQuestions = new QuestionsDAO().listForRemoveQuestions();
									if (listOfQuestions != null) {

										for (QuestionsBean bean : listOfQuestions) {
											if (bean.getQuestionId().equals(request.getAttribute("cmbQuestionId"))) {
								%>
								<option value="<%=bean.getQuestionId()%>" selected="selected"><%=bean.getQuestionContent()%>
									<%
										} else {
									%>
								
								<option value="<%=bean.getQuestionId()%>"><%=bean.getQuestionContent()%>
									<%
										}
											}

										}
									%>
								
							</select> <font color="red">${questionId}</font>
						</div>
					</div>
					<br /> <label class="col-sm-2 control-label"></label> <input
						type="reset" value="Reset" name="reset" class="btn  btn-danger">
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Remove" name="submit"
						class="btn btn-success">

				</form>
			</div>
		</div>
	</div>
</body>
</html>



