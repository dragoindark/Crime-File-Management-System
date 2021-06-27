package mainPackage;

public class AuthenticationController {
	
	public static AuthenticationController AuthenticationController() {
		if(single_instance==null) {
			single_instance=new AuthenticationController();
		}
		return single_instance;
	}
	private AuthenticationController() {
		qr=QueryRunner.QueryRunner();
	}
	public boolean checkIfAdmin(User u) {
		return (u.getType().equalsIgnoreCase("admin")) ? true: false;
	}
	public boolean checkIfUserCreatedOrAdmin(User u,int id) {
		return (u.getID()==id || u.getType().equalsIgnoreCase("admin")) ? true : false;
	}
	//methods to authenticate user
		//deleteUserMessages ==> the user owning it or the admin +
		//deleteUserNotifications ==> the user owning it or the admin +
		//if there are no criminal person with the given cpID while creating the report
		//ask the user to also create the criminal person
		//deleteReport ==> the user owning the report or the admin +
		//updateReport ==> the user owning the report or the admin +
		//assumption, only admin can delete or update criminal person
		//deleteCriminalPerson ==> the admin +
		//updateCriminalPerson ==> the admin +
	//qr.isLoggedIn(int uID)
	
	//these are the adapters that wrap and check wheter or not the user is loggedIn or not
	public boolean setUserMessagesAuthLoggedIn(User u,String messageContent,int senderUID,int recieverUID) {
		boolean isLoggedIn=qr.isLoggedIn(u.getID());
		if(isLoggedIn) {
			qr.setUserMessages(messageContent, senderUID, recieverUID);
			return true;
		}else {
			return false;
		}
		
	}
	public boolean setUserNotificationsAuthLoggedIn(User u,String messageContent,int senderUID,int recieverUID) {
		boolean isLoggedIn=qr.isLoggedIn(u.getID());
		if(isLoggedIn) {
			qr.setUserNotifications(messageContent, senderUID, recieverUID);
			return true;
		}else {
			return false;
		}
		
	}
	public void getUserMessagesLoggedIn(User u,int recieverID) {
		boolean isLoggedIn=qr.isLoggedIn(u.getID());
		if(isLoggedIn) {
			qr.getUserMessages(recieverID);
		}else {
			return;
		}
		
	}
	public void getUserNotificationsLoggedIn(User u,int recieverID) {
		boolean isLoggedIn=qr.isLoggedIn(u.getID());
		if(isLoggedIn) {
			qr.getUserNotifications(recieverID);
		}else {
			return;
		}
		
	}
	
