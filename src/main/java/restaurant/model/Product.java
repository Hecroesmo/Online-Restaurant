package restaurant.model;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pk_product;
	private String name;
	private String description;
	private int quantity;
	private double price;
	private Image image;
	private Category category;
	
	public Product() {}
	
	public Product(int id, String name, String description, int quantity,
			double price, Image image, Category category)
	{
		this.pk_product = id;
		this.name = name;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
		this.image = image;
		this.category = category;
	}

	public int getPkProduct() {
		return pk_product;
	}

	public void setPkProduct(int id) {
		this.pk_product = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [id=" + pk_product + ", name=" + name + ", description=" +
				description + ", quantity=" + quantity + ", price=" + price +
				", image=" + image + ", category=" + category + "]";
	}

	

}
