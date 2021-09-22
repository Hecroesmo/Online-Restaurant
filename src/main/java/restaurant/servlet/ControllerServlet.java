package restaurant.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.interface_.Logic;

@WebServlet("/mvc")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException 
	{
		String parameter = req.getParameter("logic");
		String className = "restaurant.logic." + parameter;
		
		try 
		{
			Class<?> class_ = Class.forName(className);
			@SuppressWarnings("deprecation")
			Logic logic = (Logic) class_.newInstance();
			
			String page = logic.execute(req, res);
			req.getRequestDispatcher(page).forward(req, res);
		} 
		catch (Exception e) 
		{
			throw new ServletException("Fail to process " + className + " logic", e);
		}
	}
}
