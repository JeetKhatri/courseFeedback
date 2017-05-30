package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.ProgramDetailsBean;
import courseFeedback.dao.ProgramDetailsDAO;
import courseFeedback.util.GenrateMathodsUtils;
import courseFeedback.util.ValidationUtils;

public class ProgramDetailsInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String programName = request.getParameter("txtProgramName");
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

		if (isError) {
			request.getRequestDispatcher("programDetailsInsert.jsp").forward(request, response);
		} else {
			programDetailsBean.setProgramDetailsId(GenrateMathodsUtils.getRandomString(15));
			if (new ProgramDetailsDAO().insert(programDetailsBean)) {

				response.sendRedirect("ProgramDetailsListServlet");

			} else {
				response.sendRedirect("ProgramDetailsListServlet");

			}

		}

	}

}
