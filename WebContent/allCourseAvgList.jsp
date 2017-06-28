<%@page import="courseFeedback.dao.TermDetailsDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="courseFeedback.bean.CourseQueestionAVGBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Average List</title>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="icon" href="photos/daiict.png" />
<style>
td, tr, th {
	text-transform: uppercase;
}
</style>
</head>
<body>
	<%@include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content content-header">
		<h1>
			Course Average <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Average</li>
		</ol>
		<br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">
							<%
								ArrayList<CourseQueestionAVGBean> courseQueestionAVGBeans = (ArrayList<CourseQueestionAVGBean>) request
										.getAttribute("courseQuestionAvg");
								if (courseQueestionAVGBeans != null) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>
									<th><center>Year - Term</center></th>
									<th><center>Course Name</center></th>
									<th><center>Course Code</center></th>
									<th><center>Average</center></th>
									<th><center>Question</center></th>
									<th><center>show Feedback</center></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < courseQueestionAVGBeans.size(); i++) {
								%>
								<tr>
								<%
								String yearName = new TermDetailsDAO().getYearName(courseQueestionAVGBeans.get(i).getYearId());
								String termName = new TermDetailsDAO().getTermName(courseQueestionAVGBeans.get(i).getTermId());
								
								%>
									<td align="center"><%=yearName%>&emsp;&emsp;  <%=termName%></td>
									<td align="left"><%=courseQueestionAVGBeans.get(i).getCourseName()%></td>
									<td align="center"><%=courseQueestionAVGBeans.get(i).getCourseCode()%></td>
									<td align="center"><%=courseQueestionAVGBeans.get(i).getAvg()%></td>
									<td align="center"><a class="btn btn-primary"
										href="CourseQuestionAvgServlet?yearId=<%=courseQueestionAVGBeans.get(i).getYearId()%>&code=<%=courseQueestionAVGBeans.get(i).getCourseCode()%>&termId=<%=courseQueestionAVGBeans.get(i).getTermId()%>">Question
											Avg</a></td>
									<td align="center"><a class="btn btn-primary"
										href="GetCourseFeedbackServlet?yearId=<%=courseQueestionAVGBeans.get(i).getYearId()%>&code=<%=courseQueestionAVGBeans.get(i).getCourseCode()%>&termId=<%=courseQueestionAVGBeans.get(i).getTermId()%>">Feedback</a></td>
								</tr>

								<%
									}
									} else {
								%>

								<h1>
									<center>No Record Found....!</center>
								</h1>
								<%
									}
								%>

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		</section>
	</div>
</body>
</html>
