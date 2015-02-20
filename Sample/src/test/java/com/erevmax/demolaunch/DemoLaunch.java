package com.erevmax.demolaunch;
import static com.erevmax.utils.DataBase.closeConnection;
import static com.erevmax.utils.DataBase.executeQuery;
import static com.erevmax.utils.DataBase.getRateTigerDataBaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;


public class DemoLaunch {
	
	@Test
	public void open(){
		//FirefoxProfile profile = new FirefoxProfile();
		//profile.setEnableNativeEvents(true);
		//WebDriver driver = new FirefoxDriver(profile);
		//WebDriver driver= new FirefoxDriver("firefox.bin","");
		//driver.get("http://rediffmail.com");
		

		String q=" SELECT  MS.MR_Schedule_ID, MS.Hotel_Code, HM.Hotel_Name, MS.Exec_Date_Time_GMT,"
				+" MS.Status, CS.Client_Noadults,MSR.Recurrence, MSR.Report_Days, CS.Client_Curr,"
				+" CS.Client_Source, CS.Report_Rate_Type, QA.Admin_Login,QA.QA_Notes"
				+" FROM dbo.RT_MR_SCHEDULES MS WITH(NOLOCK) left outer join RT_MR_QA_ASSIGN QA "
				+" on MS.Hotel_Code=QA.Hotel_Code inner join  RT_HOTEL_MAST HM "
				+" on MS.Hotel_Code = HM.Hotel_Code inner join  RT_CLIENT_SUBS CS "
				+" on MS.Hotel_Code = CS.Hotel_Code  INNER JOIN RT_MR_SCHEDULE_RECURRENCE MSR"
				+" on CS.Hotel_Code = MSR.Hotel_Code and MS.Report_Base_Date = MSR.Report_Base_Date "
				+" where MS.Report_Base_Date ='2014-09-24'";
	List<String> a=new ArrayList<String>();
	List<String> b=new ArrayList<String>();
	List<String> c=new ArrayList<String>();
	List<String> d=new ArrayList<String>();
	List<String> e=new ArrayList<String>();
	List<String> f=new ArrayList<String>();
	List<String> g=new ArrayList<String>();
	List<String> h=new ArrayList<String>();
		
	//	public  void checkDataBaseForStringList(String q){
			Connection con= getRateTigerDataBaseConnection();
			ResultSet rs=executeQuery(q,con);	
			try {
				while(rs.next()){
				
					String t1=rs.getString("Hotel_Name").trim();	
				
					 a.add(t1);
				
					 String t2=rs.getString("Report_Days").trim();	
				
					 b.add(t2);
				
					 String t3=rs.getString("Client_Noadults").trim();	
				
					 c.add(t3);
					
					 String t4=rs.getString("Client_Source").trim();	
					
					 d.add(t4);
					
					 String t5=rs.getString("Report_Rate_Type").trim();	 
				
					 e.add(t5);
				
					 String t6=rs.getString("Client_Curr").trim();	
				
					 f.add(t6);
					 
					 String t7=rs.getString("Status").trim();	
				
					 g.add(t7);
					
					 String t8=rs.getString("QA_Notes").trim();	 
					
					 h.add(t8);
				
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("sql exception occured.");
			}
			System.out.println(a.size());
			System.out.println(b.size());
			System.out.println(c.size());
			System.out.println(d.size());
			System.out.println(e.size());
			System.out.println(f.size());
			System.out.println(g.size());
			System.out.println(h.size());
			closeConnection(con,null,rs,null);
			//System.out.println(list);
			
		}
		
	//}

}
