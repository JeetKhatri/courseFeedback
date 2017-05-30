<%@page import="java.util.ArrayList"%>
<%@page import="courseFeedback.bean.DateProgramDetailsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Date Program Details List</title>
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
			Date Schedule <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Date Schedule</li>
		</ol>
		<br>
		<br>
		<a href="dateProgramDetailsInsert.jsp"><input type="button"
			value="ADD" name="ADD" class="btn btn-primary"></a> <br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">


							<%
								ArrayList<DateProgramDetailsBean> listOfDateProgramDetails = (ArrayList) request
										.getAttribute("listOfDateProgramDetails");
								if (listOfDateProgramDetails != null) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>

									<th><center>Program Name</center></th>
									<th><center>Date</center></th>
									<th><center>User Name</center></th>
									<th><center>is Available</center></th>
									<th><center>Action</center></th>

								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < listOfDateProgramDetails.size(); i++) {
											DateProgramDetailsBean dateProgramDetailsBean = listOfDateProgramDetails.get(i);
								%>

								<tr>

									<td align="center"><%=dateProgramDetailsBean.getProgramName()%></td>
									<td align="center"><%=dateProgramDetailsBean.getDate()%></td>
									<td align="center"><%=dateProgramDetailsBean.getUserName()%></td>
									<%
										if (dateProgramDetailsBean.getIsAvailable().equals("0")) {
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
									<td align="center">
										 <a
										href="DateProgramDetailsEditServlet?dateProgramDetailsId=<%=dateProgramDetailsBean.getDateProgramDetailsId()%>"><img
											src="photos/edit.ico" height="30" width="30"
											class="img-rounded" /></a>&nbsp;&nbsp;<a href="DateProgramDetailsDeleteServlet?dateProgramDetailsId=<%=dateProgramDetailsBean.getDateProgramDetailsId()%>"><img
											src="photos/Recycle Bin.ico" height="30" width="30"
											class="img-rounded" /></a>
											</td>
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