<%@page import="courseFeedback.bean.FeedbackBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Feedback List</title>

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
			Feedback <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Feedback</li>
		</ol>
		<br>
		<br>
		<!-- <a href="adminInsert.jsp"><input type="button" value="ADD"
			name="ADD" class="btn btn-primary"></a> --> <br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">
							<%
								ArrayList<FeedbackBean> listOfFeedbackBean = (ArrayList<FeedbackBean>) request
										.getAttribute("listOfFeedbackBean");
								if (listOfFeedbackBean != null) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>
									<th><center>YearId</center></th>
									<th><center>TermId</center></th>
									<th><center>Student Id</center></th>
									<th><center>QuestionId</center></th>
									<th><center>QuestionContent</center></th>
									<th><center>FeedbackContent</center></th>
									<th><center>CourseCode</center></th>
									<th><center>CourseName</center></th>
								</tr>
							</thead>


							<tbody>
								<%
									for (FeedbackBean f : listOfFeedbackBean) {
								%>
								<tr>

									<td><center><%=f.getYearId()%></center></td>
									<td><center><%=f.getTermId()%></center></td>
									<td><center><%=f.getStudentdetailsId()%></center></td>
									<td><center><%=f.getQuestionId()%></center></td>
									<td><center>
											<textarea disabled="disabled"><%=f.getQuestionContent()%></textarea>
										</center></td>
									<td><center>
											<textarea disabled="disabled"><%=f.getFeedbackContent()%></textarea>
										</center></td>
									<td><center><%=f.getCourseCode()%></center></td>
									<td><center><%=f.getCourseName()%></center></td>
									<%
										}
									%>
								</tr>

								<%
									}

									else {
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