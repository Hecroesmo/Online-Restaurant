package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import restaurant.model.Category;

public class CategoryDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public CategoryDao(Connection connection) {
		this.connection = connection;
	}
	
	public void save(Category category) {
		boolean isZero = category.getFkCategory() == 0;
		String sql = "INSERT INTO category (name, fk_category) VALUES (?,?)";
		
		if (isZero) {
			sql = "INSERT INTO category (name) VALUES (?)"; 
		}
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, category.getName());
			
			if (!isZero) statement.setInt(2, category.getFkCategory());
			
			statement.executeUpdate();
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to save this category", e);
		}
	}
	
	public Category getCategoryById(int id) {
		String sql = "SELECT * FROM category WHERE pk_category = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Category category = new Category();
			category.setPkCategory(resultSet.getInt("pk_category"));
			category.setName(resultSet.getString("name"));
			category.setFkCategory(resultSet.getInt("fk_category"));
			
			return category;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get categories by id", e);
		}
	}
	
	public List<Category> getFirstLevelCategories() {
		String sql = "SELECT * FROM category WHERE fk_category IS NULL";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			List<Category> categories = new ArrayList<Category>();
			
			do 
			{
				Category category = new Category();
				category.setPkCategory(resultSet.getInt("pk_category"));
				category.setName(resultSet.getString("name"));
				categories.add(category);
			}while (resultSet.next());
			
			return categories;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get first level categories", e);
		}
	}
	
	public List<Category> getCategoriesById(int id) {
		String sql = "SELECT * FROM category WHERE fk_category = ?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			List<Category> categories = new ArrayList<Category>();
			
			do 
			{
				Category category = new Category();
				category.setPkCategory(resultSet.getInt("pk_category"));
				category.setName(resultSet.getString("name"));
				category.setFkCategory(resultSet.getInt("fk_category"));
				categories.add(category);
			}while (resultSet.next());
			
			return categories;
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException("Fail to get categories by id", e);
		}
	}
	
	public Category getParentCategoryById(int id) {
		String sql = "SELECT * FROM category WHERE fk_category=?";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Category category = new Category();
			category.setPkCategory(resultSet.getInt("pk_category"));
			category.setName(resultSet.getString("name"));
			category.setFkCategory(resultSet.getInt("fk_category"));
			
			return category;
		} 
		catch (SQLException e) {
			throw new RuntimeException("Fail to get the parent category", e);
		}
	}
}
