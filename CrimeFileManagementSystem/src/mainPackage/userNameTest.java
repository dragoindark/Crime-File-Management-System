//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class userNameTest {
	private User testThis;
	public userNameTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new NormalUser("cthulhudestroyerofworlds","allhailcthulu");
		testThis.setID(-666);
	}
	
	@Test
	public void testNameEquals() {
		String expected="cthulhudestroyerofworlds";
		String actual=testThis.getUserName();
		assertEquals(expected,actual);
	}
	@Test
	public void testNameNotEquals() {
		String expected="cthuluiscute";
		String actual=testThis.getUserName();
		assertNotSame(expected,actual);
	}
}





