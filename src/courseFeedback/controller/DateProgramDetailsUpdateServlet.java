package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import courseFeedback.bean.AdminBean;
import courseFeedback.bean.DateProgramDetailsBean;
import courseFeedback.dao.DateProgramDetailsDAO;
import courseFeedback.util.GenrateMathodsUtils;
import courseFeedback.util.ValidationUtils;

public class DateProgramDetailsUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String programDetailsId = request.getParameter("selProgramDetailsId");
		String date = request.getParameter("txtDate");
		String password = request.getParameter("txtPassword");
		String dateProgramDetailsId = request.getParameter("dateProgramDetailsId");
		String isAvailable = request.getParameter("isAvailable");
		String passwordLength = request.getParameter("txtPasswordLength");
		String numberOfPassword = request.getParameter("txtNoOfPassword");
		boolean isError = false;
		DateProgramDetailsBean bean = new DateProgramDetailsBean();

		if (programDetailsId.equals("0")) {
			isError = true;
			request.setAttribute("programDetailsId", "<font color=red>* Program Name is Required</font>");
		}

		if (ValidationUtils.isEmpty(date)) {
			isError = true;
			request.setAttribute("date", "<font color=red>* Date is Required</font>");
		} else {
			request.setAttribute("txtDate", date);
		}
		if (ValidationUtils.isEmpty(password)) {
			isError = true;
			request.setAttribute("password", "<font color=red>* password is Required</font>");
		} else {
			request.setAttribute("txtPassword", password);
		}
		bean.setUserName(password);
		bean.setIsAvailable(isAvailable);
		if (isError) {
			bean.setProgramDetailsId(programDetailsId);
			bean.setDateProgramDetailsId(dateProgramDetailsId);
			bean.setDate(date);
			request.setAttribute("dateProgramDetailsBean", bean);
			request.getRequestDispatcher("dateProgramDetailsEdit.jsp").forward(request, response);
		} else {
			if(numberOfPassword!=null && passwordLength!=null){
				bean.setNoOfPassword(numberOfPassword);
				bean.setPasswordLength(passwordLength);
			}
			bean.setDate(GenrateMathodsUtils.convertDateSQL(date));
			bean.setProgramDetailsId(programDetailsId);
			bean.setDateProgramDetailsId(dateProgramDetailsId);
			HttpSession session = request.getSession();
			AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
			if(adminBean.getAdminEmail()!=null){
				if (new DateProgramDetailsDAO().update(bean,adminBean.getAdminEmail()))
					response.sendRedirect("DateProgramDetailsListServlet");
				else
					response.sendRedirect("DateProgramDetailsListServlet");
			}else{
				response.sendRedirect("login.jsp");
			}
		}
	}

}