	public boolean insertReportLoggedIn(User u,CrimeReport report) {
		boolean isLoggedIn=qr.isLoggedIn(u.getID());
		if(isLoggedIn) {
			qr.insertReport(report);
			return true;
		}else {
			return false;
		}
		
	}
	public CrimeReport getReportLoggedIn(User u,int rID) {
		boolean isLoggedIn=qr.isLoggedIn(u.getID());
		if(isLoggedIn) {
			return qr.getReport(rID);
		}else {
			return null;
		}
		
	}
	public boolean insertCriminalPersonLoggedIn(User u,CriminalPerson pHolder) {
		boolean isLoggedIn=qr.isLoggedIn(u.getID());
		if(isLoggedIn) {
			qr.insertCriminalPerson(pHolder);
			return true;
		}else {
			return false;
		}
		
	}
	public void getHotNewsLoggedIn(User u) {
		boolean isLoggedIn=qr.isLoggedIn(u.getID());
		if(isLoggedIn) {
			qr.getHotNews();
			//prints all data with toString
			NewsHolder.NewsHolder().printAllNews();
		}
	}
	public void getUserFeedBacksLoggedIn(User u) {
		boolean isLoggedIn=qr.isLoggedIn(u.getID());
		if(isLoggedIn) {
			qr.getUserFeedBacks();
			//prints all data with toString
			feedBackHolder.feedBackHolder().printAllFeedbacks();
		}
	}
	//these are the adapters for checking if the user is the one created the object
	//or if the user is an admin
	public boolean deleteUserMessagesAuth(User u,String messageContent,int senderUID,int recieverUID) {
		boolean isAuthenticated=checkIfUserCreatedOrAdmin(u, recieverUID);
		if(isAuthenticated) {
			qr.deleteUserMessages(messageContent, senderUID, recieverUID);
			return true;
		}else {
			return false;
		}
	}
	public boolean deleteUserNotificationsAuth(User u,String messageContent,int senderUID,int recieverUID) {
		boolean isAuthenticated=checkIfUserCreatedOrAdmin(u, recieverUID);
		if(isAuthenticated) {
			qr.deleteUserNotifications(messageContent, senderUID, recieverUID);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean deleteReportAuth(User u,int rID) {
		int uID=qr.getUIDFromRID(rID);
		if(uID==-99) {
			System.out.println("User not legal");
			return false;
		}
		boolean isAuthenticated=checkIfUserCreatedOrAdmin(u, uID);
		if(isAuthenticated) {
			qr.deleteReport(rID);
			return true;
		}else {
			return false;
		}
	}
	public boolean updateReportAuth(User u,int rID,String reportContent,String rStatus) {
		int uID=qr.getUIDFromRID(rID);
		if(uID==-99) {
			System.out.println("User not legal");
			System.out.println("You cannot change reports that you do not own");
			return false;
		}
		String notification="The information of the report changed to "+qr.getReport(rID).toString();
		boolean isAuthenticated=checkIfUserCreatedOrAdmin(u, uID);
		if(isAuthenticated) {
			qr.updateReport(rID, reportContent, rStatus);
			qr.setUserNotifications(notification, uID, rID);
			return true;
		}else {
			return false;
		}
	}
	public boolean deleteCriminalPerson(User u,int cpID) {
		boolean isAuthenticated=checkIfAdmin(u);
		if(isAuthenticated) {
			qr.deleteCriminalPerson(cpID);
			return true;
		}else {
			return false;
		}
	}
	public boolean updateCriminalPersonAuth(User u,int cpID,String cpStatus) {
		boolean isAuthenticated=checkIfAdmin(u);
		if(isAuthenticated) {
			qr.updateCriminalPerson(cpID, cpStatus);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean insertHotNewsAuth(User u,News nHolder) {
		boolean isAuthenticated=checkIfAdmin(u);
		if(isAuthenticated) {
			qr.insertHotnews(nHolder);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean deleteHotNewsAuth(User u,int hnID) {
		boolean isAuthenticated=checkIfAdmin(u);
		if(isAuthenticated) {
			qr.deleteHotNews(hnID);
			return true;
		}else {
			return false;
		}
	}
	public boolean updateHotNewsAuth(User u,int hnID,String hnContent) {
		boolean isAuthenticated=checkIfAdmin(u);
		if(isAuthenticated) {
			qr.updateHotNews(hnID, hnContent);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean insertUserFeedBackAuth(User u,Feedback fHolder) {
		boolean isAuthenticated=checkIfUserCreatedOrAdmin(u, fHolder.getFBUID());
		if(isAuthenticated) {
			qr.insertUserFeedBack(fHolder);
			return true;
		}else {
			return false;
		}
	}
	public boolean deleteUserFeedBackAuth(User u,int fID) {
		boolean isAuthenticated=checkIfUserCreatedOrAdmin(u, u.getID());
		if(isAuthenticated) {
			qr.deleteUserFeedBack(fID);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean updateUserFeedBackAuth(User u,int fID,String fbContent) {
		boolean isAuthenticated=checkIfUserCreatedOrAdmin(u, u.getID());
		if(isAuthenticated) {
			qr.updateUserFeedBack(fID, fbContent);
			return true;
		}else {
			return false;
		}
	}
	private static AuthenticationController single_instance=null;
	private QueryRunner qr=null;

	//add hot news and user feedbacks
	
	
	
	
	
}
