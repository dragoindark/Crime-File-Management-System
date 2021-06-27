//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class normalUserTypeTest {
	private User testThis;
	public normalUserTypeTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new NormalUser("cthulhudestroyerofworlds","allhailcthulu");
		testThis.setID(-666);
	}
	
	@Test
	public void testTypeEquals() {
		String expected="normal";
		String actual=testThis.getType();
		assertEquals(expected,actual);
	}
	@Test
	public void testTypeNotEquals() {
		String expected="admin";
		String actual=testThis.getType();
		assertNotSame(expected,actual);
	}
}






