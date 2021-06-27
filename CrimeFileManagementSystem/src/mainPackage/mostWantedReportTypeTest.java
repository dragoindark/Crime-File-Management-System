//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class mostWantedReportTypeTest {
	private CrimeReport testThis;
	public mostWantedReportTypeTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new MostWantedReport("Most Wanted Report","Izmir","Izmirde kaybolan kisi",5,2);
	}
	
	@Test
	public void testTypeEquals() {
		String expected="most wanted report";
		String actual=testThis.getType();
		assertEquals(expected,actual);
	}
	@Test
	public void testTypeNotEquals() {
		String wrongAnswer="complaint report";
		String actual=testThis.getReportName();
		assertNotSame(wrongAnswer,actual);
	}
}





