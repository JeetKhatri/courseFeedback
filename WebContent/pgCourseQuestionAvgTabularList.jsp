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
<title>Insert title here</title>
</head>
<body>
<form action="adminDashBoard.jsp" method="post">
<input type="submit" value="Home">
</form>
<h1 align="center">PG Details</h1>
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
			<td><%=bean.getCourseName()%></td>
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
			<td>0.0</td>
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
		ArrayList<UGPGAvgBean> list2=new FeedbackDAO().getPGQuestionAVG();
		int i=0;
		for(UGPGAvgBean ug: list2)
		{i++;
		%>
			<td><%=ug.getAvg()%></td>
		<% }
		
		while(i<20)
		{i++;%>
		<td>0.0</td>	
		<%}
		%>
		<td colspan="2"><center>UG Avg -> <%=new FeedbackDAO().PGAvg() %></center></td>
		</tr>

	</table>

</body>
</html>