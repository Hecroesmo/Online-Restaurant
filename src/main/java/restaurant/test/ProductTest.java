package restaurant.test;

import restaurant.connection.ConnectionFactory;
import restaurant.dao.ProductDao;

public class ProductTest {

	public static void main(String[] args) {
		System.out.println(new ProductDao(
			new ConnectionFactory().getConnection()).getProductByCategoryName("Fast Food"));
	}
}
