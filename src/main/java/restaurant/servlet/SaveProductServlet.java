package restaurant.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import restaurant.dao.ImageDao;
import restaurant.dao.ProductDao;
import restaurant.model.Category;
import restaurant.model.Image;
import restaurant.model.Product;

@WebServlet("/SaveProductServlet")
@MultipartConfig

public class SaveProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException 
	{
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		String firstCategory = request.getParameter("fcategory");
		String secondCategory = request.getParameter("scategory");
		String thirdCategory = request.getParameter("tcategory");
		Part filePart = request.getPart("image");
		
		InputStream fileContent = filePart.getInputStream();
		
		/*	String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();	*/
		
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setQuantity(Integer.parseInt(quantity));
		product.setPrice(Double.parseDouble(price));
		
		String categories_ [] = { firstCategory, secondCategory, thirdCategory };
		LinkedList<Category> categories = new LinkedList<Category>();
		
		for (int counter = 0; counter < categories_.length; counter++) {
			if ( !categories_[counter].equals("Selecione...") ) {
				categories.add(new Category(Integer.parseInt(categories_[counter])));
			}
		}
		
		product.setCategories(categories);
		Connection connection = (Connection) request.getAttribute("connection");
		ProductDao productDao = new ProductDao(connection);
		ImageDao imageDao = new ImageDao(connection);
		
		if (id != null) {
			Product newProduct = productDao.getProductByName(name);
			product.setPk_product(newProduct.getPk_product());
			productDao.alter(product);	
			
			if (fileContent.available() != 0) {
				product.setImage(new Image(fileContent));
				imageDao.alter(product);	
			}
		}
		else {
			productDao.save(product);
			Product newProduct = productDao.getProductByName(name);
			newProduct.setImage(new Image(fileContent));
			imageDao.save(newProduct);
		}
			
		request.getRequestDispatcher("register-product.jsp").forward(request, response);
	}
}
