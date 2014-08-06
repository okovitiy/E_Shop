package ua.nure.okovitiy.SummaryTask4.db.bean;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OrderBeanTest {

	OrderBean ob;
	
	@Before
	public void setUp() throws Exception {
		ob = new OrderBean();
		ob.setId(1);
		ob.setStatusOrder("paid");
	}

	@After
	public void tearDown() throws Exception {
		ob = null;
	}

	@Test
	public void testGetStatusOrder() {
		assertEquals(ob.getStatusOrder(), "paid");
	}

	@Test
	public void testToString() {
		assertTrue(ob.getId() == 1);
	}

	@Test
	public void testGetId() {
		String orderBean = "OrderBean [statusOrder=paid, getId()=1]";
		assertEquals(ob.toString(), orderBean);
	}

}
