<%@page import="courseFeedback.bean.RemoveSpecialQuestionBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Remove Special Question List</title>
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
			Remove Special Questions <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Remove Special Questions</li>
		</ol>
		<br>
		<br>
		<a href="removeSpecialQuestionInsert.jsp"><input type="button"
			value="ADD" name="ADD" class="btn btn-primary"></a> <br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">

							<%
								List<RemoveSpecialQuestionBean> listOfRemoveSpecialQuestionBean = (ArrayList) request
										.getAttribute("listOfRemoveSpecialQuestionBean");
								if (listOfRemoveSpecialQuestionBean != null) {
							%><thead class="gujju-theme text-uppercase">
								<tr>

									<th><center>Year Id</center></th>
									<th><center>Term Id</center></th>
									<th><center>Question Content</center></th>
									<th><center>CourseName</center></th>
									<th><center>CourseCode</center></th>
									<th><center>Action</center></th>
								</tr>

								<%
									for (int i = 0; i < listOfRemoveSpecialQuestionBean.size(); i++) {
											RemoveSpecialQuestionBean removeSpecialQuestionBean = listOfRemoveSpecialQuestionBean.get(i);
								%>
							</thead>
							<tbody>
								<tr>

									<td align="center"><%=removeSpecialQuestionBean.getYearId()%></td>
									<td align="center"><%=removeSpecialQuestionBean.getTermId()%></td>
									<td align="center"><textarea disabled="disabled" rows="2" cols="40"><%=removeSpecialQuestionBean.getQuestionContent()%></textarea></td>
									<td align="center"><%=removeSpecialQuestionBean.getCourseName()%></td>
									<td align="center"><%=removeSpecialQuestionBean.getCourseCode()%></td>
									<td align="center"><a
										href="RemoveSpecialQuestionDeleteServlet?questionIdCourseProgramId=<%=removeSpecialQuestionBean.getQuestionId()%>-<%=removeSpecialQuestionBean.getTermCourseId()%>"><img
											src="photos/Recycle Bin.ico" height="30" width="30"
											class="img-rounded" /></a> </a></td>

								</tr>
								<%
									}
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