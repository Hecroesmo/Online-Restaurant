package restaurant.logic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.dao.AccountDao;
import restaurant.dao.PersonAccountDao;
import restaurant.dao.PersonDao;
import restaurant.interface_.Logic;
import restaurant.model.Account;
import restaurant.model.AccountType;
import restaurant.model.Person;

public class ValidateAccountLogic implements Logic {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String username = req.getParameter("username");                         
		String password = req.getParameter("password");                         
		String rePassword = req.getParameter("re-password");                    
		String phoneNumber = req.getParameter("phoneNumber");                       
		
		if (!password.equals(rePassword)) {
			req.setAttribute("error", "As palavras passes não coincidem");
			req.setAttribute("phoneNumber", phoneNumber);
			return "register-account.jsp";
		}
		
		Connection connection = (Connection) req.getAttribute("connection");
		AccountDao dao = new AccountDao(connection);
		Account account = dao.getAccountById(username);
		
		if (account != null) {
			req.setAttribute("error", "Nome de usuário existente");
			req.setAttribute("phoneNumber", phoneNumber);
			return "register-account.jsp";
		}
		
		AccountType accountType = new AccountType("client");
		Account newAccount = new Account(username, password, accountType);
		dao.save(newAccount);
		Person person = new PersonDao(connection).getPersonByPhoneNumber(phoneNumber);
		new PersonAccountDao(connection).save(person.getId(), newAccount.getUsername());
		return "login.jsp";
	}
}
