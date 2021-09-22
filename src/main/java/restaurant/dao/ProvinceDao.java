package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.Region;

public class ProvinceDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public ProvinceDao(Connection connection) {
		this.connection = connection;
	}
	
	public List<Region> getAllProvinces() {
		String sql = "SELECT * FROM province";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			List<Region> regions = new ArrayList<Region>();
			
			do 
			{
				Region region = new Region();
				region.setPkRegion(resultSet.getInt("pk_province"));
				region.setName(resultSet.getString("name"));
				regions.add(region);
			}
			while(resultSet.next());
			
			return regions;
		}
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get all provinces", e);
		}
	}
	
	public Region getProvinceByName(String name) {
		String sql = "SELECT * FROM province WHERE name = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Region province = new Region();
			province.setPkRegion(resultSet.getInt("pk_province"));
			province.setName(resultSet.getString("name"));
			
			return province;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get province by name", e);
		}		
	}
	
	public Region getProvinceById(int id) {
		String sql = "SELECT * FROM province WHERE pk_province = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Region province = new Region();
			province.setPkRegion(resultSet.getInt("pk_province"));
			province.setName(resultSet.getString("name"));
			
			return province;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get province by id", e);
		}		
	}
}
