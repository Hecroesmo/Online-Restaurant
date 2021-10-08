package restaurant.utility;

public class Constants {
	public static final String [] CATEGORY_NAME = {
		"fk_fcategory",
		"fk_scategory",
		"fk_tcategory"
	};
	
	public static final int CATEGORY_NUMBER = CATEGORY_NAME.length;
	
	public static final String [] SAVE_QUERIES = {
		"",
		"INSERT INTO product(name, quantity, description, price,"
			+ " fk_fcategory) VALUES (?, ?, ?, ?, ?)",
		
		"INSERT INTO product(name, quantity, description, price,"
			+ " fk_fcategory, fk_scategory) VALUES (?, ?, ?, ?, ?, ?)",		
		
		"INSERT INTO product(name, quantity, description, price,"
			+ " fk_fcategory, fk_scategory, fk_tcategory) VALUES (?, ?, ?, ?, ?, ?, ?)"
	};
	
	public static final String [] UPDATE_QUERIES = {
		"",
		"UPDATE product SET name=?, quantity=?, description=?,"
			+ " price=?, fk_fcategory=? WHERE pk_product=?",
			
		"UPDATE product SET name=?, quantity=?, description=?,"
			+ " price=?, fk_fcategory=?, fk_scategory=? WHERE pk_product=?",		
			
		"UPDATE product SET name=?, quantity=?, description=?,"
			+ " price=?, fk_fcategory=?, fk_scategory=?, fk_tcategory=? WHERE pk_product=?"
		};
}
