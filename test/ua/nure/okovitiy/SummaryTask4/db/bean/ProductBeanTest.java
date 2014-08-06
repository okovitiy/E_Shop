package ua.nure.okovitiy.SummaryTask4.db.bean;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductBeanTest {

	ProductBean pb;

	@Before
	public void setUp() throws Exception {
		pb = new ProductBean();
		pb.setCategory("TV");
		pb.setColor("blue");
		pb.setId(1);
		pb.setName("Samsung");
		pb.setNovelty(new Date(123));
		pb.setPrice(10);
		pb.setQuantity(2);
		pb.setSize("20x15");
	}

	@After
	public void tearDown() throws Exception {
		pb = null;
	}

	@Test
	public void testGetCategory() {
		assertEquals(pb.getCategory(), "TV");
	}

	@Test
	public void testGetName() {
		assertEquals(pb.getName(), "Samsung");
	}

	@Test
	public void testGetPrice() {
		assertTrue(pb.getPrice() == 10);
	}

	@Test
	public void testGetColor() {
		assertEquals(pb.getColor(), "blue");
	}

	@Test
	public void testGetSize() {
		assertEquals(pb.getSize(), "20x15");
	}

	@Test
	public void testGetNovelty() {
		assertEquals(pb.getNovelty(), new Date(123));
	}

	@Test
	public void testGetQuantity() {
		assertTrue(pb.getQuantity() == 2);
	}

	@Test
	public void testToString() {
		String productBean = "ProductBean [category=TV, name=Samsung, " +
				"price=10, color=blue, size=20x15, novelty=1970-01-01, " +
				"quantity=2, getId()=1]";
		assertEquals(pb.toString(), productBean);
	}

	@Test
	public void testGetId() {
		assertTrue(pb.getId() == 1);
	}

}
