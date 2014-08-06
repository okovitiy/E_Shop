package ua.nure.okovitiy.SummaryTask4.db;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.nure.okovitiy.SummaryTask4.db.entity.User;

public class RoleTest {

	@Test
	public void testGetRole() {
		User user = new User();
		user.setId(1);
		assertEquals(Role.ADMIN, Role.getRole(user));
	}

	@Test
	public void testGetName() {
		String admin = "admin";
		assertEquals(admin, Role.values()[0].getName());
	}
	
	@Test
	public void coverEnum() {
		Role.valueOf(Role.values()[0].name());
	}

}
