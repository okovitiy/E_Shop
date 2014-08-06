package ua.nure.okovitiy.SummaryTask4.db.entity;

/**
 * Cart entity.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class Cart extends Entity{

	private static final long serialVersionUID = 3245489378023935232L;

	private int orderId;

	private int productId;

	private int price;

	private int quantity;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "Cart [orderId=" + orderId + ", productId=" + productId
				+ ", price=" + price + ", quantity=" + quantity + "]";
	}

}
