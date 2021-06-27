//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class hotNewsContentTest {
	private Hotnews testThis;
	public hotNewsContentTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new Hotnews(-99,10,"This is deneme hotnews","22");
	}
	
	@Test
	public void testHotNewsContentEquals() {
		String expected="This is deneme hotnews";
		String actual=testThis.getHNContent();
		assertEquals(expected,actual);
	}
	@Test
	public void testHotNewsContentNotEquals() {
		String expected="This is not deneme hotnews";
		String actual=testThis.getHNContent();
		assertNotSame(expected,actual);
	}
}










