package mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class DatabaseConnector {
	public static DatabaseConnector DatabaseConnector(){
		if(dbms==null) {
			dbms=new DatabaseConnector();
		}
		return dbms;
	}
	private DatabaseConnector() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.conn=DriverManager.getConnection(DB_URL,USER,PASSWORD);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public Connection getConnection() {
		return this.conn;
	}
	
	
	private String DB_URL = "jdbc:mysql://localhost:3306/cfms?autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String USER = "root";
	private String PASSWORD = "BBgt.1234";
	private Connection conn=null;
	private static DatabaseConnector dbms=null;

public static void main(String[] args) throws Exception {
	QueryRunner qr=QueryRunner.QueryRunner();
	User bUser=qr.getUser("ahmet");
	System.out.println(new Encrypter().shaWithMD5Encrypter("9876"));
	//senderUID#sentAt#messageContent#
	userCreationFacade uFacade=userCreationFacade.userCreationFacade();
	qr.getUserMessages(2);
	ArrayList<String> mList=uFacade.getUserByID(2).getMessages().getMessages();
	String deneme=mList.get(0);
	String actual=new userMessages().messageSplice(deneme, "message");
	System.out.println();
	System.out.println(mList.get(0));
	String expected="The message is sent by the user burak\n"
			+ "The message is sent at the time 22.06\n"
			+ "The message is Yo whatup \n";
	System.out.println("Actual");
	System.out.println(actual);
	System.out.println("Expected");
	System.out.println(expected);
	actual=actual.replace(" ","");
	expected=expected.replace(" ","");
	System.out.println(actual.equals(expected));
	boolean whatup=qr.deleteUserMessages("Kurtlar vadisi ilk 97 bölüm", 2, 1);
	System.out.println(whatup);
	CrimeReport cr=qr.getReport(4);
	CriminalPerson cp=qr.getCriminalPerson(3);
	System.out.println("Report info in the user\n");
	ArrayList<CrimeReport> crlist=uFacade.getUserByID(1).getReport();
	for(CrimeReport cr2:crlist) {
		System.out.println(cr2.toString());	
	}
	
}
}

