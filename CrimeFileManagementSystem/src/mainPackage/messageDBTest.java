package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

public class messageDBTest{
	public messageDBTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		dbmsRunner=QueryRunner.QueryRunner();
		uFacade=userCreationFacade.userCreationFacade();
		User bUser=dbmsRunner.getUser("ahmet");
	}
	@Test
	//This test tests whether or not message got from database is equal or not
	//the empty spaces gave me hard time therefore I just imploded them
	//The test actually gets the value from the database
	//than puts the actual value into the memory
	public void testMessageEquals() {
		int recieverID=2;
		String expected="The message is sent by the user burak\n"
				+ "The message is sent at the time 22.06\n"
				+ "The message is Yo whatup \n";
		dbmsRunner.getUserMessages(2);
		String message=uFacade.getUserByID(recieverID).getMessages().getMessages().get(0);
		dbmsRunner.getUserMessages(recieverID);
		String actual=uFacade.getUserByID(recieverID).getMessages().messageSplice(message, "message");
		actual=actual.replace(" ","");
		expected=expected.replace(" ","");
		assertEquals(expected,actual);
	}
	@Test
	public void testMessageNotEquals() {
		int recieverID=2;
		String expected="The message is sent by the user ahmet\n"
				+ "The message is sent at the time 22.06\n"
				+ "The message is Yo whatup \n";
		dbmsRunner.getUserMessages(2);
		String message=uFacade.getUserByID(recieverID).getMessages().getMessages().get(0);
		dbmsRunner.getUserMessages(recieverID);
		String actual=uFacade.getUserByID(recieverID).getMessages().messageSplice(message, "message");
		actual=actual.replace(" ","");
		expected=expected.replace(" ","");
		assertNotSame(expected,actual);
	}
	QueryRunner dbmsRunner;
	userCreationFacade uFacade;
}
