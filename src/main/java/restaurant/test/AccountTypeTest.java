package restaurant.test;

import restaurant.connection.ConnectionFactory;
import restaurant.dao.AccountTypeDao;

public class AccountTypeTest {

	public static void main(String[] args) {
		System.out.println(new AccountTypeDao(
			new ConnectionFactory().getConnection()).getAccountTypeByDescription("admin"));

	}

}
