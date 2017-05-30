package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import courseFeedback.bean.AdminBean;
import courseFeedback.bean.CourseProgramDetailsBean;
import courseFeedback.dao.CourseProgramDetailsDAO;

public class CourseProgramDetailsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String programDetailsId = request.getParameter("selProgramDetailsId");
		String termCourseId = request.getParameter("seltermCourseId");
		String courseProgramId = request.getParameter("courseProgramId");
		String isAvailable = request.getParameter("isAvailable");
		boolean isError = false;
		CourseProgramDetailsBean bean = new CourseProgramDetailsBean();
		if (programDetailsId.equals("0")) {
			isError = true;
			request.setAttribute("programDetailsId", "<font color=red>* Program Name is Required</font>");
		}
		if (termCourseId.equals("0")) {
			isError = true;
			request.setAttribute("termCourseId", "<font color=red>* term is Required</font>");
		}
		bean.setIsAvailable(Integer.parseInt(isAvailable));
		bean.setProgramDetailsId(programDetailsId);
		bean.setTermCourseId(termCourseId);
		bean.setCourseProgramId(courseProgramId);
		if (isError) {
			request.setAttribute("courseProgramDetailsBean", bean);
			request.getRequestDispatcher("courseProgramDetailsEdit.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
			if (adminBean.getAdminEmail() != null) {
				if (new CourseProgramDetailsDAO().update(bean, adminBean.getAdminEmail()))
					response.sendRedirect("CourseProgramDetailsListServlet");
				else
					response.sendRedirect("CourseProgramDetailsListServlet");
			} else {
				response.sendRedirect("login.jsp");
			}
		}
	}
}