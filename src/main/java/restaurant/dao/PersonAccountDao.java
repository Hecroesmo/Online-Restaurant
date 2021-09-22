package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonAccountDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public PersonAccountDao(Connection connection) {
		this.connection = connection;
	}
	
	public void save(int personId, String username) {
		String sql = "INSERT INTO person_account(fk_person, fk_username) VALUES (?, ?);";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, personId);
			statement.setString(2, username);
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to save person-account", e);
		}
	}
}
