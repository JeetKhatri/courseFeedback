<%@page import="courseFeedback.bean.ProgramDetailsBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Program List</title>
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
			Program <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Program </li>
		</ol>
		<br>
		<br>
		<a href="programDetailsInsert.jsp"><input type="button"
			value="ADD" name="ADD" class="btn btn-primary"></a> <br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">
							<%
								ArrayList<ProgramDetailsBean> listofProgramDetails = (ArrayList) request
										.getAttribute("listOfProgramDetails");

								if (listofProgramDetails != null) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>

									<th><center>ProgramDetails Id</center></th>
									<th><center>ProgramDetails Name</center></th>
									<th><center>Is Available</center></th>
									<th><center>Action</center></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < listofProgramDetails.size(); i++) {
											ProgramDetailsBean programDetailsBean = listofProgramDetails.get(i);
								%>

								<tr>

									<td align="center"><%=programDetailsBean.getProgramDetailsId()%></td>
									<td align="center"><%=programDetailsBean.getProgramName()%></td>
									<%
										if (programDetailsBean.getIsAvailable() == 0) {
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
										href="ProgramDetailsEditServlet?programDetailsId=<%=programDetailsBean.getProgramDetailsId()%>"><img
											src="photos/edit.ico" height="30" width="30"
											class="img-rounded" /></a>&emsp;&emsp;
											<a
										href="ProgramDetailsDeleteServlet?programDetailsId=<%=programDetailsBean.getProgramDetailsId()%>"><img
											src="photos/Recycle Bin.ico" height="30" width="30"
											class="img-rounded" /></a></td>

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