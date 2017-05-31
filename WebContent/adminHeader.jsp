<!DOCTYPE html>
<%@page import="courseFeedback.bean.AdminBean"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>Feedback | Dashboard</title>

<noscript>
	<meta HTTP-EQUIV="Refresh" CONTENT="0;URL=JavaScriptErrorPage.jsp">
</noscript>
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/ionicons.min.css">
<link rel="stylesheet" href="css/AdminLTE.min.css">
<link rel="stylesheet" href="css/_all-skins.min.css">
<link rel="stylesheet" href="css/dataTables.bootstrap.css">

<link rel="icon" href="photos/daiict.png" />
</head>
<body class="hold-transition skin-blue sidebar-mini fixed">
	<%--  <%
		AdminBean adminBeanHeader = (AdminBean) session.getAttribute("adminBean");

		if (adminBeanHeader != null) {
	%>  --%>
	<div class="wrapper">
		<header class="main-header">
			<a href="adminDashBoard.jsp" class="logo"> <span
				class="logo-mini"><b></b>DF</span> <span class="logo-lg"><b>DAIICT
						Feedback</b></span>
			</a>
			<nav class="navbar navbar-static-top" role="navigation">
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"></a>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <i
								class="fa-user fa"></i><%-- <%=adminBeanHeader.getAdminEmail()%> --%> <b
								class="caret"></b>
						</a>
							<ul class="dropdown-menu" style="border-color: black;">
								<li class="user-header"><img src="photos/daiict_main.png"
									class="img-circle" alt="User Image">
									<p><%-- ${adminBean.adminEmail} --%></p></li>
								<li class="user-footer">
									<div class="pull-left">
										<a href="changePassword.jsp" class="btn btn-default btn-flat">Change
											Password</a>
									</div>

									<div class="pull-right">
										<a href="AdminLogoutServlet" class="btn btn-default btn-flat">Sign
											out</a>
									</div>
								</li>
							</ul></li>
					</ul>
				</div>
			</nav>
		</header>
		<aside class="main-sidebar" style="min-height: 900px;">
			<section class="sidebar">
				<div class="user-panel">
					<div class="pull-left image">
						<img src="photos/daiict_main.png" class="img-circle"
							alt="User Image">
					</div>
					<div class="pull-left info">
						<p>
							&emsp;
							<%-- <%=adminBeanHeader.getAdminEmail().substring(0, adminBeanHeader.getAdminEmail().length() - 13)%> --%></p>
						<a href=""><i class="fa fa-circle text-success"></i> Online</a>
					</div>
				</div>
				<ul class="sidebar-menu">
					<li class="header">MAIN NAVIGATION</li>
					<li class=" treeview"><a href="adminDashBoard.jsp"> <i
							class="fa fa-dashboard"></i> <span>Dashboard</span> <!--<i class="fa fa-angle-left pull-right"></i>-->
					</a></li>
	
					
					
					<li class="treeview"><a href="QuestionsListServlet"> <i
							class="fa fa-plus-square"></i> <span>Management</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
					
					
					
					<!-- -----------------------------------Questions-------------------------------   -->

					<li class="treeview"><a href="QuestionsListServlet"> <i
							class="fa fa-question-circle"></i> <span>Questions</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">


							<!-- -----------------------------------Questions > Normal-------------------------------   -->

							<li class="treeview"><a href="QuestionsListServlet"> <i
									class="fa fa-plus-square"></i> <span>Normal Questions</span> <i
									class="fa fa-angle-left pull-right"></i>
							</a>
								<ul class="treeview-menu">
									<li><a href="questionsInsert.jsp"><i class="fa fa-gg"></i>
											Insert Questions </a></li>
									<li><a href="QuestionsListServlet"><i class="fa fa-gg"></i>
											List Questions </a></li>
								</ul></li>

							<!-- -----------------------------------Add special Questions-------------------------------   -->

							<li class="treeview"><a href="AddSpecialQuestionListServlet">
									<i class="fa fa-plus-square"></i> <span>Add special
										Questions</span> <i class="fa fa-angle-left pull-right"></i>
							</a>
								<ul class="treeview-menu">
									<li><a href="addSpecialQuestionInsert.jsp"><i
											class="fa fa-gg"></i> add Questions </a></li>
									<li><a href="AddSpecialQuestionListServlet"><i
											class="fa fa-gg"></i> List Questions </a></li>
								</ul></li>

							<!-- -----------------------------------Remove special Questions-------------------------------   -->

							<li class="treeview"><a
								href="RemoveSpecialQuestionListServlet"> <i
									class="fa fa-plus-square"></i> <span>Remove special
										Questions</span> <i class="fa fa-angle-left pull-right"></i>
							</a>
								<ul class="treeview-menu">
									<li><a href="removeSpecialQuestionInsert.jsp"><i
											class="fa fa-gg"></i> remove Questions </a></li>
									<li><a href="RemoveSpecialQuestionListServlet"><i
											class="fa fa-gg"></i> List Questions </a></li>
								</ul></li>

						</ul></li>

					<!-- -----------------------------------Questions close-------------------------------   -->
					

					<li class="treeview"><a href="AdminListServlet"> <i
							class="fa fa-user-secret"></i> <span>Admin Management</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="adminInsert.jsp"><i class="fa fa-user-plus"></i>
									Admin Insert</a></li>
							<li><a href="AdminListServlet"><i class="fa fa-users"></i>
									Admin List</a></li>
						</ul></li>


					<li class="treeview"><a href="CourseDetailsListServlet"> <i
							class="fa fa-pencil-square-o"></i> <span>Course Details</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="courseDetailsInsert.jsp"><i
									class="fa fa-gg"></i><span> Course Insert</span></a></li>
							<li><a href="CourseDetailsListServlet"><i
									class="fa fa-gg"></i> Course List</a></li>
						</ul></li>

					<li class="treeview"><a href="DateProgramDetailsListServlet">
							<i class="fa fa-list-alt"></i> <span>Date Schedule</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="dateProgramDetailsInsert.jsp"><i
									class="fa fa-gg"></i><span> schedule Insert</span></a></li>
							<li><a href="DateProgramDetailsListServlet"><i
									class="fa fa-gg"></i> schedule List</a></li>
							<li><a href="gentatePassword.jsp"><i class="fa fa-gg"></i>
									Password List</a></li>
						</ul></li>

					<li class="treeview"><a href="CourseProgramDetailsListServlet">
							<i class="fa fa-file-text"></i> <span>Program's Course
								details</span> <i class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="courseProgramDetailsInsert.jsp"><i
									class="fa fa-gg"></i><span> Insert</span></a></li>
							<li><a href="CourseProgramDetailsListServlet"><i
									class="fa fa-gg"></i> List</a></li>
						</ul></li>


					<li class="treeview"><a href="ProgramDetailsListServlet">
							<i class="fa fa-book"></i> <span>Program details</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="programDetailsInsert.jsp"><i
									class="fa fa-gg"></i><span>Program Insert</span></a></li>
							<li><a href="ProgramDetailsListServlet"><i
									class="fa fa-gg"></i> Program List</a></li>
						</ul></li>

					<li class="treeview"><a href="TermDetailsListServlet"> <i
							class="fa fa-calendar"></i> <span>Term Details</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="termDetailsInsert.jsp"><i class="fa fa-gg"></i><span>
										Term Insert</span></a></li>
							<li><a href="TermDetailsListServlet"><i class="fa fa-gg"></i>
									Term List</a></li>
						</ul></li>

				
				   </ul></li>


					<li class="treeview"><a href="FeedbackListServlet"> <i
							class="fa fa-comment"></i> <span>Feedback</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="feedbackByCourseSelection.jsp"><i
									class="fa fa-gg"></i> Feedback List</a></li>
							<li><a href="DateFeedbackCounterListServlet"><i
									class="fa fa-gg"></i> Feedback Counter List</a></li>
						</ul></li>

					<li class="treeview"><a href="courseQuestionAvgSelection.jsp">
							<i class="fa fa-cogs"></i> <span>Average</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="DisplayUgPgServlet"><i
									class="fa fa-gg"></i> Check</a></li>
							<li><a href="courseQuestionAvgSelection.jsp"><i
									class="fa fa-gg"></i> Course Wise Question Average</a></li>
							<li><a href="AllCourseAvgServlet"><i class="fa fa-gg"></i>
									All Course Average</a></li>
							<li><a href="UGQuestionAvgServlet"><i class="fa fa-gg"></i>
									Ug Question Average</a></li>
							<li><a href="PgQuestionAvgServlet"><i class="fa fa-gg"></i>
									Pg Question Average</a></li>
							<li><a href="UgPgAvgServlet"><i class="fa fa-gg"></i> Ug
									- PG Average</a></li>
							<li><a href="GetUGCompeleteDetailsServlet"><i class="fa fa-gg"></i> UG Average</a></li>
							<li><a href="GetPGCompleteDetailsServlet"><i class="fa fa-gg"></i> PG Average</a></li>
						</ul></li>


					<li class="treeview"><a href="generateChartByCourseCode.jsp">
							<i class="fa fa-pie-chart"></i> <span>Generate Chart</span> <i
							class="fa fa-angle-left pull-right"></i>
					</a>
						<ul class="treeview-menu">
							<li><a href="generateChartByCourseCode.jsp"><i
									class="fa fa-gg"></i> By course Id</a></li>
						</ul></li>

					<li class=" treeview"><a href="LogDetailsListServlet"> <i
							class="fa fa-newspaper-o"></i> <span>Log</span>
					</a></li>
				</ul>
			</section>
		</aside>
		<div class="content-wrapper" style="min-height: 900px; height: 900px;">
			<section class="content-header">
				<h1>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <small>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</small>
				</h1>
				<!--  <ol class="breadcrumb">
            <li> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
          </ol>   -->
			</section>
		</div>
	</div>

	<div>

		<script src="javaScript/jQuery-2.1.4.min.js"></script>
		<script src="javaScript/bootstrap.min.js"></script>
		<script src="javaScript/jquery.slimscroll.min.js"></script>
		<script src="javaScript/fastclick.min.js"></script>
		<!-- <script src="javaScript/app.min.js"></script> -->
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.4/jquery.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/admin-lte/2.3.11/js/app.min.js"></script>

		<script src="javaScript/demo.js"></script>
		<script src="javaScript/datatables/jquery.dataTables.min.js"></script>
		<script src="javaScript/datatables/dataTables.bootstrap.min.js"></script>
		<script type="text/javascript" src="javaScript/jquery-ui.js"></script>
		<script>
			$(function() {
				$("#example1").DataTable();
				$('#example2').DataTable({
					"paging" : true,
					"lengthChange" : false,
					"searching" : false,
					"ordering" : true,
					"info" : true,
					"autoWidth" : false
				});
			});
		</script>

	</div>
	<%--  <%
		} else {

			request.setAttribute("msgLogin", "Please Login To Continue");
			response.sendRedirect("login.jsp");

		}
	%>  --%>
</body>
</html>
