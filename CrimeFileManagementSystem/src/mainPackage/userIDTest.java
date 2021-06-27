//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class userIDTest {
	private User testThis;
	public userIDTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new NormalUser("cthulhudestroyerofworlds","allhailcthulu");
		testThis.setID(-666);
	}
	
	@Test
	public void testIDEquals() {
		int expected=-666;
		int actual=testThis.getID();
		assertEquals(expected,actual);
	}
	@Test
	public void testIDNotEquals() {
		int expected=666;
		int actual=testThis.getID();
		assertNotSame(expected,actual);
	}
}






