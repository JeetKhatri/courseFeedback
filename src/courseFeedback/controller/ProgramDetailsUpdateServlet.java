package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import courseFeedback.bean.AdminBean;
import courseFeedback.bean.ProgramDetailsBean;
import courseFeedback.dao.ProgramDetailsDAO;
import courseFeedback.util.ValidationUtils;

public class ProgramDetailsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String programDetailsId = request.getParameter("programId");
		String programName = request.getParameter("txtProgramName");
		int isAvailable = Integer.parseInt(request.getParameter("isAvailable"));
		ProgramDetailsBean programDetailsBean = new ProgramDetailsBean();

		boolean isError = false;

		if (ValidationUtils.isEmpty(programName)) {
			isError = true;
			request.setAttribute("programName", "<font color=red>* Program Name is Required</font>");
		}

		else {
			request.setAttribute("txtProgramName", programName);
			programDetailsBean.setProgramName(programName);
		}
		programDetailsBean.setProgramDetailsId(programDetailsId);
		programDetailsBean.setIsAvailable(isAvailable);
		if (isError) {
			request.getRequestDispatcher("programDetailsEdit.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
			if (adminBean != null) {
				if (new ProgramDetailsDAO().update(programDetailsBean, adminBean.getAdminEmail())) {
					response.sendRedirect("ProgramDetailsListServlet");
				} else {
					request.getRequestDispatcher("programDetailsEdit.jsp").forward(request, response);
				}
			} else {
				response.sendRedirect("login.jsp");
			}
		}

	}

}
