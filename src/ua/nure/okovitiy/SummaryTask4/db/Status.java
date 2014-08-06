package ua.nure.okovitiy.SummaryTask4.db;

import ua.nure.okovitiy.SummaryTask4.db.entity.Order;

/**
 * Status entity.
 * 
 * @author Andrew Okovitiy
 * 
 */
public enum Status {
	OPENED, REGISTERED, PAID, CLOSED, CANCELED;
	
	/**
	 * @param object Order
	 * 
	 * @return Status
	 */
	public static Status getStatus(Order order) {
		int statusId = order.getStatusID();
		return Status.values()[statusId];
	}
	
	/**
	 * @return String
	 * of the status
	 */
	public String getName() {
		return name().toLowerCase();
	}
}