<%@page import="courseFeedback.bean.ProgramDetailsBean"%>
<%@page import="java.util.List"%>
<%@page import="courseFeedback.dao.ProgramDetailsDAO"%>
<%@page import="courseFeedback.bean.DateProgramDetailsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Date Schedule Update</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<script type="text/javascript" src="javaScript/mainscript.js"></script>
<link rel="icon" href="photos/daiict.png" />
</head>
<body>
	<%
		DateProgramDetailsBean dateProgramDetailsBean = (DateProgramDetailsBean) request
				.getAttribute("dateProgramDetailsBean");

		if (dateProgramDetailsBean != null) {
	%>
	<%@ include file="adminHeader.jsp"%>
	<div style="margin-top: -850px; margin-left: 250px;">
		<section class="content-header">
		<h1>
			Date Schedule <small>Update</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="adminDashBoard.jsp"><i class="fa fa-dashboard"></i>
					Home</a></li>
			<li class="active">Date Schedule</li>
		</ol>
		</section>
		<br> <br>
		<div class="col-lg-6">
			<div class="container">
				<form action="DateProgramDetailsUpdateServlet" method="post"
					name="Registration">
					<br /> <input type="hidden" name="dateProgramDetailsId"
						id="dateProgramDetailsId"
						value="<%=dateProgramDetailsBean.getDateProgramDetailsId()%>">

					<div class="row">
						<label class="col-sm-2"> <font size="+1">Program :</font>
						</label>
						<div class="col-lg-6">
							<select name="selProgramDetailsId" class="form-control">
								<option value="0" selected="selected">Select Program</option>
								<%
									ProgramDetailsDAO programDetailsDAO = new ProgramDetailsDAO();

										List<ProgramDetailsBean> detailsBeansList = programDetailsDAO.list();

										for (int i = 0; i < detailsBeansList.size(); i++) {
											ProgramDetailsBean detailsBean = detailsBeansList.get(i);
											if (detailsBean.getProgramDetailsId().equals(dateProgramDetailsBean.getProgramDetailsId())) {
								%>
								<option value="<%=detailsBean.getProgramDetailsId()%>"
									selected="selected"><%=detailsBean.getProgramName()%></option>
								<%
									} else {
								%>
								<option value="<%=detailsBean.getProgramDetailsId()%>"><%=detailsBean.getProgramName()%></option>
								<%
									}
										}
								%>
							</select> <font color="red">${programDetailsId}</font>
						</div>
					</div>
					<br />

					<div class="row">
						<label class="col-sm-2"> <font size="+1">Date :</font>
						</label>
						<div class="col-lg-6">
							<input type="txtDate" class="form-control" name="txtDate" id="datepicker1"
								placeholder="dd/mm/yyyy" value="<%=dateProgramDetailsBean.getDate()%>" />
							<font color="red">${date} </font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1">Username :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtPassword" 
								placeholder="Password for student" value="<%=dateProgramDetailsBean.getUserName()%>" />
							<font color="red">${password} </font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1"> No Of
								Password :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtNoOfPassword"
								placeholder="Number of Password" value="${txtNoOfPassword}" maxlength="255" /><font
								color="red">${noOfPassword} </font>
						</div>
					</div>
					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1"> Password Length :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtPasswordLength"
								placeholder="Password Length" maxlength="255" /><font
								color="red">${noOfPassword} </font>
						</div>
					</div>

					<br />

					<div class="row">

						<%
							String isAvailable = dateProgramDetailsBean.getIsAvailable();
								String yes = "", no = "";
								if (isAvailable.equals("1"))
									yes = "checked";
								else
									no = "checked";
						%>

						<label class="col-sm-3"><font size="+1">Is
								Available : </font> </label>
						<div class="col-md-7">
							<div class="col-sm-6">
								<input type="radio" class="radio" name="isAvailable" value="1"
									<%=yes%> />Yes
							</div>
							<div class="col-sm-6">
								<input type="radio" class="radio" name="isAvailable" value="0"
									<%=no%> />No
							</div>
						</div>

					</div>
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<br /> <br /> <br /> <br /> <label
						class="col-sm-2 control-label"></label> <input type="reset"
						value="Reset" name="reset" class="btn  btn-danger">
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Update" name="submit"
						class="btn btn-success">

				</form>
			</div>
		</div>
	</div>
	<%
		} else {

			request.setAttribute("msgLogin", "Please Login To Continue");
			response.sendRedirect("login.jsp");

		}
	%>
	<script type="text/javascript">
		var dates = $("#datepicker1").datepicker({
			minDate : "0",
			maxDate : "+2Y",
			defaultDate : "+1w",
			dateFormat : 'mm/dd/yy',
			numberOfMonths : 1,
			onSelect : function(date) {
				for (var i = 0; i < dates.length; ++i) {
					if (dates[i].id < this.id)
						$(dates[i]).datepicker('option', 'maxDate', date);
					else if (dates[i].id > this.id)
						$(dates[i]).datepicker('option', 'minDate', date);
				}
			}
		});
	</script>
</body>
</html>





