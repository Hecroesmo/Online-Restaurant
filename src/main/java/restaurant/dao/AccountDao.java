package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import restaurant.model.Account;
import restaurant.model.AccountType;

public class AccountDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public AccountDao(Connection connection) {
		this.connection = connection;
	}
	
	public void save(Account account) {
		String sql = "INSERT INTO account VALUES (?, ?, ?);";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, account.getUsername());
			statement.setString(2, account.getPassword());
			
			AccountType accountType = new AccountTypeDao(connection).
				getAccountTypeByDescription(account.getAccountType().getDescription());
			
			statement.setInt(3, accountType.getAccountTypeId());
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to save account", e);
		}
	}
	
	public Account getAccountById(String username) {
		String sql = "SELECT a.* FROM account a WHERE pk_username = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Account account = new Account(
				resultSet.getString("pk_username"),
				resultSet.getString("password")
			);
			
			AccountType accountType;
			accountType  = new AccountTypeDao(connection).getAccountTypeById(
				resultSet.getLong("fk_account_type"));
			
			account.setAccountType(accountType);
			return account;
		} 
		catch (SQLException e)
		{
			throw new RuntimeException("Fail to get the account", e);
		}
	}
	
	
}
