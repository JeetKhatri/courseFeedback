<%@page import="courseFeedback.bean.AddSpecialQuestionBean"%>
<%@page import="courseFeedback.bean.RemoveSpecialQuestionBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Add Special Question List</title>

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
			Add Special Question <small>List</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Add Special Question</li>
		</ol>
		<br>
		<br>
		<a href="addSpecialQuestionInsert.jsp"><input type="button"
			value="ADD" name="ADD" class="btn btn-primary"></a> <br>
		<br>
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-body">
						<table id="example1"
							class="table table-bordered table-hover table-striped">
							<%
								List<AddSpecialQuestionBean> listOfAddSpecialQuestionBean = (ArrayList) request
										.getAttribute("listOfAddSpecialQuestionBean");
								if (listOfAddSpecialQuestionBean != null) {
							%>
							<thead class="gujju-theme text-uppercase">
								<tr>

									<th><center>Year Id</center></th>
									<th><center>Term Id</center></th>
									<th><center>Question Content</center></th>
									<th><center>CourseName</center></th>
									<th><center>CourseCode</center></th>
									<th><center>Ans Type</center></th>
									<th><center>Action</center></th>
								</tr>
							</thead>
							<tbody>
								<%
									for (int i = 0; i < listOfAddSpecialQuestionBean.size(); i++) {
											AddSpecialQuestionBean addSpecialQuestionBean = listOfAddSpecialQuestionBean.get(i);
								%>

								<tr>

									<td align="center"><%=addSpecialQuestionBean.getYearId()%></td>
									<td align="center"><%=addSpecialQuestionBean.getTermId()%></td>
									<td align="center"><textarea disabled="disabled" rows="2" cols="40"><%=addSpecialQuestionBean.getQuestionContent()%></textarea></td>
									<td align="center"><%=addSpecialQuestionBean.getCourseName()%></td>
									<td align="center"><%=addSpecialQuestionBean.getCourseCode()%></td>
									<td align="center"><%=addSpecialQuestionBean.getAnsType()%></td>
									<td align="center"><a
										href="AddSpecialQuestionDeleteServlet?questionIdCourseProgramId=<%=addSpecialQuestionBean.getQuestionId()%>-<%=addSpecialQuestionBean.getTermCourseId()%>"><img
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