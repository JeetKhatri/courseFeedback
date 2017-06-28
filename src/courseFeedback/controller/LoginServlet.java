package courseFeedback.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import courseFeedback.bean.AdminBean;
import courseFeedback.dao.DateProgramDetailsDAO;
import courseFeedback.dao.LoginDAO;
import courseFeedback.dao.ProgramDetailsDAO;
import courseFeedback.util.ValidationUtils;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminEmail = request.getParameter("adminEmail");
		String adminPassword = request.getParameter("adminPassword");
		boolean isError = false;
		if (adminPassword == null) {
			adminPassword = "";
		}
		if (adminEmail == null) {
			adminEmail = "";
		}
		if (ValidationUtils.isEmpty(adminPassword)) {
			isError = true;
			request.setAttribute("adminPassword", "<font color=red>* PASSWORD is Required</font>");
		} else {
			request.setAttribute("txtAdminPassword", adminPassword);
		}

		if (ValidationUtils.isEmpty(adminEmail)) {
			isError = true;
			request.setAttribute("adminEmail", "<font color=red>* Username is Required</font>");
		}

		else {
			request.setAttribute("txtAdminEmail", adminEmail);
		}

		if (isError) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {

			String programDetailsId = new DateProgramDetailsDAO().loginStudent(adminEmail, adminPassword);
			if (programDetailsId != null) {

				HttpSession session = request.getSession();
				session.setAttribute("programDetailsId", programDetailsId);
				session.setAttribute("programDetailsName", new ProgramDetailsDAO().getName(programDetailsId));
				session.setAttribute("userBean", "user");
				session.setAttribute("userPassword", adminPassword);
				/*ServletContext context = getServletContext();

				if (context.getAttribute("cnt") == null) {
					context.setAttribute("cnt", 1);
				} else {
					int temp = (int) context.getAttribute("cnt");
					context.setAttribute("cnt", ++temp);
				}*/
				request.getRequestDispatcher("instruction.html").forward(request, response);
			} else {
				isError = true;
				request.setAttribute("adminPassword", "<font color=red>* Invalid Username Or Password</font>");
				// request.getRequestDispatcher("login.jsp").forward(request,
				// response);
			}

			if (isError == true) {

				if (!adminEmail.contains("@daiict.ac.in")) {
					adminEmail += "@daiict.ac.in";
				}
				AdminBean adminBean = new LoginDAO().login(adminEmail, adminPassword);
				if (adminBean != null) {
					HttpSession session = request.getSession();
					session.setAttribute("adminBean", adminBean);
					request.getRequestDispatcher("adminDashBoard.jsp").forward(request, response);
				} else {
					// request.setAttribute("adminEmail", "<font color=red>* You
					// Are Not Authorised Person</font>");
					request.getRequestDispatcher("login.jsp").include(request, response);
				}

			}
		}
	}

}
/*
 * ServletContext context = getServletContext();
 * 
 * if (context.getAttribute("cnt") == null) { context.setAttribute("cnt", 1); }
 * else { int temp = (int) context.getAttribute("cnt");
 * context.setAttribute("cnt", ++temp); }
 */
