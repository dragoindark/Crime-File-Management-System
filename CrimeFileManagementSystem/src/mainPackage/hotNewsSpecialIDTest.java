//Written and coded by Faruk Burak Gürel@dragoindark and Nur Ruşen@haticenurrusen
package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;



public class hotNewsSpecialIDTest {
	private Hotnews testThis;
	private QueryRunner qr=QueryRunner.QueryRunner();
	public hotNewsSpecialIDTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		testThis=new Hotnews(-99,10,"This is deneme hotnews","22");
	}
	
	@Test
	public void testCorrectSpecialIDEquals() {
		qr.insertHotnews(testThis);
		int expected=qr.getMaxHotnewsID();
		int actual=testThis.getHNID();
		qr.deleteHotNews(testThis.getHNID());
		assertEquals(expected,actual);
	}
	@Test
	public void testCorrectSpecialIDNotEquals() {
		qr.insertHotnews(testThis);
		int expected=-99;
		int actual=testThis.getHNID();
		qr.deleteHotNews(testThis.getHNID());
		assertNotSame(expected,actual);
	}
}









