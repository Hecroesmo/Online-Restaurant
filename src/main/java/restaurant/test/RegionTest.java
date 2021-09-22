package restaurant.test;

import restaurant.connection.ConnectionFactory;
import restaurant.dao.CommuneDao;
import restaurant.dao.MunicipalityDao;
import restaurant.dao.NeighborhoodDao;
import restaurant.dao.ProvinceDao;

public class RegionTest {

	public static void main(String[] args) {
		System.out.println(new NeighborhoodDao(
			new ConnectionFactory().getConnection()).getNeighborhoodById(1));

	}

}
