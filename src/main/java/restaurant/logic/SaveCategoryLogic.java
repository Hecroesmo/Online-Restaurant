package restaurant.logic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import restaurant.dao.CategoryDao;
import restaurant.interface_.Logic;
import restaurant.model.Category;

public class SaveCategoryLogic implements Logic {
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String firstCategory = req.getParameter("fcategory");
		String secondCategory = req.getParameter("scategory");
		String thirdCategory = req.getParameter("tcategory");
		String name = req.getParameter("name");
		
		Connection connection = (Connection) req.getAttribute("connection");
		
		Category category = new Category();
		category.setName(name);
		
		if (!thirdCategory.equals("Selecione...")) {
			category.setFkCategory(Integer.parseInt(thirdCategory));
		}
		else if (!secondCategory.equals("Selecione...")) {
			category.setFkCategory(Integer.parseInt(secondCategory));
		}
		else if (!firstCategory.equals("Selecione...")) {
			category.setFkCategory(Integer.parseInt(firstCategory));
		}
		
		if (!category.getName().equals("")) 
			new CategoryDao(connection).save(category);
		
		return "register-category.jsp";
	}

}
