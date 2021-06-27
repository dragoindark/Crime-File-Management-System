package mainPackage;

//change maybe

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Before;
import org.junit.Test;

//this class tests whether or not report data is put inside the database correctly
public class reportInsertDBTest{
	public reportInsertDBTest() throws Exception {
		setUp();
	}
	@Before
	public void setUp() throws Exception{
		dbmsRunner=QueryRunner.QueryRunner();
		
		rID=idCreator.idCreator().createID();
		reportName="People Missing";
		reportLocation="izmir";
		reportContent="Izmirde kaybolan birisi";
	    reportDate="17.06.2021";
		type="Missing People Report";
		cpID=3;
	    p=new MissingPeopleReport(reportName,reportLocation,reportContent,cpID,1);
		
	}
	@Test
	public void testInsertSuccessfull() {
		boolean expected=true;
		boolean actual=dbmsRunner.insertReport(p);
		if(actual) {
			dbmsRunner.deleteReport(p.getRID());
		}
		assertEquals(expected,actual);
	}
	@Test
	public void testInsertNotSuccessfull() {
		boolean expected=false;
		p.setCPID(-5);
		boolean actual=dbmsRunner.insertReport(p);
		if(actual) {
			dbmsRunner.deleteReport(p.getRID());
		}
		assertNotSame(expected,actual);
	}
	QueryRunner dbmsRunner;
	int rID;
	String reportName;
	String reportLocation;
	String reportContent;
	String reportDate;
	String type;
	int cpID;
	CrimeReport p;
}

