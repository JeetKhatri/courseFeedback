package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.TermDetailsBean;
import courseFeedback.dao.TermDetailsDAO;
import courseFeedback.util.ValidationUtils;

@SuppressWarnings("serial")
public class TermDetailsInsertServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String yearId = request.getParameter("txtYearId");
		String termId = request.getParameter("txtTermId");
		String yearName = request.getParameter("txtYearName");
		String termName = request.getParameter("txtTermName");
		TermDetailsBean termDetailsBean = new TermDetailsBean();

		boolean isError = false;

		if (ValidationUtils.isEmpty(yearId)) {
			isError = true;
			request.setAttribute("yearId", "<font color=red>* YearId is Required</font>");
		}

		else {
			request.setAttribute("txtYearID", yearId);
			termDetailsBean.setYearId(Integer.parseInt(yearId));
		}

		if (ValidationUtils.isEmpty(termId)) {
			isError = true;
			request.setAttribute("termId", "<font color=red>* TermId is Required</font>");
		}

		else {
			request.setAttribute("txtTermID", termId);
			termDetailsBean.setTermId(Integer.parseInt(termId));
		}

		if (ValidationUtils.isEmpty(yearName)) {
			isError = true;
			request.setAttribute("yearName", "<font color=red>* YearName is Required</font>");
		}

		else {
			request.setAttribute("txtYearName", yearName);
			termDetailsBean.setYearName(yearName);
		}

		if (ValidationUtils.isEmpty(termName)) {
			isError = true;
			request.setAttribute("termName", "<font color=red>* TermName is Required</font>");
		}

		else {
			request.setAttribute("txtTermName", termName);
			termDetailsBean.setTermName(termName);
		}

		if (isError) {
			request.getRequestDispatcher("termDetailsInsert.jsp").forward(request, response);
		} else {
			if (new TermDetailsDAO().insert(termDetailsBean)) {
				response.sendRedirect("TermDetailsListServlet");

			} else {

			}

		}
	}

}
