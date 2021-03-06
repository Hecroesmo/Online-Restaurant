package restaurant.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import restaurant.model.Image;
import restaurant.model.Product;

public class ImageDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public ImageDao(Connection connection) {
		this.connection = connection;
	}
	
	public void save(Product product) {
		String sql = "INSERT INTO image(fk_product, image) VALUES (?, ?)";
		
		try
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, product.getPkProduct());
			statement.setBinaryStream(2, product.getImage().getImage());
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to save image", e);
		}
	}
	
	public Image getImageById(int id) {
		String sql = "SELECT * FROM image WHERE fk_product = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Image image = new Image(resultSet.getBinaryStream("image"));
			return image;
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to get image by id", e);
		}
	}
	
	public byte[] getImageBytesById(int id) {
		String sql = "SELECT * FROM image WHERE fk_product = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Image image = new Image(resultSet.getBinaryStream("image"));
			return image.getImage().readAllBytes();
			
		} 
		catch (IOException e) {
			throw new RuntimeException("Fail to get image bytes", e);
		}
		catch (SQLException e) {
			throw new RuntimeException("Fail to get image by id", e);
		}
	}
	
	public void alter(Product product) {
		String sql = "UPDATE image SET image=? WHERE fk_product=?";
		
		try
		{
			statement = connection.prepareStatement(sql);
			statement.setBinaryStream(1, product.getImage().getImage());
			statement.setInt(2, product.getPkProduct());
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to alter image", e);
		}
	}
}
