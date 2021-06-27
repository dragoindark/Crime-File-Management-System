//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class reportContentTest {
	private CrimeReport testThis;
	public reportContentTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new BasicReport("Basic Report","Izmir","Izmirde kaybolan kisi",5,2);
	}
	@Test
	public void testContentEquals() {
		String expected="Izmirde kaybolan kisi";
		String actual=testThis.getReportContent();
		assertEquals(expected,actual);
	}
	@Test
	public void testContentNotEquals() {
		String wrongAnswer="Izmirde kaybolmayan kisi";
		String actual=testThis.getReportContent();
		assertNotSame(wrongAnswer,actual);
	}
}



