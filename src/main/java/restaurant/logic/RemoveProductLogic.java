package restaurant.logic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.dao.ProductDao;
import restaurant.interface_.Logic;

public class RemoveProductLogic implements Logic {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		int id = Integer.parseInt( req.getParameter("id") );
		Connection connection = ( Connection ) req.getAttribute("connection");
		new ProductDao(connection).removeProductById(id);
		return "list-products.jsp";
	}

}
