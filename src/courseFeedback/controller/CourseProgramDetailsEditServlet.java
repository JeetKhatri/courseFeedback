package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.CourseProgramDetailsBean;
import courseFeedback.dao.CourseProgramDetailsDAO;

public class CourseProgramDetailsEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String courseProgramId = request.getParameter("courseProgramId");

		CourseProgramDetailsBean courseProgramDetailsBean = new CourseProgramDetailsDAO().getByPK(courseProgramId);

		if (courseProgramDetailsBean != null) {
			request.setAttribute("courseProgramDetailsBean", courseProgramDetailsBean);
			request.getRequestDispatcher("courseProgramDetailsEdit.jsp").forward(request, response);
		}

	}

}
