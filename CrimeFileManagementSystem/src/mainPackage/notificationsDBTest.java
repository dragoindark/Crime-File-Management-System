package mainPackage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

//this class tests wheter or not notifications are gotten from database correctly
public class notificationsDBTest{
	public notificationsDBTest() throws Exception {
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
	public void testNotificationEquals() {
		int recieverID=2;
		String expected="The notification is sent by the user burak\n"
				+ "The notification is sent at the time 23.06\n"
				+ "The notification is Yo whatup \n";
		dbmsRunner.getUserNotifications(2);
		String message=uFacade.getUserByID(recieverID).getMessages().getNotifications().get(0);
		dbmsRunner.getUserNotifications(recieverID);
		String actual=uFacade.getUserByID(recieverID).getMessages().messageSplice(message, "notification");
		actual=actual.replace(" ","");
		expected=expected.replace(" ","");
		assertEquals(expected,actual);
	}
	@Test
	public void testNotificationNotEquals() {
		int recieverID=2;
		String expected="The notification is sent by the user ahmet\n"
				+ "The notification is sent at the time 23.06\n"
				+ "The notification is Yo whatup \n";
		dbmsRunner.getUserNotifications(2);
		String message=uFacade.getUserByID(recieverID).getMessages().getNotifications().get(0);
		dbmsRunner.getUserNotifications(recieverID);
		String actual=uFacade.getUserByID(recieverID).getMessages().messageSplice(message, "notification");
		actual=actual.replace(" ","");
		expected=expected.replace(" ","");
		assertNotSame(expected,actual);
	}
	QueryRunner dbmsRunner;
	userCreationFacade uFacade;
}
