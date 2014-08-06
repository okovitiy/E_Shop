package ua.nure.okovitiy.SummaryTask4.db.bean;

import ua.nure.okovitiy.SummaryTask4.db.entity.Entity;

/**
 * Provide records for virtual table:
 * 
 * <pre>
 * |status.name|
 * </pre>
 * 
 * @author Andrew Okovitiy
 * 
 */
public class OrderBean extends Entity {

	private static final long serialVersionUID = -3481961856143858589L;

	private String statusOrder;

	public String getStatusOrder() {
		return statusOrder;
	}

	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
	}

	@Override
	public String toString() {
		return "OrderBean [statusOrder=" + statusOrder + ", getId()=" + getId()
				+ "]";
	}

}
