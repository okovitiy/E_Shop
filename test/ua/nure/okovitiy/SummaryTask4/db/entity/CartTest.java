package ua.nure.okovitiy.SummaryTask4.db.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CartTest {

	Cart cart;

	@Before
	public void setUp() throws Exception {
		cart = new Cart();
		cart.setId(1);
		cart.setOrderId(2);
		cart.setPrice(10);
		cart.setProductId(3);
		cart.setQuantity(5);
	}

	@After
	public void tearDown() throws Exception {
		cart = null;
	}

	@Test
	public void testGetProductId() {
		assertTrue(cart.getProductId() == 3);
	}

	@Test
	public void testGetPrice() {
		assertTrue(cart.getPrice() == 10);
	}

	@Test
	public void testGetQuantity() {
		assertTrue(cart.getQuantity() == 5);
	}

	@Test
	public void testGetOrderId() {
		assertTrue(cart.getOrderId() == 2);
	}

	@Test
	public void testToString() {
		String c = "Cart [orderId=2, productId=3, price=10, quantity=5]";
		assertEquals(cart.toString(), c);
	}

	@Test
	public void testGetId() {
		assertTrue(cart.getId() == 1);
	}

}
