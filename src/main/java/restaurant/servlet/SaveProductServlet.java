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
		Part filePart = request.getPart("image");
		String firstCategory = request.getParameter("fcategory");
		String secondCategory = request.getParameter("scategory");
		String thirdCategory = request.getParameter("tcategory");
		
		/*	String fileName = Paths.get(
			filePart.getSubmittedFileName()).getFileName().toString();	*/
		
		InputStream fileContent = filePart.getInputStream();
		
		Product product = new Product();
		product.setName(name);
		product.setDescription(description);
		product.setQuantity(Integer.parseInt(quantity));
		product.setPrice(Double.parseDouble(price));
		
		if (!thirdCategory.equals("Selecione...")) {
			product.setCategory(new Category(Integer.parseInt(thirdCategory)));
		}
		else if (!secondCategory.equals("Selecione...")) {
			product.setCategory(new Category(Integer.parseInt(secondCategory)));
		}
		else if (!firstCategory.equals("Selecione...")) {
			product.setCategory(new Category(Integer.parseInt(firstCategory)));
		}
		
		Connection connection = (Connection) request.getAttribute("connection");
		new ProductDao(connection).save(product);
		Product newProduct = new ProductDao(connection).getProductByName(name);
		newProduct.setImage(new Image(fileContent));
		new ImageDao(connection).save(newProduct);
		
		request.getRequestDispatcher("register-product.jsp").forward(request, response);
	}
}
