package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.CourseDetailsBean;
import courseFeedback.dao.CourseDetailsDAO;

public class CourseDetailsEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("termCourseDetailsId");
		CourseDetailsBean courseDetailsBean = new CourseDetailsDAO().getByPK(Integer.parseInt(id));
		if (courseDetailsBean != null) {
			request.setAttribute("courseDetailsBean", courseDetailsBean);
			request.getRequestDispatcher("courseDetailsEdit.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("CourseDetailsListServlet").forward(request, response);
		}
	}

}
