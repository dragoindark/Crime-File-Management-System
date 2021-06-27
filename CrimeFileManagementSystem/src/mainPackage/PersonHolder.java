//Faruk Burak Gürel@dragoindark
package mainPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

interface PersonHolder{
	public boolean addCriminalPerson(CriminalPerson person);
	public boolean deleteCriminalPerson(CriminalPerson person);
	public CriminalPerson getCriminalPerson(int id);
	public CriminalPerson getCriminalPersonByName(String person);
	public CriminalPerson getCriminalPersonByReport(CrimeReport report);
	public HashMap<Integer,CriminalPerson> getCriminalPersonMap();
	public void printAllCriminalPersons();
	public boolean checkCriminalPersonExists(CriminalPerson person);
	public boolean checkCriminalPersonExistsWithName(String name);
}

class CriminalPersonObject implements PersonHolder{
	private CriminalPersonObject() {personHolder=new HashMap<Integer,CriminalPerson>();}
	public static CriminalPersonObject CriminalPersonObject() {
		if(persons_instance==null) {
			persons_instance=new CriminalPersonObject();
		}
		return persons_instance;
	}
	public CriminalPerson getCriminalPersonByReport(CrimeReport report) {
		CriminalPerson personToReturn=null;
		for(Map.Entry<Integer,CriminalPerson> map:this.personHolder.entrySet()) {
			CriminalPerson person=map.getValue();
			if(person.getReport().getRID()==report.getRID()) {
				personToReturn=person;
			}
		}
		return personToReturn;
	}
	public boolean addCriminalPerson(CriminalPerson person) {
		if(person==null) {
			return false;
		}else {
			try {
				this.personHolder.put(person.getID(),person);
				return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}	
		}
	}
	public boolean deleteCriminalPerson(CriminalPerson person) {
		if(personHolder.containsKey(person.getID())) {
			try {
				personHolder.remove(person.getID());
				return true;
			}catch(Exception e) {
				e.printStackTrace();
				return false;
			}
		}else {
			return false;
		}
		
	}
	public CriminalPerson getCriminalPerson(int id) {
		if(personHolder.containsKey(id)) {
			return this.personHolder.get(id);
		}else {
			System.out.println("CriminalPerson not found");
			return null;
		}
	}
	public HashMap<Integer, CriminalPerson> getCriminalPersonMap() {
		return this.personHolder;
	}
	public void printAllCriminalPersons() {
		for(Map.Entry<Integer,CriminalPerson> map:this.personHolder.entrySet()) {
			CriminalPerson person=map.getValue();
			System.out.println(person.toString());
		}
	}
	public CriminalPerson getCriminalPersonByName(String name) {
		CriminalPerson personToReturn=null;
		for(Map.Entry<Integer,CriminalPerson> map:this.personHolder.entrySet()) {
			CriminalPerson person=map.getValue();
			if(person.getName().equalsIgnoreCase(name)) {
				personToReturn=person;
			}
		}
		if(personToReturn==null) {
			System.out.println("CriminalPerson not found");
		}
		return personToReturn;
	}
	public boolean checkCriminalPersonExistsWithName(String name) {
		boolean bool=false;
		for(Map.Entry<Integer,CriminalPerson> map:this.personHolder.entrySet()) {
			CriminalPerson person=map.getValue();
			if(person.getName().equalsIgnoreCase(name)) {
				bool=true;
			}
		}
		return bool;
	}
	public boolean checkCriminalPersonExists(CriminalPerson person) {
		return this.personHolder.containsKey(person.getID());
	}
	
