package restaurant.dao;

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
		String sql = "INSERT INTO image(fk_product, image) VALUES (?, ?);";
		
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
}
