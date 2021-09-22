package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.Region;

public class CommuneDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public CommuneDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Region> getCommunesByFkey(int fkey) {
		String sql = "SELECT * FROM commune WHERE fk_municipality = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, fkey);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			List<Region> communes = new ArrayList<Region>();
			
			do 
			{
				Region commune = new Region();
				commune.setPkRegion(resultSet.getInt("pk_commune"));
				commune.setName(resultSet.getString("name"));
				commune.setFkRegion(resultSet.getInt("fk_municipality"));
				communes.add(commune);
			}
			while (resultSet.next());
			
			return communes;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get communes by foreign key", e);
		}
	}
	
	public Region getCommuneByName(String name) {
		String sql = "SELECT * FROM commune WHERE name = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Region commune = new Region();
			commune.setPkRegion(resultSet.getInt("pk_commune"));
			commune.setName(resultSet.getString("name"));
			commune.setFkRegion(resultSet.getInt("fk_municipality"));
			return commune;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get commune by name", e);
		}		
	}
	
	public Region getCommuneById(int id) {
		String sql = "SELECT * FROM commune WHERE pk_commune = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Region commune = new Region();
			commune.setPkRegion(resultSet.getInt("pk_commune"));
			commune.setName(resultSet.getString("name"));
			commune.setFkRegion(resultSet.getInt("fk_municipality"));
			
			return commune;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get commune by id", e);
		}		
	}
}
