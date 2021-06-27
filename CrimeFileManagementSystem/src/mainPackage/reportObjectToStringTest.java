//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class reportObjectToStringTest {
	private CrimeReport testThis;
	public reportObjectToStringTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new BasicReport("Basic Report","Izmir","Izmirde kaybolan kisi",5,2);
		testThis.setRID(0);
		testThis.setReportDateWithValue("22");
	}
	
	@Test
	public void testToStringEquals() {
		String expected="Report id is 0\n"
				+ "Report name is Basic Report\n"
				+ "Report content is Izmirde kaybolan kisi\n"
				+ "Report location is Izmir\n"
				+ "Report date is 22\n"
				+ "Report status is Started\n"
				+ "Report type is basic report\n"
				+ "Created by the user with the id 2";
		String actual=testThis.toString();
		assertEquals(expected,actual);
	}
	@Test
	public void testToStringNotEquals() {
		String expected="Report name is "+"adana"+"\nReport content is "+"yok"+"\nReport location is "+"Izmir"+
				"\nCreated by the user with the id "+5;
		String actual=testThis.toString();
		assertNotSame(expected,actual);
	}
	
	
	

}



