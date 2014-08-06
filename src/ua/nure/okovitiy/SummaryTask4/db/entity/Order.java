package ua.nure.okovitiy.SummaryTask4.db.entity;

/**
 * Order entity.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class Order extends Entity {

	private static final long serialVersionUID = -1319478949818814841L;

	private int userID;

	private int statusID;

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	@Override
	public String toString() {
		return "Order [userID=" + userID + ", statusID=" + statusID
				+ ", getId()=" + getId() + "]";
	}

}
