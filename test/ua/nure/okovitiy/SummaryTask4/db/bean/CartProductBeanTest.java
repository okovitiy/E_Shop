package ua.nure.okovitiy.SummaryTask4.db.bean;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CartProductBeanTest {
	
	CartProductBean cpb;

	@Before
	public void setUp() throws Exception {
		cpb = new CartProductBean();
		cpb.setId(1);
		cpb.setPrice(10);
		cpb.setProductName("TV");
		cpb.setQuantity(2);
	}

	@After
	public void tearDown() throws Exception {
		cpb = null;
	}

	@Test
	public void testGetProductName() {
		assertEquals(cpb.getProductName(), "TV");
	}

	@Test
	public void testGetPrice() {
		assertTrue(cpb.getPrice() == 10);
	}

	@Test
	public void testGetQuantity() {
		assertTrue(cpb.getQuantity() == 2);
	}

	@Test
	public void testToString() {
		String cartProductBean = "CartProductBean [productName=TV, price=10, quantity=2, getId()=1]";
		assertEquals(cpb.toString(), cartProductBean);
	}

	@Test
	public void testSetId() {
		assertTrue(cpb.getId() == 1);
	}

}
