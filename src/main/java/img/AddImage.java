package img;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

@MultipartConfig
//@WebServlet("/AddImage")
public class AddImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


    public AddImage() {
        super();
        // TODO Auto-generated constructor stub
    }


	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		doGet(request, response);
		System.out.println("In do post method servlet");
		Part file = request.getPart("image");
		
		String imageFileName = file.getSubmittedFileName(); //get selected image file name
		System.out.println("File Name : "+imageFileName);
		
		//upload path where we have to store our actual image
		String uploadPath = "E:/ProjectsEclipse/ImageTutorial/src/main/webapp/images/"+imageFileName;
		System.out.println("Upload Path : "+ uploadPath);
		
		
		//uploading selected image o images folder
		try {
			
			FileOutputStream fos = new FileOutputStream(uploadPath);
			InputStream is = file.getInputStream();
			
			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//getting database connection
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/imageTutorial","root","");
			PreparedStatement stmt;
			String sql = "Insert into image(imageFileName) values(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, imageFileName);
			int row = stmt.executeUpdate(); // it returs no of rows affected
			
			if(row>0) {
				System.out.println("Image Added Successfully");
			}else {
				System.out.println("Failed to upload img");
			}
		}
		catch(Exception e) {
			System.out.println("Error: "+ e);
		}
		
		
		
		
	}

}
