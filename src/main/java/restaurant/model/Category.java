package restaurant.model;

import java.io.Serializable;

public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pkCategory;
	private String name;
	private int fkCategory;
	
	public Category() {}

	public Category(int pkCategory) {
		this.pkCategory = pkCategory;
	}

	public Category(int pkCategory, String name, int fkCategory) {
		this.pkCategory = pkCategory;
		this.name = name;
		this.fkCategory = fkCategory;
	}

	public int getPkCategory() {
		return pkCategory;
	}

	public void setPkCategory(int pkCategory) {
		this.pkCategory = pkCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFkCategory() {
		return fkCategory;
	}

	public void setFkCategory(int fkCategory) {
		this.fkCategory = fkCategory;
	}

	@Override
	public String toString() {
		return "Category [pkCategory=" + pkCategory + ", name=" + name + ", fkCategory=" + fkCategory + "]";
	}
}
