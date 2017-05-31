package courseFeedback.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import courseFeedback.bean.CourseQueestionAVGBean;
import courseFeedback.dao.FeedbackDAO;

public class AllCourseAvgServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String type = request.getParameter("type");
		String yearId = request.getParameter("yearId");
		String termId = request.getParameter("termId");
		System.out.println(type+" "+yearId+" "+termId+ " <<<<-----");
		ArrayList<CourseQueestionAVGBean> courseQuestionAVG = new FeedbackDAO().allCourseAvg(yearId,termId,type);
		
		System.out.println(courseQuestionAVG.size() + " dlfkdof;dof");

		request.setAttribute("courseQuestionAvg", courseQuestionAVG);
		request.getRequestDispatcher("allCourseAvgList.jsp").forward(request, response);
	}

}
