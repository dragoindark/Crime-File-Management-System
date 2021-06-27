//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class reportLocationTest {
	private CrimeReport testThis;
	public reportLocationTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new BasicReport("Basic Report","Izmir","Izmirde kaybolan kisi",5,2);
	}
	
	@Test
	public void testLocationEquals() {
		String expected="Izmir";
		String actual=testThis.getReportLocation();
		assertEquals(expected,actual);
	}
	@Test
	public void testLocationNotEquals() {
		String wrongAnswer="adana";
		String actual=testThis.getReportLocation();
		assertNotSame(wrongAnswer,actual);
	}
}



