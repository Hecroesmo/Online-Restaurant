package restaurant.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import restaurant.connection.ConnectionFactory;

@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, urlPatterns = { "/*" })
public class ConnectionFilter implements Filter {@Override
	public void destroy() {
	// TODO Auto-generated method stub
	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		Connection connection = new ConnectionFactory().getConnection();
		request.setAttribute("connection", connection);
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
		try 
		{
			connection.close();
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to close database connection", e);
		}
	}

}
