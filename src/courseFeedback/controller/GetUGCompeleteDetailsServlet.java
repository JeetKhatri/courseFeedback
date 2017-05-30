package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.CourseQuestionAvgTabularBean;
import courseFeedback.bean.UGPGAvgBean;
import courseFeedback.dao.FeedbackDAO;

public class GetUGCompeleteDetailsServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<UGPGAvgBean> list2=new FeedbackDAO().getUGQuestionAVG();
		ArrayList<CourseQuestionAvgTabularBean> list = new FeedbackDAO().getUGCompleteInfoOfCourseQuestion();
		if(list!=null)
		{
			request.setAttribute("list", list);
			request.setAttribute("list2", list2);
			request.setAttribute("counter", new FeedbackDAO().totalUGStudent());
			request.getRequestDispatcher("ugCourseQuestionAvgTabularList.jsp").forward(request, response);
		}
	}

}
