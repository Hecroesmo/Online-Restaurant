package restaurant.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

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
import restaurant.utility.Constants;

@WebServlet("/SaveProductServlet")
@MultipartConfig

public class SaveProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException 
	{
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		String firstCategory = request.getParameter("fcategory");
		String secondCategory = request.getParameter("scategory");
		String thirdCategory = request.getParameter("tcategory");
		Part filePart = request.getPart("image");
		InputStream fileContent = filePart.getInputStream();
		
		/*	String fileName = Paths.get(
			filePart.getSubmittedFileName()).getFileName().toString();	*/
		
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setQuantity(Integer.parseInt(quantity));
		product.setPrice(Double.parseDouble(price));
		
		String categories_ [] = { firstCategory, secondCategory, thirdCategory };
		Category [] categories = new Category[ Constants.CATEGORY_NUMBER ];
		
		for (int counter = 0; counter < categories_.length; counter++) {
			if ( !categories_[counter].equals("Selecione...") ) {
				categories[counter] = new Category();
				categories[counter].setPkCategory(Integer.parseInt(categories_[counter]));
			}
		}
		
		product.setCategories(categories);
		
		Connection connection = (Connection) request.getAttribute("connection");
		new ProductDao(connection).save(product);
		Product newProduct = new ProductDao(connection).getProductByName(name);
		newProduct.setImage(new Image(fileContent));
		new ImageDao(connection).save(newProduct);		
		request.getRequestDispatcher("register-product.jsp").forward(request, response);
	}
}
