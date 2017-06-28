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
import courseFeedback.dao.TermDetailsDAO;

@SuppressWarnings("serial")
public class GenerateUgPgChart extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String yearTerm=request.getParameter("cmbYearTerm");
		String temp[]=yearTerm.split(" ");
		String type = request.getParameter("selType");
		int[] list = new FeedbackDAO().getCount(temp[0],temp[1],type);

		DefaultPieDataset dataset = new DefaultPieDataset();
		int cnt = list[0] + list[1] + list[2] + list[3] + list[4];
		double a, b, c, d, e;
		a = ((double)list[0] * 100 / cnt);
		b = ((double)list[1] * 100 / cnt);
		c = ((double)list[2] * 100 / cnt);
		d = ((double)list[3] * 100 / cnt);
		e = ((double)list[4] * 100 / cnt);
		
		a=Double.parseDouble(String.format("%.2f", (a * 100) / 100));
		b=Double.parseDouble(String.format("%.2f", (b * 100) / 100));
		c=Double.parseDouble(String.format("%.2f", (c * 100) / 100));
		d=Double.parseDouble(String.format("%.2f", (d * 100) / 100));
		e=Double.parseDouble(String.format("%.2f", (e * 100) / 100));
		
		dataset.setValue("Option 1(%)", a);
		dataset.setValue("Option 2(%)", b);
		dataset.setValue("Option 3(%)", c);
		dataset.setValue("Option 4(%)", d);
		dataset.setValue("Option 5(%)", e);

		JFreeChart chart = ChartFactory.createPieChart(type + "  -  " + new TermDetailsDAO().getYearName(temp[0])+"  "+new TermDetailsDAO().getTermName(temp[1]), // chart
				// title
				dataset, // data
				true, // include legend
				true, false);

		ServletContext servletContext = request.getSession().getServletContext();
		String absoluteDiskPath = servletContext.getRealPath("photos/charts");

		int width = 640; // Width of the image
		int height = 480;// Height of the image
		File pieChart = new File(absoluteDiskPath + File.separator + type + "  -  " + new TermDetailsDAO().getYearName(temp[0])+"  "+new TermDetailsDAO().getTermName(temp[1]) + ".png");
		request.setAttribute("chart", type + "  -  " + new TermDetailsDAO().getYearName(temp[0])+"  "+new TermDetailsDAO().getTermName(temp[1]) + ".png");
		ChartUtilities.saveChartAsJPEG(pieChart, chart, width, height);
		request.getRequestDispatcher("displayChart.jsp").forward(request, response);
	}

}
