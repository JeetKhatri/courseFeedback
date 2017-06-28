package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.dao.AdminDAO;
import courseFeedback.util.ValidationUtils;

/**
 * Servlet implementation class ChangeAdminPasswordServlet
 */
public class ChangeAdminPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String adminId = request.getParameter("emailId");
		String newPassword = request.getParameter("newPassword");
		boolean isError = false;
		if (ValidationUtils.isEmpty(newPassword)) {
			isError = true;
			request.setAttribute("newPassword", "<font color=red>* New PASSWORD is Required</font>");
		} else {

		}
		if (ValidationUtils.isEmpty(adminId)) {
			isError = true;
			request.setAttribute("emailId", "<font color=red>* Email Id is Required</font>");
		} else if (!ValidationUtils.isValidEmailAddress(adminId)) {
			isError = true;
			request.setAttribute("emailId", "<font color=red>* Email Id is not Valid </font>");
		} else {
			request.setAttribute("email", adminId);
		}
		if (isError) {
			request.setAttribute("email", adminId);
			request.getRequestDispatcher("resetPasssword.jsp").forward(request, response);
		} else {
			if (new AdminDAO().changeAdminPassword(adminId, newPassword)) {
				request.getRequestDispatcher("adminDashBoard.jsp").forward(request, response);
			} else {
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
