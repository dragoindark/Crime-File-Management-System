//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class CrimeReportTest {
	private CrimeReport testThis;
	public CrimeReportTest() throws Exception {
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
	@Test
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
	@Test
	public void testToStringNotEquals() {
		String expected="Report name is "+"adana"+"\nReport content is "+"yok"+"\nReport location is "+"Izmir"+
				"\nCreated by the user with the id "+5;
		String actual=testThis.toString();
		assertNotSame(expected,actual);
	}
	
	
	

}


