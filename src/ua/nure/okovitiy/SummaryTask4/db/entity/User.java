package ua.nure.okovitiy.SummaryTask4.db.entity;

/**
 * User entity.
 * 
 * @author Andrew Okovitiy
 * 
 */
public class User extends Entity {

	private static final long serialVersionUID = -6472262064525409611L;

	private String name;

	private String login;

	private String password;

	private String email;

	private String localeName;

	private int roleId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getLocaleName() {
		return localeName;
	}

	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", login=" + login + ", password="
				+ password + ", email=" + email + ", localeName=" + localeName
				+ ", roleId=" + roleId + ", getId()=" + getId() + "]";
	}

}
