package restaurant.servlet;

import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import restaurant.dao.CategoryDao;
import restaurant.model.Category;

@WebServlet("/HandleRequestCategory")
public class HandleRequestCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException 
	{
		int id  = Integer.parseInt(request.getParameter("id"));
		
		Connection connection = (Connection) request.getAttribute("connection");
		
		List<Category> categories = new CategoryDao(connection).getCategoriesById(id);
		
		Gson gson = new Gson();
		String categoriesStr = gson.toJson(categories);
		
		Writer writer = response.getWriter();
		writer.write(categoriesStr);
	}

}
