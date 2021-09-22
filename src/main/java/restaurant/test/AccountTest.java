package restaurant.test;

import restaurant.connection.ConnectionFactory;
import restaurant.dao.AccountDao;
import restaurant.model.Account;
import restaurant.model.AccountType;

public class AccountTest {

	public static void main(String[] args) {
		Account account = new Account("tio_hecro", "W123", new AccountType(1, "client"));
		new AccountDao(new ConnectionFactory().getConnection()).save(account);;
		
		
		//	System.out.println(new AccountDao(new ConnectionFactory().getConnection()).getAccountById("root"));

	}

}
