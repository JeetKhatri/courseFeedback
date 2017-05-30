package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.dao.AdminDAO;
import courseFeedback.util.ValidationUtils;

public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminId = request.getParameter("adminId");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		boolean isError = false;
		if (ValidationUtils.isEmpty(oldPassword)) {
			isError = true;
			request.setAttribute("oldPassword", "<font color=red>* Old PASSWORD is Required</font>");
		}
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
			request.getRequestDispatcher("changePassword.jsp").forward(request, response);
		} else {
			if (new AdminDAO().checkPassword(adminId, oldPassword)) {
				if (new AdminDAO().changePassword(adminId, newPassword)) {
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} else {

				}
			} else {
				request.setAttribute("oldPassword", "<font color=red>* Old PASSWORD is Not Correct</font>");
				request.getRequestDispatcher("changePassword.jsp").forward(request, response);
			}
		}
	}

}
