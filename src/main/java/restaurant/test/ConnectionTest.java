package restaurant.test;

import java.sql.Connection;
import java.sql.SQLException;

import restaurant.connection.ConnectionFactory;

public class ConnectionTest {

	public static void main(String[] args) throws SQLException {
		Connection connection = new ConnectionFactory().getConnection();
		System.out.println("connected success!");
		connection.close();
	}
}
