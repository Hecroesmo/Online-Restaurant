package restaurant.test;

import restaurant.connection.ConnectionFactory;
import restaurant.dao.PersonDao;
import restaurant.model.Person;

public class PersonTest {

	public static void main(String[] args) {
		Person person = new Person();
		person.setFirstName("Hecroesmo");
		person.setLastName("De Jesus");
		person.setBirthDate("2021-08-05");
		person.setPhoneNumber("946713538");
		person.setEmail("hecroesmo.jesus77@gmail.com");
		person.setProvince("1");
		person.setMunicipality("1");
		person.setCommune("1");
		person.setNeighborhood("1");
		person.setRoad("João Pinto");
		
		//	new PersonDao(new ConnectionFactory().getConnection()).save(person);
		System.out.println(new PersonDao(new ConnectionFactory().getConnection()).getPersonByPhoneNumber(person.getPhoneNumber()));
	}

}
