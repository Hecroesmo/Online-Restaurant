package restaurant.model;

public class Employee extends Person {
	private static final long serialVersionUID = 1L;
	private String socialSecurityNumber;
	private double salary;
	
	public Employee() {}

	public Employee(String socialSecurityNumber, double salary) {
		this.socialSecurityNumber = socialSecurityNumber;
		this.salary = salary;
	}

	public Employee(String firstName, String lastName, String birthDate,
			String phoneNumber, String email, String province,
			String municipality, String commune, String neighborhood,
			String road, Account account,String socialSecurityNumber, double salary)
	{
		super(firstName, lastName, birthDate, phoneNumber, email, province,
			municipality, commune, neighborhood, road,account);
		this.socialSecurityNumber = socialSecurityNumber;
		this.salary = salary;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [socialSecurityNumber=" + socialSecurityNumber +
			", salary=" + salary + "]";
	}
}
