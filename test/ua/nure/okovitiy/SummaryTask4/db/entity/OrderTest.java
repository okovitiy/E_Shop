package ua.nure.okovitiy.SummaryTask4.db.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {

	Order or;

	@Before
	public void setUp() throws Exception {
		or = new Order();
		or.setId(1);
		or.setStatusID(2);
		or.setUserID(3);
	}

	@After
	public void tearDown() throws Exception {
		or = null;
	}

	@Test
	public void testGetStatusID() {
		assertTrue(or.getStatusID() == 2);
	}

	@Test
	public void testGetUserID() {
		assertTrue(or.getUserID() == 3);
	}

	@Test
	public void testToString() {
		String order = "Order [userID=3, statusID=2, getId()=1]";
		assertEquals(or.toString(), order);
	}

	@Test
	public void testGetId() {
		assertTrue(or.getId() == 1);
	}

}
