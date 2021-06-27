package mainPackage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CrimeReportTest.class,
	encrypterTest.class,
	messageDBTest.class,
	notificationsDBTest.class,
	messageInsertDBTest.class,
	notificationInsertDBTest.class,
	reportInsertDBTest.class,
	reportIDTest.class,
	reportNameTest.class,
	reportLocationTest.class,
	reportContentTest.class,
	reportObjectIDTest.class,
	reportObjectToStringTest.class,
	missingReportTypeTest.class,
	basicReportTypeTest.class,
	complaintReportTypeTest.class,
	mostWantedReportTypeTest.class,
	userNameTest.class,
	userIDTest.class,
	normalUserTypeTest.class,
	adminUserTypeTest.class,
	userPassTest.class,
	missingPersonTypeTest.class,
	mostWantedPersonTypeTest.class,
	mostWantedAgeTest.class,
	mostWantedPNumberTest.class,
	hotNewsSpecialIDTest.class,
	hotNewsAdminIDTest.class,
	hotNewsContentTest.class,
	hotNewsDateEquals.class,
	userFeedBackContentEqualsTest.class
	
})
public class AllTests {

}
