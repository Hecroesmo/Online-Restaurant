package restaurant.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import restaurant.dao.CommuneDao;
import restaurant.dao.MunicipalityDao;
import restaurant.dao.NeighborhoodDao;
import restaurant.model.Region;

@WebServlet("/HandleRequest")
public class HandleRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		int id  = Integer.parseInt(request.getParameter("id"));
		String typeRegion = request.getParameter("typeRegion");
		
		Connection connection = (Connection) request.getAttribute("connection");
		
		List<Region> regions = null;
		
		switch (typeRegion) {
		case "Municipality":
			regions = handleMunicipalityRequest(id, connection);
			break;
		case "Commune":
			regions = handleCommuneRequest(id, connection);
			break;
		case "Neighborhood":
			regions = handleNeighborhoodRequest(id, connection);
			break;
		}
		
		
		Gson gson = new Gson();
		String regionsJson = gson.toJson(regions);
		
		PrintWriter write = response.getWriter();
		write.write(regionsJson);
	}
	
	protected List<Region> handleMunicipalityRequest(int id, Connection connection) {
		List<Region> regions = new MunicipalityDao(connection).getMunicipalitiesByFkey(id);
		return regions;
	}
	
	protected List<Region> handleCommuneRequest(int id, Connection connection) {
		List<Region> regions = new CommuneDao(connection).getCommunesByFkey(id);
		return regions;
	}
	
	protected List<Region> handleNeighborhoodRequest(int id, Connection connection) {
		List<Region> regions = new NeighborhoodDao(connection).getNeighborhoodsByFkey(id);
		return regions;
	}

	

}
