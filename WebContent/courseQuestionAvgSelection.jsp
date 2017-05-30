<%@page import="courseFeedback.bean.FeedbackBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="courseFeedback.dao.FeedbackDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Course Wise Question Avg</title>
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
			Course Wise <small> Question Average</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Average</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
				<form action="CourseQuestionAvgServlet" method="post"
					name="Registration">
					<br /><br /><br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Select
								Course : </font>
						</label>
						<div class="col-lg-6">
							<select name="courseCode" class="form-control">
								<option value="0 0">Select Course Name</option>
								<%
									ArrayList<FeedbackBean> listOfFeedbackBean = new FeedbackDAO().uniqueCourse();
									for (FeedbackBean fb : listOfFeedbackBean) {
								%>
								<option value="<%=fb.getCourseCode()%>"><%=fb.getCourseName() + " - " + fb.getCourseCode()%></option>
								<%
									}
								%>
							</select> 
						</div>
					</div>
					<br /> <br />
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Check" name="submit"
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
</head>
<body>

	<form action="CourseQuestionAvgServlet">

		<table>

			<tr>
				<td>Select Subject</td>
				<td><select name="courseCode">
						<%
							ArrayList<FeedbackBean> listOfFeedbackBean = new FeedbackDAO().uniqueCourse();
							for (FeedbackBean fb : listOfFeedbackBean) {
						%>
						<option value="<%=fb.getCourseCode()%>"><%=fb.getCourseName() + " - " + fb.getCourseCode()%></option>
						<%
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Check"></td>
			</tr>

		</table>

	</form>

	<form action="AllCourseAvgServlet">
		<input type="submit" value="CheckAllCourseAvg">
	</form>

	<form action="UgPgAvgServlet">
		<input type="submit" value="UgPgAvg">
	</form>

	<form action="UgPgQuestionAvgServlet">
		<input type="submit" value="UgPgQuestionAvg">
	</form>

</body>
</html> --%>