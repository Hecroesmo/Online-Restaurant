package restaurant.servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.dao.CategoryDao;

@WebServlet("/DefaultCategoryServlet")
public class DefaultCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		System.out.println("DefaultCategoryServlet:" + id);
		
		Connection connection = ( Connection ) request.getAttribute("connection");
		CategoryDao dao = new CategoryDao(connection);
		
		
	}


}
