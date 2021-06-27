//Faruk Burak Gürel@dragoindark

//this class is not used,because it was not my part 
package mainPackage;

import java.time.ZonedDateTime;

//this is the interface for persons involved in criminal cases,missing,wanted 
interface CriminalPerson{
	public String getName();
	public int getAge();
	public String getDate();
	public String getDetails();
	public String getPhoneNumber();
	public int getID();
	public String getClassSpecificType();
	public String getCPStatus();
	public void setCPStatus(String cpStatus);
	public void setCrimeReport(CrimeReport report);
	public void setName(String name);
	public void setAge(int age);
	public void setDate();
	public void setDetails(String details);
	public void setPhoneNumber(String phoneNumber);
	public CrimeReport getReport();
	public void printInfo();
	public void setClassSpecificType();
	public void setDateWithValue(String date);
	public void setCPID(int cpID);
	public void setCPStatusByClass();
}
//this is the class where we will derive the concrete classes,written to reduce redundancy
abstract class CriminalBaseClass implements CriminalPerson{
	public CriminalBaseClass(String name,int age,String phoneNumber,CrimeReport report) {
		if(name==null || phoneNumber==null) {
			System.out.println("Wrong inputs exiting");
		}else {
			this.name=name;
			this.age=age;
			this.phoneNumber=phoneNumber;
			this.setDate();
			this.report=report;
			this.details="Details not known";
			this.id=idCreator.idCreator().createID();
			this.setClassSpecificType();
			this.setCPStatusByClass();
		}	
	}
	public int getID() {
		return this.id;
	}
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
	public String getDate() {
		return this.date;
	}
	public String getDetails() {
		return this.details;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	public String getClassSpecificType() {
		return this.classSpecificType;
	}
	public CrimeReport getReport() {
		if(this.report==null) {
			System.out.println("There are no reports attached to the person");
			return null;
		}else {
			return this.report;	
		}
	}
	public void setCrimeReport(CrimeReport report) {
		if(report==null) {
			System.out.println("Wrong value, report can not be null");
		}else {
			this.report=report;	
		}	
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber=phoneNumber;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public void setDate() {
		this.date=this.createTimeNow();
	}
	public void setDetails(String details) {
		this.details=details;
	}
	public String getCPStatus() {
		return this.cpStatus;
	}
	public void setCPStatus(String cpStatus) {
		this.cpStatus=cpStatus;		
	}
	public void setDateWithValue(String date) {
		this.date=date;
	}
	public void setCPID(int cpID) {
		this.id=cpID;
	}
	public String createTimeNow() {
		ZonedDateTime timeNow=ZonedDateTime.now();
	    return timeNow.getDayOfMonth()+"."+timeNow.getMonthValue()+"."+timeNow.getYear()+" "+
				timeNow.getHour()+":"+timeNow.getMinute()+":"+timeNow.getSecond()+"."+timeNow.getNano();
	}
	
	public String toString() {
		return "Persons cpID is "+id
				+"\nPersons name is "+name
				+"\nThe person is "+classSpecificType
				+"\nThe persons status is "+cpStatus
				+"\nThe persons phone number is "+phoneNumber
				+"\nPersons age is "+age
				+"\nThe person is entered in to the system at  "+date
				+"\nThe persons file id is "+report.getRID()+"\n";
	}
	public void printInfo() {
		System.out.println(this.toString());
	}
	
	protected int id; //persons id in the system
	protected String name; //persons name
	protected int age; //persons age
	protected String date; //the date of the person being submitted to the system
	protected String details; //optional details of the person, like hair color,height,if they have a scar or tatto etc.
	protected String phoneNumber; //the phone number of the person
	protected String classSpecificType; //the class specific type of the project
	protected CrimeReport report; //the crime report about the person
	protected String cpStatus;
}

class MissingPeople extends CriminalBaseClass{

	public MissingPeople(String name, int age, String phoneNumber, CrimeReport report) {
		super(name, age, phoneNumber, report);
	}
	@Override
	public void setClassSpecificType() {
		this.classSpecificType="Missing";	
	}
	@Override
	public void setCPStatusByClass() {
		this.cpStatus="Searching";
		
	}
}

class MostWanted extends CriminalBaseClass{

	public MostWanted(String name, int age, String phoneNumber, CrimeReport report) {
		super(name, age, phoneNumber, report);
	}

	@Override
	public void setClassSpecificType() {
		this.classSpecificType="Most Wanted";
		
	}

	@Override
	public void setCPStatusByClass() {
		this.cpStatus="APB Created, investigating";
	}
}























