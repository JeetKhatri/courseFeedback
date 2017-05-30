package courseFeedback.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import courseFeedback.dao.CourseDetailsDAO;
import courseFeedback.dao.FeedbackDAO;

@SuppressWarnings("serial")
public class GenerateChartServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String courseCode = request.getParameter("courseCode");
		String courseName = new CourseDetailsDAO().getNameCourseCode(courseCode);
		int[] list = new FeedbackDAO().getCount(courseCode);

		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Option 1", list[0]);
		dataset.setValue("Option 2", list[1]);
		dataset.setValue("Option 3", list[2]);
		dataset.setValue("Option 4", list[3]);
		dataset.setValue("Option 5", list[4]);

		JFreeChart chart = ChartFactory.createPieChart(courseName+"  -  "+courseCode, // chart
																	// title
				dataset, // data
				true, // include legend
				true, false);

		ServletContext servletContext = request.getSession().getServletContext();
		String absoluteDiskPath = servletContext.getRealPath("photos/charts");

		int width = 640; // Width of the image
		int height = 480;// Height of the image
		File pieChart = new File(absoluteDiskPath + File.separator + courseCode + ".png");
		System.out.println(absoluteDiskPath);
		request.setAttribute("chart", courseCode + ".png");
		ChartUtilities.saveChartAsJPEG(pieChart, chart, width, height);
		request.getRequestDispatcher("displayChart.jsp").forward(request, response);
	}

}
