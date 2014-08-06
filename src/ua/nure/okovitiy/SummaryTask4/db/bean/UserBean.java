package ua.nure.okovitiy.SummaryTask4.db.bean;

import ua.nure.okovitiy.SummaryTask4.db.entity.Entity;

/**
 * Provide records for virtual table:
 * 
 * <pre>
 * |user.id|user.login|user.name|role.name|
 * </pre>
 * 
 * @author Andrew Okovitiy
 * 
 */
public class UserBean extends Entity {

	private static final long serialVersionUID = 8595538051521681896L;

	private int userId;

	private String userLogin;

	private String userName;
	
	private String userEmail;

	private String roleName;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "UserBean [userId=" + userId + ", userLogin=" + userLogin
				+ ", userName=" + userName + ", userEmail=" + userEmail
				+ ", roleName=" + roleName + "]";
	}
	

}
