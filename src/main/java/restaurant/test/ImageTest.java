package restaurant.test;

import restaurant.connection.ConnectionFactory;
import restaurant.dao.ImageDao;

public class ImageTest {

	public static void main(String[] args) {
		System.out.println(
			new ImageDao(new ConnectionFactory().getConnection()).getImageById(6));
	}
}
