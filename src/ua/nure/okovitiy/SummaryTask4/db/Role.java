package ua.nure.okovitiy.SummaryTask4.db;

import ua.nure.okovitiy.SummaryTask4.db.entity.User;

/**
 * Role entity.
 * 
 * @author Andrew Okovitiy
 * 
 */

public enum Role {
	ADMIN, CLIENT, BLOCKED_CLIENT;
	
	/**
	 * @param object User
	 * 
	 * @return Role
	 */
	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}
	
	/**
	 * @return String
	 * of the role
	 */
	public String getName() {
		return name().toLowerCase();
	}
	
}
