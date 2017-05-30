package courseFeedback.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import courseFeedback.bean.AdminBean;
import courseFeedback.dao.AddSpecialQuestionDAO;

@SuppressWarnings("serial")
public class AddSpecialQuestionDeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String questionIdCourseProgramId=request.getParameter("questionIdCourseProgramId");
		String[] temp=questionIdCourseProgramId.split("-");
		HttpSession session = request.getSession();
		AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
		try{
		if(adminBean.getAdminEmail()!=null){
			if(new AddSpecialQuestionDAO().delete(temp[0],temp[1],adminBean.getAdminEmail()))
				request.getRequestDispatcher("AddSpecialQuestionListServlet").forward(request, response);
			else
				request.getRequestDispatcher("AddSpecialQuestionListServlet").forward(request, response);
		}else{
			response.sendRedirect("login.jsp");
		}
		}catch(Exception e){
			response.sendRedirect("login.jsp");
		}
	}

}
