package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.AdminBean;
import courseFeedback.dao.AdminDAO;
import courseFeedback.util.GenrateMathodsUtils;
import courseFeedback.util.ValidationUtils;

public class AdminInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String adminEmail = request.getParameter("txtAdminEmail");
		String adminPassword = request.getParameter("txtAdminPassword");
		AdminBean adminBean = new AdminBean();

		boolean isError = false;

		if (ValidationUtils.isEmpty(adminEmail)) {
			isError = true;
			request.setAttribute("adminEmail", "<font color=red>* E-MAIL is Required</font>");
		} else if (!ValidationUtils.isValidEmailAddress(adminEmail)) {
			isError = true;
			request.setAttribute("txtAdminEmail", adminEmail);
			request.setAttribute("adminEmail", "<font color=red>* E-MAIL is Not Proper</font>");
		} else {
			request.setAttribute("txtAdminEmail", adminEmail);
			adminBean.setAdminEmail(adminEmail);
		}

		if (ValidationUtils.isEmpty(adminPassword)) {
			isError = true;
			request.setAttribute("adminPassword", "<font color=red>* PASSWORD is Required</font>");
		}

		else {
			request.setAttribute("txtAdminPassword", adminPassword);
			adminBean.setAdminPassword(GenrateMathodsUtils.makeSHA512(adminPassword));
		}

		if (isError) {
			request.getRequestDispatcher("adminInsert.jsp").forward(request, response);
		} else {
			adminBean.setIsSuper("0");
			adminBean.setAdminId(GenrateMathodsUtils.getRandomString(15));
			if (new AdminDAO().insert(adminBean)) {
				response.sendRedirect("AdminListServlet");
			} else {
				response.sendRedirect("AdminListServlet");
			}

		}

	}

}
