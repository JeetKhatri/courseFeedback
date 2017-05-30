package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.CourseProgramDetailsBean;
import courseFeedback.dao.CourseProgramDetailsDAO;

public class CourseProgramDetailsInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String programDetailsId = request.getParameter("selProgramDetailsId");
		String termId = request.getParameter("selCourseDetailsId");
		boolean isError = false;

		if (programDetailsId.equals("0")) {
			isError = true;
			request.setAttribute("programDetailsId", "<font color=red>* Program Name is Required</font>");
		} else {
			request.setAttribute("selProgramDetailsId", programDetailsId);
		}
		if (termId.equals("0")) {
			isError = true;
			request.setAttribute("courseDetailsId", "<font color=red>* Program Name is Required</font>");
		} else {
			request.setAttribute("selcourseDetailsId", programDetailsId);
		}

		if (isError) {
			request.getRequestDispatcher("courseProgramDetailsInsert.jsp").forward(request, response);
		} else {

			CourseProgramDetailsBean bean = new CourseProgramDetailsBean();
			bean.setProgramDetailsId(programDetailsId);
			bean.setTermCourseId(termId);
			if (new CourseProgramDetailsDAO().insert(bean)) {
				response.sendRedirect("CourseProgramDetailsListServlet");
			} else
				request.getRequestDispatcher("courseProgramDetailsInsert.jsp").forward(request, response);
		}

	}
}
