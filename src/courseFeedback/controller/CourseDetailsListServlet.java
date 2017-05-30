package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.CourseDetailsBean;
import courseFeedback.dao.CourseDetailsDAO;

public class CourseDetailsListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<CourseDetailsBean> listofCourse = new CourseDetailsDAO().select();

		if (listofCourse.size() > 0) {
			request.setAttribute("listofCourse", listofCourse);
			request.getRequestDispatcher("courseDetailsList.jsp").forward(request, response);
		}
	}
}
