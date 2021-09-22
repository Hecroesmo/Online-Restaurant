package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import restaurant.model.AccountType;

public class AccountTypeDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public AccountTypeDao(Connection connection) {
		this.connection = connection;
	}
	
	public AccountType getAccountTypeById(Long id) {
		String sql = "SELECT at.* FROM account_type at WHERE pk_account_type = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			AccountType accountType = new AccountType(
				resultSet.getInt("pk_account_type"),
				resultSet.getString("description"));
			
			return accountType;
		}
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get the account type by id", e);
		}
	}
	
	public AccountType getAccountTypeByDescription(String description) {
		String sql = "SELECT * FROM account_type WHERE description = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, description);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			AccountType accountType = new AccountType(
				resultSet.getInt("pk_account_type"),
				resultSet.getString("description"));
			
			return accountType;
		}
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get the account type by description", e);
		}
	}
	
	
}
