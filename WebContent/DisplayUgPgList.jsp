<%@page import="courseFeedback.bean.UGPGAvgBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | UG/PG List</title>
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
			UG/PG <small>List</small>
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
								ArrayList<UGPGAvgBean> bean = (ArrayList<UGPGAvgBean>) request.getAttribute("courseQuestionAvg");
								if (bean != null) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>
									<th><center>YearId</center></th>
									<th><center>TermId</center></th>
									<th><center>Type</center></th>
									<th><center>Average</center></th>
									<th><center>Action</center></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (UGPGAvgBean u : bean) {
								%>

								<tr>
									<td align="center"><%=u.getYearId()%></td>
									<td align="center"><%=u.getTermId()%></td>
									<td align="center"><%=u.getType()%></td>
									<td align="center"><%=u.getAvg()%></td>
									<td align="center"><a class="btn btn-primary"
										href="AllCourseAvgServlet?yearId=<%=u.getYearId()%>&termId=<%=u.getTermId()%>&type=<%=u.getType()%>">Course Avg</a> 
										&emsp;&emsp;&emsp; 
										<a class="btn btn-primary"
										href="UgPgQuestionAvgServlet?yearId=<%=u.getYearId()%>&termId=<%=u.getTermId()%>&type=<%=u.getType()%>">Question Avg</a>
										&emsp;&emsp;&emsp;
										<a class="btn btn-primary"
										href="AllCourseAvgServlet?yearId=<%=u.getYearId()%>&termId=<%=u.getTermId()%>&type=<%=u.getType()%>">Tabular
											Avg</a></td>
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



