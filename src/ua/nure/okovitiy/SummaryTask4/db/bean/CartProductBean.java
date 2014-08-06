package ua.nure.okovitiy.SummaryTask4.db.bean;

import ua.nure.okovitiy.SummaryTask4.db.entity.Entity;

/**
 * Provide records for virtual table:
 * 
 * <pre>
 * |product.name|product.price|product.name|
 * </pre>
 * 
 * @author Andrew Okovitiy
 * 
 */
public class CartProductBean extends Entity {

	private static final long serialVersionUID = 856013419141560445L;

	private String productName;

	private int price;

	private int quantity;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartProductBean [productName=" + productName + ", price="
				+ price + ", quantity=" + quantity + ", getId()=" + getId()
				+ "]";
	}

}
