<%@page import="courseFeedback.bean.AdminBean"%>
<%@page import="courseFeedback.dao.FeedbackDAO"%>
<%@page import="courseFeedback.bean.UGPGAvgBean"%>
<%@page import="courseFeedback.bean.CourseQuestionAvgTabularBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin | Report</title>
<link rel="icon" href="photos/daiict.png" />
</head>
<body>
<%
		AdminBean adminBeanHeader = (AdminBean) session.getAttribute("adminBean");

		if (adminBeanHeader != null) {
	%>
<form action="DisplayUgPgServlet" method="post">
<center><input type="submit" value="Home"></center>
</form>
<center><h1> <%=request.getAttribute("type") %> Courses Average Report</h1></center>
	<table border="1" align="center">
		<tr>
			<th>Code	</th>
			<th>L</th>
			<th>T</th>
			<th>P</th>
			<%
				for (int i = 0; i < 20; i++) {
			%>
			<th rowspan="1"><%=i + 1%></th>
			<%
				}
			%>
			<th>Total</th>
			<th>Average</th>
			<th>Student</th>
		</tr>
		<%
			ArrayList<CourseQuestionAvgTabularBean> list = (ArrayList<CourseQuestionAvgTabularBean>) request
					.getAttribute("list");
		Double allOverTotal=0.0;
			for (CourseQuestionAvgTabularBean bean : list) {
				Double totalAvg=0.0;
		%>
		<tr>
			<td><%=bean.getCourseCode()%></td>
			<td><%=bean.getL()%></td>
			<td><%=bean.getT()%></td>
			<td><%=bean.getP()%></td>
			<%
				double avg[] = bean.getAvg();
			double s=0.0,temp;
			int tcnt=0;
					for (int i = 1; i <= 20; i++) {
						s+=avg[i];
						if(avg[i]>0){
							allOverTotal+=avg[i];
							totalAvg+=avg[i];
							tcnt++;
			%>
			<td align="right"><%=avg[i]%> </td><%
						}
						else
						{
							%>
							<td>&nbsp;</td><%
						}
 	}
					
 		if (bean.getCourseAvg()!=null) {
 			
 %>
 <td align="right"><%=String.format("%.2f", (totalAvg * 100) / 100) %></td>
 
 	<%
 	
 	%>
 
			<!-- <td><%=bean.getCourseAvg()%></td>-->
			<td align="right"><%=String.format("%.2f", (Double.parseDouble(s/tcnt+"") * 100) / 100)%></td>
			<%
				} else {
			%>
			<td></td>
			<%
				}
 		if(bean.getNoOfStudent()==0){
			%> <td>&nbsp;</td>
			<%} %>
			<td align="right"><%=bean.getNoOfStudent()%></td>
		</tr>
		<%
			}
		%>
		
		<tr>
		<td colspan="4"><center>Question's Total</center></td>
		<%
		ArrayList<UGPGAvgBean> beans=(ArrayList<UGPGAvgBean>) request.getAttribute("totalAvg");
 			for(UGPGAvgBean b: beans)
			{%>
			<td align="right"><%=String.format("%.2f", (b.getAvg() * 100) / 100) %></td>	
			<%}
		
		%>
		<td align="right"><b><%=String.format("%.2f", (allOverTotal * 100) / 100) %></b></td>
		<td align="right" style="color: red;"><b><%=request.getAttribute("ugpgAvg")%></b></td>
		<td align="right"><b><%=request.getAttribute("counter")%></b></td>
		</tr>
		
		
		<tr>
		
		<td colspan="4"><center>Question's Avg</center></td>
		
		<%
		ArrayList<UGPGAvgBean> list2=(ArrayList<UGPGAvgBean>)request.getAttribute("list2");
		int i=0;
		double totalQAvg=0.0;
		for(UGPGAvgBean ug: list2)
		{i++;
		totalQAvg+=ug.getAvg();
		%>
			<td align="right"><%=ug.getAvg()%></td>
		<% }
		
		%>
		<td align="right" style="color: red;"><b><%=String.format("%.2f", (totalQAvg/i * 100) / 100) %></b></td>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
		</tr>

	</table>
<%
		} else {

			request.setAttribute("msgLogin", "Please Login To Continue");
			response.sendRedirect("login.jsp");

		}
	%>
</body>
<center><h1> <%=request.getAttribute("type") %> Courses Average = <%=request.getAttribute("ugpgAvg")%> </h1></center>

</html>



















<%-- <%@page import="courseFeedback.dao.FeedbackDAO"%>
<%@page import="courseFeedback.bean.UGPGAvgBean"%>
<%@page import="courseFeedback.bean.CourseQuestionAvgTabularBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
<form action="DisplayUgPgServlet" method="post">
<center><input type="submit" value="Home"></center>
</form>
<center><h1>Total Average <%=request.getAttribute("type") %> Report</h1></center>
	<table border="1" align="center">
		<tr>
			<th>Course Name</th>
			<th>L</th>
			<th>T</th>
			<th>P</th>
			<%
				for (int i = 0; i < 20; i++) {
			%>
			<th><%=i + 1%></th>
			<%
				}
			%>
			<th>Course Avg</th>
			<th>No. Of Student</th>
		</tr>
		<%
			ArrayList<CourseQuestionAvgTabularBean> list = (ArrayList<CourseQuestionAvgTabularBean>) request
					.getAttribute("list");
			for (CourseQuestionAvgTabularBean bean : list) {
		%>
		<tr>
			<td><%=bean.getCourseCode()%></td>
			<td><%=bean.getL()%></td>
			<td><%=bean.getT()%></td>
			<td><%=bean.getP()%></td>
			<%
				double avg[] = bean.getAvg();
			double s=0.0,temp;
			int tcnt=0;
					for (int i = 1; i <= 20; i++) {
						s+=avg[i];
						if(avg[i]>0)
							tcnt++;
			%>
			<td><%=avg[i]%> </td><%
 	}
 		if (bean.getCourseAvg()!=null) {
 %>
			<!-- <td><%=bean.getCourseAvg()%></td>-->
			<td><%=String.format("%.2f", (Double.parseDouble(s/tcnt+"") * 100) / 100)%></td>
			<%
				} else {
			%>
			<td></td>
			<%
				}
			%> 
			<td><%=bean.getNoOfStudent()%></td>
		</tr>
		<%
			}
		%>
		<tr>
		<td colspan="2"><center>Total Student -> <%=request.getAttribute("counter")%></center></td>
		<td colspan="2"><center>Question wise Avg-></center></td>
		
		<%
		ArrayList<UGPGAvgBean> list2=(ArrayList<UGPGAvgBean>)request.getAttribute("list2");
		int i=0;
		for(UGPGAvgBean ug: list2)
		{i++;
		%>
			<td><%=ug.getAvg()%></td>
		<% }
		
		while(i<20)
		{i++;%>
		<td></td>	
		<%}
		%>
		<td colspan="2"><center>UG Avg -> <%=request.getAttribute("ugpgAvg")%></center></td>
		</tr>

	</table>

</body>
</html> --%>