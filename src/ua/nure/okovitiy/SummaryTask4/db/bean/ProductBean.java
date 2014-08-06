package ua.nure.okovitiy.SummaryTask4.db.bean;

import java.sql.Date;

import ua.nure.okovitiy.SummaryTask4.db.entity.Entity;

/**
 * Provide records for virtual table:
 * 
 * <pre>
 * |category.name|product.name|product.price|product.color|product.size|product.novelty|product.quantity|
 * </pre>
 * 
 * @author Andrew Okovitiy
 * 
 */
public class ProductBean extends Entity {

	private static final long serialVersionUID = -1876686566433048447L;

	private String category;

	private String name;

	private int price;

	private String color;

	private String size;

	private Date novelty;

	private int quantity;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

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
		return "ProductBean [category=" + category + ", name=" + name
				+ ", price=" + price + ", color=" + color + ", size=" + size
				+ ", novelty=" + novelty + ", quantity=" + quantity
				+ ", getId()=" + getId() + "]";
	}

}
