package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.SemesterProgramDetailsBean;
import courseFeedback.dao.SemesterProgramDetailsDAO;

public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String counter=request.getParameter("counter");
		if(counter!=null){
			int c=0;
			if(request.getSession().getAttribute("courseCounter")!=null){
				c=Integer.parseInt(request.getSession().getAttribute("courseCounter")+"");
				c=(c>0)?c-1:0;
				request.getSession().setAttribute("courseCounter", c);
			}
			return;
		}
		
		
		String valAjax = request.getParameter("valAjax");

		if (valAjax == null) {
			
			valAjax = request.getParameter("valAjax1");
			ArrayList<SemesterProgramDetailsBean> list = (ArrayList<SemesterProgramDetailsBean>) new SemesterProgramDetailsDAO()
					.listOfCourseName(valAjax);
			response.getWriter().write("Select Course : <select><option value='0' disabled='disabled'>--Select Course--</option>");
			for (int i = 0; i < list.size(); i++)
				response.getWriter().write("<option value=" + list.get(i).getCourseCode() + ">"
						+ list.get(i).getCourseName() + "</option>");
			response.getWriter().write("</select>");

		} /*else {
			ArrayList<SemesterProgramDetailsBean> list = (ArrayList<SemesterProgramDetailsBean>) new SemesterProgramDetailsDAO()
					.list(valAjax);
			response.getWriter()
					.write("Select Course : <select onChange='chngOne()'><option value='0'>--Select Year--</option>");
			for (int i = 0; i < list.size(); i++)
				response.getWriter().write(
						"<option value=" + list.get(i).getSemId() + ">" + list.get(i).getBatchName() + "</option>");
			response.getWriter().write("</select>");
		}*/
	}

}