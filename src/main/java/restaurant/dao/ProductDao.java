package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import restaurant.model.Category;
import restaurant.model.Image;
import restaurant.model.Product;
import restaurant.utility.Constants;

public class ProductDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public ProductDao(Connection connection) {
		this.connection = connection;
	}
	
	public void save(Product product) {
		String sql = Constants.SAVE_QUERIES[product.getCategories().size()];
		
		try 
		{
			statement = connection.prepareStatement(sql);
			setProductsFields(statement, product);
			
			List<Category> categories = product.getCategories();
			final int FIVE = 5;
			int counter = 0;
			
			setCategories(categories, statement, FIVE, counter);
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
				setProductFields(resultSet, product);
				
				LinkedList<Category> categories = new LinkedList<Category>();				
				setCategories(categories, resultSet);
				product.setCategories(categories);
				
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
			setProductFields(resultSet, product);
			
			LinkedList<Category> categories = new LinkedList<Category>();				
			setCategories(categories, resultSet);
			product.setCategories(categories);
			
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
			setProductFields(resultSet, product);
			
			LinkedList<Category> categories = new LinkedList<Category>();				
			setCategories(categories, resultSet);
			product.setCategories(categories);	
			
			return product;
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to get product by name", e);
		}
	}
	
	public List<Product> getProductByCategoryName(String categoryName) {
		String sql = "SELECT * FROM product "
			+ "WHERE fk_fcategory = ? or fk_scategory = ? or fk_tcategory = ?";
		
		try 
		{
			Category category = new CategoryDao(connection).getCategoryByName("Fast Food");
			statement = connection.prepareStatement(sql);
			statement.setInt(1, category.getPkCategory());
			statement.setInt(2, category.getPkCategory());
			statement.setInt(3, category.getPkCategory());
			
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			List<Product> products = new LinkedList<Product>();
			
			do 
			{
				Product product = new Product();
				setProductFields(resultSet, product);
				LinkedList<Category> categories = new LinkedList<Category>();				
				setCategories(categories, resultSet);
				product.setCategories(categories);
				
				product.setImage(new Image(
					new ImageDao(connection).getImageBytesById(product.getPk_product())));
				
				products.add(product);
			} 
			while ( resultSet.next() );	
			
			return products;
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to get product by category name", e);
		}
	}
	
	public void alter(Product product) {
		String sql = Constants.UPDATE_QUERIES[product.getCategories().size()];
		
		try 
		{
			statement = connection.prepareStatement(sql);
			setProductsFields(statement, product);
			
			List<Category> categories = product.getCategories();
			final int FIVE = 5;
			int counter = 0;
			
			counter = setCategories(categories, statement, FIVE, counter);
			statement.setInt(counter + FIVE, product.getPk_product());
			statement.executeUpdate();
		}
		catch (SQLException e) {
			throw new RuntimeException("Could not alter this product", e);
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
			throw new RuntimeException("Fail to remove the product", e);
		}
	}
	
	//---------------------------------------------------------- Support methods
	
	private int setCategories(List<Category> categories,
		PreparedStatement statement, int FIVE, int counter) throws SQLException 
	{
		for (counter = 0; counter < categories.size(); counter++) {
			statement.setInt( counter + FIVE, categories.get(counter).getPkCategory() );
		}
		
		return counter;
	}
	
	private void setCategories(List<Category> categories, ResultSet rs)
			throws SQLException 
	{
		int categoryId, counter = 0;
		
		do 
		{
			categoryId = rs.getInt(
				Constants.CATEGORY_NAME[counter]);
			
			categories.add(
				new CategoryDao(connection).getCategoryById(categoryId));
			
			counter++;
		} 
		while (categoryId != 0 && counter < Constants.CATEGORY_NUMBER);
	}
	
	private void setProductsFields(PreparedStatement statement, Product product )
			throws SQLException 
	{
		statement.setString(1, product.getName());
		statement.setInt(2, product.getQuantity());
		statement.setString(3, product.getDescription());
		statement.setDouble(4, product.getPrice());
	}
	
	private void setProductFields(ResultSet rs, Product product) 
			throws SQLException 
	{
		product.setPkProduct(rs.getInt("pk_product"));
		product.setName(rs.getString("name"));
		product.setQuantity(rs.getInt("quantity"));
		product.setDescription(rs.getString("description"));
		product.setPrice(rs.getDouble("price"));
	}
}
