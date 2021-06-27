package mainPackage;

import java.util.ArrayList;

interface News {
	public int getHNID();
	public String getHNContent();
	public String getHNDate();
	public int getAdminID();
	public void setHNID(int HNID);
	public void setHNContent(String HNContent);
	public void setHNDate(String HNDate);
	public void setAdminID(int adminID);
	public void printInfo();
}

public class Hotnews implements News {
	
	@Override
	public int getHNID() {
		return this.HNID;
	}
	@Override
	public String getHNContent() {
		return this.HNContent;
	}
	@Override
	public String getHNDate() {
		return this.HNDate;
	}
	@Override
	public int getAdminID() {
		return this.adminID;
	}
	@Override
	public void setHNID(int HNID) {
		this.HNID=HNID;
	}
	@Override
	public void setHNContent(String HNContent) {
		this.HNContent=HNContent;
	}
	@Override
	public void setHNDate(String HNDate) {
		this.HNDate=HNDate;
	}
	@Override
	public void setAdminID(int adminID) {
		this.adminID=adminID;
	}
	public Hotnews(int HNID,int adminID,String HNContent,String HNDate) {
		if(HNID==-99) {
			this.HNID=QueryRunner.QueryRunner().getMaxHotnewsID()+1;
		}else {
			this.HNID=HNID;
		}
		this.adminID=adminID;
		this.HNContent=HNContent;
		this.HNDate=timeCreator.createTimeNow();
	}
	public String toString() {
		return "The HNID is "+HNID
				+"\nThe admin ID is"+adminID
				+"\nThe content is "+HNContent
				+"\nThe date created is "+HNDate+"\n";
	}
	public void printInfo() {
		System.out.println(this.toString());
	}
	private int HNID;
	private int adminID;
	private String HNContent;
	private String HNDate;
}
class NewsHolder{
	public static NewsHolder NewsHolder() {
		if(single_instance==null) {
			single_instance=new NewsHolder();
		}
		return single_instance;
		
	}
	private NewsHolder() {
		newsHolder=new ArrayList<News>();
	}
	public void addAllNews(int hnID,int adminID,String hnContent,String hnDate) {
		this.newsHolder.add(new Hotnews(hnID,adminID,hnContent,hnDate));
	}
	public void printAllNews() {
		for(int i=0;i<this.newsHolder.size();i++) {
			newsHolder.get(i).printInfo();
		}
	}
	public ArrayList<News> getNewsHolder(){
		return this.newsHolder;
	}
	private ArrayList<News> newsHolder;
	private static NewsHolder single_instance=null;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
