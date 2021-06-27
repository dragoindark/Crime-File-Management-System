//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class encrypterTest {
	private Encrypter testThis;
	String encryptedText="fefb8d1fe059fdabdcb139814125e5e2";
	public encrypterTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new Encrypter();
	}
	
	@Test
	public void testEncryptionCorrect() {
		String expected=encryptedText;
		String actual=testThis.shaWithMD5Encrypter("encryptthis");
		assertEquals(expected,actual);
	}
	@Test
	public void testEncryptionNotEquals() {
		String expected=encryptedText;
		String actual=testThis.shaWithMD5Encrypter("allahkurtarsiq");
		assertNotSame(expected,actual);
	}
}






