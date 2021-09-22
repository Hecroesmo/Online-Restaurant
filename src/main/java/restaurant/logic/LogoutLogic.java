package restaurant.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.interface_.Logic;

public class LogoutLogic implements Logic {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		req.getSession().invalidate();
		return "login.jsp";
	}

}
