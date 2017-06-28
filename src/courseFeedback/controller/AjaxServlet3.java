package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.FeedbackBean;
import courseFeedback.dao.FeedbackDAO;

public class AjaxServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String valAjax = request.getParameter("valAjax2");
		ArrayList<FeedbackBean> arrayList = new FeedbackDAO().listofCourse(valAjax.split(" "));
		response.getWriter()
				.write("Select Course : <select>");
		for (int i = 0; i < arrayList.size(); i++)
			response.getWriter().write("<option value=" + arrayList.get(i).getCourseCode() + ">"
					+ arrayList.get(i).getCourseName() + " - " + arrayList.get(i).getCourseCode() + "</option>");
		response.getWriter().write("</select>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
