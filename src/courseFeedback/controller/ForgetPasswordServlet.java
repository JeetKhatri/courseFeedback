package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.dao.AdminDAO;
import courseFeedback.util.ValidationUtils;

public class ForgetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String emailId = request.getParameter("emailId");
		boolean isError = false;
		String string = new AdminDAO().isEmailExists(emailId);
		if (ValidationUtils.isEmpty(emailId)) {
			isError = true;
			request.setAttribute("emailId", "<font color=red>* Please enter Email Id</font>");
		} else if (string.equals("")) {
			isError = true;
			request.setAttribute("emailId", "<font color=red>* please enter valid email id</font>");
		}
		if (isError) {
			request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
		} else {

			String tmp = "http://localhost:8080/CourseFeedback/resetPassword.jsp?adminId=" + string;
			String from = "digitalgsrtc2016@gmail.com";
			String password = "digital2016";
			String subject = "Forgot Password";
			String message = "Please click below link to change your password...! " + tmp;
			String[] to = new String[1];
			to[0] = emailId;
			if (new SendEmail().sendMail(from, password, subject, message, to)) {
				request.setAttribute("msgLogin", "succesfully send to " + "your email<br>please check your email<br>");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else {
				request.setAttribute("msgLogin", "failed!please try again");
				request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
			}
		}

	}
}