	private static CriminalPersonObject persons_instance=null;
	protected HashMap<Integer,CriminalPerson> personHolder;
}
//this is a facade class that Faruk Burak Gurel and Nur created, this basically makes whole process easier.
class personFacade{
	public static personFacade personFacade() {
		if(person_facade==null) {
			person_facade=new personFacade();
		}
		return person_facade;
	}
	private personFacade() {
		holder=CriminalPersonObject.CriminalPersonObject();
		reports=new HashMap<Integer,CrimeReport>();
	}
	public boolean printAllReports() {
		if(this.reports.size()==0) {
			System.out.println("There are no reports");
			return false;
		}else {
			for(Map.Entry<Integer,CrimeReport> map:this.reports.entrySet()) {
				System.out.println("Report with the id "+map.getKey());
				map.getValue().printInfo();
			}
			return true;
		}		
	}
	public boolean printAllReportsByType(String type) {
		boolean foundOne=false;
		if(this.reports.size()==0) {
			System.out.println("There are no reports to report");
			return false;
		}else {
			System.out.println("Actual type is "+type);
			System.out.println("There are total of "+this.reports.size()+" reports");
			for(Map.Entry<Integer,CrimeReport> map:this.reports.entrySet()) {
				//types are 1. Basic Report 2. Missing People Report 3. Complaint
				System.out.println(map.getValue().getType());
				if(map.getValue().getType().equalsIgnoreCase(type)) {
					System.out.println("Report with the id "+map.getKey());
					map.getValue().printInfo();
					foundOne=true;
				}
			}
			return foundOne;
		}		
	}
	//this method automatically populates
	/*
	public void populatePerson() {
		userCreationFacade facade=userCreationFacade.userCreationFacade();
		HashMap<Integer,User> map=facade.getHolder().getUserMap();
		ArrayList<Integer> allIDs=new ArrayList<Integer>();
		for(Map.Entry<Integer,User> mapFacade:map.entrySet()) {
			allIDs.add(mapFacade.getKey());			
		}
		Random numGen=new Random();
		String[] dummyReportNames={"Hırsızlık","Kaçırma","Cinayet","Soygun","Illegal Ticaret","Bilinmiyor"};
		String[] dummyLocationNames= {"Cennet mahallesi","Kurtlar vadisi","Orgrimmar","Stormwind","Yaprak Dökümündeki Köşk","Izmir"};
		String[] dummyContentNames= {"Mahallede kavga","Racon kestiler","Efsane kurtlar vadisi raconları","Benim kızım yapmaz","Yine orklar","Adanalılar sıcak diye güneşe sıktı"};
		String[] dummyReportTypes= {"report","complaint","missing people report","report","complaint","missing people report"};
		for(int i=0;i<allIDs.size();i++) {
			int randomNumber=numGen.nextInt(6);
			System.out.println(allIDs.get(i));
			this.createCrimeReport(dummyReportNames[randomNumber], dummyLocationNames[randomNumber], 
					dummyContentNames[randomNumber], dummyReportTypes[randomNumber], allIDs.get(i));
		}
	
	}*/
	//this is to create a criminal person with a facade
	public boolean createCriminalPerson(String name, int age, String phoneNumber, CrimeReport report,String type) {
		CriminalPerson person;
		boolean creationSuccessfull=false;
		if(type.equalsIgnoreCase("missing")) {
			person=new MissingPeople(name,age,phoneNumber,report);
			System.out.println("Creation successfull");
			creationSuccessfull=true;
		}else {
			person=null;
		}
		if(person!=null) {
			System.out.println("Criminal person created with the info");
			person.printInfo();
			System.out.println("Criminal person report created with info");
			person.getReport().printInfo();
		}
		this.holder.addCriminalPerson(person);
		return creationSuccessfull;
	}
	//adapter function to create user from db info
	public CriminalPerson createCriminalPersonFromDB(int cpID,String name, int age,String date,String phoneNumber,String type, int rID,String cpStatus) {
		CriminalPerson person;
		CrimeReport report=QueryRunner.QueryRunner().getReport(rID);
		if(type.equalsIgnoreCase("missing")) {
			person=new MissingPeople(name,age,phoneNumber,report);
			System.out.println("Creation successfull");
		}else if(type.equalsIgnoreCase("most wanted")){
			person=new MostWanted(name, age, phoneNumber, report);
		}else {
			person=null;
		}
		if(person!=null) {
			person.setCPID(cpID);
			person.setDateWithValue(date);
			person.setCPStatus(cpStatus);
			try {
				this.reports.put(rID, report);
				this.holder.addCriminalPerson(person);	
				try {
					userCreationFacade.userCreationFacade().getUserByID(report.getUID()).addCrimeReport(report);
				}catch(NullPointerException e) {
					System.out.println("The user object is not created");
					System.out.println("Creating the user object and adding the crime report to it compositly");
					UserObject.UserObject().addUser(QueryRunner.QueryRunner().getUserWithID(report.getUID()));
					try {
						userCreationFacade.userCreationFacade().getUserByID(report.getUID()).addCrimeReport(report);
					}catch(NullPointerException k) {
					System.out.println("Value cannot be gotten from userCreation facade");
					}
				}finally {
					System.out.println("Report created successfully with information");	
				}
			}catch(NullPointerException e) {
				e.printStackTrace();
				System.out.println("Value cannot be inserted into person holder");
			}finally {
				System.out.println("Criminal Person Created Successfully");
			}
		}
		return person;
	}
	//adapter function for reports created from database
		public CrimeReport createCrimeReportFromDB(int RID,String reportName, String reportLocation, String reportContent,String reportDate,String type,int cpID,int uID,String rStatus) {
			CrimeReport report;
			if(type.equalsIgnoreCase("basic report")) {
				report=new BasicReport(reportName,reportLocation,reportContent,-1,uID);
			}else if(type.equalsIgnoreCase("complaint")) {
				report=new ComplaintReport(reportName,reportLocation,reportContent,-1,uID);
			}else if(type.equalsIgnoreCase("missing people report")) {
				report=new MissingPeopleReport(reportName,reportLocation,reportContent,cpID,uID);
			}else if(type.equalsIgnoreCase("most wanted report")) {
				report=new MostWantedReport(reportName,reportLocation,reportContent,cpID,uID);
			}
			else {
				report=null;
			}
			if(report!=null) {
				report.setRID(RID);	
				report.setReportDateWithValue(reportDate);
				report.setReportStatus(rStatus);
				//This checks whether the user that created this report is instantiated
				try {
					userCreationFacade.userCreationFacade().getUserByID(uID).addCrimeReport(report);
				}catch(NullPointerException e) {
					System.out.println("The user object is not created");
					System.out.println("Creating the user object and adding the crime report to it compositly");
					UserObject.UserObject().addUser(QueryRunner.QueryRunner().getUserWithID(uID));
					try {
						userCreationFacade.userCreationFacade().getUserByID(uID).addCrimeReport(report);
					}catch(NullPointerException k) {
					System.out.println("Value cannot be gotten from userCreation facade");
					}
				}finally {
					System.out.println("Report created successfully with information");	
				}
				//another try catch maybe to insert report into the criminal person
			}
			return report;
		}
	public CriminalPerson getCriminalPerson(String name) {
		return holder.getCriminalPersonByName(name);
	}
	
