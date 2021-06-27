//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class reportObjectIDTest {
	private CrimeReport testThis;
	public reportObjectIDTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new BasicReport("Basic Report","Izmir","Izmirde kaybolan kisi",-1,2);
	}
	
	public void testIDEquals() {
		int expected=2;
		int actual=testThis.getUID();
		assertEquals(expected,actual);
	}
	@Test
	public void testIDNotEquals() {
		int wrongAnswer=99;
		int actual=testThis.getUID();
		assertNotSame(wrongAnswer,actual);
	}
	
	
	

}