class QueryRunner{
	public static QueryRunner QueryRunner() {
		if(query_instance==null) {
			query_instance=new QueryRunner();
		}
		return query_instance;
		
	}
	private QueryRunner() {
		dbms=DatabaseConnector.DatabaseConnector();		
	}
	public DatabaseConnector getDatabaseConnector() {
		return this.dbms;
	}
	//alter table User add loggedIn char(1) default "F";
	//Work on this give admins a different row to check if they are admins
	public void insertUser(String userName,String password,String createdAt,String lastDate,String type) {
		try {
			//insert into UserMessages(senderUID,recieverUID,messageContent,sentAt)values (1,2,"Yo whatup","22.06");
			String sql="INSERT INTO User "+ "(id,userName,password,createdAt,lastDate,type)VALUES "+"(?,?,?,?,?,?)"; 
		    PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
		    stmt.setInt(1, idCreator.idCreator().createID());
		    stmt.setString(2, "burak");
		    stmt.setString(3, new Encrypter().shaWithMD5Encrypter(password));
		    stmt.setString(4, createdAt);
		    stmt.setString(5, lastDate);
		    //if admin insert admin as type else insert normal
		    stmt.setString(6,type);
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void insertUserWithObject(User u) {
		try {
			//insert into UserMessages(senderUID,recieverUID,messageContent,sentAt)values (1,2,"Yo whatup","22.06");
			String sql="INSERT INTO User "+ "(id,userName,password,createdAt,lastDate,type)VALUES "+"(?,?,?,?,?,?)"; 
		    PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
		    stmt.setInt(1, u.getID());
		    stmt.setString(2, u.getUserName());
		    stmt.setString(3, u.getUserPassword());
		    stmt.setString(4, u.getCreatedAt());
		    stmt.setString(5, u.getLastDate());
		    //if admin insert admin as type else insert normal
		    stmt.setString(6,u.getType());
			stmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public boolean isLoggedIn(int uID) {
		try {
			String sql="Select loggedIn from User where id=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, uID);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			String loggedIn=rs.getString("loggedIn");
			if(loggedIn.equalsIgnoreCase("t")) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public void printUserNameID() {
		try {
			String sql="Select userName,id from User";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				System.out.println("User name is "+rs.getString("userName")
				+" The user id is "+rs.getInt("id"));
			}
		}catch(SQLException e) {
		}
	}
	public boolean flipLoggedIn(String loggedIn,int uID) {
		try {
			String sql="UPDATE User set loggedIn=? where id=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setString(1, "T");
			stmt.setInt(2, uID);
			stmt.executeUpdate();
			return true;	
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean isAdmin(String userName) {
		try {
			String sql="Select type from User where userName=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setString(1, userName);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			String type=rs.getString("type");
			if(type.equals("admin")) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean validateUserIDExists(int id) {
		try {
			String sql="SELECT EXISTS(Select id from User where"
					+ " id=?)";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			return rs.getBoolean(1);
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}
	//validates user
	public boolean validateUser(String userName,String password) {
		try {
			String sql="SELECT EXISTS(Select * from User where"
					+ " userName=? and password=?)";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2,new Encrypter().shaWithMD5Encrypter(password));
			ResultSet rs=stmt.executeQuery();
			rs.next();
			if(rs.getBoolean(1)) {
				sql="UPDATE User SET lastDate=? where userName=? and password=?";
				stmt=this.dbms.getConnection().prepareStatement(sql);
				stmt.setString(1,timeCreator.createTimeNow());
				stmt.setString(2, userName);
				stmt.setString(3, password);
				stmt.executeUpdate();
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}
	//returns user object with the data got from the database
	public User getUser(String userName) {
		try {
			String sql="Select id,userName,password,createdAt,lastDate,type from User where userName=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setString(1, userName);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			return userCreationFacade.userCreationFacade().CreateUserWithType(rs.getInt("id")
					, rs.getString("userName"), rs.getString("password"), rs.getString("createdAt"), 
					rs.getString("lastDate"),rs.getString("type"));
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public User getUserWithPass(String userName,String password) {
		try {
			String sql="Select id,userName,password,createdAt,lastDate,type from User where userName=? and password=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			return userCreationFacade.userCreationFacade().CreateUserWithType(rs.getInt("id")
					, rs.getString("userName"), rs.getString("password"), rs.getString("createdAt"), 
					rs.getString("lastDate"),rs.getString("type"));
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public User getUserWithID(int id) {
		try {
			String sql="Select id,userName,password,createdAt,lastDate,type from User where id=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			return userCreationFacade.userCreationFacade().CreateUserWithType(rs.getInt("id")
					, rs.getString("userName"), rs.getString("password"), rs.getString("createdAt"), 
					rs.getString("lastDate"),rs.getString("type"));
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//Gets the max ID from database tables to feed into the database
	public int getMaxId() {
		try {
			String CriminalMaxID="select MAX(cpID) from CriminalPerson";
			String ReportMaxID="select MAX(rID) from Report";
			String UserMaxID="select MAX(id) from User";
			int CriminalMaxIDVal=0;
			int ReportMaxIDVal=0;
			int UserMaxIDVal=0;
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(CriminalMaxID);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				CriminalMaxIDVal=rs.getInt(1);
			}
			stmt=this.dbms.getConnection().prepareStatement(ReportMaxID);
			rs=stmt.executeQuery();
			while(rs.next()) {
				ReportMaxIDVal=rs.getInt(1);
			}
			stmt=this.dbms.getConnection().prepareStatement(UserMaxID);
			rs=stmt.executeQuery();
			while(rs.next()) {
				UserMaxIDVal=rs.getInt(1);
			}
			//System.out.println("CriminalMaxIDVal "+CriminalMaxIDVal+"\n"+"ReportMaxIDVal "+ReportMaxIDVal+"\n"+"UserMaxIDVal "+UserMaxIDVal+"\n");
			int max=0;
			if(CriminalMaxIDVal>ReportMaxIDVal) {
				max=CriminalMaxIDVal;
			}else {
				max=ReportMaxIDVal;
			}
			if(UserMaxIDVal>max) {
				max=UserMaxIDVal;
			}
			return max;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	//add a logic to get message than go into reports than criminal person
	//the strings cannot be kept inside a database in a single row as a string
	//therefore we will concat the messages
	
	//The ussermessage database creation query
	//create table UserMessages 
	//create table UserMessages (mID int primary key auto_increment,senderUID int NOT NULL,recieverUID int NOT NULL,messageContent varchar(255),sentAt varchar(255),
	//		FOREIGN KEY(senderUID) REFERENCES User(id) ON UPDATE CASCADE ON DELETE CASCADE, 
	//		FOREIGN KEY (recieverUID) REFERENCES User(id) ON UPDATE CASCADE ON DELETE CASCADE);
	
	//delete from UserMessages where mID=2;
	//this deletes message from the database by firstly getting the mID with the given values
	public boolean deleteUserMessages(String messageContent,int senderUID,int recieverUID) {
		try {
			int mID=0;
			String sqlSelect="Select mID from UserMessages where senderUID=? and recieverUID=? and "
					+ "messageContent=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sqlSelect);
			stmt.setInt(1, senderUID);
			stmt.setInt(2, recieverUID);
			stmt.setString(3, messageContent);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				mID=rs.getInt(1);
			}
			String sql="delete from UserMessages where mID=?";
			stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, mID);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;	
		}
	}
	//this deletes notifications from the database by firstly getting the mID with the given values
		public boolean deleteUserNotifications(String messageContent,int senderUID,int recieverUID) {
			try {
				int mID=0;
				String sqlSelect="Select mID from UserNotifications where senderUID=? and recieverUID=? and "
						+ "messageContent=?";
				PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sqlSelect);
				stmt.setInt(1, senderUID);
				stmt.setInt(2, recieverUID);
				stmt.setString(3, messageContent);
				ResultSet rs=stmt.executeQuery();
				while(rs.next()) {
					mID=rs.getInt(1);
				}
				String sql="delete from UserNotifications where mID=?";
				stmt=this.dbms.getConnection().prepareStatement(sql);
				stmt.setInt(1, mID);
				stmt.executeUpdate();
				return true;
				
			}catch(SQLException e) {
				e.printStackTrace();
				return false;	
			}
		}
		//this inserts new user messsage into the database
	public boolean setUserMessages(String messageContent,int senderUID,int recieverUID) {
		try {
			String sql="INSERT INTO UserMessages (senderUID,recieverUID,messageContent,sentAt)VALUES (?,?,?,?)";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, senderUID);
			stmt.setInt(2, recieverUID);
			stmt.setString(3, messageContent);
			stmt.setString(4,timeCreator.createTimeNow());
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//notifications are the updates on reports or complaints submitted by the user
	//this inserts new notification into the database
	public boolean setUserNotifications(String messageContent,int senderUID,int recieverUID) {
		try {
			String sql="INSERT INTO UserNotifications (senderUID,recieverUID,messageContent,sentAt)VALUES (?,?,?,?)";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, senderUID);
			stmt.setInt(2, recieverUID);
			stmt.setString(3, messageContent);
			stmt.setString(4,timeCreator.createTimeNow());
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//get user messages may be changed to use ID
	//Insert into user insert into User(id,userName,password,createdAt,lastDate,type) values(2,"ahmet","ccd970666edc2b8c40a446f7b9c77ec0","6.66","7.77","normal");
	public void getUserMessages(int recieverID){
		try {
			String sql="Select messageContent,senderUID,sentAt from UserMessages where recieverUID=? ";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, recieverID);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				//we have arraylist as object, we have to use adaptor, therefore the message is concatenated to 3 part
				//senderUID#sentAt#messageContent#
				//use regex and split this data into 3 parts before showing
				String addMessage=rs.getInt("senderUID")+"#"+rs.getString("sentAt")+"#"+rs.getString("messageContent")+"#";
				userCreationFacade.userCreationFacade().getUserByID(recieverID).getMessages().addMessages(addMessage);		
				}
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	//create table UserNotifications (mID int primary key auto_increment,senderUID int NOT NULL,recieverUID int NOT NULL,messageContent varchar(255),sentAt varchar(255),
	//FOREIGN KEY(senderUID) REFERENCES User(id) ON UPDATE CASCADE ON DELETE CASCADE, 
	//FOREIGN KEY (recieverUID) REFERENCES User(id) ON UPDATE CASCADE ON DELETE CASCADE);
	public void getUserNotifications(int recieverID){
		try {
			String sql="Select messageContent,senderUID,sentAt from UserNotifications where recieverUID=? ";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, recieverID);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				//we have arraylist as object, we have to use adaptor, therefore the message is concatenated to 3 part
				//senderUID#sentAt#messageContent#
				//use regex and split this data into 3 parts before showing
				String addMessage=rs.getInt("senderUID")+"#"+rs.getString("sentAt")+"#"+rs.getString("messageContent")+"#";
				userCreationFacade.userCreationFacade().getUserByID(recieverID).getMessages().addNotification(addMessage);		
				}
		}catch(Exception e) {
			e.printStackTrace();
		}	
	}
	//report queries to database, 
	//report table creation query
	//create table Report(rID int primary key,reportName varchar(255),reportLocation varchar(255),reportContent varchar(255),reportDate varchar(255),
	//type varchar(255), cpID int, FOREIGN KEY(cpID) references CriminalPerson(cpID) ON UPDATE CASCADE ON DELETE CASCADE);
	
	//insert report query
	//insert into Report (rID,reportName,reportLocation,reportContent,reportDate,type,cpID) VALUES(4,"Izmirde Kayip","Izmir","Izmirde aksam saatlerinde kaybolan kisi araniyor","19.06.2021","Missing People Report",3);

	//alter table Report add uID int not null;
	//alter table Report add constraint foreign key(uID) references User(id) on UPDATE CASCADE ON DELETE CASCADE;
	//alter table Report add rStatus varchar(255) not null;
	
	//this function adds report to the database
	public boolean insertReport(CrimeReport report) {
		try {
			String sql="INSERT INTO Report (rID,reportName,reportLocation,reportContent,reportDate,type,cpID,uID,rStatus) "
					+ "VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, report.getRID());
			stmt.setString(2,report.getReportName());
			stmt.setString(3, report.getReportLocation());
			stmt.setString(4, report.getReportContent());
			stmt.setString(5,report.getReportDate());
			stmt.setString(6, report.getType());
			stmt.setInt(7, report.getCPID());
			stmt.setInt(8, report.getUID());
			stmt.setString(9, report.getReportStatus());
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean deleteReport(int rID) {
		try {
			String sql="delete from Report where rID=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, rID);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}
	//Assumption only the content and report status can be changed in each report
	//Our earlier versions allowed each field to change but when though of it it seems really 
	//meaningless to let every field be changed, it violates the objective of app
	public boolean updateReport(int rID,String reportContent,String rStatus) {
		try {
			String sql="UPDATE Report SET reportContent=?,rStatus=? where rID=? ";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setString(1, reportContent);
			stmt.setString(2, rStatus);
			stmt.setInt(3, rID);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public CrimeReport getReport(int rID) {
		try {
			String sql="SELECT reportName,reportLocation,reportContent,reportDate,type,cpID,uID,rStatus from Report where rID=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1,rID);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			return personFacade.personFacade().createCrimeReportFromDB(rID, rs.getString("reportName"), 
					rs.getString("reportLocation"), rs.getString("reportContent"), rs.getString("reportDate"),
					rs.getString("type"),rs.getInt("cpID"),rs.getInt("uID"),rs.getString("rStatus"));
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public int getUIDFromRID(int rID) {
		try {
			String sql="Select uID from Report where rID=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, rID);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			return rs.getInt("uID");
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -99;
		}
	}
	
	public int getUIDFromfID(int fID) {
		try {
			String sql="Select uID from userFeedback where fID=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, fID);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			return rs.getInt("uID");
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -99;
		}
	}
	
	//CriminalPerson queries to database
	//CriminalPerson table creation query
	//create table CriminalPerson(cpID int primary key,name varchar(255) not null,
	//age int,date varchar(255) not null,phoneNumber varchar(255),type varchar(255) not null,reportID int,
	//FOREIGN KEY(reportID) references Report(rID) on UPDATE CASCADE ON DELETE CASCADE);
	
	//insert query
	//insert into CriminalPerson (cpID,name,age,date,phoneNumber,type,reportID) VALUES (3,"Polat Alemdar",55,"19.06.2021","5550002222","Missing",4);
	//alter table CriminalPerson add cpStatus varchar(255) not null;
	public boolean insertCriminalPerson(CriminalPerson pHolder) {
		try {
			String sql="INSERT INTO CriminalPerson (cpID,name,age,date,phoneNumber,type,reportID,cpStatus)VALUES "
					+ "(?,?,?,?,?,?,?,?)";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1,pHolder.getID());
			stmt.setString(2, pHolder.getName());
			stmt.setInt(3, pHolder.getAge());
			stmt.setString(4, pHolder.getDate());
			stmt.setString(5, pHolder.getPhoneNumber());
			stmt.setString(6, pHolder.getClassSpecificType());
			stmt.setInt(7, pHolder.getReport().getRID());
			stmt.setString(8, pHolder.getCPStatus());
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteCriminalPerson(int cpID) {
		try {
			String sql="DELETE from CriminalPerson where cpID=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, cpID);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//Assumption, only the cpStatus can be changed in criminal person
	//the other values are constants
	//they can be most wanted,found,APB etc.
	public boolean updateCriminalPerson(int cpID,String cpStatus) {
		try {
			String sql="UPDATE CriminalPerson SET cpStatus=? where cpID=? ";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setString(1, cpStatus);
			stmt.setInt(2, cpID);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//add criminal person status ,pStatus varchar(255)
	//add report status, rStatus varchar(255)
	
	public CriminalPerson getCriminalPerson(int cpID) {
		try {
			String sql="SELECT cpID,name,age,date,phoneNumber,type,reportID,cpStatus from CriminalPerson where cpID=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1,cpID);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			return personFacade.personFacade().createCriminalPersonFromDB(cpID, rs.getString("name"), 
					rs.getInt("age"), rs.getString("date"), rs.getString("phoneNumber"), rs.getString("type"),
					rs.getInt("reportID"),rs.getString("cpStatus"));
			}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void getAllCriminalPersonFromDB() {
		try {
			if(!personFacade.personFacade().holder.getCriminalPersonMap().isEmpty()) {
				personFacade.personFacade().holder.getCriminalPersonMap().clear();
			}
			String sql="SELECT cpID,name,age,date,phoneNumber,type,reportID,cpStatus from CriminalPerson";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				personFacade.personFacade().createCriminalPersonFromDB(rs.getInt("cpID"), rs.getString("name"), 
						rs.getInt("age"), rs.getString("date"), rs.getString("phoneNumber"), rs.getString("type"),
						rs.getInt("reportID"),rs.getString("cpStatus"));
			}
			
			}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public int getReportMaxID() {
		String sql="SELECT MAX(rID) as max from Report";
		try {
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			return rs.getInt("max");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -99;
		}
		
	}
	
	//hotnews
	//create table Hotnews(hnID int primary key auto_increment,hnContent varchar(255) not null,hnDate varchar(255) not null,adminID int,
	//foreign key(adminID) references User(id) on update cascade on delete cascade);
	
	public boolean insertHotnews(News nHolder) {
		try {
			String sql="INSERT INTO Hotnews (hnID,hnContent,hnDate,adminID)VALUES "
					+ "(?,?,?,?)";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, nHolder.getHNID());
			stmt.setString(2,nHolder.getHNContent());
			stmt.setString(3,nHolder.getHNDate());
			stmt.setInt(4, nHolder.getAdminID());
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public int getMaxHotnewsID() {
		try {
			String sql="SELECT max(hnID) as max from Hotnews";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			return rs.getInt("max");
		}catch(SQLException e) {
			e.printStackTrace();
			return -99;
		}
	}
	
	public int getMaxFeedbackID() {
		try {
			String sql="SELECT max(fID) as max from userFeedback";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			return rs.getInt("max");
		}catch(SQLException e) {
			e.printStackTrace();
			return -99;
		}
	}
	
	public boolean deleteHotNews(int hnID) {
		try {
			String sql="DELETE from Hotnews where hnID=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, hnID);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//assumption only content and the date can be changed
	public boolean updateHotNews(int hnID,String hnContent) {
		try {
			String sql="UPDATE Hotnews SET hnContent=?,hnDate=? where hnID=? ";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setString(1, hnContent);
			stmt.setString(2, timeCreator.createTimeNow());
			stmt.setInt(3, hnID);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public void getHotNews() {
		try {
			if(!NewsHolder.NewsHolder().getNewsHolder().isEmpty()) {
				NewsHolder.NewsHolder().getNewsHolder().clear();
			}
			String sql="SELECT hnID,adminID,hnContent,hnDate from Hotnews";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			NewsHolder nHolder=NewsHolder.NewsHolder();
			while(rs.next()) {
				nHolder.addAllNews(rs.getInt("hnID"),rs.getInt("adminID"),
						rs.getString("hnContent"), rs.getString("hnDate"));
			}
			}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//userFeedBack
	//create table userFeedback(fID int primary key auto_increment,fbContent varchar(255) not null, fbDate varchar(255) not null,uID int not null,
	//foreign key(uID) references User(id) on update cascade on delete cascade);
	
	
	public boolean insertUserFeedBack(Feedback fHolder) {
		try {
			String sql="INSERT INTO userFeedback (fID,fbContent,fbDate,uID,rating)VALUES "
					+ "(?,?,?,?,?)";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, fHolder.getFID());
			stmt.setString(2,fHolder.getFBContent());
			stmt.setString(3,fHolder.getFBDate());
			stmt.setInt(4, fHolder.getFBUID());
			stmt.setInt(5,fHolder.getRating());
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteUserFeedBack(int fID) {
		try {
			String sql="DELETE from userFeedback where fID=?";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setInt(1, fID);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//assumption only content and the date can be changed
	public boolean updateUserFeedBack(int fID,String fbContent) {
		try {
			String sql="UPDATE userFeedback SET fbContent=?,fbDate=? where fID=? ";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			stmt.setString(1, fbContent);
			stmt.setString(2, timeCreator.createTimeNow());
			stmt.setInt(3, fID);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public void getUserFeedBacks() {
		try {
			if(!feedBackHolder.feedBackHolder().getHolder().isEmpty()) {
				feedBackHolder.feedBackHolder().getHolder().clear();
			}
			String sql="SELECT fID,fbContent,fbDate,uID,rating from userFeedback";
			PreparedStatement stmt=this.dbms.getConnection().prepareStatement(sql);
			ResultSet rs=stmt.executeQuery();
			feedBackHolder fbHolder=feedBackHolder.feedBackHolder();
			while(rs.next()) {
				fbHolder.addAllFeedbacks(rs.getInt("fID"), rs.getInt("uID"), rs.getInt("rating"),
						rs.getString("fbContent"),rs.getString("fbDate"));
			}
			}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private DatabaseConnector dbms=null;
	private static QueryRunner query_instance=null;
	
}



