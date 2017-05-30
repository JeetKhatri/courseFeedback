<%@page import="courseFeedback.bean.CourseDetailsBean"%>
<%@page import="courseFeedback.bean.AdminBean"%>
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
			Course <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Course Details</li>
		</ol>
		<br>
		<br>
		<a href="courseDetailsInsert.jsp"><input type="button" value="ADD"
			name="ADD" class="btn btn-primary"></a> <br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">
							<%
								ArrayList<CourseDetailsBean> listofCourse = (ArrayList) request.getAttribute("listofCourse");
								if (listofCourse != null) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>
									<th><center>YearId</center></th>
									<th><center>TermId</center></th>
									<th><center>Course Name</center></th>
									<th><center>Course Code</center></th>
									<th><center>Course Credit</center></th>
									<th><center>Lecture</center></th>
									<th><center>Tutorial</center></th>
									<th><center>Prectical</center></th>
									<th><center>Is Available</center></th>
									<th><center>Action</center></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < listofCourse.size(); i++) {
											CourseDetailsBean courseDetailsBean = listofCourse.get(i);
								%>

								<tr>
									<td align="center"><%=courseDetailsBean.getYearId()%></td>
									<td align="center"><%=courseDetailsBean.getTermId()%></td>
									<td align="center"><%=courseDetailsBean.getCourseName()%></td>
									<td align="center"><%=courseDetailsBean.getCourseCode()%></td>
									<td align="center"><%=courseDetailsBean.getCredit()%></td>
									<td align="center"><%=(int) courseDetailsBean.getL()%></td>
									<td align="center"><%=(int) courseDetailsBean.getT()%></td>
									<td align="center"><%=(int) courseDetailsBean.getP()%></td>
									<%
										if (courseDetailsBean.getIsAvailable()==0) {
									%>
									<td align="center"><img src="photos/no1.jpg" height="30"
										width="30" class="img-rounded" /></td>
									<%
										} else {
									%>
									<td align="center"><img src="photos/yes1.jpg" height="30"
										width="30" class="img-rounded" /></td>
									<%
										}
									%>
									
									<td align="center"><a
										href="CourseDetailsEditServlet?termCourseDetailsId=<%=courseDetailsBean.getTermCourseId()%>"><img
											src="photos/edit.ico" height="30" width="30"
											class="img-rounded" /></a>&nbsp;&nbsp;
										<a
										href="CourseDetailsDeleteServlet?termCourseDetailsId=<%=courseDetailsBean.getTermCourseId()%>"><img
											src="photos/Recycle Bin.ico" height="30" width="30"
											class="img-rounded" /></a>
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