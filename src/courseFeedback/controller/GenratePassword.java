package courseFeedback.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import courseFeedback.util.DBConnection;

public class GenratePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GenratePassword() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ServletOutputStream os = response.getOutputStream();
		response.setContentType("application/pdf");
		Document doc = new Document();
		Font bf13 = new Font(FontFamily.TIMES_ROMAN, 13);
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = null;

		try {

			PdfWriter.getInstance(doc, os);
			doc.addAuthor("Sharvil");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("Sharvil");
			doc.addTitle("Password List");
			doc.setPageSize(PageSize.A3);
			doc.open();
			doc.add(new Paragraph());
			conn = DBConnection.getConnection();
			String temp = request.getParameter("selProgramDetailsId");
			sql = "select username,password from passwordpool pd,dateprogramdetails dp where pd.programDetailsId=dp.programDetailsId and dp.programDetailsId=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, temp);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				doc.add(new Paragraph(" URL = http://localhost:CourseFeedback/index.html |   Password = "
						+ rs.getString("password") + " |   Username = " + rs.getString("username"), bf13));
				doc.add(Chunk.NEWLINE);
			}

			rs.close();
			stmt.close();
			doc.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
