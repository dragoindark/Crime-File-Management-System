package mainPackage;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class PopulateDB {
	public PopulateDB() {
		qr=QueryRunner.QueryRunner();
		rand=new Random(); //gets random number as [0,the upper limit -1]
	}
	public static void configure() {
		String sql="SET FOREIGN_KEY_CHECKS=0;";
		try {
			PreparedStatement stmt=QueryRunner.QueryRunner().getDatabaseConnector().getConnection().prepareStatement(sql);
		    stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
       	
	}
	public static void main(String[] args) throws SQLException {
		PopulateDB pdb=new PopulateDB();
		//pdb.populateUser();
		System.out.println(new Encrypter().shaWithMD5Encrypter("güvenlisifre"));
		System.out.println(new Encrypter().shaWithMD5Encrypter("canimanam"));
		System.out.println(new Encrypter().shaWithMD5Encrypter("arthasdidnothingwrong"));
		
		//pdb.populateCriminalPerson();	
		//pdb.populateHotNews();
		//pdb.populateUFB();
		//pdb.populateUMessages();
		//pdb.populateUNotifications();
		
	}
	public void populateUNotifications() {
		int[] mID= {2,3,4,5,6,7};
		int[] senderID= {0,1,2,3};
		int[] recieverID= {6,7,8,9};
		String[] messageContent= {"5 yeni mesaj","Raporunuz guncellendi"
				,"Sedat peker yeni video cikardi","Guncelleme getirildi",
				"Takip ettiginiz bir suclu yakalandı","Allah kurtarsin"};
		String[] sentAt= {"22.06","23.07","24.08","25.09","26.10","27.11"};
		for(int i=0;i<mID.length;i++) {
			int ranNum=rand.nextInt(senderID.length);
			qr.setUserNotifications(messageContent[i], senderID[ranNum], recieverID[ranNum]);
		}
	}
	public void populateUMessages() {
		int[] mID= {2,3,4,5,6,7};
		int[] senderID= {0,1,2,3};
		int[] recieverID= {6,7,8,9};
		String[] messageContent= {"Merhabalar nasilsin","Projeyi naptin"
				,"Son sedat peker videosunu izledin mi","Pazar isin var mi",
				"Gelecek hakkinda hic endisem yok","Allah kurtarsin"};
		String[] sentAt= {"22.06","23.07","24.08","25.09","26.10","27.11"};
		for(int i=0;i<mID.length;i++) {
			int ranNum=rand.nextInt(senderID.length);
			qr.setUserMessages(messageContent[i], senderID[ranNum], recieverID[ranNum]);
		}
	}
	
	public void populateUFB() {
		int[] fID= {1,2,3,4,5,6};
		String[] fbContent= {"Kullanimi cok kolay","Arkadaslarima onerdim","Mukkemel uygulama 10/10",
				"Cok hizli calisiyor","istedigimiz yerden ulasabiliyoruz"
				,"Sayesinde bir cok suclunun yakalanmasina yardim ettim"
		};
		int[] uID= {0,5,6,7,8,9};
		for(int i=0;i<fID.length;i++) {
			int randomNum=rand.nextInt(6);
			int randomRating=rand.nextInt(11);
			Feedback fb=new userFeedback(fID[i],fbContent[i],"22",randomRating,uID[randomNum]);
			qr.insertUserFeedBack(fb);
		}
	}
	public void populateHotNews() {
		int[] hnID= {0,1,2,3,4,5};
		String[] hnContent= {"Bu ay toplamda 100 kisi yakalandi","1000 kullaniciya ulasildi",
				"Guvenliginiz bizim icin önemli","Bize geri donutlerinizi gonderin",
				"Sistem 2.0 a guncellendi","IoT ile beraber suclular makalemizi inceleyin"
		};
		int[] adminID= {10,11,12};
		for(int i=0;i<hnID.length;i++) {
			int randomNum=rand.nextInt(3);
			News n=new Hotnews(hnID[i],adminID[randomNum],hnContent[i],"22");
			qr.insertHotnews(n);
		}
	}
	public void populateUser() {
		String[] userName= {"sedat","arthas","garrosh","ezgi","baris","servan","gulsah","serhat"};
		String[] passwords= {"güvenlisifre","canimanam","arthasdidnothingwrong"};
		for(int i=0;i<5;i++) {
			int rNumPas=rand.nextInt(3);
			User u=new NormalUser(userName[i],passwords[rNumPas]);
			qr.insertUserWithObject(u);
		}
		for(int i=5;i<8;i++) {
			int rNumPas=rand.nextInt(3);
			User u=new admin(userName[i],passwords[rNumPas]);
			qr.insertUserWithObject(u);
		}
	}
	public void populateCriminalPerson() {
		String[] missingPeopleName= {"ayse","fatma","hayriye","alex"};
		int[] missingPeopleAge= {102,42,14,7};
		String[] missingPeoplePhoneNumber= {"5558884422","6669995533","7770006644","8881117733"};
		String[] mostWantedName= {"jack","thomas","arthur","phoebe"};
		int[] mostWantedAge= {20,22,27,86};
		String[] mostWantedPhoneNumber= {"0001112233","1112223344","2223334455","33344445566"};
		String[] dummyReportNames={"izmirde Hirsizlik","Adanada kacirma","Ugandada cinayet","ankarada soygun"};
		String[] dummyReportNames2={"buyuculer kacti","orgimmara baskin","olanlara inanamiyacaksiniz","kayincosuyla evlenen adam"};
		String[] dummyLocationNames= {"Cennet mahallesi","Kurtlar vadisi","Orgrimmar","Stormwind","Yaprak Dokumundeki Kosk","Izmir"};
		String[] dummyContentNames= {"Mahallede kavga","Racon kestiler","Efsane kurtlar vadisi raconlari","Benim kizim yapmaz","Yine orklar","Adanalilar sicak diye günese sikti"};
		int[] uIDs= {6,7,8,9};
		for(int i=0;i<missingPeopleName.length;i++) {
			int rNumPas=rand.nextInt(mostWantedName.length);
			int rNumDummy=rand.nextInt(dummyReportNames.length);
			MissingPeople p=new MissingPeople(missingPeopleName[i],missingPeopleAge[i],
					missingPeoplePhoneNumber[rNumPas],null);
			CrimeReport misRep=new MissingPeopleReport(dummyReportNames[i],
					dummyLocationNames[rNumDummy],dummyContentNames[rNumDummy],p.getID(),uIDs[i]);
			p.setCrimeReport(misRep);
			System.out.println("Inserting missing people report");
			qr.insertReport(misRep);
			System.out.println("Inserting missing people");
			System.out.println(p.toString());
			qr.insertCriminalPerson(p);
			MostWanted mwP=new MostWanted(mostWantedName[i],mostWantedAge[i],
					mostWantedPhoneNumber[rNumPas],null);
			int rNumDummy2=rand.nextInt(dummyReportNames.length);
			while(rNumDummy2==rNumDummy) {
				rNumDummy2=rand.nextInt(dummyReportNames.length);
			}
			CrimeReport misRepMW=new MostWantedReport(dummyReportNames2[rNumDummy],
					dummyLocationNames[rNumDummy],dummyContentNames[rNumDummy],mwP.getID(),uIDs[i]);
			mwP.setCrimeReport(misRepMW);
			System.out.println("Inserting most wanted people report");
			qr.insertReport(misRepMW);
			System.out.println("Inserting most wanted people");
			System.out.println(mwP.toString());			
			qr.insertCriminalPerson(mwP);
		}
	}
	private QueryRunner qr;
	private Random rand;
}
