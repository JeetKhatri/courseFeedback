<%@page import="courseFeedback.bean.ProgramDetailsBean"%>
<%@page import="java.util.List"%>
<%@page import="courseFeedback.dao.ProgramDetailsDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			Date Schedule <small>Insert</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">DateSchedule</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
				<form action="GenratePassword" method="post"
					name="Registration">
					<br />

					<div class="row">
						<label class="col-sm-2"> <font size="+1">Program :</font>
						</label>
						<div class="col-lg-6">
							<select name="selProgramDetailsId" class="form-control">
								<option value="0" selected="selected">Select Program</option>
								<%
									ProgramDetailsDAO programDetailsDAO = new ProgramDetailsDAO();
									List<ProgramDetailsBean> programDetailsList = programDetailsDAO.list();

									for (int i = 0; i < programDetailsList.size(); i++) {

										String tmp = "unselected";
										String type = request.getParameter("selProgramDetailsId");
										if (type == null)
											tmp = "unselected";
										else if (programDetailsList.get(i).getProgramDetailsId().equals(type))
											tmp = "selected";
								%>

								<option
									value=<%=programDetailsList.get(i).getProgramDetailsId()%>
									<%=tmp%>>
									<%=programDetailsList.get(i).getProgramName()%></option>
								<%
									}
								%>
							</select> <font color="red"></font>
						</div>
					</div>
					<br />

					<br /> <br /> <label class="col-sm-2 control-label"></label> <input
						type="reset" value="Reset" name="reset" class="btn  btn-danger">
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Click" name="submit"
						class="btn btn-success">

				</form>
			</div>
		</div>
	</div>
</body>
</html>