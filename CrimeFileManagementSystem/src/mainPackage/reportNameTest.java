//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class reportNameTest {
	private CrimeReport testThis;
	public reportNameTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new BasicReport("Basic Report","Izmir","Izmirde kaybolan kisi",5,2);
	}
	
	@Test
	public void testNameEquals() {
		String expected="Basic Report";
		String actual=testThis.getReportName();
		assertEquals(expected,actual);
	}
	@Test
	public void testNameNotEquals() {
		String wrongAnswer="no reports";
		String actual=testThis.getReportName();
		assertNotSame(wrongAnswer,actual);
	}
	
	
	

}



