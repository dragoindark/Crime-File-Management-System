package mainPackage;

import java.util.Scanner;

public class ApplicationFinal {

	public static void main(String[] args) {
		System.out.println("Welcome to the crime file management system");
		//facade creation
		AuthenticationController auth=AuthenticationController.AuthenticationController();
		userCreationFacade uFacade=userCreationFacade.userCreationFacade();
		QueryRunner qr=QueryRunner.QueryRunner();
		personFacade pFacade=personFacade.personFacade();
		NewsHolder nFacade=NewsHolder.NewsHolder();
		PopulateDB.configure();//temp solution
		feedBackHolder fbFacade=feedBackHolder.feedBackHolder();
		Scanner input=new Scanner(System.in);
		//populate memory with data
		qr.getAllCriminalPersonFromDB();
		qr.getHotNews();
		qr.getUserFeedBacks();
		boolean insideSystem=false;
		User usr=null;
		while(true) {
			while(!insideSystem) {
				System.out.print("Please create a new account or login with an existing account \n"
						+ "Enter 1 or login for login and 2 or new account for new account \n"
						+ "Type exit or 99 anytime to exit the system\n");
				String loginText=input.nextLine();
				loginText=loginText.toLowerCase();
				switch(loginText) {
				//cases without body is for multiple choice switch cases
				case "login":
				case "1":
					System.out.println("Pleaser create a new account or login with an existing account");
					System.out.println("Entering into the system");
					System.out.println("Please type the username");
					String name=input.nextLine();
					System.out.println("Please type the password");
					String password=input.nextLine();
					boolean validUser=qr.validateUser(name, password);
					if(validUser) {
						usr=qr.getUser(name);
						boolean flipped=qr.flipLoggedIn("T", usr.getID());
						qr.getUserMessages(usr.getID());
						qr.getUserNotifications(usr.getID());
						System.out.println("Welcome to the system "+usr.getUserName());
						uFacade.getUserByID(usr.getID()).printAllNewInfo();
						insideSystem=true;						
					}else {
						System.out.println("Not valid user try again");
					}
					break;
				case "new account":
				case "2":
					System.out.println("Creating new account");
					System.out.println("Please enter username chosen");
					String newName=input.nextLine();
					System.out.println("Please enter the password");
					String newPassword=input.nextLine();
					//creating a new account with normal type
					usr=new NormalUser(newName,newPassword);
					qr.insertUserWithObject(usr);
					qr.flipLoggedIn("T",usr.getID());
					insideSystem=true;
					break;
				case "exit":
				case "99":
					System.out.println("Exiting the system");
					qr.flipLoggedIn("F", usr.getID());
					System.exit(1);
					break;
				default:
					System.out.println("Wrong input try again");
					break;
				}							
				}
				if(qr.isAdmin(usr.getUserName())) {
					System.out.println("Logged in as the admin");
					System.out.println("Initiating the admin panel");
					while(true) {
						System.out.println(
								"\nTo see all of the criminal person type pPerson or 1"
								+"\nTo see all of the criminal reports type pReport or 2"
								+"\nTo see all of the hot news type pHotnews or 3"
							    +"\nTo see all of the user feedbacks type pFeedbacks or 4"
								+"\nTo delete criminal person type dCriminal or 5"
								+"\nTo update criminal person type uCriminal or 6"
								+"\nTo delete report type dReport or 7"
								+"\nTo update report type uReport or 8"
								+"\nTo insert new hot news type ihotNews or 9"
								+"\nTo delete hot news type dHotnews or 10"
								+"\nTo update hot news type uHotnews or 11"
								+"\nTo delete user feed back type dFeedback or 12"
								+"\nTo exit the system anytime type exit or 99"
                                );
						String uCommand=input.nextLine();
						uCommand=uCommand.toLowerCase();
						switch(uCommand) {
						//cases without body is for multiple choice switch cases
						case "pperson":
						case "1":
							pFacade.holder.printAllCriminalPersons();
							break;
						case "preport":
						case "2":
							pFacade.printAllReports();
							break;
						case "photnews":
						case "3":
							nFacade.printAllNews();					
							break;
						case "pfeedbacks":
						case "4":
							fbFacade.printAllFeedbacks();
							break;
						case "dcriminal":
						case "5":
							System.out.println("Please enter the id of criminal person"
									+ " you want to delete from the system");
							int personID=input.nextInt();
							input.nextLine();
							auth.deleteCriminalPerson(usr,personID);
							qr.getAllCriminalPersonFromDB();
							break;
						case "ucriminal":
						case "6":
							System.out.println("Please enter the id of criminal person"
									+ " you want to update in the system"
									);
						    int personID2=input.nextInt();
						    input.nextLine();
						    System.out.println("Please enter the new status for the criminal person");
						    String newStatus=input.nextLine();
						    auth.updateCriminalPersonAuth(usr, personID2, newStatus);
						    qr.getAllCriminalPersonFromDB();
							break;
						case "dreport":
						case "7":
							uFacade.getHolder().printAllUsersReport();
							System.out.println("Please enter the id of report"
									+ " you want to delete from the system"
									);
							int reportID=input.nextInt();
							input.nextLine();
							auth.deleteReportAuth(usr, reportID);
							qr.getAllCriminalPersonFromDB();
							break;
						case "ureport":
						case "8":
							uFacade.getHolder().printAllUsersReport();
							System.out.println("Please enter the id of the report"
									+ " you want to update in the system"
									);
							int reportID2=input.nextInt();
							input.nextLine();
							System.out.println("Please enter the new report content");
							String reportContent=input.nextLine();
							System.out.println("Please enter the new status for the criminal person");
							String reportStatus=input.nextLine();
							auth.updateReportAuth(usr, reportID2, reportContent, reportStatus);
							qr.getAllCriminalPersonFromDB();
							break;
						case "ihotnews":
						case "9":
						//int HNID,int adminID,String HNContent,String HNDate
							System.out.println("Please enter the content for the hotnew");
							String hotContent=input.nextLine();
							Hotnews hnHolder=new Hotnews(-99,usr.getID(),hotContent,"");
							auth.insertHotNewsAuth(usr, hnHolder);
							qr.getHotNews();
							break;
						case "dhotnews":
						case "10":
							System.out.println("Please enter the id of the hotnew you "
									+ "want to delete");
							int hID=input.nextInt();
							input.nextLine();
							auth.deleteHotNewsAuth(usr, hID);
							qr.getHotNews();
							break;
						case "uhotnews":
						case "11":
							System.out.println("Please enter the id of the hotnew you "
									+ "want to update");
							int hID2=input.nextInt();
							input.nextLine();
							System.out.println("Please enter the new content");
							String hotContent2=input.nextLine();
							auth.updateHotNewsAuth(usr, hID2, hotContent2);
							qr.getHotNews();
							break;
						case "dfeedback":
						case "12":
							System.out.println("Please enter the id of the "
									+ "feedback you want to delete");
							int fbID=input.nextInt();
							input.nextLine();
							auth.deleteUserFeedBackAuth(usr, fbID);
							qr.getUserFeedBacks();
							break;
						case "exit":
						case "99":
							System.out.println("Exiting the system");
							qr.flipLoggedIn("F", usr.getID());
							System.exit(1);
							break;
						default:
							System.out.println("Wrong input try again");
							break;
						}		
						}
					}else {
						wi:while(true) {
							System.out.println("\nPlease enter 1 or print hotnews to print all hot news"
									+ "\nPlease enter 2 or print userfeedback to print all userfeedback"
									+ "\nPlease enter 3 or new userfeedback to enter userfeedback"
									+ "\nPlease enter 4 or enter criminal person to enter new criminal person"
									+ "with the corresponding report"
									+ "\nPlease enter 5 or new report to create a new complaint or a basic report"
									+ "\nPlease enter 6 or edit report to edit an existing report"
									+ "\nPlease enter 7 or message to send message to another user"
									+ "\nPlease enter 98 or continue to exit creation system"
									+ "\nPlease enter 99 or exit to exit to operating system");
							
							String courseOfAction=input.nextLine();
							courseOfAction=courseOfAction.toLowerCase();
							switch(courseOfAction) {
							case "print hotnews":
							case "1":
								nFacade.printAllNews();					
								break;
							case "print userfeedback":
							case "2":
								fbFacade.printAllFeedbacks();
								break;
							case "new userfeedback":
							case "3":
								System.out.println("Please enter the feedback content");
								String content=input.nextLine();
								System.out.println("Please enter the rating of the feedback");
								int rating=input.nextInt();
								input.nextLine();
								auth.insertUserFeedBackAuth(usr, new userFeedback(-99,content,"",rating,usr.getID()));
								qr.getUserFeedBacks();
								break;
							case "enter criminal person":
							case "4":
								w0:while(true) {
									System.out.println("\nPlease enter the type of the person, the types are as follows"
											+"\nEnter missing or 1 to enter missing people"
											+"\nEnter most wanted or 2 to enter most wanted people"
											+"\nEnter exit or 99 to exit this branch"
											);
									String personInserter=input.nextLine();
									s0:switch(personInserter) {
									case "missing":
									case "1":
										System.out.println("Entering new missing person");
										//String name, int age, String phoneNumber, CrimeReport report
										System.out.println("Please enter the name of the person");
										String mName=input.nextLine();
										System.out.println("Please enter the age of the person");
										int mAge=input.nextInt();
										input.nextLine();
										System.out.println("Please enter the phone number of the person");
										String mPhoneNumber=input.nextLine();
										CriminalPerson mPerson=new MissingPeople(mName,mAge,mPhoneNumber,null);
										//String reportName, String reportLocation,String reportContent, int cpID,int uID
										System.out.println("Please enter the name of the report");
										String mReportName=input.nextLine();
										System.out.println("Please enter the location of the report");
										String mReportLocation=input.nextLine();
										System.out.println("Please enter the content of the report");
										String mReportContent=input.nextLine();
										CrimeReport mReport=pFacade.createCrimeReport(mReportName, mReportLocation, mReportContent,"missing people report",mPerson.getID(),usr.getID());
										mPerson.setCrimeReport(mReport);
										pFacade.holder.addCriminalPerson(mPerson);
										auth.insertReportLoggedIn(usr, mReport);
										auth.insertCriminalPersonLoggedIn(usr, mPerson);
										qr.getAllCriminalPersonFromDB();
										break;
									case "most wanted":
									case "2":
										System.out.println("Entering new most wanted");
										//String name, int age, String phoneNumber, CrimeReport report
										System.out.println("Please enter the name of the person");
										String mwName=input.nextLine();
										System.out.println("Please enter the age of the person");
										int mwAge=input.nextInt();
										input.nextLine();
										System.out.println("Please enter the phone number of the person");
										String mwPhoneNumber=input.nextLine();
										CriminalPerson mwPerson=new MostWanted(mwName,mwAge,mwPhoneNumber,null);
										//String reportName, String reportLocation,String reportContent, int cpID,int uID
										System.out.println("Please enter the name of the report");
										String mwReportName=input.nextLine();
										System.out.println("Please enter the location of the report");
										String mwReportLocation=input.nextLine();
										System.out.println("Please enter the content of the report");
										String mwReportContent=input.nextLine();
										CrimeReport mwReport=pFacade.createCrimeReport(mwReportName, mwReportLocation, mwReportContent,"most wanted report",mwPerson.getID(),usr.getID());
										mwPerson.setCrimeReport(mwReport);
										pFacade.holder.addCriminalPerson(mwPerson);
										auth.insertReportLoggedIn(usr, mwReport);
										auth.insertCriminalPersonLoggedIn(usr, mwPerson);
										qr.getAllCriminalPersonFromDB();
										break;
									case "exit":
									case "99":
										//look at this
										break w0;
									default:
										System.out.println("Wrong choice try agin");
										break;
									}
								}
							break;
							//one problem with basic report thing
							case "new report":
							case "5":
								w1:while(true) {
									System.out.println("Please enter the type, "
											+ "\nEnter complaint or 1 for complaint"
											+ "\nEnter basic report or 2 for basic report"
											+"\nEnter exit or 99 to exit this branch");
									String rType=input.nextLine();
									switch(rType) {				
									//reportName,reportLocation,reportContent,-1,uID
									case "complaint":
									case "1":
										System.out.println("Please enter the complaint name");
										String cName=input.nextLine();
										System.out.println("Please enter the complaint location");
										String cLocation=input.nextLine();
										System.out.println("Please enter the content of the complaint");
										String cContent=input.nextLine();
										CrimeReport complaint=pFacade.createCrimeReport(cName, cLocation, cContent, "complaint", -1, usr.getID());
										auth.insertReportLoggedIn(usr, complaint);
										qr.getAllCriminalPersonFromDB();
										break;
									case "basic report":
									case "2":
										System.out.println("Please enter the basic report name");
										String brName=input.nextLine();
										System.out.println("Please enter the basic report location");
										String brLocation=input.nextLine();
										System.out.println("Please enter the content of the basic report");
										String brContent=input.nextLine();
										CrimeReport basicReport=pFacade.createCrimeReport(brName, brLocation, brContent, "complaint", -1, usr.getID());
										auth.insertReportLoggedIn(usr, basicReport);
										qr.getAllCriminalPersonFromDB();
										break;
									case "exit":
									case "99":
										break w1;
									}
								}
								break;
							//look at notification creation
							case "edit report":
							case "6":
								w2:while(true) {
									System.out.println("Please enter the type of the report you want to update"
											+"\nThe types are as follows"
											+"\nEnter 1 or basic to edit basic report"
											+"\nEnter 2 or complaint to edit complaint"
											+"\nEnter 3 or missing people report to edit missing people report"
											+"\nEnter 4 or most wanted report to edit most wanted peope report"
											+"\nEnter 99 or exit to exit this branch"
											);
									String reqType=input.nextLine();
									reqType=reqType.toLowerCase();
									switch(reqType) {
									case "basic report":
									case "1":
										System.out.println("Printing all basic reports");
										uFacade.getHolder().printAllUsersReportWithType("basic report");
										System.out.println("Please enter the id of the report you want to change");
										int bid=input.nextInt();
										input.nextLine();
										System.out.println("Please enter the new content");
										String breportContent=input.nextLine();
										System.out.println("Please enter the new status");
										String breportStatus=input.nextLine();
										auth.updateReportAuth(usr, bid, breportContent, breportStatus);
										break;
									case "complaint":
									case "2":
										System.out.println("Printing complaints");
										uFacade.getHolder().printAllUsersReportWithType("complaint");
										System.out.println("Please enter the id of the report you want to change");
										int cid=input.nextInt();
										input.nextLine();
										System.out.println("Please enter the new content");
										String creportContent=input.nextLine();
										System.out.println("Please enter the new status");
										String creportStatus=input.nextLine();
										auth.updateReportAuth(usr, cid, creportContent, creportStatus);
										break;
									case "missing people report":
									case "3":
										System.out.println("Printing all missing people reports");
										pFacade.printAllReportsByType("missing people report");
										System.out.println("Please enter the id of the report you want to change");
										int mid=input.nextInt();
										input.nextLine();
										System.out.println("Please enter the new content");
										String mreportContent=input.nextLine();
										System.out.println("Please enter the new status");
										String mreportStatus=input.nextLine();
										auth.updateReportAuth(usr, mid, mreportContent, mreportStatus);
										qr.getAllCriminalPersonFromDB();
										break;
									case "most wanted report":
									case "4":
										System.out.println("Printing all most wanted reports");
										pFacade.printAllReportsByType("missing people report");
										System.out.println("Please enter the id of the report you want to change");
										int mwid=input.nextInt();
										input.nextLine();
										System.out.println("Please enter the new content");
										String mwreportContent=input.nextLine();
										System.out.println("Please enter the new status");
										String mwreportStatus=input.nextLine();
										auth.updateReportAuth(usr, mwid, mwreportContent, mwreportStatus);
										qr.getAllCriminalPersonFromDB();
										break;
									case "exit":
									case "99":
										break w2;
									}
								}
								break;
							case "message":
							case "7":
								wm:while(true) {
									System.out.println("Printing all users");
									qr.printUserNameID();
									System.out.println("Please enter an user id to send message");
									System.out.println("Or enter exit or 99 to exit");
									String coa=input.nextLine();
									if(coa.equalsIgnoreCase("exit") || coa.equalsIgnoreCase("99")) {
										break wm;
									}
									int UID=Integer.parseInt(coa);
									if(qr.validateUserIDExists(UID)) {
										System.out.println("Please enter the message you want to send");
										String messageContent=input.nextLine();
										auth.setUserMessagesAuthLoggedIn(usr, messageContent, usr.getID(), UID);																												
									}else {
										System.out.println("No user with that name try again");
									}
								}
								break;
							case "continue":
							case "98":
								System.out.println("Exiting the creation system");
								insideSystem=false;
								break wi;
							case "exit":
							case "99":
								System.out.println("Exiting to the OS");
								qr.flipLoggedIn("F", usr.getID());
								System.exit(1);
								break;
							default:
								System.out.println("Wrong input try again");
								break;		
							}
						}
						if(insideSystem) {
							while(true) {
								System.out.println("What is the next course of action\n"
										+ "To enter with another user type 1 or logout\n"
										+ "To continue into the system again type 2 or continue\n"
										+ "To exit the system type 99 or exit");
								String courseOfAction=input.nextLine();
								courseOfAction=courseOfAction.toLowerCase();
								boolean breakLoop=false;
								switch(courseOfAction) {
								case "logout":
								case "1":
									System.out.println("Loggin out");
									insideSystem=false;
									breakLoop=true;
									qr.flipLoggedIn("F", usr.getID());
									usr=null;
									break;
								case "continue":
								case "2":
									System.out.println("Continuing to the system");
									breakLoop=true;
									break;
								case "exit":
								case "99":
									System.exit(1);
									break;
								default:
									System.out.println("Wrong input try again");
									break;
								}
								if(breakLoop) {
									break;
								}
							}
						}
					}
			}
		}
	}

