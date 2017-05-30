package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.DateProgramDetailsBean;
import courseFeedback.dao.DateProgramDetailsDAO;
import courseFeedback.util.GenrateMathodsUtils;
import courseFeedback.util.ValidationUtils;

public class DateProgramDetailsInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String programDetailsId = request.getParameter("selProgramDetailsId");
		String date = request.getParameter("txtDate");
		String userName = request.getParameter("txtUserName");
		String numberOfPassword = request.getParameter("txtNoOfPassword");
		String passwordLength = request.getParameter("txtPasswordLength");
		boolean isError = false;

		if (programDetailsId.equals("0")) {
			isError = true;
			request.setAttribute("programDetailsId", "<font color=red>* Program Name is Required</font>");
		} else {
			request.setAttribute("selProgramDetailsId", programDetailsId);
		}

		if (ValidationUtils.isEmpty(date)) {
			isError = true;
			request.setAttribute("date", "<font color=red>* Date is Required</font>");
		}

		else {
			request.setAttribute("txtDate", date);
		}

		if (ValidationUtils.isEmpty(userName)) {
			isError = true;
			request.setAttribute("userName", "<font color=red>* userName is Required</font>");
		} else {
			request.setAttribute("txtUserName", userName);
		}

		if (ValidationUtils.isEmpty(numberOfPassword)) {
			isError = true;
			request.setAttribute("noOfPassword", "<font color=red>*  number of Password is Required</font>");
		} else {
			request.setAttribute("txtNoOfPassword", numberOfPassword);
		}
		
		if (isError) {
			request.getRequestDispatcher("dateProgramDetailsInsert.jsp").forward(request, response);
		} else {
			
			DateProgramDetailsBean bean = new DateProgramDetailsBean();
			bean.setNoOfPassword(numberOfPassword);
			bean.setPasswordLength(passwordLength);
			bean.setDate(GenrateMathodsUtils.convertDateSQL(date));
			bean.setUserName(userName);
			bean.setProgramDetailsId(programDetailsId);
			bean.setDateProgramDetailsId(GenrateMathodsUtils.getRandomString(15));
			if (new DateProgramDetailsDAO().insert(bean)) {
				response.sendRedirect("DateProgramDetailsListServlet");
			} else
				request.getRequestDispatcher("dateProgramDetailsInsert.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
