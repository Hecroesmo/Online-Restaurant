package restaurant.test;

import restaurant.connection.ConnectionFactory;
import restaurant.dao.CategoryDao;
import restaurant.model.Category;

public class CategoryTest {

	public static void main(String[] args) {
		System.out.println(new CategoryDao(
			new ConnectionFactory().getConnection()).getParentCategoryById(13));
		
		/*	Category category = new Category();
		category.setFkCategory(4);
		category.setName("Nocal");
		new CategoryDao(
				new ConnectionFactory().getConnection()).save(category);	*/
	}
}
