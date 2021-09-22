package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.Region;

public class MunicipalityDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public MunicipalityDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Region> getMunicipalitiesByFkey(int fkey) {
		String sql = "SELECT * FROM municipality WHERE fk_province = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, fkey);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			List<Region> municipalities = new ArrayList<Region>();
			
			do 
			{
				Region municipality = new Region();
				municipality.setPkRegion(resultSet.getInt("pk_municipality"));
				municipality.setName(resultSet.getString("name"));
				municipality.setFkRegion(resultSet.getInt("fk_province"));
				municipalities.add(municipality);
			}
			while (resultSet.next());
			
			return municipalities;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("", e);
		}
	}
	
	public Region getMunicipalityByName(String name) {
		String sql = "SELECT * FROM municipality WHERE name = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Region municipality = new Region();
			municipality.setPkRegion(resultSet.getInt("pk_municipality"));
			municipality.setName(resultSet.getString("name"));
			municipality.setFkRegion(resultSet.getInt("fk_province"));
			return municipality;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get municipality by name", e);
		}		
	}
	
	public Region getMunicipalityById(int id) {
		String sql = "SELECT * FROM municipality WHERE pk_municipality = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Region municipality = new Region();
			municipality.setPkRegion(resultSet.getInt("pk_municipality"));
			municipality.setName(resultSet.getString("name"));
			municipality.setFkRegion(resultSet.getInt("fk_province"));
			
			return municipality;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get municipality by id", e);
		}		
	}
}