	public boolean checkCriminalPerson(CriminalPerson person) {
		return holder.checkCriminalPersonExists(person);
	}
	public boolean checkCriminalPersonWithName(String name) {
		return holder.checkCriminalPersonExistsWithName(name);
	}
	public boolean changeCrimeReportWithID(String reportName,String reportLocation,String reportContent,int id) {
		if(this.reports.containsKey(id)) {
			this.reports.get(id).setReportName(reportName);
			this.reports.get(id).setReportLocation(reportLocation);
			this.reports.get(id).setReportContent(reportContent);
			System.out.println("New info is");
			this.reports.get(id).printInfo();
			int creatorID=this.reports.get(id).getUID();
			User holder=UserObject.UserObject().getUser(creatorID);
			String notification="The information of the report changed to "+this.reports.get(id).toString();
			System.out.println("The information changed for the report which was created by the user "+holder.getUserName());
			UserObject.UserObject().getUser(creatorID).getMessages().addNotification(notification);
			return true;
		}else {
			System.out.println("Wrong id");
			return false;
		}	
	}
	public boolean reportHashContainsID(int id) {
		return this.reports.containsKey(id);
	}
	public boolean changeCrimeReportWithName(String reportName,String reportLocation,String reportContent) {
		boolean bool=false;
		for(CrimeReport report: this.reports.values()) {
			if(report.getReportName().equalsIgnoreCase(reportName)) {
				int id=report.getRID();
				this.reports.get(id).setReportName(reportName);
				this.reports.get(id).setReportLocation(reportLocation);
				this.reports.get(id).setReportContent(reportContent);
				bool=true;
			}
		}
		return bool;
	}
	public CrimeReport createCrimeReport(String reportName, String reportLocation, String reportContent,String type,int cpID,int uID) {
		CrimeReport report;
		if(type.equalsIgnoreCase("report")) {
			report=new BasicReport(reportName,reportLocation,reportContent,cpID,uID);
		}else if(type.equalsIgnoreCase("complaint")) {
			report=new ComplaintReport(reportName,reportLocation,reportContent,-1,uID);
		}else if(type.equalsIgnoreCase("missing people report")) {
			report=new MissingPeopleReport(reportName,reportLocation,reportContent,cpID,uID);
		}else if(type.equalsIgnoreCase("most wanted report")) {
			report=new MostWantedReport(reportName,reportLocation,reportContent,cpID,uID);
		}else {
			report=null;
		}
		try {
			userCreationFacade.userCreationFacade().getUserByID(uID).addCrimeReport(report);
		}catch(NullPointerException e) {
			System.out.println("The user object is not created");
			System.out.println("Creating the user object and adding the crime report to it compositly");
			UserObject.UserObject().addUser(QueryRunner.QueryRunner().getUserWithID(uID));
			try {
				userCreationFacade.userCreationFacade().getUserByID(uID).addCrimeReport(report);
			}catch(NullPointerException k) {
			System.out.println("Value cannot be gotten from userCreation facade");
			}
		}finally {
			System.out.println("Report created successfully with information");	
		}
		
		return report;
	}
	public boolean criminalPersonPrompt(CrimeReport report) {
		System.out.println("Welcome to the Crime File Management System");
		Scanner input=new Scanner(System.in);
		input.nextLine();
		boolean personProcess=false;
		String loginAsk="Enter the person details, to exit anytime type exit or -1 \n"
				+"Enter persons name,phone number,age and type \n"
				+ "Please enter the crime type, missing for missing people and wanted for wanted people";
		while(!personProcess) {
			System.out.println(loginAsk);
			String type=input.nextLine();
			if(type.equalsIgnoreCase("exit") || type.equalsIgnoreCase("-1")) {
				System.out.println("Exiting");
				break;
			}
			System.out.println("Please enter the persons name");
			String name=input.nextLine();
			if(name.equalsIgnoreCase("exit") || name.equalsIgnoreCase("-1")) {
				System.out.println("Exiting");
				break;
			}else {
				System.out.println("Please enter the persons phone number");
				String phoneNumber=input.nextLine();
				if(phoneNumber.equalsIgnoreCase("exit") || phoneNumber.equalsIgnoreCase("-1")) {
					System.out.println("Exiting");
					break;
				}else {
					System.out.println("Please enter persons age");
					int age=input.nextInt();
					this.createCriminalPerson(name, age, phoneNumber,report, type);
					System.out.println("Creation successfull");
					personProcess=true;
				}
			}
		}
		input.close();
		return personProcess;
	}
	PersonHolder holder;
	private static personFacade person_facade=null;
	protected HashMap<Integer,CrimeReport> reports;
}



