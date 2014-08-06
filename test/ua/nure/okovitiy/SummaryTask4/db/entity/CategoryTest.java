package ua.nure.okovitiy.SummaryTask4.db.entity;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CategoryTest {

	Category cat;

	@Before
	public void setUp() throws Exception {
		cat = new Category();
		cat.setId(1);
		cat.setName("TV");
	}

	@After
	public void tearDown() throws Exception {
		cat = null;
	}

	@Test
	public void testGetName() {
		assertEquals(cat.getName(), "TV");
	}

	@Test
	public void testToString() {
		String c = "Category [name=TV, getId()=1]";
		assertEquals(cat.toString(), c);
	}

	@Test
	public void testGetId() {
		assertTrue(cat.getId() == 1);
	}

}
