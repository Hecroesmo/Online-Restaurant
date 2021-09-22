package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.Region;

public class NeighborhoodDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public NeighborhoodDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Region> getNeighborhoodsByFkey(int fkey) {
		String sql = "SELECT * FROM neighborhood WHERE fk_commune = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, fkey);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			List<Region> neighborhoods = new ArrayList<Region>();
			
			do 
			{
				Region neighborhood = new Region();
				neighborhood.setPkRegion(resultSet.getInt("pk_neighborhood"));
				neighborhood.setName(resultSet.getString("name"));
				neighborhood.setFkRegion(resultSet.getInt("fk_commune"));
				neighborhoods.add(neighborhood);
			}
			while (resultSet.next());
			
			return neighborhoods;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get neighborhoods by foreign key", e);
		}
	}
	
	public Region getNeighborhoodByName(String name) {
		String sql = "SELECT * FROM neighborhood WHERE name = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Region neighborhood = new Region();
			neighborhood.setPkRegion(resultSet.getInt("pk_neighborhood"));
			neighborhood.setName(resultSet.getString("name"));
			neighborhood.setFkRegion(resultSet.getInt("fk_commune"));
			return neighborhood;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get neighborhood by name", e);
		}		
	}
	
	public Region getNeighborhoodById(int id) {
		String sql = "SELECT * FROM neighborhood WHERE pk_neighborhood = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Region neighborhood = new Region();
			neighborhood.setPkRegion(resultSet.getInt("pk_neighborhood"));
			neighborhood.setName(resultSet.getString("name"));
			neighborhood.setFkRegion(resultSet.getInt("fk_commune"));
			
			return neighborhood;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get neighborhood by id", e);
		}		
	}
	
}
