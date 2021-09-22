package restaurant.logic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import restaurant.dao.AccountDao;
import restaurant.interface_.Logic;
import restaurant.model.Account;

public class AuthenticateLogic implements Logic {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Connection connection = (Connection) req.getAttribute("connection");
		Account account = new AccountDao(connection).getAccountById(username);
		
		if (account == null || !account.getPassword().equals(password)) {
			req.setAttribute("error", "Erro nas credencias!");
			return "login.jsp";
		}
		
		HttpSession session = req.getSession(true);
		session.setAttribute("account", account);
		return "index.jsp";
	}

}
