//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class userPassTest {
	private User testThis;
	public userPassTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new NormalUser("cthulhudestroyerofworlds","allhailcthulu");
		testThis.setID(-666);
	}
	
	@Test
	public void testPasswordEncryptedCorrectly() {
		String expected=new Encrypter().shaWithMD5Encrypter("allhailcthulu");
		String actual=testThis.getUserPassword();
		assertEquals(expected,actual);
	}
	@Test
	public void testPasswordNotCorrectEncryption() {
		String expected=new Encrypter().shaWithMD5Encrypter("allhailcthulu1");
		String actual=testThis.getUserPassword();
		assertNotSame(expected,actual);
	}
}





