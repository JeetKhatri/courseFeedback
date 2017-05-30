package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.dao.AdminDAO;
import courseFeedback.util.ValidationUtils;

public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminId = request.getParameter("adminId");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		boolean isError = false;
		if (ValidationUtils.isEmpty(newPassword)) {
			isError = true;
			request.setAttribute("newPassword", "<font color=red>* New PASSWORD is Required</font>");
		}
		if (ValidationUtils.isEmpty(confirmPassword)) {
			isError = true;
			request.setAttribute("confirmPassword", "<font color=red>* Confirm PASSWORD is Required</font>");
		}
		if (!newPassword.equals(confirmPassword)) {
			isError = true;
			request.setAttribute("confirmPassword",
					"<font color=red>* New Password And Confirm Password DoesNot Match</font>");
		}

		if (isError) {
			request.setAttribute("adminId", adminId);
			request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
		} else {
			if (new AdminDAO().changePassword(adminId, newPassword)) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {

			}
		}
	}

}