package ua.nure.okovitiy.SummaryTask4.db.bean;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserOrderBeanTest {

	UserOrderBean uob;

	@Before
	public void setUp() throws Exception {
		uob = new UserOrderBean();
		uob.setCartPrice(10);
		uob.setId(1);
		uob.setOrderId(2);
		uob.setStatusName("paid");
		uob.setUserLogin("login");
		uob.setUserName("name");
	}

	@After
	public void tearDown() throws Exception {
		uob = null;
	}

	@Test
	public void testGetOrderId() {
		assertTrue(uob.getOrderId() == 2);
	}

	@Test
	public void testGetUserLogin() {
		assertEquals(uob.getUserLogin(), "login");
	}

	@Test
	public void testGetUserName() {
		assertEquals(uob.getUserName(), "name");
	}

	@Test
	public void testGetStatusName() {
		assertEquals(uob.getStatusName(), "paid");
	}

	@Test
	public void testGetCartPrice() {
		assertTrue(uob.getCartPrice() == 10);
	}

	@Test
	public void testToString() {
		String userOrderBean = "UserOrderBean [orderId=2, userLogin=login, userName=name, "
				+ "cartPrice=10, statusName=paid, getId()=1]";
		assertEquals(uob.toString(), userOrderBean);
	}

	@Test
	public void testGetId() {
		assertTrue(uob.getId() == 1);
	}

}
