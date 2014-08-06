package ua.nure.okovitiy.SummaryTask4.tag;

import ua.nure.okovitiy.SummaryTask4.db.entity.User;

/**
 * User function/
 * 
 * @author Andrew Okovitiy
 * 
 */
public class UserFunction {

	/**
	 * @param User
	 * 
	 * @return String 
	 * 				name with role of the user 	
	 */
	public static String nameWithRole(User user) {
		String role = "";
		if(user.getRoleId() == 0){
			role = "admin";
		} else {
			if(user.getRoleId() == 1){
				role = "user";
			} else {
				role = "";
			}
		}
		return user.getName() + " (" + role + ")";
	}
}