//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class mostWantedPersonTypeTest {
	private CriminalPerson testThis;
	public mostWantedPersonTypeTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new MostWanted("cthulhu",99999999,"666",null);
	}
	
	@Test
	public void testCorrectMostWantedTypeEquals() {
		String expected="Most Wanted";
		String actual=testThis.getClassSpecificType();
		assertEquals(expected,actual);
	}
	@Test
	public void testCorrectMostWantedTypeNotEquals() {
		String expected="Missing";
		String actual=testThis.getClassSpecificType();
		assertNotSame(expected,actual);
	}
}







