package img;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet implementation class DisplayServlet
 */
public class DisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//		doGet(request, response);
		System.out.println("OKKK");
		String imageId = request.getParameter("imageId");
		int id = Integer.parseInt(imageId);
		
		
		Connection conn = null;
		int  imgId=0;
		String imgFileName=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/imageTutorial","root","");
			Statement stmt;
			String sql = "Select * from image";
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				if(rs.getInt("imageId")==id) {
					imgId = rs.getInt("imageId");
					imgFileName = rs.getString("imageFileName");
				}
			}
			
			
		}
		catch(Exception e) {
			System.out.println("Error: "+ e);
		}
		
		//to pass servlet data to jsp page
		
		RequestDispatcher rd;
		request.setAttribute("id",String.valueOf(imgId));
		request.setAttribute("img",imgFileName );
		rd = request.getRequestDispatcher("displayimage.jsp");
		rd.forward(request, response);
	}

}
