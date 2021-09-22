package restaurant.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.dao.ImageDao;
import restaurant.model.Image;

@WebServlet("/GetImageServlet")
public class GetImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		Connection connection = ( Connection ) request.getAttribute("connection");
		Image image = new ImageDao(connection).getImageById(id);
		byte [] imageBytes = image.getImage().readAllBytes();
		
		response.setContentType("image/jpeg");
		response.setContentLength(imageBytes.length);
		response.getOutputStream().write(imageBytes);
		response.getOutputStream().flush();
	}
}
