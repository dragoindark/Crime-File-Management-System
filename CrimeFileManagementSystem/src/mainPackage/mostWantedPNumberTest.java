//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class mostWantedPNumberTest {
	private CriminalPerson testThis;
	public mostWantedPNumberTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new MostWanted("cthulhu",99999999,"666",null);
	}
	
	@Test
	public void testCorrectMostWantedPNumberEquals() {
		String expected="666";
		String actual=testThis.getPhoneNumber();
		assertEquals(expected,actual);
	}
	@Test
	public void testCorrectMostWantedPNumberNotEquals() {
		String expected="777";
		String actual=testThis.getPhoneNumber();
		assertNotSame(expected,actual);
	}
}








