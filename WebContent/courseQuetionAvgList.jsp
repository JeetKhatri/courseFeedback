<%@page import="courseFeedback.bean.CourseQueestionAVGBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Course List</title>
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
			Course Question Average <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Average</li>
		</ol>
		<br>
		<br>
		<%
			ArrayList<CourseQueestionAVGBean> courseQueestionAVGBeans1 = (ArrayList<CourseQueestionAVGBean>) request
					.getAttribute("courseQuestionAvg");
		%>
		<center><h4>
			YearId :
			<b><%=courseQueestionAVGBeans1.get(0).getYearId()%></b>
			&emsp;TermId :
			<b><%=courseQueestionAVGBeans1.get(0).getTermId()%></b>
			&emsp;Course Name :
			<b><%=courseQueestionAVGBeans1.get(0).getCourseName()%></b>
			&emsp;Course Code :
			<b><%=courseQueestionAVGBeans1.get(0).getCourseCode()%></b>
		</h4></center>

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
									<th><center>Question Number</center></th>
									<th><center>Question Content</center></th>
									<th><center>Average</center></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < courseQueestionAVGBeans.size(); i++) {
								%>

								<tr>

									<td align="center"><%=courseQueestionAVGBeans.get(i).getQuestionNumber()%></td>
									<td align="center"><textarea rows="2" cols="70"><%=courseQueestionAVGBeans.get(i).getQuestionContent()%></textarea></td>
									<td align="center"><%=courseQueestionAVGBeans.get(i).getAvg()%></td>
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

