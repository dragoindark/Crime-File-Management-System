//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class hotNewsAdminIDTest {
	private Hotnews testThis;
	public hotNewsAdminIDTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new Hotnews(-99,10,"This is deneme hotnews","22");
	}
	
	@Test
	public void testAdminIDEquals() {
		int expected=10;
		int actual=testThis.getAdminID();
		assertEquals(expected,actual);
	}
	@Test
	public void testAdminIDNotEquals() {
		int expected=-999;
		int actual=testThis.getAdminID();
		assertNotSame(expected,actual);
	}
}










