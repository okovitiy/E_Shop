package ua.nure.okovitiy.SummaryTask4.db.bean;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserBeanTest {

	UserBean ub;

	@Before
	public void setUp() throws Exception {
		ub = new UserBean();
		ub.setId(1);
		ub.setRoleName("admin");
		ub.setUserEmail("email");
		ub.setUserId(2);
		ub.setUserLogin("login");
		ub.setUserName("name");
	}

	@After
	public void tearDown() throws Exception {
		ub = null;
	}

	@Test
	public void testGetUserId() {
		assertTrue(ub.getUserId() == 2);
	}

	@Test
	public void testGetUserLogin() {
		assertEquals(ub.getUserLogin(), "login");
	}

	@Test
	public void testGetUserName() {
		assertEquals(ub.getUserName(), "name");
	}

	@Test
	public void testGetUserEmail() {
		assertEquals(ub.getUserEmail(), "email");
	}

	@Test
	public void testGetRoleName() {
		assertEquals(ub.getRoleName(), "admin");
	}

	@Test
	public void testToString() {
		String userBean = "UserBean [userId=2, userLogin=login, " +
				"userName=name, userEmail=email, roleName=admin]";
		assertEquals(ub.toString(), userBean);
	}

	@Test
	public void testGetId() {
		assertTrue(ub.getId() == 1);
	}

}
