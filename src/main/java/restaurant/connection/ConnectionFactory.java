package restaurant.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	public Connection getConnection() {
		try 
		{
			Class.forName("org.postgresql.Driver");
			
			return DriverManager.getConnection(
				"jdbc:postgresql://localhost/restaurant", "postgres", "postgres");
		} catch (Exception e) {
			throw new RuntimeException("Fail to create a connection to database", e);
		}
	}
}
