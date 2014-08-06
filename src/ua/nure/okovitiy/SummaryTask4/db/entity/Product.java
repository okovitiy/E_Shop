package ua.nure.okovitiy.SummaryTask4.db.entity;

import java.sql.Date;

/**
 * Product entity.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class Product extends Entity {

	private static final long serialVersionUID = 4959794966827904335L;

	private String name;

	private int price;

	private String color;

	private String size;

	private Date novelty;

	private int quantity;

	private int categoryId;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setNovelty(Date novelty) {
		this.novelty = novelty;
	}

	public Date getNovelty() {
		return novelty;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", color="
				+ color + ", size=" + size + ", novelty=" + novelty
				+ ", quantity=" + quantity + ", categoryId=" + categoryId
				+ ", getId()=" + getId() + "]";
	}
}
