//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class userFeedBackContentEqualsTest {
	private Feedback testThis;
	public userFeedBackContentEqualsTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new userFeedback(-99,"deneme feedback","22",10,10);
	}
	
	@Test
	public void testFeedbackContentEquals() {
		String expected="deneme feedback";
		String actual=testThis.getFBContent();
		assertEquals(expected,actual);
	}
	@Test
	public void testFeedbackContentNotEquals() {
		String expected="not deneme feedback";
		String actual=testThis.getFBContent();
		assertNotSame(expected,actual);
	}
}










