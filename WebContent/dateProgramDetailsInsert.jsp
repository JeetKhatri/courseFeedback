<%@page import="courseFeedback.bean.ProgramDetailsBean"%>
<%@page import="java.util.List"%>
<%@page import="courseFeedback.dao.ProgramDetailsDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Course program Insert</title>
<noscript>
	<div style="color: #FF0000">place enable java script</div>
</noscript>
<script type="text/javascript" src="javaScript/mainscript.js"></script>
<link rel="icon" href="photos/daiict.png" />
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
				<form action="DateProgramDetailsInsertServlet" method="post"
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
							</select> <font col or="red">${programDetailsId}</font>
						</div>
					</div>
					<br />

					<div class="row">
						<label class="col-sm-2"> <font size="+1"> Date :</font>
						</label>
						<div class="col-lg-6">
							<input type="text" id="datepicker1" class="form-control"
								name="txtDate" value="${txtDate}" /> <font color="red">${date}
							</font>
						</div>
					</div>

					<br />
					<div class="row">
						<label class="col-sm-2"> <font size="+1"> UserName
								:</font>
						</label>
						<div class="col-lg-6">
							<input type="text" class="form-control" name="txtUserName"
								placeholder="UserName" maxlength="255" value="${txtUserName }" /><font color="red">${userName}
							</font>
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

					<br /> <br /> <label class="col-sm-2 control-label"></label> <input
						type="reset" value="Reset" name="reset" class="btn  btn-danger">
					&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
					<input type="Submit" value="Insert" name="submit"
						class="btn btn-success">

				</form>
			</div>
		</div>
	</div>
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