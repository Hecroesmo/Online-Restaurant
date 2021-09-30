package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.Category;
import restaurant.model.Product;
import restaurant.utility.Constants;

public class ProductDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public ProductDao(Connection connection) {
		this.connection = connection;
	}
	
	public void save(Product product) {
		String sql = "INSERT INTO public.product(name, quantity, description,"
			+ " price, fk_fcategory, fk_scategory, fk_tcategory) VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, product.getName());
			statement.setInt(2, product.getQuantity());
			statement.setString(3, product.getDescription());
			statement.setDouble(4, product.getPrice());
			
			Category [] categories = product.getCategories();
			
			for (int counter = 0; counter < categories.length; counter++) {
				statement.setInt( counter + 5, categories[counter].getPkCategory() );
			}
			
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
				product.setPrice(resultSet.getDouble("price"));
				
				Category category [] = new Category[ Constants.CATEGORY_NUMBER ];
				
				for (int counter = 0; counter < Constants.CATEGORY_NUMBER; counter++) {
					int categoryId = resultSet.getInt( Constants.CATEGORY_NAME[counter] );
					category[counter] = new CategoryDao(connection).getCategoryById(categoryId);
				}
				
				product.setCategories(category);
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
			product.setPrice(resultSet.getDouble("price"));
			
			Category category [] = new Category[ Constants.CATEGORY_NUMBER ];
			
			for (int counter = 0; counter < Constants.CATEGORY_NUMBER; counter++) {
				int categoryId = resultSet.getInt( Constants.CATEGORY_NAME[counter] );
				category[counter] = new CategoryDao(connection).getCategoryById(categoryId);
			}
			
			product.setCategories(category);			
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
			product.setPrice(resultSet.getDouble("price"));
			
			Category category [] = new Category[ Constants.CATEGORY_NUMBER ];
			
			for (int counter = 0; counter < Constants.CATEGORY_NUMBER; counter++) {
				int categoryId = resultSet.getInt( Constants.CATEGORY_NAME[counter] );
				category[counter] = new CategoryDao(connection).getCategoryById(categoryId);
			}
			
			product.setCategories(category);			
			product.setImage(new ImageDao(connection).getImageById(
				resultSet.getInt("pk_product")));
			return product;
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to get product by name", e);
		}
	}
	
	public void alterProductById(Product product) {
		String sql = "UPDATE product SET name=?, quantity=?, description=?,"
			+ " price=?, fk_fcategory=?, fk_scategory=?, fk_tcategory=? WHERE pk_product=?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, product.getName());
			statement.setInt(2, product.getQuantity());
			statement.setString(3, product.getDescription());
			statement.setDouble(4, product.getPrice());
			
			Category [] categories = product.getCategories();
			int counter;
			
			for (counter = 0; counter < categories.length; counter++) {
				statement.setInt( counter + 5, categories[counter].getPkCategory() );
			}
			
			statement.setInt(counter, product.getPk_product());
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

}
