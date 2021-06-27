package mainPackage;

//change maybe

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

public class messageInsertDBTest{
	public messageInsertDBTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		dbmsRunner=QueryRunner.QueryRunner();
	}
	@Test
	public void testInsertSuccessfull() {
		boolean expected=true;
		boolean actual=dbmsRunner.setUserMessages("Kurtlar vadisi ilk 97 bölüm", 2, 1);
		if(actual) {
			dbmsRunner.deleteUserMessages("Kurtlar vadisi ilk 97 bölüm", 2, 1);
		}
		assertEquals(expected,actual);
	}
	public void testInsertNotSuccessfull() {
		boolean expected=true;
		boolean actual=dbmsRunner.setUserMessages("Kurtlar vadisi ilk 97 bölüm", -6006, -666);
		if(actual) {
			dbmsRunner.deleteUserMessages("Kurtlar vadisi ilk 97 bölüm", 2, 1);
		}
		assertNotSame(expected,actual);
	}
	QueryRunner dbmsRunner;
}

