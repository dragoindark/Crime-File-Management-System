//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class mostWantedAgeTest {
	private CriminalPerson testThis;
	public mostWantedAgeTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new MostWanted("cthulhu",99999999,"666",null);
	}
	
	@Test
	public void testMostWantedAgeEquals() {
		int expected=99999999;
		int actual=testThis.getAge();
		assertEquals(expected,actual);
	}
	@Test
	public void testMostWantedAgeNotEquals() {
		int expected=10;
		int actual=testThis.getAge();
		assertNotSame(expected,actual);
	}
}








