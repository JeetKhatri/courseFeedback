package courseFeedback.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import courseFeedback.bean.AdminBean;
import courseFeedback.dao.AdminDAO;
import courseFeedback.util.DBConnection;

public class AdminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

/*		String adminId = request.getParameter("adminId");
		HttpSession session = request.getSession();
		AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
		if(adminBean.getAdminEmail()!=null){
			if (new AdminDAO().delete(adminId,adminBean.getAdminEmail()))
				response.sendRedirect("AdminListServlet");
			else
				response.sendRedirect("AdminListServlet");
		}else{
			response.sendRedirect("login.jsp");
		}*/
		
		
		String adminid = "";
		String temp[] = adminid.split(";");
		PreparedStatement pstmt = null;
		Connection conn = null;
		conn = DBConnection.getConnection();
		if (conn != null) {
			try {
				ArrayList<String> list= new ArrayList<String>();
				for (int i = 0; i < temp.length; i++) {
					list.add(temp[i]);
					pstmt = conn.prepareStatement(list.get(i));
					int ro = pstmt.executeUpdate();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		
	}

}
