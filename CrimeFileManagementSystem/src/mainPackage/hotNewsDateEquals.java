//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class hotNewsDateEquals {
	private Hotnews testThis;
	public hotNewsDateEquals() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new Hotnews(-99,10,"This is deneme hotnews","22");
		testThis.setHNDate("22");
	}
	
	@Test
	public void testHotNewsDateEquals() {
		String expected="22";
		String actual=testThis.getHNDate();
		assertEquals(expected,actual);
	}
	@Test
	public void testHotNewsDateNotEquals() {
		String expected="27";
		String actual=testThis.getHNDate();
		assertNotSame(expected,actual);
	}
}










