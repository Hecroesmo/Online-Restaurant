package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.Product;

public class ProductDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public ProductDao(Connection connection) {
		this.connection = connection;
	}
	
	public void save(Product product) {
		String sql = "INSERT INTO product(name, quantity, description,"
			+ " fk_category, price) VALUES (?, ?, ?, ?, ?);";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, product.getName());
			statement.setInt(2, product.getQuantity());
			statement.setString(3, product.getDescription());
			statement.setInt(4, product.getCategory().getPkCategory());
			statement.setDouble(5, product.getPrice());
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to save product", e);
		}
	}
	
	public List<Product> getProducts() {
		String sql = "SELECT * FROM product";
				
		try 
		{
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			List<Product> products = new ArrayList<Product>();
			
			do 
			{
				Product product = new Product();
				product.setPkProduct(resultSet.getInt("pk_product"));
				product.setName(resultSet.getString("name"));
				product.setQuantity(resultSet.getInt("quantity"));
				product.setDescription(resultSet.getString("description"));
				
				product.setCategory(new CategoryDao(connection).getCategoryById(
					resultSet.getInt("fk_category")));	
				
				product.setPrice(resultSet.getDouble("price"));
				
				products.add(product);
			} 
			while (resultSet.next());
			
			return products;
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to get products", e);
		}
	}
	
	public Product getProductById(int id) {
		String sql = "SELECT * FROM product WHERE pk_product = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Product product = new Product();
			product.setPkProduct(resultSet.getInt("pk_product"));
			product.setName(resultSet.getString("name"));
			product.setQuantity(resultSet.getInt("quantity"));
			product.setDescription(resultSet.getString("description"));
			
			product.setCategory(new CategoryDao(connection).getCategoryById(
				resultSet.getInt("fk_category")));		
			
			product.setPrice(resultSet.getDouble("price"));
			
			return product;
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to get product by id", e);
		}
	}
	
	public Product getProductByName(String name) {
		String sql = "SELECT * FROM product WHERE name = ?;";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Product product = new Product();
			product.setPkProduct(resultSet.getInt("pk_product"));
			product.setName(resultSet.getString("name"));
			product.setQuantity(resultSet.getInt("quantity"));
			product.setDescription(resultSet.getString("description"));
			
			product.setCategory(new CategoryDao(connection).getCategoryById(
				resultSet.getInt("fk_category")));
			
			product.setPrice(resultSet.getDouble("price"));
			
			product.setImage(new ImageDao(connection).getImageById(
				resultSet.getInt("pk_product")));
			
			return product;
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to get product by name", e);
		}
	}
	
	public void removeProductById(int id) {
		String sql = "DELETE FROM product WHERE pk_product=?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.executeUpdate();
		}
		catch (SQLException e) {
			
		}
	}
}
