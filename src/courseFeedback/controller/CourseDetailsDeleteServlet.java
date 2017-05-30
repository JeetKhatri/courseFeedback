package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import courseFeedback.bean.AdminBean;
import courseFeedback.dao.CourseDetailsDAO;

public class CourseDetailsDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int termCourseId=Integer.parseInt(request.getParameter("termCourseDetailsId"));
		HttpSession session = request.getSession();
		AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
		if(adminBean.getAdminEmail()!=null){
			new CourseDetailsDAO().delete(termCourseId,adminBean.getAdminEmail());
			request.getRequestDispatcher("CourseDetailsListServlet").forward(request, response);
		}else{
			response.sendRedirect("login.jsp");
		}
	}

}
