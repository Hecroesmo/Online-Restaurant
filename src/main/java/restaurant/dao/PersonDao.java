package restaurant.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import restaurant.model.Person;

public class PersonDao {
	private final Connection connection;
	private PreparedStatement statement;
	
	public PersonDao(Connection connection) {
		this.connection = connection;
	}
	
	public void save(Person person) {
		String sql = "INSERT INTO public.person(first_name, last_name, birth_date,"
			+ " phone_number, email, road, fk_province, fk_municipality,"
			+ " fk_commune, fk_neighborhood) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, person.getFirstName());
			statement.setString(2, person.getLastName());
			
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(
				person.getBirthDate());
			
			statement.setDate(3, new java.sql.Date(date.getTime()));
			statement.setString(4, person.getPhoneNumber());
			statement.setString(5, person.getEmail());
			statement.setString(6, person.getRoad());
			statement.setInt(7, Integer.parseInt(person.getProvince()));
			statement.setInt(8, Integer.parseInt(person.getMunicipality()));
			statement.setInt(9, Integer.parseInt(person.getCommune()));
			statement.setInt(10, Integer.parseInt(person.getNeighborhood()));
			statement.executeUpdate();
			
		} 
		catch (Exception e)
		{
			throw new RuntimeException("Fail to save this person", e);
		}
	}
	
	public Person getPersonByPhoneNumber(String phoneNumber) {
		String sql = "SELECT * FROM person WHERE phone_number = ?;";
		
		try 
		{
			statement = connection.prepareStatement(sql);
			statement.setString(1, phoneNumber);
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next() == false) return null;
			
			Person person = new Person();
			person.setId(resultSet.getInt("pk_person"));
			person.setFirstName(resultSet.getString("first_name"));
			person.setLastName(resultSet.getString("last_name"));
			
			Date date = new Date(resultSet.getDate("birth_date").getTime());
			
			person.setBirthDate(date.toString());
			person.setPhoneNumber(resultSet.getString("phone_number"));
			person.setEmail(resultSet.getString("email"));
			person.setRoad(resultSet.getString("road"));
			
			person.setProvince(new ProvinceDao(connection).getProvinceById(
				resultSet.getInt("fk_province")).getName());
			
			person.setMunicipality(new MunicipalityDao(connection).getMunicipalityById(
				resultSet.getInt("fk_municipality")).getName());
			
			person.setCommune(new CommuneDao(connection).getCommuneById(
				resultSet.getInt("fk_commune")).getName());
			
			person.setNeighborhood(new NeighborhoodDao(connection).getNeighborhoodById(
				resultSet.getInt("fk_neighborhood")).getName());
			
			return person;
		} 
		catch (Exception e)
		{
			throw new RuntimeException("Fail to get person by phone number", e);
		}
	}
}
