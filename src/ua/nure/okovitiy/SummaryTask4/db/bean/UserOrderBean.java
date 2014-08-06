package ua.nure.okovitiy.SummaryTask4.db.bean;

import ua.nure.okovitiy.SummaryTask4.db.entity.Entity;

/**
 * Provide records for virtual table:
 * 
 * <pre>
 * |order.id|user.login|user.name|cart.price|status.name|
 * </pre>
 * 
 * @author Andrew Okovitiy
 * 
 */
public class UserOrderBean extends Entity {

	private static final long serialVersionUID = 8069679092818949823L;

	private int orderId;

	private String userLogin;

	private String userName;

	private int cartPrice;

	private String statusName;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public int getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(int cartPrice) {
		this.cartPrice = cartPrice;
	}

	@Override
	public String toString() {
		return "UserOrderBean [orderId=" + orderId + ", userLogin=" + userLogin
				+ ", userName=" + userName + ", cartPrice=" + cartPrice
				+ ", statusName=" + statusName + ", getId()=" + getId() + "]";
	}

}
