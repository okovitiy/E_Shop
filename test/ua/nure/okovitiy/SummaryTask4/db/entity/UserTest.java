package ua.nure.okovitiy.SummaryTask4.db.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	User us;

	@Before
	public void setUp() throws Exception {
		us = new User();
		us.setEmail("email");
		us.setId(1);
		us.setLocaleName("ru");
		us.setLogin("login");
		us.setName("name");
		us.setPassword("pas");
		us.setRoleId(2);
	}

	@After
	public void tearDown() throws Exception {
		us = null;
	}

	@Test
	public void testGetName() {
		assertEquals(us.getName(), "name");
	}

	@Test
	public void testGetLogin() {
		assertEquals(us.getLogin(), "login");
	}

	@Test
	public void testGetPassword() {
		assertEquals(us.getPassword(), "pas");
	}

	@Test
	public void testGetEmail() {
		assertEquals(us.getEmail(), "email");
	}

	@Test
	public void testGetRoleId() {
		assertTrue(us.getRoleId() == 2);
	}

	@Test
	public void testGetLocaleName() {
		assertEquals(us.getLocaleName(), "ru");
	}

	@Test
	public void testToString() {
		String user = "User [name=name, login=login, password=pas, "
				+ "email=email, localeName=ru, roleId=2, getId()=1]";
		assertEquals(us.toString(), user);
	}

	@Test
	public void testGetId() {
		assertTrue(us.getId() == 1);
	}

}
