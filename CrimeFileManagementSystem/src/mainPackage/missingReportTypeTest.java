//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class missingReportTypeTest {
	private CrimeReport testThis;
	public missingReportTypeTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new MissingPeopleReport("Missing Report","Izmir","Izmirde kaybolan kisi",5,2);
	}
	
	@Test
	public void testTypeEquals() {
		String expected="missing people report";
		String actual=testThis.getType();
		assertEquals(expected,actual);
	}
	@Test
	public void testTypeNotEquals() {
		String wrongAnswer="most wanted report";
		String actual=testThis.getReportName();
		assertNotSame(wrongAnswer,actual);
	}
}



