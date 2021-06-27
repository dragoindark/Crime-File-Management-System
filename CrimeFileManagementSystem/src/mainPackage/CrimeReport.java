//Faruk Burak Gürel@dragoindark
package mainPackage;

import java.time.Duration;
import java.time.ZonedDateTime;

//this is the main class where we will create objects for crime reports, they will be derived from the base interface
interface CrimeReport {
	public String getReportName(); //the name of the report
	public String getReportContent(); //content about the case,how it happened etc
	public String getReportLocation(); //details about the location where the case happened
	public String getReportDate(); // the date when the report was created
	public String getReportStatus(); //the current status of the crime report
	public String getType();
	public int getCPID();
	public int getUID();
	public int getRID();
	public String createTimeNow();
	public boolean isFinished(String status);
	public void setReportName(String reportName);
	public void setReportContent(String reportContent);
	public void setReportLocation(String reportLocation);
	public void setReportStatus(String reportStatus);
	public void setReportDate();
	public void setReportDateWithValue(String date);
	public void setCPID(int cpID);
	public void setRID(int rID);
	public void setUID(int uID);
	public String getReportSpecificData();
	public void printInfo();
	public void setType();
}


abstract class BaseCrimeReportClass implements CrimeReport{
	public BaseCrimeReportClass(String reportName,String reportLocation,String reportContent,int cpID,int uID) {
		this.reportName=reportName;
		this.reportLocation=reportLocation;
		this.reportContent=reportContent;
		this.setReportDate();
		this.reportStatus="Started";
		this.RID=idCreator.idCreator().createID();
		this.setType();
		this.cpID=cpID;
		this.uID=uID;
	}
	public void setReportDateWithValue(String reportDate) {
		this.reportDate=reportDate;
	}
	public int getRID() {
		return this.RID;
	}
	public void setRID(int RID) {
		this.RID=RID;
	}
	public void setCPID(int cpID) {
		this.cpID=cpID;
	}
	public int getCPID() {
		return this.cpID;
	}
	public int getUID() {
		return this.uID;
	}
	public void setUID(int uID) {
		this.uID=uID;
	}
	public void setReportName(String reportName) {
		this.reportName=reportName;
	}
	public String getReportName() {
		return this.reportName;
	}
	public void setReportContent(String reportContent) {
		this.reportContent=reportContent;
	}
	public String getReportContent() {
		return this.reportContent;
	}
	public void setReportLocation(String reportLocation) {
		this.reportLocation=reportLocation;
	}
	public String getReportLocation() {
		return this.reportLocation;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus=reportStatus;
	}
	public String getReportStatus() {
		return this.reportStatus;
	}
	public void setReportDate() {
		this.reportDate=this.createTimeNow();
	}
	public String getReportDate() {
		return this.reportDate;
	}
	public String createTimeNow() {
		ZonedDateTime timeNow=ZonedDateTime.now();
	    return timeNow.getDayOfMonth()+"."+timeNow.getMonthValue()+"."+timeNow.getYear()+" "+
				timeNow.getHour()+":"+timeNow.getMinute()+":"+timeNow.getSecond()+"."+timeNow.getNano();
	}
	
	public boolean isFinished(String status) {
		if(status.equalsIgnoreCase("finished") || status.equalsIgnoreCase("done") || status.equalsIgnoreCase("found") ) {
			return true;
		}else {
			return false;
		}
	}
	
	public String toString() {
		switch(cpID) {
		case -1:
			return "Report id is "+RID
					+"\nReport name is "+reportName
					+"\nReport content is "+reportContent
			        +"\nReport location is "+reportLocation
			        +"\nReport date is "+reportDate
			        +"\nReport status is "+reportStatus
			        +"\nReport type is "+type
					+"\nCreated by the user with the id "+uID;
		
		default:
			return  "Report id is "+RID
					+"\nReport name is "+reportName
					+"\nReport content is "+reportContent
			        +"\nReport location is "+reportLocation
			        +"\nReport date is "+reportDate
			        +"\nReport status is "+reportStatus
			        +"\nReport type is "+type
					+"\nCreated by the user with the id "+uID
					+"\nReport is for the criminal person with the id "+cpID;
		}
		}
	public void printInfo() {
		System.out.println(this.toString());
	}
	
	public String getType() {
		return this.type;
	}
	
	protected String reportName;
	protected String reportContent;
	protected String reportLocation;
	protected String reportDate;
	protected String reportStatus;
	protected int RID;
	protected String type;
	protected int uID;
	protected int cpID;
}
class BasicReport extends BaseCrimeReportClass{

	public BasicReport(String reportName, String reportLocation, String reportContent,int cpID,int uID) {
		//cpID is -1 because there are no person related to basic report
		super(reportName, reportLocation, reportContent, -1,uID);
	}

	@Override
	public String getReportSpecificData() {
		return null;
	}
	public void setType() {
		this.type="basic report";
	}
}
class MissingPeopleReport extends BaseCrimeReportClass{

	public MissingPeopleReport(String reportName, String reportLocation,String reportContent, int cpID,int uID) {
		super(reportName, reportLocation, reportContent, cpID,uID);
	}
	
	public String getType() {
		return this.type;
	}
	public void setType() {
		this.type="missing people report";
	}
	
	public void personFound(String foundAt) {
		this.reportStatus="found";
		this.foundAt=foundAt;
		this.foundDate=this.createTimeNow();
		this.amountOfDaysMissing=this.calculateDayDifference();
	}
	
	public int calculateDayDifference() {
		try {
			Duration duration=Duration.between(ZonedDateTime.parse(reportDate),ZonedDateTime.parse(foundDate));
			return Math.toIntExact(duration.toDays());	
		}catch(Exception e){
			e.printStackTrace();
			return 0;			
		}	
	}
	protected String foundAt;
	protected String foundDate;
	protected int amountOfDaysMissing;
	public String getReportSpecificData() {
		return null;
	}
}

class ComplaintReport extends BaseCrimeReportClass{

	//complaint has cpID defaulted to -1 because it does not have any criminal person associated
	public ComplaintReport(String reportName, String reportLocation, String reportContent,int cpID,int uID) {
		super(reportName, reportLocation, reportContent, -1,uID);
	}

	public String getReportSpecificData() {
		
		return null;
	}
	public void setType() {
		this.type="complaint";
	}
	
}

class MostWantedReport extends BaseCrimeReportClass{

	public MostWantedReport(String reportName, String reportLocation, String reportContent,int cpID,int uID) {
		super(reportName, reportLocation, reportContent, cpID,uID);
	}

	public String getReportSpecificData() {
		
		return null;
	}
	public void setType() {
		this.type="most wanted report";
	}
	
}
















