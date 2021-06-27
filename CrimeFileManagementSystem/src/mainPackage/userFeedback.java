package mainPackage;

import java.util.ArrayList;

interface Feedback{
	public void setFID(int fID);
	public void setFBContent(String fbContent);
	public void setRating(int rating);
	public void setFBDate(String fbDate);
	public void setFBUID(int fbUID);
	public int getFID();
	public String getFBContent();
	public int getRating();
	public String getFBDate();
	public int getFBUID();
	public void printInfo();
}

public class userFeedback implements Feedback {
	public userFeedback(int fID,String fbContent,String fbDate,int rating,int uID) {
		if(fID==-99) {
			this.fID=QueryRunner.QueryRunner().getMaxFeedbackID()+1;
		}else {
			this.fID=fID;
		}
		this.fbContent=fbContent;
		this.fbDate=timeCreator.createTimeNow();
		this.rating=rating;
		this.uID=uID;
	}
	@Override
	public void setFID(int fID) {
		this.fID=fID;
	}
	@Override
	public void setFBContent(String fbContent) {
		this.fbContent=fbContent;		
	}
	@Override
	public void setRating(int rating) {
		this.rating=rating;
	}
	@Override
	public void setFBDate(String fbDate) {
		this.fbDate=fbDate;
	}
	@Override
	public void setFBUID(int fbUID) {
		this.uID=fbUID;
	}
	@Override
	public int getFBUID() {
		return this.uID;
	}
	@Override
	public int getFID() {
		return this.fID;
	}
	@Override
	public String getFBContent() {
		return this.fbContent;
	}
	@Override
	public int getRating() {
		return this.rating;
	}
	@Override
	public String getFBDate() {
		return this.fbDate;
	}
	@Override
	public void printInfo() {
		System.out.println(this.toString());
	}
	public String toString() {
		return "The feedback id is "+fID
				+"\nThe feedback is submitted by the user with the id "+uID
				+"\nThe feedback rating is "+rating
				+"\nThe feedback content is "+fbContent
				+"\nThe feedback date is  "+fbDate;
	}
	private int fID;
	private String fbContent;
	private int rating;//rating between 1 to 10
	private String fbDate; //the date fb is created
	private int uID;//the user that created the fb
}

class feedBackHolder{
	private feedBackHolder() {
		feedbackHolder=new ArrayList<Feedback>();
	}
	public static feedBackHolder feedBackHolder() {
		if(single_instance==null) {
			single_instance=new feedBackHolder();
		}
		return single_instance;
	}
	public void addAllFeedbacks(int fID,int uID,int rating,String fbContent,String fbDate) {
		this.feedbackHolder.add(new userFeedback(fID, fbContent, fbDate,rating, uID));
	}
	public void printAllFeedbacks() {
		for(int i=0;i<this.feedbackHolder.size();i++) {
			feedbackHolder.get(i).printInfo();
		}
	}
	public ArrayList<Feedback> getHolder(){
		return this.feedbackHolder;
	}
	private static feedBackHolder single_instance=null;
	private ArrayList<Feedback> feedbackHolder;
}
