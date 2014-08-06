package ua.nure.okovitiy.SummaryTask4.db.entity;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProductTest {

	Product pr;

	@Before
	public void setUp() throws Exception {
		pr = new Product();
		pr.setCategoryId(2);
		pr.setColor("red");
		pr.setId(1);
		pr.setName("name");
		pr.setNovelty(new Date(123));
		pr.setPrice(10);
		pr.setQuantity(5);
		pr.setSize("20x15");
	}

	@After
	public void tearDown() throws Exception {
		pr = null;
	}

	@Test
	public void testGetName() {
		assertEquals(pr.getName(), "name");
	}

	@Test
	public void testGetPrice() {
		assertTrue(pr.getPrice() == 10);
	}

	@Test
	public void testGetColor() {
		assertEquals(pr.getColor(), "red");
	}

	@Test
	public void testGetSize() {
		assertEquals(pr.getSize(), "20x15");
	}

	@Test
	public void testGetCategoryId() {
		assertTrue(pr.getCategoryId() == 2);
	}

	@Test
	public void testGetNovelty() {
		assertEquals(pr.getNovelty(), new Date(123));
	}

	@Test
	public void testGetQuantity() {
		assertTrue(pr.getQuantity() == 5);
	}

	@Test
	public void testToString() {
		String product = "Product [name=name, price=10, color=red, size=20x15, "
				+ "novelty=1970-01-01, quantity=5, categoryId=2, getId()=1]";
		assertEquals(pr.toString(), product);
	}

	@Test
	public void testGetId() {
		assertTrue(pr.getId() == 1);
	}

}
