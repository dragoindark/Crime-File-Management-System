//Faruk Burak Gürel@dragoindark
package mainPackage;

import java.time.ZonedDateTime;
import java.util.ArrayList;
//this is a singleton class that creates id for users
class idCreator{
	private ArrayList<Integer> idKeeper;
	private static idCreator single_instance=null;
	QueryRunner qr;
	
	private idCreator() {
		idKeeper=new ArrayList<Integer>();
		qr=QueryRunner.QueryRunner();
	}
	public static idCreator idCreator() {
		if(single_instance==null) {
			single_instance=new idCreator();
		}
		return single_instance;
	}
	public int createID() {
		if(this.idKeeper.isEmpty()) {
			this.idKeeper.add(qr.getMaxId());
			return 0;
		}else {
			int id=this.idKeeper.get(idKeeper.size()-1);
			this.idKeeper.add(id+1);
			return id+1;
		}
	}
}
class timeCreator{
	public static String createTimeNow() {
		ZonedDateTime timeNow=ZonedDateTime.now();
	    return timeNow.getDayOfMonth()+"."+timeNow.getMonthValue()+"."+timeNow.getYear()+" "+
				timeNow.getHour()+":"+timeNow.getMinute()+":"+timeNow.getSecond()+"."+timeNow.getNano();
	}
}
//This is the basic interface for login system
interface User{

	public void setUserName(String userName);
	public void setUserPassword(String password);
	public String getUserName();
	public void setLastDate(String lastDate);
	public void setCreatedAt(String createdAt);
	public String getLastDate();
	public String getCreatedAt();
	public boolean checkUserData(String name,String password);
	public String getDate();
	public String getLastLogin();
	public int getID();
	public String getUserPassword();
	public userMessages getMessages();
	public void printAllNewInfo();
	public boolean isAdmin();
	public void setID(int id);
	public ArrayList<CrimeReport> getReport();
	public void addCrimeReport(CrimeReport cr);
	public String getType();
	public void setType();
}

//This is the abstract class that will not be needed to written again, each subclass will just extend this class
abstract class BasicUser implements User {
	
	public BasicUser(String userName,String password) {
		encryptObject=new Encrypter();
		this.setUserName(userName);
		this.setUserPassword(password);
		this.createdAt=this.createTimeNow();
		this.id=idCreator.idCreator().createID();
		this.updates=new userMessages();
		reportsCreated=new ArrayList<CrimeReport>();
		this.setType();
	}
	public void setLastDate(String lastDate) {
		this.lastDate=lastDate;
	}
	public void setID(int id) {
		this.id=id;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt=createdAt;
	}
	public String getUserPassword() {
		return this.password;
	}
	public String getLastDate() {
		return this.lastDate;
	}
	public String getCreatedAt() {
		return this.createdAt;
	}
	public userMessages getMessages() {
		return this.updates;
	}
	public int getID() {
		return this.id;
	}
	public void setUserName(String userName) {
		this.userName=userName;
	}
	public void setUserPassword(String password) {
		if(encryptObject==null) {
			encryptObject=new Encrypter();
		}
		this.password=encryptObject.shaWithMD5Encrypter(password);
	}
	public String getUserName() {
		return this.userName;
	}
	public void printAllNewInfo() {
		this.updates.printAllNewInfo();
	}
	//checks the values of the user
	public boolean checkUserData(String userName,String password) {
		if(encryptObject==null) {
			encryptObject=new Encrypter();
		}
		if(this.userName.equals(userName) && this.password.equals(encryptObject.shaWithMD5Encrypter(password))) {
			this.lastDate=this.createTimeNow();
			return true;			
		}else {
			return false;
		}
	}
	public String getDate() {
		return this.createdAt;
	}
	public String getLastLogin() {
		if(this.lastDate==null) {
			return "User never logged in";
		}else {
			return lastDate;	
		}
	}
	//creates the atomic time right now with zoneddatetime library
	public String createTimeNow() {
		ZonedDateTime timeNow=ZonedDateTime.now();
	    return timeNow.getDayOfMonth()+"."+timeNow.getMonthValue()+"."+timeNow.getYear()+" "+
				timeNow.getHour()+":"+timeNow.getMinute()+":"+timeNow.getSecond()+"."+timeNow.getNano();
	}
	public String toString() {
		return "User Name " + userName+" user id "+id+" last login date "+lastDate+" account created at "+createdAt;
	}

	public ArrayList<CrimeReport> getReport() {
		return this.reportsCreated;
	}

	public void addCrimeReport(CrimeReport cr) {
		for(int i=0;i<reportsCreated.size();i++) {
			if(reportsCreated.get(i).getRID()==cr.getRID()) {
				return;
			}
		}
		reportsCreated.add(cr);
	}
	public String getType() {
		return this.type;
	}
	
	protected String userName;
	protected String password;
	protected String lastDate;
	protected String createdAt;
	protected Encrypter encryptObject=null;
	protected int id;
	protected userMessages updates=null;
	protected String type;
	protected ArrayList<CrimeReport> reportsCreated=null;
}

class NormalUser extends BasicUser{
	
	public NormalUser(String userName, String password) {
		super(userName, password);
	}
	
	public boolean isAdmin() {
		return false;
	}

	@Override
	public void setType() {
		this.type="normal";	
	}
	
}

class admin extends BasicUser{

	public admin(String userName, String password) {
		super(userName, password);
	}
	public boolean isAdmin() {
		return true;
	}
	@Override
	public void setType() {
		this.type="admin";
		
	}
}
//this is an composite object that keeps information about the notifications and the messages
class userMessages{
	private ArrayList<String> notifications=null;
	private ArrayList<String> messages=null;
	
	public userMessages() {
		notifications=new ArrayList<String>();
		messages=new ArrayList<String>();
	}
	public void addNotification(String notification) {
		this.notifications.add(notification);
	}
	public void addMessages(String message) {
		this.messages.add(message);
	}
	public ArrayList<String> getNotifications(){
		return this.notifications;
	}
	public ArrayList<String> getMessages(){
		return this.messages;
	}
	public void printAllNewInfo() {
		if(this.notifications.isEmpty()) {
			System.out.println("There are no new notifications");
		}else {
			System.out.println("There are "+notifications.size()+" new notifications");
			for(String holder:this.notifications) {
				System.out.println(this.messageSplice(holder, "notification"));
			}
		}
		if(this.messages.isEmpty()) {
			System.out.println("There are no new messages");
		}else {
			System.out.println("There are "+messages.size()+" new messages");
			for(String holder:this.messages) {
				System.out.println(this.messageSplice(holder, "message"));
			}
		}
	}
	public String messageSplice(String message,String type) {
		String[] splicedMessage=new String[3];
		splicedMessage=message.split("#");
		type=type.toLowerCase();
		switch(type) {
		case "message":
			return "The message is sent by the user "
			+QueryRunner.QueryRunner().getUserWithID(Integer.parseInt(splicedMessage[0])).getUserName()+"\n"
	+"The message is sent at the time "+splicedMessage[1]+"\n"
	+"The message is "+splicedMessage[2]+"\n";
		case "notification":
			return "The notification is sent by the user "
			+QueryRunner.QueryRunner().getUserWithID(Integer.parseInt(splicedMessage[0])).getUserName()+"\n"
	+"The notification is sent at the time "+splicedMessage[1]+"\n"
	+"The notification is "+splicedMessage[2]+"\n";
		default:
			return null;
			
		}
		
		
	}
}



